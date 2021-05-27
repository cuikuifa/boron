package cn.od.moutian.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送类
 */
@Slf4j
public class MailSender {

	private static String smtpServer = ResourceMessageUtils.getMessage("email_smtpServer", "smtp.exmail.qq.com"); // smtp地址
	private static String userName = ResourceMessageUtils.getMessage("email_username", "monitor@hunteryx.com"); // 邮箱登录名monitor@pushyx.com
	private static String password = ResourceMessageUtils.getMessage("email_password", "Hunter&8888"); // 邮箱密码pushTest8888
	// 记录所有附件文件的集合,发送邮件的附件
	List<String> attachments = new ArrayList<String>();

	// 建立会话
	private static MimeMessage message;
	private static Session session;

	// 将字符串转换为中文,否则标题会发生乱码现象,QQ邮箱为UTF-8.用GBK.GB2312都会乱码.
	public String translateChinese(String strText) {
		try {
			// 字符集编码时使用 "Q" 编码；其他情况使用 "B" 编码。
			strText = MimeUtility.encodeText(new String(strText.getBytes(), "UTF-8"), "UTF-8", "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}

	// 增加附件
	public void addAttachment(String fname) {
		attachments.add(fname);
	}

	public void send(String subject, String content) {
		String receivers = ResourceMessageUtils.getMessage("receivers", "");
		if (StringUtils.isBlank(receivers)) {
			log.error("获取邮件接收人为空");
		} else {
			this.send(subject, content, receivers);
		}
	}

	/**
	 * 组装表格
	 */
	public boolean send(String[] header, List<List<String>> list, String title, String noticeList) {
		try {
			if (list.size() > 0) {
				StringBuffer tableContent = new StringBuffer(
						"<table style=\"width:100%;background-color:#FFFFFF;text-align:center;\" cellspacing=\"0\" cellpadding=\"2\" bordercolor=\"#000000\" border=\"2\" align=\"center\"><tbody>");
				String tableBegin = "<tr>";
				String tableEnd = "</tbody></table><br /><span id=\"transmark\"></span><br />";
				tableContent.append(tableBegin);

				// 表头
				for (String content : header) {
					tableContent.append("<td>").append(content).append("<br/></td>");
				}
				tableContent.append("</tr>");
				// 表内容
				for (List<String> object : list) {
					tableContent.append("<tr style=\"color:#FF0000;\">");
					for (String content : object) {
						tableContent.append("<td>").append(content).append("<br/></td>");
					}
					tableContent.append("</tr>");
				}
				tableContent.append(tableEnd);
				send(title, tableContent.toString(), noticeList);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean send(String[] header, String[] header2, List<List<String>> list, List<List<String>> list2, String title, String noticeList) {
		try {
			String table = "";
			if (list.size() > 0) {
				StringBuffer tableContent = new StringBuffer(
						"<table style=\"width:100%;background-color:#F65A7C;\" cellspacing=\"0\" cellpadding=\"2\" bordercolor=\"#0B9D94\" border=\"2\" align=\"center\"><tbody>");
				String tableBegin = "<tr>";
				String tableEnd = "</tbody></table><br /><span id=\"transmark\"></span><br />";
				tableContent.append(tableBegin);

				// 表头
				for (String content : header) {
					tableContent.append("<td>").append(content).append("<br/></td>");
				}
				tableContent.append("</tr>");
				// 表内容
				for (List<String> object : list) {
					tableContent.append(tableBegin);
					for (String content : object) {
						tableContent.append("<td>").append(content).append("<br/></td>");
					}
					tableContent.append("</tr>");
				}
				tableContent.append(tableEnd);
				table = tableContent.toString();
			}
			if (list2.size() > 0) {
				StringBuffer tableContent = new StringBuffer(
						"<table style=\"width:100%;background-color:#F65A7C;\" cellspacing=\"0\" cellpadding=\"2\" bordercolor=\"#0B9D94\" border=\"2\" align=\"center\"><tbody>");
				String tableBegin = "<tr>";
				String tableEnd = "</tbody></table><br /><span id=\"transmark\"></span><br />";
				tableContent.append(tableBegin);

				// 表头
				for (String content : header2) {
					tableContent.append("<td>").append(content).append("<br/></td>");
				}
				tableContent.append("</tr>");
				// 表内容
				for (List<String> object : list2) {
					tableContent.append(tableBegin);
					for (String content : object) {
						tableContent.append("<td>").append(content).append("<br/></td>");
					}
					tableContent.append("</tr>");
				}
				tableContent.append(tableEnd);
				table = table + "<br>" + tableContent.toString();
			}
			if (StringUtils.isNotBlank(table)) {
				send(title, table, noticeList);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 *  组装表格
	 */
	public boolean send(String[] header, List<List<String>> list, String title, String noticeList, String backgroud) {
		try {
			if (list.size() > 0) {
				StringBuffer tableContent = new StringBuffer("<table style=\"width:100%;background-color:" + backgroud
						+ ";\" cellspacing=\"0\" cellpadding=\"2\" bordercolor=\"#0B9D94\" border=\"2\" align=\"center\"><tbody>");
				String tableBegin = "<tr>";
				String tableEnd = "</tbody></table><br /><span id=\"transmark\"></span><br />";
				tableContent.append(tableBegin);

				// 表头
				for (String content : header) {
					tableContent.append("<td>").append(content).append("<br/></td>");
				}
				tableContent.append("</tr>");
				// 表内容
				for (List<String> object : list) {
					tableContent.append(tableBegin);
					for (String content : object) {
						tableContent.append("<td>").append(content).append("<br/></td>");
					}
					tableContent.append("</tr>");
				}
				tableContent.append(tableEnd);
				send(title, tableContent.toString(), noticeList);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 发送邮件
	 *
	 */
	public void send(String subject, String content, String receive) {
		receive = receive.replaceAll(";", ",");
		String resultMsg = "";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", smtpServer);
		props.setProperty("mail.smtp.port", "25");

		props.put("mail.smtp.auth", "true");
		// 重写Authenticator类的getPasswordAuthentication()方法, 以确保对该邮箱有发送邮件的权利。
		session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		// session.setDebug(true);
		message = new MimeMessage(session);
		try {
			// 发件人
			InternetAddress from = new InternetAddress(userName);
			from.setPersonal("mm");
			message.setFrom(from);
			// 收件人
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receive));
			// 邮件标题
			message.setSubject(subject);
			// 邮件内容,也可以使纯文本"text/plain"
			message.setContent(content, "text/html;charset=utf-8");
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(smtpServer, userName, password);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			resultMsg = "send email success!";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(String.format("|receive:%s|subject:%s|content:%s|resultMsg:%s|errocause:%s|", receive, subject, content, e.getMessage(), e.getCause()));
		} finally {
			log.info(String.format("|receive:%s|subject:%s|content:%s|resultMsg:%s|", receive, subject, content, resultMsg));
		}
	}

	/**
	 * 发送带有附件的邮件
	 *
	 */
	public void send(String subject, File file, String receive) {
		receive = receive.replaceAll(";", ",");
		String resultMsg = "";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", smtpServer);
		props.setProperty("mail.smtp.port", "587");

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.timeout", "5000");
		props.put("mail.smtp.connectiontimeout", "5000");
		// 重写Authenticator类的getPasswordAuthentication()方法, 以确保对该邮箱有发送邮件的权利。
		session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		// session.setDebug(true);
		message = new MimeMessage(session);
		try {
			// 发件人
			InternetAddress from = new InternetAddress(userName);
			from.setPersonal("mm");
			message.setFrom(from);
			// 收件人
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receive));
			// 邮件标题
			message.setSubject(subject);
			// 邮件内容,也可以使纯文本"text/plain"
			Multipart multipart = new MimeMultipart();
			BodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
			multipart.addBodyPart(attachmentBodyPart);
			message.setContent(multipart);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(smtpServer, userName, password);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			resultMsg = "send email success!";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(String.format("|receive:%s|subject:%s|content:%s|resultMsg:%s|errocause:%s|", receive, subject, file.getName(), e.getMessage(), e.getCause()));
		} finally {
			log.info(String.format("|receive:%s|subject:%s|content:%s|resultMsg:%s|", receive, subject, file.getName(), resultMsg));
		}
	}

	/**
	 *  异常监控
	 */
	public void sendException(String title, String receive) {
		send(title, title, receive);
	}

	public void sendWorning() {
		List<List<String>> contentList = new ArrayList<List<String>>();
		List<String> content = new ArrayList<String>();
		content.add("htpay");
		content.add("htpay-retransmission");
		content.add("addQrcode");
		content.add("there was no qrcode added in ten minutes！");
		contentList.add(content);
		content = new ArrayList<String>();
		content.add("支付平台");
		content.add("接口转发节点");
		content.add("二维码接收接口");
		content.add("十分钟内没有二维码上传！");
		contentList.add(content);
		String[] header = { "项目名", "模块名", "功能点", "预警内容" };
		this.send(header, contentList, "10分钟内无二维码上传预警", "lugzh@hunteryx.com,1107022342@qq.com");
	}


	public static void main(String[] args) {
		MailSender sender = new MailSender();
		/*List<List<String>> contentList = new ArrayList<List<String>>();
		List<String> content = new ArrayList<String>();
		content.add("1");
		content.add("名称123");
		content.add("test123");
		contentList.add(content);
		String[] header = { "编号", "名称", "数据" };
		sender.send(header, contentList, "测试标题", "xinwen@hunteryx.com,1107022342@qq.com");*/
		sender.sendWorning();
	}
}
