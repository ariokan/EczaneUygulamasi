package com.example.eczaneuygulamasi;

public class Eczane {

    private String name;
    private String tel;
    private String faks;
    private String sgk;
    private String address;
    private String x,y;
    private int id;

    public Eczane(String name, String tel, String faks, String sgk, String address, String x,String y,int id) {
        this.name = name;
        this.tel = tel;
        this.faks = faks;
        this.sgk = sgk;
        this.address = address;
        this.x = x;
        this.y=y;
    }
    public String getName(){

        return name;
    }
    public String setName(){
        return this.name=name;
    }
    public String getTel(){

        return tel;
    }
    public String setTel(){
        return this.tel=tel;
    }
    public String getFaks(){

        return faks;
    }
    public String setFaks(){
        return this.faks=faks;
    }
    public String getSgk(){

        return sgk;
    }
    public String setSgk(){
        return this.sgk=sgk;
    }
    public String getAddress(){

        return address;
    }
    public String Setx(){

        return this.x=x;
    }
    public String Getx(){

        return x;
    }
    public String Sety(){
        return this.y=y;
    }
    public String Gety(){

        return y;
    }
    public int GetId(){

        return id;
    }
    public  int SetId(){

        return this.id =id;
    }


}
