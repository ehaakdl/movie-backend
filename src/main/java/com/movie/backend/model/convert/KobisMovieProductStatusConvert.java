package com.movie.backend.model.convert;

import com.movie.backend.model.entity.movie.eKobisMovieProductStatus;

import jakarta.persistence.AttributeConverter;

public class KobisMovieProductStatusConvert implements AttributeConverter<eKobisMovieProductStatus, String>{

    // Enum > db 
    @Override
    public String convertToDatabaseColumn(eKobisMovieProductStatus attribute) {
        return attribute.name();
    }

    // db > Enum
    @Override
    public eKobisMovieProductStatus convertToEntityAttribute(String dbData) {
        return eKobisMovieProductStatus.valueOf(dbData);
    }
}
