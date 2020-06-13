package com.illia.riasurfing.mailing;

import org.springframework.stereotype.Service;

@Service
public interface MailingService {

    void sendEmail(String[] to, String subject, String text);

}
