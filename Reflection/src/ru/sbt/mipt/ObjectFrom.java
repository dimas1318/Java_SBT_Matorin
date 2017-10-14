package ru.sbt.mipt;

public class ObjectFrom {
    private Integer age;
    private Object name;
    private String surname;
    private Double balance;
    private Double height;
    private Number friendsNumber;
    private Boolean gender;
    private Boolean isMarried;

    public ObjectFrom(Integer age, Object name, String surname, Double balance, Double height,
                      Number friendsNumber, Boolean gender, Boolean isMarried) {
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.height = height;
        this.friendsNumber = friendsNumber;
        this.gender = gender;
        this.isMarried = isMarried;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Number getFriendsNumber() {
        return friendsNumber;
    }

    public void setFriendsNumber(Number friendsNumber) {
        this.friendsNumber = friendsNumber;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Boolean married) {
        isMarried = married;
    }

    @Override
    public String toString() {
        try {
            return this.getClass().getDeclaredField("age").getType() + " age: " + age + "\n" +
                   this.getClass().getDeclaredField("name").getType() + " name: " + name + "\n" +
                   this.getClass().getDeclaredField("surname").getType() +  " surname: " + surname + "\n" +
                   this.getClass().getDeclaredField("balance").getType() +  " balance: " + balance + "\n" +
                   this.getClass().getDeclaredField("height").getType() +  " height: " + height + "\n" +
                   this.getClass().getDeclaredField("friendsNumber").getType() +  " friendsNumber: " + friendsNumber + "\n" +
                   this.getClass().getDeclaredField("gender").getType() +  " gender: " + gender + "\n" +
                   this.getClass().getDeclaredField("isMarried").getType() +  " isMarried: " + isMarried;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
