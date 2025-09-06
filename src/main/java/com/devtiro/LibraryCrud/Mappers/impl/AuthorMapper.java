package com.devtiro.LibraryCrud.Mappers.impl;

import com.devtiro.LibraryCrud.Mappers.Mapper;
import com.devtiro.LibraryCrud.domain.AuthorEntity;
import com.devtiro.LibraryCrud.domain.Dto.AuthorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorEntity, AuthorDto> {
    private ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorEntity mapToEntity(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorEntity.class);
    }

    @Override
    public AuthorDto mapToDto(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }
}
