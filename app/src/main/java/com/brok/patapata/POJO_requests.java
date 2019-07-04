package com.brok.patapata;

public class POJO_requests {
    private String id;
    private Integer litres;


    public POJO_requests(String id, Integer litres) {
        this.id = id;
        this.litres = litres;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLitres() {
        return litres;
    }

    public void setLitres(Integer litres) {
        this.litres = litres;
    }
}
