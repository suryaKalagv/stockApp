package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.ExecutionServiceImpl;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {
	
	private String security;

	private int volume;
	private TradingType type;
	private double price;
	private TradingStatus status;

	

	public TradingStrategy(String security, int volume,TradingType type, double price){
		this.security = security;
		this.volume = volume;
		this.type = type;
		this.price = price;
		this.status = TradingStatus.PENDING;
	}
	
	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public TradingType getType() {
		return type;
	}

	public void setType(TradingType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public TradingStatus getStatus() {
		return status;
	}

	public void setStatus(TradingStatus status) {
		this.status = status;
	}
	
	public synchronized void  execute(double currentprice) {
		if (status == TradingStatus.PENDING) {
			System.out.println("Processing :" + this.toString());
			status = TradingStatus.PROCESSING;
			ExecutionService es = new ExecutionServiceImpl();
			if (type == TradingType.BUY) {
				es.buy(security, currentprice, volume);
			} else {
				es.sell(security, currentprice, volume);
			}
			status = TradingStatus.EXECUTED;
		}
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ Security:'").append(security).append("' price:").append(price);
		sb.append(" volume:").append(volume).append(" Type:'").append(type).append("' Status:'").append(getStatus().name()).append("'}");
		return sb.toString();
	}
}
