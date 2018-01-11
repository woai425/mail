package com.fang.mail.service;

import java.util.Map;

/**
 * *********************************************************************
 * 模板引擎配置 <br/>
 * VelocityConfigDTO.java <br/>
 *
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @copyright   Copyright: 2015-2020
 * @creator     fxf <br/>
 * @create-time 2017年4月24日 下午2:56:40
 * @revision    $Id:  *
 **********************************************************************
 */
public class VelocityConfigDTO {
	private static final String DEFAULT_ENCODING = "UTF-8";

	String templateLocation;// 模板引擎的位置
	String encoding = DEFAULT_ENCODING;// 模板引擎的默认编码方式
	Map<String, Object> velocityContext;// 模板引擎中页面参数映射

	/**
	 * no parameter construtor
	 */
	public VelocityConfigDTO() {
		super();
	}
	
	/**
	 * 构造模板引擎
	 * 
	 * @param templateLocation
	 * @param velocityContext
	 */
	public VelocityConfigDTO(String templateLocation,Map<String, Object> velocityContext){
		this.templateLocation = templateLocation;
		this.velocityContext = velocityContext;
	}
	
	/**
	 * 获取模板位置
	 * @return
	 */
	public String getTemplateLocation() {
		return templateLocation;
	}

	/**
	 * 设置模板位置
	 * @param templateLocation 模板路径
	 */
	public void setTemplateLocation(String templateLocation) {
		this.templateLocation = templateLocation;
	}

	/**
	 * 获取模板内容的编码格式
	 * @return
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * 获取模板内容的编码格式
	 * @param encoding 编码格式
	 * @return
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * 获取模板中的填充内容
	 * @return
	 */
	public Map<String, Object> getVelocityContext() {
		return velocityContext;
	}

	/**
	 * 设置模板内容，填充模板变量
	 * @param velocityContext
	 */
	public void setVelocityContext(Map<String, Object> velocityContext) {
		this.velocityContext = velocityContext;
	}
}
