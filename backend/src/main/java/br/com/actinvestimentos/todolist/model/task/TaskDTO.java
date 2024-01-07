package br.com.actinvestimentos.todolist.model.task;

import br.com.actinvestimentos.todolist.enums.PriorityEnum;
import br.com.actinvestimentos.todolist.enums.StatusEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String name;
    private PriorityEnum priorityEnum;
    private StatusEnum statusEnum;
}
