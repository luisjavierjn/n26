package com.n26.domain.service;

import com.n26.domain.data.Repository;
import com.n26.domain.dto.StatisticsDTO;
import com.n26.domain.dto.TransactionsDTO;
import com.n26.exceptions.FutureDateOrInvalidParseException;
import com.n26.exceptions.TransactionTooOld;
import com.n26.util.Formula;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component("transactionService")
public class TransactionServiceImpl implements ITransactionService {

    @Override
    public void add(TransactionsDTO aTransactionsDTO) throws FutureDateOrInvalidParseException, TransactionTooOld {
        BigDecimal bd;
        Instant dateTransaction;
        Instant now = Instant.now();

        try {
            bd = new BigDecimal(aTransactionsDTO.getAmount());
            dateTransaction = Instant.parse(aTransactionsDTO.getTimestamp());
        } catch (Exception e) {
            throw new FutureDateOrInvalidParseException("");
        }

        long diffSeg = now.getEpochSecond() - dateTransaction.getEpochSecond();

        if(diffSeg < 0) {
            throw new FutureDateOrInvalidParseException("");
        }
        else if(diffSeg >= 60) {
            throw new TransactionTooOld("");
        }

        String key = aTransactionsDTO.getAmount() + "_" +  aTransactionsDTO.getTimestamp();
        Repository.getInstance().add(key,aTransactionsDTO);
    }

    @Override
    public StatisticsDTO get() {
        StatisticsDTO stat = new StatisticsDTO();
        List<TransactionsDTO> transs = Repository.getInstance().get();

        if(transs.size() > 0) {
            stat.setSum(Formula.Sum(transs));
            stat.setAvg(Formula.Avg(transs));
            stat.setMax(Formula.Max(transs));
            stat.setMin(Formula.Min(transs));
            stat.setCount(Formula.Count(transs));
        }

        return stat;
    }

    @Override
    public void del() {
        Repository.getInstance().del();
    }
}
