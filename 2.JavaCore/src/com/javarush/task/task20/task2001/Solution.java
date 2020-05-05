package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("D:/1.bin", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            System.out.println(ivanov.equals(somePerson));

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            if (name != null) {
                outputStream.write(name.length());
                outputStream.write(name.getBytes());
                outputStream.write(assets.size());
                for (Asset asset : assets) {
                    outputStream.write(asset.getName().length());
                    outputStream.write(asset.getName().getBytes());
                    outputStream.write(Double.toString(asset.getPrice()).length());
                    outputStream.write(Double.toString(asset.getPrice()).getBytes());
                }
            } else outputStream.write(0);
        }

        public void load(InputStream inputStream) throws Exception {
            int nameLength = inputStream.read();
            if (nameLength != 0) {
                byte[] nameBytes = new byte[nameLength];
                inputStream.read(nameBytes);
                name = new String(nameBytes);
                int assetsSize = inputStream.read();
                for (int i = 0; i < assetsSize; i++) {
                    int assetNameLength = inputStream.read();
                    byte[] assetNameBytes = new byte[assetNameLength];
                    inputStream.read(assetNameBytes);
                    String assetName = new String(assetNameBytes);
                    int assetPriceLength = inputStream.read();
                    byte[] assetPriceBytes = new byte[assetPriceLength];
                    inputStream.read(assetPriceBytes);
                    double assetPrice = Double.parseDouble(new String(assetPriceBytes));
                    assets.add(new Asset(assetName, assetPrice));
                }
            }
        }
    }
}
