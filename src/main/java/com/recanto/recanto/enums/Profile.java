package com.recanto.recanto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Profile {
    ADMIN(0, "ROLE_ADMIN"),EMPLOYEE(1, "ROLE_EMPLOYEE"), RESIDENT(2, "ROLE_RESIDENT");

    private final Integer code;
    private final String description;

    public static Profile toEnum(Integer cod) {
        if(cod == null ) {
            return null;
        }

        for(Profile perfil : Profile.values()) {
            if (cod.equals(perfil.getCode())) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Profile invalid");
    }
}
