package com.example.krpizzaPrj.mappers;


import com.example.krpizzaPrj.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMainMapper {


    @Select("SELECT a.id, a.option_code, u.user_num, u.user_name, u.user_id, a.subject, a.content, a.regdate, a.orgName, a.savedFileName, a.savedFilePathName, a.savedFileSize, a.folderName, a.ext, a.grp, a.seq, a.`depth` from ask a join users u on a.user_num = u.user_num order by grp desc, seq asc LIMIT 7")
    List<AskDto> viewAsk();

    @Select("select * from users order by user_num desc limit 7;")
    List<UserDto> viewUsers();

    @Select("select * from items order by item_id ASC limit 7;")
    List<AddItemDto> viewItems();

    @Select("select * from orders order by id desc limit 7;")
    List<OrdersDto> viewOrders();

    @Select("select o.id, r.score, o.user_id, o.user_name, r.review_content, r.review_st, r.regdate, r.grp, r.seq, r.`depth`  from review r join orders o ON r.id = o.id order by o.id desc limit 7;")
    List<ReviewDto> viewRivews();

    @Select("SELECT COUNT(*) FROM users WHERE user_st = '00'")
    int cntUsers();

    @Select("SELECT COUNT(*)  FROM ask where writer IS NULL")
    int cntAsk();

    @Select("SELECT COUNT(*) FROM ask WHERE writer IS NOT NULL;")
    int adminCnt();

    @Select("select avg(r.score) from review r")
    Double reviewAvg();

    @Select("select count(*) from orders")
    int ordersCnt();

    @Select("select sum(o.total_price) from orders o")
    Integer ordersSum();
}
