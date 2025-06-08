#!/bin/bash
JAR="google-java-format-1.27.0-all-deps.jar"
find . -name "*.java" -print0 | xargs -0 java -jar $JAR --replace
