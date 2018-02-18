package com.appvizory.houzing.qpidmsg;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created by elflord on 2/17/18.
 */


    public class HelloWorld {
        public static void main(String[] args) throws Exception {

            HelloWorld hw = new HelloWorld();
            hw.testHelloMsg();
        }

        public void testHelloMsg(){
            try {
                // The configuration for the Qpid InitialContextFactory has been supplied in
                // a jndi.properties file in the classpath, which results in it being picked
                // up automatically by the InitialContext constructor.


                Context context = new InitialContext();

                ConnectionFactory factory = (ConnectionFactory) context.lookup("myFactoryLookup");
                Destination queue = (Destination) context.lookup("myQueueLookup");


                Connection connection = factory.createConnection(System.getProperty("USER"), System.getProperty("PASSWORD"));
                connection.setExceptionListener(new MyExceptionListener());
                connection.start();

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                MessageProducer messageProducer = session.createProducer(queue);
                MessageConsumer messageConsumer = session.createConsumer(queue);

                TextMessage message = session.createTextMessage("Hello world!");
                messageProducer.send(message, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
                TextMessage receivedMessage = (TextMessage) messageConsumer.receive(2000L);

                if (receivedMessage != null) {
                    System.out.println(receivedMessage.getText());
                } else {
                    System.out.println("No message received within the given timeout!");
                }

                connection.close();
            } catch (Exception exp) {
                System.out.println("Caught exception, exiting.");
                exp.printStackTrace(System.out);
                System.exit(1);
            }
        }

        private static class MyExceptionListener implements ExceptionListener {
            @Override
            public void onException(JMSException exception) {
                System.out.println("Connection ExceptionListener fired, exiting.");
                exception.printStackTrace(System.out);
                System.exit(1);
            }
        }
    }

