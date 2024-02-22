package LOGGER.info;

import lombok.Getter;

import java.awt.*;

@Getter
public enum InfoRowType {

    /**
     * STEP -- business logic level (e.g. "Login to the app")
     * ACTION -- element level (e.g. "Click 'Login Button'")
     * SUCCESS/FAIL/ERROR -- verification level
     * **/
    STEP("STEP", Color.black),
    ACTION("ACTION", Color.gray),
    SUCCESS("VERIFICATION", Color.green),
    FAIL("VERIFICATION", Color.orange),
    ERROR("VERIFICATION", Color.red);

    private final String name;
    private final Color color;

    InfoRowType(String name, Color color){
        this.name = name;
        this.color = color;
    }

}
