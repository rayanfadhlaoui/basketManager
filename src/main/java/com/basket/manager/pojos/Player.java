package com.basket.manager.pojos;

public class Player {
    private String lastName;
    private String firstName;
    private int age;
    private Integer height;
    private OffensiveSkills offensiveSkills;
    private ReboundingSkills reboundingSkills;
    private PhysicalSkills physicalSkills;
    private Stats stats;
    private Integer id;
    private TechnicalSkills technicalSkills;

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

    public ReboundingSkills getReboundingSkills() {
        return reboundingSkills;
    }

    public PhysicalSkills getPhysicalSkills() {
        return physicalSkills;
    }

    public void setReboundingSkills(ReboundingSkills reboundingSkills) {
        this.reboundingSkills = reboundingSkills;
    }

    public void setPhysicalSkills(PhysicalSkills physicalSkills) {
        this.physicalSkills = physicalSkills;
    }

    public TechnicalSkills getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(TechnicalSkills technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
