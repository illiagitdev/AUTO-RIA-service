package com.illia.riasurfing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.mailing.AmazonSES;
import com.illia.riasurfing.mailing.GmailMailing;
import com.illia.riasurfing.mailing.MailingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CommonConfiguration {

    @Bean
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Profile("aws")
    public MailingService mailingServiceAWS() {
        return new AmazonSES();
    }

    @Bean
    @Profile("gmail")
    public MailingService mailingServiceGmail() {
        return new GmailMailing();
    }

}
