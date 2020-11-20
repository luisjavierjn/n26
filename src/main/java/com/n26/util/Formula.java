package com.n26.util;

import com.n26.domain.dto.TransactionsDTO;

import java.math.BigDecimal;
import java.util.List;

public class Formula {

    public static BigDecimal Sum(List<TransactionsDTO> aTransactionsDTOList) {
        return Sumatoria(aTransactionsDTOList);
    }

    public static BigDecimal Avg(List<TransactionsDTO> aTransactionsDTOList) {
        BigDecimal bd = Sumatoria(aTransactionsDTOList);
        return bd.divide(new BigDecimal(aTransactionsDTOList.size()),2,BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal Max(List<TransactionsDTO> aTransactionsDTOList) {
        BigDecimal max = new BigDecimal("0").setScale(2,BigDecimal.ROUND_HALF_UP);
        int result = 0;
        for(TransactionsDTO valor: aTransactionsDTOList){
            BigDecimal val = new BigDecimal(valor.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            result = max.compareTo(val);
            if(result == -1){
                max = new BigDecimal(valor.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
        }
        return max;
    }

    public static BigDecimal Min(List<TransactionsDTO> aTransactionsDTOList) {
        BigDecimal min = new BigDecimal("0");
        if(null != aTransactionsDTOList && aTransactionsDTOList.size() > 0) {
            min = new BigDecimal(aTransactionsDTOList.get(0).getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            int result = 0;
            for (TransactionsDTO valor : aTransactionsDTOList) {
                BigDecimal val = new BigDecimal(valor.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                result = min.compareTo(val);
                if (result != -1) {
                    min = new BigDecimal(valor.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                }
            }
        }
        return min;
    }

    public static long Count(List<TransactionsDTO> aTransactionsDTOList) {
        long bd = aTransactionsDTOList.size();
        return bd;
    }

    public static BigDecimal Sumatoria(List<TransactionsDTO> aTransactionsDTOList) {
        BigDecimal result = new BigDecimal("0").setScale(2,BigDecimal.ROUND_HALF_UP);
        for(TransactionsDTO valor: aTransactionsDTOList){
            BigDecimal val = new BigDecimal(valor.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            result = result.add(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        return result;
    }

}