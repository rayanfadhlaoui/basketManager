package com.basket.manager.pojos;

public class Player {
    private String lastName;
    private String firstName;
    private int age;
    private Integer height;
    private OffensiveSkills offensiveSkills;
    private Stats stats;

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

    public void setOffensiveSkills(OffensiveSkills offensiveSkills) {
        this.offensiveSkills = offensiveSkills;
    }

    public OffensiveSkills getOffensiveSkills() {
        return offensiveSkills;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
