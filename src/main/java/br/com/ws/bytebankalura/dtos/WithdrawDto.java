package br.com.ws.bytebankalura.dtos;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class WithdrawDto extends DepositDto {

    private String password;
}
