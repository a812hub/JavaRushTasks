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
//        int size = 0;
//        for (List<V> valueList : map.values()) {
//            size += valueList.size();
//        }
//        return size;
        return values().size();
    }

    @Override
    public V put(K key, V value) {
        List<V> valueList = map.get(key);
        if (valueList != null) {
            if (valueList.size() >= repeatCount) {
                valueList.remove(0);
            }
            valueList.add(value);
            return valueList.get(valueList.size() - 2);
        } else {
            valueList = new ArrayList<>();
            valueList.add(value);
            map.put(key, valueList);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        if (map.containsKey(key)) {
            List<V> value = map.get(key);
            if (value.size() <= 1) {
                map.remove(key);
            }
            return value.remove(0);
        } else
            return null;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        Collection<V> values  = new ArrayList<>();
        for (List<V> list : map.values()) {
            values.addAll(list);
        }
        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
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