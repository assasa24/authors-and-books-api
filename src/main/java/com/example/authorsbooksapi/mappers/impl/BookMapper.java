package com.example.authorsbooksapi.mappers.impl;

import com.example.authorsbooksapi.domain.dto.BookDto;
import com.example.authorsbooksapi.domain.entities.BookEntity;
import com.example.authorsbooksapi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {

    private ModelMapper modelMapper ;

    public BookMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
