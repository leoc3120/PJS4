package com.example.pjs4;

public class Userinformation {

    public String Name;
    public String Height;
    public String Weight;
    public String Sex;
    public String Birth;
    public String Country;


    public Userinformation(){
    }

    public Userinformation(String Name,String Height, String Weight, String Sex, String Birth, String Country){
        this.Name = Name;
        this.Height = Height;
        this.Weight = Weight;
        this.Sex = Sex;
        this.Birth = Birth;
        this.Country = Country;

    }
    public String getUserName() {
        return Name;
    }
    public String getUserHeight() {
        return Height;
    }
    public String getUserWeight() {
        return Weight;
    }
    public String getUserSex() { return Sex; }
    public String getUserBirth() { return Birth; }
    public String getUserCountry() {
        return Country;
    }


}