package com.rfgbot.flow;

import com.rfgbot.flow.addon.AddonRegistry;
import com.rfgbot.flow.addon.GetParadigmAddonExecutor;
import com.rfgbot.flow.addon.addons.currency.CurrencyAddon;
import com.rfgbot.flow.addon.addons.meme.MemeAddon;
import com.rfgbot.flow.addon.addons.translate.TranslateAddon;
import com.rfgbot.flow.key.KeyCombo;
import com.rfgbot.flow.parse.DefaultParser;
import com.rfgbot.flow.ui.ApplicationFX;
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
                LOG.error("failed to register native hook", e);
            }

            KeyCombo trigger = new KeyCombo(NativeKeyEvent.VC_CONTROL, NativeKeyEvent.VC_ALT, NativeKeyEvent.VC_F);

            Interpreter interpreter = new Interpreter(AddonRegistry.getInstance(), new DefaultParser('$', ':', '|'),
                    new GetParadigmAddonExecutor(), new JavaFXAddonExeErrorHandler(applicationFX));

            ClipboardIO clipboardIO = new ClipboardIO();

            new App(trigger, clipboardIO, clipboardIO, interpreter);

            /*GlobalScreen.addNativeKeyListener(new KeyComboListener(KeyCombo.Native.COPY, () -> {
                System.out.println("triggered copy");
            }));*/

            AddonRegistry.getInstance().register(new CurrencyAddon());
            AddonRegistry.getInstance().register(new TranslateAddon());
            AddonRegistry.getInstance().register(new MemeAddon());
        }).start();

        applicationFX.launchIt();
    }

    public static void exit() {

        try {
            GlobalScreen.unregisterNativeHook();
            System.exit(1);
        } catch (NativeHookException e) {
            LOG.error("failed to exit", e);
        }


    }
}
