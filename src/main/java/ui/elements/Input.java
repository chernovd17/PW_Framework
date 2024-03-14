package ui.elements;

import ui.IWebContext;
import ui.elements.locator.Loc;

public class Input extends Element {

    public Input(String name, IWebContext context, Loc loc) {
        super("'" + name + "' Input", context, loc);
    }

    public Input(String name, IWebContext context, Loc loc, boolean handleInitialization) {
        super("'" + name + "' Input", context, loc, handleInitialization);
    }

    public void setText(String text) {
        fillText(text);
    }

    public String getValue() {
        return getPWLoc().inputValue();
    }

}
