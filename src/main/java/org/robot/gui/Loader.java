package org.robot.gui;

import org.robot.gui.model.IRobot;
import org.robot.gui.windows.MainApplicationFrame;
import org.robot.gui.state.AppState;

import java.io.*;
import java.util.Locale;

public class Loader {

    private static final String DATA_FILE = "data.loader";
    private static final String LAST_ROBOT_FILE = "last_used_robot_class.txt";
    private static final String LANGUAGE_FILE = "language.txt";


    public static MainApplicationFrame deserializeMainFrame() {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return new MainApplicationFrame((AppState) inStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            return new MainApplicationFrame();
        }
    }


    public static void serializeAppState(AppState appState) {
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            outStream.writeObject(appState);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    public static void serializeRobot(IRobot iRobot) {
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(LAST_ROBOT_FILE))) {
            outStream.writeObject(iRobot.getClass().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String deserializeRobot() {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(LAST_ROBOT_FILE))) {
            return (String) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }


    public static void serializeLanguage(Locale locale) {
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(LANGUAGE_FILE))) {
            outStream.writeObject(locale);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    public static Locale deserializeLanguage() {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(LANGUAGE_FILE))) {
            return (Locale) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Locale("ru");
        }
    }
}
