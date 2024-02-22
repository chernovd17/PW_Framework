package ui.elements;

import ui.IWebContext;
import ui.elements.locator.Loc;

import java.io.File;

public class UploaderElement extends Element {

    public UploaderElement(String title, IWebContext context, Loc loc) {
        super(title + " uploader", context, loc);
    }

    public void upload(File file) {
        try {
            getPWLoc().setInputFiles(file.toPath());
        } catch (Throwable e) {
            //throw new ElementException(this, "Загрузить файл", e, file);
        }
    }
}
