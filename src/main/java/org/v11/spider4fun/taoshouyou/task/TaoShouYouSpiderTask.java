package org.v11.spider4fun.taoshouyou.task;

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
import org.v11.spider4fun.core.task.ItemBasicTask;
import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.core.utils.WY163Email;

public class TaoShouYouSpiderTask extends ItemBasicTask{
	/**
	 * construction
	 * @param etitle 邮件标题
	 * @param emails 接受地址
	 */
	public TaoShouYouSpiderTask(String etitle, String emails) {
		super(etitle, emails);
	}
	@Override
	public List<String> getgids(Document doc) {
		return null;
	}
	@Override
	public List<String> getItemName(Document doc) {
		return null;
	}
	@Override
	public List<Double> getItemPrice(Document doc) {
		return null;
	}
}
