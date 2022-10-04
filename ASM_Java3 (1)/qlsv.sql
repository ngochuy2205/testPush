CREATE DATABASE CSDL_JAVA3;
USE CSDL_JAVA3;

CREATE TABLE USERS (
    USERNAME NVARCHAR(50) PRIMARY KEY,
    PASSWORD NVARCHAR(50) NULL,
    ROLE NVARCHAR(50) NULL,
	ROLEID INT,
	STATUS INT,
)
CREATE TABLE STUDENTS (
   MASV NVARCHAR(50) PRIMARY KEY,
   HOTEN NVARCHAR(50) NULL,
   EMAIL NVARCHAR(50) NULL,
   SODT NVARCHAR(50) NULL,
   GIOITINH NVARCHAR(4) NULL,
   DIACHI NVARCHAR(60) NULL,
   HINH NVARCHAR(50) NULL,
)
CREATE TABLE GRADE (
    MASV NVARCHAR(50) PRIMARY KEY,
    TIENGANH INT NOT NULL,
    TINHOC INT NOT NULL,
    GDTC INT NOT NULL,
    FOREIGN KEY (MASV)
    REFERENCES STUDENTS(MASV),
)

INSERT INTO USERS VALUES
(N'anhqq','123',N'Cán bộ đào tạo',1,0),
(N'thudt','123',N'Giảng viên',0,0),
(N'hannt','123',N'Giảng viên',0,0),
(N'dungnt','123',N'Cán bộ đào tạo',1,0),
(N'huydt','123',N'Cán bộ đào tạo',1,0),
(N'huygv','123',N'Giảng viên',0,0);


INSERT INTO STUDENTS VALUES
('PS1',N'Nguyễn Ngọc Huy','nnhuy2205@gmail.com','0903414245',N'Nam',N'1096 Quang Trung P.8 Q.Gò Vấp, Thành phố Hồ Chí Minh','user1.png'),
('PS2',N'Nguyễn Thị Thu Hà','hannt1997@fpt.edu.vn','0937841642',N'Nữ',N'284/3c Phan Huy Ích','user2.png'),
('PS3',N'Nguyễn Thị Thu Hương','huongntt1998@fpt.edu.vn','0943624309',N'Nữ',N'98 Đinh Tiên Hoàng, Phường 1','user3.png'),
('PS4',N'Nguyễn Thế Dũng','dungnt1972@fpt.edu.vn','0354593621',N'Nam',N'124 Trần Quang Diệu','user4.png'),
('PS5',N'Nguyễn Thị Phấn','phannt1976@fpt.edu.vn','078785892',N'Nữ',N'122 Huỳnh Văn Bánh','user5.png'),
('PS6',N'Nguyễn Thị Thùy Trang','trangnttps22005@fpt.edu.vn','0358903651',N'Nữ',N'304-306 Nguyễn Trãi, Quận 5, TP. Hồ Chí Minh','user6.png'),
('PS7',N'Ngô Thu Uyên','uyenntps22006@fpt.edu.vn','0769631237',N'Nữ',N'107 Cao Văn Lầu, phường 1','user7.png'),
('PS8',N'Đào Thị Anh Thư','thu2003dao@gmail.com','0373857992',N'Nữ',N'504 Cách Mạng Tháng 8, Quận 3, TP. Hồ Chí Minh','user8.png'),
('PS9',N'Nguyễn Tiến Đạt','yenntps22008@fpt.edu.vn','0948647361',N'Nữ',N'412 Lê Văn Sỹ, Quận 3, TP. Hồ Chí Minh','user9.png');

INSERT INTO GRADE VALUES 
('PS1',7,8,8),
('PS3',9,9,9),
('PS5',7,7,8),
('PS6',10,9,9),
('PS8',10,10,9);

SELECT * FROM USERS
Select * from STUDENTS

SELECT ST.MASV, ST.HOTEN, ST.EMAIL, ST.SODT, ST.GIOITINH, ST.DIACHI,GR.TIENGANH,GR.TINHOC,GR.GDTC FROM STUDENTS AS ST INNER JOIN GRADE AS GR ON ST.MASV = GR.MASV