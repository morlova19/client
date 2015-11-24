package listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public interface CustomWindowListener extends WindowListener {
    @Override
    default void windowOpened(WindowEvent e){}

    @Override
    void windowClosing(WindowEvent e);

    @Override
    default void windowClosed(WindowEvent e){}

    @Override
    default void windowIconified(WindowEvent e){}

    @Override
    default void windowDeiconified(WindowEvent e){}

    @Override
    default void windowActivated(WindowEvent e){}

    @Override
    default void windowDeactivated(WindowEvent e){}
}
