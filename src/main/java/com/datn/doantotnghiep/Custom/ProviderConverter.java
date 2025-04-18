package com.datn.doantotnghiep.Custom;

import com.datn.datnai.Model.Users;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//import java.security.Provider;

@Converter(autoApply = true)
public class ProviderConverter implements AttributeConverter<Users.Provider, String> {

    @Override
    public String convertToDatabaseColumn(Users.Provider provider) {
        return provider == null ? null : provider.name().toLowerCase();
    }



    @Override
    public Users.Provider convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Users.Provider.valueOf(dbData.toUpperCase());
    }
}
