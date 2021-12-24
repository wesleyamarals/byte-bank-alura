package br.com.ws.bytebankalura.entity;

import br.com.ws.bytebankalura.model.Account;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id = 1L;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "agency", nullable = false)
    private Integer agency;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    @Column(name = "clientId", nullable = false)
    private Long clientId;

    public Account toAccount() {
        return Account.builder().number(this.number).agency(this.agency).balance(this.balance).build();
    }

}
