package org.robot.gui.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.beans.PropertyVetoException;
import java.io.Console;
import java.io.File;
import java.io.Serializable;
import org.robot.gui.model.IRobot;
import org.robot.gui.model.GameVisualizer;
import org.robot.gui.model.RobotLoader;
import org.robot.gui.state.WindowState;


public class RobotLoaderWindow extends JInternalFrame implements Serializable {
    private JPanel panel;
    private JButton loadButton;
    private JFileChooser fileChooser;
    private IRobot currentRobot;
    private GameVisualizer visualizer;
    private GameWindow gameWindow;
    public RobotLoaderWindow(GameWindow gameWindow, IRobot iRobot){
        super("Роботы", true, true, true, true);
        setSize(300, 300);
        JPanel panel = new JPanel(new BorderLayout());
        this.currentRobot = iRobot;
        this.gameWindow = gameWindow;
        loadButton = new JButton("Загрузить робота");
        panel.add(loadButton, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JAR files", "jar"));

        loadButton.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try {
                    loadRobot(file);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        getContentPane().add(panel);
    }

    public RobotLoaderWindow(GameWindow gameWindow, IRobot IRobot, WindowState windowState){
        this(gameWindow, IRobot);
        this.setSize(windowState.getSize());
        try {
            this.setIcon(windowState.isMinimized());
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        this.setLocation(windowState.getLocation());
    }

    private void loadRobot(File file){
        String defaultClassName = "org.robot.custom.DefaultRobot";
        String robotClassName = JOptionPane.showInputDialog(this, "Введите имя класса робота (с пакетами): ", defaultClassName);

        try{
            IRobot robot = (IRobot) RobotLoader.loadRobot(file.getAbsolutePath(), robotClassName);

            if (currentRobot != null){
                this.gameWindow.setRobot(robot);
            }
            currentRobot = robot;
        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load robot: " + e.getMessage());
        }
    }

}
