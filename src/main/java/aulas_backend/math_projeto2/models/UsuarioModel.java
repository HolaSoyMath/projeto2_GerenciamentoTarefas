package aulas_backend.math_projeto2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity(name = "Usuario")
@Table(name = "tb_user")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank(message = "Login deve ser informado")
    private String username;

    @NotBlank(message = "Uma senha deve ser informada")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "Um email deve ser informado")
    private String email;

    @OneToMany(mappedBy = "idUser")
    private List<TarefasModel> tarefas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TarefasModel> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefasModel> tarefas) {
        this.tarefas = tarefas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
