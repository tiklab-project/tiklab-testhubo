@echo off

REM 启动类文件
set APP_MAIN=io.thoughtware.teston.TestOnApplication

set PID=0

for /f "usebackq tokens=1-2" %%a in (`jps -l ^| findstr %APP_MAIN%`) do (
set PID=%%a
)

if %PID% == 0 (
     echo ================================================================================================================
     echo %APP_MAIN%  is not running
) else (
    echo ================================================================================================================
    echo %APP_MAIN% already started(PID=%PID%)
   )