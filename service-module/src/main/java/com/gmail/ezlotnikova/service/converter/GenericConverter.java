package com.gmail.ezlotnikova.service.converter;

public interface GenericConverter<DTO, T> {

    DTO getDatabaseObjectFromDTO(T databaseObject);

    T getDTOFromDatabaseObject(DTO dto);

}