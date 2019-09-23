package com.yesipov;

import com.yesipov.model.Student;

import java.util.*;
/**
 b) Отношение M:M
 Создать несколько групп
 Добавить несколько студентов в каждую группу
 Добавить студента в несколько групп
 И записать в БД. Сделать запрос в БД и вывести:
 Список студентов по названию группы (в любом регистре)
 Список групп по имени студента (в любом регистре)*
* */
public class Main {
    public static void main(String[] args) {
        DataService dataService = new DataService();
        dataService.addGroup(1, "Java");
        dataService.addGroup(4, "UI/UX");
        dataService.addGroup(5, "QA");

        Student student1 = new Student("Rodion");
        student1.addGroup(dataService.getGroup(1));
        student1.addGroup(dataService.getGroup(5));
        dataService.addStudent(student1);

        Student student2 = new Student("Alex");
        student2.addGroup(dataService.getGroup(1));
        student2.addGroup(dataService.getGroup(5));
        dataService.addStudent(student2);

        Student student3 = new Student("Helen");
        student3.addGroup(dataService.getGroup(4));
        dataService.addStudent(student3);

        /////////
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("Enter a group name to output its students:");
        System.out.println("===========================================");
        String groupName = scanner.nextLine();
        System.out.println("===========================================");
        System.out.println("Students list:");
        System.out.println(dataService.getStudentsByGroup(groupName));

        System.out.println("\n\n===========================================");
        System.out.println("Enter a name of a student to output its groups:");
        System.out.println("===========================================");
        String studentName = scanner.nextLine();
        System.out.println("===========================================");
        System.out.println("Group list:");
        System.out.println(dataService.getGroupsByStudentName(studentName));

    }
}
