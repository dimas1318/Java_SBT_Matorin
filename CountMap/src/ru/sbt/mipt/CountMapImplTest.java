package ru.sbt.mipt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountMapImplTest {
    @Test
    void addAndGetCount() {
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        assertEquals(2, map.getCount(5));
        assertEquals(1, map.getCount(6));
        assertEquals(3, map.getCount(10));
        assertEquals(0, map.getCount(9));
        assertEquals(0, map.getCount(-3));
    }

    @Test
    void remove() {
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        assertEquals(2, map.getCount(5));

        map.remove(5);
        assertEquals(1, map.getCount(5));

        map.remove(5);
        assertEquals(0, map.getCount(5));

        map.remove(5);
        assertEquals(0, map.getCount(5));
    }

    @Test
    void size() {
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
        assertEquals(3, map.size());

        map.remove(6);
        assertEquals(2, map.size());

        map.remove(10);
        assertEquals(2, map.size());

        map.remove(10);
        assertEquals(2, map.size());

        map.remove(10);
        assertEquals(1, map.size());
    }

    @Test
    void addAll() {
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        CountMap<Integer> map2 = new CountMapImpl<>();
        map2.add(10);
        map2.add(10);
        map2.add(5);
        map2.add(6);

        map.addAll(map2);

        assertEquals(3, map.getCount(5));
        assertEquals(2, map.getCount(6));
        assertEquals(5, map.getCount(10));
        assertEquals(0, map.getCount(9));
        assertEquals(0, map.getCount(-3));
    }
}