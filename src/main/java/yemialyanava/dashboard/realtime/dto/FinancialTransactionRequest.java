package yemialyanava.dashboard.realtime.dto;

import java.math.BigDecimal;
import java.util.UUID;

import yemialyanava.dashboard.realtime.enums.Currency;
import yemialyanava.dashboard.realtime.enums.TransactionType;

public record FinancialTransactionRequest(
 
    UUID externalId,
    BigDecimal amount, 
    Currency currency, 
    TransactionType type, 
    String description) {

}
