package org.robot.gui.windows;

import org.robot.log.Logger;

import javax.swing.*;
import java.awt.event.*;


public class MenuBar extends JMenuBar {

    private final MainApplicationFrame parent;
    public MenuBar(MainApplicationFrame parent) {
        this.parent = parent;
        this.add(createExitMenu());
        this.add(createLookAndFeelMenu());
        this.add(createTestMenu());

    }

    private JMenu createExitMenu() {
        JMenu menu = createJMenu("Выход", KeyEvent.VK_E, "Выйти из приложения");
        JMenuItem exitMenuItem = createJMenuItem("Выйти", KeyEvent.VK_X, e -> parent.confirmWindowClose());
        menu.add(exitMenuItem);
        return menu;
    }

    private JMenu createLookAndFeelMenu() {
        JMenu menu = createJMenu("Режим отображения", KeyEvent.VK_V, "Управление режимом отображения приложения");

        menu.add(createJMenuItem("Системная схема", KeyEvent.VK_S, e -> setLookAndFeel(UIManager.getSystemLookAndFeelClassName())));

        menu.add(createJMenuItem("Универсальная схема", KeyEvent.VK_S, e -> setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())));

        return menu;
    }

    private JMenu createTestMenu(){
        JMenu menu = createJMenu("Тест", KeyEvent.VK_T, "Тестовые команды");
        menu.add(createJMenuItem("Сообщение в лог", KeyEvent.VK_S, e ->  Logger.debug("Новая строка")));
        return menu;
    }
    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }
    private JMenu createJMenu(String label, int mnemonic, String description ) {
        JMenu menu = new JMenu(label);
        menu.setMnemonic(mnemonic);
        menu.getAccessibleContext().setAccessibleDescription(description);

        return menu;
    }

    private JMenuItem createJMenuItem(String label, int mnemonic, ActionListener callback) {
        JMenuItem menuItem = new JMenuItem(label, mnemonic);
        menuItem.addActionListener(callback);

        return menuItem;
    }

}