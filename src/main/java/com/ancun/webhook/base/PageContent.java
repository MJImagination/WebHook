package com.ancun.webhook.base;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
public class PageContent {

    private int page = 0;
    private int rows = 10;
    private String sort;
    private String order;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if(page >= 0 ){
            this.page = page;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if( rows > 0){
            this.rows = rows;
        }
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}