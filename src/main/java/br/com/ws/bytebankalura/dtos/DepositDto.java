package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepositDto {

    private OperationsClientDto client;
    private AccountDto account;
    private Integer value;

}
