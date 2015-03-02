# -*- coding: utf-8 -*-
# created by 2015Äê2ÔÂ28ÈÕ 16:59:17

import string, sys, sqlite3, httplib, random, datetime, time, smtplib, os, xml.dom.minidom as minidom, uuid, hashlib

# reload(sys)
# sys.setdefaultencoding( "utf-8" )

app_name = "SVN log Parser 1.0.0"
dbfile = "svnlog.db"

def svnlog_parse(t0, t1):
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
	
	ls = 0
	ps = 0
	
	
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
			ls = ls + 1
		except Exception, data:
			pass
			# print "Exception in executing svn_logentry sql: ", Exception, ":", data
			# print "SQL is:", sql, "\n"
		
	
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
				ps = ps + 1
			except Exception, data:
				pass
				# print "Exception in executing svn_logpath sql: ", Exception, ":", data
				# print "SQL is:", sql, "\n"
	
	cx.commit()
	cx.close()
	time.sleep(3)
	os.system("del last.xml")
	print "imported log entity: " + str(ls) + ", paths: " + str(ps) + "\n"
	

if __name__ == '__main__':
	os.system("title " + app_name)
	print "\n                       " + app_name + "\n---------------------------------------------------------------\n"

	cx = sqlite3.connect(dbfile)
	now = datetime.datetime.now()
	
	cur = cx.execute("select date(max(commit_time)) from svn_logentry")
	for row in cur:
		trice = row[0]
		
	if trice == None:
		trice = now
	else:
		trice = datetime.datetime.strptime(trice,'%Y-%m-%d')
		
	# trice = datetime.datetime.strptime("2015-02-25", '%Y-%m-%d')
	
	while trice <= now:
		print trice.strftime('%Y-%m-%d'), '...'
	
		yesterday = trice - datetime.timedelta(days=1)
		tomorrow = trice + datetime.timedelta(days=1) 

		t0 = yesterday.strftime('%Y-%m-%d')
		t1 = tomorrow.strftime('%Y-%m-%d')
		svnlog_parse(t0, t1)
		trice = trice + datetime.timedelta(days=1) 
		
	
	print "ALL finished.\n"