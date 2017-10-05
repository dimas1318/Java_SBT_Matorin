package ru.sbt.mipt;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {
    @Test
    void range() {
        assertEquals(Arrays.asList(3, 4, 5, 6), CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6));
        assertEquals(Arrays.asList(6, 8), CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 6, 8));
    }

    @Test
    void containsAll() {
        assertEquals(true, CollectionUtils.containsAll(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 4, 5)));
        assertEquals(false, CollectionUtils.containsAll(Arrays.asList(1, 2, 3, 4, 6), Arrays.asList(1, 4, 5)));
    }

    @Test
    void containsAny() {
        assertEquals(false, CollectionUtils.containsAny(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(15, 8)));
        assertEquals(true, CollectionUtils.containsAny(Arrays.asList(1, 2, 3, 4, 6), Arrays.asList(7, 2, 5)));
    }
}