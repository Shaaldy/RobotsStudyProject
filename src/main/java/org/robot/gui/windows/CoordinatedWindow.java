package org.robot.gui.windows;

import org.robot.gui.model.IRobot;
import org.robot.gui.state.WindowState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class CoordinatedWindow extends JInternalFrame implements Observer, Serializable {

    private IRobot robot;
    private JTextArea textArea;
    public CoordinatedWindow(IRobot robot){
        super("Координаты робота", true, true, true, true);

        JPanel jPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(50, 50));
        jPanel.add(textArea, BorderLayout.CENTER);
        getContentPane().add(jPanel);
        pack();

        this.robot = robot;
        robot.addObserver(this);
    }

    public CoordinatedWindow(WindowState state, IRobot robot){
        this(robot);
        this.setSize(state.getSize());
        this.setLocation(state.getLocation());
        try {
            this.setIcon(state.isMinimized());
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        if (o.equals(robot)) {
            if (arg.equals("robot moved"))
                onRobotMoved();
        }
    }
    private void onRobotMoved() {
        textArea.setText(robot.getInfo());
    }
}
