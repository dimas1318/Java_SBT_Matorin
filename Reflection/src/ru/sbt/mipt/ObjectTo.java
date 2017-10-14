package ru.sbt.mipt;

public class ObjectTo {
    private Integer age;
    private String name;
    private String password;
    private Object balance;
    private String weight;
    private Integer friendsNumber;
    private Boolean gender;
    private Boolean isWalking;

    public ObjectTo(Integer age, String name, String password, Object balance, String weight,
                    Integer friendsNumber, Boolean gender, Boolean isWalking) {
        this.age = age;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.weight = weight;
        this.friendsNumber = friendsNumber;
        this.gender = gender;
        this.isWalking = isWalking;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = balance;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getFriendsNumber() {
        return friendsNumber;
    }

    public void setFriendsNumber(Integer friendsNumber) {
        this.friendsNumber = friendsNumber;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getIsWalking() {
        return isWalking;
    }

    public void setIsWalking(Boolean walking) {
        isWalking = walking;
    }

    @Override
    public String toString() {
        try {
            return this.getClass().getDeclaredField("age").getType() + " age: " + age + "\n" +
                   this.getClass().getDeclaredField("name").getType() + " name: " + name + "\n" +
                   this.getClass().getDeclaredField("password").getType() + " password: " + password + "\n" +
                   this.getClass().getDeclaredField("balance").getType() + " balance: " + balance + "\n" +
                   this.getClass().getDeclaredField("weight").getType() + " weight: " + weight + "\n" +
                   this.getClass().getDeclaredField("friendsNumber").getType() + " friendsNumber: " + friendsNumber + "\n" +
                   this.getClass().getDeclaredField("gender").getType() + " gender: " + gender + "\n" +
                   this.getClass().getDeclaredField("isWalking").getType() + " isWalking: " + isWalking;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
