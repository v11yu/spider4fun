package org.v11.spider4fun.taoshouyou.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.taoshouyou.task.TaoShouYouSpiderTask;
import org.v11.spider4fun.taoshouyou.utils.TaoShouYouConfig;


public class TaoShouYouApp {
	TaoShouYouSpiderTask task = new TaoShouYouSpiderTask();
	public void jobDetail() throws InterruptedException{
		Log.info("初始化properties文件....");
		TaoShouYouConfig.init();
		TaskConfig.init();
		Log.info("开始获取需要待抓取URL....");
		List<String> urls = new ArrayList<String>();
		Iterator<Entry<Object, Object>> iter = TaoShouYouConfig.props.entrySet().iterator();
		while(iter.hasNext()){
			Entry<Object, Object> en = iter.next();
			urls.add((String)en.getValue());
		}
		task.work(urls);
	}
	public static void main(String[] args) throws InterruptedException {
		TaoShouYouApp app = new TaoShouYouApp();
		while(true){
			app.jobDetail();
			int TIME = TaskConfig.getNum("taosy_time_interval");
			Thread.sleep(1000*60*TIME);
		}
	}
}
