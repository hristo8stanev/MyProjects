Precognitions:
Have a working folder with JMeter on your system.
Open and run cmd in your Jmeter /bin folder.

1.How to create a html report from test plan (.jmx file):
jmeter -n -t "the path to your .jmx file'" -l "the path where your .csv file will be created" 
-e -o "the path where your html folder with the report will be created"!!!!

2.How to create a html report from a result(.csv) file:
jmeter -g "the location of your result .csv file" 
-o "the path where your html folder with the report will be created"!!!


You can also execute the "ExecuteJmeterTestReportHtml.bat" file, however
you will need edit and imput your system specific paths in it.

@echo off
ECHO Starting the HTML report generation for JMeter
CALL cd "directory of your JMeter /bin folder"
CALL jmeter -n -t "path to your .jmx file" 
-l "path to your .csv file" -e -o "path where the newly generated folder will be"
ECHO Report generation completed
PAUSE >nul

In the folder there is an example of a locally working .bat file