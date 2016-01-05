package org.v11.spider4fun.jiaoyimao.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

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

public class JiaoYiMaoSpiderTask extends ItemBasicTask{
	
	public JiaoYiMaoSpiderTask(String etitle, String emails_properties) {
		super(etitle, emails_properties);
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<String> getgids(Document doc){
		List<String> res = new ArrayList<String>();
		Elements ids = doc.getElementsByAttribute("category-id");
		for(Element e : ids){
			Elements gids = e.getElementsByTag("a");
			res.add(gids.attr("href"));
		}
		return res;
	}
	@Override
	public List<String> getItemName(Document doc){
		List<String> itemsName = new ArrayList<String>();
		Elements ems = doc.getElementsByClass("brief");
		for(Element e : ems){
			itemsName.add(e.text());
		}
		return itemsName;
	}
	@Override
	public List<Double> getItemPrice(Document doc){
		List<Double> itemsName = new ArrayList<Double>();
		Elements ems = doc.getElementsByClass("price");
		for(Element e : ems){
			itemsName.add(Double.parseDouble(e.text().trim().substring(1)));
		}
		return itemsName;
	}
	
	public static void main(String[] args) {
		JiaoYiMaoSpiderTask ap = new JiaoYiMaoSpiderTask("交易猫","jym_emails");
		String url = "http://m.jiaoyimao.com/g1677/?keyword=%E7%BE%8A&1448508115736366=%E6%9C%AA%E8%AE%A4%E8%AF%81%E8%BA%AB%E4%BB%BD%E8%AF%81";
		List<String> ls = new ArrayList<String>();
		ls.add(url);
		ap.work(ls);
	}
}
