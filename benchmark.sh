#! /bin/sh

echo "All results will be written to result.txt.\nStarting sbt..."

sbt test | tee result.txt
