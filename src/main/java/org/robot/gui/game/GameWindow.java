package org.robot.gui.game;

import org.robot.gui.game.GameVisualizer;
import org.robot.gui.state.WindowState;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame
{
    private final GameVisualizer m_visualizer;
    public GameWindow()
    {
        super("Игровое поле", true, true, true, true);
        m_visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public GameWindow(WindowState windowState){
        this();
        this.setSize(windowState.getSize());
        try {
            this.setIcon(windowState.isMinimized());
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        this.setLocation(windowState.getLocaction());
    }
}