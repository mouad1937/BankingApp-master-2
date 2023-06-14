package com.codecrackers.bankapi.controller;

import com.codecrackers.bankapi.model.Bill;
import com.codecrackers.bankapi.service.BillService;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/accounts/{accountId}")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/bills")
    public ResponseEntity<Bill> createBill(@PathVariable Long accountId, @RequestBody Bill bill) {
        return new ResponseEntity<>(billService.addBill(accountId, bill), HttpStatus.CREATED);
    }

    @GetMapping("/bills")
    public ResponseEntity<List<Bill>> getAllBills(@PathVariable Long accountId) {
        return new ResponseEntity<>(billService.getAllBillForASpecificAccount(accountId), HttpStatus.OK);
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<Void> updateBill(@PathVariable Long accountId, @PathVariable Long billId, @RequestBody Bill bill) {
        billService.updateBill(accountId, billId, bill);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        return new ResponseEntity<>(billService.getBillById(billId),HttpStatus.OK);
    }
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<Void> removeBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
