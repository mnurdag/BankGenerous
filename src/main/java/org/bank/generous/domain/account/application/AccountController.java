package org.bank.generous.domain.account.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.account.core.model.AccountResponse;
import org.bank.generous.domain.account.core.ports.incoming.GetAccountResponse;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/account")
@Validated
public class AccountController {

    GetAccountResponse getAccountResponse;

    @GetMapping(path = "/{accountNumber}")
    public ResponseEntity<AccountResponse> readAccount(
            @PathVariable @Length(min = 10, max = 10, message = "{source.length}") String accountNumber) {
        AccountResponse response = getAccountResponse.handle(accountNumber);
        return ok(response);
    }

}
