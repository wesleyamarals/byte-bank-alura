package br.com.ws.bytebankalura.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id = 1L;

    @Column(name = "Number", nullable = false)
    private Integer number;

    @Column(name = "Agency", nullable = false)
    private Integer agency;

    @Column(name = "Balance", nullable = false)
    private Long balance;

    @Column(name = "ClientId", nullable = false)
    private Long clientId;

}
