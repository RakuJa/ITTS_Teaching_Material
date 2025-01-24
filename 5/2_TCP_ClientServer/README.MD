# Java Client-Server App using TCP to play a simple "ping" "pong" game

This project demonstrates a basic example of network programming using TCP sockets.
Every time a new client connects to the server a thread will be instantiated to handle the connection.
If a client sends "PING" the server will respond "PONG"
If a client sends "PONG" the server will respond "PING"
Otherwise the server will answer with an error message.

## Improvements
Students are encouraged to improve this code by:
1. Using threadpool shown in the UDP example;
2. Handle the Exit command sent by the server instead of raising an exception.