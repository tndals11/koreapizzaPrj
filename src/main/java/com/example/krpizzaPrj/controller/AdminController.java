package com.example.krpizzaPrj.controller;


import com.example.krpizzaPrj.dto.*;
import com.example.krpizzaPrj.mappers.*;
import com.example.krpizzaPrj.service.AskService;
import com.example.krpizzaPrj.service.ItemService;
import com.example.krpizzaPrj.service.OrderService;
import com.example.krpizzaPrj.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class AdminController {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AddItemMapper addItemMapper;

    @Value("${fileDir}")
    String fileDir;

    @Autowired
    ItemService itemService;

    @Autowired
    AskMapper askMapper;

    @Autowired
    AskService askService;

    @Autowired
    AdminMainMapper adminMainMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    OrderService orderService;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    ReviewService reviewService;


    @GetMapping("/admin/userAdminPage")
    public String getUserAdminPage(Model model, @RequestParam(defaultValue = "") String searchType,
                                   @RequestParam(defaultValue = "") String words,
                                   @RequestParam(value = "page", defaultValue = "1") int page) {

        String queryString = "";
        if (searchType.equals("userid")) {
            queryString = "WHERE user_id = '" + words + "'";
        } else if (searchType.equals("username")) {
            queryString = "WHERE user_name = '" + words + "'";
        } else {
            queryString = "";
        }

        PageDto pageDto = new PageDto();
        // "SELECT COUNT(*) FROM users ${queryString}"
        // queryString => 값이 출력된다
        int totalCount = adminMapper.getUserAdminCount(queryString);
        // Math.ceil() 소수점을 강제로 1로 만든다
        int totalPage = (int) Math.ceil((double) totalCount / pageDto.getPageCount());
        // 이쪽 구문에서 식오류 잘보고 체크할 것 그리고 sout이나 디버그를 잘 사용하자
        int startPage = (int) (Math.ceil((double) page) / (pageDto.getBlockCount()) - 1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        // 끝 페이지 수가 전체 페이지 수 보다 크면
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

        model.addAttribute("cnt", adminMapper.getUserAdminCount(queryString));
        model.addAttribute("mem", adminMapper.getUserAdminPage(queryString, startNum, offset));
        // 이쪽 구문에서 오류 발생 !! => Service를 사용했을때는 pageDto가 들어가는 것을 몰랐는데
        // page.page 구문이 실행하기 위해서는 "page"에 pageDto를 담아서 전달해주어야한다
        model.addAttribute("page", pageDto);

        return "admin/userAdminPage";
    }

    @GetMapping("/admin/viewUser")
    public String getViewUser(@RequestParam int id, Model model) {

        model.addAttribute("mem", adminMapper.getViewUser(id));
        return "admin/viewUser";
    }

    @GetMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam int id, RedirectAttributes ra) {

        adminMapper.deleteUser(id);
        ra.addFlashAttribute("msg", "회원이 삭제되었습니다.");

        return "redirect:/admin/userAdminPage";
    }

    @GetMapping("/admin/addItem")
    public String getAddItem() {
        return "admin/addItem";
    }

    @PostMapping("/admin/addItem")
    public String setAddItem(@ModelAttribute AddItemDto addItemDto, @RequestParam("file") MultipartFile mf) throws IOException {

        if (!mf.isEmpty()) {
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);

            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            // orgName => 파일의 원본이름 ex)pizza1.jpg
            String orgName = mf.getOriginalFilename();
            // ext orgName에 .을 기준으로 자른다 ex).jpg
            String ext = orgName.substring(orgName.lastIndexOf("."));
            // UUID 임의의 변수를 생성해준다 ex) fkdkgsdgdmkmsdk
            String uuid = UUID.randomUUID().toString();
            // 임의로 생성된 uuid와 .을 기준으로 잘라진 .jpg를 연결시켜준다 ex) dasdasdfjgdjgfdgjddr24254kmsk.jpg
            String saveFileName = uuid + ext;
            // 파일 경로와 오늘날짜 폴더이름(20231219) + "/" 폴더안이라는 뜻 saveFileName(dasdasdfjgdjgfdgjddr24254kmsk.jpg) 값을 넣어준다.
            String savedFilePathName = fileDir + folderName + "/" + saveFileName;


            // boardDto => db */
            addItemDto.setOrgName(orgName);
            addItemDto.setSavedFileName(saveFileName);
            addItemDto.setSavedFilePathName(savedFilePathName);
            addItemDto.setSavedFileSize(mf.getSize());
            addItemDto.setFolderName(folderName);
            addItemDto.setExt(ext);

            // 파일 업로드 쓰기 => db에 먼저 작성하고 사용한다
            mf.transferTo(new File(savedFilePathName));
        }

        addItemMapper.setAddItem(addItemDto);

        return "redirect:/admin/itemList";
    }


    @PostMapping("/admin/category")
    @ResponseBody
    public Map<String, Object> setAddCategory() {
        Map<String, Object> map = new HashMap<>();
        map.put("category", addItemMapper.addCategory());
        return map;
    }


    @GetMapping("/admin/itemList")
    public String getItemList(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType,
                              @RequestParam(value = "words", defaultValue = "") String words,
                              @RequestParam(value = "page", defaultValue = "1") int page) {
        model.addAttribute("item", itemService.getItemList(page, searchType, words));
        model.addAttribute("cnt", itemService.getItemListCnt(searchType, words));
        model.addAttribute
                ("page", itemService.BoardPageCalc(page, searchType, words));
        return "admin/itemList";
    }


    @GetMapping("/admin/itemView")
    public String ItemListView(@RequestParam String itemName, Model model) {
        model.addAttribute("tem", addItemMapper.getItemView(itemName));
        return "/admin/itemView";
    }

    @GetMapping("/admin/itemDelete")
    public String ItemListDelete(@RequestParam String itemName) {
        addItemMapper.getItemDelete(itemName);
        return "redirect:/admin/itemList";
    }

    @GetMapping("/admin/itemUpdate")
    public String ItemListUpdate(@RequestParam String itemId, Model model) {
        model.addAttribute("item", addItemMapper.getUpdateView(itemId));
        return "admin/itemUpdate";
    }

    @PostMapping("/admin/itemUpdate")
    public String ItemListSetUpdate(@ModelAttribute AddItemDto addItemDto, @RequestParam("file") MultipartFile mf) throws IOException {

        if (!mf.isEmpty()) {
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);

            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            // orgName => 파일의 원본이름 ex)pizza1.jpg
            String orgName = mf.getOriginalFilename();
            // ext orgName에 .을 기준으로 자른다 ex).jpg
            String ext = orgName.substring(orgName.lastIndexOf("."));
            // UUID 임의의 변수를 생성해준다 ex) fkdkgsdgdmkmsdk
            String uuid = UUID.randomUUID().toString();
            // 임의로 생성된 uuid와 .을 기준으로 잘라진 .jpg를 연결시켜준다 ex) dasdasdfjgdjgfdgjddr24254kmsk.jpg
            String saveFileName = uuid + ext;
            // 파일 경로와 오늘날짜 폴더이름(20231219) + "/" 폴더안이라는 뜻 saveFileName(dasdasdfjgdjgfdgjddr24254kmsk.jpg) 값을 넣어준다.
            String savedFilePathName = fileDir + folderName + "/" + saveFileName;
            // boardDto => db */
            addItemDto.setOrgName(orgName);
            addItemDto.setSavedFileName(saveFileName);
            addItemDto.setSavedFilePathName(savedFilePathName);
            addItemDto.setSavedFileSize(mf.getSize());
            addItemDto.setFolderName(folderName);
            addItemDto.setExt(ext);
            // 파일 업로드 쓰기 => db에 먼저 작성하고 사용한다
            mf.transferTo(new File(savedFilePathName));
        } else {

            addItemDto.setSavedFileName(addItemDto.getSavedFileName());
            addItemDto.setSavedFilePathName(addItemDto.getSavedFilePathName());
            addItemDto.setSavedFileSize(addItemDto.getSavedFileSize());
            addItemDto.setFolderName(addItemDto.getFolderName());
            addItemDto.setExt(addItemDto.getExt());
        }

        addItemMapper.setItemUpdate(addItemDto);

        return "redirect:/admin/itemList";
    }


    // 파일 다운로드
    @GetMapping("/download")
    public ResponseEntity<Resource> getDownload(String filename) throws MalformedURLException {
        UrlResource resource = new UrlResource("file:" + filename);
        String encodedFileName = UriUtils.encode(filename, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }

    //1:1문의 리스트 검색어처리
    @GetMapping("/admin/ask")
    public String getAsk(@RequestParam(value = "searchType", defaultValue = "") String searchType,
                         @RequestParam(value = "words", defaultValue = "") String words,
                         @RequestParam(value = "page", defaultValue = "1") int Page,
                         Model model) {
        model.addAttribute("ask", askService.getAsk(Page, searchType, words));
        model.addAttribute("cnt", askService.getAskCnt(searchType, words));
        model.addAttribute("page", askService.AskBoardPageCalc(Page, searchType, words));
        return "admin/ask";
    }

    // 1:1문의 상세보기
    @GetMapping("/admin/askView")
    public String getAskView(@RequestParam int id, Model model) {
        model.addAttribute("ask", askMapper.getAskView(id));
        return "admin/askView";
    }

    // 1:1문의 삭제하기
    @GetMapping("/admin/askDelete")
    public String getAskDelete(@RequestParam int id) {
        askMapper.getAskDelete(id);
        return "redirect:/admin/ask";
    }


    @GetMapping("/admin/replyAsk")
    public String getReplyAsk(@RequestParam int id, Model model) {
        AskDto a = askMapper.getAskView(id);
        model.addAttribute("reply", a);
        return "admin/replyAsk";
    }


    @PostMapping("/admin/replyAsk")
    public String setReplyAsk(@ModelAttribute AskDto askDto, @RequestParam("file") MultipartFile mf) throws IOException {
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
        AskDto ad =  askMapper.getAskView(askDto.getId()); //=> 넘겨받은 값
        // 답글
        // bd.getGrp() => 1 고정값(대분류),     2   =>  bd.getSeq() + 1답글,         3=>  bd.getDepth() + 1 들여쓰기
        askDto.setGrp(ad.getGrp());
        askDto.setSeq(ad.getSeq() +1);
        askDto.setDepth(ad.getDepth() +1);
        askMapper.setReply(askDto);
        return "redirect:/admin/ask";
    }



    @GetMapping("/admin/adminMainPage")
    public String getAdminMainPage(Model model) {
        if (model != null) {
            model.addAttribute("ask", adminMainMapper.viewAsk());
            model.addAttribute("user", adminMainMapper.viewUsers());
            model.addAttribute("item", adminMainMapper.viewItems());
            model.addAttribute("cnt", adminMainMapper.cntUsers());
            model.addAttribute("askcnt", adminMainMapper.cntAsk());
            model.addAttribute("admincnt", adminMainMapper.adminCnt());
            model.addAttribute("orders", adminMainMapper.viewOrders());
            model.addAttribute("review", adminMainMapper.viewRivews());
            model.addAttribute("reviewAvg", adminMainMapper.reviewAvg());
            model.addAttribute("ordersCnt", adminMainMapper.ordersCnt());



        }

        Integer ordersSum = adminMainMapper.ordersSum();
        if (ordersSum == null) {
            ordersSum = 0; // null인 경우 0으로 초기화 또는 다른 기본값으로 설정
        }
        model.addAttribute("ordersSum", ordersSum);

        return "admin/adminMainPage";
    }

    @GetMapping("/admin/orderList")
    public String getOrderList(Model model,
                               @RequestParam(value = "page", defaultValue = "1") int page){
        List<OrdersDto> result = ordersMapper.getOrderList();
        model.addAttribute("orderList", result);
        //paging
        model.addAttribute("item", orderService.getItemList(page));
        //model.addAttribute("cnt", itemService.getItemListCnt(searchType, words));
        model.addAttribute("page", orderService.BoardPageCalc(page));
        return "admin/orderList";
    }

    @GetMapping("/admin/deleteOrders")
    public String deleteOrders(@RequestParam String id, RedirectAttributes ra) {
        ordersMapper.deleteOrders(id);
        ra.addFlashAttribute("msg", "주문이 취소되었습니다.");
        return "redirect:/admin/orderList";
    }


    @GetMapping("/admin/reviewList")
    public String showReview(@RequestParam(value = "searchType", defaultValue = "") String searchType,
                             @RequestParam(value = "words", defaultValue = "") String words,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             Model model){
        model.addAttribute("review", reviewService.showReview(searchType,words, page));
        model.addAttribute("cnt", reviewService.reviewCnt(searchType, words));
        model.addAttribute("page", reviewService.reviewBoardPageCalc(page,searchType, words));
        return "admin/reviewList";
    }


}
