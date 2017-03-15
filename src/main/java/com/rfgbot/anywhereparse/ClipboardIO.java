package com.rfgbot.anywhereparse;

import com.rfgbot.anywhereparse.key.KeyCombo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by nickm on 3/11/2017.
 */
public class ClipboardIO implements InputProvider, CompiledOut {

    private static final Logger LOG = LoggerFactory.getLogger(ClipboardIO.class);

    private Clipboard clipboard;
    private Robot robot;

    private String clipboardRestore; // used to restore the clipboard contents

    public ClipboardIO() {
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            robot  = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private void restore() {
        LOG.debug("restoring clipboard to: " + clipboardRestore);
        StringSelection selection = new StringSelection(clipboardRestore);
        clipboard.setContents(selection, selection);
    }

    /**
     * Gets the input by selecting everything at the cursor, then copies it into the clipboard for reading
     * @return the read text
     * @throws IOException failed to read from clipboard
     */
    @Override
    public String getInput() throws IOException {
        try {
            clipboardRestore = (String) clipboard.getData(DataFlavor.stringFlavor);
            Thread.sleep(100); // otherwise may combine keys presses with event that calls this
            KeyCombo.AWT.SELECT_ALL.emulate(robot);
            KeyCombo.AWT.COPY.emulate(robot);
            Thread.sleep(100); // clipboard is update is delayed

            return (String) clipboard.getData(DataFlavor.stringFlavor);
        }  catch (UnsupportedFlavorException | InterruptedException e) {
           throw new RuntimeException(e);
        }
    }

    /**
     * Outputs to the cursor. Selects everything to replace.
     * @param text the text to output
     */
    @Override
    public void output(String text) {
        KeyCombo.AWT.SELECT_ALL.emulate(robot);
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
        KeyCombo.AWT.PASTE.emulate(robot);

        try {
            Thread.sleep(200); // paste may use the restored clipboard if there is no delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        restore();
    }
}
