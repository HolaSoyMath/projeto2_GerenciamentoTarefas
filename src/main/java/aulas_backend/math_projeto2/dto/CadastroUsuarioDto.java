package aulas_backend.math_projeto2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CadastroUsuarioDto {

    private Integer id;

    @NotBlank(message = "Login deve ser informado")
    private String username;

    @NotBlank(message = "Uma senha deve ser informada")
    private String password;

    @NotBlank(message = "Um email deve ser informado")
    @Email(message = "O email deve ser válido")
    private String email;

    public @NotBlank(message = "Login deve ser informado") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Login deve ser informado") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Uma senha deve ser informada") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Uma senha deve ser informada") String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Um email deve ser informado") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Um email deve ser informado") String email) {
        this.email = email;
    }
}
