# -*- coding: utf-8 -*-
# created by 2015Äê2ÔÂ28ÈÕ 16:59:17

import string, sys, sqlite3, httplib, random, datetime, time, smtplib, os
from email.mime.text import MIMEText 

# reload(sys)
# sys.setdefaultencoding( "utf-8" )

app_name = "SVN log Parse 1.0.0"

os.system("title " + app_name)

# print "\n                       " + app_name + "\n---------------------------------------------------------------\n"

print "\n"

today = datetime.date.today()
yesterday = today - datetime.timedelta(days=1)
tomorrow = today + datetime.timedelta(days=1) 

t0 = yesterday.strftime('%Y-%m-%d')
t1 = tomorrow.strftime('%Y-%m-%d')

cmd = "svn log --username yanfa --password svn-yanfa^&2015 --xml --revision {" + t0 + "}:{" + t1 + "} -v https://192.168.1.16/svn/yanfa>last.xml"
os.system(cmd)
print "OK."

