package yemialyanava.dashboard.realtime.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import yemialyanava.dashboard.realtime.dto.FinancialTransactionRequest;
import yemialyanava.dashboard.realtime.dto.FinancialTransactionResponse;

import yemialyanava.dashboard.realtime.service.FinancialTransactionService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FinancialTransactionController {

    private final FinancialTransactionService service;

    @PostMapping("/transaction")
    public ResponseEntity<FinancialTransactionResponse> createTransaction(
            @RequestBody FinancialTransactionRequest request) {

        FinancialTransactionResponse transactionResponse = service.processTransaction(request);
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<FinancialTransactionResponse> getTransaction(@PathVariable Long id) {

        FinancialTransactionResponse transactionResponse = service.getTransactionByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<FinancialTransactionResponse>> getTransactions() {

        List<FinancialTransactionResponse> transactionsResponse = service.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(transactionsResponse);
    }

}
