package ui.elements;


import ui.IWebContext;
import ui.elements.locator.Loc;

public class Button extends Element {

    public Button(String name, IWebContext context, Loc loc) {
        super("'" + name + "' Button", context, loc);
    }
}
