package br.com.ws.bytebankalura.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
@AllArgsConstructor
public class AccountDto {

    @Min(value = 1)
    private Integer number;

    @Min(value = 1)
    private Integer agency;
}
