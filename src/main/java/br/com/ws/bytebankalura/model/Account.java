package br.com.ws.bytebankalura.model;

import br.com.ws.bytebankalura.entity.AccountEntity;
import lombok.Getter;

@Getter
public class Account {

    private Integer number;
    private Integer agency;
    private Client client;
    private Long balance;

    public Account(Integer number, Integer agency, Client client) {
        this.number = number;
        this.agency = agency;
        this.client = client;
        this.balance = 0L;
    }

    public Boolean withdraw(Long value) {
        if (this.balance >= value) {
            this.balance -= value;
            return true;
        }
        return false;
    }

    public Long deposit(Long value) {
        this.balance += value;
        return this.getBalance();
    }

    public AccountEntity toEntity(Long clientId) {
        return AccountEntity.builder().number(this.number).agency(this.agency)
                .balance(this.balance).clientId(clientId).build();
    }
}
