package br.com.ws.bytebankalura.dtos;

import lombok.Getter;

@Getter
public class OperationsTransferDto extends OperationsWithdrawDto {

    AccountDto destinationAccount;
    OperationsClientDto destinationClient;

    public OperationsTransferDto(OperationsClientDto client, AccountDto sourceAccount, Integer value, String password,
                                 AccountDto destinationAccount, OperationsClientDto destinationClient) {
        super(client, sourceAccount, value, password);
        this.destinationAccount = destinationAccount;
        this.destinationClient = destinationClient;
    }
}
