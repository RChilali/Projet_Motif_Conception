#!/bin/bash

# Directory paths
SRC_DIR="src"
OUT_DIR="src/out"

# Compile all Java files
echo "Compiling Java files..."
javac -d $OUT_DIR $SRC_DIR/**/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running simulation..."
    java -cp $OUT_DIR src.simulation.Simulation
else
    echo "Compilation failed. Please check for errors."
fi