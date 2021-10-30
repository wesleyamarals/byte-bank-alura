package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccountDto {

    private AccountDto accountDto;
    private ClientDto clientDto;
}
