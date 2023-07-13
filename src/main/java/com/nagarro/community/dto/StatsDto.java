package com.nagarro.community.dto;

public class StatsDto {
    long userCount;
    long productCount;
    long reviewCount;
    public long getUserCount() {
        return userCount;
    }
    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }
    public long getProductCount() {
        return productCount;
    }
    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }
    public long getReviewCount() {
        return reviewCount;
    }
    public void setReviewCount(long reviewCount) {
        this.reviewCount = reviewCount;
    }
}
