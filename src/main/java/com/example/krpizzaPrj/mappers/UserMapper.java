package com.example.krpizzaPrj.mappers;



import com.example.krpizzaPrj.dto.AskDto;
import com.example.krpizzaPrj.dto.OrdersDto;
import com.example.krpizzaPrj.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper {
        
        // 회원가입
        @Insert("INSERT INTO users VALUES(NULL, #{userId}, #{userPasswd}, #{userEmail}, #{userName}, NOW(), NOW(), '00')")
        public void setRegister(UserDto userDto);

        // 아이디 중복 체크
        @Select("SELECT COUNT(*) FROM users WHERE user_id = #{userId}")
        public int getCheckUserId(String userId);
        
        // 이메일 중복 체크
        @Select("SELECT COUNT(*) FROM users WHERE user_email = #{userEmail}")
        public int getCheckUserEmail(String userEmail);

        // 아이디 찾기
        @Select("SELECT users.user_id FROM users WHERE user_name = #{userName} AND user_email = #{userEmail}")
        public UserDto checkFindID( UserDto userDto );

        // 비밀번호 찾기
        // #userPasswd# userPasswd가 없어서 오류 계속 발생!! mybatis 이름 변환이 달라서 그런것 같다
        @Select("SELECT users.user_passwd  FROM users WHERE user_id = #{userId} AND user_email = #{userEmail}")
        public UserDto checkFindPw( UserDto userDto);

        // 로그인
        @Select("SELECT * FROM users  WHERE user_id = #{userId} AND user_passwd = #{userPasswd}")
        public UserDto checkLogin(UserDto userDto);

        @Select("SELECT COUNT(*) FROM users  WHERE user_email = #{userEmail} AND user_num = #{userNum}")
        public int setGoupDatePage(String userEmail, int userNum);

        @Update("UPDATE users SET user_name = #{userName} WHERE user_num = #{userNum}")
        public void setupdateUserInfo(String userName, int userNum);

        @Update("UPDATE users SET user_st = '90', user_enddate = now() WHERE user_num = #{userNum}")
        public void deleteUserInfo(int userNum);

        @Update("UPDATE users SET user_passwd = #{userPasswd} WHERE user_num = #{userNum}")
        public void updateUserPasswd(String userPasswd ,int userNum);

        //주문내역 데이터 삽입
        @Insert("insert into orders values( #{id}, #{userId}, #{userName}, #{itemNameAndCount}, #{inquiry}, #{totalCount}, #{totalPrice}, now())")
        public void setOrders(OrdersDto ordersDto);

        //주문내역 조회
        @Select("select * from orders where user_id = #{userId}")
        public List<OrdersDto> getOrders(String userId);


        @Select("SELECT a.id, a.option_code, u.user_num, u.user_name, u.user_id, a.subject, a.content, a.regdate, a.orgName, a.savedFileName, a.savedFilePathName, a.savedFileSize, a.folderName, a.ext, a.grp, a.seq, a.`depth` from ask a join users u on a.user_num = u.user_num order by grp desc, seq asc LIMIT #{startNum}, #{offset}")
        List<AskDto> getAskList(int startNum, int offset);


        @Select("SELECT a.id, a.option_code, u.user_num, u.user_name, u.user_id, a.subject, a.content, a.regdate, a.orgName, a.savedFileName, a.savedFilePathName, a.savedFileSize, a.folderName, a.ext, a.grp, a.seq, a.`depth` from ask a join users u on a.user_num = u.user_num WHERE id = #{id}")
        AskDto getAskView(int id);

        @Select("SELECT count(*) FROM ask")
        int totalCountAsk();
}
