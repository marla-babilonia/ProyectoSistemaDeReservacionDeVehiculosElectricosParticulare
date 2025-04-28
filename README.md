## Electric Vehicle Reservation System
Welcome to our Program

The creators are:
Marla Babilonia, Pedro Bonilla, Pedro Soler

Now more than ever it is imperitive to search for ways to save our planet from pollution. With this program we propose a way! 

This simple java-based console application manages electric-vehicle reservations on the University of Puerto Rico Mayaguez console. 

The data is stores is csv files under resouces.

To use the program in windows enter the following lines in the command line:
- Get-ChildItem -Recurse -Filter *.class | Remove-Item
- javac -d bin src\CSVHandlers\*.java src\HelpfulClasses\*.java src\information\*.java src\menus\*.java src\*.java
- java -cp bin Main


## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- 'resources' : store all the data in csv files

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

scr has the following packages

information - where most important classes are stored such as users, reservations, vehicles, waitlist, and transactions

CSVHandlers - here are the classes that manage writing and updating csv's

HelpfulClasses - classes that were helpful for more readable code and manageable code 

menu - holds the menu

main.java - hit play 

## Prerequisites

- Java 11+ (tested)  
- No external libraries  


## Features

- **Users**  
  - Add / remove / modify users  
  - Enforce valid UPR e-mail, phone, and client/owner role  
- **Vehicles**  
  - Add / remove / modify vehicles  
  - Owners limited to 2 vehicles  
  - Tracks each vehicle’s availability schedule  
  - Updates station capacities as vehicles are added/removed  
- **Reservations**  
  - View / add / cancel / modify reservations  
  - Cost rules by vehicle type (bike, scooter, skateboard)  
  - Prevent double‐booking and self-booking at the same time  
  - Records each reservation as a transaction  
- **Waitlist**  
  - Add clients to a station waitlist when no vehicles are free  
  - View / remove waitlist entries  
- **Transactions**  
  - Auto-record credit transfers on reservation  
  - View / remove transaction history  


 

