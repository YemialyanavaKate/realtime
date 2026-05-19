package yemialyanava.dashboard.realtime.service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import yemialyanava.dashboard.realtime.dto.FinancialTransactionRequest;
import yemialyanava.dashboard.realtime.dto.FinancialTransactionResponse;
import yemialyanava.dashboard.realtime.enums.Currency;
import yemialyanava.dashboard.realtime.enums.TransactionType;
import yemialyanava.dashboard.realtime.websocket.TransactionWebSocketHandler;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionSimulatorService {

    private final FinancialTransactionService financialTransactionService;
    private final TransactionWebSocketHandler transactionWebSocketHandler;
    private final Random random = new Random();

    public void generateSimulateTransaction() {
        log.info("Generating simulated transaction");

        UUID externalId = UUID.randomUUID();

        BigDecimal amount = BigDecimal.valueOf(random.nextInt(1000));

        Currency currency = Currency.values()[random.nextInt(Currency.values().length)];

        TransactionType type = TransactionType.values()[random.nextInt(TransactionType.values().length)];

        String description = "Simulated " + type.toString() + " transaction ";

        FinancialTransactionRequest request = new FinancialTransactionRequest(
                externalId,
                amount,
                currency,
                type,
                description);

        try {
            FinancialTransactionResponse response = financialTransactionService.processTransaction(request);
            transactionWebSocketHandler.sendMessage(response);
        } catch (Exception e) {
            log.error("Error processing simulated transaction", e);
        }

    }
}
