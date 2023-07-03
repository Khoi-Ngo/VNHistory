package com.swp.vnhistory.dto.request;

public class SearchQuizForm {
    private Long userId;
    private String keyword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public SearchQuizForm(Long userId, String keyword) {
        this.userId = userId;
        this.keyword = keyword;
    }
}
