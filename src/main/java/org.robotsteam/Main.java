package org.robotsteam;

import org.robotsteam.gui.MainApplicationFrame;

import java.awt.Frame;
import java.util.Locale;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main
{
    public static void main(String[] args) {
      try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
          UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      SwingUtilities.invokeLater(() -> {
        MainApplicationFrame frame = new MainApplicationFrame();
        Locale.setDefault(new Locale("ru", "RU"));
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }}
