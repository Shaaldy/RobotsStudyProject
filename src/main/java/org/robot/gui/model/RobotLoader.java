package org.robot.gui.model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class RobotLoader {
    public static IRobot loadRobot(String jarPath, String robotClassName) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        File file = new File(jarPath);
        if (!file.exists() || !file.isFile()){
            throw new IOException("File not found: " + jarPath);
        }

        if (!jarPath.endsWith(".jar")){
            throw new IOException("Not a .jar file: " + jarPath);
        }

        URL jarUrl = file.toURI().toURL();
        URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, RobotLoader.class.getClassLoader());

        Class<?> robotClass = classLoader.loadClass(robotClassName);

        if (!IRobot.class.isAssignableFrom(robotClass)){
            throw new ClassNotFoundException("Class does not implement IRobot interface: " + robotClassName);
        }

        Constructor<?> constructor = robotClass.getConstructor();
        return (IRobot) constructor.newInstance();
    }
}
