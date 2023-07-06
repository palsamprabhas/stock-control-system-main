# STOCK-CONTROL-SYSTEM-API

## Prerequisites to build this module

* Install Java 11 version
* Install Maven 3.8 or above
* Install Mongo database

## Build

To build this module

```
# In the stock-control-system/stock-control-system-api directory run
mvn clean install
```

## Unit Tests

To run unit tests

```
# In the stock-control-system/stock-control-system-api directory run
mvn clean test
```

## Unit Tests Report

To get unit tests report

```
# In the stock-control-system/stock-control-system-api directory run
mvn test
 find the reports at target/site/jacoco/index.html
```

## Run Application

To run this module

```
# In the stock-control-system/stock-control-system-api directory run
mvn spring-boot:run
```