package com.devtiro.LibraryCrud.Mappers.impl;

import com.devtiro.LibraryCrud.Mappers.Mapper;
import com.devtiro.LibraryCrud.domain.BookEntity;
import com.devtiro.LibraryCrud.domain.Dto.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {
    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookEntity mapToEntity(BookDto bookDto) {
        BookEntity entity = modelMapper.map(bookDto, BookEntity.class);

        if (bookDto.getImageBase64() != null) {
            entity.setImage(Base64.getDecoder().decode(bookDto.getImageBase64()));
        }
        return entity;
    }

    @Override
    public BookDto mapToDto(BookEntity bookEntity) {
        BookDto dto = modelMapper.map(bookEntity, BookDto.class);
        if (bookEntity.getImage() != null) {
            dto.setImageBase64(Base64.getEncoder().encodeToString(bookEntity.getImage()));
        }
        return dto;
    }

}
