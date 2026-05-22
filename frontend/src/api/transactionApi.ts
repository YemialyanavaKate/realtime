import axios from "axios";
import type { Transaction } from "../types/Transaction";

const api = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true
});

export const transactionApi = {

    getAllTransactions: async (): Promise<Transaction[]> => {
        const response = await api.get<Transaction[]>("/transactions");
        return response.data;
    }
};
