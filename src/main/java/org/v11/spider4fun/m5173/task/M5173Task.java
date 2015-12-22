package org.v11.spider4fun.m5173.task;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.v11.spider4fun.core.rule.KeyWords5173Rule;
import org.v11.spider4fun.core.task.ItemBasicTask;

public class M5173Task extends ItemBasicTask {

	public M5173Task(String etitle, String emails_properties) {
		super(etitle, emails_properties);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		ruleChecker.rules.add(new KeyWords5173Rule());
		super.init();
	}
	@Override
	public List<String> getgids(Document doc) {
		String preUrl = "http://sy.5173.com/bizoffer/";
		Elements es = doc.getElementsByClass("td-title");
		List<String> urls = new ArrayList<String>();
		for(int i=0;i<es.size();i++){
			if(i == 0) continue;
			Element e = es.get(i);
			urls.add(preUrl+e.getElementsByTag("a").attr("href"));
		}
		return urls;
	}

	@Override
	public List<String> getItemName(Document doc) {
		Elements es = doc.getElementsByClass("td-title");
		List<String> itemNames = new ArrayList<String>();
		for(int i=0;i<es.size();i++){
			if(i == 0) continue;
			Element e = es.get(i);
			itemNames.add(e.text());
		}
		return itemNames;
	}

	@Override
	public List<Double> getItemPrice(Document doc) {
		Elements moneys = doc.getElementsByClass("money-c");
		List<Double> prices = new ArrayList<Double>();
		for(Element e : moneys){
			prices.add(Double.parseDouble(e.text()));
		}
		return prices.size()>0?prices:null;
	}

}
