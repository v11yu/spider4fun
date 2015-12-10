package org.v11.spider4fun.m5173.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.core.utils.WY163Email;

public class m5173SpiderTask {
	static String emailUserName = "wow_haigui";
	static String emailPassword = "wow123wow";
	static String url = "http://sy.5173.com/bizoffer/GoodsList?GameId=9ebdb1e338d64a45ac2a25d35324200d&GamePlatformId=40c9692aede64b379aa4da7577e62b3a&GameCateId=a87961b64a7d49ada686e29f180f36a8&GameAreaId=d47cfbcb11d6420aafdaf3b583d442b3&GameServerId=&Keyword=%u7F8A";
	static String preUrl = "http://sy.5173.com/bizoffer/";
	static int Num = 300;
	static double eps = 1e-3;
	List<String> rules = new ArrayList<String>();
	Map<String,Double> mp = new HashMap<String, Double>();
	void init(){
		rules = new ArrayList<String>();
		for(String rule:TaskConfig.getValue("5173_rule").split(",")){
			rules.add(rule);
		}
		Log.info(rules);
		
	}
	boolean check(String str){
		for(String rule:rules){
			if(str.contains(rule)) return true;
		}
		return false;
	}
	void sleep(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			Log.error(e);
		}
	}
	public void work(){
		init();
		String message = "";
		for(int i=1;i<=Num;i++){
			String baseUrl = url + "&pageIndex="+i;
			try {
				Document doc = Jsoup.connect(baseUrl).get();
				Elements es = doc.getElementsByClass("td-title");
				Elements moneys = doc.getElementsByClass("money-c");
				List<Double> prices = new ArrayList<Double>();
				for(Element e : moneys){
					prices.add(Double.parseDouble(e.text()));
				}
				if(prices.size() == 0){
					Log.info("抓取至"+i+"页，结束");
					break ;
				}
				int th = 0;
				//System.out.println(es.size());
				for(Element e : es){
					if(th == prices.size()) break;
					String newMessage = "";
					String title = e.text();
					String gid = preUrl+e.getElementsByTag("a").attr("href");
					double curPrice = prices.get(th);
					if(check(title)){
						if(mp.containsKey(gid)){
							double price = mp.get(gid);
							if(Math.abs(price-curPrice) > eps){
								mp.put(gid, curPrice);
								newMessage = "[价格变动] "+title+" 由"+price+"->"+curPrice+" "+gid;
							}
						}else{
							mp.put(gid, curPrice);
							newMessage = "[新商品] "+title+" "+curPrice+" "+gid;
						}
					}
					if(newMessage.length()>0){
						Log.info(newMessage);
						message += newMessage+"\n";
					}
					th++;
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("jsoup 链接错误"+e);
				return ;
			}
		}
		if(message.length()>0){
			try {
				String emails = TaskConfig.getValue("5173_emails");
				for(String to : emails.split(",")){
					Log.info("will seng to "+to);
					WY163Email.Send("wow_haigui", "wow123wow", to, "","5173账号变化"+new Date(), message);
				}
				
			} catch (Exception e) {
				Log.error("邮件异常"+e);
			}
		}
	}
}
