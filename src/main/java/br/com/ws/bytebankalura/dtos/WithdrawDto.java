package br.com.ws.bytebankalura.dtos;

import lombok.Getter;

@Getter
public class WithdrawDto extends DepositDto {

    private final String password;

    public WithdrawDto(OperationsClientDto client, AccountDto account, Integer value, String password) {
        super(client, account, value);
        this.password = password;
    }
}
