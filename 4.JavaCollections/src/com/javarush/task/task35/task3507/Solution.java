package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        /**
         * Создаем загрузчик модулей.
         */
//        try {
//            pathToAnimals = URLDecoder.decode(pathToAnimals, "UTF-8").substring(1);
//        } catch (UnsupportedEncodingException ignored) {
//        }
        MyLoader loader = new MyLoader(pathToAnimals, ClassLoader.getSystemClassLoader());

        File dir = new File(pathToAnimals);
        String[] files = dir.list();

        for (String file : files) {
            try {
                String fileName = file.split(".class")[0];
                Class<?> clazz = loader.loadClass(fileName);
                Animal animal = (Animal) clazz.newInstance();
                result.add(animal);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException ignored) {
            }
        }
        return result;
    }
}

class MyLoader extends ClassLoader {

    /**
     * Путь до директории с модулями.
     */
    private String pathtobin;

    public MyLoader(String pathtobin, ClassLoader parent) {
        super(parent);
        this.pathtobin = pathtobin;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            /**
             * Получем байт-код из файла и загружаем класс в рантайм
             */
            byte[] b = Files.readAllBytes(Paths.get(pathtobin + "/" + className + ".class"));
            return defineClass(null, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }

    }

}