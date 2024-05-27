package org.robot.gui;

import org.robot.gui.model.IRobot;
import org.robot.gui.windows.MainApplicationFrame;
import org.robot.gui.state.AppState;

import java.io.*;

public class Loader {

    public static MainApplicationFrame deserializeMainFrame(){
        try (FileInputStream file = new FileInputStream("data.loader")) {
            ObjectInputStream inStream = new ObjectInputStream(file);
            return new MainApplicationFrame((AppState)inStream.readObject());
        }
        catch (IOException | ClassNotFoundException e) { return new MainApplicationFrame(); }
    }

    public static void serializeAppState(AppState appState) {
        try (FileOutputStream file = new FileOutputStream("data.loader")) {
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            outStream.writeObject(appState);
        }
        catch (IOException e){ e.printStackTrace(System.out); }
    }

    public static void serializeRobot(IRobot iRobot) {
        String className = iRobot.getClass().getName();
        try (FileOutputStream file = new FileOutputStream("last_used_robot_class.txt");
             ObjectOutputStream outStream = new ObjectOutputStream(file)) {
            outStream.writeObject(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String deserializeRobot() {
        try (FileInputStream file = new FileInputStream("last_used_robot_class.txt");
             ObjectInputStream inStream = new ObjectInputStream(file)) {
            return (String) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}