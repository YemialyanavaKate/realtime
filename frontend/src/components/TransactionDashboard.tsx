import { useCallback, useEffect, useState } from "react";
import { transactionApi } from "../api/transactionApi";
import { Box, Card, Container, Typography } from "@mui/material";
import type { Transaction } from "../types/Transaction";
import { useTransactionWebSocket } from "../hooks/useTransactionWebSocket";
import { TransactionTable } from "./TransactionTable";

export const TransactionDashboard = () => {
    const [transactions, setTransactions] = useState<Transaction[]>([]);

    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadInitialHistory = async () => {
            try {
                const data = await transactionApi.getAllTransactions();
                setTransactions(data);
            } catch (err) {
                console.error("Failed to load transaction history:", err);
                setError("Failed to load data from server");
            }
        };

        loadInitialHistory();
    }, []);

    const handleNewTransaction = useCallback((newTx: Transaction) => {
        console.log("New transaction received:", newTx);
        setTransactions((prevTransactions) => {
            const exists = prevTransactions.some((tx) => tx.id === newTx.id);
            if (exists) {
                return prevTransactions;
            }
            return [newTx, ...prevTransactions];
        });
    }, []);

    const isWebSocketConnected = useTransactionWebSocket(handleNewTransaction);

    return (
        <Container maxWidth="xl" sx={{ mt: 4, mb: 4 }}>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 4 }}>
                <Box>
                    <Typography variant="h4" component="h1" sx={{ fontWeight: 'bold', letterSpacing: 1 }}>
                        TRANSACTION MONITOR
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        Data flow audit for real-time transactions
                    </Typography>
                </Box>
                <Card sx={{ px: 2, py: 1, borderRadiys: 3, display: 'flex', alignItems: 'center', gap: 1.5, boxShadow: 2 }}>
                    <Box
                        sx={{
                            width: 12,
                            height: 12,
                            borderRadius: '50%',
                            backgroundColor: isWebSocketConnected ? '#2e7d32' : '#d32f2f',
                            boxShadow: isWebSocketConnected ? '0 0 8px #2e7d32' : '0 0 8px #d32f2f',
                        }}
                    />
                    <Typography variant="subtitle2" sx={{ fontWeight: 'bold' }}>
                        {isWebSocketConnected ? 'LIVE STREAM CONNECTED' : 'DISCONNECTED'}
                    </Typography>
                </Card>
            </Box>

            {error && (
                <Typography
                    color="error" sx={{ mb: 2, textAlign: 'center', fontWeight: 'bold' }}
                >
                    {error}
                </Typography>
            )}
            <TransactionTable transactions={transactions} />
        </Container>
    );
};
