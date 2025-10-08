package com.bank.noida.cards.service.impl;

import com.bank.noida.cards.constants.AccountsConstants;
import com.bank.noida.cards.dto.AccountsDTO;
import com.bank.noida.cards.dto.CustomerDTO;
import com.bank.noida.cards.entity.Accounts;
import com.bank.noida.cards.entity.Customer;
import com.bank.noida.cards.exception.CustomerAlreadyExistsException;
import com.bank.noida.cards.exception.ResourceNotFoundException;
import com.bank.noida.cards.mapper.AccountsMapper;
import com.bank.noida.cards.mapper.CustomerMapper;
import com.bank.noida.cards.repository.AccountsRepository;
import com.bank.noida.cards.repository.CustomerRepository;
import com.bank.noida.cards.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountsMapper accountsMapper;

    /**
     *
     * @param customerDTO
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);

        Optional<Customer> optionalCustomer =
                customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered" +
                    " with the provided mobile number: "+customerDTO.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    public Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        // Generate a random account number
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);

        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }


    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDTO getAccountDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber.trim()).orElseThrow(
                () ->new ResourceNotFoundException("Customer","Mobile Number",String.valueOf(mobileNumber))
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () ->new ResourceNotFoundException("Account","Customer ID: ",customer.getCustomerId().toString())
        );
        CustomerDTO customerDTO = customerMapper.toDTO(customer);
        customerDTO.setAccountsDTO(accountsMapper.toDTO(accounts));
        return customerDTO;
    }

    /**
     *
     * @param customerDTO
     * @return
     */
    @Override
    public boolean updateAccounts(CustomerDTO customerDTO) {

        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();

        if(accountsDTO !=null){
            Accounts accounts = accountsRepository.findById(accountsDTO.getAccountNumber())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Accounts","Account Number",accountsDTO.getAccountNumber().toString())
                    );
            accounts.setAccountType(accountsDTO.getAccountType());
            accounts.setBranchAddress(accountsDTO.getBranchAddress());
            accounts = accountsRepository.save(accounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer","Customer ID",customerId.toString())
            );
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customer.setMobileNumber(customerDTO.getMobileNumber());
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
            Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
            );

            accountsRepository.deleteByCustomerId(customer.getCustomerId());
            customerRepository.deleteById(customer.getCustomerId());
            return true;
    }

}
