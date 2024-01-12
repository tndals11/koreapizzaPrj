package com.example.krpizzaPrj.dto;

public class AddItemDto {

    private int itemId;
    private String categoryCode;
    private String itemName;
    private String itemPrice;
    private String itemIntro;
    private  int visit;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemIntro() {
        return itemIntro;
    }

    public void setItemIntro(String itemIntro) {
        this.itemIntro = itemIntro;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
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
        return "AddItemDto{" +
                "itemId=" + itemId +
                ", categoryCode='" + categoryCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemIntro='" + itemIntro + '\'' +
                ", visit=" + visit +
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
