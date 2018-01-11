package com.fang.mail.service;

/**
 * *********************************************************************
 *  邮件测试服务接口 <br/>
 * IMailTestService.java <br/>
 *
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @copyright   Copyright: 2015-2020
 * @creator     fxf <br/>
 * @create-time 2018年1月11日 下午11:15:57
 * @revision    1
 **********************************************************************
 */
public interface IMailTestService {

	void sendMailWithoutVelocity() throws Exception;

	void sendMailWithVelocity() throws Exception;
}
