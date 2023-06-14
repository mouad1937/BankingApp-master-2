package com.codecrackers.bankapi.controller;

import com.codecrackers.bankapi.model.Transaction;
import com.codecrackers.bankapi.model.Transactions;
import com.codecrackers.bankapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/accounts/{accountId}/transactions") // TODO: DONE
    public ResponseEntity<?> getAllTransactionsByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch transactions as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched transactions matching the provided account ID: " + accountId;
        Iterable<Transaction> successResponseData = this.transactionService.getAllTransactionsByAccountId(accountId, exceptionMessage);
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/transactions/{transactionId}") // TODO: DONE
    public ResponseEntity<?> getTransactionById(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to fetch transaction as no transaction was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched transaction matching the provided transaction ID: " + transactionId;
        Transaction successResponseData = this.transactionService.getTransactionById(transactionId, exceptionMessage);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/accounts/{accountId}/transactions") // TODO: DONE
    public ResponseEntity<?>createTransaction(@PathVariable Long accountId, @Valid @RequestBody TransactionCreateDTO transactionCreateDTO) {
        String exceptionMessage = "Unable to create new transaction as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new transaction for account with ID: " + accountId;
        Transaction successResponseData = this.transactionService.createTransaction(accountId, exceptionMessage, transactionCreateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/transactions/{transactionId}") // TODO: DONE
    public ResponseEntity<?> updateTransaction(@PathVariable Long transactionId, @Valid @RequestBody TransactionUpdateDTO transactionUpdateDTO) {
        String exceptionMessage = "Unable to update transaction as no transaction was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated transaction matching the provided transaction ID: " + transactionId;
        Transaction successResponseData = this.transactionService.updateTransaction(transactionId, exceptionMessage, transactionUpdateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/transactions/{transactionId}") // TODO: DONE
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to delete transaction as no transaction was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully deleted transaction matching the provided transaction ID: " + transactionId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.transactionService.deleteTransaction(transactionId, exceptionMessage);
        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}