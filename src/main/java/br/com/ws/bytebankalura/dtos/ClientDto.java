package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClientDto {

    private String name;
    private String document;
    private String password;
    private List<String> telephones;
}
