package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize = DEFAULT_INITIAL_CAPACITY * DEFAULT_BUCKET_SIZE_LIMIT;

    int hash(Long key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        int hash = hash(key);
        FileBucket[] tab = table;
        Entry first;
        Entry e;
        int n;
        Long k;
        if (tab != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash].getEntry()) != null) {
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
        maxBucketSize = newCapacity * bucketSizeLimit;
    }

    void transfer(FileBucket[] newTable) {
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j].getEntry();
            if (e != null) {
                src[j].remove();
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i].getEntry();
                    newTable[i].putEntry(e);
                    e = next;
                } while (e != null);
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = null;
        if (table[bucketIndex] != null) {
            e = table[bucketIndex].getEntry();
        } else {
            table[bucketIndex] = new FileBucket();
        }
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        if (size++ >= bucketSizeLimit)
            resize(2 * table.length);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = null;
        if (table[bucketIndex] != null) {
            e = table[bucketIndex].getEntry();
        } else {
            table[bucketIndex] = new FileBucket();
        }
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }


    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        return getKey(value) != null;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        if (table[i] != null) {
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                Long k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    e.value = value;
                    table[i].putEntry(e);
                    return;
                }
            }
        }
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                Entry e = tab[i].getEntry();
                while (e != null) {
                    if (value.equals(e.value)) {
                        return e.key;
                    }
                    e = e.next;
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        int hash = hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return null;
    }
}
