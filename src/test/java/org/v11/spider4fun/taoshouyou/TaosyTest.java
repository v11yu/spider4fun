package org.v11.spider4fun.taoshouyou;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.v11.spider4fun.core.utils.Log;

public class TaosyTest {
	@Test
	public void test() throws IOException{
		Document doc = Jsoup.connect("http://www.taoshouyou.com/game/menghuanxiyou-352-20-1/0-0-0-9-0-0-0?wd=%E7%BE%8A").get();
		System.out.println(doc.text());
		Elements es = doc.getElementsByClass("row");
		System.out.println(es.size());
		for(Element e:es){
			Log.info(e.text());
			Elements ts= e.getElementsByClass("price");
			Log.info(ts.size() + " "+ts.text());
		}
		
	}
}
