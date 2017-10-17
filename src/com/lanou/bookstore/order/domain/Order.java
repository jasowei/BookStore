package com.lanou.bookstore.order.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class Order implements Serializable{
    private String oid;
    private String ordertime;
    private String total;
    private String state;
    private String uid;
    private String address;
    private List<OrderItem> orderList;


    public Order() {
    }

    public Order(String oid, String ordertime, String total, String state, String uid, String address) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.total = total;
        this.state = state;
        this.uid = uid;
        this.address = address;
    }

    public List<OrderItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderItem> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", total='" + total + '\'' +
                ", state='" + state + '\'' +
                ", uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                ", orderList=" + orderList +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
