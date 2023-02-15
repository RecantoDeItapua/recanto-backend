package com.recanto.recanto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LocalReservation {
    CHURRASQUEIRA(0, "CHURRASQUEIRA"),
    QUADRA(1, "QUADRA"),
    SALAO(2, "SALÃO");

    private final Integer code;
    private final String description;

    public static LocalReservation toEnum(Integer cod) {
        if(cod == null ) {
            return null;
        }

        for(LocalReservation reservation : LocalReservation.values()) {
            if (cod.equals(reservation.getCode())) {
                return reservation;
            }
        }
        throw new IllegalArgumentException("Tipo de reserva não corresponde a um tipo válido.");
    }
}
