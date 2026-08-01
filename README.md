# Task Tracker CLI

A simple command line application built with Java to manage tasks.

## Features

- Add tasks
- Update tasks
- Delete tasks
- Mark tasks as in-progress
- Mark tasks as done
- List all tasks
- Store tasks in JSON file

## How to Run

Compile the project:

javac *.java

Run commands:

Add a task:
java Main add "Learn Java"

List tasks:
java Main list

Update a task:
java Main update 1 "Learn Java Backend"

Delete a task:
java Main delete 1

Mark task as done:
java Main mark-done 1

Mark task as in progress:
java Main mark-in-progress 1

## Technologies

- Java
- File System
- JSON storage
- Command Line Interface