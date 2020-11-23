package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class UseController implements Controller {

    private String currentString;
    private final List<String> printedStrings;
    
    public UseController() {
        this.currentString = null;
        this.printedStrings = new ArrayList<>();
    }
    public final void setCurrentString(final String sentence) throws Exception {
        if (sentence != null) {
            this.currentString = sentence;
        } else {
            throw new IllegalStateException("Exception due to null sentence to print..");
        }
    }
    public final String getCurrentString() {
        return this.currentString;
    }
    public final List<String> getPrintedStrings() {
        return List.copyOf(this.printedStrings);
    }
    public final void printCurrentString() throws Exception {
        if (this.currentString != null) {
            System.out.println(this.currentString);
            this.printedStrings.add(this.currentString);
        } else {
            throw new IllegalStateException("Exception due to null current string to print..");
        }
    }

}
