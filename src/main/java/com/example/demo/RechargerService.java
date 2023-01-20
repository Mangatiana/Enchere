package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class RechargerService {
    @Autowired
    private RechargerRepository accountRepository;

    @Transactional 
    public void rechargeAccount(Recharger account) {
        accountRepository.save(account);
    }

}
