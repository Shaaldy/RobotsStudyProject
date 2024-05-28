package org.robot.gui.windows;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import org.robot.custom.DefaultRobot;
import org.robot.gui.Loader;
import org.robot.gui.LocalizationManager;
import org.robot.gui.model.IRobot;
import org.robot.gui.state.AppState;
import org.robot.gui.state.WindowState;
import org.robot.log.Logger;


public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final LogWindow logWindow;
    private final GameWindow gameWindow;
    private final CoordinatedWindow coordinatedWindow;
    private final RobotLoaderWindow robotLoaderWindow;
    IRobot robot;
    public MainApplicationFrame( WindowState gameWindowState, WindowState logWindowState, WindowState coordinatedWindowState, WindowState robotLoaderWindow){
        int inset = 50;
        this.setLocation(new Point(inset, inset));
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        setContentPane(desktopPane);
        String classRobot = Loader.deserializeRobot();
        if (classRobot != null){
            try {
                Class<?> robotClass = Class.forName(classRobot);
                Constructor<?> constructor = robotClass.getConstructor();
                this.robot = (IRobot) constructor.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            this.robot = new DefaultRobot(10, 10);
        }
        this.logWindow = initLogWindow(logWindowState);
        this.gameWindow = new GameWindow(gameWindowState, robot);
        this.coordinatedWindow = new CoordinatedWindow(coordinatedWindowState, robot);
        this.robotLoaderWindow = new RobotLoaderWindow(gameWindow, coordinatedWindow, robot, robotLoaderWindow);

        addWindow(this.logWindow);
        addWindow(this.gameWindow);
        addWindow(this.coordinatedWindow);
        addWindow(this.robotLoaderWindow);

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
                        new Point(300, 100), false),
                new WindowState(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()),
                        new Point(300, 300), false));
    }

    public MainApplicationFrame(AppState state){
        this(state.getGameWindowState(), state.getLogWindowState(), state.getCoordinatedWindowState(), state.getRobotLoaderWindowState());
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    protected LogWindow initLogWindow(WindowState logWindowState){
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource(), logWindowState);
        Logger.debug(LocalizationManager.getKey("log.work"));
        return logWindow;
    }

    protected void confirmWindowClose(){
        int option = JOptionPane.showOptionDialog(
                this,
                LocalizationManager.getKey("message.exit.confirm"),
                LocalizationManager.getKey("title.confirm.exit"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{LocalizationManager.getKey("button.yes"), LocalizationManager.getKey("button.no")},
                LocalizationManager.getKey("button.no")
        );

        if (option == JOptionPane.YES_OPTION) {
            Loader.serializeAppState(heapState());
            Loader.serializeRobot(robotLoaderWindow.getRobot());
            Loader.serializeLanguage(LocalizationManager.getCurLocale());
            System.exit(0);

        }
    }

    private AppState heapState() {
        return new AppState(gameWindow, logWindow, coordinatedWindow, robotLoaderWindow);
    }

    public  void updateText(){
        setJMenuBar(new MenuBar(this));
        logWindow.setTitle(LocalizationManager.getKey("title.log"));
        gameWindow.setTitle(LocalizationManager.getKey("title.field"));
        coordinatedWindow.setTitle(LocalizationManager.getKey("title.cor"));
        robotLoaderWindow.setTitle(LocalizationManager.getKey("title.robots"));
        SwingUtilities.updateComponentTreeUI(this);
    }
}