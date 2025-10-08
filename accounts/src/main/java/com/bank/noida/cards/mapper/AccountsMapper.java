package com.bank.noida.cards.mapper;

import com.bank.noida.cards.dto.AccountsDTO;
import com.bank.noida.cards.entity.Accounts;
import org.springframework.stereotype.Component;

@Component
public class AccountsMapper {

    // DTO -> Entity
    public static Accounts toEntity(AccountsDTO dto) {
        if (dto == null) return null;

        Accounts accounts = new Accounts();
        accounts.setAccountNumber(dto.getAccountNumber());
        accounts.setAccountType(dto.getAccountType());
        accounts.setBranchAddress(dto.getBranchAddress());
        return accounts;
    }

    // Entity -> DTO
    public static AccountsDTO toDTO(Accounts entity) {
        if (entity == null) return null;

        AccountsDTO dto = new AccountsDTO();
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setAccountType(entity.getAccountType());
        dto.setBranchAddress(entity.getBranchAddress());
        return dto;
    }
}
