package br.com.ws.bytebankalura.dtos;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
public class OperationsWithdrawDto extends OperationsDepositDto {

    @Size(min = 4, max = 4)
    private final String password;

    public OperationsWithdrawDto(@Valid OperationsClientDto client, @Valid AccountDto account, @Positive Integer value,
                                 String password) {
        super(client, account, value);
        this.password = password;

    }
}
