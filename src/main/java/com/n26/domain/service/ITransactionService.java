package com.n26.domain.service;

import com.n26.domain.dto.StatisticsDTO;
import com.n26.domain.dto.TransactionsDTO;
import com.n26.exceptions.FutureDateOrInvalidParseException;
import com.n26.exceptions.TransactionTooOld;

public interface ITransactionService {
    void add(TransactionsDTO aTransactionsDTO) throws FutureDateOrInvalidParseException, TransactionTooOld;

    StatisticsDTO get();

    void del();


}
