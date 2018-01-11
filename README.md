# mail
使用spring + 模板引擎（velocity）发送邮件

Java中发送邮件基本上有以下几种形式：
1，使用原生的javaMail接口实现，
"javaMail，oracle公司发布的标准发送邮件接口"			
使用的接口或类：			
 	Properties	邮件参数配置	
 	Session	创建JavaMail环境	
 	Message	邮件内容	
 	Transport	发送邮件对象	
 	Address	解析邮件地址	
	Authenticator	验证用户身份	

2，使用spring封装的接口，底层还是调用javaMail实现
"SpringMail，spring框架封装的基于javaMail实现的发送邮件接口"			
  使用的接口或类：			
 	MailSender 	邮件发送器	
 	MailMessage 	邮件内容对象	
 	MimeMessageHelper 	辅助创建邮件内容	
 	MimeMessagePreparator 	邮件内容准备器	
			
主要将javaMail封装成发送和邮件内容两大块	

3，使用自己封装的发送邮件功能.		
"IMailSenderService，自己封装的基于SpringMail的发送邮件接口"			
使用的接口类：			
 	IMailSenderService及其实现类MailSenderServiceImpl	
 	velocity	封装邮件内容，不包括附件	
			
"将SpringMail封装成一个类类发送邮件，而邮件内容则通过模板引擎类实现"			
			
