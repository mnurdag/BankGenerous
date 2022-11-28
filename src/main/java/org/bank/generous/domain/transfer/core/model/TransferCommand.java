package org.bank.generous.domain.transfer.core.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferCommand {
    @Length(min = 10, max = 10, message = "{source.length}")
    String sourceAccountNumber;
    @Length(min = 10, max = 10, message = "{destination.length}")
    String destinationAccountNumber;
    @Positive(message = "{amount.not.positive}")
    BigDecimal amount;
}
