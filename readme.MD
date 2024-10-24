# Selenium TestNG Automation

This repository contains Selenium test cases written in Java using the TestNG framework for automated testing of [Entrata](https://www.entrata.com/) website.

## TestNG Suite Configuration

The test suite configuration is defined in the `testng.xml` file. The suite includes four test classes: `Listeners`, `BaseTest`, `ProductNevigation`. These classes are part of the `EntrataTests` package.

## Prerequisite
1. The pom.xml file is required to open for the dependencies.

### Steps to import the project( Used: Eclipse IDE )
1. Go to file>>Import>>Git>>Projects From Git>>Clone URI and provide the following URL
 https://github.com/
2. Use the required directory location

### Steps to run the project
1. In Project Explorer of Eclipse locate pom.xml and open it.
2. In the Project Explorer of Eclipse, locate testNG.xml( From the main directory).
3. Right-click on the "testNG.xml" file and Run as testNG test.

##To check the reports
1. Go to reports->index.html-> right click-> properties-> take the path
2. Paste the path in chrome.
3. Under reports we can also see screenshots of different Tests