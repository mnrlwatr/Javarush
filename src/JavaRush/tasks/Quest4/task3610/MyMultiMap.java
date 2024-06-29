package JavaRush.tasks.Quest4.task3610;

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
        for (List<V> list : map.values()) {
            size += list.size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        List<V> existList = map.get(key);
        if (existList != null) {
            if (existList.size() < repeatCount) {
                existList.add(value);
                return existList.get(existList.size() - 2);
            } else {
                existList.remove(0);
                existList.add(value);
                return existList.get(repeatCount - 2);
            }
        } else {
            existList = new ArrayList<>();
            existList.add(value);
            map.put(key, existList);
            return null;
        }
    }

    @Override
    public V remove(Object key) {

        List<V> list = map.get(key);
        if (list != null) {
            if (list.isEmpty()) {
                map.remove(key);
            } else {
                return list.remove(0);
            }
        } else {
            return null;
        }
        return null; // доработать: что вернуть если list.isEmpty==true ?
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            list.addAll(entry.getValue());
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            for (V v : entry.getValue()) {
                if (v.equals(value)) {
                    return true;
                }
            }
        }
        return false;
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