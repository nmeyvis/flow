package com.rfgbot.anywhereparse.addon.addons.currency;

import com.rfgbot.anywhereparse.addon.Addon;
import com.rfgbot.anywhereparse.addon.exception.UserInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Currency;

/**
 * Created by nickm on 3/11/2017.
 */
public class CurrencyAddon implements Addon {
    private static final Logger LOG = LoggerFactory.getLogger(CurrencyAddon.class);

    @Override
    public String getName() {
        return "Currency Util";
    }

    @Override
    public String getDescription() {
        return "Allows conversion between different currencies";
    }

    @Override
    public String[] getAliases() {
        return new String[] { "$" };
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onUnload() {

    }

    public CurrencyExchange get(String currencyCode) {
        LOG.debug("Addon started");
        try {
            return new CurrencyExchange(Currency.getInstance("USD"), Currency.getInstance(currencyCode.toUpperCase()));
        } catch(IllegalArgumentException e) {
            throw new UserInputException("invalid currency code");
        }
    }
}
