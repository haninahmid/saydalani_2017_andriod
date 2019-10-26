package com.example.haninahmid.saydalani.Model;

public class productClass {

    String Id,Picture,Id_pharmacy,Company_name,ProductNamElmy,Product_Name,Type,Country_of_manufacture,information,Production_date,Expiration_date;
            double Amount,Price;

    public productClass(String picture,String productNamElmy ,String id ,String id_pharmacy, String company_name, String product_Name,double amount, double price, String type, String country_of_manufacture,String Information, String production_date, String expiration_date) {
        Picture = picture;
        Id_pharmacy = id_pharmacy;
        Company_name = company_name;
        Product_Name = product_Name;
        Type = type;
        Country_of_manufacture = country_of_manufacture;
        Production_date = production_date;
        Expiration_date = expiration_date;
        Amount = amount;
        information = Information;
        Price = price;
        Id=id;
        ProductNamElmy=productNamElmy;
    }

    public String getProductNamElmy() {
        return ProductNamElmy;
    }

    public void setProductNamElmy(String productNamElmy) {
        ProductNamElmy = productNamElmy;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getId_pharmacy() {
        return Id_pharmacy;
    }

    public void setId_pharmacy(String id_pharmacy) {
        Id_pharmacy = id_pharmacy;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
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

    public String getCountry_of_manufacture() {
        return Country_of_manufacture;
    }

    public void setCountry_of_manufacture(String country_of_manufacture) {
        Country_of_manufacture = country_of_manufacture;
    }

    public String getProduction_date() {
        return Production_date;
    }

    public void setProduction_date(String production_date) {
        Production_date = production_date;
    }

    public String getExpiration_date() {
        return Expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        Expiration_date = expiration_date;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
