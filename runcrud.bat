call gradlew build
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:rename
echo Deleting old file crud.war...
del build\libs\crud.war
echo Renaming new file to crud.war...
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot rename file
goto fail

:stoptomcat
echo Stopping server...
call %CATALINA_HOME%\bin\shutdown

:copyfile
echo Coping file to Tomcat folder %CATALINA_HOME%\webapps ...
copy build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%" == "0" goto runtomcat
echo Cannot copy file
goto fail

:runtomcat
call %CATALINA_HOME%\bin\startup
goto end

:fail
echo.
echo There were errors


:end
echo.
echo runcrud.bat work is finished