/**
 * 
 */
package util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * Title: cypudong<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-9-7 下午3:13:35 <br>
 * @author freeway
 */
public class EmailHelper {
	private static final String sendFrom = "ftc@ftczj.com";
    private static final String password = "Cturl2018";
    private static final String host = "smtp.ftczj.com";//阿里企业邮箱格式  smtp.+绑定域名，
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    
    public static void sendEmail(String togmail, String title, String content) throws MessagingException{
    	 Properties props = new Properties();
         props.put("mail.smtp.host", host);//指定邮件的发送服务器地址
         props.put("mail.smtp.auth", "true");//服务器是否要验证用户的身份信息
         
         //阿里云服务器25端口官方禁用,用465需要SSL协议发送，所以用到下面这行
         props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
         props.setProperty("mail.smtp.port", "465");
         props.setProperty("mail.smtp.socketFactory.port", "465");
         Session session = Session.getInstance(props);//得到Session
         session.setDebug(true);//代表启用debug模式，可以在控制台输出smtp协议应答的过程
         //创建一个MimeMessage格式的邮件
         MimeMessage message = new MimeMessage(session);
         //设置发送者
         Address fromAddress = new InternetAddress(sendFrom);//邮件地址
         message.setFrom(fromAddress);//设置发送的邮件地址
         //设置接收者
         Address toAddress = new InternetAddress(togmail);//要接收邮件的邮箱
         message.setRecipient(RecipientType.TO, toAddress);//设置接收者的地址
         //设置邮件的主题
         message.setSubject(title);
         //设置邮件的内容
         message.setText(content);
         //保存邮件
         message.saveChanges();
         //得到发送邮件的服务器(这里用的是smtp服务器)
         Transport transport = session.getTransport("smtp");
         //发送者的账号连接到smtp服务器上  @163.com可以不写
         transport.connect(host,sendFrom,password);
         //发送信息
         transport.sendMessage(message, message.getAllRecipients());
         //关闭服务器通道
         transport.close();
    }
    public static void main(String[] args) throws MessagingException {
    	sendEmail( "670713590@qq.com", "标题测试", "内容测试");
	}
}
