@echo off
ECHO Starting the HTML report generation for JMeter
CALL cd C:\Users\K.Kochev\Desktop\apache\apache-jmeter-5.6.2\bin
CALL jmeter -n -t "C:\Users\K.Kochev\Desktop\apache\apache-jmeter-5.6.2\Weare.jmx" 
-l "C:\Users\K.Kochev\Desktop\Result2.csv" -e -o "C:\Users\K.Kochev\Desktop\ReportsJMeter"
ECHO Report generation completed
PAUSE >nul