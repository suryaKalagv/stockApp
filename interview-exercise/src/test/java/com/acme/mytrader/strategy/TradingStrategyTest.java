package com.acme.mytrader.strategy;

import org.junit.Test;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSourceImpl;

import org.junit.Assert;

public class TradingStrategyTest {
    @Test
    public void testTradeStrategy() {
    	// A Price Source instance to connect to price Source and get stock price
    	PriceSourceImpl ps = new PriceSourceImpl();
    	
    	//Price Source has a map of listeners getting listener for one security
    	PriceListener ibmlistener = ps.getPriceListener("IBM");
    	
    	//Trading Strategy uses executor service to execute trade
    	TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.BUY, 50);
    	TradingStrategy ts1 = new TradingStrategy("IBM", 200, TradingType.BUY, 25);
    	TradingStrategy ts2 = new TradingStrategy("IBM", 300, TradingType.SELL, 40);
    	TradingStrategy ts3 = new TradingStrategy("IBM", 400, TradingType.SELL, 60);
    	TradingStrategy ts4 = new TradingStrategy("IBM", 500, TradingType.BUY, 50);
    	TradingStrategy ts5 = new TradingStrategy("IBM", 600, TradingType.SELL, 40);
    	
    	/*listener contains the list of buy Strategies and Sell strategies
    	 A map with key as price and list of strategies as value is used 
    	 this allows user to add multiple trading strategies for same price 
    	 can be extended for multiple users in future
    	 */
    	ibmlistener.addStrategy(ts);
    	ibmlistener.addStrategy(ts1);
    	ibmlistener.addStrategy(ts2);
    	ibmlistener.addStrategy(ts3);
    	ibmlistener.addStrategy(ts4);
    	ibmlistener.addStrategy(ts5);
    	// Buy if the current price is less than trade strategy
    	// Sell if the current price is greater than trade strategy
    //Price update on the price source executes trade on qualified trade strategies
    	// as per the current price
    	ps.priceUpdateFromStocks(45, "IBM");
    	
    	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
    	Assert.assertEquals(TradingStatus.PENDING, ts1.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts2.getStatus());
    	Assert.assertEquals(TradingStatus.PENDING, ts3.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts4.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts5.getStatus());
    	
    	ps.priceUpdateFromStocks(15, "IBM");
    	
    	ps.priceUpdateFromStocks(115, "IBM");
    	
    }
}
