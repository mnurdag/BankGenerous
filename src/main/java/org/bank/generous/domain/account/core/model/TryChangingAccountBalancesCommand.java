package org.bank.generous.domain.account.core.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.shared.entity.Account;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TryChangingAccountBalancesCommand {
    Account sourceAccount;
    Account destinationAccount;
    BigDecimal amount;
}
