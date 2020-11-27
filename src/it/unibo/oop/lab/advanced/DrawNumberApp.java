package it.unibo.oop.lab.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> view;

    /**
     * 
     */
    public DrawNumberApp() {
        final ReadFromResources rfr = new ReadFromResources();
        final int min = rfr.readIntFromResources();
        final int max = rfr.readIntFromResources();
        final int attempts = rfr.readIntFromResources();
        this.model = new DrawNumberImpl(min, max, attempts);
        this.view = new ArrayList<>();
        final DrawNumberView mainView = new DrawNumberViewImpl();
        mainView.setObserver(this);
        mainView.start();
        this.view.add(new DrawNumberViewImplOnFile());
        this.view.add(new DrawNumberViewImplOnStdout());
        this.view.add(mainView);
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView vi: this.view) {
                vi.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView vi: this.view) {
                vi.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView vi: this.view) {
                vi.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
