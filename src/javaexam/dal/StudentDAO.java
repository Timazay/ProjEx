package javaexam.dal;

import by.itstep.timazay.javaexam.model.entity.Course;
import by.itstep.timazay.javaexam.model.entity.Entity;
import by.itstep.timazay.javaexam.model.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO extends AbstractDAO implements CRUD<Entity>, StudentInterface {
    public static final String GET_ALL_STUDENTS_BY_NAME = "SELECT s.* FROM students s " +
            "JOIN courses_has_students chs ON s.id_student = chs.students_id_student " +
            "WHERE chs.courses_id_course = ? " +
            "ORDER BY s.name";
    public static final String GET_STUDENT = "SELECT * FROM students WHERE id_student = ?";
    public static final String DELETE_STUDENTS = "DELETE FROM students where id_student = ?";
    public static final String ADD_STUDENTS = "INSERT INTO students(name,age) VALUES(?, ?)";
    public static final String GET_MAX_COST = "SELECT MAX(course_cost) AS max_cost FROM courses";
    public static final String GET_MIN_COST = "SELECT MIN(course_cost) AS min_cost FROM courses";

    public StudentDAO() {
    }

    @Override
    public void add(Entity obj) {
        Student student = (Student) obj;
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(ADD_STUDENTS);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            release();
        }
    }

    @Override
    public void remove(int id) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            release();
        }
    }

    @Override
    public void update(int id, Entity obj) {
        Student student = (Student) obj;
        remove(id);
        add(student);
    }

    @Override
    public Student find(int id) {
        Student student = null;
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(GET_STUDENT);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                student = new Student();
                student.setName(set.getString("name"));
                student.setAge(set.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            release();
        }
        return student;
    }

    @Override
    public Entity findAll(int courseId) {
        Course group = new Course();
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_STUDENTS_BY_NAME);

            statement.setInt(1, courseId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));

                group.getList().add(student);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            release();
        }
        return group;
    }


    @Override
    public double maxCourseCost() {
        try {
            connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(GET_MAX_COST);
            rs.next();
            double maxCost = rs.getDouble("max_cost");
            return maxCost;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            release();
        }
    }

    @Override
    public double minCourseCost() {
        try {
            connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(GET_MIN_COST);
            rs.next();
            double minCost = rs.getDouble("min_cost");
            return minCost;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            release();
        }
    }
}
