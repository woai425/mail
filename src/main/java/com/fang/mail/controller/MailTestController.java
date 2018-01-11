package com.fang.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fang.mail.service.IMailTestService;

@Controller
@RequestMapping(value="/mailTest")
public class MailTestController {

    @Autowired
    private IMailTestService mailTestService;

    @RequestMapping(value = "/sendEmail/{flag}")
    public String sendMail(@PathVariable String flag) throws Exception{
    	if(flag == "0"){
    		mailTestService.sendMailWithoutVelocity();
    	}else{
    		mailTestService.sendMailWithVelocity();
    	}
        System.out.println("邮件发送成功!");
        return "success";
    }
    
}
