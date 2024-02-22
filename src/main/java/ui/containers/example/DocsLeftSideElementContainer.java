package ui.containers.example;

import ui.IWebContext;
import ui.containers.BaseElementContainer;
import ui.elements.Element;
import ui.elements.locator.Loc;

public class DocsLeftSideElementContainer extends BaseElementContainer {

    public DocsLeftSideElementContainer(IWebContext parent) {
        super("Left Menu", parent, Loc.css("nav[aria-label='Docs sidebar']"));
    }

    public void clickOption(String option){
        Element optionElement = createOption(option);
        optionElement.click();
    }

    private Element createOption(String option){
        return new Element(option + " option", this,
                Loc.xpath(".//a[contains(@class, 'menu') and text()='" + option + "']"));
    }

}
