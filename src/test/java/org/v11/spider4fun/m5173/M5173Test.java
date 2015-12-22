package org.v11.spider4fun.m5173;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class M5173Test {
	String url = "http://sy.5173.com/bizoffer/GoodsList?GameId=9ebdb1e338d64a45ac2a25d35324200d&GamePlatformId=40c9692aede64b379aa4da7577e62b3a&GameCateId=a87961b64a7d49ada686e29f180f36a8&GameAreaId=d47cfbcb11d6420aafdaf3b583d442b3&GameServerId=&Keyword=%u7F8A";
	@Test
	public void testDoc() throws IOException{
		Document doc = Jsoup.connect(url).get();
		Elements moneys = doc.getElementsByClass("money-c");
		List<Double> prices = new ArrayList<Double>();
		for(Element e : moneys){
			prices.add(Double.parseDouble(e.text()));
		}
		System.out.println(prices);
		Elements es = doc.getElementsByClass("td-title");
		List<String> itemNames = new ArrayList<String>();
		List<String> urls = new ArrayList<String>();
		for(int i=0;i<es.size();i++){
			if(i == 0) continue;
			Element e = es.get(i);
			System.out.println(e.text());
			System.out.println(e.getElementsByTag("a").attr("href"));
		}
	}
}
