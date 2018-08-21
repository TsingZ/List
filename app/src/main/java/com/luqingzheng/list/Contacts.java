package com.luqingzheng.list;

public class Contacts {
    private String name,subname;
    private int imageId;
    public Contacts(String name,String subname,int imageId){
        this.name = name;
        this.subname = subname;
        this.imageId = imageId;
    }

    public String getName()
    {
        return name;
    }

    public String getSubname()
    {
        return subname;
    }

    public int getImageId(){
        return imageId;
    }
}
