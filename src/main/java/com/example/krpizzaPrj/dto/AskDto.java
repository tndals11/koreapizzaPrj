package com.example.krpizzaPrj.dto;

public class AskDto {
    private int id;
    private String optionCode;
    private int userNum;
    private String userName;
    private String userId;
    private String writer;
    private String subject;
    private String content;
    private String regdate;
    private String orgName;
    private String savedFileName;
    private String savedFilePathName;
    private long savedFileSize;
    private int grp;
    private int seq;
    private int depth;
    private String folderName;
    private String ext;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSavedFileName() {
        return savedFileName;
    }

    public void setSavedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
    }

    public String getSavedFilePathName() {
        return savedFilePathName;
    }

    public void setSavedFilePathName(String savedFilePathName) {
        this.savedFilePathName = savedFilePathName;
    }

    public long getSavedFileSize() {
        return savedFileSize;
    }

    public void setSavedFileSize(long savedFileSize) {
        this.savedFileSize = savedFileSize;
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

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "AskDto{" +
                "id=" + id +
                ", optionCode='" + optionCode + '\'' +
                ", userNum=" + userNum +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", writer='" + writer + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", regdate='" + regdate + '\'' +
                ", orgName='" + orgName + '\'' +
                ", savedFileName='" + savedFileName + '\'' +
                ", savedFilePathName='" + savedFilePathName + '\'' +
                ", savedFileSize=" + savedFileSize +
                ", grp=" + grp +
                ", seq=" + seq +
                ", depth=" + depth +
                ", folderName='" + folderName + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}
