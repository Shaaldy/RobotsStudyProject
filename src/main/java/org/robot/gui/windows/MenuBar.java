package org.robot.gui.windows;

import org.robot.gui.LocalizationManager;
import org.robot.log.Logger;

import javax.swing.*;
import java.awt.event.*;
import java.util.Locale;

public class MenuBar extends JMenuBar {

    private final MainApplicationFrame parent;

    public MenuBar(MainApplicationFrame parent) {
        this.parent = parent;
        this.add(createExitMenu());
        this.add(createLookAndFeelMenu());
        this.add(createLanguageMenu());
        this.add(createTestMenu());
    }

    private JMenu createLanguageMenu() {
        JMenu menu = createJMenu(LocalizationManager.getKey("menu.language"), KeyEvent.VK_L, "Выбор языка");

        JMenuItem englishItem = createJMenuItem(LocalizationManager.getKey("english"), null, e -> {
            LocalizationManager.setLocale(new Locale("en"));
            parent.updateText();
        });

        JMenuItem russianItem = createJMenuItem(LocalizationManager.getKey("russian"), null, e -> {
            LocalizationManager.setLocale(new Locale("ru"));
            parent.updateText();
        });

        menu.add(englishItem);
        menu.add(russianItem);

        return menu;
    }

    private JMenu createExitMenu() {
        JMenu menu = createJMenu(LocalizationManager.getKey("menu.file"), KeyEvent.VK_F, "Меню файла");
        JMenuItem exitMenuItem = createJMenuItem(LocalizationManager.getKey("menu.exit"), KeyEvent.VK_X, e -> parent.confirmWindowClose());
        menu.add(exitMenuItem);
        return menu;
    }

    private JMenu createLookAndFeelMenu() {
        JMenu menu = createJMenu(LocalizationManager.getKey("menu.display"), KeyEvent.VK_V, "Управление режимом отображения приложения");

        menu.add(createJMenuItem(LocalizationManager.getKey("menu.system.d"), KeyEvent.VK_S, e -> setLookAndFeel(UIManager.getSystemLookAndFeelClassName())));
        menu.add(createJMenuItem(LocalizationManager.getKey("menu.system.u"), KeyEvent.VK_U, e -> setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())));

        return menu;
    }

    private JMenu createTestMenu() {
        JMenu menu = createJMenu(LocalizationManager.getKey("menu.test"), KeyEvent.VK_T, "Тестовые команды");
        menu.add(createJMenuItem(LocalizationManager.getKey("menu.log"), KeyEvent.VK_S, e -> Logger.debug(LocalizationManager.getKey("log.str"))));
        return menu;
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }

    private JMenu createJMenu(String label, Integer mnemonic, String description) {
        JMenu menu = new JMenu(label);
        if (mnemonic != null) {
            menu.setMnemonic(mnemonic);
        }
        menu.getAccessibleContext().setAccessibleDescription(description);
        return menu;
    }

    private JMenuItem createJMenuItem(String label, Integer mnemonic, ActionListener callback) {
        JMenuItem menuItem = new JMenuItem(label);
        if (mnemonic != null) {
            menuItem.setMnemonic(mnemonic);
        }
        menuItem.addActionListener(callback);
        return menuItem;
    }
}
