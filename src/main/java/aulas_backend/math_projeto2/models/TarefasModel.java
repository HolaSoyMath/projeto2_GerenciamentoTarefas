package aulas_backend.math_projeto2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Tarefas")
@Table(name = "tb_task")
public class TarefasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "O Id do usuário deve ser informado")
    private Integer idUser;

    @ManyToOne
    @NotNull(message = "O Id do usuário deve ser informado")
    private UsuarioModel user;

    @NotBlank(message = "O conteúdo da tarefa deve ser informado")
    private String descricao;

    private int positionDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "O Id do usuário deve ser informado") Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(@NotNull(message = "O Id do usuário deve ser informado") Integer idUser) {
        this.idUser = idUser;
    }

    public UsuarioModel getUser() {
        return user;
    }

    public void setUser(UsuarioModel user) {
        this.user = user;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(int positionDesc) {
        this.positionDesc = positionDesc;
    }


}
