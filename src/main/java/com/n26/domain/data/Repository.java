package com.n26.domain.data;

import com.n26.domain.dto.TransactionsDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Repository {

    private ConcurrentHashMap<String, TransactionsDTO> mapConcurrente = new ConcurrentHashMap<String, TransactionsDTO>();

    private static Repository repository = new Repository( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private Repository() { }

    /* Static 'instance' method */
    public static Repository getInstance( ) {
        return repository;
    }

    /* Other methods protected by repository-ness */
    public void add(String key, TransactionsDTO aTransactionsDTO) {
        mapConcurrente.put(key,aTransactionsDTO);
    }

    /* Transactions in the last 60 seconds */
    public List<TransactionsDTO> get() {
        List<TransactionsDTO> result = new ArrayList<>();

        for (Map.Entry<String, TransactionsDTO> entry : mapConcurrente.entrySet()) {
            TransactionsDTO value = entry.getValue();
            Instant oldDate = Instant.parse(value.getTimestamp());
            Instant date = Instant.now();
            long diffSeg = date.getEpochSecond() - oldDate.getEpochSecond();

            if(diffSeg < 60) {
                result.add(value);
            }
        }

        return result;
    }

    public void del() {
        mapConcurrente.clear();
    }
}
