package com.illia.riasurfing.scheduler;

import com.illia.riasurfing.dao.CustomRequestRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Transactional
public class ScheduledTasks {
    private static final Logger LOG = LogManager.getLogger(ScheduledTasks.class);
    private CustomRequestRepository requestRepository;

    public ScheduledTasks(CustomRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Scheduled(cron = "${cron.scheduler.clear}")
    public void deleteOldSearchRequests(){
        LocalDateTime age = LocalDateTime.now().minusMonths(3);
        requestRepository.deleteByTimeCreatedBefore(Timestamp.valueOf(age).getTime());
        LOG.debug(String.format("deleted subscriptions with age above: %s", age));
    }

}
