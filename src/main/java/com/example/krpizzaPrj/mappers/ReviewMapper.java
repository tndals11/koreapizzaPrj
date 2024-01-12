package com.example.krpizzaPrj.mappers;

import com.example.krpizzaPrj.dto.OrdersDto;
import com.example.krpizzaPrj.dto.ReviewDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    @Select("SELECT review_st FROM review WHERE id = #{id}")
    String getReview(String id);

    @Insert("INSERT INTO review VALUES(null, #{id}, #{score}, #{reviewcontent},NOW(), 'Y',  #{grp}, 1, 1)")
    void setReview(ReviewDto reviewDto);

    @Select("select o.id, r.score, o.user_id, o.user_name, r.review_content, r.review_st, r.regdate, r.grp, r.seq, r.`depth`  from review r join orders o ON r.id = o.id ${searchQuery} ORDER BY r.regdate DESC")
    List<ReviewDto> showReview(Map<String, Object> map);

    @Select("select count(*) from review r join orders o ON r.id = o.id ${searchQuery}")
    int reviewCnt(Map<String, Object> map);

    //전체 리뷰 내역 조회
    @Select("SELECT a.id, a.user_id, b.score, b.review_content, b.regdate FROM orders a LEFT JOIN review b on a.id = b.id ORDER BY b.regdate DESC")
    List<OrdersDto> getCommonReviewList();

    //사용자 리뷰 내역 조회
    @Select("SELECT a.id, a.user_name, a.user_id, b.score, b.review_content, b.regdate FROM orders a LEFT JOIN review b on a.id = b.id WHERE a.user_id = #{userId} ORDER BY b.regdate DESC")
    List<OrdersDto> getUserReviewList(String userId);

    //리뷰횟수
    @Select("SELECT COUNT(*) FROM orders a LEFT JOIN review b ON a.id = b.id WHERE a.id = b.id AND a.user_id = #{userId} ORDER BY a.id DESC")
    int getUserReviewCount(String userId);


}
