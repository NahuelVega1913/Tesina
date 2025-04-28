package org.example.backendtesina.DTOs.Get;

import java.util.List;

public class GetCartDTO {
    private int id;

    private String user;

    List<GetSpareDTO> spareList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<GetSpareDTO> getSpareList() {
        return spareList;
    }

    public void setSpareList(List<GetSpareDTO> spareList) {
        this.spareList = spareList;
    }
}
