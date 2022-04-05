package com.example.todo.controllers

import com.example.todo.entities.Todo
import com.example.todo.entities.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/todos")
class TodoController(val todoRepository: TodoRepository) {

    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping
    fun getTodos() = todoRepository.findAll()

    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable("todoId") todoId: Long): Optional<Todo> {
        return todoRepository.findById(todoId)
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping
    fun newTodo(@RequestBody todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable("todoId") todoId: Long, @RequestBody updatedTodo: Todo): Todo? {
        val oldTodo = todoRepository.findByIdOrNull(todoId)
        if (oldTodo == null) {
            return oldTodo
        }
        return todoRepository.save(updatedTodo)
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable("todoId") todoId: Long) {
        todoRepository.deleteById(todoId)
    }
}
