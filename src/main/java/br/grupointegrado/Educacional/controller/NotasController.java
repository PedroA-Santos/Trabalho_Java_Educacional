package br.grupointegrado.Educacional.controller;


import br.grupointegrado.Educacional.repository.NotasRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notas")
public class NotasController {

    public NotasRepository repository;
}

