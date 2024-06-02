package JavaRush.tasks.Quest4.task3708.retrievers;

import JavaRush.tasks.Quest4.task3708.cache.LRUCache;
import JavaRush.tasks.Quest4.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    LRUCache<Long, Object> cache = new LRUCache<>(10);
    OriginalRetriever originalRetriever;

    @Override
    public Object retrieve(long id) {
        Object result = cache.find(id);
        if (result == null) {
            result = originalRetriever.retrieve(id);
            cache.set(id, result);
            return result;
        }
        System.out.println("Returning cached object!");
        return result;
    }

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
    }
}
