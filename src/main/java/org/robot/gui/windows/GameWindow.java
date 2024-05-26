package org.robot.gui.windows;

import org.robot.gui.model.GameVisualizer;
import org.robot.gui.state.WindowState;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.robot.gui.model.IRobot;

public class GameWindow extends JInternalFrame
{
    private GameVisualizer m_visualizer;
    public GameWindow(IRobot robot)
    {
        super("Игровое поле", true, true, true, true);
        m_visualizer = new GameVisualizer(robot);
        initialize();
    }

    public GameWindow(WindowState windowState, IRobot robot){
        this(robot);
        this.setSize(windowState.getSize());
        try {
            this.setIcon(windowState.isMinimized());
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        this.setLocation(windowState.getLocation());
    }

    public void initialize(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public void setRobot(IRobot robot) {
        m_visualizer.setRobot(robot);
    }
}