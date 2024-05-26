package org.robot.gui.model;

import org.robot.custom.DefaultRobot;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class RobotLoader {
    public static DefaultRobot loadRobot(String jarPath, String robotClassName) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        File file = new File(jarPath);
        if (!file.exists() || !file.isFile()){
            throw new IOException("File not fount: " + jarPath);
        }

        if (!jarPath.endsWith(".jar")){
            throw new IOException("Not a .jar file: " + jarPath);
        }

        URL jarUrl = file.toURI().toURL();
        URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, RobotLoader.class.getClassLoader());

        Class<?> robotClass = classLoader.loadClass(robotClassName);


        if (!DefaultRobot.class.isAssignableFrom(robotClass)){
            throw new ClassNotFoundException("Class does not implements DefaultRobot interface: " + robotClassName);
        }


        Constructor<?> constructor = robotClass.getConstructor();
        return (DefaultRobot) constructor.newInstance();
    }
}
