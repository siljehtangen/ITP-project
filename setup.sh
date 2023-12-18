#!/bin/zsh

# Kill any process using port 8080
lsof -ti:8080 | xargs kill

# Change to the rest directory and run mvn clean install without tests
cd ourplace/rest && mvn clean install -DskipTests

# Start the server
mvn spring-boot:run &

# Wait for server to initialize
sleep 3

# Change to the fxui directory and run the command
cd ../fxui && mvn javafx:run
