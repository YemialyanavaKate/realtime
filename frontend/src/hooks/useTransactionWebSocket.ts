import { useEffect, useRef, useState } from "react";
import type { Transaction } from "../types/Transaction";

export function useTransactionWebSocket(onMessageReceived: (transaction: Transaction) => void) {

    const [isConnected, setIsConnected] = useState<boolean>(false);

    const socketRef = useRef<WebSocket | null>(null);

    useEffect(() => {
        const connect = () => {

            console.log("Attempting to connect to websocket");
            const socket = new WebSocket("ws://localhost:8080/ws/transactions");
            socketRef.current = socket;

            socket.onopen = () => {
                console.log("WebSocket connected");
                setIsConnected(true);
            }

            socket.onmessage = (event) => {
                try {
                    const transaction: Transaction = JSON.parse(event.data);
                    onMessageReceived(transaction);
                } catch (error) {
                    console.error("Error parsing transaction:", error);
                }
            }

            socket.onclose = () => {
                console.warn("WebSocket disconnected, retrying in 5 seconds");
                setIsConnected(false);
                setTimeout(connect, 5000);
            }

            socket.onerror = (error) => {
                console.error("WebSocket error:", error);
                socket.close();
            }

        }
        connect();

        return () => {
            if (socketRef.current) {
                socketRef.current.close();
            }
        }
    }, [onMessageReceived]);

    return isConnected;
}