package aulas_backend.math_projeto2.repositories;

import aulas_backend.math_projeto2.models.TarefasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<TarefasModel, Integer> {

    @Query("select u from Tarefas u where u.idUser = ?1")
    List<TarefasModel> findByIduser(Integer idUser);

    @Query("select t from Tarefas t where t.idUser = ?1 and t.positionDesc = ?2")
    Optional<TarefasModel> findByIduserAndPositionDesc(Integer idUser, Integer positionDesc);

}
