package com.example.haninahmid.saydalani.Model;

public class serchproductclass {
    double Longitude,Latitude;
    int Nighty,Pres_server;
    String Name,Address,Email,Telepon,Website,Facebook,Paymthod,Id,Product_Name,Type;
    String Image;
    String productNamElmy,Price,Country_of_manufacture,Picture;



    public serchproductclass(String product_Name, String ProductNamElmy, String price, String country_of_manufacture, String picture, String type, String id, String name, String address, String email, String website, String facebook, String paymthod, String telepon, double latitude, double longitude, int nighty, int per)
    {

        Product_Name=product_Name;
        Type=type;
        Id=id;
        Name=name;
        Pres_server=per;
        Address=address;
        Email=email;
        Telepon=telepon;
        Website=website;
        Facebook=facebook;
        Paymthod=paymthod;
        Nighty=nighty;
        Latitude=latitude;
        Longitude=longitude;

       productNamElmy= ProductNamElmy;
        Price=price;
        Country_of_manufacture=country_of_manufacture;
        Picture=picture;



    }
    public String getProductNamElmy() {
        return productNamElmy;
    }

    public void setProductNamElmy(String productNamElmy) {
        this.productNamElmy = productNamElmy;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCountry_of_manufacture() {
        return Country_of_manufacture;
    }

    public void setCountry_of_manufacture(String country_of_manufacture) {
        Country_of_manufacture = country_of_manufacture;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }
    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(int longitude) {
        this.Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(int latitude) {
        this.Latitude = latitude;
    }

    public int getNighty() {
        return Nighty;
    }

    public void setNighty(int nighty) {
        this.Nighty = nighty;
    }

    public int getPres_server() {
        return Pres_server;
    }

    public void setPres_server(int pres_server) {
        Pres_server = pres_server;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelepon() {
        return Telepon;
    }

    public void setTelepon(String telepon) {
        Telepon = telepon;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getPaymthod() {
        return Paymthod;
    }

    public void setPaymthod(String paymthod) {
        Paymthod = paymthod;
    }



}
