package org.robot.custom;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class NewRobot extends DefaultRobot{
    public NewRobot(){
        super();
    }

    protected void drawRobot(Graphics2D g, int x, int y, double direction) {
        int robotCenterX = round(x);
        int robotCenterY = round(y);
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.MAGENTA);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.WHITE);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
    }
}
