package com.kyleli.factsms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSManager {
    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public SMSManager() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendText(String body, String phone) {
        Message.creator(
                        new PhoneNumber(phone),
                        new PhoneNumber(System.getenv("TWILIO_NUMBER")),
                        body
                )
                .create();
    }
}
