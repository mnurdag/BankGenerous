package org.bank.generous.domain.transfer.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.transfer.core.model.TransferCommand;
import org.bank.generous.domain.transfer.core.model.TransferResponse;
import org.bank.generous.domain.transfer.core.ports.incoming.TransferMoney;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/transfer")
public class TransferController {

    TransferMoney transferMoney;

    @PostMapping
    public ResponseEntity<TransferResponse> transferMoney(@Valid @RequestBody TransferCommand command) {
        TransferResponse response = transferMoney.handle(command);
        return ok(response);
    }

}
