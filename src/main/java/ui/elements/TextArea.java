package ui.elements;

import com.microsoft.playwright.Locator;
import ui.IWebContext;
import ui.elements.locator.Loc;

public class TextArea extends Element {

    public TextArea(String name, IWebContext context, Loc loc) {
        super(name, context, loc);
    }

    public TextArea(String name, IWebContext context, Loc loc, boolean handleInitialization) {
        super(name, context, loc, handleInitialization);
    }

    public void setText(String text) {
        ACTION("Set Text \n" + text + "\n");
        getPWLoc().fill(text);
    }

    //should work with invisible fields, but need to test
    public void setTextWithForce(String text) {
        getPWLoc().fill(text, new Locator.FillOptions().setForce(true));
    }

    public String getValue() {
        return getPWLoc().inputValue();
    }

}
