package aulas_backend.math_projeto2.repositories;

import aulas_backend.math_projeto2.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

}
