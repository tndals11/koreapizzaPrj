package com.example.krpizzaPrj.mappers;

import com.example.krpizzaPrj.dto.OrdersDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper {
    @Mapper
        @Select("select * from orders")
        public List<OrdersDto> getOrderList();

        @Select("SELECT COUNT(*) FROM orders")
        int totalCount();

        @Select("SELECT COUNT(*) FROM orders WHERE user_id = #{userId}")
        int OrderTotalCount(String userId);

        @Select("SELECT * FROM orders ORDER BY id DESC LIMIT #{startNum}, #{offset}")
        List<OrdersDto> getAddItem(Map<String, Object> map);

        @Select("SELECT a.id, a.itemNameAndCount, a.inquiry, a.total_price, a.order_date, b.review_st FROM orders a LEFT JOIN review b on a.id = b.id WHERE a.user_id = #{userId} ORDER BY a.id DESC LIMIT #{startNum}, #{offset}")
        List<OrdersDto> OrderGetAddItem(int startNum, int offset, String userId);

        //이용금액
        @Select("SELECT SUM(total_price) FROM orders WHERE user_id = #{userId}")
        Integer getUserTotalPrice(String userId);


        @Delete("DELETE FROM orders WHERE id = #{id}")
        void deleteOrders(String id);
    }