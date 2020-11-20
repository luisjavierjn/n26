package com.n26.controller;

import com.n26.domain.dto.StatisticsDTO;
import com.n26.domain.dto.TransactionsDTO;
import com.n26.domain.service.ITransactionService;
import com.n26.exceptions.FutureDateOrInvalidParseException;
import com.n26.exceptions.TransactionTooOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    @Qualifier("transactionService")
    ITransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST, value = "/transactions")
    public ResponseEntity<?> add(@RequestBody TransactionsDTO aTransactionsDTO) {
        if(!Optional.ofNullable(aTransactionsDTO).isPresent()
                || !Optional.ofNullable(aTransactionsDTO.getAmount()).isPresent()
                || !Optional.ofNullable(aTransactionsDTO.getTimestamp()).isPresent()) {
            System.out.println("the JSON is invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            transactionService.add(aTransactionsDTO);
        } catch (FutureDateOrInvalidParseException e) {
            System.out.println("fields are not parsable or the transaction date is in the future: " + e.toString());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TransactionTooOld e2) {
            System.out.println("transaction is older than 60 seconds: " + e2.toString());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsDTO> get() {
        StatisticsDTO stat = transactionService.get();
        return new ResponseEntity<>(stat, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/transactions")
    public ResponseEntity<?> del() {
        transactionService.del();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
