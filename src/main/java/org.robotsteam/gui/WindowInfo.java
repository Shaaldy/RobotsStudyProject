package org.robotsteam.gui;

import java.awt.*;

public class WindowInfo {
    private Point location;
    private Dimension size;
    private boolean isMaximized;

    public WindowInfo(Point location, Dimension size, boolean isMaximized){
        this.location = location;
        this.size = size;
        this.isMaximized = isMaximized;
    }

    public Point getLocation() {
        return location;
    }
    public Dimension getSize(){
        return size;
    }
    public boolean IsMaximized(){
        return  isMaximized;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setMaximized(boolean maximized) {
        isMaximized = maximized;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }
}
