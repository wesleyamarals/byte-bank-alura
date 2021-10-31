package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;

@Getter
@AllArgsConstructor
public class CreateAccountDto {

    @Valid
    private AccountDto account;

    @Valid
    private ClientDto client;
}
