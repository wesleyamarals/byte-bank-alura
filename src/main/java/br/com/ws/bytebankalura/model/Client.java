package br.com.ws.bytebankalura.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class Client {

    @Getter
    private final String name;

    @Getter
    private final String document;

    @Getter
    private final String password;

    @Getter
    @Setter
    private List<String> telephone;

}
