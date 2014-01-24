ciklum-test-app
===============
This is a test application that shows how to easily create web applications in Java, if you select the right technology. 

This application allows you to work with data by HTTP/1.1 in JSON format.

To create an application, I used the following technologies:
-------------------------
* MongoDB - NoSQL Document-oriented database which provides easy way to persist your data and without relational    complexity using
Link: http://www.mongodb.org/
* Dropwizard - fast, light-weight, easy-coding framework which provides fast creation web-apps. Dropwizard has out-of-the-box support for sophisticated configuration, application metrics, logging, operational tools, and much more, allowing you and your team to ship a production-quality HTTP+JSON web service in the shortest time possible.
* JUnit framework for testing.
* Maven - Maven is a build automation tool for Java projects.

How to run application
-------------------------
For starting application you need to do this steps:
1.  Download MongoDB from http://www.mongodb.org/downloads for your's OS and start it
2.  Have ready installed Maven 3.X version
3.  Get the source from GitHub
  
  git clone https://github.com/stalkermn/ciklum-test-app.git

4.  Enter to the directory and run Maven:
  
  mvn install

5.  In "/target" directory you will have a .jar file, that can be started by next command:

  java -jar {application-name}.jar

For all questions, please email me: 
* valerij.vasilkov@gmail.com



URL Mappings:
-------------------------
    GET     /address/{id}         -   get the address by id
    POST    /address/             -   add new address
    PUT     /address/{id}         -   update the address by id
    DELETE  /address/{id}         -   delete address by id
    
    GET     /address/{id}/phones  -   get address phones by address id
    POST    /address/{id}/phone   -   add phone to address by  id
    DELETE  /address/{id}/phone   -   delete phone by address id
    
    GET     /                     -   "Hello world" test message
