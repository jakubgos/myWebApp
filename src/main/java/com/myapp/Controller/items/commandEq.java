package com.myapp.Controller.items;

/**
 * Created by Bos on 2017-01-09.
 */
public class commandEq {


    private long id;
    private String name;
    private Long producerId;
    private Long idEqSelected;

    public Long getIdEqSelected() {
        return idEqSelected;
    }

    public void setIdEqSelected(Long idEqSelected) {
        this.idEqSelected = idEqSelected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }
}
