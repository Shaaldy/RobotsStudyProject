package org.robot.gui.state;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class WindowState implements Serializable {

    private final Dimension windowSize;
    private final Point location;
    private final boolean isMinimized;


    public WindowState(Dimension windowSize, Point locaction, boolean isMinimized) {
        this.windowSize = windowSize;
        this.location = locaction;
        this.isMinimized = isMinimized;
    }

    public Dimension getSize() {
        return windowSize;
    }

    public Point getLocation() {
        return location;
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
