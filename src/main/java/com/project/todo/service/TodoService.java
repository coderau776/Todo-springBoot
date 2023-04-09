package com.project.todo.service;

import com.project.todo.dao.TodoRepo;
import com.project.todo.dao.UserRepo;
import com.project.todo.entity.Todo;
import com.project.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Todo> fetchAll(){
        return todoRepo.findAll();
    }
    public Todo findById(int id){
        return todoRepo.findById(id).get();
    }
    public Todo save(Todo todo){
        return todoRepo.save(todo);
    }
    public void delete(int id){
         todoRepo.deleteById(id);
    }
    public User save(User user){
        return userRepo.save(user);
    }
    public List<Todo> getTodos(int id){
        return todoRepo.findAllByUserId(id);
    }
    public User getUser(String name,String email){
        return userRepo.findByNameAndEmail(name,email);
    }

}
