package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        double capacity = Math.max(16, collection.size() / 0.75);
        map = new HashMap<>((int) Math.ceil(capacity));
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == null;
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public int size() {
        return map.size();
    }


    @Override
    public Object clone() {
        try {
            AmigoSet<E> copy = (AmigoSet<E>) super.clone();
            copy.map = (HashMap<E, Object>) this.map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.defaultWriteObject();
            oos.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            oos.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
            oos.writeInt(map.size());
            for (E e : map.keySet()) {
                oos.writeObject(e);
            }
        } catch (IOException ignored) {
        }
    }

    private void readObject(ObjectInputStream ois) {
        try {
            ois.defaultReadObject();
            int capacity = (int) ois.readObject();
            float loadFactor = (float) ois.readObject();
            map = new HashMap<>(capacity, loadFactor);
            int size = ois.readInt();
            for (int i = 0; i < size; i++) {
                map.put((E) ois.readObject(), PRESENT);
            }
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

}
