package br.com.ws.bytebankalura.service;

import br.com.ws.bytebankalura.dtos.OperationsClientDto;
import br.com.ws.bytebankalura.dtos.OperationsDepositDto;
import br.com.ws.bytebankalura.dtos.OperationsTransferDto;
import br.com.ws.bytebankalura.dtos.OperationsWithdrawDto;
import br.com.ws.bytebankalura.entity.AccountEntity;
import br.com.ws.bytebankalura.entity.ClientEntity;
import br.com.ws.bytebankalura.model.Account;
import br.com.ws.bytebankalura.repository.AccountRepository;
import br.com.ws.bytebankalura.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

        ClientEntity clientEntity = verifyAndGetClient(operationsWithdrawDto.getClient());
        if (clientEntity == null) return "Verifique se seus dados estão corretos";

        String authenticationMessage = authentication(operationsWithdrawDto, clientEntity);
        if (authenticationMessage != null) return authenticationMessage;

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

        ClientEntity clientEntity = verifyAndGetClient(operationsDepositDto.getClient());
        if (clientEntity == null) return "O Cliente para o qual deseja realizar o depósito não existe";

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

    public String transfer(OperationsTransferDto operationsTransferDto) {

        ClientEntity clientEntity = verifyAndGetClient(operationsTransferDto.getClient());
        if (clientEntity == null) return "Verifique se seus dados estão corretos";

        String authenticationMessage = authentication(operationsTransferDto, clientEntity);
        if (authenticationMessage != null) return authenticationMessage;

        AccountEntity sourceAccountEntity = accountRepository.findByClientId(clientEntity.getId());
        Account sourceAccount = sourceAccountEntity.toAccount();

        ClientEntity destinationClientEntity = verifyAndGetClient(operationsTransferDto.getDestinationClient());
        if (destinationClientEntity == null) return "Verifique se os dados do cliente de destino estão corretos";

        AccountEntity destinationAccountEntity = accountRepository.findByClientId(destinationClientEntity.getId());
        Account destinationAccount = destinationAccountEntity.toAccount();

        if (sourceAccount.transfer(destinationAccount, operationsTransferDto.getValue())) {
            sourceAccountEntity.setBalance(sourceAccount.getBalance());
            destinationAccountEntity.setBalance(destinationAccount.getBalance());
            accountRepository.saveAll(Arrays.asList(sourceAccountEntity, destinationAccountEntity));

            return "Sua transferência foi realizada com sucesso: \n" +
                    "Cliente de destino: " + destinationClientEntity.getName().toUpperCase() + "\n" +
                    "CPF: " + destinationClientEntity.getDocument() + "\n" +
                    "Agência: " + destinationAccountEntity.getAgency() + "\n" +
                    "Conta: " + destinationAccountEntity.getNumber() + "\n" +
                    "Valor: R$" + operationsTransferDto.getValue() + ",00";
        }

        return "Transferência não realizada, verifique seu saldo";
    }

    private ClientEntity verifyAndGetClient(OperationsClientDto operationsClientDto) {
        ClientEntity clientEntity = clientRepository.findByDocument(operationsClientDto.getDocument());
        if (Objects.isNull(clientEntity)) {
            return null;
        }
        return clientEntity;
    }

    private String authentication(OperationsWithdrawDto operationsWithdrawDto, ClientEntity clientEntity) {
        if (!Objects.equals(clientEntity.getPassword(), operationsWithdrawDto.getPassword())) {
            return "Sua senha está incorreta, tente novamente";
        }
        return null;
    }
}
