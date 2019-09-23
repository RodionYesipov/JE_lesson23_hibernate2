package com.yesipov.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Expose
    private int groupId;

    @Expose
    @Column
    private String groupName;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    public Group() {
    }

    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Group(int groupId, String groupName, Set<Student> students) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.students = students;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }


    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public Set<Student> getStudents() {
        return students;
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
