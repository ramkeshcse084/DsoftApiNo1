package com.nestedmango.dsoftapino1;

/**
 * Created by Ramkesh Singh on 29-06-2017.
 */
public class CityPlace {
    private String name;
    private String id;
    private String cretedone;
    private String status;
    private String image;

    public CityPlace() {
        //this.name = name;
    }

    public CityPlace(String name, String id, String cretedone, String status,
                  String image) {
        super();
        this.name = name;
        this.id = id;
        this.cretedone = cretedone;
        this.status = status;
        this.image =image;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCretedone() {
        return cretedone;
    }

    public void setCretedone(String cretedone) {
        this.cretedone = cretedone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
