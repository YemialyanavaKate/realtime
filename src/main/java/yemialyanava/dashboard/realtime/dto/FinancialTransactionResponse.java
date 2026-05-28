package yemialyanava.dashboard.realtime.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import yemialyanava.dashboard.realtime.enums.Currency;
import yemialyanava.dashboard.realtime.enums.TransactionStatus;
import yemialyanava.dashboard.realtime.enums.TransactionType;

public record FinancialTransactionResponse(
        Long id,
        UUID externalId,
        BigDecimal amount,
        Currency currency,
        TransactionStatus status,
        TransactionType type,
        @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        timezone = "UTC")
        LocalDateTime createdAt,
        String description) {

}
