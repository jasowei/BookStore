package com.lanou.bookstore.user.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/9/28.
 */
public class China implements Serializable {
    private String province;
    private List<String> city;

    public China() {
    }

    public China(String province, List<String> city) {
        this.province = province;
        this.city = city;
    }

    @Override
    public String toString() {
        return "China{" +
                "province='" + province + '\'' +
                ", city=" + city +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }
}
