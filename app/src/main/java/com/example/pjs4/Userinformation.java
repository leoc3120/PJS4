package com.example.pjs4;

public class Userinformation {

    public String Name;
    public String Sex;
    public String Birth;
    public String Country;
    public String Weight;
    public String Height;


    public Userinformation(){
    }

    public Userinformation(String Name,String Sex, String Birth, String Country, String Weight, String Height){
        this.Name = Name;
        this.Sex = Sex;
        this.Birth = Birth;
        this.Country = Country;
        this.Weight = Weight;
        this.Height = Height;
    }
    public String getUserName() {
        return Name;
    }
    public String getUserBirth() {
        return Birth;
    }
    public String getUserSex() {
        return Sex;
    }
    public String getUserCountry() {
        return Country;
    }
    public String getUserWeight() {
        return Weight;
    }
    public String getUserHeight() {
        return Height;
    }
}