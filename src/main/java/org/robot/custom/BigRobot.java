package org.robot.custom;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class BigRobot extends DefaultRobot{
    public BigRobot(){
        super();
        this.maxVelocity = 0.05;
        this.maxAngularVelocity = 0.0005;
        this.currentPosition = new Point2D.Double(500, 500);
        this.direction = 3;
    }

    protected void drawRobot(Graphics2D g, int x, int y, double direction) {
        int robotCenterX = round(x);
        int robotCenterY = round(y);
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.RED);
        fillOval(g, robotCenterX, robotCenterY, 60, 20);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 60, 20);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX + 20, robotCenterY, 10, 10);
    }
}
