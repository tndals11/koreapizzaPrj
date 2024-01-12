package com.example.krpizzaPrj.mappers;

import com.example.krpizzaPrj.dto.AskDto;
import com.example.krpizzaPrj.dto.OptionsDto;
import com.example.krpizzaPrj.dto.UserDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AskMapper {

    // 1:1 회원문의 카테고리 만들기
    @Select("SELECT * FROM options ORDER BY option_code ASC")
    List<OptionsDto> getOptionAll();
    
    // 사용자가 입력한 1:1문의 담기
    @Insert("INSERT INTO ask VALUES(NULL, #{optionCode}, #{userNum}, NULL, #{subject},  #{content}, NOW(), #{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, #{folderName}, #{ext}, #{grp}, 1, 1)")
    void setAsk(AskDto askDto);
    
    // 답변달기
    @Insert("INSERT INTO ask VALUES(NULL, #{optionCode}, #{userNum}, #{writer}, #{subject}, #{content}, NOW(), #{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, #{folderName}, #{ext}, #{grp}, #{seq}, #{depth})")
    void setReply(AskDto askDto);

    // 게시판 답글달기에 필요한 초기 쿼리문
    @Select("SELECT ifnull( max(grp) + 1, 1 ) AS maxGrp FROM ask")
    int getMaxGrp();

    // 관리자 페이지 화면에 뿌려주기 검색어 입력
    @Select("SELECT a.id, a.option_code, u.user_num, u.user_name, u.user_id, a.subject, a.content, a.regdate, a.orgName, a.savedFileName, a.savedFilePathName, a.savedFileSize, a.folderName, a.ext, a.grp, a.seq, a.`depth` from ask a join users u on a.user_num = u.user_num ${searchQuery} order by grp desc, seq asc")
    List<AskDto> getAsk(Map<String, Object> map);

    // 1:1문의 개수 검색어 개수
    @Select("select count(*) from ask a inner join users u on a.user_num = u.user_num ${searchQuery} ")
    int getAskCnt(Map<String, Object> map);

    // 1:1문의 게시판 상세보기
    @Select("SELECT a.id, a.option_code, u.user_num, u.user_name, u.user_id, a.subject, a.content, a.regdate, a.orgName, a.savedFileName, a.savedFilePathName, a.savedFileSize, a.folderName, a.ext, a.grp, a.seq, a.`depth` from ask a join users u on a.user_num = u.user_num where id = #{id}")
    AskDto getAskView(int id);

    // 1:1문의 게시판 삭제
    @Delete("DELETE FROM ask WHERE id = #{id}")
    void getAskDelete(int id);




}
