package ui.containers.example.forms;

import ui.IWebContext;
import ui.containers.BaseElementContainer;
import ui.elements.Input;
import ui.elements.locator.Loc;

public class StudentFormContainer extends BaseElementContainer {

    private final Input firstName = new Input("First Name", this, Loc.id("firstName"));
    private final Input lastName = new Input("Last Name", this, Loc.id("lastName"));
    private final Input email = new Input("Email", this, Loc.id("userEmail"));


    public StudentFormContainer(IWebContext parent) {
        super("Student Registration Form", parent, Loc.id("userForm"));
    }

    public void setFirstName(String name) {
        firstName.setText(name);
    }

    public void setLastName(String name) {
        lastName.setText(name);
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }
}
