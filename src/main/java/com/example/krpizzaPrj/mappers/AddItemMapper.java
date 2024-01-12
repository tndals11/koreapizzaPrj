package com.example.krpizzaPrj.mappers;

import com.example.krpizzaPrj.dto.AddItemDto;
import com.example.krpizzaPrj.dto.CategoryDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddItemMapper {
    // 카테고리 번호 생성
    @Select("SELECT * FROM category ORDER BY category_code ASC")
    List<CategoryDto> addCategory();
    
    // 아이템 정보입력
    @Insert("INSERT INTO items VALUES(NULL, #{categoryCode}, #{itemName}, #{itemPrice}, #{itemIntro}, 0, NOW(), #{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, #{folderName}, #{ext}, #{grp}, 1, 1)")
    void setAddItem(AddItemDto addItemDto);
    
    // 아이템 리스트 정렬
    @Select("SELECT * FROM items ${searchQuery} ORDER BY category_code ASC LIMIT #{startNum}, #{offset}")
    List<AddItemDto> getAddItem( Map<String, Object> map );

    // 아이템 리스트 등록된 개수
    @Select("SELECT COUNT(*) FROM items ${searchQuery}")
    int countAddItem( Map<String, Object> map);

    // 아이템 리스트 상세보기
    @Select("SELECT * FROM items WHERE item_name = #{itemName}")
    AddItemDto getItemView(String itemName);

    // 아이템 수정하기
    @Select("SELECT * FROM items WHERE item_id = #{itemId}")
    AddItemDto getUpdateView(String itemId);

    // 아이템 리스트 삭제
    @Delete("DELETE FROM items WHERE item_name = #{itemName}")
    void getItemDelete(String itemName);

    @Update("UPDATE items SET category_code=#{categoryCode}, item_name=#{itemName}, item_price=#{itemPrice}, item_intro=#{itemIntro}, regdate = now(), orgName=#{orgName}, savedFileName=#{savedFileName}, savedFilePathName=#{savedFilePathName}, savedFileSize=#{savedFileSize},folderName=#{folderName} WHERE item_id=#{itemId}")
    void setItemUpdate(AddItemDto addItemDto);
}

