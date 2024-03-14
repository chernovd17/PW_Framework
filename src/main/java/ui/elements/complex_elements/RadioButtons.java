package ui.elements.complex_elements;

import com.beust.ah.A;
import ui.IWebContext;
import ui.containers.BaseElementContainer;
import ui.elements.RadioButton;
import ui.elements.locator.Loc;

import java.util.ArrayList;
import java.util.List;

public class RadioButtons extends BaseElementContainer {

    private List<RadioButton> buttons = new ArrayList<>();

    public RadioButtons(String name, IWebContext parent, Loc loc, List<RadioButton> buttons) {
        super(name, parent, loc);
        this.buttons = buttons;
    }

    private RadioButton getSelectedRadioButton() {
        return buttons.stream().filter(RadioButton::isSelected).findFirst().orElse(null);
    }

    private RadioButton getRadioButtonByValue(String value) {
        return buttons.stream().filter(button -> button.getProperty("value").equalsIgnoreCase(value)).findFirst().orElse(null);
    }


    public void selectRadioButton(String value) {
        RadioButton requireButton = getRadioButtonByValue(value);
        if(requireButton != null)
            requireButton.select();
        else
            FATAL("Radio Button with value " + value + " not found");
    }
}
