package com.illia.riasurfing.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class ScheduledTasks {
    private static final Logger LOG = LogManager.getLogger(ScheduledTasks.class);
    public static final int LAST_DAY_NEW_TICKETS_INDEX = 11;
    public static final int COUNTPAGE = 10;
    private String from;
    private String subjectDefault;
    private String textDefault;
    private String rootLink;
    public static final String SORTING_BY_ID = "id";
    private CustomRequestService requestService;
    private UserService userService;
    private MailingService mailingService;
    private HttpClientService clientService;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss d-M-yyyy");

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

    @Value("${mail.default.text}")
    public void setTextDefault(String textDefault) {
        this.textDefault = textDefault;
    }


    private static final String TEXT_BODY_CONTENT_LINKS = "<a href=\"%s\">%s</a>";
    private static final String TEXT_BODY_CONTENT = "%s за день появилось %s новых объявлений(я) - %s.<br>";
    private static final String TEXT_BODY = "<h1>Добрый день %s!</h1>" +
            "<p>По вашим запросам:<br>" +
            "%s</p>" +
            "С уважением,<br>Помошник RIA.";

    @Deprecated
    private ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Scheduled(cron = "${cron.scheduler}")
    public void manageSubscription() throws IOException {
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

            System.out.println(mapper.writeValueAsString(page));
            int pages = page.getTotalPages();

            StringBuilder textBodyContent = new StringBuilder();
            do {
                int newPosts;

                for (CustomRequest next : page) {
                    StringBuilder textBodyContentLinks = new StringBuilder();

                    String auto = "";
                    next.setTop(LAST_DAY_NEW_TICKETS_INDEX);
                    next.setCountpage(COUNTPAGE);
                    final List<IdSearchResponseSlim> searchResults = clientService.getIdSearchResponseSlims(next);
                    if (searchResults.size() > 0) {
                        auto = searchResults.get(0).getMarkName();
                        System.out.println("auto: " + auto);
                    }

                    newPosts = searchResults.size();
                    for (IdSearchResponseSlim singleResult : searchResults) {
                        textBodyContentLinks.append(String.format(TEXT_BODY_CONTENT_LINKS,
                                rootLink + singleResult.getLinkToView(), singleResult.getTitle()));
                        System.out.println("textBodyContentLinks: " + textBodyContentLinks);
                    }
                    textBodyContent.append(String.format(TEXT_BODY_CONTENT, auto, newPosts, textBodyContentLinks.toString()));
                    System.out.println("textBodyContent: " + textBodyContent);
                }


            } while (--pages > 0);

            text = String.format(TEXT_BODY, user.getNickname(), textBodyContent.toString());
            try {
                mailingService.sendEmail(from, to, subject, text);
                LOG.debug(String.format("manageSubscription(): subject=%s to=%s, text=%s", subject, to, text));
            } catch (MessagingException e) {
                LOG.error(String.format("manageSubscription(): ex=%s", e), e);
                e.printStackTrace();
            }

        }
    }

    @Scheduled(cron = "${cron.scheduler.clear}")
    public void deleteOldSearchRequests() {
        LocalDateTime age = LocalDateTime.now().minusMonths(3);
        requestService.deleteByTimeCreatedBefore(Timestamp.valueOf(age).getTime());
        LOG.debug(String.format("deleted subscriptions with age above: %s", age));
    }

}
