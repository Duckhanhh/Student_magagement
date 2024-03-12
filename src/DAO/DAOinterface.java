package DAO;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DAOinterface<T>{
    public int insert(T t);
    public int delete(T t);
    public Map<T, List<String>> selectAll();
    public T selectByID(T t);
}
