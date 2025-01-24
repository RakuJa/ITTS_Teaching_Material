# Java Client-Server App using UDP to play a simple "ping" "pong" game

This project demonstrates a basic example of network programming using UDP sockets.
Every time a new client connects to the server a thread will be instantiated to handle the connection.
If a client sends "PING" the server will respond "PONG"
If a client sends "PONG" the server will respond "PING"
Otherwise the server will answer with an error message.

## Improvements
Students are encouraged to improve this code by:
1. Improve usage of threads;
2. Introduce packet parsing, avoiding the padding.