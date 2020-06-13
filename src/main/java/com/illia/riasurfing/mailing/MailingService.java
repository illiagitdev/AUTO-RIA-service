package com.illia.riasurfing.mailing;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface MailingService {

    void sendEmail(String from, String to, String subject, String text) throws MessagingException;

}
