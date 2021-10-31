package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class OperationsDepositDto {
    
    @Valid
    private OperationsClientDto client;

    @Valid
    private AccountDto account;

    @Positive
    private Integer value;

}
