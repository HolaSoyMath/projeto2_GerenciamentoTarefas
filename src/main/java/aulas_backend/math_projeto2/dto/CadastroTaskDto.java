package aulas_backend.math_projeto2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CadastroTaskDto {

    private Integer idTask;

    @NotNull(message = "ID User deve ser informado")
    private Integer idUser;

    @NotBlank(message = "Descrição da tarefa deve ser informada")
    private String descricao;

    private int positionDesc;

    @NotNull(message = "ID User deve ser informado")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(@NotNull(message = "ID User deve ser informado") Integer idUser) {
        this.idUser = idUser;
    }

    public @NotBlank(message = "Descrição da tarefa deve ser informada") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Descrição da tarefa deve ser informada") String descricao) {
        this.descricao = descricao;
    }

    public int getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(int positionDesc) {
        this.positionDesc = positionDesc;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }
}
