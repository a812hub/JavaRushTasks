package com.javarush.task.task36.task3606;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
//        try {
//            packageName = URLDecoder.decode(packageName, "UTF-8");
//            packageName = packageName.substring(1);
//        } catch (UnsupportedEncodingException ignored) {
//        }

        ClassLoader loader = new MyClassLoader(ClassLoader.getSystemClassLoader());
        File path = new File(packageName);
        String[] files = path.list();
        if (files != null) {
            for (String s : files) {
                if (s.endsWith(".class")) {
                    hiddenClasses.add(loader.loadClass(s.substring(0, s.length() - 6)));
                }
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        HiddenClass hiddenClassObject = null;
        for (Class<?> clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase()) && HiddenClass.class.isAssignableFrom(clazz)) {
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    hiddenClassObject = (HiddenClass) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                }
            }
        }
        return hiddenClassObject;
    }

    private class MyClassLoader extends ClassLoader {

        public MyClassLoader(ClassLoader systemClassLoader) {
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(packageName + "/" + name + ".class"));
                return defineClass(null, bytes, 0, bytes.length);
            } catch (IOException e) {
                return super.findClass(name);
            }
        }
    }
}

