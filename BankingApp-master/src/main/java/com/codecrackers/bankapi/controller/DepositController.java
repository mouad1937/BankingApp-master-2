package com.codecrackers.bankapi.controller;

//import com.codecrackers.bankapi.model.Deposit;
//import com.codecrackers.bankapi.service.DepositService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class DepositController {
//
//    private final DepositService depositService;
//
//    @Autowired
//    public DepositController(DepositService depositService) {
//        this.depositService = depositService;
//    }
//
//    @PostMapping("/accounts/{accountId}/deposits")
//    private ResponseEntity<Deposit> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit) {
//        return new ResponseEntity<>(depositService.addDeposit(accountId, deposit), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/accounts/{accountId}/deposits")
//    private ResponseEntity<List<Deposit>> getAllDepositsByAccountId(@PathVariable Long accountId) {
//        return new ResponseEntity<>(depositService.getAllDepositsByAccountId(accountId), HttpStatus.OK);
//    }
//
////@PutMapping("/account/{accountId}/deposit/{depositId}")
////private ResponseEntity<Void> updateDeposit(@PathVariable Long accountId, @PathVariable Long depositId , @RequestBody Deposit deposit){
////     depositService.update(accountId, depositId, deposit);
////    return new ResponseEntity<>(HttpStatus.OK);
////
////
////
////}
//
//    @GetMapping("/deposits/{depositId}")
//    private ResponseEntity<Deposit> getDepositById(@PathVariable Long depositId) {
//        return new ResponseEntity<>(depositService.getDepositById(depositId), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deposits/{depositId}")
//    private ResponseEntity<Void> deleteDeposit(@PathVariable Long depositId) {
//        depositService.deleteDeposit(depositId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//}
