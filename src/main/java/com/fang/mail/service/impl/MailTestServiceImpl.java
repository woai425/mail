package com.fang.mail.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fang.mail.pojo.Member;
import com.fang.mail.service.IMailSenderService;
import com.fang.mail.service.IMailTestService;
import com.fang.mail.service.MailConfigDTO;
import com.fang.mail.service.VelocityConfigDTO;
/**
 * *********************************************************************
 *   邮件测试服务类实现<br/>
 * MailTestServiceImpl.java <br/>
 *
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @copyright   Copyright: 2015-2020
 * @creator     fxf <br/>
 * @create-time 2018年1月11日 下午11:16:26
 * @revision    1
 **********************************************************************
 */
@Service
public class MailTestServiceImpl implements IMailTestService {

    Logger logger = Logger.getLogger(MailTestServiceImpl.class);

    @Autowired
    private IMailSenderService mailSenderService;

    //从spring加载的配置文件中读取
    @Value("${MAIL_HOST}")
    private String MAIL_HOST;
    @Value("${MAIL_USERNAME}")
    private String MAIL_USERNAME;
    @Value("${MAIL_PASSWORD}")
    private String MAIL_PASSWORD;
    @Value("${MAIL_FROM}")
    private String MAIL_FROM;
    @Value("${MAIL_TO}")
    private String MAIL_TO;
    @Value("${MAIL_SUBJECT}")
    private String MAIL_SUBJECT;
    @Value("${VM_LOCATION}")
    private String VM_LOCATION;

	@Override
	public void sendMailWithoutVelocity() throws Exception {
		MailConfigDTO mailConfigDTO = new MailConfigDTO(MAIL_HOST, MAIL_USERNAME, MAIL_PASSWORD, MAIL_FROM);
		mailConfigDTO.setTo(MAIL_TO);
		mailConfigDTO.setSubject(MAIL_SUBJECT);
		String emailContent = "hello,everybody! this is a email without velocity";
		mailSenderService.sendMail(mailConfigDTO, emailContent);
		
	}
	
	@Override
	public void sendMailWithVelocity() throws Exception {
		MailConfigDTO mailConfigDTO = new MailConfigDTO(MAIL_HOST, MAIL_USERNAME, MAIL_PASSWORD, MAIL_FROM);
		mailConfigDTO.setTo(MAIL_TO);
		mailConfigDTO.setSubject(MAIL_SUBJECT);
		VelocityConfigDTO velocityConfigDTO = new VelocityConfigDTO();
        String templateLocation = VM_LOCATION;
        velocityConfigDTO.setTemplateLocation(templateLocation);
        
        Map<String, Object> velocityContext = new HashMap<String,Object>();
		velocityContext.put("userName", "FXF");
		velocityContext.put("email", MAIL_FROM);
		List<Member> members = new ArrayList<Member>();
        members.add(new Member("张三",20,"male"));
        members.add(new Member("李四",21,"male"));
        members.add(new Member("王五",22,"female"));
        velocityContext.put("members", members);
		velocityConfigDTO.setVelocityContext(velocityContext );
		
		mailSenderService.sendMail(mailConfigDTO, velocityConfigDTO);
	}
}