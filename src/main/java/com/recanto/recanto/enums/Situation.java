package com.recanto.recanto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Situation {
    ABERTO(0, "ABERTO"),PENDENTE(1, "PENDENTE"), FINALIZADO(2, "FINALIZADO");

    private final Integer code;
    private final String description;

    public static Situation toEnum(Integer cod) {
        if(cod == null ) {
            return null;
        }

        for(Situation perfil : Situation.values()) {
            if (cod.equals(perfil.getCode())) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Tipo de situação não corresponde a um tipo válido.");
    }
}
