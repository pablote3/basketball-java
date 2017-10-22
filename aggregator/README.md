
# aggregator-parent

The aggregator-parent application flattens and enhances basketball data from relational database into domain centric data perspectives.

## Getting Started

These instructions will get a local instance of the project up and running for development and testing.

## Installing

Clone [basketball repository](id:https://github.com/pablote3/basketball-java) into favorite IDE.

## Running Unit Tests

Unit tests execute against mock data. 

  Update test/resources/service.properties

    xmlstats.fileBoxScore: append "~/pdrive/pwork/basketball-test/accumulator" with location of testIntegration fileBoxScore directory
    
    xmlStats.fileRoster: append "~/pdrive/pwork/basketball/accumulator-test" with location of testIntegration fileRoster directory
    
    xmlstats.fileStandings: append "~/pdrive/pwork/basketball/accumulator-test" with location of testIntegration fileStandings directory
    
  Run all JUnit tests in package com.rossotti.basketball

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