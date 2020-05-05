package com.javarush.task.task21.task2107;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);
            System.out.println(solution.users == clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (!(o instanceof User)) {
                return false;
            }
            if (((User) o).age != this.age) return false;
            return (Objects.equals(((User) o).name, this.name));
        }

        public int hashCode() {
            int result = age;
            if (this.name != null) {
                result = 31 * result + name.hashCode();
            }
            return result;
        }
    }

    public Object clone() throws CloneNotSupportedException {
        Solution solution1 = (Solution)super.clone();
        solution1.users = new LinkedHashMap();
        for (Map.Entry<String, User> e : this.users.entrySet()) {
            solution1.users.put(e.getKey(), (User)e.getValue().clone());
        }
        return solution1;
    }
}
