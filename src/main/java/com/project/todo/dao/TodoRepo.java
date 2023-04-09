package com.project.todo.dao;

import com.project.todo.entity.Todo;
import com.project.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo,Integer> {
    public List<Todo> findAllByUserId(int id);
}
