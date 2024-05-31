
# Aggregator

Aggregator is a set of Java applications to flatten and enhance basketball statistics from a relational database populated by the accumulator applications

It is composed of four main components:

* [aggregator-teamBoxScore](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-teamBoxScore) stores game data from each teams perspective

* [aggregator-officialBoxScore](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-officialBoxScore) stores game data from each officials perspective

* [aggregator-playerBoxScore](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-playerBoxScore) stores game data from each players perspective

* [aggregator-standing](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-standing) stores standings data for each team every day during the season

* [aggregator-services](https://github.com/pablote3/basketball-java/tree/master/aggregator/aggregator-services) provides sharable Spring components


## Getting Started

These instructions will get a local instance of the project up and running for development and testing

## Installation

* Clone or download [basketball repository](https://github.com/pablote3/basketball-java)

* Import aggregator project into favorite IDE

## Running Unit Tests

Run all JUnit tests in the whole project

There are two types of tests that will be executed: 

* Unit tests are defined in test projects test processor logic and run against mock data

* Spring Batch automatically runs the jobs defined in the configuration files using the test resources 

    Data input into these tests are based off MySQL database generated from successful completion of [accumulator](https://github.com/pablote3/basketball-java/tree/master/accumulator) system tests.  

    Results are .csv extract files written to the extract directory.  These need to be confirmed manually by comparing against expectations.

    * OfficialBoxScoreTests extract file officialBoxScore.csv should contain 24 official entries from games played on 10/26/2016
    
    * PlayerBoxScoreTests extract file playerBoxScore.csv should contain 85 player entries from games played on 10/26/2016
    
    * TeamBoxScoreTests extract file teamBoxScore.csv should contain 8 team entries from games played on 10/26/2016
    
    * StandingTests extract file standings.csv should contain 30 entries from all teams on 10/26/2016

## System Test Preparation

Copy testSystem folder to directory on local file system
   
    https://drive.google.com/open?id=0ByBsbTluZmwKVm94SC1CdVVOYUE

System tests require MySQL database to provide and to persist data
    This should have been accomplished setting up the accumulator applications (see [accumulator](https://github.com/pablote3/basketball-java/tree/master/accumulator) project)
   
Create database schema using mysql command

    mysql -u root -p CREATE SCHEMA `aggregate_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
    
Load database by running mysql command from testSystem directory

    mysql -u root -p aggregate_test < aggregate_systemTest_20161026.sql

Package application by running command from aggregator directory

    mvn package
    
## System Test Execution

Launch OfficialBoxScore system tests by running command from project target directory
    java -DfromDate="2016-10-27" -DtoDate="2016-10-27" -jar aggregator-officialBoxScore.jar
    
Launch PlayerBoxScore system tests by running command from project target directory
    java -DfromDate="2016-10-27" -DtoDate="2016-10-27" -jar aggregator-playerBoxScore.jar
    
Launch TeamBoxScore system tests by running command from project target directory
    java -DfromDate="2016-10-27" -DtoDate="2016-10-27" -jar aggregator-teamBoxScore.jar

Launch Standing system tests by running command from project target directory
    java -DfromDate="2016-10-27" -DtoDate="2016-10-27" -jar aggregator-standing.jar
