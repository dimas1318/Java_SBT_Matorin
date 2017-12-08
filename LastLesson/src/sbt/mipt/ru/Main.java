package sbt.mipt.ru;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alex", 10));
        people.add(new Person("Alex", 29));
        people.add(new Person("Alex", 20));
        people.add(new Person("lolya", 40));
        people.add(new Person("Sonya", 10));
        people.add(new Person("Bob", 10));
        people.add(new Person("Alice", 10));

        Map m = Streams.of(people)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(((Person)p).getName(), ((Person)p).getAge() + 30))
                .toMap(p -> ((Person)p).getName(), p -> p);

        System.out.println(m);

        for (Object key : m.keySet()) {
            System.out.println(key + " " + ((Person) m.get(key)).getName() + " " + ((Person) m.get(key)).getAge());
            System.out.println();
        }
    }

//    private static Map<String, List<Person>> getAges2(List<Person> people) {
//        return people.stream()
//                .filter(person -> person.getAge() > 18)
//                .collect(groupingBy(p -> p.getName()));
//        Collectors.toMap()
//
//    }
}
