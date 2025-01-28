# Multithreaded example of Producers and Consumers

## Rationale

The problem we have with this multithreaded application is:

1. We have multiple threads producing values;
2. We have multiple threads reading values;
3. When someone is consuming it will delete the value;
4. When someone is producing no one can read nor produce.

This solution is not bulletproof. It contains some logic bugs that should be fixed by the students.

## Explanation of the logical bug
As of now, only one thread can produce and only one can consume at a time.
The problem is that this behaviour is not enforced correctly, we may have many consumers passing the "consume" check
leading to the first consumer reading the correct value and everyone after it reading a null (0) value.
This means that we either relax the "delete after read" requirement, or we force the consumers to be only one at the time during consume operation.