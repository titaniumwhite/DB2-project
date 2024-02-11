# Telco Web App #
### @Politecnico di Milano, Academic Year 2021-2022, Data Bases 2 ###
This repository contains the Java project for the Data Bases 2 course. The project's primary goal is to develop a web interface enabling end-users to interact with a MySQL Database using the Java Persistence API. This repository contains the Java code for the web component (presentation layer), the Java code for the EJB component (business layer), the DDL SQL scripts for creating and managing the database (data layer), the needed triggers and the web interface.

## Requested Requirements ##
- Easy Log-in and Sign-in
- Two applications: one for Customers and one for Employees
### Customer Application ###
- Multiple basic Service Packages available
- Possibility to associate a Service Package to a Validity Period (with different monthly fee)
- Possibility to associate a Service Package to one or more Optional Products (e.g., an SMS news feed, an internet TV channel, etc.), each one with an additional monthly fee
- Possibility to personalize Start and End data of subscription
- Alert in case of three insolved payments
### Employee Application ###
- Possbility to create new Service Packages with different possible Optional Products to associate
- Possibility to create a summary Sales Report page

## Technlogies Used ##
- Java Enterprise Edition
- MySQL (Query, Materialized Views, Triggers)
- Bootstrap

## Learn More ##
Check out our [presentation](./presentation.pdf) for more details.
