package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    private int size;

    public CustomTree() {
        this.root = new Entry<>("root");
        size = 0;
    }

    public String getParent(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.leftChild != null) {
                if (s.equals(entry.leftChild.elementName)) {
                    return entry.elementName;
                } else {
                    queue.offer(entry.leftChild);
                }
            }
            if (entry.rightChild != null) {
                if (s.equals(entry.rightChild.elementName)) {
                    return entry.elementName;
                } else {
                    queue.offer(entry.rightChild);
                }
            }
        }
        return null;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(String s) {
        boolean result = add0(s);
        if (!result) {
            refresh();
            result = add0(s);
        }
        return result;
    }

    private boolean add0(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.isAvailableToAddChildren()) {
                if (entry.availableToAddLeftChildren) {
                    entry.leftChild = new Entry<>(s);
                    entry.leftChild.parent = entry;
                    entry.availableToAddLeftChildren = false;
                } else {
                    entry.rightChild = new Entry<>(s);
                    entry.rightChild.parent = entry;
                    entry.availableToAddRightChildren = false;
                }
                size++;
                return true;
            } else {
                if (entry.leftChild != null) queue.offer(entry.leftChild);
                if (entry.rightChild != null) queue.offer(entry.rightChild);
            }
        }
        return false;
    }

    private void refresh() {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.leftChild == null && entry.rightChild == null && !entry.isAvailableToAddChildren()) {
                entry.availableToAddLeftChildren = true;
                entry.availableToAddRightChildren = true;
            } else {
                queue.offer(entry.leftChild);
                queue.offer(entry.rightChild);
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.leftChild != null) {
                if (o.equals(entry.leftChild.elementName)) {
                    reSize(entry.leftChild);
                    entry.leftChild = null;
                    return true;
                } else {
                    queue.offer(entry.leftChild);
                }
            }
            if (entry.rightChild != null) {
                if (o.equals(entry.rightChild.elementName)) {
                    reSize(entry.rightChild);
                    entry.rightChild = null;
                    return true;
                } else {
                    queue.offer(entry.rightChild);
                }
            }
        }
        return false;
    }

    private void reSize(Entry<String> entry) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(entry);
        while (!queue.isEmpty()) {
            Entry<String> entry0 = queue.poll();
            size--;
            if (entry0.leftChild != null) {
                queue.offer(entry0.leftChild);
            }
            if (entry0.rightChild != null) {
                queue.offer(entry0.rightChild);
            }
        }
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

    }


}
