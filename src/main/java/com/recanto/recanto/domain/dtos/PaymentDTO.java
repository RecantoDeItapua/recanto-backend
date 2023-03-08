package com.recanto.recanto.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PaymentDTO {
    private Integer id;
    private String title;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate datePayment = LocalDate.now();
    private Integer situation;
    private Integer modePayment;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate finishPayment;
    private Integer person;
    private String personName;
    private Double cash;
    private String obs;
    private String adress;

    public PaymentDTO(Payment obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.cash = obj.getCash();
        this.datePayment = obj.getDatePayment();
        this.situation = obj.getSituation().getCode();
        this.modePayment = obj.getModePayment().getCode();
        this.finishPayment = obj.getFinishPayment();
        this.person = obj.getPerson().getId();
        this.personName = obj.getPerson().getName();
        this.obs = obj.getObs();
        this.adress = obj.getAdress();
    }
}
