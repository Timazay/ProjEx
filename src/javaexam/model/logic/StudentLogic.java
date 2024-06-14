package javaexam.model.logic;

import by.itstep.timazay.javaexam.model.entity.Course;
import by.itstep.timazay.javaexam.model.entity.Student;

import java.util.ArrayList;

public class StudentLogic {
    public static int calcTotalStudents(Course course) {
        int amount = 0;

        for (Student student : course.getList()) {
            amount++;
        }

        return amount;
    }

    public static double  countTotalProfit(Course course) {
        double max = course.getCost();
        ArrayList<Student> c = (ArrayList<Student>) course.getList();

        for (int i = 0; i < c.size(); i++) {
            max += course.getCost();
        }

        return max;
    }
    
}
