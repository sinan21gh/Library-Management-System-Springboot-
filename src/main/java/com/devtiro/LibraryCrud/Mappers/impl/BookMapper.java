package com.devtiro.LibraryCrud.Mappers.impl;

import com.devtiro.LibraryCrud.Mappers.Mapper;
import com.devtiro.LibraryCrud.domain.BookEntity;
import com.devtiro.LibraryCrud.domain.Dto.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {
    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookEntity mapToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }

    @Override
    public BookDto mapToDto(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

}
