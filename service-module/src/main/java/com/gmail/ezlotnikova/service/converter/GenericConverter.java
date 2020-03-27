package com.gmail.ezlotnikova.service.converter;

public interface GenericConverter<DTO, T> {

    T getDatabaseObjectFromDTO(DTO dto);

    DTO getDTOFromDatabaseObject(T databaseObject);

}