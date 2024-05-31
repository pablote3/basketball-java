# Accumulator

Accumulator is a set of Java applications to gather and sweeten basketball statistics

It is composed of three main components:

* [accumulator-scoreGame](https://github.com/pablote3/basketball-java/tree/master/accumulator/accumulator-scoreGame) retrieves game, roster, and standings statistics for NBA games and stores in a relational database

* [accumulator-loadSchedule](https://github.com/pablote3/basketball-java/tree/master/accumulator/accumulator-loadSchedule) loads a .csv file of scheduled games and stores in a relational database

* [accumulator-services](https://github.com/pablote3/basketball-java/tree/master/accumulator/accumulator-services) provides sharable Spring componets for JPA, client, and business services

## Getting Started

These instructions will get a local instance of the project up and running for development and testing purposes

## Installation

* Clone or download [basketball repository](https://github.com/pablote3/basketball-java)

* Import accumulator project into your favorite IDE

## Unit Test Preparation

Unit tests execute against in memory H2 database for JPA and mock data for components

  ##### Copy testUnit folder to directory on local file system

    https://drive.google.com/open?id=0ByBsbTluZmwKa3NFTENYcWlDSDQ

  ##### Update accumulator-services/src/test/resources/application.properties

    replace xmlstats.fileBoxScore value with path to fileBoxScore directory under testUnit
        
    replace xmlStats.fileRoster value with path to fileRoster directory under testUnit
        
    replace xmlstats.fileStandings value with path to fileStandings directory under testUnit

## Unit Test Execution for services

  Run all tests in package for module accumulator-services

## System Test Preparation

System tests uses the file system to supply JSON input files and MySQL database to persist results

  ##### Copy testSystem folder to directory on local file system
   
    https://drive.google.com/open?id=0ByBsbTluZmwKa3NFTENYcWlDSDQ
    
  ##### Update accumulator-services/src/main/resources/application.properties
  
    replace xmlstats.fileBoxScore value with path to fileBoxScore directory under testSystem
        
    replace xmlStats.fileRoster value with path to fileRoster directory under testSystem
        
    replace xmlstats.fileStandings value with path to fileStandings directory under testSystem
    
  ##### Update accumulator-loadSchedule/src/main/resources/application.properties???
      
    replace loader.fileSchedule value with path to fileLoad directory under testSystem

  ##### Install MySQL on Linux based system running command

    sudo apt-get install mysql-server
 
  ##### Create database schema using mysql command

    mysql -u root -p CREATE SCHEMA `accumulate_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
      
## System Test Execution for scoreGame

  ##### Reset database to 10/26/2016 by running mysql command from testSystem directory

    mysql -u root -p accumulate_test < accumulate_systemTest_20161026.sql
   
  ##### Package application by running command from accumulator root directory

    mvn package
     
  Launch system tests by running commands from accumulator-scoreGame target directory
    
  ##### No roster changes 
    java -DgameDate="2016-10-27" -DgameTeam="sacramento-kings" -jar accumulator-scoreGame.jar
  
  ##### 1 roster change
    java -DgameDate="2016-10-27" -DgameTeam="chicago-bulls" -jar accumulator-scoreGame.jar
  
  ##### 2 roster changes
    java -DgameDate="2016-10-27" -DgameTeam="atlanta-hawks" -jar accumulator-scoreGame.jar
  
  ##### Remaining games for date
    java -DgameDate="2016-10-27" -DgameTeam="" -jar accumulator-scoreGame.jar

## System Test Execution for loadSchedule

  ##### Reset database to 10/26/2016 by running mysql command from testSystem directory

    mysql -u root -p accumulate_test < accumulate_systemTest_20161026.sql
   
  ##### Package application by running command from accumulator root directory

    mvn package
     
  ##### Launch system tests by running commands from accumulator root directory
  
    java -DfileName="Schedule_2017-2018.csv" -jar accumulator-loadSchedule.jar
