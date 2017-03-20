package com.rfgbot.flow.addon.addons.translate;

import com.rfgbot.flow.addon.Addon;

/**
 * Created by nickm on 3/13/2017.
 */
public class TranslateAddon implements Addon {
    @Override
    public String getName() {
        return "Translate";
    }

    @Override
    public String getDescription() {
        return "Translates your text into any language";
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
