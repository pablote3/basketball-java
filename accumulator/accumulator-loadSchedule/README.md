# accumulator-loadSchedule

The accumulator-loadSchedule application loads a .csv file of scheduled games and stores in a relational database.  It relies on [accumulator-services](id:https://github.com/pablote3/basketball/tree/master/accumulator/accumulator-services) to supply Spring components.

## Getting Started

Instructions for installing and testing are provided in [accumulator-scoreGame](id:https://github.com/pablote3/basketball/tree/master/accumulator/accumulator-scoreGame)

## Running System Tests

Instructions for preparing MySQL database to persist data is provided in are provided in [accumulator-scoreGame](id:https://github.com/pablote3/basketball/tree/master/accumulator/accumulator-scoreGame)
 
System tests require JSON input files to be accessible from local file system.

Copy testSystem folder to directory on local file system

    https://drive.google.com/drive/folders/0ByBsbTluZmwKb2dGOE92bnRJY2c
 
Update main/resources/service.properties

    loader.fileSchedule: append "~/pdrive/pwork/basketball/accumulator" with location of testLoad directory

Package application by running command from accumulator directory

    mvn package
    
Launch system tests by running command from project target directory
    
    java -DfileName="Schedule_2017-2018.csv" -jar accumulator-loadSchedule.jar