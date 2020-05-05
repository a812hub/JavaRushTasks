package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("D:/3.txt", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            User user1 = new User();
            user1.setFirstName("Mike");
            user1.setLastName("Johnson");
            user1.setBirthDate(new Date());
            user1.setMale(true);
            user1.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user1);
            User user2 = new User();
            user2.setFirstName("Jane");
            user2.setLastName("Osborn");
            user2.setBirthDate(new Date(1573571268644L));
            user2.setMale(false);
            user2.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user2);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            System.out.println(loadedObject.equals(javaRush));
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            outputStream.write(users.size());
            for (int i = 0; i < users.size(); i++) {
                int userFirstNameLength = users.get(i).getFirstName().length();
                outputStream.write(userFirstNameLength);
                outputStream.write(users.get(i).getFirstName().getBytes());
                int userLastNameLength = users.get(i).getLastName().length();
                outputStream.write(userLastNameLength);
                outputStream.write(users.get(i).getLastName().getBytes());
                int userBirthDateLength = String.valueOf(users.get(i).getBirthDate().getTime()).length();
                outputStream.write(userBirthDateLength);
                outputStream.write(String.valueOf(users.get(i).getBirthDate().getTime()).getBytes());
                int userIsMale = users.get(i).isMale() ? 1 : 0;
                outputStream.write(userIsMale);
                int userCountryLength = users.get(i).getCountry().toString().length();
                outputStream.write(userCountryLength);
                outputStream.write(users.get(i).getCountry().toString().getBytes());
            }
        }

        public void load(InputStream inputStream) throws Exception {
            int usersCount = inputStream.read();
            for (int i = 0; i < usersCount; i++) {
                User user = new User();
                int firstNameLength = inputStream.read();
                byte[] byteFirstName = new byte[firstNameLength];
                inputStream.read(byteFirstName);
                String firstName = new String(byteFirstName);
                int lastNameLength = inputStream.read();
                byte[] byteLastName = new byte[lastNameLength];
                inputStream.read(byteLastName);
                String lastName = new String(byteLastName);
                int birthDateLength = inputStream.read();
                byte[] byteBirthDate = new byte[birthDateLength];
                inputStream.read(byteBirthDate);
                Date birthDate = new Date(Long.parseLong(new String(byteBirthDate)));
                boolean isMale = inputStream.read() == 1;
                int countryLength = inputStream.read();
                byte[] byteCountry = new byte[countryLength];
                inputStream.read(byteCountry);
                User.Country country = User.Country.valueOf(new String(byteCountry));
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setBirthDate(birthDate);
                user.setMale(isMale);
                user.setCountry(country);
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
