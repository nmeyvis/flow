package com.rfgbot.anywhereparse.addon.addons.translate;

import com.rfgbot.anywhereparse.addon.Addon;

/**
 * Created by nickm on 3/13/2017.
 */
public class TranslateAddon implements Addon {
    @Override
    public String getName() {
        return "translate";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String[] getAliases() {
        return new String[] { "lang" };
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onUnload() {
    }

    public GoogleTranslate get(String targetLang) {
        return new GoogleTranslate("en", targetLang);
    }
}
