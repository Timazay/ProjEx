package javaexam.controller;

import by.itstep.timazay.javaexam.dal.StudentDAO;
import by.itstep.timazay.javaexam.model.entity.Course;
import by.itstep.timazay.javaexam.model.entity.Student;
import by.itstep.timazay.javaexam.model.logic.StudentLogic;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Bob", 22);
        Student student2 = new Student("Alex", 20);
        Student student3 = new Student("Elvis", 19);


        StudentDAO studentDAO = new StudentDAO();
          Course course = (Course) studentDAO.findAll(1);
        System.out.println(course);
        int maxStudents = StudentLogic.calcTotalStudents(course);

        System.out.println(maxStudents);
    }
}
