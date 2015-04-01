@echo off

title 系统环境信息收集程序-v2.2
set count=85
setlocal enabledelayedexpansion
set CURRENT=%CD%
md "%COMPUTERNAME%.sysinfo"
cd "%COMPUTERNAME%.sysinfo"
echo.                 系统环境信息收集程序-v2.2
echo.      _____________________________________________
echo.
echo 开始收集:%date% %time%>收集日志.log
echo [1/85]systeminfo ...
systeminfo>systeminfo.log

echo [2/85]set ...
set>set.log

echo [3/85]tasklist ...
tasklist>tasklist.log

echo [4/85]netstat ...
netstat -ano>netstat.log

echo [5/85]ipconfig ...
ipconfig /all>ipconfig.log

echo [6/85]net start ...
net start>net-start.log

wmic exit
set j=6

for %%i in (alias baseboard bios bootconfig cdrom computersystem cpu csproduct dcomapp desktop desktopmonitor devicememoryaddress diskdrive diskquota dmachannel environment  group idecontroller irq job loadorder logicaldisk logon memcache memorychip memphysical netclient netlogin netprotocol netuse nic nicconfig ntdomain nteventlog onboarddevice os pagefile pagefileset partition port portconnector printer printerconfig printjob process product qfe quotasetting rdaccount rdnic rdpermissions rdtoggle recoveros registry scsicontroller server service shadowcopy shadowstorage share softwareelement softwarefeature sounddev startup sysaccount sysdriver systemenclosure systemslot tapedrive temperature timezone ups useraccount voltage volume volumequotasetting volumeuserquota wmiset) do (
	set/a j+=1
	echo [!j!/85]getting %%i ...
	wmic %%i get /value>%%i.log
)

echo 收集完成:%date% %time%
echo 收集完成:%date% %time%>>收集日志.log

cd ..\..
echo compressing %COMPUTERNAME% ...
rem %CURRENT%\7za.exe a -ry "%COMPUTERNAME%@%LOG_FOLDER%.7z" "%LOG_FOLDER%\*.*"

rem zip -r %COMPUTERNAME%@%LOG_FOLDER%.zip .\*

rem rd/s/q "%LOG_FOLDER%"

echo 收集完成，按任意键退出。
pause>nul
