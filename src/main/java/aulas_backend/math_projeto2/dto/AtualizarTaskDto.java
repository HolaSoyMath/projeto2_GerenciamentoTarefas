package aulas_backend.math_projeto2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtualizarTaskDto {

    @NotNull(message = "ID da Task deve ser informado")
    private Integer idTask;

    @NotBlank(message = "Indicar se deve aumentar ou diminuir a prioridade da tarefa")
    private String priorizacao;

    public @NotNull(message = "ID da Task deve ser informado") Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(@NotNull(message = "ID da Task deve ser informado") Integer idTask) {
        this.idTask = idTask;
    }

    public @NotBlank(message = "Indicar se deve aumentar ou diminuir a prioridade da tarefa") String getPriorizacao() {
        return priorizacao;
    }

    public void setPriorizacao(@NotBlank(message = "Indicar se deve aumentar ou diminuir a prioridade da tarefa") String priorizacao) {
        this.priorizacao = priorizacao;
    }
}
