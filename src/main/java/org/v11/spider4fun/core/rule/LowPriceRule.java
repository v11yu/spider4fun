package org.v11.spider4fun.core.rule;

public class LowPriceRule extends BasicRules{

	@Override
	public boolean checkRule(String itemName, Double price) {
		// TODO Auto-generated method stub
		if(price > 280) return true;
		return false;
	}

}
