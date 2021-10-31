package br.com.ws.bytebankalura.controller;

import br.com.ws.bytebankalura.dtos.CreateAccountDto;
import br.com.ws.bytebankalura.service.AdministrativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "byteBankAluraAdministrative")
public class ByteBankAluraAdministrativeController {

    AdministrativeService administrativeService;

    @Autowired
    public ByteBankAluraAdministrativeController(AdministrativeService administrativeService) {
        this.administrativeService = administrativeService;
    }

    @PostMapping(path = "createAccount")
    public ResponseEntity<String> createAccount(@RequestBody @Valid CreateAccountDto createAccountDto) {
        try {
            return ResponseEntity.ok(administrativeService.createAccount(createAccountDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível criar a conta");

        }
    }
}
