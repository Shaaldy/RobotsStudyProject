package org.robot.custom;

import java.awt.*;
import java.awt.geom.Point2D;

public class CarRobot extends DefaultRobot{
    public CarRobot(){
        super();
        this.maxVelocity = 0.4;
        this.maxAngularVelocity = 0.004;
    }

    public void update(Point2D target, Dimension bounds){
        if (currentPosition.distance(target) < 2) return;

        double angularVelocity = countAngularVelocity(target);
        moveRobot(maxVelocity, angularVelocity, 10, bounds);

        setChanged();
        notifyObservers("robot moved");
        clearChanged();
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
