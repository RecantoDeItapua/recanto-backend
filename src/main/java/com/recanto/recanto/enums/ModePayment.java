package com.recanto.recanto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ModePayment {
    DINHEIRO(0, "DINHEIRO"), CARTAO(1, "CARTÃO"), PIX(2, "PIX");

    private final Integer code;
    private final String description;

    public static ModePayment toEnum(Integer cod) {
        if(cod == null ) {
            return null;
        }

        for(ModePayment mode : ModePayment.values()) {
            if (cod.equals(mode.getCode())) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Tipo de situação não corresponde a um tipo válido.");
    }
}
