package it.unibo.oop.lab.advanced;

public class DrawNumberViewImplOnStdout implements DrawNumberView {

   private static final String NEW_GAME = ": a new game starts!";

    private DrawNumberViewObserver observer;

    public final void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }
    public void start() {
    }
    public final void numberIncorrect() {
        System.out.println("Incorrect Number.. try again");
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
        System.out.println("You lost" + NEW_GAME);
    }
    protected final void plainMessage(final String msg) {
        System.out.println(msg);
    }
    public final void displayError(final String message) {
        System.out.println(message);
    }

}