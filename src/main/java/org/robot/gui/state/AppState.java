package org.robot.gui.state;

import javax.swing.*;
import java.io.Serializable;

public class AppState implements Serializable {
    private WindowState gameWindowState;
    private WindowState logWindowState;
    private WindowState coordinatedWindowState;

    public AppState(JInternalFrame gameWindow, JInternalFrame logWindow, JInternalFrame coordinatedWindow){
        gameWindowState = WindowStateBuild.buildFromJInternalFrame(gameWindow);
        logWindowState = WindowStateBuild.buildFromJInternalFrame(logWindow);
        coordinatedWindowState = WindowStateBuild.buildFromJInternalFrame(coordinatedWindow);
    }

    public WindowState getGameWindowState() {
        return gameWindowState;
    }

    public WindowState getLogWindowState() {
        return logWindowState;
    }

    public WindowState getCoordinatedWindowState(){ return coordinatedWindowState; }

}
