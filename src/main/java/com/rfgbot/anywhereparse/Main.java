package com.rfgbot.anywhereparse;

import com.rfgbot.anywhereparse.addon.AddonRegistry;
import com.rfgbot.anywhereparse.addon.GetParadigmAddonExecutor;
import com.rfgbot.anywhereparse.addon.currency.CurrencyAddon;
import com.rfgbot.anywhereparse.addon.meme.MemeAddon;
import com.rfgbot.anywhereparse.addon.translate.TranslateAddon;
import com.rfgbot.anywhereparse.key.KeyComboListener;
import com.rfgbot.anywhereparse.key.KeyCombo;
import com.rfgbot.anywhereparse.parse.DefaultParser;
import com.rfgbot.anywhereparse.ui.ApplicationFX;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nickm on 3/11/2017.
 */
public class Main {
    static ApplicationFX applicationFX = new ApplicationFX();

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(Main::exit));

        new Thread(() -> {
            System.out.println("listening...");

            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);

            logger.setUseParentHandlers(false);

            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }

            KeyCombo trigger = new KeyCombo(NativeKeyEvent.VC_CONTROL, NativeKeyEvent.VC_ALT, NativeKeyEvent.VC_F);

            Interpreter interpreter = new Interpreter(AddonRegistry.getInstance(), new DefaultParser(), new GetParadigmAddonExecutor(), new JavaFXAddonExeErrorHandler(applicationFX));

            ClipboardIO clipboardIO = new ClipboardIO();

            new App(trigger, clipboardIO, clipboardIO, interpreter);

            GlobalScreen.addNativeKeyListener(new KeyComboListener(KeyCombo.Native.COPY, () -> {
                System.out.println("triggered copy");
            }));

            AddonRegistry.getInstance().register(new CurrencyAddon());
            AddonRegistry.getInstance().register(new TranslateAddon());
            AddonRegistry.getInstance().register(new MemeAddon());
        }).start();

        applicationFX.start();
    }

    public static void exit() {
        try {
            GlobalScreen.unregisterNativeHook();
            System.exit(1);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
}
