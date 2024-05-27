package org.robot.gui.windows;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import org.robot.gui.Loader;
import org.robot.gui.model.Robot;
import org.robot.gui.state.AppState;
import org.robot.gui.state.WindowState;
import org.robot.log.Logger;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final LogWindow logWindow;
    private final GameWindow gameWindow;
    private final CoordinatedWindow coordinatedWindow;
    public MainApplicationFrame( WindowState gameWindowState, WindowState logWindowState, WindowState coordinatedWindowState){
        int inset = 50;
        this.setLocation(new Point(inset, inset));
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        setContentPane(desktopPane);
        Robot robot = new Robot(100, 100);
        logWindow = initLogWindow(logWindowState);
        gameWindow = new GameWindow(gameWindowState, robot);
        coordinatedWindow = new CoordinatedWindow(coordinatedWindowState, robot);

        addWindow(logWindow);
        addWindow(gameWindow);
        addWindow(coordinatedWindow);

        setJMenuBar(new MenuBar(this));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmWindowClose();
            }
        });
    }

    public MainApplicationFrame(){
        this(new WindowState(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()),
                        new Point(300, 100), false),
                new WindowState(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()),
                    new Point(10, 10), false),
                new WindowState(new Dimension(300, 100),
                        new Point(300, 100), false));
    }

    public MainApplicationFrame(AppState state){
        this(state.getGameWindowState(), state.getLogWindowState(), state.getCoordinatedWindowState());
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    protected LogWindow initLogWindow(WindowState logWindowState){
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource(), logWindowState);
        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void confirmWindowClose(){
        int option = JOptionPane.showOptionDialog(
                this,
                "Вы уверены, что хотите выйти?",
                "Подтверждение выхода",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Да", "Нет"},
                "Нет"
        );

        if (option == JOptionPane.YES_OPTION) {
            Loader.serializeAppState(heapState());
            System.exit(0);

        }
    }

    private AppState heapState() {
        return new AppState(gameWindow, logWindow, coordinatedWindow);
    }

}