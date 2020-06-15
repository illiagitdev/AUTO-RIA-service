package com.illia.riasurfing.scheduler;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.search.request.CustomRequest;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponseSlim;
import com.illia.riasurfing.mailing.MailingService;
import com.illia.riasurfing.service.CustomRequestService;
import com.illia.riasurfing.service.HttpClientService;
import com.illia.riasurfing.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class ScheduledTasks {
    private static final Logger LOG = LogManager.getLogger(ScheduledTasks.class);
    public static final int LAST_DAY_NEW_TICKETS_INDEX = 11;
    public static final int COUNTPAGE = 10;
    public static final int LIFESPAN_MONTHS = 3;
    public static final String SORTING_BY_ID = "id";
    public static final int CURRENCY_USD_INDEX = 1;


    private String from;
    private String subjectDefault;
    private String rootLink;
    private CustomRequestService requestService;
    private UserService userService;
    private MailingService mailingService;
    private HttpClientService clientService;

    @Autowired
    public ScheduledTasks(CustomRequestService requestService, MailingService mailingService,
                          HttpClientService clientService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
        this.mailingService = mailingService;
        this.clientService = clientService;
    }

    @Value("${base.ria.url}")
    public void setRootLink(String rootLink) {
        this.rootLink = rootLink;
    }

    @Value("${mail.default.from}")
    public void setFrom(String from) {
        this.from = from;
    }

    @Value("${mail.default.subject}")
    public void setSubjectDefault(String subjectDefault) {
        this.subjectDefault = subjectDefault;
    }

    private static final String TEXT_BODY_CONTENT_LINKS = "<a href=\"%s\">%s</a> %s USD, ";
    private static final String TEXT_BODY_CONTENT = "%s за день появилось %s новых объявлений(я) - %s.<br>";
    private static final String TEXT_BODY = "<h1>Добрый день %s!</h1><p>По вашим запросам:<br>%s</p>" +
            "С уважением,<br>Помошник RIA.";

    @PostConstruct
    public void initTime(){}

    @Scheduled(cron = "${cron.scheduler:0 0 6 * * ?}")
    public void manageSubscription() throws IOException {
        LOG.info("manageSubscription() started...");
        List<Integer> usersWithSubscription = requestService.getUserUdsWithSubscription(true);
        String subject;
        String text;
        String to;

        Page<CustomRequest> page;
        for (Integer userId : usersWithSubscription) {
            Pageable p = PageRequest.of(0, 50, Sort.by(SORTING_BY_ID).ascending());
            page = requestService.getAllSubscriptionForUser(userId, p);


            User user = userService.getUser(userId);
            subject = String.format(subjectDefault, user.getNickname());
            to = user.getEmail();

            int pages = page.getTotalPages();

            StringBuilder textBodyContent = new StringBuilder();
            do {
                int newPosts;

                for (CustomRequest next : page) {
                    StringBuilder textBodyContentLinks = new StringBuilder();

                    String auto = "";
                    next.setTop(LAST_DAY_NEW_TICKETS_INDEX);
                    next.setCurrency(CURRENCY_USD_INDEX);
                    next.setCountpage(COUNTPAGE);
                    final List<IdSearchResponseSlim> searchResults = clientService.getIdSearchResponseSlims(next);
                    if (searchResults.size() > 0) {
                        auto = searchResults.get(0).getMarkName();
                    }

                    newPosts = searchResults.size();
                    for (IdSearchResponseSlim singleResult : searchResults) {
                        textBodyContentLinks.append(String.format(TEXT_BODY_CONTENT_LINKS,
                                rootLink + singleResult.getLinkToView(), singleResult.getTitle(), singleResult.getUsd()));
                    }
                    textBodyContent.append(String.format(TEXT_BODY_CONTENT, auto, newPosts,
                            textBodyContentLinks.toString()));
                }


            } while (--pages > 0);

            text = String.format(TEXT_BODY, user.getNickname(), textBodyContent.toString());
            try {
                LOG.debug(String.format("manageSubscription(): subject=%s to=%s, text=%s", subject, to, text));
                mailingService.sendEmail(from, to, subject, text);
            } catch (MessagingException e) {
                LOG.error(String.format("manageSubscription(): exception=%s", e), e);
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "${cron.scheduler.clear}")
    public void deleteOldSearchRequests() {
        LocalDateTime age = LocalDateTime.now().minusMonths(LIFESPAN_MONTHS);
        requestService.deleteByTimeCreatedBefore(Timestamp.valueOf(age).getTime());
        LOG.debug(String.format("deleted subscriptions with age above: %s", age));
    }

}
