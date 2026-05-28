package yemialyanava.dashboard.realtime.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yemialyanava.dashboard.realtime.entity.FinancialTransaction;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {

    Optional<FinancialTransaction> findByExternalId(UUID externalId);

    List<FinancialTransaction> findAllByOrderByCreatedAtDesc();
}
