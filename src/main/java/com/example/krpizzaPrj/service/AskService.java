package com.example.krpizzaPrj.service;

import com.example.krpizzaPrj.dto.AskDto;
import com.example.krpizzaPrj.dto.PageDto;
import com.example.krpizzaPrj.mappers.AskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AskService {

    @Autowired
    AskMapper askMapper;

    public PageDto AskBoardPageCalc(int page, String searchType, String words) {
        PageDto pageDto = new PageDto();
        // "SELECT COUNT(*) FROM users ${queryString}"
        // queryString => 값이 출력된다
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
        if ("a.option_code".equals(searchType) || "u.user_name".equals(searchType) || "u.user_id".equals(searchType)) {
            searchQuery = " WHERE " + searchType + " = '" + words + "'";
        } else if ("a.content".equals(searchType)) {
            searchQuery = " WHERE " + searchType + " LIKE '% " + words + " %'";
        } else {
            searchQuery ="";
        }
        map.put("searchQuery", searchQuery);

        int totalCount = askMapper.getAskCnt(map);
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

    public List<AskDto> getAsk(int page, String searchType, String words) {
        PageDto pageDto = new PageDto();
        // "SELECT COUNT(*) FROM users ${queryString}"
        // queryString => 값이 출력된다
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
        if ("a.option_code".equals(searchType) || "u.user_name".equals(searchType) || "u.user_id".equals(searchType)) {
            searchQuery = " WHERE " + searchType + " = '" + words + "'";
        } else if ("a.content".equals(searchType)) {
            searchQuery = " WHERE " + searchType + " LIKE '% " + words + " %'";
        } else {
            searchQuery ="";
        }

        //limit 시작, 개수
        // LIMIT 0, 3
        // LIMIT 3, 3
        // LIMIT 6, 3
        int startNum = (page - 1) * pageDto.getPageCount();

        map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        return askMapper.getAsk(map);
    }

    public int getAskCnt(String searchType, String words) {
        PageDto pageDto = new PageDto();
        // "SELECT COUNT(*) FROM users ${queryString}"
        // queryString => 값이 출력된다
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
        if ("a.option_code".equals(searchType) || "u.user_name".equals(searchType) || "u.user_id".equals(searchType)) {
            searchQuery = " WHERE " + searchType + " = '" + words + "'";
        } else if ("a.content".equals(searchType)) {
            searchQuery = " WHERE " + searchType + " LIKE '% " + words + " %'";
        } else {
            searchQuery ="";
        }

        map.put("searchQuery", searchQuery);

        return askMapper.getAskCnt(map);
    }

}
