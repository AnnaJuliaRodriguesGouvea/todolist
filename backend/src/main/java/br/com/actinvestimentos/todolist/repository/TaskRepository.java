package br.com.actinvestimentos.todolist.repository;

import br.com.actinvestimentos.todolist.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
