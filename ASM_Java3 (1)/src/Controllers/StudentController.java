/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Student;
import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ps21313_NguyenNgocHuy
 */
public class StudentController {

    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=CSDL_JAVA3;encrypt=true;trustServerCertificate=true;";
    private static String username = "ngochuy_ADM";
    private static String password = "";

    public static List<Student> getAllStudentInfo() {
        List<Student> list = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stm = con.createStatement();
            String sql = "SELECT ST.MASV, ST.HOTEN, ST.EMAIL, ST.SODT, ST.GIOITINH, ST.DIACHI,GR.TIENGANH,GR.TINHOC,GR.GDTC FROM STUDENTS AS ST INNER JOIN GRADE AS GR ON ST.MASV = GR.MASV";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Student st = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                list.add(st);
            }
            rs.close();
            stm.close();
            con.close();
            return list;
        } catch (Exception e) {
            System.out.println("Loi lay tat ca thong tin sinh vien");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<Student> getAllStudent() {
        ArrayList<Student> list = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stm = con.createStatement();
            String sql = "select * from Students";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Student st = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(st);
            }
            rs.close();
            stm.close();
            con.close();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean addStudent(Student st) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO Students (MaSV, HoTen, Email, SoDT, GioiTinh, DiaChi, Hinh) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, st.getId());
            stm.setString(2, st.getName());
            stm.setString(3, st.getEmail());
            stm.setString(4, st.getPhone());
            stm.setString(5, st.getSex());
            stm.setString(6, st.getAddress());
            stm.setString(7, st.getHinh());
            stm.executeUpdate();
            stm.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Student findStudentById(String id) {
        try {
            Student std = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "Select * from Students where MaSV=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                std = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
            stm.close();
            con.close();
            return std;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean deleteStudent(String id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String deletesql = "Delete GRADE where MaSV=?";
            PreparedStatement stmDelete = con.prepareStatement(deletesql);
            stmDelete.setString(1, id);
            stmDelete.executeUpdate();
            stmDelete.close();
            String sql = "Delete Students where MaSV=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
            stm.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi xóa sinh viên");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateStudent(Student st) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "Update Students Set HoTen=?, Email=? , SoDT=?, GioiTinh=?, DiaChi=?, Hinh=? Where MaSV=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, st.getName());
            stm.setString(2, st.getEmail());
            stm.setString(3, st.getPhone());
            stm.setString(4, st.getSex());
            stm.setString(5, st.getAddress());
            stm.setString(6, st.getHinh());
            stm.setString(7, st.getId());
            stm.executeUpdate();
            stm.close();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<Student> getAllStudentWithGrade() {
        ArrayList<Student> list = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stm = con.createStatement();
            String sql = "SELECT ST.MASV, ST.HOTEN, ST.EMAIL, GR.TIENGANH, GR.TINHOC, GR.GDTC FROM STUDENTS AS ST LEFT JOIN GRADE AS GR ON ST.MASV=GR.MASV";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Student st = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                list.add(st);
            }
            rs.close();
            stm.close();
            con.close();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public static Student findStudentByIdWithGrade(String id) {
        try {
            Student std = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT ST.MASV, ST.HOTEN, ST.EMAIL, GR.TIENGANH, GR.TINHOC, GR.GDTC FROM STUDENTS AS ST LEFT JOIN GRADE AS GR ON ST.MASV=GR.MASV WHERE ST.MASV=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                std = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
            }
            stm.close();
            con.close();
            return std;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Loi tim sinh vien inner Bang diem");
            return null;
        }
    }
    
    public static Student findStudentByIdInGrade(String id) {
        try {
            Student std = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM GRADE WHERE MASV=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                std = new Student(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
            }
            stm.close();
            con.close();
            return std;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Loi tim sinh vien inner Bang diem");
            return null;
        }
    }

    public static boolean addStudentToGrade(Student st) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO GRADE (MaSV, TIENGANH, TINHOC, GDTC) VALUES(?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, st.getId());
            stm.setInt(2, st.getTienganh());
            stm.setInt(3, st.getTinhoc());
            stm.setInt(4, st.getGdtc());
            stm.executeUpdate();
            stm.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateStudentToGrade(Student st) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE GRADE SET TIENGANH=?, TINHOC=?, GDTC=? WHERE MASV=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, st.getTienganh());
            stm.setInt(2, st.getTinhoc());
            stm.setInt(3, st.getGdtc());
            stm.setString(4, st.getId());
            stm.executeUpdate();
            stm.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean deleteStudentGrade(String id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "DELETE GRADE WHERE MASV=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
            stm.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
