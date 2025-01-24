# ITIS_Maven_Example
A HelloWorld example that uses maven to handle the log4j dependency.

This repository has been created for a course I'm teaching at ITTS Vito Volterra (TPSIT for the classes 5A and 5B).

## How to use

1. Clone the repository (use git clone or an IDE of your choice to clone)
2. Install maven (follow the slide and the tutorial)
3. Build the project and run it.

## log4j2

I've chosen log4j as a library to install and use, because logging will be a fundamental part of the project (don't use sysout)

Logs work in level, these are the most important:
1. DEBUG
2. INFO
3. WARN
4. ERROR

If we set the project log level to "DEBUG" it will enable all the logs from DEBUG onward (DEBUG, INFO, WARN, ERROR)

If we set the project log level to "INFO" it will enable all the logs from INFO onward (INFO, WARN, ERROR)

If we set the project log level to "WARN" it will enable all the logs from WARN onward (WARN, ERROR)

If we set the project log level to "ERROR" it will enable all the logs from ERROR onward (ERROR)

This means that we can write code like
```java
logger.debug("This is a debug message!");
logger.info("This is an info message!");
logger.warn("This is a warning message!");
logger.error("This is an error message!");
```

and change in runtime the log level to hide specific messages (or show them).
