package com.acme.mytrader.price;

import com.acme.mytrader.strategy.TradingStrategy;

public interface PriceListener {
    void priceUpdate(String security, double price);

	String getSecurity();
	void addStrategy(TradingStrategy strategy);
    
    
}
