package com.fang.mail.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/***********************************************************************
 * 邮件发送接口 <br/>
 * IMailSenderService.java <br/>
 *
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @copyright Copyright: 2015-2020
 * @creator fxf <br/>
 * @create-time 2017年4月24日 下午2:47:50
 * @revision $Id: *
 ***********************************************************************/
public interface IMailSenderService {
	/**
	 * 发送邮件,不使用模板引擎
	 * 
	 * @param mailConfigDTO
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public void sendMail(MailConfigDTO mailConfigDTO, String emailContent) throws AddressException, MessagingException,
			UnsupportedEncodingException;

	/**
	 * 发送邮件，使用模板引擎
	 * 
	 * @param mailConfigDTO
	 * @param velocityConfigDTO
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public void sendMail(MailConfigDTO mailConfigDTO, VelocityConfigDTO velocityConfigDTO) throws AddressException,
			MessagingException, UnsupportedEncodingException;

}
