package com.ghoelzer_rht.cosmosdb.demo;

public class CityItem {
    private String city_id;   
    private String name;
    private String state_id;
    private String state_code;
    private String country_id;
    private String country_code;
    private String latitude;
    private String longitude;
    private String id;
    private String _rid;
    private String _self;
    private String _etag;
    private String _attachments;
    private String _ts;

    public CityItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_Id() {
        return city_id;
    }

    public void setCity_Id(String city_id) {
        this.city_id = city_id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public String getState_Id() {
        return state_id;
    }

    public void setState_Id(String state_id) {
        this.state_id = state_id;
    }
    
    public String getState_Code() {
        return state_id;
    }

    public void setState_Code(String state_code) {
        this.state_code = state_Code;
    }

}