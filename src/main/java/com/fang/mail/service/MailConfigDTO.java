package com.fang.mail.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * *********************************************************************
 * 邮件参数配置 <br/>
 * MailConfigDTO.java <br/>
 *
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @copyright   Copyright: 2015-2020
 * @creator     fxf <br/>
 * @create-time 2017年4月24日 下午2:55:47
 * @revision    $Id:  *
 **********************************************************************
 */
public class MailConfigDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_PORT = 25;
	private static final String DEFAULT_PROTOCOL = "smtp";
	private static final String DEFAULT_ENCODING = "UTF-8";

	private Properties javaMailProperties = new Properties();// 邮件配置的一些属性
	private String protocol = DEFAULT_PROTOCOL;// 邮件协议，默认smtp
	private String host;// 邮件服务器名称
	private int port = DEFAULT_PORT;// 邮件服务器端口，默认25
	private String username;// 用户名，邮件服务器需要认证时使用
	private String password;// 用户密码，邮件服务器需要认证时使用
	private String from;// 邮件发件人地址
	private String aliasName;// 邮件发件人别名
	private String subject;// 邮件主题
	private String[] tos = new String[] {};// 收件人地址列表
	private String[] ccs = new String[] {};// 抄送人地址列表
	private String[] bccs = new String[] {};// 暗送人地址列表
	private String encoding = DEFAULT_ENCODING;// 编码格式，默认UTF-8
	private String contentIdName = "contentId";// 内嵌资源id名称,默认contentId
	private String contentLocationName = "contentLocation";// 内嵌资源位置名称,默认contentLocation
	private String attachmentNameName = "attachmentName"; // 附件名称的名称,默认attachmentName
	private String attachmentLocationName = "attachmentLocation";// 附件位置名称,默认attachmentLocation
	private List<Map<String, String>> contentList = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> attachmentList = new ArrayList<Map<String, String>>();

	/**
	 * no parameter constructor
	 */
	public MailConfigDTO() {
		super();
	}

	/**
	 * 初始化邮件服务器、用户名和密码、发件人
	 * 
	 * @param host
	 * @param username
	 * @param password
	 */
	public MailConfigDTO(String host, String username, String password, String from) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.from = from;
	}

	/**
	 * 初始化发件人、收件人、主题
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 */
	public MailConfigDTO(String from, String to, String subject) {
		this.from = from;
		this.tos = new String[]{to};
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String[] getTos() {
		return tos;
	}

	public void setTos(String[] tos) {
		this.tos = tos;
	}

	public String[] getCcs() {
		return ccs;
	}

	public void setCcs(String[] ccs) {
		this.ccs = ccs;
	}

	public String[] getBccs() {
		return bccs;
	}

	public void setBccs(String[] bccs) {
		this.bccs = bccs;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getContentIdName() {
		return contentIdName;
	}

	public void setContentIdName(String contentIdName) {
		this.contentIdName = contentIdName;
	}

	public String getContentLocationName() {
		return contentLocationName;
	}

	public void setContentLocationName(String contentLocationName) {
		this.contentLocationName = contentLocationName;
	}

	public String getAttachmentNameName() {
		return attachmentNameName;
	}

	public void setAttachmentNameName(String attachmentNameName) {
		this.attachmentNameName = attachmentNameName;
	}

	public String getAttachmentLocationName() {
		return attachmentLocationName;
	}

	public void setAttachmentLocationName(String attachmentLocationName) {
		this.attachmentLocationName = attachmentLocationName;
	}

	public List<Map<String, String>> getContentList() {
		return contentList;
	}

	public void setContentList(List<Map<String, String>> contentList) {
		this.contentList = contentList;
	}

	public List<Map<String, String>> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Map<String, String>> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public Properties getJavaMailProperties() {
		return javaMailProperties;
	}

	public void setJavaMailProperties(Properties javaMailProperties) {
		this.javaMailProperties = javaMailProperties;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTo(String to) {
		if (to != null) {
			this.tos = new String[] { to };
		}else{
			this.tos = new String[]{};
		}
	}

	public void setCc(String cc) {
		if (cc != null) {
			this.ccs = new String[] { cc };
		} else {
			this.ccs = new String[] {};
		}
	}

	public void setBcc(String bcc) {
		if (bcc != null) {
			this.bccs = new String[] { bcc };
		} else {
			this.bccs = new String[] {};
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
