package br.com.actinvestimentos.todolist.service;

import br.com.actinvestimentos.todolist.enums.PriorityEnum;
import br.com.actinvestimentos.todolist.enums.StatusEnum;
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

    public TaskDTO insertTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        isValidTask(task);
        task.setStatusEnum(StatusEnum.NEW);
        return taskMapper.toDto(taskRepository.save(task));
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        isValidTask(task);
        // TODO - colocar exception no OPTIONAL
        var foundTask = taskRepository.findById(id);
        foundTask.get().setName(task.getName());
        foundTask.get().setPriorityEnum(task.getPriorityEnum());
        return taskMapper.toDto(taskRepository.save(foundTask.get()));
    }

    public TaskDTO workingTask(Long id) {
        // TODO - colocar exception no OPTIONAL
        Optional<Task> task = taskRepository.findById(id);
        task.get().working();
        return taskMapper.toDto(taskRepository.save(task.get()));
    }

    public TaskDTO completedTask(Long id) {
        // TODO - colocar exception no OPTIONAL
        Optional<Task> task = taskRepository.findById(id);
        task.get().completed();
        return taskMapper.toDto(taskRepository.save(task.get()));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean isValidTask(Task task) {
        return isValidName(task.getName())
                && isValidPriority(task.getPriorityEnum());
    }

    public boolean isValidName(String name) {
        return !name.isBlank();
    }

    public boolean isValidPriority(PriorityEnum priorityEnum) {
        return priorityEnum != null;
    }

}
