package management.selenium.elements;

import management.selenium.web_context.IWebContext;
import org.openqa.selenium.By;

public class Link extends Element {

    public Link(String name, IWebContext context, By loc) {
        super(name, context, loc);
    }

    public String getLink(){
        return getAttribute("href");
    }
}
