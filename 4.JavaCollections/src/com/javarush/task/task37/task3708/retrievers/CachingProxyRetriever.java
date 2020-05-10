package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    final int DEFAULT_INITIAL_CAPACITY = 6;

    OriginalRetriever originalRetriever;
    LRUCache cache = new LRUCache<>(DEFAULT_INITIAL_CAPACITY);

    public CachingProxyRetriever(Storage storage) {
        this.originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object result = cache.find(id);
        if (result == null) {
            result = originalRetriever.retrieve(id);
            cache.set(id, result);
        }
        return result;
    }
}
