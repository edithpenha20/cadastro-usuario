package com.registration.controller;

import com.registration.dto.DadosAtualizaUsuario;
import com.registration.dto.DadosCadastroUsuario;
import com.registration.dto.DadosListarUsuario;
import com.registration.model.Usuario;
import com.registration.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dados){
        repository.save(new Usuario(dados));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DadosListarUsuario> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListarUsuario::new);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaUsuario dados){
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.getReferenceById(id);
    }
}
