package com.codecrackers.bankapi.service;

import com.codecrackers.bankapi.enumeration.TransactionType;
import com.codecrackers.bankapi.exception.ResourceNotFoundException;
//import com.codecrackers.bankapi.model.Deposit;
import com.codecrackers.bankapi.model.Transaction;
import com.codecrackers.bankapi.repository.AccountRepository;
import com.codecrackers.bankapi.repository.BillRepository;
import com.codecrackers.bankapi.repository.CustomerRepository;
import com.codecrackers.bankapi.repository.TransactionRepository;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    protected void verifyAccount(Long accountId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.accountRepository.existsById(accountId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    protected void verifyBill(Long billId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.billRepository.existsById(billId))){
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }

    protected void verifyCustomer (Long customerId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    protected void verifyTransaction(Long transactionId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.transactionRepository.existsById(transactionId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }


    public Transaction getTransactionById(Long transactionId, String exceptionMessage) {
        this.verifyTransaction(transactionId, exceptionMessage);
        return this.transactionRepository.findById(transactionId).get();
    }

    public Transaction createTransaction(Long accountId, String exceptionMessage, TransactionCreateDTO transactionCreateDTO) {
        this.verifyAccount(accountId, exceptionMessage);
        LocalDate today = LocalDate.now();
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.fromString(transactionCreateDTO.getType()));
        transaction.setStatus(TransactionStatus.fromString(transactionCreateDTO.getStatus()));
        transaction.setMedium(TransactionMedium.fromString(transactionCreateDTO.getMedium()));
        transaction.setAmount(transactionCreateDTO.getAmount());
        transaction.setDescription(transactionCreateDTO.getDescription());
        transaction.setTransactionDate(today);
        transaction.setAccount(this.accountRepository.findById(accountId).get());
        return this.transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long transactionId, String exceptionMessage, TransactionUpdateDTO transactionUpdateDTO) {
        this.verifyTransaction(transactionId, exceptionMessage);
        Transaction transactionToUpdate = this.transactionRepository.findById(transactionId).get();
        if (!(Objects.isNull(transactionUpdateDTO.getStatus())) && !(transactionUpdateDTO.getStatus().isBlank())) {
            transactionToUpdate.setStatus(TransactionStatus.fromString(transactionUpdateDTO.getStatus()));
        }
        if (!(Objects.isNull(transactionUpdateDTO.getDescription())) && !(transactionUpdateDTO.getDescription().isBlank())) {
            transactionToUpdate.setDescription(transactionUpdateDTO.getDescription());
        }
        return this.transactionRepository.save(transactionToUpdate);
    }

    public void deleteTransaction(Long transactionId, String exceptionMessage) {
        this.verifyTransaction(transactionId, exceptionMessage);
        this.transactionRepository.deleteById(transactionId);
    }

    public Iterable<Transaction> getAllTransactionsByAccountId(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        return this.transactionRepository.findAllTransactionsByAccountId(accountId);
    }
}










}
