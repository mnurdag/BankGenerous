package org.bank.generous.domain.transfer.infrastructure;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.shared.entity.Account;
import org.bank.generous.domain.shared.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(schema = "BANK", name = "TRANSACTION")
@AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false))
public class Transaction extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCEACCOUNTID")
    Account sourceAccount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESTINATIONACCOUNTID", nullable = false)
    Account destinationAccount;

    @Column(name = "AMOUNT", nullable = false)
    BigDecimal amount;

    @Column(name = "TRANSACTIONTIME")
    LocalDateTime transactionTime;

}

