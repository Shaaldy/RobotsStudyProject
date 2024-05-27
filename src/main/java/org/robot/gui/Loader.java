package org.robot.gui;

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
}