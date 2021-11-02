package br.com.ws.bytebankalura.model;

import br.com.ws.bytebankalura.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer number;
    private Integer agency;
    private Integer balance;

    public Account(Integer number, Integer agency) {
        this.number = number;
        this.agency = agency;
        this.balance = 0;
    }

    public Boolean withdraw(Integer value) {
        if (this.balance >= value) {
            this.balance -= value;
            return true;
        }
        return false;
    }

    public Integer deposit(Integer value) {
        this.balance += value;
        return this.getBalance();
    }

    public Boolean transfer(Account destinationAccount, Integer value) {
        if (this.withdraw(value)) {
            destinationAccount.deposit(value);
            return true;
        }
        return false;
    }

    public AccountEntity toEntity(Long clientId) {
        return AccountEntity.builder().number(this.number).agency(this.agency)
                .balance(this.balance).clientId(clientId).build();
    }
}
