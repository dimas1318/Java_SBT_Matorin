package ru.sbt.mipt;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<E> implements CountMap<E> {
    private final Map<E, Integer> map;

    public CountMapImpl() {
        this.map = new HashMap<>();
    }

    @Override
    public void add(E o) {
        if (map.containsKey(o)) {
            map.put(o, map.get(o) + 1);
        } else {
            map.put(o, 1);
        }
    }

    @Override
    public int getCount(E o) {
        if (map.containsKey(o)) {
            return map.get(o);
        } else {
            return 0;
        }
    }

    @Override
    public int remove(E o) {
        int count = 0;
        if (map.containsKey(o)) {
            count = map.get(o);
        }

        if (count == 0) {
            return 0;
        } else if (count == 1) {
            map.remove(o);
        } else {
            map.put(o, count - 1);
        }

        return count;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends E> source) {
        for (E o : source.toMap().keySet()) {
            map.put(o, map.get(o) + (Integer) source.toMap().get(o));
        }
    }

    @Override
    public Map<E, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<? super E, Integer> destination) {
        destination.putAll(map);
    }
}
