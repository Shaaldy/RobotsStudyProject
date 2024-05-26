package org.robot.gui.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;

public abstract class IRobot extends Observable {
    public abstract void update(Point2D target, Dimension bounds);

    public abstract double getDirection();

    public abstract Point2D getCurrentPosition();

    public abstract String getInfo();

    public abstract void setPosition(Point2D newPosition);

    public abstract void setDirection(Double newDirection);
}