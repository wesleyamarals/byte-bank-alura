package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class OperationsClientDto {

    @NotEmpty
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 11, max = 11)
    private String document;
}
