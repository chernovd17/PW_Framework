package ui.elements;

import ui.IWebContext;
import ui.elements.locator.Loc;

public class RadioButton extends Element{

    public RadioButton(String name, IWebContext context, Loc loc) {
        super(name, context, loc);
    }

    public RadioButton(String name, IWebContext context, Loc loc, boolean handleInitialization) {
        super(name, context, loc, handleInitialization);
    }

    public void select(){
        ACTION(String.format("Select %s option",getLogicalName()));
        getPWLoc().check();
    }

    public boolean isSelected() {
        return getPWLoc().isChecked();
    }


}
