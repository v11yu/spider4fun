package org.v11.spider4fun.taoshouyou.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.v11.spider4fun.core.fetcher.webSpider;
import org.v11.spider4fun.core.fetcher.impl.webSpiderImpl;
import org.v11.spider4fun.core.task.ItemBasicTask;
import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.core.utils.WY163Email;

public class TaoShouYouSpiderTask extends ItemBasicTask{
	/**
	 * construction
	 * @param etitle 邮件标题
	 * @param emails_properties 接受地址,taskconfig属性名
	 */
	public TaoShouYouSpiderTask(String etitle, String emails_properties) {
		super(etitle, emails_properties);
	}
	@Override
	public List<String> getgids(Document doc) {
		List<String> res = new ArrayList<String>();
		String pre = "http://www.taoshouyou.com";
		Elements es = doc.getElementsByClass("row");
		for(Element e:es){
			Elements ts= e.getElementsByTag("a");
			res.add(pre+ts.attr("href"));
		}
		return res;
	}
	@Override
	public List<String> getItemName(Document doc) {
		List<String> res = new ArrayList<String>();
		Elements es = doc.getElementsByClass("row");
		for(Element e:es){
			Elements ts= e.getElementsByClass("title");
			res.add(ts.text());
		}
		return res;
	}
	@Override
	public List<Double> getItemPrice(Document doc) {
		List<Double> res = new ArrayList<Double>();
		Elements es = doc.getElementsByClass("row");
		System.out.println(es.size());
		for(Element e:es){
			Elements ts= e.getElementsByClass("price");
			res.add(Double.parseDouble(ts.text()));
		}
		return res;
	}
}
