package JavaRush.tasks.Quest3.task32.task3209.listeners;

import JavaRush.tasks.Quest3.task32.task3209.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * .
 *
 * Слушает и обрабатывает изменения состояния панели вкладок
 */
public class TabbedPaneChangeListener implements ChangeListener{
    private View view;

    public TabbedPaneChangeListener(View view) {
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();
    }
}
