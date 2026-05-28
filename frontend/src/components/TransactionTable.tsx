import React from "react";
import { Chip, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from "@mui/material";
import type { Transaction, TransactionStatus, TransactionType } from "../types/Transaction";

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
};

const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    return date.toLocaleTimeString() + ' ' + date.toLocaleDateString();
};

export const TransactionTable: React.FC<TransactionTableProps> = ({ transactions }) => {

    if (transactions.length === 0) {
        return (
            <Typography variant="body1" sx={{ textAlign: 'center', my: 4, color: 'text.secondary' }}>

            </Typography>
        )
    }

    return (

        <TableContainer component={Paper} sx={{ maxHeight: 600, borderRadius: 2, }}>
            <Table stickyHeader aria-label='transaction table'>
                <TableHead>
                    <TableRow>
                        <TableCell><strong>ID</strong></TableCell>
                        <TableCell><strong>ExternalID</strong></TableCell>
                        <TableCell align="right"><strong>Amount</strong></TableCell>
                        <TableCell><strong>Currency</strong></TableCell>
                        <TableCell><strong>Status</strong></TableCell>
                        <TableCell><strong>Type</strong></TableCell>
                        <TableCell><strong>Created At</strong></TableCell>
                        <TableCell><strong>Description</strong></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {transactions.map((tx) => (
                        <TableRow
                            key={tx.id}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell>{tx.id}</TableCell>
                            <TableCell sx={{ fontFamily: 'monospace', fontWeight: 'bold' }}>{tx.externalId}</TableCell>
                            <TableCell align="right">{tx.amount.toFixed(2)}</TableCell>
                            <TableCell>{tx.currency}</TableCell>
                            <TableCell>{getStatusChip(tx.status)}</TableCell>
                            <TableCell><Chip label={tx.type} size="small" /></TableCell>
                            <TableCell>{formatDate(tx.createdAt)}</TableCell>
                            <TableCell sx={{ color: 'text.secondary', maxWidth: 200 }}>{tx.description}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};