package com.example.armada_nasar.budayaid;

public class Budaya {
    private String mNamaBudaya, mAsalKotaBudaya, mGoogleSearchTerm, mImgURLBudaya;
    private int mImgResIDBudaya;

    public Budaya(String mNamaBudaya, String mAsalKotaBudaya, String mGoogleSearchTerm, int mImgResIDBudaya) {
        this.mNamaBudaya = mNamaBudaya;
        this.mAsalKotaBudaya = mAsalKotaBudaya;
        this.mGoogleSearchTerm = mGoogleSearchTerm;
        this.mImgResIDBudaya = mImgResIDBudaya;
    }

    public Budaya(String mNamaBudaya, String mAsalKotaBudaya, String mGoogleSearchTerm, String mImgURLBudaya) {
        this.mNamaBudaya = mNamaBudaya;
        this.mAsalKotaBudaya = mAsalKotaBudaya;
        this.mGoogleSearchTerm = mGoogleSearchTerm;
        this.mImgURLBudaya = mImgURLBudaya;
    }

    public String getmNamaBudaya() {
        return mNamaBudaya;
    }

    public void setmNamaBudaya(String mNamaBudaya) {
        this.mNamaBudaya = mNamaBudaya;
    }

    public String getmAsalKotaBudaya() {
        return mAsalKotaBudaya;
    }

    public void setmAsalKotaBudaya(String mAsalKotaBudaya) {
        this.mAsalKotaBudaya = mAsalKotaBudaya;
    }

    public String getmGoogleSearchTerm() {
        return mGoogleSearchTerm;
    }

    public void setmGoogleSearchTerm(String mGoogleSearchTerm) {
        this.mGoogleSearchTerm = mGoogleSearchTerm;
    }

    public String getmImgURLBudaya() {
        return mImgURLBudaya;
    }

    public void setmImgURLBudaya(String mImgURLBudaya) {
        this.mImgURLBudaya = mImgURLBudaya;
    }

    public int getmImgResIDBudaya() {
        return mImgResIDBudaya;
    }

    public void setmImgResIDBudaya(int mImgResIDBudaya) {
        this.mImgResIDBudaya = mImgResIDBudaya;
    }
}
