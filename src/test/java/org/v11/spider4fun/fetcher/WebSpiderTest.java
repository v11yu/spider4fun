package org.v11.spider4fun.fetcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.v11.spider4fun.core.fetcher.webSpider;
import org.v11.spider4fun.core.fetcher.impl.webSpiderImpl;

public class WebSpiderTest {
	@Test
	public void testFetch(){
		String url = "http://m.jiaoyimao.com/g1677/?keyword=%E7%BE%8A&1448508115736366=%E6%9C%AA%E8%AE%A4%E8%AF%81%E8%BA%AB%E4%BB%BD%E8%AF%81";
		webSpider ws = new webSpiderImpl();
		String urlString = ws.getRequest(url);
		//System.out.println(urlString);
		Document doc = Jsoup.parse(urlString);
		Elements ids = doc.getElementsByAttribute("category-id");
		for(Element e : ids){
			//System.out.println(e.html());
			//System.out.println(e.text());
			//System.out.println(e.html());
			Elements gids = e.getElementsByTag("a");
			System.out.println(gids.html());
			System.out.println(gids.attr("href"));
		}
		Elements ems = doc.getElementsByClass("brief");
		for(Element e : ems){
			//System.out.println(e.html());
			System.out.println(e.text());
		}
		Elements prices = doc.getElementsByClass("price");
		for(Element e : prices){
			//System.out.println(e.html());
			System.out.println(e.text());
		}
		//System.out.println(doc.text());
	}
}
