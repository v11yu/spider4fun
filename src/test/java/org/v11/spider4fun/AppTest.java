package org.v11.spider4fun;

import org.junit.Test;
import org.v11.spider4fun.core.fetcher.webSpider;
import org.v11.spider4fun.core.fetcher.impl.webSpiderImpl;

public class AppTest {
	@Test
	public void testFetch(){
		String url = "http://m.jiaoyimao.com/g1677/";
		webSpider ws = new webSpiderImpl();
		ws.getRequest(url);
	}
}
