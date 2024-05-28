package org.robot.gui.windows;

import org.robot.gui.LocalizationManager;
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
    private JPanel panel;
    public GameWindow(IRobot robot)
    {
        super(LocalizationManager.getKey("title.field"), true, true, true, true);
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
        this.panel = new JPanel(new BorderLayout());
        this.panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(this.panel);
        pack();
    }

    public void setRobot(IRobot robot) {
        m_visualizer.setRobot(robot);
    }
}