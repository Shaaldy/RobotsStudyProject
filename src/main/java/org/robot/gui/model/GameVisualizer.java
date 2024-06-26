package org.robot.gui.model;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GameVisualizer extends JPanel implements Observer {
    private final Timer m_timer = initTimer();

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    private Robot robot;
    private volatile Point2D target = new Point2D.Double(150, 150);

    private volatile double m_robotPositionX = 0;
    private volatile double m_robotPositionY = 0;
    private volatile double m_robotDirection = 0;

    public GameVisualizer(Robot robot) {
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() { onRedrawEvent(); }
        }, 0, 50);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() { robot.update(target, GameVisualizer.this.getSize()); }
        }, 0, 10);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setTargetPosition(e.getPoint()); repaint();
            }
        });
        setDoubleBuffered(true);
        this.robot = robot; robot.addObserver(this);
    }

    protected void setTargetPosition(Point2D p) {
        target = p;
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.equals(robot)) {
            if (arg.equals("robot moved")) {
                m_robotDirection = robot.getDirection();
                m_robotPositionX = robot.getCurrentPosition().getX();
                m_robotPositionY = robot.getCurrentPosition().getY();
            }
        }
    }

    private static int round(double value) {
        return (int)(value + 0.5);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawRobot(g2d, round(m_robotPositionX), round(m_robotPositionY), m_robotDirection);
        drawTarget(g2d, round(target.getX()), round(target.getY()));
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawRobot(Graphics2D g, int x, int y, double direction) {
        int robotCenterX = round(x);
        int robotCenterY = round(y);
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
    }

    private void drawTarget(Graphics2D g, int x, int y) {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }
}