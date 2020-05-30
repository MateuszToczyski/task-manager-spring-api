call runcrud.bat
if "%ERRORLEVEL%" == "0" goto launch-client
echo Error while running runcrud
goto fail

:launch-client
start "" http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Error while accessing http://localhost:8080/crud/v1/task/getTasks
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.