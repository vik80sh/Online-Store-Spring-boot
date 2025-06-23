package com.electronic.store.helper;

import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    public static <U,V>PageableResponse<V> getPageableResponse(Page<U> page, Class<V> classType){
        List<U> entity= page.getContent();

        List<V> dtoList= entity.stream().map(object->new ModelMapper().map(object, classType)).collect(Collectors.toList());
        PageableResponse<V> pageableResponse= new PageableResponse<>();
        pageableResponse.setContent(dtoList);
        pageableResponse.setPageNumber(page.getNumber());
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setTotalElements(page.getTotalElements());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setLastPage(page.isLast());

        return pageableResponse;
    }
}
