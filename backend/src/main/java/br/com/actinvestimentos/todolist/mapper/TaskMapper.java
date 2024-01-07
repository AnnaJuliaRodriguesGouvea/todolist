package br.com.actinvestimentos.todolist.mapper;

import br.com.actinvestimentos.todolist.model.task.Task;
import br.com.actinvestimentos.todolist.model.task.TaskDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface TaskMapper {
    TaskDTO toDto(Task task);
    List<TaskDTO> toDtoList(List<Task> task);
    Task toEntity(TaskDTO taskDTO);
}
