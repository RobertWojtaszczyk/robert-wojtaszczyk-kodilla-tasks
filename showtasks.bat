call runcrud
if "%ERRORLEVEL%" == "0" goto runbrowser
echo Cannot build and run Tasks project - breaking work!
goto end

:runbrowser
echo Ready to start Firefox...
pause
start "" "C:\Program Files (x86)\Mozilla Firefox\firefox.exe" http://localhost:8080/crud/v1/task/getTasks

:end
echo.
echo showtasks.bat work is finished