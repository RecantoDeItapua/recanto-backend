package com.recanto.recanto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReservationAuthorite {
    AUTORIZADO(0, "AUTORIZADO"),
    PENDENTE(1, "PENDENTE"),
    NAO_AUTORIZADO(2, "NÃO AUTORIZADO");

    private final Integer code;
    private final String description;

    public static ReservationAuthorite toEnum(Integer cod) {
        if(cod == null ) {
            return null;
        }

        for(ReservationAuthorite reservation : ReservationAuthorite.values()) {
            if (cod.equals(reservation.getCode())) {
                return reservation;
            }
        }
        throw new IllegalArgumentException("Tipo de autorização não corresponde a um tipo válido.");
    }
}
