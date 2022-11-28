package org.bank.generous.domain.account.infrastructure;

import org.bank.generous.domain.shared.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select a from Account a where a.accountNumber = :accountNumber")
    Optional<Account> findLockedAccountByAccountNumber(String accountNumber);

    Optional<Account> findByAccountNumber(String accountNumber);

}
