# qpid-spring-boot
A Helloworld Spring Boot Apache Qpid starter program for using Qpid infrastructure.

This Spring Boot application will send messages using JMS template scheduled every 5 seconds. Also contains a listener that receives and logs the message received.

To run the sample successfully, you MUST:

1. Create the queue after setting up Qpid broker 
2. Build and run the resulting jar like any Spring boot application.

Build from command line of the project root :  mvn clean install
Run the application using: java -jar target/qpidmsg-1.5.10.RELEASE.jar

For more information feel free to check [Spring Boot Apache Qpid](https://iamtrishulpani.wordpress.com/2018/02/18/spring-boot-apache-qpid-starter/)
To set up Qpid locally, I have an additional post at [https://github.com/trishulpani/qpid-helloworld](https://github.com/trishulpani/qpid-helloworld)