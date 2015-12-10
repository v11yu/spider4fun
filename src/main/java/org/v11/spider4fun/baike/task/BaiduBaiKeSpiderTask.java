package org.v11.spider4fun.baike.task;

import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.v11.spider4fun.core.fetcher.webSpider;
import org.v11.spider4fun.core.fetcher.impl.webSpiderImpl;
import org.v11.spider4fun.core.utils.HttpUtils;
import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.core.utils.WY163Email;

public class BaiduBaiKeSpiderTask {
	String baseUrl = "http://baike.baidu.com/item/";
	public void work(List<String> names){
		webSpider wsp = new webSpiderImpl();
		for(String name : names){
			String url = baseUrl+HttpUtils.encode(name);
			String html = wsp.getRequest(url);
			Document doc = Jsoup.parse(html);
			Log.info(doc.text());
			//Log.info(html);
		}	
	}
}
