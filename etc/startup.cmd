@echo off
set title=�����������Ϣ�ɼ����㻪�Ƽ���
title %title%
echo.    %title%
echo. -------------------------------------------------
echo.  �ó���ɼ���ɺ���Զ��رգ������ֶ��رգ�лл��
timeout 3
echo. ��ʼ ...
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

echo. ���ڵ�������ҳ��������Ϣ��лл��
start http://hostinfo.hhwy.org/post?id=%uuid%
echo. ��ɡ�
goto end

:error
cls
echo.
echo.  ���Է��ʷ�����ʧ�ܣ�������Ϣ���Ĺ���Ա��ϵ��
echo.
echo.  �绰��010-82078686-****, �ֻ���, ���䣺zyy@ieforever.com
goto end
	

:end
endlocal
popd

