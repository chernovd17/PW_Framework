package ui.elements;

import ui.IWebContext;
import ui.elements.locator.Loc;

public class Checkbox extends Element {

    public Checkbox(String name, IWebContext context, Loc loc) {
        super("'" + name + "' Checkbox", context, loc);
    }

    public Checkbox(String name, IWebContext context, Loc loc, boolean handleInitialization) {
        super("'" + name + "' Checkbox", context, loc, handleInitialization);
    }

    public void check() {
        ACTION("Check" + getLogicalName());
        getPWLoc().check();
    }

    public void uncheck() {
        ACTION("Uncheck" + getLogicalName());
        getPWLoc().uncheck();
    }

    public boolean isChecked() {
        return getPWLoc().isChecked();
    }

    public boolean isUnchecked() {
        return !isChecked();
    }
}
