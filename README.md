# bter-transaction-script
bter的比特币交易使用脚本（Java版）bter transaction script for Java
帮盆友写的一个交易模块，bter上只有php版的，我就顺手翻译了一下~~

例子（example）：
	
	// example 1: get funds
	bterHttpsPost.bter_query('1/private/getfunds', "");
	
	// example 2: cancel an order
	bterHttpsPost.bter_query('1/private/cancelorder', "your post URLencode data");
	
	// example 3: get order status
	bterHttpsPost.bter_query('1/private/getorder', "your post URLencode data");
	
	//example 4: list all open orders
	bterHttpsPost.bter_query('1/private/orderlist', "");
