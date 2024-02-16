package ui.elements.locator;

public enum LocType {

    xpath("xpath=%s"),
    css("css=%s"),
    name("[name=%s]"),//css
    id("id=%s");
    private String type;

    LocType(String label) {
        this.type = label;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getType() {
        return type;
    }

}
