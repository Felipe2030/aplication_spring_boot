package com.example.aplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aplication.entities.Turma;
import com.example.aplication.services.TurmaService;

@RestController
@CrossOrigin
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaService TurmaService;

    @Autowired
    public TurmaController(TurmaService TurmaService) {
        this.TurmaService = TurmaService;
    }

    @PostMapping
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma Turma) {
        Turma newTurma = TurmaService.createTurma(Turma);
        return new ResponseEntity<>(newTurma, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listTurmaes() {
        List<Turma> Turmaes = TurmaService.listAllTurmas();
        return new ResponseEntity<>(Turmaes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable Long id) {
        Turma Turma = TurmaService.getTurmaById(id);
        return Turma != null ?
               new ResponseEntity<>(Turma, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma Turma) {
        Turma TurmaAtualizado = TurmaService.updateTurma(id, Turma);
        return TurmaAtualizado != null ?
               new ResponseEntity<>(TurmaAtualizado, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTurma(@PathVariable Long id) {
        TurmaService.deleteTurma(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
