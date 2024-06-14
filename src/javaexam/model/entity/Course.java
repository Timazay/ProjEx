package javaexam.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Course extends Entity{
    private String name;
    private double cost;
    private List<Student> list;
    public Course() {
        list = new ArrayList<>();
    }

    public Course(List<Student> list) {
        this.list = list;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("List of students: ");

        for (Student s :
                list) {
            builder.append("\n").append(s);

        }
        return builder.toString();
    }
}
