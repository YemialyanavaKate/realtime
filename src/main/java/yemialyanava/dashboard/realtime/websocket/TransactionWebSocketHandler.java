package yemialyanava.dashboard.realtime.websocket;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.concurrent.CopyOnWriteArraySet;

//import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import yemialyanava.dashboard.realtime.dto.FinancialTransactionResponse;

@Slf4j
@Component
// @RequiredArgsConstructor
public class TransactionWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("New WebSocket connection has been opened. Session ID: {}", session.getId());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        log.info("WebSocket connection has been closed. Session ID: {}, Status: {}", session.getId(), status);
    }

    public void sendMessage(FinancialTransactionResponse transaction) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(transaction);
            TextMessage message = new TextMessage(jsonPayload);

            log.info("Sending transaction {} to {} active clients", transaction.id(), sessions.size());

            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(message);
                    } catch (Exception e) {
                        log.error("Error sending message to session {}", session.getId(), e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error serializing transaction to JSON", e);
        }
    }
}
