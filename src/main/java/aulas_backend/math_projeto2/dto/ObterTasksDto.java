package aulas_backend.math_projeto2.dto;

import jakarta.validation.constraints.NotNull;

public class ObterTasksDto {

    @NotNull(message = "ID da do usuário deve ser informado")
    private Integer idUser;

    public @NotNull(message = "ID da do usuário deve ser informado") Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(@NotNull(message = "ID da do usuário deve ser informado") Integer idUser) {
        this.idUser = idUser;
    }

}
