package ru.sbt.mipt;

public class Main {
    public static void main(String[] args) {
        ObjectFrom from = new ObjectFrom(13, "Ivan", "Sparrow", 99.9,
                12.02, 908, true, false);
        ObjectTo to = new ObjectTo(22, "David", "0000", 123.78, "80kg",
                67, false, true);

        System.out.println("ObjectFrom:");
        System.out.println(from.toString());
        System.out.println();
        System.out.println("ObjectTo (Until):");
        System.out.println(to.toString());

        BeanUtils.assign(to, from);

        System.out.println();
        System.out.println("ObjectTo (After):");
        System.out.println(to.toString());
    }
}
