package management.selenium.elements;

import management.selenium.web_context.IWebContext;
import org.openqa.selenium.By;

public class Select extends Element {

    public Select(String name, IWebContext context, By loc) {
        super(name, context, loc);
    }

    public void selectValue(String value) {
        ACTION(String.format("Select value: %s, from dropdown: %s", value, getLogicalName()));
        try{
            org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(getWebElement());
            select.selectByValue(value);
        } catch (Throwable e){
            logError(String.format("ERROR caused by element %s, function selectValue().\n ERROR: %s", getLogicalName(), e));
        }
    }

}
