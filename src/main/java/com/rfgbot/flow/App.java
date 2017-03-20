package com.rfgbot.flow;

import com.rfgbot.flow.key.KeyCombo;
import com.rfgbot.flow.key.KeyComboListener;
import org.jnativehook.GlobalScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by nickm on 3/12/2017.
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private KeyCombo hotkey;
    private InputProvider input;
    private CompiledOut output;
    private Interpreter interpreter;

    public App(KeyCombo hotkey, InputProvider input, CompiledOut output, Interpreter interpreter) {
        this.hotkey = hotkey;
        this.input = input;
        this.output = output;
        this.interpreter = interpreter;

        GlobalScreen.addNativeKeyListener(new KeyComboListener(hotkey, this::run));
    }

    private void run() {
        LOG.debug("captured hotkey");
        try {
            output.output(interpreter.interpret(input.getInput()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.debug("finished interpreting");
    }

    public KeyCombo getHotkey() {
        return hotkey;
    }
}
