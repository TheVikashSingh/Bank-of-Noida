package com.bank.noida.cards.service;

import com.bank.noida.cards.dto.CustomerDTO;

public interface IAccountsService {

    /**
     *
     * @param customerDTO
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber
     * @return
     */
    CustomerDTO getAccountDetails(String mobileNumber);


    /**
     *
     * @param customerDTO
     * @return
     */
    boolean updateAccounts(CustomerDTO customerDTO);


    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);

}
