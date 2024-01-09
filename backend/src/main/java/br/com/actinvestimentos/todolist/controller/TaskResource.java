package br.com.actinvestimentos.todolist.controller;

import br.com.actinvestimentos.todolist.exceptions.IdNotFoundException;
import br.com.actinvestimentos.todolist.exceptions.InvalidNameException;
import br.com.actinvestimentos.todolist.exceptions.InvalidPriorityException;
import br.com.actinvestimentos.todolist.model.task.TaskDTO;
import br.com.actinvestimentos.todolist.service.TaskService;
import jakarta.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskResource {
    @Inject
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> listTasks() {
        var taskDTOList = taskService.listTasks();
        return new ResponseEntity<>(taskDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> insertTask(@RequestBody TaskDTO taskDTO)
            throws InvalidNameException, InvalidPriorityException {
        var taskDto = taskService.insertTask(taskDTO);
        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable ("id") Long id, @RequestBody TaskDTO taskDTO)
            throws InvalidNameException, InvalidPriorityException, IdNotFoundException {
        var taskDto = taskService.updateTask(id, taskDTO);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @PutMapping("/working/{id}")
    public ResponseEntity<TaskDTO> workingTask(@PathVariable ("id") Long id) throws IdNotFoundException {
        var taskDto = taskService.workingTask(id);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @PutMapping("/completed/{id}")
    public ResponseEntity<TaskDTO> completedTask(@PathVariable ("id") Long id) throws IdNotFoundException {
        var taskDto = taskService.completedTask(id);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable ("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
