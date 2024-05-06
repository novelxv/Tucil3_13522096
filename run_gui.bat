@echo off
cd src
javac -d ..\bin *.java
cd ..
java -cp .\bin MainGUI