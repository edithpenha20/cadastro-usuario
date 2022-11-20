package com.registration.dto;

import javax.validation.constraints.NotNull;

public record DadosAtualizaUsuario(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
