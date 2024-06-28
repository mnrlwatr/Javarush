package JavaRush.tasks.Quest3.task32.task3209.listeners;

import JavaRush.tasks.Quest3.task32.task3209.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The type Frame listener.
 *
 * .
 */
public class FrameListener extends WindowAdapter {
    private View view;

    /**
     * Instantiates a new Frame listener.
     *
     * @param view the view
     */
    public FrameListener(View view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        view.exit();
        super.windowClosing(e);
    }
}
