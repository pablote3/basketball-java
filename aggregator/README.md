
# Aggregator

Aggregator is a set of Java applications to flatten and enhance basketball statistics from a relational database populated by the accumulator applications.

It is composed of four main components:

* [aggregator-teamBoxScore](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-teamBoxScore) stores game data from each teams perspective

* [aggregator-officialBoxScore](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-officialBoxScore) stores game data from each officials perspective

* [aggregator-playerBoxScore](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-playerBoxScore) stores game data from each players perspective

* [aggregator-standing](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-standing) stores standings data for each team every day during the season

* [aggregator-services](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-services) provides sharable Spring components


## Getting Started

These instructions will get a local instance of the project up and running for development and testing.

## Installation

* Clone or download [basketball repository](https://github.com/pablote3/basketball-java)

* Import aggregator project into favorite IDE.

## Running Unit Tests

Run all JUnit tests in the whole project.

There are two types of tests that will be executed: 

* Unit tests defined in test projects execute against mock data.  These essentially test the processors.

* Spring Batch will also run the jobs defined in the configuration file against the databases defined in test resources.  

  These tests launch automatically without interaction outside of property files.  Results need to be confirmed manually.


## Running System Tests

System tests require MySQL database to persist data and file system to supply JSON input files.

Install MySQL on Linux based system using command

    sudo apt-get install mysql-server
    
Copy testSystem folder to directory on local file system
   
    https://drive.google.com/open?id=0ByBsbTluZmwKa3NFTENYcWlDSDQ

Create database schema using mysql command

    mysql -u root -p CREATE SCHEMA `accumulate_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
    
Load database by running mysql command from testSystem directory

    mysql -u root -p accumulate_test < accumulate_systemTest_20161026.sql

Update main/resources/service.properties

    xmlstats.fileBoxScore: append "~/pdrive/pwork/basketball-java/accumulator" with location of testSystem fileBoxScore directory
    
    xmlStats.fileRoster: append "~/pdrive/pwork/basketball-java/accumulator" with location of testSystem fileRoster directory
    
    xmlstats.fileStandings: append "~/pdrive/pwork/basketball-java/accumulator" with location of testSystem fileStandings directory
    
Package application by running command from accumulator directory

    mvn package
    
Launch system tests by running command from project target directory
    
    java -DgameDate="2016-10-27" -DgameTeam="" -jar accumulator-scoreGame.jar
