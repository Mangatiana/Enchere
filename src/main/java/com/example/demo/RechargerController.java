package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class RechargerController {
    @Autowired
    private RechargerService accountService;

    private RechargerRepository accountRepository;

    @PostMapping("/recharge")
    public void rechargeAccount(@RequestBody Recharger accountRechargeDTO) {
        accountService.rechargeAccount(accountRechargeDTO)  ;
    }
}
