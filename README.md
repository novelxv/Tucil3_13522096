# Solving the Word Ladder Game Using the UCS Algorithm, Greedy Best First Search, and A* 

Tugas Kecil 3 Mata Kuliah IF2211 Strategi Algoritma 2024 - Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*

## Table of Contents
- [Description](#description)
- [Requirements and Installation](#requirements-and-installation)
- [How to Run](#how-to-run)
- [Program Display](#program-display)
- [Author Information](#author-information)

## Description
The Word Ladder Solver is a Java application designed to find the shortest transformation sequence from one word to another word by changing a single letter at a time, where each transformed word must be a valid dictionary word. This application implements three different algorithms to find the most efficient solution: Uniform Cost Search (UCS), Greedy Best First Search (GBFS), and A* Search, providing insights into the computational efficiency and effectiveness of each algorithm.

## Requirements and Installation
- Java Runtime Environment (JRE) 8 or newer.
- Java Development Kit (JDK) 8 or newer (if you plan to compile the code).

### Installing Java
Download and install Java from Oracle's Java website or use a package manager like `apt` on Ubuntu (`sudo apt install default-jdk`) or `brew` on macOS (`brew install java`).

## How to Run
To run the program, open a terminal or command prompt, navigate to the directory where the program file is saved

### On Windows

For CLI
```
.\run.bat
```
For GUI
```
.\run-gui.bat
```

### On Linux

For CLI
```
./run.sh
```

For GUI
```
./run-gui.sh
```

### Usage Instructions

1. Start the Program: Run the Main class using the above command.
2. Input Word Length: You will be prompted to enter the length of the word. This should be between 2 and 15.
3. Load Dictionary: The program automatically attempts to load the dictionary corresponding to the specified word length.
4. Enter Start and End Words: Input the start and end words (case insensitive) of the desired length. These words must exist in the loaded dictionary.
5. Choose the Algorithm: Select the algorithm you wish to use (UCS, GBFS, or A*) by entering the corresponding number when prompted.
6. View the Results: The program will display the shortest path from the start word to the end word, the number of steps, the nodes visited, and the execution time.

## Program Display

![](assets/bezier-3.gif)

## Author Information
| Name                    | NIM      |
| ----------------------- |:--------:|
| Novelya Putri Ramadhani | 13522096 |