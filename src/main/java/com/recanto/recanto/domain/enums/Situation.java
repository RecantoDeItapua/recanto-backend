package com.recanto.recanto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Situation {
    ABERTO(0, "ABERTO"),VENCIDO(1, "VENCIDO"), FINALIZADO(2, "FINALIZADO");

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
        throw new IllegalArgumentException("Pagamento inválido");
    }
}
