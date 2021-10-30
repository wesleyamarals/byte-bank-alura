package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
@AllArgsConstructor
public class OperationsDepositDto {

    private OperationsClientDto client;
    private AccountDto account;

    @Min(value = 1)
    private Integer value;

}
