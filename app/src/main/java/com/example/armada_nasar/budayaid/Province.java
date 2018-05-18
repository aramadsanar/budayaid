package com.example.armada_nasar.budayaid;

public class Province {
    private String name;
    private String friendly_name;
    private int id;

    public Province(String name, String friendly_name, int id) {
        this.name = name;
        this.friendly_name = friendly_name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFriendly_name() {
        return friendly_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFriendly_name(String friendly_name) {
        this.friendly_name = friendly_name;
    }
}
