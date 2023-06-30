# SpringGroupProject
Spring microservices group
Developer Project for Bootcamp 
Ojas Gupta and Chase McCoy

 

Project Name – User Appointment Management System 

 

Project Description – This is a web-based project that is aimed at providing the interface for managing personal appointments for a given user. The system is going to be used by front-desk people working at a facility. The system should be generic enough so that it can be used by various lines of Businesses. 

 

Use Case Diagram –  

 

UserService: 

 

SelectUser 

ListUsers 

DeleteUser 

CreateUser 

UpdateUser 

 

 

AppointmentService: 

ListAppts 

CreateAppt 

UpdateApt 

GetAppt 

DeleteAppt 

 

 

 

Basic Entity Objects 

  

 

Service View 

 

 

Text BoxShape 

 

Text Box 

Shape 

Text BoxShape 

 

 

 

 

 

 

Technical Details 

 

Install a SQL DB like Postgres or MySQL or oracle express edition (light weight and comes with test data tables by default for reference). 

Create a user that would have right privileges to access the necessary tables. This is the user that would be used for Spring JDBC or Hibernate 

Design and create the tables for Basic Entity Objects. 

Install the latest eclipse version or IntelliJ or Spring Tool suite  

Create a Java project that will have the DAO layer to access the User related tables. The project should utilize the Java persistence & JPA annotations. Hibernate or Spring JDBC persistence framework should be used with JPA 

Create Junits to test the DAO project for operations getUser, updateUser, insertUser, deleteUser and listUsers(). 

The DAO layer should have the Java APIs for implementing CRUD with User/Comm model.  

The java project created above should utilize the Springboot framework and should be built using the maven or gradle dependencies 

Create a Java project that will expose REST APIs for consumption 

Create JUnits to test the REST service 

Spring REST should be used to implement the REST API  

All CRUD operations must be exposed with proper REST URI naming conventions & design principles 

This REST project should utilize Config service and have a relevant health check 

Create a Java project that will have the DAO layer to access the Appt related tables. The project should utilize the Java persistence & JPA annotations. Hibernate or Spring JDBC persistence framework should be used with JPA 

Create Junits to test the DAO project for operations getAppt, updateAppt, insertAppt, deleteAppt and listAppts(). 

The DAO layer should have the Java APIs for implementing CRUD with Appt/Comm model.  

The java project created above should utilize the Spring framework and should be built using the maven or gradle dependencies 

Repeat Step 6 for the Appt Service.  

Create a Java project that will have the 2 services User and Appt Service (management service) 

Design a web page to expose the User details with following functionality. 

Add users to the database 

Search users by first name or last name. 

Delete and update users on webpage. 

Design a web page to manage the Appt details with following functionality. 

Add/Update appts for a given user 

List appts. 

Delete appts on webpage. 

Create a web application utilizing the designed web pages and the Appt Management Service Java project 

Install Postman, SOAPUI or Chrome based REST plugin (postman) to test the REST services 

Once the web application is working for Users & Appts, secure the page using OAuth. 

 
