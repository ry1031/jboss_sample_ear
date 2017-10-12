package org.test;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

@Stateless
public class WelcomeService {
	
	@EJB
	private ConfigRepo repo;

	public String getWelcomeMsg(String name) {
		
		String prefix = repo.getConfigVal("prefix1");
		
		String returnMsg = prefix + name;
		
		QueueConnection connection = null;
		Queue que = null;
		try {
			InitialContext ic = new InitialContext();
			QueueConnectionFactory qcf = (QueueConnectionFactory) ic
					.lookup("java:/ConnectionFactory");
			connection = qcf.createQueueConnection();
			que = (Queue) ic.lookup("queue/test");
			QueueSession session = connection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			TextMessage textMsg = session.createTextMessage(name);
			QueueSender sender = session.createSender(que);
			sender.send(textMsg);
			sender.close();
			
	        session.close();
	        connection.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		return returnMsg;
	}
}
