package jmi.services;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class Consumer {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination =  session.createQueue("ethnic");
        MessageConsumer consumer = session.createConsumer(destination);
        Scanner scanner = new Scanner(System.in);
        TextMessage message=null;
        String msg ="";
        do{
            System.out.println("Recieving mesage");
            message = (TextMessage)  consumer.receive();
            msg = message.getText();
            System.out.println(msg);
        }while (!msg.equalsIgnoreCase("lets wind up"));
        scanner.close();
        connection.close();
    }
}
