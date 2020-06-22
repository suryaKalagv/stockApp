package com.acme.mytrader.price;


import org.junit.Assert;
import org.junit.Test;

public class PriceListenerTest {
	@Test
	public void updateMultiplelistenersTest() {
	// A Price Source instance to connect to price Source and get stock price
	PriceSourceImpl ps = new PriceSourceImpl();

	//Price Source has a map of listeners getting listener for one security
	PriceListenerImpl ibmlistener = new PriceListenerImpl("IBM");
	PriceListenerImpl kolistener = new PriceListenerImpl("KO");
   ps.addPriceListener(ibmlistener);
   ps.addPriceListener(kolistener);
   
   
	ps.priceUpdateFromStocks(100, "IBM");
	ps.priceUpdateFromStocks(50, "KO");
	System.out.println(ibmlistener.getPrice());
	Assert.assertEquals(100, ibmlistener.getPrice(),0);
	Assert.assertEquals(50, kolistener.getPrice(),0);
		
	}
}
