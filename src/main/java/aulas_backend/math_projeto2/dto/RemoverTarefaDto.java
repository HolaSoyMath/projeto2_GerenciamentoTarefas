package aulas_backend.math_projeto2.dto;

import jakarta.validation.constraints.NotNull;

public class RemoverTarefaDto {

    @NotNull(message = "ID da tarefa deve ser informado")
    private Integer idTask;

    public @NotNull(message = "ID da tarefa deve ser informado") Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(@NotNull(message = "ID da tarefa deve ser informado") Integer idTask) {
        this.idTask = idTask;
    }
}
