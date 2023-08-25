package com.example.blog.Utils;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
    private List<T> data;
    private Long totalRecords;
    private int totalPages;

    public PageResponse(List<T> data, Long totalRecords, int totalPages) {
        this.data = data;
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
    }

    public PageResponse(com.example.blog.Utils.Page page) {
    }

    public PageResponse<T> setResponse(List<T> data, Page<?> page) {
        setData(data);
        setTotalPages(page.getTotalPages());
        setTotalRecords(page.getTotalElements());
        return this;
    }

    private void setTotalRecords(long totalElements) {
        this.totalRecords = totalElements;
    }

    private void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    private void setData(List<T> data) {
        this.data = data;
    }
}
