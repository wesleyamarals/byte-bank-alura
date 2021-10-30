package br.com.ws.bytebankalura.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id = 1L;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Document", nullable = false)
    private String document;

    @Column(name = "Telephones", nullable = false)
    private String telephones;

    @Column(name = "Password", nullable = false)
    private String password;
}
