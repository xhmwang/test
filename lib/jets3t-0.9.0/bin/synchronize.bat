@echo off

rem -------------------------------------------------------------------
rem Environmental variables:
rem
rem JETS3T_HOME  Points to the home directory of a JetS3t distribution.
rem
rem JAVA_HOME    The home directory of the Java Runtime Environment or 
rem              Java Development Kit to use. 
rem -------------------------------------------------------------------

rem Check the JETS3T_HOME directory

if not "%JETS3T_HOME%" == "" goto gotJetS3tHome

rem Find the home directory, assuming this script is %JETS3T_HOME%\bin
set MY_JETS3T_HOME=%~dp0\..
if exist "%MY_JETS3T_HOME%\bin\%~nx0" goto foundJetS3tHome

echo Please set the environment variable JETS3T_HOME
goto END

:gotJetS3tHome
set MY_JETS3T_HOME=%JETS3T_HOME%

:foundJetS3tHome

rem Check the JAVA_HOME directory

if not "%JAVA_HOME%" == "" goto gotJavaHome
set EXEC=java
goto noJavaHome

:gotJavaHome

set EXEC=%JAVA_HOME%\bin\java

:noJavaHome

rem echo JetS3t path: %MY_JETS3T_HOME%
rem echo Java path: %EXEC%

rem -------------------------------------------------------------------


REM Include configurations directory in classpath
set CP=%MY_JETS3T_HOME%/configs

REM Include resources directory in classpath
set CP=%CP%;%MY_JETS3T_HOME%/resources                    

REM Include libraries in classpath
set CP=%CP%;%MY_JETS3T_HOME%/jars/jets3t-0.9.0.jar
set CP=%CP%;%MY_JETS3T_HOME%/jars/synchronize-0.9.0.jar
set CP=%CP%;%MY_JETS3T_HOME%/libs/commons-logging/commons-logging-1.1.1.jar
set CP=%CP%;%MY_JETS3T_HOME%/libs/commons-codec/commons-codec-1.4.jar
set CP=%CP%;%MY_JETS3T_HOME%/libs/httpcomponents/httpclient-4.1.2.jar
set CP=%CP%;%MY_JETS3T_HOME%/libs/httpcomponents/httpcore-4.1.2.jar
set CP=%CP%;%MY_JETS3T_HOME%/libs/logging-log4j/log4j-1.2.15.jar
set CP=%CP%;%MY_JETS3T_HOME%/libs/bouncycastle/bcprov-jdk14-138.jar
set CP=%CP%;%MY_JETS3T_HOME%/libs/java-xmlbuilder/java-xmlbuilder-0.4.jar

REM OutOfMemory errors? Increase the memory available by changing -Xmx256M

"%EXEC%" -Xmx256M -classpath "%CP%" org.jets3t.apps.synchronize.Synchronize %*

:END
