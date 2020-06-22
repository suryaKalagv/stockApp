package com.acme.mytrader.execution;

import org.junit.Assert;
import org.junit.Test;

public class ExecutionTest {

	
	@Test
	public void buyTest() {
		ExecutionServiceImpl es = new ExecutionServiceImpl();
		es.buy("IBM", -1.1, -10);
		Assert.assertEquals(0, es.getPrice(),0);
		Assert.assertEquals(0, es.getVolume(),0);
		
		es.buy("IBM", 10, 100);
		Assert.assertEquals(10, es.getPrice(),0);
		Assert.assertEquals(100, es.getVolume(),0);
		
	}

	@Test
	public void sellTest() {
		ExecutionServiceImpl es = new ExecutionServiceImpl();
		es.sell("IBM", -1.1, -10);
		Assert.assertEquals(0, es.getPrice(),0);
		Assert.assertEquals(0, es.getVolume(),0);
		
		es.sell("IBM", 10, 100);
		Assert.assertEquals(10, es.getPrice(),0);
		Assert.assertEquals(100, es.getVolume(),0);
		
		
	}

}
