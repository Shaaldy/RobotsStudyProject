package org.robot.gui.state;

import javax.swing.*;
import java.io.Serializable;

public class AppState implements Serializable {
    private WindowState gameWindowState;
    private WindowState logWindowState;

    public AppState(JInternalFrame gameWindow, JInternalFrame logWindow){
        gameWindowState = WindowStateBuild.buildFromJInternalFrame(gameWindow);
        logWindowState = WindowStateBuild.buildFromJInternalFrame(logWindow);
    }

    public WindowState getGameWindowState() {
        return gameWindowState;
    }

    public WindowState getLogWindowState() {
        return logWindowState;
    }

}
