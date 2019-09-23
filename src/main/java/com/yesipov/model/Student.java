package com.yesipov.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @Expose
    private String studentName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_group",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    private Set<Group> groups = new HashSet<>();


    public Student() {
    }

    public Student(int id, String studentName, Set<Group> groupSet) {
        this.id = id;
        this.studentName = studentName;
        this.groups = groupSet;
    }

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public void addGroup(Group group) {
        groups.add(group);
        group.getStudents().add(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return studentName;
    }

    public Set<Group> getGroupSet() {
        return this.groups;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String studentName) {
        this.studentName = studentName;
    }

    public void setGroups(Set<Group> groupSet) {
        this.groups = groupSet;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        return gson.toJson(this);
    }
}
