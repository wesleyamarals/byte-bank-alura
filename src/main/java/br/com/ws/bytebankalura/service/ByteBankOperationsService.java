package br.com.ws.bytebankalura.service;

import br.com.ws.bytebankalura.dtos.OperationsDepositDto;
import br.com.ws.bytebankalura.dtos.OperationsWithdrawDto;
import br.com.ws.bytebankalura.entity.AccountEntity;
import br.com.ws.bytebankalura.entity.ClientEntity;
import br.com.ws.bytebankalura.model.Account;
import br.com.ws.bytebankalura.repository.AccountRepository;
import br.com.ws.bytebankalura.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ByteBankOperationsService {

    ClientRepository clientRepository;
    AccountRepository accountRepository;

    public ByteBankOperationsService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public String withdraw(OperationsWithdrawDto operationsWithdrawDto) {
        ClientEntity clientEntity = clientRepository.findByDocument(operationsWithdrawDto.getClient().getDocument());
        if (Objects.isNull(clientEntity)) {
            return "O Cliente para o qual deseja realizar o saque não existe";
        }

        if (!Objects.equals(clientEntity.getPassword(), operationsWithdrawDto.getPassword())) {
            return "Sua senha está incorreta, tente novamente";
        }

        AccountEntity accountEntity = accountRepository.findByClientId(clientEntity.getId());
        Account account = accountEntity.toAccount();

        if (!Objects.equals(account.getNumber(), operationsWithdrawDto.getAccount().getNumber())
                || !Objects.equals(account.getAgency(), operationsWithdrawDto.getAccount().getAgency())) {

            return "A conta ou agência para a qual deseja realizar o saque não existe";
        }

        if (!account.withdraw(operationsWithdrawDto.getValue())) {
            return "Saldo insuficiente";
        }
        accountEntity.setBalance(account.getBalance());
        accountRepository.save(accountEntity);

        return "Seu saque foi realizado com sucesso: \n" +
                clientEntity.getName().toUpperCase() + "\n" +
                "CPF: " + clientEntity.getDocument() + "\n" +
                "Agência: " + account.getAgency() + "\n" +
                "Conta: " + account.getNumber() + "\n" +
                "Valor: R$" + operationsWithdrawDto.getValue() + ",00";
    }


    public String deposit(OperationsDepositDto operationsDepositDto) {

        ClientEntity clientEntity = clientRepository.findByDocument(operationsDepositDto.getClient().getDocument());

        if (Objects.isNull(clientEntity)) {
            return "O Cliente para o qual deseja realizar o depósito não existe";
        }


        AccountEntity accountEntity = accountRepository.findByClientId(clientEntity.getId());
        Account account = accountEntity.toAccount();

        if (!Objects.equals(account.getNumber(), operationsDepositDto.getAccount().getNumber())
                || !Objects.equals(account.getAgency(), operationsDepositDto.getAccount().getAgency())) {

            return "A conta ou agência para a qual deseja realizar o depósito não existe";
        }

        account.deposit(operationsDepositDto.getValue());
        accountEntity.setBalance(account.getBalance());

        accountRepository.save(accountEntity);
        return "Seu depósito foi realizado com sucesso: \n" +
                clientEntity.getName().toUpperCase() + "\n" +
                "CPF: " + clientEntity.getDocument() + "\n" +
                "Agência: " + account.getAgency() + "\n" +
                "Conta: " + account.getNumber() + "\n" +
                "Valor: R$" + operationsDepositDto.getValue() + ",00";
    }
}
