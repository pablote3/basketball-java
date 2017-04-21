# accumulator-scoreGame

The accumulator-scoreGame application retrieves game, roster, and standings statistics for NBA games and stores in a relational database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Installing

Clone [pablote3/basketball](id:https://github.com/pablote3/basketball) project into favorite IDE.

Unit tests can be run by simply cloning the project.  System testing requires a MySQL database.

## Running Unit Tests

Unit tests will complete without additional configuration.  For Spring Integration tests to test functionality, they require JSON input files to be supplied on the file system.

Copy testIntegration folder to directory on local file system

    https://drive.google.com/open?id=0ByBsbTluZmwKa3NFTENYcWlDSDQ

Update test/resources/service.properties

    xmlstats.fileBoxScore: append "~/pdrive/pwork/basketball/accumulator" with location of testIntegration fileBoxScore directory
    
    xmlStats.fileRoster: append "~/pdrive/pwork/basketball/accumulator" with location of testIntegration fileRoster directory
    
    xmlstats.fileStandings: append "~/pdrive/pwork/basketball/accumulator" with location of testIntegration fileStandings directory

## Running System Tests

System tests require MySQL database to persist data and file system to supply JSON input files.

Install MySQL on Linux based system using command

    sudo apt-get install mysql-server

Create database schema using mysql command

    mysql -u root -p CREATE SCHEMA `accumulate_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

Update main/resources/application.properties

    hibernate.hbm2ddl.auto= to hibernate.hbm2ddl.auto=create
    
Create tables by running command from project directory

    mvn clean install
 
Reset main/resources/application.properties

    hibernate.hbm2ddl.auto=create to hibernate.hbm2ddl.auto=

Copy testSystem folder to directory on local file system

    https://drive.google.com/open?id=0ByBsbTluZmwKa3NFTENYcWlDSDQ

Load database by running mysql command from testSystem directory

    mysql -u root -p accumulate_test < accumulate_systemTest_20161026.sql

Update main/resources/service.properties

    xmlstats.fileBoxScore: append "~/pdrive/pwork/basketball/accumulator" with location of testSystem fileBoxScore directory
    
    xmlStats.fileRoster: append "~/pdrive/pwork/basketball/accumulator" with location of testSystem fileRoster directory
    
    xmlstats.fileStandings: append "~/pdrive/pwork/basketball/accumulator" with location of testSystem fileStandings directory
    
Package application by running command from project directory

    mvn package
    
Launch system tests by running command from project target directory
    
    java -DgameDate="2016-10-27" -DgameTeam="" -jar accumulator-scoreGame.jar
    
## Deployment

Add additional notes about how to deploy this on a live system
