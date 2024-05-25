package org.robot.gui.state;

import javax.swing.*;
import java.io.Serializable;

public class AppState implements Serializable {
    private WindowState gameWindowState;
    private WindowState logWindowState;
    private WindowState coordinatedWindowState;

    private WindowState robotLoaderWindowState;

    public AppState(JInternalFrame gameWindow, JInternalFrame logWindow, JInternalFrame coordinatedWindow, JInternalFrame robotLoaderWindow){
        gameWindowState = WindowStateBuild.buildFromJInternalFrame(gameWindow);
        logWindowState = WindowStateBuild.buildFromJInternalFrame(logWindow);
        coordinatedWindowState = WindowStateBuild.buildFromJInternalFrame(coordinatedWindow);
        robotLoaderWindowState = WindowStateBuild.buildFromJInternalFrame(robotLoaderWindow);
    }

    public WindowState getGameWindowState() {
        return gameWindowState;
    }

    public WindowState getLogWindowState() {
        return logWindowState;
    }

    public WindowState getCoordinatedWindowState(){ return coordinatedWindowState;}

    public WindowState getRobotLoaderWindowState(){ return robotLoaderWindowState;}



}
