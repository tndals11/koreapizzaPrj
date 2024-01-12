package com.example.krpizzaPrj.controller;


import com.example.krpizzaPrj.dto.*;
import com.example.krpizzaPrj.mappers.AskMapper;
import com.example.krpizzaPrj.mappers.OrdersMapper;
import com.example.krpizzaPrj.mappers.ReviewMapper;
import com.example.krpizzaPrj.mappers.UserMapper;
import com.example.krpizzaPrj.service.AskService;
import com.example.krpizzaPrj.service.OrderService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AskMapper askMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    OrderService orderService;

    @Value("${fileDir}")
    String fileDir;

    @Autowired
    OrdersMapper ordersMapper;



    // 아이디 찾기 후 뷰단에 보여주는 페이지
    @GetMapping("/user/viewFindId")
    public String viewFindId(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        return "user/viewFindId";
    }

    @GetMapping("/user/goupDatePage")
    public String goupDatePage() {
        return "user/goupDatePage";
    }
    @PostMapping("/user/goupDatePage")
    @ResponseBody
    public Map<String, Object> setGoupDatePage(@RequestParam String userEmail, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        UserDto dto = (UserDto) session.getAttribute("user");
        int userName = dto.getUserNum();
        int result =  userMapper.setGoupDatePage( userEmail , userName);
        if ( result > 0 ) {
            map.put("msg", "success");
        } else {
            map.put("msg", "fail");
        }

        return map;
    }

    @GetMapping("/user/updateUserInfo")
    public String getupdateUserInfoPage(Model model, HttpSession session) {
        UserDto dto = (UserDto) session.getAttribute("user");
        if ( dto != null) {
            model.addAttribute("userId",dto.getUserId());
            model.addAttribute("userName",dto.getUserName());
            model.addAttribute("userEmail", dto.getUserEmail());
        }

        return "user/updateUserInfo";
    }

    @PostMapping("/user/updateUserInfo")
    @ResponseBody
    public Map <String, Object> setupdateUserInfoPage(@RequestParam String userName, HttpSession session) {
        UserDto dto = (UserDto) session.getAttribute("user");
        int userNum = dto.getUserNum();
        Map <String, Object> map = new HashMap<>();

        if ( userName.equals(dto.getUserName())) {
            map.put("msg", "fail");
        } else {
            userMapper.setupdateUserInfo(userName, userNum);
            map.put("msg", "success");
        }
        return map;
    }

    @PostMapping("/user/deleteUserInfo")
    @ResponseBody
    public Map<String, Object> deleteUserInfo(Model model, HttpSession session) {
        UserDto dto = (UserDto) session.getAttribute("user");
        int userNum = dto.getUserNum();
        String userSt = dto.getUserSt();
        userMapper.deleteUserInfo(userNum);
        Map<String, Object> map = new HashMap<>();

        if ( "90".equals(userSt) ) {
            map.put("msg", "fail");

        } else {
            map.put("msg", "success");
        }
        return map;
    }


    @GetMapping("/user/updatePasswd") public String getUserUpdatePasswd() {

        return "/user/updatePasswd";
    }

    @PostMapping("/user/updatePasswd")
    @ResponseBody
    public Map <String, Object> UserUpdatePasswd(@RequestParam String newPasswd ,HttpSession session) {
        Map <String, Object> map = new HashMap<>();
        UserDto dto = (UserDto) session.getAttribute("user");
        int userNum = dto.getUserNum();
        if (newPasswd.equals(dto.getUserPasswd())) {
            map.put("msg", "fail");
        } else {
            userMapper.updateUserPasswd(newPasswd,userNum);
            map.put("msg", "success");
        }
        return map;
    }



    @GetMapping("/user/afterLogin")
    public String getAfterLogin(Model model) {
        return "user/afterLogin";
    }

    @GetMapping("/user/userinfo") public String getUserInfo() {

        return "user/userinfo";
    }

    @GetMapping("/user/ask")
    public String userAsk(HttpSession session, Model model) {
        UserDto dto = (UserDto) session.getAttribute("user");

        if(dto != null){
            model.addAttribute("userName", dto.getUserName());
            model.addAttribute("userEmail", dto.getUserEmail());
            model.addAttribute("userId", dto.getUserId());
            model.addAttribute("userNum", dto.getUserNum());
        }
        return "user/ask";
    }


    @GetMapping("/user/viewAsk")
    public String userAsk(@RequestParam int id, Model model) {

        model.addAttribute("ask", userMapper.getAskView(id));
        return "user/viewAsk";
    }

    @GetMapping("/user/askList")
    public String userAskList(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

        PageDto pageDto = new PageDto();
        int totalCount = userMapper.totalCountAsk();
        int totalPage = (int) Math.ceil((double) totalCount / pageDto.getPageCount());
        int startPage = (int) (Math.ceil((double) page) / (pageDto.getBlockCount()) - 1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        if (endPage > totalPage) {
            endPage = totalPage;
        }

        // dto에 값을 보내주는
        pageDto.setPage(page);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setTotalPage(totalPage);
        //limit 시작, 개수
        // LIMIT 0, 3
        // LIMIT 3, 3
        // LIMIT 6, 3
        int startNum = (page - 1) * pageDto.getPageCount();
        int offset = pageDto.getPageCount();

        model.addAttribute("ask", userMapper.getAskList(startNum,offset));
        model.addAttribute("page", pageDto);

        return "user/askList";
    }

    @PostMapping("/user/ask")
    public String setuserAsk(@ModelAttribute AskDto askDto, @RequestParam("file") MultipartFile mf) throws IOException {

        if ( !mf.isEmpty() ) {
            // 오늘 날짜의 폴더 생성
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder =  new File(fileDir + folderName );

            if ( !makeFolder.exists() ) {
                makeFolder.mkdir();
            }

            String orgName = mf.getOriginalFilename();
            String ext = orgName.substring(orgName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String saveFileName = uuid + ext;
            // 경로추가 + 232323.jpg => 두개의 값을 더해서 변수에 전해준다
            String savedFilePathName = fileDir + folderName + "/" + saveFileName;

            // askDto => db로 보내주기 */
            askDto.setOrgName(orgName);
            askDto.setSavedFileName(saveFileName);
            askDto.setSavedFilePathName(savedFilePathName);
            askDto.setSavedFileSize(mf.getSize());
            askDto.setFolderName(folderName);
            askDto.setExt(ext);

            askDto.toString();

            // 파일 업로드 쓰기 => db에 먼저 작성하고 사용한다
            mf.transferTo( new File(savedFilePathName) );
        }

        int maxGrp = askMapper.getMaxGrp();
        askDto.setGrp(maxGrp);
        askMapper.setAsk(askDto);
        return "redirect:/user/afterLogin";
    }

    @PostMapping("/user/options")
    @ResponseBody
    public Map<String, Object> setOptions() {
        Map<String, Object> map = new HashMap<>();
        map.put("options", askMapper.getOptionAll() );
        return map;
    }

    //장바구니 페이지 연결
    @GetMapping("/user/cart")
    public String getCart(HttpSession session, Model model){
        UserDto dto = (UserDto) session.getAttribute("user");
        model.addAttribute("userName", dto.getUserName());
        model.addAttribute("userId", dto.getUserId());
        return "user/cart";
    }


    //주문내역 페이지
    @PostMapping("/user/cart")
    @ResponseBody
    public String setCart(@RequestBody OrdersDto ordersDto, HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<>();

        if (ordersDto != null) {
            userMapper.setOrders(ordersDto);
            map.put("msg", "주문내역이 저장되었습니다.");
        }
        return "user/cart";
    }

    @GetMapping("/user/orders")
    public String getOrders(HttpSession session, Model model, @RequestParam(value = "page", defaultValue = "1") int page){
        UserDto dto = (UserDto) session.getAttribute("user");

        if (dto != null) {
            model.addAttribute("item", orderService.OrderGetItemList(page, dto.getUserId()));
            model.addAttribute("page", orderService.OrderBoardPageCalc(page, dto.getUserId()));
            model.addAttribute("cnt", orderService.getOrderItemListCnt(dto.getUserId()));
        }

        return "user/orders";
    }

    @PostMapping("/user/getreviewst")
    @ResponseBody
    public String getReviewSt(@RequestParam(value = "id" , defaultValue = "") String id, Model model){

        String result = reviewMapper.getReview(id);
        return result;
    }


    @PostMapping("/user/orders")
    @ResponseBody
    public Map<String, Object> setOrders(@RequestBody ReviewDto reviewDto){
        Map<String, Object> map = new HashMap<>();

        if (reviewDto != null) {
            reviewMapper.setReview(reviewDto);
            map.put("msg", "success");
        } else {
            map.put("msg", "fail");
        }

        return map;
    }

    @PostMapping("/user/sessionCheck")
    @ResponseBody
    public UserDto sessionCheck(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        return userDto;
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession hs) {
        hs.invalidate();
        return "redirect:/common/login";
    }
    //사용자 리뷰 페이지
    @GetMapping("/user/userReviewList")
    public String getUserReviewList(HttpSession session, Model model){
        UserDto dto = (UserDto) session.getAttribute("user");

        if (dto != null) {
            model.addAttribute("userName", dto.getUserName());
            model.addAttribute("review", reviewMapper.getUserReviewList(dto.getUserId()));
            model.addAttribute("reviewCount", reviewMapper.getUserReviewCount(dto.getUserId()));
            model.addAttribute("orderCount", ordersMapper.OrderTotalCount(dto.getUserId()));
            model.addAttribute("orderTotalPrice", ordersMapper.getUserTotalPrice(dto.getUserId()));
        }

        return "user/userReviewList";
    }



}