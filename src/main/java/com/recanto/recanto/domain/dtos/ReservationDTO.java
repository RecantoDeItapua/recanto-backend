package com.recanto.recanto.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ReservationDTO {
    private Integer id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime openDate = LocalDateTime.now();

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime reserveDate;

    private Integer person;
    private String personName;

    public ReservationDTO(Reservation obj) {
        this.id = obj.getId();
        this.openDate = obj.getOpenDate();
        this.reserveDate = obj.getReserveDate();
        this.person = obj.getPerson().getId();
        this.personName = obj.getPerson().getName();
    }
}
