USE [master]
GO
/****** Object:  Database [TheHotel]    Script Date: 11/7/2020 9:04:41 PM ******/
CREATE DATABASE [TheHotel]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TheHotel', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\TheHotel.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'TheHotel_log', FILENAME = N'D:\LogsSqlServer\TheHotel_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [TheHotel] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TheHotel].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TheHotel] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TheHotel] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TheHotel] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TheHotel] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TheHotel] SET ARITHABORT OFF 
GO
ALTER DATABASE [TheHotel] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TheHotel] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TheHotel] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TheHotel] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TheHotel] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TheHotel] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TheHotel] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TheHotel] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TheHotel] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TheHotel] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TheHotel] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TheHotel] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TheHotel] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TheHotel] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TheHotel] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TheHotel] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TheHotel] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TheHotel] SET RECOVERY FULL 
GO
ALTER DATABASE [TheHotel] SET  MULTI_USER 
GO
ALTER DATABASE [TheHotel] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TheHotel] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TheHotel] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TheHotel] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [TheHotel] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [TheHotel] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'TheHotel', N'ON'
GO
ALTER DATABASE [TheHotel] SET QUERY_STORE = OFF
GO
USE [TheHotel]
GO
/****** Object:  Table [dbo].[tbl_Area]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Area](
	[areaID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[areaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Category]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Category](
	[categoryID] [int] IDENTITY(1,1) NOT NULL,
	[type] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_CodeResetPass]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_CodeResetPass](
	[Email] [varchar](50) NULL,
	[Code] [varchar](50) NULL,
	[Status] [varchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Discount]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Discount](
	[discountID] [varchar](50) NOT NULL,
	[code] [nchar](20) NULL,
	[discount] [int] NOT NULL,
	[status] [varchar](50) NULL,
	[datecreate] [date] NULL,
 CONSTRAINT [PK__tbl_Disc__D2130A062D75A52F] PRIMARY KEY CLUSTERED 
(
	[discountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Feedback]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Feedback](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[content] [nvarchar](200) NULL,
	[rate] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[feedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Hotel]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Hotel](
	[hotelID] [int] IDENTITY(1,1) NOT NULL,
	[areaID] [int] NULL,
	[name] [nvarchar](50) NULL,
	[address] [nvarchar](250) NULL,
	[status] [varchar](50) NULL,
 CONSTRAINT [PK__tbl_Hote__17ADC4928AF918E3] PRIMARY KEY CLUSTERED 
(
	[hotelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Order]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Order](
	[orderID] [int] IDENTITY(300620000,1) NOT NULL,
	[userID] [varchar](50) NULL,
	[totalprice] [real] NULL,
	[dateorder] [date] NULL,
	[status] [nchar](10) NULL,
	[feedbackID] [int] NULL,
	[discountID] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_OrderDetails]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_OrderDetails](
	[detailsID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [int] NULL,
	[quantity] [int] NULL,
	[datecheckin] [date] NULL,
	[datecheckout] [date] NULL,
	[hotelID] [int] NULL,
 CONSTRAINT [PK__tbl_Orde__EB8EA79068EB7C94] PRIMARY KEY CLUSTERED 
(
	[detailsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_OTPCode]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_OTPCode](
	[Email] [varchar](50) NULL,
	[OTP] [varchar](50) NULL,
	[Status] [nchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[roleID] [int] NOT NULL,
	[type] [nvarchar](50) NULL,
 CONSTRAINT [PK__tbl_Role__CD98460A5007032C] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Room]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Room](
	[hotelID] [int] NULL,
	[descriptioin] [nvarchar](200) NULL,
	[quantity] [int] NULL,
	[price] [real] NULL,
	[categoryID] [int] NULL,
	[status] [nchar](10) NULL,
	[roomAvailable] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Users]    Script Date: 11/7/2020 9:04:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Users](
	[userID] [varchar](50) NOT NULL,
	[password] [varchar](300) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[phone] [nchar](20) NOT NULL,
	[address] [nvarchar](200) NULL,
	[createDate] [date] NOT NULL,
	[roleID] [int] NOT NULL,
	[status] [varchar](50) NULL,
	[generateCode] [varchar](50) NULL,
 CONSTRAINT [PK__tbl_User__CB9A1CDFAD6D5A7E] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tbl_Area] ON 

INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (1, N'An Giang')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (2, N'Bà Rịa - Vũng Tàu
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (3, N'Bắc Giang
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (4, N'Bắc Kạn
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (5, N'Bạc Liêu
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (6, N'Bắc Ninh
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (7, N'Bến Tre
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (8, N'Bình Định
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (9, N'Bình Dương
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (10, N'Bình Phước
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (11, N'Bình Thuận
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (12, N'Cà Mau
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (13, N'Dak Lak')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (14, N'Cao Bằng
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (15, N'Đắk Nông
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (16, N'Điện Biên
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (17, N'Đồng Nai
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (18, N'Đồng Tháp
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (19, N'Gia Lai
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (20, N'Hà Giang	')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (21, N'Hà Nam
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (22, N'Hà Tĩnh
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (23, N'Hải Dương
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (24, N'Hậu Giang
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (25, N'Hòa Bình
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (26, N'Hưng Yên
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (27, N'Khánh Hòa
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (28, N'Kiên Giang
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (29, N'Kon Tum
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (30, N'Lai Châu
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (31, N'Lâm Đồng
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (32, N'Lạng Sơn
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (33, N'Lào Cai
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (34, N'Long An
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (35, N'Nam Định
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (36, N'Nghệ An
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (37, N'Ninh Bình
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (38, N'Ninh Thuận
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (39, N'Phú Thọ
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (40, N'Quảng Bình')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (41, N'Quảng Nam
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (42, N'Quảng Ngãi
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (43, N'Quảng Ninh
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (44, N'Quảng Trị
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (45, N'Sóc Trăng
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (46, N'Sơn La
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (47, N'Tây Ninh
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (48, N'Thái Bình
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (49, N'Thái Nguyên
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (50, N'Thanh Hóa
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (51, N'Thừa Thiên Huế
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (52, N'Tiền Giang
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (53, N'Trà Vinh
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (54, N'Tuyên Quang
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (55, N'Vĩnh Long
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (56, N'Vĩnh Phúc
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (57, N'Yên Bái
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (58, N'Phú Yên	')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (59, N'Cần Thơ
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (60, N'Đà Nẵng
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (61, N'Hải Phòng
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (62, N'Hà Nội
')
INSERT [dbo].[tbl_Area] ([areaID], [name]) VALUES (63, N'TP HCM
')
SET IDENTITY_INSERT [dbo].[tbl_Area] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_Category] ON 

INSERT [dbo].[tbl_Category] ([categoryID], [type]) VALUES (1, N'Double')
INSERT [dbo].[tbl_Category] ([categoryID], [type]) VALUES (2, N'Single')
INSERT [dbo].[tbl_Category] ([categoryID], [type]) VALUES (3, N'Family')
INSERT [dbo].[tbl_Category] ([categoryID], [type]) VALUES (4, N'Large')
INSERT [dbo].[tbl_Category] ([categoryID], [type]) VALUES (5, N'Couple')
SET IDENTITY_INSERT [dbo].[tbl_Category] OFF
GO
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'06RE35N87YZPAEC4O5H00C5YG', N'Not Yet')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'K55K8XAPK0BLKQCFDTXNTEM2S', N'Not Yet')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'XPR4AB205OHZJQBKSR33Z3WNW', N'Not Yet')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'XEJKYBUA7HJ8403HN5X7GAIHY', N'Not Yet')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'DMMFLHAZIQIH19O6KHE7RT60M', N'Active')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'Y6GJEV99WK66YVLW4YKUOD81S', N'Active')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'UJAGI0ZFTAINVGJJJZUIM54TI', N'Active')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgtse151537@fpt.edu.vn', N'2U4N7M5V4A8ROU6YFN5U3Y8Q3', N'Active')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgtse151537@fpt.edu.vn', N'MQH7FHOPAE6IZVPLRLVXRJ0CU', N'Active')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgtse151537@fpt.edu.vn', N'CH41AZ52LL3SJ097O8C80JPS4', N'Active')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'AL0939XSS5S7ZMUXGXFAD1IKC', N'Active')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'FNIAIIMAPNS4MC056WNFP2OWK', N'Active')
INSERT [dbo].[tbl_CodeResetPass] ([Email], [Code], [Status]) VALUES (N'dinhgiang443@gmail.com', N'ZIUOSZVCI1QTZ9SMEG0AV98XM', N'Active')
GO
INSERT [dbo].[tbl_Discount] ([discountID], [code], [discount], [status], [datecreate]) VALUES (N'FREEFEE01', N'Giam Phi Dat Phong  ', 10, N'Active', CAST(N'2020-11-07' AS Date))
INSERT [dbo].[tbl_Discount] ([discountID], [code], [discount], [status], [datecreate]) VALUES (N'FREEFEE02', N'Giang DInh          ', 10, N'Active', CAST(N'2020-11-07' AS Date))
GO
SET IDENTITY_INSERT [dbo].[tbl_Hotel] ON 

INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (1, 63, N'Orchids Saigon Hotel', N' 192 Pasteur Street, Ward 6, District 3, Quận 3, TP. Hồ Chí Minh, Việt Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (3, 63, N'Fusion Suites Saigon', N' 3-5 Suong Nguyet Anh, Quận 1, TP. Hồ Chí Minh, Việt Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (4, 63, N'Fusion Suites Saigon', N'73 - 75 Thu Khoa Huan, Quận 1, TP. Hồ Chí Minh, Việt Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (5, 63, N'Silverland Yen Hotel', N'73 - 75 Thu Khoa Huan, Quận 1, TP. Hồ Chí Minh, Việt Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (6, 63, N'Chez Mimosa Petite', N'135/46 Tran Hung Dao, District 1, Quận 1, TP. Hồ Chí Minh, Việt Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (7, 63, N'Chez Mimosa Local', N'Đường Đề Thám 88/2, Quận 1, TP. Hồ Chí Minh, Việt Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (8, 63, N'Thanh Long Hotel - Tra Khuc 
', N'18 Trà Khúc, Quận Tân Bình, TP. Hồ Chí Minh, Việt Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (9, 63, N'HOSTIE SAIGON', N'214/B15 Nguyễn Trãi 214/B15 Nguyễn Trãi, P. Nguyễn Cư Trinh, HCM, Quận 1, TP. Hồ Chí Minh, Việt Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (10, 60, N'Luxury DN', N' 3-5 Hai Ba Trung, TP. Da Nang, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (11, 60, N'Muong Thanh DN', N' 22-27 Suong Nguyet Anh, TP. Da Nang, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (12, 60, N'May ', N' 3 Anh Khoa, TP. Da Nang, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (13, 60, N'Bouliver ', N' 3-5 Suong Nguyet Anh, TP. Da Nang, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (14, 59, N'Vinpearl Hotel Can Tho', N'209, 30/4 Road, Xuan Khanh Ward, Ninh Kieu District, Câ`n Tho, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (15, 59, N'Nesta Can Tho Hotel', N'9C Tran Phu, Câ`n Tho, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (16, 59, N'Con Khuong Resort Can Tho', N'99A, Nguy?n H?u C?u, C?n Khuong, Qu?n Ninh Ki?u, Thành ph? C?n Tho 99A', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (17, 59, N'VWest Hotel', N'88-90-92 Hai Ba Trung, Câ`n Tho, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (18, 59, N'LION HOTEL', N'63-65 Ðu?ng Hùng Vuong, Phu?ng Th?i Bình, Qu?n Ninh Ki?u, thành ph? C?n Tho, Câ`n Tho, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (19, 59, N'KP Hotel', N'09 Nam K? Kh?i Nghia, phu?ng Tân An, qu?n Ninh Ki?u, Câ`n Tho, Viê?t Nam', N'Active')
INSERT [dbo].[tbl_Hotel] ([hotelID], [areaID], [name], [address], [status]) VALUES (20, 59, N'Can Tho Ecolodge', N'542, Area No.3, Ba Lang Ward, Cai Rang District, Câ`n Tho, Viê?t Nam', N'Active')
SET IDENTITY_INSERT [dbo].[tbl_Hotel] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_Order] ON 

INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620000, N'12', 100000, CAST(N'2020-03-11' AS Date), N'inActive  ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620001, N'12', 12315132, CAST(N'2020-03-11' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620002, N'12', 1499999, CAST(N'2020-11-06' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620003, N'12', 1499999, CAST(N'2020-11-06' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620004, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-06' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620005, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-06' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620006, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-06' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620007, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-06' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620008, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-06' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620009, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-06' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620010, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-06' AS Date), N'Confirm   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620011, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-07' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620012, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-07' AS Date), N'Confirm   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620013, N'dinhgiang443@gmail.com', 1499999, CAST(N'2020-11-07' AS Date), N'Confirm   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620014, N'dinhgiang443@gmail.com', 1527865, CAST(N'2020-11-07' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620018, N'dinhgiang443@gmail.com', 1253789, CAST(N'2020-11-07' AS Date), N'Confirm   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620019, N'dinhgiang443@gmail.com', 125378.9, CAST(N'2020-11-07' AS Date), N'Confirm   ', NULL, N'FREEFEE01')
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620020, N'dinhgiang443@gmail.com', 1732711.5, CAST(N'2020-11-07' AS Date), N'Confirm   ', NULL, N'FREEFEE01')
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620021, N'dinhgiang443@gmail.com', 139711.5, CAST(N'2020-11-07' AS Date), N'Confirm   ', NULL, N'FREEFEE01')
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620022, N'dinhgiang443@gmail.com', 1253789, CAST(N'2020-11-07' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620023, N'dinhgiang443@gmail.com', 1253789, CAST(N'2020-11-07' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620024, N'dinhgiang443@gmail.com', 1253789, CAST(N'2020-11-07' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620025, N'dinhgiang443@gmail.com', 1253789, CAST(N'2020-11-07' AS Date), N'Pending   ', NULL, NULL)
INSERT [dbo].[tbl_Order] ([orderID], [userID], [totalprice], [dateorder], [status], [feedbackID], [discountID]) VALUES (300620026, N'dinhgiang443@gmail.com', 1253789, CAST(N'2020-11-07' AS Date), N'Pending   ', NULL, NULL)
SET IDENTITY_INSERT [dbo].[tbl_Order] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_OrderDetails] ON 

INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1, 300620000, 1, CAST(N'2020-03-11' AS Date), CAST(N'2020-03-11' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (2, 300620000, 3, CAST(N'2020-05-11' AS Date), CAST(N'2020-05-11' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1002, 300620003, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1003, 300620004, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-08' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1004, 300620005, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1005, 300620006, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1006, 300620007, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1007, 300620008, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-10' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1008, 300620009, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1009, 300620010, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-08' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1010, 300620011, 1, CAST(N'2020-11-07' AS Date), CAST(N'2020-11-09' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1011, 300620012, 1, CAST(N'2020-11-07' AS Date), CAST(N'2020-11-08' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1012, 300620013, 1, CAST(N'2020-11-07' AS Date), CAST(N'2020-11-09' AS Date), 1)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1013, 300620014, 1, CAST(N'2020-11-13' AS Date), CAST(N'2020-11-17' AS Date), 4)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1014, 300620018, 1, CAST(N'2020-11-07' AS Date), CAST(N'2020-11-09' AS Date), 3)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1015, 300620019, 1, CAST(N'2020-11-07' AS Date), CAST(N'2020-11-08' AS Date), 3)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1016, 300620020, 1, CAST(N'2020-11-07' AS Date), CAST(N'2020-11-08' AS Date), 14)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1017, 300620021, 1, CAST(N'2020-11-07' AS Date), CAST(N'2020-11-08' AS Date), 14)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1018, 300620022, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), 3)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1019, 300620023, 1, CAST(N'2020-11-05' AS Date), CAST(N'2020-11-08' AS Date), 3)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1020, 300620024, 1, CAST(N'2020-11-06' AS Date), CAST(N'2020-11-08' AS Date), 3)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1021, 300620025, 1, CAST(N'2020-11-05' AS Date), CAST(N'2020-11-07' AS Date), 3)
INSERT [dbo].[tbl_OrderDetails] ([detailsID], [orderID], [quantity], [datecheckin], [datecheckout], [hotelID]) VALUES (1022, 300620026, 1, CAST(N'2020-11-05' AS Date), CAST(N'2020-11-07' AS Date), 3)
SET IDENTITY_INSERT [dbo].[tbl_OrderDetails] OFF
GO
INSERT [dbo].[tbl_OTPCode] ([Email], [OTP], [Status]) VALUES (N'dinh3135134162@gmail.com', N'3OGVUK', N'Active    ')
INSERT [dbo].[tbl_OTPCode] ([Email], [OTP], [Status]) VALUES (N'dinhgiang443@gmail.com', N'I6VZAV', N'Active    ')
INSERT [dbo].[tbl_OTPCode] ([Email], [OTP], [Status]) VALUES (N'dinhgtce140538@fpt.edu.vn', N'5991P2', N'Active    ')
GO
INSERT [dbo].[tbl_Role] ([roleID], [type]) VALUES (1, N'Admin')
INSERT [dbo].[tbl_Role] ([roleID], [type]) VALUES (2, N'Member')
INSERT [dbo].[tbl_Role] ([roleID], [type]) VALUES (3, N'Guest')
GO
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (1, N' Phòng Deluxe Giường Đôi/2 Giường Đơn Nhìn Ra Thành Phố', 5, 1499999, 1, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (1, N'Phòng Premium Deluxe Giường Đôi/2 Giường Đơn Nhìn ra Thành phố', 5, 1415682, 2, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (1, N' Suite Giường đôi/2 Giường đơn Nhìn ra Thành phố', 5, 1234578, 3, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (1, N'Suite Gia đình nhìn ra Thành phố', 5, 1948723, 3, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (3, N'Quy Hung Hotel', 5, 1253789, 1, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (4, N'The Myst Dong Khoi', 5, 1527865, 2, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (3, N'Sử Dụng Trong Ngày (4 Tiếng) - Phòng Tiêu Chuẩn Giường Đôi
', 5, 123341, 1, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (3, N'Phòng Superior Giường Đôi (Cửa Sổ Bên Trong)', 5, 123124, 2, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (4, N'Phòng Superior 2 Giường Đơn (Cửa Sổ Bên Trong)', 5, 1234552, 3, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (5, N'Phòng Deluxe 2 Giường Đơn (Cửa Sổ Bên Trong', 5, 1234533, 1, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (5, N'Phòng Deluxe Có Giường Cỡ King
', 5, 2331213, 2, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (14, N'Phòng Superior 2 Giu?ng Ðon', 5, 155235, 2, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (14, N'Phòng Deluxe 2 Giu?ng Ðon Nhìn Ra Thành Ph?', 5, 1925235, 2, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (14, N'Phòng Giu?ng Ðôi Deluxe nhi`n ra Sông', 5, 1788235, 1, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (16, N'Phòng Superior 2 Giu?ng Ðon', 5, 155235, 2, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (16, N'Phòng Deluxe 2 Giu?ng Ðon Nhìn Ra Thành Ph?', 5, 1925235, 2, N'Active    ', 5)
INSERT [dbo].[tbl_Room] ([hotelID], [descriptioin], [quantity], [price], [categoryID], [status], [roomAvailable]) VALUES (16, N'Phòng Giu?ng Ðôi Deluxe nhi`n ra Sông', 5, 1788235, 1, N'Active    ', 5)
GO
INSERT [dbo].[tbl_Users] ([userID], [password], [name], [phone], [address], [createDate], [roleID], [status], [generateCode]) VALUES (N'12', N'4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce', N'Dinh', N'0333322189          ', N'ádasdasdasdads', CAST(N'2020-11-02' AS Date), 1, N'Active', NULL)
INSERT [dbo].[tbl_Users] ([userID], [password], [name], [phone], [address], [createDate], [roleID], [status], [generateCode]) VALUES (N'dinh3135134162@gmail.com', N'932f3c1b56257ce8539ac269d7aab42550dacf8818d075f0bdf1990562aae3ef', N'Giang Thanh DInh', N'0333322189          ', N'Ã¡dadfadfsafgasdfgsdfgsdgfsdgh', CAST(N'2020-11-02' AS Date), 2, N'Active', NULL)
INSERT [dbo].[tbl_Users] ([userID], [password], [name], [phone], [address], [createDate], [roleID], [status], [generateCode]) VALUES (N'dinhgiang443@gmail.com', N'932f3c1b56257ce8539ac269d7aab42550dacf8818d075f0bdf1990562aae3ef', N'Giang Thanh DInh', N'0333322189          ', N'ÃÂ¡dadfadfsafgasdfgsdfgsdgfsdgh', CAST(N'2020-11-02' AS Date), 2, N'Active', N'EQN7ANJ8KKQHZI1DVSMLNSDX2')
INSERT [dbo].[tbl_Users] ([userID], [password], [name], [phone], [address], [createDate], [roleID], [status], [generateCode]) VALUES (N'dinhgtce140538@fpt.edu.vn', N'932f3c1b56257ce8539ac269d7aab42550dacf8818d075f0bdf1990562aae3ef', N'Giang Thanh DInh', N'0333322189          ', N'Ã¡dadfadfsafgasdfgsdfgsdgfsdgh', CAST(N'2020-11-07' AS Date), 2, N'Active', NULL)
INSERT [dbo].[tbl_Users] ([userID], [password], [name], [phone], [address], [createDate], [roleID], [status], [generateCode]) VALUES (N'dinhgtse151537@fpt.edu.vn', N'932f3c1b56257ce8539ac269d7aab42550dacf8818d075f0bdf1990562aae3ef', N'Giang Thanh DInh', N'0333322189          ', N'Ã¡dadfadfsafgasdfgsdfgsdgfsdgh', CAST(N'2020-11-07' AS Date), 2, N'Active', NULL)
GO
ALTER TABLE [dbo].[tbl_CodeResetPass]  WITH CHECK ADD  CONSTRAINT [FK_tbl_CodeResetPass_tbl_Users] FOREIGN KEY([Email])
REFERENCES [dbo].[tbl_Users] ([userID])
GO
ALTER TABLE [dbo].[tbl_CodeResetPass] CHECK CONSTRAINT [FK_tbl_CodeResetPass_tbl_Users]
GO
ALTER TABLE [dbo].[tbl_Hotel]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Hotel_tbl_Area] FOREIGN KEY([areaID])
REFERENCES [dbo].[tbl_Area] ([areaID])
GO
ALTER TABLE [dbo].[tbl_Hotel] CHECK CONSTRAINT [FK_tbl_Hotel_tbl_Area]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_tbl_Discount] FOREIGN KEY([discountID])
REFERENCES [dbo].[tbl_Discount] ([discountID])
GO
ALTER TABLE [dbo].[tbl_Order] CHECK CONSTRAINT [FK_tbl_Order_tbl_Discount]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_tbl_Feedback] FOREIGN KEY([feedbackID])
REFERENCES [dbo].[tbl_Feedback] ([feedbackID])
GO
ALTER TABLE [dbo].[tbl_Order] CHECK CONSTRAINT [FK_tbl_Order_tbl_Feedback]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_tbl_Users] FOREIGN KEY([userID])
REFERENCES [dbo].[tbl_Users] ([userID])
GO
ALTER TABLE [dbo].[tbl_Order] CHECK CONSTRAINT [FK_tbl_Order_tbl_Users]
GO
ALTER TABLE [dbo].[tbl_OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OrderDetails_tbl_Hotel] FOREIGN KEY([hotelID])
REFERENCES [dbo].[tbl_Hotel] ([hotelID])
GO
ALTER TABLE [dbo].[tbl_OrderDetails] CHECK CONSTRAINT [FK_tbl_OrderDetails_tbl_Hotel]
GO
ALTER TABLE [dbo].[tbl_OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OrderDetails_tbl_Order] FOREIGN KEY([orderID])
REFERENCES [dbo].[tbl_Order] ([orderID])
GO
ALTER TABLE [dbo].[tbl_OrderDetails] CHECK CONSTRAINT [FK_tbl_OrderDetails_tbl_Order]
GO
ALTER TABLE [dbo].[tbl_OTPCode]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OTPCode_tbl_Users] FOREIGN KEY([Email])
REFERENCES [dbo].[tbl_Users] ([userID])
GO
ALTER TABLE [dbo].[tbl_OTPCode] CHECK CONSTRAINT [FK_tbl_OTPCode_tbl_Users]
GO
ALTER TABLE [dbo].[tbl_Room]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Room_tbl_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tbl_Category] ([categoryID])
GO
ALTER TABLE [dbo].[tbl_Room] CHECK CONSTRAINT [FK_tbl_Room_tbl_Category]
GO
ALTER TABLE [dbo].[tbl_Room]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Room_tbl_Hotel] FOREIGN KEY([hotelID])
REFERENCES [dbo].[tbl_Hotel] ([hotelID])
GO
ALTER TABLE [dbo].[tbl_Room] CHECK CONSTRAINT [FK_tbl_Room_tbl_Hotel]
GO
ALTER TABLE [dbo].[tbl_Users]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Users_tbl_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[tbl_Role] ([roleID])
GO
ALTER TABLE [dbo].[tbl_Users] CHECK CONSTRAINT [FK_tbl_Users_tbl_Role]
GO
USE [master]
GO
ALTER DATABASE [TheHotel] SET  READ_WRITE 
GO
