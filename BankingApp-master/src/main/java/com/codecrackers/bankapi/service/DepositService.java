package com.codecrackers.bankapi.service;
//
//import com.codecrackers.bankapi.exception.ResourceNotFoundException;
//import com.codecrackers.bankapi.model.Account;
//import com.codecrackers.bankapi.model.Deposit;
//import com.codecrackers.bankapi.repository.AccountRepository;
//import com.codecrackers.bankapi.repository.DepositRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DepositService {
//
//    private final DepositRepository depositRepository;
//    private final AccountRepository accountRepository;
//
//    @Autowired
//    public DepositService(DepositRepository depositRepository, AccountRepository accountRepository) {
//        this.depositRepository = depositRepository;
//        this.accountRepository = accountRepository;
//    }
//
//    public void verifyDeposit(Long accountId, Long depositsId) throws ResourceNotFoundException {
//        if(!(this.accountRepository.existsById(accountId))){
//            throw (new ResourceNotFoundException("Account Not Found"));
//        } if (!(this.depositRepository.existsById(depositsId))) {
//            throw (new ResourceNotFoundException("error fetching deposit with id" + depositsId));
//        }
//    }
//
//    public Deposit addDeposit(Long accountId, Deposit deposit) {
//        Account account = accountRepository.findById(accountId).orElseThrow(
//                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
//        );
//        account.balance += deposit.getAmount();
//        accountRepository.save(account);
//        deposit.setAccount(account);
//        return depositRepository.save(deposit);
//    }
//
//    public List<Deposit> getAllDepositsByAccountId(Long accountId) {
//        return depositRepository.findAllDepositsByAccountId(accountId);
//    }
//
////public void update(Long accountId, Long depositId ,Deposit deposit){
////    Account account = accountRepository.findById(accountId).orElse(null);
////     Deposit d = depositRepository.findById(depositId).orElse(null);
////     if(d != null){
////         d.setType(deposit.getType());
////         d.setTransaction_date(deposit.getTransaction_date());
////         d.setStatus(deposit.getStatus());
////         d.setPayee_id(deposit.getPayee_id());
////         d.setMedium(deposit.getMedium());
////         d.setAmount(deposit.getAmount());
////         d.setDescription(deposit.getDescription());
////     }
////      deposit.setAccount(account);
////     depositRepository.save(deposit);
////}
//
//    public void deleteDeposit(Long id) {
//        depositRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException(String.format("Deposit with ID %s not found", id))
//        );
//        try {
//            depositRepository.deleteById(id);
//        } catch (Exception exception) {
//            throw new RuntimeException("Error deleting deposit");
//        }
//    }
//
//    public Deposit getDepositById(Long id) {
//        return depositRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException(String.format("Deposit with ID %s not found", id))
//        );
//    }
//
//}
