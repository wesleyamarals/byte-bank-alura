package br.com.ws.bytebankalura.model;

import lombok.Getter;

@Getter
public class Account {

    private Integer number;
    private Integer agency;
    private Client client;
    private Integer balance;

    public Account(Integer number, Integer agency, Client client) {
        this.number = number;
        this.agency = agency;
        this.client = client;
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
}
