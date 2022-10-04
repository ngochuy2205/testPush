/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Ps21313_NguyenNgocHuy
 */
public class Student {

    private String Id, Name, Email, Phone, sex, Address, Hinh;
    private int tienganh, tinhoc, gdtc;

    public Student() {
    }

    public Student(String Id, String Name, String Email, String Phone, String sex, String Address, String Hinh) {
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
        this.sex = sex;
        this.Address = Address;
        this.Hinh = Hinh;
    }

    public Student(String Id, String Name, String Email, String Phone, String sex, String Address, int tienganh, int tinhoc, int gdtc) {
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
        this.sex = sex;
        this.Address = Address;
        this.tienganh = tienganh;
        this.tinhoc = tinhoc;
        this.gdtc = gdtc;
    }

    public Student(String Id, String Name, String Email, int tienganh, int tinhoc, int gdtc) {
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.tienganh = tienganh;
        this.tinhoc = tinhoc;
        this.gdtc = gdtc;
    }

    public Student(String Id, int tienganh, int tinhoc, int gdtc) {
        this.Id = Id;
        this.tienganh = tienganh;
        this.tinhoc = tinhoc;
        this.gdtc = gdtc;
    }
    
    

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public int getTienganh() {
        return tienganh;
    }

    public void setTienganh(int tienganh) {
        this.tienganh = tienganh;
    }

    public int getTinhoc() {
        return tinhoc;
    }

    public void setTinhoc(int tinhoc) {
        this.tinhoc = tinhoc;
    }

    public int getGdtc() {
        return gdtc;
    }

    public void setGdtc(int gdtc) {
        this.gdtc = gdtc;
    }

    public double getTB() {
        return (double)(tienganh + tinhoc + gdtc) / 3;
    }
}
