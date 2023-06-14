package com.codecrackers.bankapi.service;

import com.codecrackers.bankapi.errorHandler.AddressNotFoundException;
import com.codecrackers.bankapi.exception.ResourceNotFoundException;
import com.codecrackers.bankapi.model.Account;
import com.codecrackers.bankapi.model.Bill;
import com.codecrackers.bankapi.repository.AccountRepository;
import com.codecrackers.bankapi.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private static final Logger logger = LoggerFactory.getLogger(BillService.class);

    private final BillRepository billRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public BillService(BillRepository billRepository, AccountRepository accountRepository) {
        this.billRepository = billRepository;
        this.accountRepository = accountRepository;
    }

    private void verifyBill(Long billId) throws AddressNotFoundException {
        if (!(this.billRepository.existsById(billId))) {
            logger.error("Error fetching bills with ID {}", billId);
            throw (new ResourceNotFoundException(
                    "code : 404 ,"+ "\n"+
                    "Error fetching bills with ID " + billId));
        }
    }



    //createABill
    public Bill addBill(Long id, Bill bill) {
        Account account = accountRepository.findById(id).orElse(null);
        bill.setAccount(account);
        logger.info("Adding a bill: {}", bill);
        return billRepository.save(bill);
    }

    //get all bills for a specific account using its id
    public List<Bill> getAllBillForASpecificAccount(Long id) {
        try {
            logger.info("Fetching all bills for account ID {}", id);
            return billRepository.findAllBillsByAccountId(id);
        } catch (Exception e) {
            logger.error("Error fetching bills for account ID {}: {}", id, e.getMessage());
            throw new RuntimeException("code" + ":" + "404" + "," +
                                       " message: " + " error fetching bills}");
        }
    }

    //edit-modify bill
    public void updateBill(Long accountId, Long billId, Bill bill) {
        Account account = accountRepository.findById(accountId).orElse(null);
        Bill bill1= billRepository.findById(billId).orElse(null);
        if (bill1 != null) {
            bill1.setStatus(bill.getStatus());
            bill1.setPayee(bill.getPayee());
            bill1.setNickname(bill.getNickname());
            bill1.setCreationDate(bill.getCreationDate());
            bill1.setPaymentDate(bill.getPaymentDate());
            bill1.setRecurringDate(bill.getRecurringDate());
            bill1.setPaymentDate(bill.getPaymentDate());
            bill1.setUpcomingPaymentDate(bill.getUpcomingPaymentDate());
            bill1.setPaymentAmount(bill.getPaymentAmount());
        }
            bill.setAccount(account);
               billRepository.save(bill);
    }

    public void  deleteBill(Long billId) {
        logger.info("Deleting bill with ID {}", billId);
        billRepository.findById(billId).orElseThrow(()->new ResourceNotFoundException(String.format("code : 404 , message: This id does not exist in bills")));
    try {
           billRepository.deleteById(billId);
       }catch (Exception e) {
           throw new RuntimeException("Fail deleting the bill");
       }
    }




    public Bill getBillById(Long id) {
        logger.info("Fetching bill with ID {}", id);
        return billRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(String.format(
                        "code :"+404+ ","+
                        "message :" +"error fetching bill with id:" +id
))
        );
    }

}
