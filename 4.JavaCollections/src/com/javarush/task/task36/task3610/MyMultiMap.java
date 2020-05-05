package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (List<V> value : map.values()) {
            size += value.size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            if (list.size() < repeatCount) {
                map.get(key).add(value);
            } else {
                list.remove(0);
                list.add(value);
            }
            return list.get(list.size() - 2);
        } else {
            List<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        map.containsKey(key) ? map.
        return null;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return null;
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
        //напишите тут ваш код
    }

    @Override
    public boolean containsValue(Object value) {
        return true;
        //напишите тут ваш код
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}