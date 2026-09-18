#!/bin/sh
set -e
mkdir -p out
echo "Compiling Java source files..."
javac -d out src/hospital/*.java
echo "Starting Hospital Management System..."
java -cp out hospital.Main
