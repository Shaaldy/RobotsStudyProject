package org.robot.custom;

import java.awt.*;
import java.awt.geom.Point2D;

public class ZigZagRobot extends DefaultRobot{
    private Point2D currentPosition;
    private Point2D targetPosition;
    private double direction;
    private double maxVelocity;
    private double maxAngularVelocity;
    private boolean moveLeft; // Флаг для определения направления движения

    public ZigZagRobot(int PosX, int PosY) {
        super();
        this.currentPosition = new Point2D.Double(PosX, PosY);
        this.direction = 0;
        this.maxVelocity = 0.1;
        this.maxAngularVelocity = 0.001;
        this.moveLeft = true; // Начинаем движение влево
    }

    public ZigZagRobot() {
        this(10, 10);
    }

    public void setPosition(Point2D newPosition) {
        currentPosition = newPosition;
    }

    public void setDirection(Double newDirection) {
        direction = newDirection;
    }

    protected double countAngularVelocity() {
        double angularVelocity = moveLeft ? -maxAngularVelocity : maxAngularVelocity;
        moveLeft = !moveLeft;
        return angularVelocity;
    }
}
