package com.example.krpizzaPrj.dto;

public class OrdersDto {
    private String id;
    private String userId;
    private String userName;
    private String itemNameAndCount;
    private String inquiry;
    private int totalCount;
    private int totalPrice;
    private String orderDate;
    private String reviewst;

    private int score;
    private String reviewcontent;
    private String regdate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemNameAndCount() {
        return itemNameAndCount;
    }

    public void setItemNameAndCount(String itemNameAndCount) {
        this.itemNameAndCount = itemNameAndCount;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getReviewst() {
        return reviewst;
    }

    public void setReviewst(String reviewst) {
        this.reviewst = reviewst;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReviewcontent() {
        return reviewcontent;
    }

    public void setReviewcontent(String reviewcontent) {
        this.reviewcontent = reviewcontent;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "OrdersDto{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", itemNameAndCount='" + itemNameAndCount + '\'' +
                ", inquiry='" + inquiry + '\'' +
                ", totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", orderDate='" + orderDate + '\'' +
                ", reviewst='" + reviewst + '\'' +
                ", score=" + score +
                ", reviewcontent='" + reviewcontent + '\'' +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
