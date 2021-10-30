package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OperationsClientDto {

    private String name;
    private String document;
    private String password;
}
