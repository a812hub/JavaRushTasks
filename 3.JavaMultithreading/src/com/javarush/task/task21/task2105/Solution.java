package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Solution))
            return false;
        Solution n = (Solution) o;
        if (!Objects.equals(this.first, n.first)) return false;
        return Objects.equals(n.last, this.last);
    }

    @Override
    public int hashCode() {
        return (this.first == null ? 0 : this.first.hashCode()) * 31 + (this.last == null ? 0 :this.last.hashCode());
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
