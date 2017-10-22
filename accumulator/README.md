# accumulator

The accumulator project is composed of three main components:

* The [accumulator-scoreGame](id:https://github.com/pablote3/basketball-java/tree/master/accumulator/accumulator-scoreGame) application retrieves game, roster, and standings statistics for NBA games and stores in a relational database

  The application uses Spring Integration to control application flow and relies on [accumulator-services](id:https://github.com/pablote3/basketball-java/tree/master/accumulator/accumulator-services) to supply Spring components 
* The [accumulator-loadSchedule](id:https://github.com/pablote3/basketball-java/tree/master/accumulator/accumulator-loadSchedule) application loads a .csv file of scheduled games and stores in a relational database

  The application relies on [accumulator-services](id:https://github.com/pablote3/basketball-java/tree/master/accumulator/accumulator-services) to supply Spring components
* The [accumulator-services](id:https://github.com/pablote3/basketball-java/tree/master/accumulator/accumulator-services) application provides sharable Spring componets extended from Spring Boot projects

## Getting Started

These instructions will get a local instance of the project up and running for development and testing purposes

## Installing

Clone [basketball repository](id:https://github.com/pablote3/basketball-java) and import into your favorite IDE

## Running Unit Tests

Unit tests execute against mock data external to the application

  Copy testUnit folder to directory on local file system

    https://drive.google.com/open?id=0ByBsbTluZmwKa3NFTENYcWlDSDQ

  Update test/resources/service.properties

    replace xmlstats.fileBoxScore value with path to fileBoxScore directory under testUnit
        
    replace xmlStats.fileRoster value with path to fileRoster directory under testUnit
        
    replace xmlstats.fileStandings value with path to fileStandings directory under testUnit

## Running System Tests

System tests uses the file system to supply JSON input files and MySQL database to persist results.

  Copy testSystem folder to directory on local file system
   
    https://drive.google.com/open?id=0ByBsbTluZmwKa3NFTENYcWlDSDQ
    
  Update main/resources/service.properties
  
    replace xmlstats.fileBoxScore value with path to fileBoxScore directory under testSystem
        
    replace xmlStats.fileRoster value with path to fileRoster directory under testSystem
        
    replace xmlstats.fileStandings value with path to fileStandings directory under testSystem


Install MySQL on Linux based system running command

    sudo apt-get install mysql-server
 
Create database schema using mysql command

    mysql -u root -p CREATE SCHEMA `accumulate_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
    
Load database by running mysql command from testSystem directory

    mysql -u root -p accumulate_test < accumulate_systemTest_20161026.sql
   
Package application by running command from accumulator directory

    mvn package
    
Launch system tests by running command from project target directory
    
    java -DgameDate="2016-10-27" -DgameTeam="" -jar accumulator-scoreGame.jar
