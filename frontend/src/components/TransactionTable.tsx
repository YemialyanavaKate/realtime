import { Chip } from "@mui/material";
import type { Transaction, TransactionStatus } from "../types/Transaction";

interface TransactionTableProps {
    transactions: Transaction[];
}

const getStatusChip = (status: TransactionStatus) => {
    switch (status) {
        case "COMPLETED":
            return <Chip label="COMPLETED" color="success" variant="outlined" size="small" />;
        case "PENDING":
            return <Chip label="PENDING" color="warning" variant="outlined" size="small" />;
        case "FAILED":
            return <Chip label="FAILED" color="error" variant="outlined" size="small" />;
        default:
            return <Chip label={status} color="default" variant="outlined" size="small" />;
    }
}