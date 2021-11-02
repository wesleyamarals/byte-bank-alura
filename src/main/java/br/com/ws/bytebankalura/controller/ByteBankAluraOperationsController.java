package br.com.ws.bytebankalura.controller;

import br.com.ws.bytebankalura.dtos.OperationsDepositDto;
import br.com.ws.bytebankalura.dtos.OperationsTransferDto;
import br.com.ws.bytebankalura.dtos.OperationsWithdrawDto;
import br.com.ws.bytebankalura.service.ByteBankOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "byteBankAluraOperations")
public class ByteBankAluraOperationsController {

    private final ByteBankOperationsService byteBankOperationsService;

    @Autowired
    public ByteBankAluraOperationsController(ByteBankOperationsService byteBankOperationsService) {
        this.byteBankOperationsService = byteBankOperationsService;
    }

    @PostMapping(path = "withdraw")
    public ResponseEntity<String> withdrway(@RequestBody OperationsWithdrawDto withdrawDto) {
        try {
            return ResponseEntity.ok(byteBankOperationsService.withdraw(withdrawDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível processar o seu saque");

        }
    }

    @PostMapping(path = "deposit")
    public ResponseEntity<String> deposit(@RequestBody @Valid OperationsDepositDto depositDto) {
        try {
            return ResponseEntity.ok(byteBankOperationsService.deposit(depositDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível processar o seu depósito");

        }
    }

    @PostMapping(path = "transfer")
    public ResponseEntity<String> transfer(@RequestBody @Valid OperationsTransferDto transferDto) {
        try {
            return ResponseEntity.ok(byteBankOperationsService.transfer(transferDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível processar a sua " +
                    "transferência");

        }
    }
}
