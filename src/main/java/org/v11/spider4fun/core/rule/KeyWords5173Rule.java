package org.v11.spider4fun.core.rule;

import java.util.ArrayList;
import java.util.List;

import org.v11.spider4fun.core.utils.TaskConfig;

public class KeyWords5173Rule extends BasicRules{
	
	@Override
	public boolean checkRule(String itemName, Double price) {
		for(String rule:TaskConfig.getValue("5173_rule").split(",")){
			if(itemName.contains(rule)) return true;
		}
		return false;
	}

}
