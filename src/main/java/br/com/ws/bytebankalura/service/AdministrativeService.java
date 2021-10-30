package br.com.ws.bytebankalura.service;

import br.com.ws.bytebankalura.dtos.CreateAccountDto;
import br.com.ws.bytebankalura.entity.AccountEntity;
import br.com.ws.bytebankalura.entity.ClientEntity;
import br.com.ws.bytebankalura.model.Account;
import br.com.ws.bytebankalura.model.Client;
import br.com.ws.bytebankalura.repository.AccountRepository;
import br.com.ws.bytebankalura.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrativeService {

    AccountRepository accountRepository;
    ClientRepository clientRepository;

    @Autowired
    public AdministrativeService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    public String createAccount(CreateAccountDto createAccountDto) {

        Client client = new Client(createAccountDto.getClient().getName(),
                createAccountDto.getClient().getDocument(), createAccountDto.getClient().getPassword(),
                createAccountDto.getClient().getTelephones());
        Account account = new Account(createAccountDto.getAccount().getNumber(),
                createAccountDto.getAccount().getAgency(), client);

        ClientEntity clientEntity = clientRepository.save(client.toEntity());
        AccountEntity accountEntity = accountRepository.save(account.toEntity(clientEntity.getId()));

        return "Conta criada com sucesso, dados da conta: \n" +
                "Numero: " + accountEntity.getNumber() + "\n" +
                "Agência " + accountEntity.getAgency();

    }
}
