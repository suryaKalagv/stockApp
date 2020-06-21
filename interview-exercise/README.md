#Solution
 Please run the test case testTradeStrategy in TradingStrategyTest 
 Program flow 
1. Price Source creates a listener for each security or stock 
2. Price listener receives price update from Price Source.
3. Price listener triggers validation of trading strategies with new price
4. List of valid BUY strategies and SELL strategies is created
5. Execution service on  valid Trade strategies is called synchronously
6. Executed trade strategies are removed from the listeners list.

# Tetcase output
Price updated for security IBM as 45.0
Processing :{ Security:'IBM' price:50.0 volume:100 Type:'BUY'}
Buying IBM at price 45.0 Volume 100
Processing :{ Security:'IBM' price:50.0 volume:500 Type:'BUY'}
Buying IBM at price 45.0 Volume 500
Processing :{ Security:'IBM' price:40.0 volume:300 Type:'SELL'}
Selling IBM at price 45.0 Volume 300
Processing :{ Security:'IBM' price:40.0 volume:600 Type:'SELL'}
Selling IBM at price 45.0 Volume 600

  Price updated for security IBM as 15.0
Processing :{ Security:'IBM' price:25.0 volume:200 Type:'BUY'}
Buying IBM at price 15.0 Volume 200

  Price updated for security IBM as 115.0
Processing :{ Security:'IBM' price:60.0 volume:400 Type:'SELL'}
Selling IBM at price 115.0 Volume 400


## User Story

As a trader I want to be able to monitor stock prices such that when they breach a trigger level orders can be executed automatically.

## Exercise

Given the following interface definitions (provided)

```
public interface ExecutionService {
    void buy(String security, double price, int volume);
    void sell(String security, double price, int volume);
}
```

```
public interface PriceListener {
    void priceUpdate(String security, double price);
}
```

```
public interface PriceSource {
    void addPriceListener(PriceListener listener);
    void removePriceListener(PriceListener listener);
}
```

Develop a basic implementation of the PriceListener interface that provides the following behavior:

1. Connects to a PriceSource instance
1. Monitors price movements on a specified single stock (e.g. "IBM")
1. Executes a single "buy" instruction for a specified number of lots (e.g. 100) as soon as the price of that stock is seen to be below
a specified price (e.g. 55.0). Don’t worry what units that is in.

### Considerations

* Please "work out loud" and ask questions
* This is not a test of your API knowledge so feel free to check the web as reference
* There is no specific solution we are looking for

### Some libraries already available:

* Java 8
* JUnit 4
* Mockito
* EasyMock
* JMock
