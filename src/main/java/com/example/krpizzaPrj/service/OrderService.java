package com.example.krpizzaPrj.service;

import com.example.krpizzaPrj.dto.OrderPageDto;
import com.example.krpizzaPrj.dto.OrdersDto;
import com.example.krpizzaPrj.dto.PageDto;
import com.example.krpizzaPrj.mappers.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    OrdersMapper ordersMapper;

    public PageDto BoardPageCalc(int page) {

        Map<String, Object> map = new HashMap<>();

        PageDto pageDto = new PageDto();
        // "SELECT COUNT(*) FROM users ${queryString}"
        // queryString => 값이 출력된다
        int totalCount = ordersMapper.totalCount();
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

    public OrderPageDto OrderBoardPageCalc(int page, String userId) {

        OrderPageDto orderPageDto = new OrderPageDto();

        int totalCount = ordersMapper.OrderTotalCount(userId);
        // Math.ceil() 소수점을 강제로 1로 만든다
        int totalPage = (int)Math.ceil((double) totalCount / orderPageDto.getPageCount());
        // 이쪽 구문에서 식오류 잘보고 체크할 것 그리고 sout이나 디버그를 잘 사용하자
        int startPage = (int) (Math.ceil( (double) page) / (orderPageDto.getBlockCount()) -1) * orderPageDto.getBlockCount() + 1;
        int endPage = startPage + orderPageDto.getBlockCount() - 1;

        // 끝 페이지 수가 전체 페이지 수 보다 크면
        if ( endPage > totalPage ) {
            endPage = totalPage;
        }

        // dto에 값을 보내주는
        orderPageDto.setPage(page);
        orderPageDto.setStartPage(startPage);
        orderPageDto.setEndPage(endPage);
        orderPageDto.setTotalPage(totalPage);

        return orderPageDto;
    }

    public List<OrdersDto> getItemList(int page) {
        Map<String, Object> map = new HashMap<>();

        PageDto pageDto = new PageDto();

        //limit 시작, 개수
        // LIMIT 0, 3
        // LIMIT 3, 3
        // LIMIT 6, 3
        int startNum = (page - 1) * pageDto.getPageCount();

        //map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        return ordersMapper.getAddItem(map);
    }

    public List<OrdersDto> OrderGetItemList(int page, String userId) {
        Map<String, Object> map = new HashMap<>();

        OrderPageDto orderPageDto = new OrderPageDto();

        int startNum = (page - 1) * orderPageDto.getPageCount();
        int offset = orderPageDto.getPageCount();

        return ordersMapper.OrderGetAddItem(startNum, offset, userId);
    }

    public int getItemListCnt() {

        return ordersMapper.totalCount();
    }

    public int getOrderItemListCnt(String userId) {

        return ordersMapper.OrderTotalCount(userId);
    }



}
