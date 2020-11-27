package it.unibo.oop.lab.advanced;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrawNumberViewImplOnFile implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";
    private static final String PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + DrawNumberViewImplOnFile.class.getSimpleName() + ".txt";

    private DrawNumberViewObserver observer;

    public final void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }
    public void start() {
    }
    public final void numberIncorrect() {
        try (
                DataOutputStream dstream = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(PATH)));
        ) {
            dstream.writeUTF("Incorrect Number.. try again");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public final void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            plainMessage(res.getDescription());
            return;
        case YOU_WON:
            plainMessage(res.getDescription() + NEW_GAME);
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
    }
    public final void limitsReached() {
        try (
                DataOutputStream dstream = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(PATH)));
        ) {
            dstream.writeUTF("You lost" + NEW_GAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected final void plainMessage(final String msg) {
        try (
                DataOutputStream dstream = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(PATH)));
        ) {
            dstream.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public final void displayError(final String message) {
        try (
                DataOutputStream dstream = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(PATH)));
        ) {
            dstream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
