package com.gmail.ezlotnikova.service.converter;

public interface GenericConverter<S, T> {

    S convertDatabaseObjectToDTO(T databaseObject);

    T convertDTOtoDatabaseObject(S DTO);

}