package javaexam.dal;

public interface CRUD<T> {
     void add(T obj);

     void remove(int id);

     void update(int id, T obj);

     T find(int id);

     T findAll(int courseId);
}
