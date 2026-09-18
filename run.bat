@echo off
if not exist out mkdir out
echo Compiling Java source files...
javac -d out src\hospital\*.java
if errorlevel 1 (
    echo Compilation failed.
    exit /b 1
)
echo Starting Hospital Management System...
java -cp out hospital.Main
