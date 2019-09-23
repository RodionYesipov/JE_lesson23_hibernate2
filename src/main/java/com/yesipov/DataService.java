package com.yesipov;

import com.yesipov.dao.GroupDao;
import com.yesipov.dao.StudentDao;
import com.yesipov.model.Group;
import com.yesipov.model.Student;

import java.util.List;

public class DataService {
    private StudentDao studentDao = new StudentDao();
    private GroupDao groupDao = new GroupDao();

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void addGroup(int id, String groupName) {
        groupDao.addGroup(new Group(id, groupName));
    }

    public Group getGroup(int id) {
        return groupDao.getGroup(id);
    }

    public List<String> getStudentsByGroup(String groupName) {
        return studentDao.getStudentsByGroup(groupName);
    }

    public List<String> getGroupsByStudentName(String studentName) {
        return studentDao.getGroupsByStudentName(studentName);
    }

    public void close() {
        studentDao.close();
    }
}
