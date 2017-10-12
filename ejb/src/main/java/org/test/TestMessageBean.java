package org.test;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

@MessageDriven(activationConfig =
{
@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
@ActivationConfigProperty(propertyName="destination", propertyValue="queue/test")
})
public class TestMessageBean implements MessageListener {
	
	private static final Logger LOG = Logger.getLogger(TestMessageBean.class);

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				LOG.info("JMS: name = " + textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
				LOG.error("JMS: unexpected error", e);
			}
		} else {
			LOG.info("JMS: message is not TextMessage");
		}
	}
}
