# -*- coding: utf-8 -*-
# created by 2015Äê2ÔÂ28ÈÕ 16:59:17

import string, sys, sqlite3, httplib, random, datetime, time, smtplib, os, xml.dom.minidom as minidom, uuid, hashlib

# reload(sys)
# sys.setdefaultencoding( "utf-8" )

app_name = "SVN log Parse 1.0.0"
dbfile = "svnlog.db"

os.system("title " + app_name)


# print "\n                       " + app_name + "\n---------------------------------------------------------------\n"

print "\n"

today = datetime.date.today()
yesterday = today - datetime.timedelta(days=1)
tomorrow = today + datetime.timedelta(days=1) 

t0 = yesterday.strftime('%Y-%m-%d')
t1 = tomorrow.strftime('%Y-%m-%d')

print "generate change_log.xml ..."
cmd = "svn log --username yanfa --password svn-yanfa^&2015 --xml --revision {" + t0 + "}:{" + t1 + "} -v https://192.168.1.16/svn/yanfa>last.xml"
# print cmd
os.system(cmd)

print "OK.\n"
time.sleep(3)

dom = minidom.parse("last.xml")
root = dom.getElementsByTagName("logentry")

print "import " + dbfile + " ..."
cx = sqlite3.connect(dbfile)
	
for logentry in root: 
	# revision
	revision = logentry.getAttribute("revision")
	# author
	author = logentry.getElementsByTagName("author")[0].childNodes[0].nodeValue
	# date
	commit_date = logentry.getElementsByTagName("date")[0].childNodes[0].nodeValue
	# msg
	msg = logentry.getElementsByTagName("msg")[0].childNodes[0].nodeValue
	
	sql = "insert into svn_logentry(revision, author, commit_time, msg, import_time) values('" + revision + "', '" + author + "', '" + commit_date + "', '" + msg + "', datetime('now', 'localtime'))"
	# print sql
	try:
		# print ""
		cx.execute(sql)
	except Exception, data:
		print "Exception in execute insert svn_logentry sql: ", Exception, ":", data
		print "SQL is:", sql, "\n"
		
	
	paths = logentry.getElementsByTagName("path")
	for path in paths:
		kind = path.getAttribute("kind")
		action = path.getAttribute("action")
		p = path.childNodes[0].nodeValue
		text = action + "[" + revision + "]" + kind + ":" + p
		# print text
		id = hashlib.md5(text.encode("utf-8")).hexdigest()
		sql = "insert into svn_logpath(id, revision, kind, action, path) values('" + id + "', '" + revision + "', '" + kind + "', '" + action + "', '" + p + "')"
		# print sql
		try:
			# print ""
			cx.execute(sql)
		except Exception, data:
			print "Exception in execute insert svn_logpath sql: ", Exception, ":", data
			print "SQL is:", sql, "\n"
	
cx.commit()
cx.close()
print "Finished."