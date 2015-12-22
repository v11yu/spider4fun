package org.v11.spider4fun.core.task;

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
import org.v11.spider4fun.core.fetcher.webSpider;
import org.v11.spider4fun.core.fetcher.impl.webSpiderImpl;
import org.v11.spider4fun.core.rule.RuleChecker;
import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.core.utils.WY163Email;

public abstract class ItemBasicTask {
	static String emailUserName = "wow_haigui";
	static String emailPassword = "wow123wow";
	protected RuleChecker ruleChecker = new RuleChecker();
	Map<String,Double> mp = new HashMap<String, Double>();
	double eps = 1e-3;
	/* 邮件标题 */
	private String etitle;
	/* received emails 接受邮件的邮箱地址 */
	private String emails_properties;
	/**
	 * 子类有需要，可以重写
	 */
	public void init(){}
	/**
	 * construction
	 * @param etitle 邮件标题
	 * @param emails 接受地址
	 */
	public ItemBasicTask(String etitle,String emails_properties){
		this.emails_properties = emails_properties;
		this.etitle = etitle;
		init();
	}
	/**
	 * 获取商品id，可以是url链接
	 * @param doc
	 * @return
	 */
	public abstract List<String> getgids(Document doc);
	/**
	 * 获取商品标题，用于判断是否推送
	 * @param doc
	 * @return
	 */
	public abstract List<String> getItemName(Document doc);
	/**
	 * 获取商品价格
	 * @param doc
	 * @return
	 */
	public abstract List<Double> getItemPrice(Document doc);
	void sleep(long t){
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void work(List<String> urls){
		String message = "";
		for(String url : urls){
			sleep(100);
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.error(e);
			}
			if(doc == null) continue;
			List<String> giduUrls = getgids(doc);
			List<String> itemNames = getItemName(doc);
			List<Double> prices = getItemPrice(doc);
			/* sign for stop loop */
			if(prices == null) break; 
			for(int i=0;i < giduUrls.size();i++){
				String gid = giduUrls.get(i);
				String itemName = itemNames.get(i);
				Double curPrice = prices.get(i);
				if(!ruleChecker.checkRule(itemName, curPrice)) continue;
				String newMessage = "";
				if(mp.containsKey(gid)){
					Double price = mp.get(gid);
					if(Math.abs(price - curPrice) > eps){
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
			Log.info(TaskConfig.getValue(emails_properties));
			try {
				for(String to : TaskConfig.getValue(emails_properties).split(",")){
					WY163Email.Send("wow_haigui", "wow123wow", to, "",etitle+"账号变化"+new Date(), message);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("邮件异常"+e.getMessage());
			}
		}
	}
}
