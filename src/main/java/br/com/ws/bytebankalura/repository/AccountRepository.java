package br.com.ws.bytebankalura.repository;

import br.com.ws.bytebankalura.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByClientId(Long id);

}

