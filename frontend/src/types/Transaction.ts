export type TransactionStatus = "PENDING" | "COMPLETED" | "FAILED";
export type TransactionType = "DEPOSIT" | "WITHDRAWAL" | "TRANSFER" | "PAYMENT" | "REFUND";
export type Currency = "USD" | "EUR" | "GBP"

export interface Transaction {
    id: string;
    externalId: string;
    amount: number;
    currency: Currency;
    status: TransactionStatus;
    type: TransactionType;
    createdAt: string;
    description: string;
}