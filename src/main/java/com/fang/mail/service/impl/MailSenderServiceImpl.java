package com.fang.mail.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.fang.mail.service.IMailSenderService;
import com.fang.mail.service.MailConfigDTO;
import com.fang.mail.service.VelocityConfigDTO;

/**
 * *********************************************************************
 * 邮件发送实现类 <br/>
 * MailSenderServiceImpl.java <br/>
 *
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @copyright   Copyright: 2015-2020
 * @creator     fxf <br/>
 * @create-time 2017年4月24日 下午2:54:32
 * @revision    $Id:  *
 **********************************************************************
 */
@Service("mailSenderService")
public class MailSenderServiceImpl implements IMailSenderService {

	@Autowired
	private JavaMailSenderImpl javaMailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	@Override
	public void sendMail(MailConfigDTO mailConfigDTO, String emailContent) throws AddressException, MessagingException,
			UnsupportedEncodingException {

		sendMail(mailConfigDTO, false, emailContent, null);
	}

	@Override
	public void sendMail(MailConfigDTO mailConfigDTO, VelocityConfigDTO velocityConfigDTO) throws AddressException,
			MessagingException, UnsupportedEncodingException {

		sendMail(mailConfigDTO, true, null, velocityConfigDTO);

	}

	private void sendMail(MailConfigDTO mailConfigDTO, boolean hasTemplate, String emailContext,
			VelocityConfigDTO velocityConfigDTO) throws AddressException, MessagingException,
			UnsupportedEncodingException {
		initMailProp(mailConfigDTO);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, mailConfigDTO.getEncoding());
		// 设置邮件头信息
		// 发件人
		String aliasName = mailConfigDTO.getAliasName();
		String from = mailConfigDTO.getFrom();
		if (StringUtils.isNotBlank(aliasName)) {
			messageHelper.setFrom(from, aliasName);
		} else {
			mimeMessage.setFrom(new InternetAddress(from));
		}

		// 收件人
		String[] tos = mailConfigDTO.getTos();
		setRecipients(mimeMessage, tos, RecipientType.TO);
		String[] ccs = mailConfigDTO.getCcs();
		setRecipients(mimeMessage, ccs, RecipientType.CC);
		String[] bccs = mailConfigDTO.getBccs();
		setRecipients(mimeMessage, bccs, RecipientType.BCC);
		// 主题
		mimeMessage.setSubject(mailConfigDTO.getSubject());

		// 设置邮件正文
		if (hasTemplate) {
			String mailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
					velocityConfigDTO.getTemplateLocation(), velocityConfigDTO.getEncoding(),
					velocityConfigDTO.getVelocityContext());
			messageHelper.setText(mailText, true);
		} else {
			messageHelper.setText(emailContext, true);
		}

		// 设置邮件附件
		List<Map<String, String>> attachmentList = mailConfigDTO.getAttachmentList();
		if ((attachmentList != null) && (attachmentList.size() > 0)) {
			for (Map<String, String> map : attachmentList) {
				messageHelper.addAttachment(map.get(mailConfigDTO.getAttachmentNameName()),
						new File(map.get(mailConfigDTO.getAttachmentLocationName())));
			}
		}

		// 设置邮件内嵌资源
		List<Map<String, String>> contentList = mailConfigDTO.getContentList();
		if ((contentList != null) && (contentList.size() > 0)) {
			for (Map<String, String> map : contentList) {
				messageHelper.addInline(map.get(mailConfigDTO.getContentIdName()),
						new File(map.get(mailConfigDTO.getContentLocationName())));
			}
		}

		// 发送邮件
		javaMailSender.send(mimeMessage);
	}
	
	/**
	 * 初始化邮件发送环境
	 * 
	 * @param mailConfigDTO
	 */
	private void initMailProp(MailConfigDTO mailConfigDTO) {
		String host = mailConfigDTO.getHost();
		if (StringUtils.isNotBlank(host)) {
			javaMailSender.setHost(host);
		}
		String username = mailConfigDTO.getUsername();
		if (StringUtils.isNotEmpty(username)) {
			javaMailSender.setUsername(username);
		}
		String password = mailConfigDTO.getPassword();
		if (StringUtils.isNotEmpty(password)) {
			javaMailSender.setPassword(password);
		}
		Properties mailProperties = mailConfigDTO.getJavaMailProperties();
		if (mailProperties.size() > 0) {
			javaMailSender.setJavaMailProperties(mailProperties);
		}
		javaMailSender.setPort(mailConfigDTO.getPort());
		javaMailSender.setProtocol(mailConfigDTO.getProtocol());
	}

	/**
	 * 设置邮件的接收者,然后放到邮件消息中
	 * 
	 * @param mailMessage
	 * @param recipients
	 * @param type
	 *            收件人、抄送人、暗送人
	 * @throws MessagingException
	 * @throws AddressException
	 */
	private void setRecipients(MimeMessage mailMessage, String[] recipients, RecipientType type)
			throws MessagingException, AddressException {
		if (recipients.length == 0) {
			if (RecipientType.TO.equals(type)) {
				throw new RuntimeException("收件人不能为空");
			}
		} else if (recipients.length == 1) {
			mailMessage.setRecipient(type, new InternetAddress(recipients[0]));
		} else {
			StringBuffer recipientAddr = new StringBuffer(recipients[0]);
			for (int i = 1; i < recipients.length; i++) {
				if (i < (recipients.length)) {
					recipientAddr.append(",");
				}
				recipientAddr.append(recipients[i]);
			}
			mailMessage.setRecipients(type, InternetAddress.parse(recipientAddr.toString()));
		}
	}

}
