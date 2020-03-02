/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Christopher
 */
public class User {

    private String name;
    private Long clicks;

    private Integer range = 30;

    public User() {
        clicks = 0L;
    }

    public User(String name, Long clicks) {
        this.name = name;
        this.clicks = clicks;
    }

    public void click() {
        clicks++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClics() {
        return clicks;
    }

    public void setClics(Long clics) {
        this.clicks = clics;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    @Override
    public String toString() {
        String retur = "";

        for (int i = 0; i < 100; i++) {
            if (i != clicks) {
                retur += "_";
            } else {
                retur += name;
            }
        }
        return retur;
    }
}
