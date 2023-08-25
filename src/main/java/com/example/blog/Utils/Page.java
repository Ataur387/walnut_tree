package com.example.blog.Utils;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jdk.jfr.DataAmount;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.support.PageableExecutionUtils.getPage;
public class Page {
    protected int page = 0;
    protected int perPage = 10;
    protected List<String> sort = List.of("id");
    protected Sort.Direction direction = Sort.Direction.DESC;
    private String filter = null;

    public Page(int page, int perPage) {
        this.page = page;
        this.perPage = perPage;
    }
    public Page() {
    }

    public Page(int page, int perPage, List<String> sort, Sort.Direction direction, String filter) {
        this.page = page;
        this.perPage = perPage;
        this.sort = sort;
        this.direction = direction;
        this.filter = filter;
    }

    @JsonSetter
    public void setDirection(String direction) {
        if (direction == null)
            return;
        this.direction = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    @JsonGetter
    public String getDirection() {
        return direction.toString().toLowerCase();
    }

    public String getFilter() {
        if (filter == null || filter.isBlank())
            return null;
        return filter;
    }

    @JsonIgnore
    public Pageable getPageable() {
        List<Sort.Order> orders = new ArrayList<>();
        for (String s : getSort()) {
            s = s.trim();
            orders.add(new Sort.Order(direction, s));
        }
        return PageRequest.of(getPage(), getPerPage(), Sort.by(orders));
    }

    @JsonIgnore
    public <T> PageResponse<T> getResponse(List<T> data, org.springframework.data.domain.Page<?> page) {
        PageResponse response = new PageResponse(this);
        return response.setResponse(data, page);
    }

    public int getPage() {
        return this.page;
    }

    public int getPerPage() {
        return this.perPage;
    }

    public List<String> getSort() {
        return this.sort;
    }
}
