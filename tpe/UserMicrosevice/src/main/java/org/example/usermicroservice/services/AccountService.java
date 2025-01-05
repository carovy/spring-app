package org.example.usermicroservice.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.usermicroservice.entities.Account;
import org.example.usermicroservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service("AccountService")
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    public Account save(Account account){
        this.accountRepository.save(account);
        return account;
    }

    public void delete(Account account){

        accountRepository.delete(account);
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    @Transactional
    public Account setAccountAnullated(Long id, boolean status) {
        Account ac = this.getAccountById(id);
        this.accountRepository.setAccountAnullated(id, status);
        ac.setAnullated(true);
        return ac;
        }

        @Transactional
    public Account cargarDinero(Long idCuenta, int monto) {
        Account ac = this.getAccountById(idCuenta);
        this.accountRepository.setNuevoMonto(idCuenta, monto);
        ac.setBalance(ac.getBalance() + monto);
        return ac;
    }
}
