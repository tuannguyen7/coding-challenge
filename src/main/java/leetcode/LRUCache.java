package leetcode;

import java.util.*;

public class LRUCache {

    int capacity;
    int size;
    Map<Integer, Integer> map;
    Deque<Integer> entries;

    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;
        map = new HashMap<>();
        entries = new LinkedList<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Iterator<Integer> iter  = entries.iterator();

            while (iter.hasNext()) {
                if (iter.next().equals(key)) {
                    iter.remove();
                }
            }
            entries.addLast(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Iterator<Integer> iter  = entries.iterator();

            while (iter.hasNext()) {
                if (iter.next().equals(key)) {
                    iter.remove();
                }
            }
            map.put(key, value);
            entries.addLast(key);
        } else {
            if (size < capacity) {
                map.put(key, value);
                entries.addLast(key);
                size++;
            } else {
                int lruKey = entries.removeFirst();
                entries.addLast(key);
                map.remove(lruKey);
                map.put(key, value);
            }
        }

    }
}
