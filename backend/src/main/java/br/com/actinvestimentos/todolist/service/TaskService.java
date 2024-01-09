package br.com.actinvestimentos.todolist.service;

import br.com.actinvestimentos.todolist.enums.PriorityEnum;
import br.com.actinvestimentos.todolist.enums.StatusEnum;
import br.com.actinvestimentos.todolist.exceptions.IdNotFoundException;
import br.com.actinvestimentos.todolist.exceptions.InvalidNameException;
import br.com.actinvestimentos.todolist.exceptions.InvalidPriorityException;
import br.com.actinvestimentos.todolist.mapper.TaskMapper;
import br.com.actinvestimentos.todolist.model.task.Task;
import br.com.actinvestimentos.todolist.model.task.TaskDTO;
import br.com.actinvestimentos.todolist.repository.TaskRepository;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Inject
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    public List<TaskDTO> listTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

    public TaskDTO insertTask(TaskDTO taskDTO) throws InvalidNameException, InvalidPriorityException {
        Task task = taskMapper.toEntity(taskDTO);
        isValidTask(task);
        task.setStatusEnum(StatusEnum.NEW);
        return taskMapper.toDto(taskRepository.save(task));
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO)
            throws InvalidNameException, InvalidPriorityException, IdNotFoundException {
        Task task = taskMapper.toEntity(taskDTO);
        isValidTask(task);
        var foundTask = taskRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not found!"));
        foundTask.setName(task.getName());
        foundTask.setPriorityEnum(task.getPriorityEnum());
        return taskMapper.toDto(taskRepository.save(foundTask));
    }

    public TaskDTO workingTask(Long id) throws IdNotFoundException {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not found!"));
        task.working();
        return taskMapper.toDto(taskRepository.save(task));
    }

    public TaskDTO completedTask(Long id) throws IdNotFoundException {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not found!"));
        task.completed();
        return taskMapper.toDto(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void isValidTask(Task task) throws InvalidNameException, InvalidPriorityException {
        if(!isValidName(task.getName())) {
            throw new InvalidNameException("Name entered is invalid!");
        }

        if(!isValidPriority(task.getPriorityEnum())) {
            throw new InvalidPriorityException("Priority entered is invalid!");
        }
    }

    public boolean isValidName(String name) {
        return !name.isBlank();
    }

    public boolean isValidPriority(PriorityEnum priorityEnum) {
        return priorityEnum != null;
    }

}
