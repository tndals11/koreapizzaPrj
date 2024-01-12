package com.example.krpizzaPrj.service;


import com.example.krpizzaPrj.dto.PageDto;
import com.example.krpizzaPrj.dto.ReviewDto;
import com.example.krpizzaPrj.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    public PageDto reviewBoardPageCalc(int page, String searchType, String words) {
        PageDto pageDto = new PageDto();

        Map<String, Object> map = new HashMap<>();
        String searchQuery = "";
        if ( ("score").equals(searchType) || ("user_id").equals(searchType) || ("user_name").equals(searchType) ) {
            searchQuery = " WHERE " + searchType + " = '" + words +"'";
        } else if ( ("review_content").equals(searchType) ) {
            searchQuery = " WHERE " + searchType + " LIKE '%" + words + "%'";
        } else {
            searchQuery = "";
        }

        map.put("searchQuery", searchQuery);
        int totalCount = reviewMapper.reviewCnt(map);
        // Math.ceil() 소수점을 강제로 1로 만든다
        int totalPage = (int)Math.ceil((double) totalCount / pageDto.getPageCount());
        // 이쪽 구문에서 식오류 잘보고 체크할 것 그리고 sout이나 디버그를 잘 사용하자
        int startPage = (int) (Math.ceil( (double) page) / (pageDto.getBlockCount()) -1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        // 끝 페이지 수가 전체 페이지 수 보다 크면
        if ( endPage > totalPage ) {
            endPage = totalPage;
        }

        // dto에 값을 보내주는
        pageDto.setPage(page);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setTotalPage(totalPage);

        return pageDto;
    }

    public List<ReviewDto> showReview(String searchType, String words, int page) {

        Map<String, Object> map = new HashMap<>();
        String searchQuery = "";
        if ( ("score").equals(searchType) || ("user_id").equals(searchType) || ("user_name").equals(searchType) ) {
            searchQuery = " WHERE " + searchType + " = '" + words +"'";
        } else if ( ("review_content").equals(searchType) ) {
            searchQuery = " WHERE " + searchType + " LIKE '%" + words + "%'";
        } else {
            searchQuery = "";
        }

        PageDto pageDto = new PageDto();
        //limit 시작, 개수
        // LIMIT 0, 3
        // LIMIT 3, 3
        // LIMIT 6, 3
        int startNum = (page - 1) * pageDto.getPageCount();
        map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());
        return reviewMapper.showReview(map);
    }

    public int reviewCnt(String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
        if ( ("score").equals(searchType) || ("user_id").equals(searchType) || ("user_name").equals(searchType) ) {
            searchQuery = " WHERE " + searchType + " = '" + words +"'";
        } else if ( ("review_content").equals(searchType) ) {
            searchQuery = " WHERE " + searchType + " LIKE '%" + words + "%'";
        } else {
            searchQuery = "";
        }

        map.put("searchQuery", searchQuery);


        return reviewMapper.reviewCnt(map);
    }

}