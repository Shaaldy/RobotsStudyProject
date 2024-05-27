package org.robot.gui.state;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class WindowState implements Serializable {

    private final Dimension windowSize;
    private final Point locaction;
    private final boolean isMinimized;


    public WindowState(Dimension windowSize, Point locaction, boolean isMinimized) {
        this.windowSize = windowSize;
        this.locaction = locaction;
        this.isMinimized = isMinimized;
    }

    public Dimension getSize() {
        return windowSize;
    }

    public Point getLocaction() {
        return locaction;
    }

    public boolean isMinimized() {
        return isMinimized;
    }
}


class WindowStateBuild {
    public static WindowState buildFromJInternalFrame(JInternalFrame frame){
        return new WindowState(frame.getSize(), frame.getLocation(), frame.isIcon());
    }
}
