package org.gdut.ptg.util.engine;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 消息引擎
 * 
 * @author ht
 * 
 */
public class MessageEngine {

	private final Log logger = LogFactory.getLog(MessageEngine.class);
	private JavaMailSender mailSender;

	private String fromUser = "";

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 发送邮件
	 * 
	 * @param msg
	 *            邮件对象
	 * @param ifHtml
	 *            是否为html内容
	 */
	public void sendMail(SimpleMailMessage msg) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage);
			mimeMessageHelper.setSubject(msg.getSubject());
			mimeMessageHelper.setTo(msg.getTo());
			if (msg.getCc() != null)
				mimeMessageHelper.setCc(msg.getCc());
			if (msg.getBcc() != null)
				mimeMessageHelper.setBcc(msg.getBcc());
			mimeMessageHelper.setFrom(fromUser);
			mimeMessageHelper.setSentDate(new Date());
			mimeMessageHelper.setText(msg.getText(), true);
			mailSender.send(mimeMessage);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}
}
