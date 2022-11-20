package com.registration.dto;

import com.registration.model.Endereco;
import com.registration.model.Usuario;

public record DadosListarUsuario(
        Long id,
        String nome,
        String email,
        Endereco endereco
) {
    public DadosListarUsuario(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getEndereco()
        );
    }
}
