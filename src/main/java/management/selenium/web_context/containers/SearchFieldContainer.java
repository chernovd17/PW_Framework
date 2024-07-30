package management.selenium.web_context.containers;

import management.selenium.elements.Button;
import management.selenium.elements.Input;
import management.selenium.web_context.IWebContext;
import org.openqa.selenium.By;

public class SearchFieldContainer extends BaseElementContainer {

    private final Input whereSearchField;
    private final Button searchButton;

    public SearchFieldContainer(IWebContext parent) {
        super("Search Field", parent, By.cssSelector("div[aria-labelledby='search-block-tab-STAYS']"));
        whereSearchField = new Input("Where Search Field",this, By.id("bigsearch-query-location-input"));
        searchButton = new Button("Search Button",this, By.cssSelector("button[data-testid='structured-search-input-search-button']"));
    }

    public void setWhereSearchField(String where) {
        STEP("Set Where Search Field");
        whereSearchField.setText(where);
    }

    public void clickSearchButton() {
        STEP("Click Search Button");
        searchButton.click();
    }

    public String getWhereSearchField() {
        return whereSearchField.getText();
    }

}
