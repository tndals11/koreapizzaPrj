package com.example.krpizzaPrj.controller;


import com.example.krpizzaPrj.dto.OrdersDto;
import com.example.krpizzaPrj.dto.ReviewDto;
import com.example.krpizzaPrj.dto.UserDto;
import com.example.krpizzaPrj.mappers.AskMapper;
import com.example.krpizzaPrj.mappers.ReviewMapper;
import com.example.krpizzaPrj.mappers.UserMapper;
import com.example.krpizzaPrj.mappers.ViewItemMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommonController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    ViewItemMapper viewItemMapper;

    @GetMapping("/common/register")
    public String getRegister() {

        return "common/register";
    }
    // 회원가입 페이지
    @PostMapping("/common/register")
    @ResponseBody
    public Map<String, Object> setRegister(@ModelAttribute UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        if (userDto != null) {
            userMapper.setRegister(userDto);
            map.put("msg", "회원가입이 완료되었습니다.");
        }
        return map;
    }

    // 아이디 중복성 체크
    @GetMapping("/common/checkUserId")
    @ResponseBody
    public Map<String, Object> getCheckUserId(@RequestParam String userId) {
        int CheckUserId = userMapper.getCheckUserId(userId);

        return Map.of("checkUserId", CheckUserId);
    }

    // 이메일 중복성 체크
    @GetMapping("/common/checkUserEmail")
    @ResponseBody
    public Map<String, Object> getCheckUserEmail(@RequestParam String userEmail) {
        int CheckUserEmail = userMapper.getCheckUserEmail(userEmail);

        return Map.of("CheckUserEmail", CheckUserEmail);
    }

    @GetMapping("/common/findID")
    public String getFindID() {

        return "common/findID";
    }

    // 로그인 페이지
    @GetMapping("/common/login")
    public String getLogin() {

        return "common/login";
    }

    // 로그인 페이지 값을 받아와 처리
    @PostMapping("/common/checkLogin")
    @ResponseBody
    public String checkLogin(@RequestBody UserDto userDto, HttpServletRequest request) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단

        final String adminId = "admin1234";
        final String adminPw = "Admin1234!!";

        // 입력받은 자격 증명이 특정 관리자의 것과 일치하는지 확인
        if (adminId.equals(userDto.getUserId()) && adminPw.equals(userDto.getUserPasswd())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userDto); // 관리자 정보를 세션에 저장
            return "admin"; // 클라이언트에게 'admin' 상태를 알림
        }


        UserDto userdto = userMapper.checkLogin(userDto);
        String msg = "";
        if (userdto != null ) {
            if ("90".equals(userdto.getUserSt())) {
                msg = "userDelete";
            } else if ("50".equals(userdto.getUserSt())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userdto);
                msg= "admin";
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", userdto);
                msg = "success";
            }
        }  else {
            msg = "fail";
        }

        return msg;
    }

    // 아이디 찾기 페이지 값을 받아와 처리
    @PostMapping("/common/checkFindID")
    @ResponseBody
    public Map<String, Object> checkFindID(@RequestBody UserDto userDto) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단

        UserDto result = userMapper.checkFindID(userDto);
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        if ( result != null ) {
            msg = "success";
            map.put("userId", result.getUserId() ); // url 담을려고 변수명 userId에 getUserId를 받아옴
        } else {
            msg = "fail";
        }
        map.put("msg", msg); // --> 메세지를 보낼려고 쓴것
        return map;
    }

    // 비밀번호 찾기 주소
    @GetMapping("/common/findPw")
    public String getFindPw() {

        return "common/findPw";
    }

    // 비밀번호 찾기 주소 값 받기
    @PostMapping("/common/findPw")
    public String checkFindPw(@ModelAttribute UserDto userDto, Model model, RedirectAttributes ra) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단
        UserDto res = userMapper.checkFindPw(userDto);
        if ( res != null ) {
            String userPasswd = res.getUserPasswd();
            ra.addFlashAttribute("msg",userPasswd );
            return "redirect:/common/viewFindPw";
        } else {
            ra.addFlashAttribute("msg", "아이디와 이메일을 다시 확인해주세요");
            return "redirect:/common/findPw";
        }
    }

    // 비밀번호 찾기 후 뷰단에 보여주는 페이지
    @GetMapping("/common/viewFindPw")
    public String viewFindPw(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        return "common/viewFindPw";
    }
    @GetMapping("/common/mainPage")
    public String getMainPage() {

        return "common/mainPage";
    }

    @GetMapping("/common/map")
    public String getMap() {

        return "common/map";
    }
    @GetMapping("/common/eventPage") public String getEventPage() {
        return "common/eventPage";
    }
    @GetMapping("/common/endEventPage") public String getEndEventPage() {
        return "common/endEventPage";
    }


    @GetMapping("/common/premium")
    public String getPremium(Model model){
        model.addAttribute("premium",viewItemMapper.viewPremium());
        return "common/premium";
    }

    @GetMapping("/common/original")
    public String getOriginal(Model model){
        model.addAttribute("original",viewItemMapper.viewOriginal());
        return "common/original";
    }

    @GetMapping("/common/side")
    public String getSide(Model model){
        model.addAttribute("side",viewItemMapper.viewSide());
        return "common/side";
    }

    @GetMapping("/common/drink")
    public String getDrink(Model model){
        model.addAttribute("drink",viewItemMapper.viewDrink());
        return "common/drink";
    }

    @GetMapping("/common/emptyCart")
    public String getEmptyCart(){
        return "common/emptyCart";
    }


    //리뷰게시판 출력
    @GetMapping("/common/commonReviewList")
    public String getCommonReviewList(@ModelAttribute ReviewDto reviewDto, Model model){

        List<OrdersDto> result = reviewMapper.getCommonReviewList();
        model.addAttribute("review", result);
        return "common/commonReviewList";
    }
}
