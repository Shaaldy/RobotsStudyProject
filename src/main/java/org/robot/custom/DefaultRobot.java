package org.robot.custom;

import org.robot.gui.model.IRobot;

import java.awt.*;
import java.awt.geom.Point2D;

public class DefaultRobot extends IRobot {

    private Point2D currentPosition;
    private volatile Point2D targetPosition = new Point2D.Double(150, 150);;
    double direction;
    double maxVelocity;
    double maxAngularVelocity;

    public DefaultRobot(int PosX, int PosY){
        super();
        this.currentPosition = new Point2D.Double(PosX, PosY);
        this.direction = 0;
        this.maxVelocity = 0.1;
        this.maxAngularVelocity = 0.001;
    }

    public DefaultRobot(){
        this(10, 10);
    }
    @Override
    public double getDirection(){
        return direction;
    }
    @Override
    public Point2D getCurrentPosition(){
        return currentPosition;
    }

    @Override
    public String getInfo(){
        return String.format("Position: (%f, %f) | Direction: %f", currentPosition.getX(), currentPosition.getY(), direction);
    }

    @Override
    public void update(Point2D target, Dimension bounds){
        if (currentPosition.distance(target) < 0.5) return;

        double angularVelocity = countAngularVelocity(target);
        moveRobot(maxVelocity, angularVelocity, 10, bounds);

        setChanged();
        notifyObservers("robot moved");
        clearChanged();
    }

    public void setPosition(Point2D newPosition){
        currentPosition = newPosition;
    }

    public void setDirection(Double newDirection){
        direction = newDirection;
    }




    public Point2D newPos(double vel, double angleVel, double t){
        double x = currentPosition.getX();
        double y = currentPosition.getY();
        double newX = x + vel / angleVel * (Math.sin(direction + angleVel * t) - Math.sin(direction));
        double newY = y - vel / angleVel * (Math.cos(direction + angleVel * t) - Math.cos(direction));

        if (!Double.isFinite(newX)) newX = x + vel * t * Math.cos(direction);
        if (!Double.isFinite(newY)) newY = y + vel * t * Math.sin(direction);

        return new Point2D.Double(newX, newY);
    }

    private double countAngularVelocity(Point2D targetPosition){
        double angularVelocity = 0;
        double angleToTarget = angleTo(currentPosition, targetPosition);
        double diff = asNormalizedRadians(angleToTarget - direction);
        if (diff < Math.PI) angularVelocity = maxAngularVelocity;
        if (diff > Math.PI) angularVelocity = -maxAngularVelocity;
        if(unreachedTarget(targetPosition, currentPosition)) angularVelocity = 0;
        return angularVelocity;
    }

    private boolean unreachedTarget(Point2D robotPosition, Point2D targetPosition){
        double dx = targetPosition.getX() - robotPosition.getX();
        double dy = targetPosition.getY() - robotPosition.getY();
        double newDx = Math.cos(direction) * dx + Math.sin(direction) * dy;
        double newDy = Math.cos(direction) * dy - Math.sin(direction) * dx;
        double deviation = maxVelocity / maxAngularVelocity;
        return !(distance(newDx, newDy, 0, deviation) > deviation) || !(distance(newDx, newDy + deviation, 0, 0) > deviation);
    }

    public static double applyLimits(double value, double min, double max)
    {
        if (value < min) return min;
        return Math.min(value, max);
    }
    private static double distance(double x1, double y1, double x2, double y2)
    {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(Point2D robotPos, Point2D targetPos)
    {
        double diffX = targetPos.getX() - robotPos.getX();
        double diffY = targetPos.getY() - robotPos.getY();

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    private static double asNormalizedRadians(double angle)
    {
        while (angle < 0)
            angle += 2*Math.PI;
        while (angle >= 2*Math.PI)
            angle -= 2*Math.PI;
        return angle;
    }

    private double applyBounds(Dimension bounds, double direction) {
        double x = currentPosition.getX();
        double y = currentPosition.getY();
        if (x < 0 || y > bounds.width) {
            return asNormalizedRadians(Math.PI - direction);
        }

        if (y < 0 || y > bounds.height) {
            return asNormalizedRadians(-direction);
        }

        return direction;
    }

    private void moveRobot(double velocity, double angularVelocity, double duration, Dimension bounds)
    {
        velocity = applyLimits(velocity, 0, maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);

        double newDirection = direction + angularVelocity * duration;

        setPosition(newPos(velocity, angularVelocity, duration));
        setDirection(applyBounds(bounds, newDirection));
    }
}
