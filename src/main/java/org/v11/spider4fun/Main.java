package org.v11.spider4fun;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.v11.spider4fun.baike.utils.BaikeConfig;
import org.v11.spider4fun.core.utils.TaskConfig;

public class Main {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://m.jiaoyimao.com/g1677/?keyword=%E7%BE%8A").get();
		System.out.println(doc.text());
		
	}
	
}
