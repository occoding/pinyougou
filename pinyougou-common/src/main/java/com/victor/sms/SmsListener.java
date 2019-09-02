package com.victor.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class SmsListener implements MessageListener {
    @Autowired
    private SmsUtil smsUtil;


    @Override
    public void onMessage(Message message) {
        MapMessage map=(MapMessage) message;
        System.out.println("静茹方法");
        String phoneNumber= null;
        String randomCode= null;
        try {
            phoneNumber = map.getString("mobile");
            randomCode = map.getString("param");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        smsUtil.sendMessage(phoneNumber,randomCode);
    }
}
