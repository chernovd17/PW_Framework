package ui.elements.locator;

public class Loc {

    public LocType locType;
    public String locBody;

    public Loc(LocType locType, String locBody){
        this.locType = locType;
        this.locBody = locBody;
    }

    private static Loc createLoc(LocType locType, String locBody){
        return new Loc(locType, locBody);
    }

    public String toString(){
        return String.format(locType.toString(), locBody);
    }

    public static Loc xpath(String locBody){
        return createLoc(LocType.xpath, locBody);
    }

    public static Loc css(String locBody){
        return createLoc(LocType.css, locBody);
    }

    public static Loc id(String locBody){
        return createLoc(LocType.id, locBody);
    }

    public static Loc name(String locBody){
        return createLoc(LocType.name, locBody);
    }

}
