package com.devtiro.LibraryCrud.Mappers;

public interface Mapper <A,B>{
    A mapToEntity(B b);
    B mapToDto(A a);

}
