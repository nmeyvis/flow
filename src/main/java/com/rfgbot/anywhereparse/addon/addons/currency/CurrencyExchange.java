package com.rfgbot.anywhereparse.addon.addons.currency;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;

/**
 * Created by nickm on 3/11/2017.
 */
public class CurrencyExchange {
    private Currency source;
    private Currency target;

    public CurrencyExchange(Currency source, Currency target) {
        this.source = source;
        this.target = target;
    }

    public String get(String amount) {
        try {
            System.out.println(target.getSymbol());
            System.out.println(target.getCurrencyCode());
            Stock stock = YahooFinance.get(target.getCurrencyCode() + "=X");
            BigDecimal price = stock.getQuote(true).getPrice();
            BigDecimal convertedAmount = price.multiply(new BigDecimal(Double.parseDouble(amount)));

            NumberFormat currencyFormat = NumberFormat.getInstance();
            currencyFormat.setCurrency(target);
            return currencyFormat.format(convertedAmount) +" (" +  target.getCurrencyCode() + ")";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
