package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if (n == null) return false;
        if (n == this) return true;
        if (n.getClass() != this.getClass()) return false;
        Solution solution = (Solution) n;
        if (!Objects.equals(solution.first, this.first)) return false;
        return Objects.equals(solution.last, this.last);
    }

    public int hashCode() {
        int nFirst = 0;
        int nLast = 0;
        if (this.first != null) {
            nFirst = first.hashCode();
        }
        if (this.last != null) {
            nLast = last.hashCode();
        }
        return 31 * nFirst + nLast;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
//        s.add(new Solution("Donald", "Duck"));
//        System.out.println(s.contains(new Solution("Donald", "Duck")));
        s.add(new Solution(null, "Duck"));
        System.out.println(s.contains(new Solution(null, "Duck")));
    }
}
