package aulas_backend.math_projeto2.controllers;

import aulas_backend.math_projeto2.dto.AtualizarTaskDto;
import aulas_backend.math_projeto2.dto.CadastroTaskDto;
import aulas_backend.math_projeto2.dto.ObterTasksDto;
import aulas_backend.math_projeto2.dto.RemoverTarefaDto;
import aulas_backend.math_projeto2.models.TarefasModel;
import aulas_backend.math_projeto2.models.UsuarioModel;
import aulas_backend.math_projeto2.repositories.TaskRepository;
import aulas_backend.math_projeto2.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UsuarioRepository usuarioRepository;

    public TaskController(TaskRepository taskRepository, UsuarioRepository usuarioRepository) {
        this.taskRepository = taskRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Operation(summary = "Cadastrar tarefa", description = "Cadastrar uma nova tarefa para um usuário", tags = "task")
    @PostMapping()
    public ResponseEntity<Object> salvar(@RequestBody CadastroTaskDto tarefaDto) {

        // Buscar usuário pelo idUser
        Optional<UsuarioModel> usuario = usuarioRepository.findById(tarefaDto.getIdUser());

        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

        // Converter Dto para model
        TarefasModel tarefasModel = new TarefasModel();
        tarefasModel.setIdUser(tarefaDto.getIdUser());
        tarefasModel.setUser(usuario.get());
        tarefasModel.setDescricao(tarefaDto.getDescricao());
        // Obter as tasks do usuário
        List<TarefasModel> tasks = taskRepository.findByIduser(tarefaDto.getIdUser());
        System.out.println(tasks);
        // Obter o maior valor de "prioridade" de tarefas
        int maxTask = tasks.stream()
                .mapToInt(TarefasModel::getPositionDesc)  // Transforma a stream de objetos para uma stream de inteiros
                .max()                                    // Encontra o maior valor na stream de inteiros
                .orElse(0);                         // Retorna 0 se a stream estiver vazia
        int proximaTask = maxTask + 1;
        tarefasModel.setPositionDesc(proximaTask);

        // Setar o valor da tarefa priorizada
        tarefaDto.setPositionDesc(proximaTask);

        // Salvar a tarefa
        tarefasModel = taskRepository.save(tarefasModel);

        // Setar o valor do ID da tarefa
        tarefaDto.setIdTask(tarefasModel.getId());

        return ResponseEntity.ok().body(tarefaDto);
    }

    @Operation(summary = "Remover tarefa", description = "Remover uma tarefa cadastrada", tags = "task")
    @DeleteMapping("/remove")
    public ResponseEntity<Object> remover(@RequestBody RemoverTarefaDto tarefaDto){

        Optional<TarefasModel> tarefa = taskRepository.findById(tarefaDto.getIdTask());

        if (tarefa.isEmpty()) {
            return ResponseEntity.badRequest().body("Tarefa não encontrada");
        }

        taskRepository.delete(tarefa.get());

        return ResponseEntity.ok().body(tarefaDto);

    }

    @Operation(summary = "Obter tarefas de um ID", description = "RObter todas as tarefas referentes a um usuário especificado", tags = "task")
    @GetMapping("/getTasks")
    public ResponseEntity<Object> getTasks(@RequestBody ObterTasksDto obterTasksDto){

        // Obter todas as tasks através do ID
        List<TarefasModel> tasks = taskRepository.findByIduser(obterTasksDto.getIdUser());

        // Criar um novo Array
        List<CadastroTaskDto> responseDto = new ArrayList<>();
        // Fazer um looping nas tasks recebidas e popular o array criado
        for (var item : tasks) {
            CadastroTaskDto dto = new CadastroTaskDto();
            dto.setIdTask(item.getId());
            dto.setDescricao(item.getDescricao());
            dto.setPositionDesc(item.getPositionDesc());
            dto.setIdUser(item.getIdUser());
            responseDto.add(dto);
        }

        // Retornar o array criado
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Atualizar prioridade da tarefa")
    @PatchMapping("/updateTasks")
    public ResponseEntity<Object> updateTasks(@RequestBody AtualizarTaskDto atualizarTaskDto) {
        // Buscar a tarefa pelo ID
        Optional<TarefasModel> tarefaOpt = taskRepository.findById(atualizarTaskDto.getIdTask());

        if (tarefaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Tarefa não encontrada");
        }

        TarefasModel tarefaAtual = tarefaOpt.get();
        int idUser = tarefaAtual.getIdUser();
        int novaPositionDesc = tarefaAtual.getPositionDesc();

        // Verifica se deve aumentar ou diminuir a prioridade
        if (atualizarTaskDto.getPriorizacao().equalsIgnoreCase("aumentar")) {
            novaPositionDesc--;
        } else if (atualizarTaskDto.getPriorizacao().equalsIgnoreCase("diminuir")) {
            novaPositionDesc++;
        } else {
            return ResponseEntity.badRequest().body("Prioridade inválida. Use 'aumentar' ou 'diminuir'.");
        }

        // Encontrar a tarefa que deve ser trocada
        Optional<TarefasModel> tarefaParaTrocarOpt = taskRepository.findByIduserAndPositionDesc(idUser, novaPositionDesc);

        if (tarefaParaTrocarOpt.isPresent()) {
            TarefasModel tarefaParaTrocar = tarefaParaTrocarOpt.get();

            // Trocar as prioridades
            tarefaParaTrocar.setPositionDesc(tarefaAtual.getPositionDesc());
            tarefaAtual.setPositionDesc(novaPositionDesc);

            // Salvar as tarefas atualizadas
            taskRepository.save(tarefaParaTrocar);
        } else {
            // Se não houver tarefa para trocar, apenas atualiza a posição da tarefa atual
            tarefaAtual.setPositionDesc(novaPositionDesc);
        }

        // Salvar a tarefa atualizada
        taskRepository.save(tarefaAtual);

        // Retornar a tarefa atualizada como resposta
        return ResponseEntity.ok().body("Prioridade atualizada com sucesso");
    }

}
