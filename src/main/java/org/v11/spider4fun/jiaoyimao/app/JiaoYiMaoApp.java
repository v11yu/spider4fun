package org.v11.spider4fun.jiaoyimao.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.jiaoyimao.task.JiaoYiMaoSpiderTask;
import org.v11.spider4fun.jiaoyimao.utils.JiaoYiMaoConfig;


public class JiaoYiMaoApp {
	JiaoYiMaoSpiderTask task = new JiaoYiMaoSpiderTask("交易猫","jym_emails");
	public void jobDetail() throws InterruptedException{
		Log.info("初始化properties文件....");
		JiaoYiMaoConfig.init();
		TaskConfig.init();
		Log.info("开始获取需要待抓取URL....");
		List<String> urls = new ArrayList<String>();
		Iterator<Entry<Object, Object>> iter = JiaoYiMaoConfig.props.entrySet().iterator();
		while(iter.hasNext()){
			Entry<Object, Object> en = iter.next();
			urls.add((String)en.getValue());
		}
		task.work(urls);
	}
	public static void main(String[] args) throws InterruptedException {
		JiaoYiMaoApp app = new JiaoYiMaoApp();
		while(true){
			app.jobDetail();
			int TIME = TaskConfig.getNum("jym_time_interval");
			Thread.sleep(1000*60*TIME);
		}
	}
}
