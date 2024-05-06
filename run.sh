#!/bin/bash
cd src
javac -d ../bin *.java
cd ..
java -cp ./bin Main