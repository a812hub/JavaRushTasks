package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException ignored) {
        }
    }

    public long getFileSize() {
        long size = 0L;
        try {
            size = Files.size(path);
        } catch (IOException ignored) {
        }
        return size;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path));) {
            objectOutputStream.writeObject(entry);
        } catch (IOException ignored) {
        }
    }

    public Entry getEntry() {
        Entry entry = null;
        if (getFileSize() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
                entry = (Entry) ois.readObject();
            } catch (IOException | ClassNotFoundException ignored) {
            }
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }
}
