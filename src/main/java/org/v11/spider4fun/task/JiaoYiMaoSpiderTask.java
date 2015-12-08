package org.v11.spider4fun.task;

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
import org.v11.spider4fun.fetcher.webSpider;
import org.v11.spider4fun.fetcher.impl.webSpiderImpl;
import org.v11.spider4fun.utils.Log;
import org.v11.spider4fun.utils.WY163Email;

public class JiaoYiMaoSpiderTask {
	static String emailUserName = "wow_haigui";
	static String emailPassword = "wow123wow";
	Map<String,Integer> mp = new HashMap<String, Integer>();
	List<String> getgids(Document doc){
		List<String> res = new ArrayList<String>();
		Elements ids = doc.getElementsByAttribute("category-id");
		for(Element e : ids){
			Elements gids = e.getElementsByTag("a");
			res.add(gids.attr("href"));
		}
		return res;
	}
	List<String> getItemName(Document doc){
		List<String> itemsName = new ArrayList<String>();
		Elements ems = doc.getElementsByClass("brief");
		for(Element e : ems){
			itemsName.add(e.text());
		}
		return itemsName;
	}
	List<Integer> getItemPrice(Document doc){
		List<Integer> itemsName = new ArrayList<Integer>();
		Elements ems = doc.getElementsByClass("price");
		for(Element e : ems){
			itemsName.add(Integer.parseInt(e.text().trim().substring(1)));
		}
		return itemsName;
	}
	public void work(List<String> urls){
		webSpider wsp = new webSpiderImpl();
		String message = "";
		for(String url : urls){
			String urlString = wsp.getRequest(url);
			Document doc = Jsoup.parse(urlString);
			List<String> giduUrls = getgids(doc);
			List<String> itemNames = getItemName(doc);
			List<Integer> prices = getItemPrice(doc);
			for(int i=0;i < giduUrls.size();i++){
				String gid = giduUrls.get(i);
				String itemName = itemNames.get(i);
				int curPrice = prices.get(i);
				String newMessage = "";
				if(mp.containsKey(gid)){
					int price = mp.get(gid);
					if(price != curPrice){
						mp.put(gid, curPrice);
						newMessage = "[价格变动] "+itemName+" 由"+price+"->"+curPrice+" "+gid;
					}
				}else{
					mp.put(gid, curPrice);
					newMessage = "[新商品] "+itemName+" "+curPrice+" "+gid;
				}
				if(newMessage.length()>0){
					Log.info(newMessage);
					message += newMessage+"\n";
				}
			}
		}
		if(message.length()>0){
			try {
				WY163Email.Send("wow_haigui", "wow123wow", "442629928@qq.com", "",new Date()+"账号变化", message);
			} catch (MessagingException e) {
				Log.error("邮件异常"+e);
			}
		}
		
	}
	public static void main(String[] args) {
		JiaoYiMaoSpiderTask ap = new JiaoYiMaoSpiderTask();
		String url = "http://m.jiaoyimao.com/g1677/?keyword=%E7%BE%8A&1448508115736366=%E6%9C%AA%E8%AE%A4%E8%AF%81%E8%BA%AB%E4%BB%BD%E8%AF%81";
		List<String> ls = new ArrayList<String>();
		ap.work(ls);
	}
}
