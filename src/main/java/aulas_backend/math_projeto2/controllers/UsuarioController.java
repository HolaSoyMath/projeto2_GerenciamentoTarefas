package aulas_backend.math_projeto2.controllers;

import aulas_backend.math_projeto2.dto.CadastroUsuarioDto;
import aulas_backend.math_projeto2.models.UsuarioModel;
import aulas_backend.math_projeto2.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Cadastrar usuario", description = "Cadastrar um novo usuário no banco de dados", tags = "usuario")
    @PostMapping("/salvar")
    public ResponseEntity<CadastroUsuarioDto> salvar(@RequestBody CadastroUsuarioDto usuarioDTO) {

        // Converter o DTO para o Model
        UsuarioModel usuario= new UsuarioModel();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario = repository.save(usuario);
        usuarioDTO.setId(usuario.getId());

        return ResponseEntity.status(201).body(usuarioDTO);
    }

}
