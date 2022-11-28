package org.bank.generous.domain.shared.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(schema = "BANK", name = "ACCOUNT")
@DynamicUpdate
@AttributeOverride(name = "id", column = @Column(name = "ACCOUNTID"))
public class Account extends VersionedBaseEntity {

    @NaturalId
    @Column(name = "ACCOUNTNUMBER", length = 10, nullable = false)
    String accountNumber;

    @Column(name = "BALANCE", nullable = false)
    BigDecimal balance;

    public boolean hasEnoughBalanceForAmount(BigDecimal amount) {
        return balance.compareTo(amount) != -1;
    }

}

