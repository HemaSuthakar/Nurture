package com.training.employee.model;

public class Skill {

    private int skillId;
    private String name;

    public Skill() {
    }

    public Skill(int skillId, String name) {
        this.skillId = skillId;
        this.name = name;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
