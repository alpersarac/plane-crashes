package com.example.alp.planecrashes;

public class Person {
    private String name="";
    private String address="";
    private String photoURL="";

    public Person(String name,String address,  String photoURL) {
        this.name = name;
        this.address = address;
        this.photoURL = photoURL;

    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {return address;}

}
