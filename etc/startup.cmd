@echo off
set title=计算机配置信息采集（恒华科技）
title %title%
echo.    %title%
echo. -------------------------------------------------
echo.  该程序采集完成后会自动关闭，请勿手动关闭，谢谢。
timeout 3
echo. 开始 ...
setlocal enabledelayedexpansion
set uuid=
for /f %%i in ('curl http://hostinfo.hhwy.org/uuid') do set uuid=%%i

if "%uuid%"=="" goto error

set n=1
:again
set u=!uuid:~%n%,1!
if not "!u!"=="" (
	set/a n+=1
	goto again
)

if not "%n%"=="36" goto error

md "%uuid%"
pushd "%uuid%"


rem curl -F "file1=@20d9c5dc-2183-4e22-9882-20afa11eaa03.zip" "http://hostinfo.hhwy.org/upload"

echo. 请在弹出的网页中填报相关信息，谢谢。
start http://hostinfo.hhwy.org/post?id=%uuid%
echo. 完成。
goto end

:error
cls
echo.
echo.  尝试访问服务器失败，请与信息中心管理员联系。
echo.
echo.  电话：010-82078686-****, 手机：, 邮箱：zyy@ieforever.com
goto end
	

:end
endlocal
popd

