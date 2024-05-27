package org.robot.custom;

import java.awt.*;

public class CarRobot extends DefaultRobot{
    public CarRobot(){
        super();
        this.maxVelocity = 0.22;
        this.maxAngularVelocity = 0.0022;
    }

    protected void drawRobot(Graphics2D g, int x, int y, double direction) {
        super.drawRobot(g, x, y, direction);
        int robotCenterX = round(x);
        int robotCenterY = round(y);
        g.setColor(Color.RED);
        fillOval(g, robotCenterX - 10, robotCenterY + 5, 8, 8);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX - 10, robotCenterY + 5, 8, 8);
        g.setColor(Color.RED);
        fillOval(g, robotCenterX + 12, robotCenterY + 5, 8, 8);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX + 12, robotCenterY + 5, 8, 8);
    }

}
