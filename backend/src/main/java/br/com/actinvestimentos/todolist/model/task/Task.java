package br.com.actinvestimentos.todolist.model.task;

import br.com.actinvestimentos.todolist.enums.PriorityEnum;
import br.com.actinvestimentos.todolist.enums.StatusEnum;
import br.com.actinvestimentos.todolist.enums.converters.PriorityEnumConverter;
import br.com.actinvestimentos.todolist.enums.converters.StatusEnumConverter;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    @Convert(converter = PriorityEnumConverter.class)
    private PriorityEnum priorityEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusEnumConverter.class)
    private StatusEnum statusEnum;

    public void working() {
        this.statusEnum = StatusEnum.WORKING;
    }

    public void completed() {
        if(StatusEnum.WORKING.equals(this.statusEnum)) {
            this.statusEnum = StatusEnum.COMPLETED;
        }
    }
}
