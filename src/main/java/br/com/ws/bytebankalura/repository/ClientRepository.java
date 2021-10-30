package br.com.ws.bytebankalura.repository;

import br.com.ws.bytebankalura.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findByDocument(String document);
}
