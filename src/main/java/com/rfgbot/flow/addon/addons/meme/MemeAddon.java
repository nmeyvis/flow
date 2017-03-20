package com.rfgbot.flow.addon.addons.meme;

import com.rfgbot.flow.addon.Addon;
import com.rfgbot.flow.addon.exception.UserInputException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nickm on 3/14/2017.
 */
public class MemeAddon implements Addon {

    private static Map<String, String> memeMap = new HashMap<>();

    static {
        memeMap.put("fliptable", "(╯°□°）╯︵ ┻━┻");
        memeMap.put("knife", ")xxxxx[;;;;;;;;;>");
        memeMap.put("smug", "(‾⌣‾)");
        memeMap.put("gimme", "༼ つ ◕_◕ ༽つ");
        memeMap.put("stroll", "ᕕ( ᐛ )ᕗ");
        memeMap.put("power", "ᕦ(ò_óˇ)ᕤ");
        memeMap.put("lenny", "( ͡° ͜ʖ ͡°)");
    }

    @Override
    public String getName() {
        return "memes";
    }

    @Override
    public String getDescription() {
        return "gets you the memes";
    }

    @Override
    public String[] getAliases() {
        return new String[] { "meme" };
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onUnload() {

    }

    public String get(String name) {
        String meme = memeMap.get(name);

        if(meme == null) {
            throw new UserInputException("no meme by " + name);
        }

        return meme;
    }
}
