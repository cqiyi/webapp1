# -*- coding: utf-8 -*-
# created by 2014年10月8日 15:03:24

import string, sys, sqlite3, httplib, random, time, smtplib, os
from email.mime.text import MIMEText 

# reload(sys)
# sys.setdefaultencoding( "utf-8" )

app_name = "tdxw helper 2.0.0"

os.system("title " + app_name)

# print "\n                       " + app_name + "\n---------------------------------------------------------------\n"
print "\n"

# 股票列表
stock_list = "sz000949,sh600488"



# 定义发送列表 
mailto_list=["opsd<opensender@163.com>", "成七一<ccqiyi@qq.com>"] 
 
# 设置服务器名称、用户名、密码以及邮件后缀 
mail_host = "smtp.163.com" 
mail_user = "opensender" 
mail_pass = "opsd#2015" 
mail_postfix="163.com" 

# 发送邮件函数 
def send_mail(sender, subject, body): 
    me = sender + "<" + mail_user + "@" + mail_postfix + ">" 
    msg = MIMEText(body, 'html', 'utf-8')
    msg['Subject'] = subject 
    msg['From'] = me 
    msg['To'] = ";".join(mailto_list) 
    
    try:
        send_smtp = smtplib.SMTP() 
        send_smtp.connect(mail_host) 
        send_smtp.login(mail_user, mail_pass) 
        send_smtp.sendmail(me, mailto_list, msg.as_string()) 
        send_smtp.close()
        
        # 邮件发完后，暂停1秒
        time.sleep(1)
        
    except Exception, data:
        print "Exception in send_mail: ", Exception, ":", data
	
if __name__ == '__main__':
	dbfile = "stock.db"
	# 止损比例为-4%
	stoplimit = 96
	
	cx = sqlite3.connect(dbfile)
	
	# 初始化创建表
	cx.execute("create table if not exists [stock] ([code] varchar2, [name] varchar2, [price] varchar2, [close_price] varchar2, [hclose_price] varchar2, [percent] double default 0, [trans_time] varchar2, [create_time] datetime default (datetime('now', 'localtime')));")

	# 获取
	conn = httplib.HTTPConnection("qt.gtimg.cn")
	conn.request("GET", "/r=" + str(random.randint(100,10000)) + "&q=" + stock_list)

	resp = conn.getresponse()
	text = resp.read()
	conn.close()

	arr = text.split("\n")	
	stock_count = 0	
	for k, v in enumerate(arr):
		try:
			it = v.split("~")
			# 过滤掉不合格的数据
			if len(it) < 30:
				continue
				
			# 检查该股票的该时刻是否已经记录，避免重复记录
			rc = 0
			cur = cx.execute("select count(*) from stock where code='" + it[2] + "' and trans_time='" + it[30] + "'")
			for row in cur:
				rc = row[0]
						
			# 如果存在，则取历史最大收盘价
			cur = cx.execute("select max(close_price) from stock where code='" + it[2] + "'")
			for row in cur:
				hclose_price = row[0]
				
			if  it[4] > hclose_price:
				hclose_price = it[4]
				
			percent = 100*float(it[3])/float(hclose_price)
			# it[2]代码、it[1]股票名、it[30]当前时间、it[3]当前价格、it[4]昨日收盘价、最高收盘价
			print it[2], it[1], it[30], it[3], it[4], hclose_price, str(round(percent, 2))+"%"

			
			if rc > 0:
				print "IGNORE: already stored in the database.\n"
				continue;
				
			it1 = unicode(it[1], 'gbk')
			
			# 将价格记录至数据库
			try:
				cx.execute("insert into stock(code, name, price, close_price, hclose_price, percent, trans_time) values('" + it[2] + "', '" + it1 + "', '" + it[3] + "', '" + it[4] + "', '" + hclose_price + "', ' " + str(percent) + "', '" + it[30] + "')")
			except Exception, data:
				print "Exception in execute insert sql: ", Exception, ":", data
		
			pre = "INFO"
			
			# percent < 96, 当跌破最高收盘价的4%时
			if percent < stoplimit:
				pre = "WARN"
				
			# 跌破70%，则认为是非法异常
			if percent < 70:
				pre = "ERROR"
			
			
			it30 = it[30]		
			trans_time = it30[:4] + "/" + it30[4:6] + "/" + it30[6:8] + " " + it30[8:10] + ":" + it30[10:12] + ":" + it30[12:14]
			
			# time.strftime('%Y/%m/%d %H:%M:%S')
			
			title = u"{0}: {1} / {3}% : {2}".format(pre, it[30], it1, round(percent,2))
			content = (u"<h3>{0}（{1}）</h3>水平：{2}%<br/>价格：{3} <br/> 昨收：{4} <br/>最高：{5} <br/>---------------------------------------<br/>{6}").format(it1, it[2], round(percent,2), it[3], it[4], hclose_price, trans_time)
				
			print "{0}: {1} / {2}% ...".format(pre, it[30], round(percent,2))

			send_mail(it1, title, content)
				
			# 当跌破最高收盘价的4%时，并且没有出错，自动卖出
			if pre == "WARN":
				print "EXECUTING SELL ..."
				os.system("tdxw-sell.exe " + it[2])
				send_mail(it1, title, "<h3>SELL ​​HAS BEEN EXECUTED.</h3>")
						
			# 该只股票处理成功，计数器+1
			stock_count += 1
			print "\n"
			
			# 暂停1秒
			time.sleep(1)
        
		except Exception, data:
			print "Exception in for: ", Exception, ":", data
			
	cx.commit()
	cx.close()
	
	# 校验总数是否正常
	len_stock = len(stock_list.split(","))
	if stock_count != len_stock:
		email_body = (u"<h3>总数校验异常</h3>stock_count：{0}<br/>len(stock_list)：{1} <br/>---------------------------------------<br/>{2}").format(stock_count, len_stock, time.strftime('%Y/%m/%d %H:%M:%S'))
		send_mail("tdxw-helper", "异常报告", email_body)
		print "ERROR in stock_count check: stock_count=", stock_count, "; len_stock=", len_stock
	
	print "---------------------------------------------------------------"
	print "                                          ", time.strftime('%Y/%m/%d %H:%M:%S')
