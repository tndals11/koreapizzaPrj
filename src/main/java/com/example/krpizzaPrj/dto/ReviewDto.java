package com.example.krpizzaPrj.dto;

import java.sql.Timestamp;

public class ReviewDto {
    private int reviewNum;
    private String id;
    private int score;
    private String userId;
    private String userName;
    private String regdate;
    private String reviewcontent;
    private String reviewst;
    private int grp;
    private int seq;
    private int depth;

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getReviewcontent() {
        return reviewcontent;
    }

    public void setReviewcontent(String reviewcontent) {
        this.reviewcontent = reviewcontent;
    }

    public String getReviewst() {
        return reviewst;
    }

    public void setReviewst(String reviewst) {
        this.reviewst = reviewst;
    }

    public int getGrp() {
        return grp;
    }

    public void setGrp(int grp) {
        this.grp = grp;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "reviewNum=" + reviewNum +
                ", id='" + id + '\'' +
                ", score=" + score +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", regdate=" + regdate +
                ", reviewcontent='" + reviewcontent + '\'' +
                ", reviewst='" + reviewst + '\'' +
                ", grp=" + grp +
                ", seq=" + seq +
                ", depth=" + depth +
                '}';
    }
}
