package com.example.krpizzaPrj.mappers;


import com.example.krpizzaPrj.dto.AddItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ViewItemMapper {


    @Select("SELECT * FROM items WHERE category_code = 1")
    List<AddItemDto> viewPremium();


    @Select("SELECT * FROM items WHERE category_code = 2")
    List<AddItemDto> viewOriginal();

    @Select("SELECT * FROM items WHERE category_code = 3")
    List<AddItemDto> viewSide();

    @Select("SELECT * FROM items WHERE category_code = 4")
    List<AddItemDto> viewDrink();
}
