package parshin_dmitry_sbt;

public class Main {
    public static void main(String[] args) {
        Person one = new Person(true, "John");
        Person two = new Person(false, "Margo");
        Person three = new Person(false, "Julia");
        Person four = new Person(true, "Robert");

        System.out.println(one.marry(two));
        System.out.println(one.marry(three));
        System.out.println(two.marry(three));
        System.out.println(one.marry(three));
        System.out.println(four.marry(one));
    }
}
