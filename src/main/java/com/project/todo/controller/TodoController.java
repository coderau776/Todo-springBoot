package com.project.todo.controller;

import com.project.todo.entity.Todo;
import com.project.todo.entity.User;
import com.project.todo.service.TodoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/showTodos")
    public String showTodos(Model model, @AuthenticationPrincipal OAuth2User user, HttpSession session){
        String name = user.getAttribute("name").toString();
        String email = user.getAttribute("email").toString();
        model.addAttribute("name",name);
        User loggedInUser = todoService.getUser(name,email);
        if(loggedInUser==null) {
            loggedInUser = new User(name, email);
            loggedInUser = todoService.save(loggedInUser);
        }
        session.setAttribute("uid",loggedInUser.getId());
        List<Todo> todos = todoService.getTodos(loggedInUser.getId());
        model.addAttribute("todos",todos);
        return "todos/todos.html";
    }
    @GetMapping("/editTodo")
    public String editTodo(@RequestParam(required = false) String id, Model model,HttpSession session){
        Todo todo =new Todo();
        if (id!=null)
            todo = todoService.findById(Integer.parseInt(id));
        todo.setUserId((Integer) session.getAttribute("uid"));
        model.addAttribute("todo",todo);
        return "todos/edit.html";
    }
    @GetMapping("/fetchAll")
    public List<Todo> fetchAll(){
        return todoService.fetchAll();
    }
    @GetMapping("/fetch")
    public Todo fetch(@RequestParam(value = "id") int id){
        return todoService.findById(id);
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("todo") Todo todo){
        todo.setInsertionTime(new Timestamp(System.currentTimeMillis()));
        todoService.save(todo);
        return "redirect:/todos/showTodos";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") int id){
         todoService.delete(id);
         return "redirect:/todos/showTodos";
    }
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
    }
}
