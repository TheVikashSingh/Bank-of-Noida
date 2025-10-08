package com.bank.noida.cards.mapper;

import com.bank.noida.cards.dto.CustomerDTO;
import com.bank.noida.cards.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    // DTO -> Entity
    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setMobileNumber(dto.getMobileNumber());
        return customer;
    }

    // Entity -> DTO
    public static CustomerDTO toDTO(Customer entity) {
        if (entity == null) return null;

        CustomerDTO dto = new CustomerDTO();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setMobileNumber(entity.getMobileNumber());
        return dto;
    }



}
