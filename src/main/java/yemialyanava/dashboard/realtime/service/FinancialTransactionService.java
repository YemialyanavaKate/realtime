package yemialyanava.dashboard.realtime.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import yemialyanava.dashboard.realtime.dto.FinancialTransactionRequest;
import yemialyanava.dashboard.realtime.dto.FinancialTransactionResponse;
import yemialyanava.dashboard.realtime.entity.FinancialTransaction;
import yemialyanava.dashboard.realtime.mapper.FinancialTransactionMapper;
import yemialyanava.dashboard.realtime.repository.FinancialTransactionRepository;

@Service
@RequiredArgsConstructor
public class FinancialTransactionService {

    private final FinancialTransactionRepository repository;

    private final FinancialTransactionMapper transactionMapper;

    public FinancialTransactionResponse createResponse(FinancialTransaction transaction) {

        return transactionMapper.toResponse(transaction);
    }

    @Transactional
    public FinancialTransactionResponse getTransactionByID(Long id) {

        FinancialTransaction transaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        FinancialTransactionResponse response = transactionMapper.toResponse(transaction);

        return response;
    }

    @Transactional
    public FinancialTransactionResponse processTransaction(FinancialTransactionRequest transactionRequest) {

        FinancialTransaction transaction = transactionMapper.toEntity(transactionRequest);
        repository.save(transaction);

        FinancialTransaction transactionForTransfer = repository.findByExternalId(transaction.getExternalId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        FinancialTransactionResponse response = transactionMapper.toResponse(transactionForTransfer);
        return response;
    }

    @Transactional
    public List<FinancialTransactionResponse> getAllTransactions() {

        List<FinancialTransaction> transactions = repository.findAllByOrderByCreatedAtDesc();

        if (transactions == null) {
            throw new RuntimeException("Transaction not found");
        }

        return transactions.stream()
                .map(transactionMapper::toResponse)
                .toList();
    }

}
