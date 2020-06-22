package com.acme.mytrader.strategy;

import org.junit.Test;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSourceImpl;

import org.junit.Assert;

public class TradingStrategyTest {
	
	@Test
    public void buyTradeStrategyExecuted() {
	 	PriceSourceImpl ps = new PriceSourceImpl();
		PriceListener ibmlistener = ps.getPriceListener("IBM");
		TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.BUY, 50);
	 	ibmlistener.addStrategy(ts);
	 	
	 	System.out.println("Before processing");
	 	System.out.println(ts.toString() );
	 	
	 	ps.priceUpdateFromStocks(45, "IBM");
	 	
	 	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
	 	
	 	System.out.println("After processing");
	 	System.out.println(ts.toString() );
	}
	
	@Test
    public void buyTradeStrategyPending() {
	 	PriceSourceImpl ps = new PriceSourceImpl();
		PriceListener ibmlistener = ps.getPriceListener("IBM");
		TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.BUY, 50);
		
		System.out.println("Before processing");
	 	System.out.println(ts.toString() );
	 	
	 	ibmlistener.addStrategy(ts);
	 	ps.priceUpdateFromStocks(55, "IBM");
	
	 	Assert.assertEquals(TradingStatus.PENDING, ts.getStatus());
	 	
	 	System.out.println("After processing");
	 	System.out.println(ts.toString() );
	}
	@Test
    public void sellTradeStrategyPending() {
	 	PriceSourceImpl ps = new PriceSourceImpl();
		PriceListener ibmlistener = ps.getPriceListener("IBM");
		TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.SELL, 50);
		System.out.println("Before processing");
	 	System.out.println(ts.toString() );
		
	 	ibmlistener.addStrategy(ts);
	 	ps.priceUpdateFromStocks(45, "IBM");
	 	
	 	Assert.assertEquals(TradingStatus.PENDING, ts.getStatus());
	 	
	 	System.out.println("After processing");
	 	System.out.println(ts.toString() );
	}
	
	@Test
    public void sellTradeStrategyExecuted() {
	 	PriceSourceImpl ps = new PriceSourceImpl();
		PriceListener ibmlistener = ps.getPriceListener("IBM");
		TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.SELL, 50);
		System.out.println("Before processing");
	 	System.out.println(ts.toString() );
	 	
	 	ibmlistener.addStrategy(ts);
	 	ps.priceUpdateFromStocks(55, "IBM");
	 	
	 	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
	 	
	 	System.out.println("After processing");
	 	System.out.println(ts.toString() );
	}
	@Test
    public void buyTradeMultiplePrice() {
	 	PriceSourceImpl ps = new PriceSourceImpl();
		PriceListener ibmlistener = ps.getPriceListener("IBM");
		TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.BUY, 50);
		TradingStrategy ts1 = new TradingStrategy("IBM", 200, TradingType.BUY, 40);
		
		System.out.println("Before processing");
	 	System.out.println(ts.toString() );
	 	System.out.println(ts1.toString() );
	 	
	 	ibmlistener.addStrategy(ts);
	 	ibmlistener.addStrategy(ts1);
	 	ps.priceUpdateFromStocks(45, "IBM");
	 	
	 	System.out.println("After processing");
	 	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
	 	System.out.println(ts.toString() );
	 	
	 	
	 	Assert.assertEquals(TradingStatus.PENDING, ts1.getStatus());
	 	System.out.println(ts1.toString() );
	 	
	}
	@Test
    public void buyTradeMultipleSamePrice() {
	 	PriceSourceImpl ps = new PriceSourceImpl();
		PriceListener ibmlistener = ps.getPriceListener("IBM");
		TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.BUY, 50);
		TradingStrategy ts1 = new TradingStrategy("IBM", 200, TradingType.BUY, 50);
	 	ibmlistener.addStrategy(ts);
	 	ibmlistener.addStrategy(ts1);
	 	System.out.println("Before processing");
	 	System.out.println(ts.toString() );
	 	System.out.println(ts1.toString() );
	 	
	 	ps.priceUpdateFromStocks(45, "IBM");
	 	
	 	System.out.println("After processing");
	 	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
	 	System.out.println(ts.toString() );
	 	
	 	Assert.assertEquals(TradingStatus.EXECUTED, ts1.getStatus());
	 	System.out.println(ts1.toString() );
	 	
	}
	@Test
    public void sellTradeMultiplePrice() {
	 	PriceSourceImpl ps = new PriceSourceImpl();
		PriceListener ibmlistener = ps.getPriceListener("IBM");
		TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.SELL, 50);
		TradingStrategy ts1 = new TradingStrategy("IBM", 200, TradingType.SELL, 40);
	 	ibmlistener.addStrategy(ts);
	 	ibmlistener.addStrategy(ts1);
	 	System.out.println("Before processing");
	 	System.out.println(ts.toString() );
	 	System.out.println(ts1.toString() );
	 	
	 	ps.priceUpdateFromStocks(45, "IBM");
	 	
	 	System.out.println("After processing");
	 	Assert.assertEquals(TradingStatus.PENDING, ts.getStatus());
	 	System.out.println(ts.toString() );
	 	
	 	Assert.assertEquals(TradingStatus.EXECUTED, ts1.getStatus());
	 	System.out.println(ts1.toString() );
	 	
	}
	@Test
    public void sellTradeMultipleSamePrice() {
	 	PriceSourceImpl ps = new PriceSourceImpl();
		PriceListener ibmlistener = ps.getPriceListener("IBM");
		TradingStrategy ts = new TradingStrategy("IBM", 100, TradingType.SELL, 50);
		TradingStrategy ts1 = new TradingStrategy("IBM", 200, TradingType.SELL, 50);
	 	ibmlistener.addStrategy(ts);
	 	ibmlistener.addStrategy(ts1);
	 	System.out.println("Before processing");
	 	System.out.println(ts.toString() );
	 	System.out.println(ts1.toString() );
	 	
	 	ps.priceUpdateFromStocks(55, "IBM");
	 	
	 	System.out.println("After processing");
	 	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
	 	System.out.println(ts.toString() );
	 	
	 	Assert.assertEquals(TradingStatus.EXECUTED, ts1.getStatus());
	 	System.out.println(ts1.toString() );
	 	
	}
	
	@Test
    public void testMultipleTradeStrategy() {
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
    	System.out.println("After processing");
    	System.out.println(ts.toString());
    	System.out.println(ts1.toString());
    	System.out.println(ts2.toString());
    	System.out.println(ts3.toString());
    	System.out.println(ts4.toString());
    	System.out.println(ts5.toString());
    	
    	ps.priceUpdateFromStocks(45, "IBM");
    	System.out.println("After processing");
    	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
    	Assert.assertEquals(TradingStatus.PENDING, ts1.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts2.getStatus());
    	Assert.assertEquals(TradingStatus.PENDING, ts3.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts4.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts5.getStatus());
    	
    	System.out.println("After processing");
    	System.out.println(ts.toString());
    	System.out.println(ts1.toString());
    	System.out.println(ts2.toString());
    	System.out.println(ts3.toString());
    	System.out.println(ts4.toString());
    	System.out.println(ts5.toString());
    
    	
    	ps.priceUpdateFromStocks(15, "IBM");
    
   
    	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts1.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts2.getStatus());
    	Assert.assertEquals(TradingStatus.PENDING, ts3.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts4.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts5.getStatus());
    	
    	System.out.println("After processing");
    	System.out.println(ts.toString());
    	System.out.println(ts1.toString());
    	System.out.println(ts2.toString());
    	System.out.println(ts3.toString());
    	System.out.println(ts4.toString());
    	System.out.println(ts5.toString());
    	
    	
    	
    	ps.priceUpdateFromStocks(115, "IBM");
    	System.out.println("After processing");
    	Assert.assertEquals(TradingStatus.EXECUTED, ts.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts1.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts2.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts3.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts4.getStatus());
    	Assert.assertEquals(TradingStatus.EXECUTED, ts5.getStatus());
    	System.out.println(ts.toString());
    	System.out.println(ts1.toString());
    	System.out.println(ts2.toString());
    	System.out.println(ts3.toString());
    	System.out.println(ts4.toString());
    	System.out.println(ts5.toString());
   
    }
}
