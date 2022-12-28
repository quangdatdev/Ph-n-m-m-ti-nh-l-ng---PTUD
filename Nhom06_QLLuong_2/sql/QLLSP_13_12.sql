

use master
create database QLLuongSPC
go
USE [QLLuongSPC]
GO
/****** Object:  Table [dbo].[CaLamCN]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamCN](
	[maCa] [nvarchar](10) NOT NULL,
	[tenCa] [nvarchar](50) NULL,
	[gioTheoCa] [nvarchar](50) NULL,
	[luongTheoCa] [nchar](10) NULL,
 CONSTRAINT [PK_CaLamCN] PRIMARY KEY CLUSTERED 
(
	[maCa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CaLamNV]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamNV](
	[maCa] [nvarchar](10) NOT NULL,
	[tenCa] [nvarchar](50) NULL,
	[gioTheoCa] [nvarchar](50) NULL,
	[luongTheoCa] [money] NULL,
 CONSTRAINT [PK_CaLamNV] PRIMARY KEY CLUSTERED 
(
	[maCa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChamCongCN]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongCN](
	[maCong] [nvarchar](10) NOT NULL,
	[soSP] [int] NULL,
	[ngayCham] [date] NULL,
	[maCa] [nvarchar](10) NOT NULL,
	[nghiPhep] [bit] NULL,
	[maPC] [nvarchar](10) NOT NULL,
	[trangThai] [bit] NULL,
 CONSTRAINT [PK_ChamCongCN] PRIMARY KEY CLUSTERED 
(
	[maCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChamCongNV]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongNV](
	[maCong] [nvarchar](10) NOT NULL,
	[ngayCham] [date] NULL,
	[trangThai] [bit] NULL,
	[nghiPhep] [bit] NULL,
	[maNV] [nvarchar](10) NOT NULL,
	[maCa] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_ChamCongNV] PRIMARY KEY CLUSTERED 
(
	[maCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongDoanSanXuat]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongDoanSanXuat](
	[maCD] [nvarchar](10) NOT NULL,
	[tenCD] [nvarchar](50) NULL,
	[giaCD] [money] NULL,
	[soLuong] [int] NULL,
	[giaiDoan] [int] NULL,
	[maSP] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_CongDoanSanXuat] PRIMARY KEY CLUSTERED 
(
	[maCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongNhan]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongNhan](
	[maCN] [nvarchar](10) NOT NULL,
	[hoTen] [nvarchar](50) NULL,
	[sdt] [nvarchar](50) NULL,
	[diaChi] [nvarchar](50) NULL,
	[soCMND] [nvarchar](50) NULL,
	[namSinh] [int] NULL,
	[gioiTinh] [nvarchar](50) NULL,
	[maTayNghe] [nvarchar](10) NOT NULL,
	[troCap] [money] NULL,
 CONSTRAINT [PK_CongNhan] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LuongCN]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LuongCN](
	[maLuongCN] [nvarchar](10) NOT NULL,
	[thang] [int] NULL,
	[nam] [int] NULL,
	[maCong] [nvarchar](10) NOT NULL,
	[luong] [money] NULL,
 CONSTRAINT [PK_LuongCN] PRIMARY KEY CLUSTERED 
(
	[maLuongCN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LuongNV]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LuongNV](
	[maLuongNV] [nvarchar](10) NOT NULL,
	[thang] [int] NULL,
	[nam] [int] NULL,
	[maCong] [nvarchar](10) NOT NULL,
	[luong] [money] NULL,
 CONSTRAINT [PK_LuongNV] PRIMARY KEY CLUSTERED 
(
	[maLuongNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [nvarchar](10) NOT NULL,
	[hoTen] [nvarchar](50) NULL,
	[soDT] [nvarchar](50) NULL,
	[soCMND] [nvarchar](50) NULL,
	[diaChi] [nvarchar](50) NULL,
	[gioiTinh] [nvarchar](50) NULL,
	[namSinh] [int] NULL,
	[maTrinhDo] [nvarchar](10) NOT NULL,
	[luongCB] [money] NULL,
	[troCap] [money] NULL,
	[heSoLuong] [money] NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhanCongCN]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanCongCN](
	[maPC] [nvarchar](10) NOT NULL,
	[maCN] [nvarchar](10) NULL,
	[maCD] [nvarchar](10) NULL,
	[soLuong] [int] NULL,
	[ngay] [date] NULL,
 CONSTRAINT [PK_PhanCongCN] PRIMARY KEY CLUSTERED 
(
	[maPC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSP] [nvarchar](10) NOT NULL,
	[tenSP] [nvarchar](50) NULL,
	[kieuDang] [nvarchar](50) NULL,
	[chatLieu] [nvarchar](50) NULL,
	[soLuong] [int] NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[tenDangNhap] [nvarchar](10) NOT NULL,
	[matKhau] [nchar](10) NOT NULL,
	[maNV] [nvarchar](10) NULL,
	[maCN] [nvarchar](10) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[tenDangNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TayNghe]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TayNghe](
	[maTayNghe] [nvarchar](10) NOT NULL,
	[tenTayNghe] [nvarchar](50) NULL,
 CONSTRAINT [PK_TayNghe] PRIMARY KEY CLUSTERED 
(
	[maTayNghe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TrinhDo]    Script Date: 11/18/2022 7:32:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TrinhDo](
	[maTrinhDo] [nvarchar](10) NOT NULL,
	[tenTrinhDo] [nvarchar](50) NULL,
 CONSTRAINT [PK_TrinhDo] PRIMARY KEY CLUSTERED 
(
	[maTrinhDo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CaLamCN] ([maCa], [tenCa], [gioTheoCa], [luongTheoCa]) VALUES (N'CACN01', N'Sáng', N'7h-11h', N'1500000   ')
INSERT [dbo].[CaLamCN] ([maCa], [tenCa], [gioTheoCa], [luongTheoCa]) VALUES (N'CACN02', N'Chiều', N'12h30-16h30', N'2000000   ')
GO
INSERT [dbo].[CaLamNV] ([maCa], [tenCa], [gioTheoCa], [luongTheoCa]) VALUES (N'CANV01', N'Chiều', N'12h30-16h30', 2500000.0000)
INSERT [dbo].[CaLamNV] ([maCa], [tenCa], [gioTheoCa], [luongTheoCa]) VALUES (N'CANV02', N'Sáng', N'7h-11h', 2000000.0000)
GO
INSERT [dbo].[ChamCongCN] ([maCong], [soSP], [ngayCham], [maCa], [nghiPhep], [maPC], [trangThai]) VALUES (N'CCCN01', 10, CAST(N'2022-10-11' AS Date), N'CACN01', 0, N'PC01', 1)
INSERT [dbo].[ChamCongCN] ([maCong], [soSP], [ngayCham], [maCa], [nghiPhep], [maPC], [trangThai]) VALUES (N'CCCN02', 8, CAST(N'2022-10-11' AS Date), N'CACN02', 0, N'PC02', 1)
INSERT [dbo].[ChamCongCN] ([maCong], [soSP], [ngayCham], [maCa], [nghiPhep], [maPC], [trangThai]) VALUES (N'CCCN03', 10, CAST(N'2022-10-11' AS Date), N'CACN02', 0, N'PC03', 1)
INSERT [dbo].[ChamCongCN] ([maCong], [soSP], [ngayCham], [maCa], [nghiPhep], [maPC], [trangThai]) VALUES (N'CCCN04', 9, CAST(N'2022-10-11' AS Date), N'CACN01', 0, N'PC04', 1)
INSERT [dbo].[ChamCongCN] ([maCong], [soSP], [ngayCham], [maCa], [nghiPhep], [maPC], [trangThai]) VALUES (N'CCCN05', 10, CAST(N'2022-10-11' AS Date), N'CACN02', 0, N'PC05', 1)
INSERT [dbo].[ChamCongCN] ([maCong], [soSP], [ngayCham], [maCa], [nghiPhep], [maPC], [trangThai]) VALUES (N'CCCN06', 50, CAST(N'2022-11-15' AS Date), N'CACN01', 0, N'PC02', 1)
GO
INSERT [dbo].[ChamCongNV] ([maCong], [ngayCham], [trangThai], [nghiPhep], [maNV], [maCa]) VALUES (N'CCNV01', CAST(N'2022-10-11' AS Date), 1, 0, N'NV01', N'CANV01')
INSERT [dbo].[ChamCongNV] ([maCong], [ngayCham], [trangThai], [nghiPhep], [maNV], [maCa]) VALUES (N'CCNV02', CAST(N'2022-10-11' AS Date), 1, 0, N'NV02', N'CANV01')
INSERT [dbo].[ChamCongNV] ([maCong], [ngayCham], [trangThai], [nghiPhep], [maNV], [maCa]) VALUES (N'CCNV03', CAST(N'2022-10-11' AS Date), 1, 0, N'NV03', N'CANV02')
INSERT [dbo].[ChamCongNV] ([maCong], [ngayCham], [trangThai], [nghiPhep], [maNV], [maCa]) VALUES (N'CCNV04', CAST(N'2022-10-11' AS Date), 1, 0, N'NV04', N'CANV02')
INSERT [dbo].[ChamCongNV] ([maCong], [ngayCham], [trangThai], [nghiPhep], [maNV], [maCa]) VALUES (N'CCNV05', CAST(N'2022-10-11' AS Date), 1, 0, N'NV05', N'CANV02')
INSERT [dbo].[ChamCongNV] ([maCong], [ngayCham], [trangThai], [nghiPhep], [maNV], [maCa]) VALUES (N'CCNV06', CAST(N'2022-11-15' AS Date), 1, 0, N'NV04', N'CANV02')

GO
INSERT [dbo].[CongDoanSanXuat] ([maCD], [tenCD], [giaCD], [soLuong], [giaiDoan], [maSP]) VALUES (N'CD01', N'Lên khuôn giày', 30000.0000, 100, 1, N'SP01')
INSERT [dbo].[CongDoanSanXuat] ([maCD], [tenCD], [giaCD], [soLuong], [giaiDoan], [maSP]) VALUES (N'CD02', N'Khâu dập và may da', 50000.0000, 100, 2, N'SP01')
INSERT [dbo].[CongDoanSanXuat] ([maCD], [tenCD], [giaCD], [soLuong], [giaiDoan], [maSP]) VALUES (N'CD03', N'Hoàn thiện mũi giày', 40000.0000, 100, 3, N'SP01')
INSERT [dbo].[CongDoanSanXuat] ([maCD], [tenCD], [giaCD], [soLuong], [giaiDoan], [maSP]) VALUES (N'CD04', N'Hoàn thiện và trang trí', 30000.0000, 100, 4, N'SP01')
INSERT [dbo].[CongDoanSanXuat] ([maCD], [tenCD], [giaCD], [soLuong], [giaiDoan], [maSP]) VALUES (N'CD05', N'Kiểm tra lại thành phẩm', 20000.0000, 100, 5, N'SP01')
GO
INSERT [dbo].[CongNhan] ([maCN], [hoTen], [sdt], [diaChi], [soCMND], [namSinh], [gioiTinh], [maTayNghe], [troCap]) VALUES (N'CN01', N'Lê Quang Đạt', N'0123456789', N'109 Nguyễn Thái Sơn P4 quận Gò Vấp TP.HCM', N'1111111111', 2001, N'Nam', N'TN01', 21000.0000)
INSERT [dbo].[CongNhan] ([maCN], [hoTen], [sdt], [diaChi], [soCMND], [namSinh], [gioiTinh], [maTayNghe], [troCap]) VALUES (N'CN02', N'Phạm Chí Xuân', N'0332787756', N'108 Nguyễn Thái Sơn P4 quận Gò Vấp TP.HCM', N'2222222222', 2002, N'Nam', N'TN01', 20000.0000)
INSERT [dbo].[CongNhan] ([maCN], [hoTen], [sdt], [diaChi], [soCMND], [namSinh], [gioiTinh], [maTayNghe], [troCap]) VALUES (N'CN03', N'Nguyễn Quốc Khôi', N'0354597106', N'D5/143A xã Đa Phước huyện Bình Chánh TP.HCM', N'9876543210', 2002, N'Nam', N'TN01', 20000.0000)
INSERT [dbo].[CongNhan] ([maCN], [hoTen], [sdt], [diaChi], [soCMND], [namSinh], [gioiTinh], [maTayNghe], [troCap]) VALUES (N'CN04', N'Lê Văn Bánh', N'0123456787', N'119 Nguyễn Thái Sơn P4 quận Gò Vấp TP.HCM', N'1111111121', 1998, N'Nam', N'TN02', 15000.0000)
INSERT [dbo].[CongNhan] ([maCN], [hoTen], [sdt], [diaChi], [soCMND], [namSinh], [gioiTinh], [maTayNghe], [troCap]) VALUES (N'CN05', N'Nguyễn Văn Hai', N'0547896321', N'Nguyễn Thái Sơn P4 quận Gò Vấp TP.HCM', N'0214537953', 1999, N'Nam', N'TN03', 10000.0000)
GO
INSERT [dbo].[LuongCN] ([maLuongCN], [thang], [nam], [maCong], [luong]) VALUES (N'LCNCCCN01', 10, 2022, N'CCCN01', 300000.0000)
INSERT [dbo].[LuongCN] ([maLuongCN], [thang], [nam], [maCong], [luong]) VALUES (N'LCNCCCN02', 10, 2022, N'CCCN02', 270000.0000)
INSERT [dbo].[LuongCN] ([maLuongCN], [thang], [nam], [maCong], [luong]) VALUES (N'LCNCCCN03', 10, 2022, N'CCCN03', 400000.0000)
INSERT [dbo].[LuongCN] ([maLuongCN], [thang], [nam], [maCong], [luong]) VALUES (N'LCNCCCN04', 10, 2022, N'CCCN04', 400000.0000)
INSERT [dbo].[LuongCN] ([maLuongCN], [thang], [nam], [maCong], [luong]) VALUES (N'LCNCCCN05', 10, 2022, N'CCCN05', 400000.0000)
INSERT [dbo].[LuongCN] ([maLuongCN], [thang], [nam], [maCong], [luong]) VALUES (N'LCNCCCN06', 11, 2022, N'CCCN06', 2500000.0000)
GO
INSERT [dbo].[LuongNV] ([maLuongNV], [thang], [nam], [maCong], [luong]) VALUES (N'LNVCCNV01', 10, 2022, N'CCNV01', 4500000.0000)
INSERT [dbo].[LuongNV] ([maLuongNV], [thang], [nam], [maCong], [luong]) VALUES (N'LNVCCNV02', 10, 2022, N'CCNV02', 6000000.0000)
INSERT [dbo].[LuongNV] ([maLuongNV], [thang], [nam], [maCong], [luong]) VALUES (N'LNVCCNV03', 10, 2022, N'CCNV03', 5000000.0000)
INSERT [dbo].[LuongNV] ([maLuongNV], [thang], [nam], [maCong], [luong]) VALUES (N'LNVCCNV04', 10, 2022, N'CCNV04', 5500000.0000)
INSERT [dbo].[LuongNV] ([maLuongNV], [thang], [nam], [maCong], [luong]) VALUES (N'LNVCCNV05', 10, 2022, N'CCNV05', 4000000.0000)
INSERT [dbo].[LuongNV] ([maLuongNV], [thang], [nam], [maCong], [luong]) VALUES (N'LNVCCNV06', 11, 2022, N'CCNV06', 91153.8462)
GO
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [soDT], [soCMND], [diaChi], [gioiTinh], [namSinh], [maTrinhDo], [luongCB], [troCap], [heSoLuong]) VALUES (N'NV01', N'Lê Quang Đạt', N'0123456789',N'1111111111', N'119 Nguyễn Thái Sơn P4 quận Gò Vấp', N'Nam', 2002, N'TD01', 1300000.0000, 50000.0000, 1.2)
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [soDT], [soCMND], [diaChi], [gioiTinh], [namSinh], [maTrinhDo], [luongCB], [troCap], [heSoLuong]) VALUES (N'NV02', N'Phạm Chí Xuân', N'0190833489',N'2222222222', N'109 Nguyễn Thái Sơn P4 quận Gò Vấp TP.HCM', N'Nam', 2002, N'TD01', 1300000.0000, 50000.0000, 1.2)
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [soDT], [soCMND],[diaChi], [gioiTinh], [namSinh], [maTrinhDo], [luongCB], [troCap], [heSoLuong]) VALUES (N'NV03', N'Nguyễn Quốc Khôi', N'0987098789',N'3333333333', N'55 Nguyễn Thái Sơn P4 quận Gò Vấp TP.HCM', N'Nam', 2002, N'TD01', 1300000.0000, 50000.0000, 1.2)
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [soDT], [soCMND],[diaChi], [gioiTinh], [namSinh], [maTrinhDo], [luongCB], [troCap], [heSoLuong]) VALUES (N'NV04', N'Lê Văn Bánh', N'0970287123',N'1234567890', N'D5/143A xã Đa Phước huyện Bình Chánh TP.HCM', N'Nam', 1998, N'TD02', 1200000.0000, 45000.0000, 1.1)
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [soDT], [soCMND],[diaChi], [gioiTinh], [namSinh], [maTrinhDo], [luongCB], [troCap], [heSoLuong]) VALUES (N'NV05', N'Nguyễn Văn Hai', N'033201098',N'9876543210' ,N'89 Nguyễn Thái Sơn P4 quận Gò Vấp TP.HCM', N'Nam', 1998, N'TD03', 1100000.0000, 55000.0000, 1.1)
GO
INSERT [dbo].[PhanCongCN] ([maPC], [maCN], [maCD], [soLuong], [ngay]) VALUES (N'PC01', N'CN01', N'CD01', 22, CAST(N'2022-10-18' AS Date))
INSERT [dbo].[PhanCongCN] ([maPC], [maCN], [maCD], [soLuong], [ngay]) VALUES (N'PC02', N'CN02', N'CD02', 15, CAST(N'2022-10-18' AS Date))
INSERT [dbo].[PhanCongCN] ([maPC], [maCN], [maCD], [soLuong], [ngay]) VALUES (N'PC03', N'CN03', N'CD03', 20, CAST(N'2022-10-18' AS Date))
INSERT [dbo].[PhanCongCN] ([maPC], [maCN], [maCD], [soLuong], [ngay]) VALUES (N'PC04', N'CN04', N'CD04', 30, CAST(N'2022-10-18' AS Date))
INSERT [dbo].[PhanCongCN] ([maPC], [maCN], [maCD], [soLuong], [ngay]) VALUES (N'PC05', N'CN05', N'CD05', 22, CAST(N'2022-10-18' AS Date))
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [kieuDang], [chatLieu], [soLuong]) VALUES (N'SP01', N'Giày da bò', N'Quý ông', N'Da Bò', 100)
INSERT [dbo].[SanPham] ([maSP], [tenSP], [kieuDang], [chatLieu], [soLuong]) VALUES (N'SP02', N'Giày da cá sấu', N'Boot', N'Da Cá Sấu', 100)
INSERT [dbo].[SanPham] ([maSP], [tenSP], [kieuDang], [chatLieu], [soLuong]) VALUES (N'SP03', N'Giày da tổng hợp', N'Quý ông', N'Da Tổng Hợp', 100)
INSERT [dbo].[SanPham] ([maSP], [tenSP], [kieuDang], [chatLieu], [soLuong]) VALUES (N'SP04', N'Giày da thuộc', N'Quý ông', N'Da Thuộc', 100)
INSERT [dbo].[SanPham] ([maSP], [tenSP], [kieuDang], [chatLieu], [soLuong]) VALUES (N'SP05', N'Giày da lộn', N'Boot', N'Da Lộn', 100)
GO
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNV], [maCN]) VALUES (N'ADMIN', N'123       ', NULL, NULL)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNV], [maCN]) VALUES (N'CN01', N'123       ', NULL, N'CN01')
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNV], [maCN]) VALUES (N'CN02', N'123       ', NULL, N'CN02')
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNV], [maCN]) VALUES (N'CN03', N'123       ', NULL, N'CN03')
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNV], [maCN]) VALUES (N'NV01', N'123       ', N'NV01', NULL)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNV], [maCN]) VALUES (N'NV02', N'123       ', N'NV02', NULL)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNV], [maCN]) VALUES (N'NV03', N'123       ', N'NV03', NULL)
GO
INSERT [dbo].[TayNghe] ([maTayNghe], [tenTayNghe]) VALUES (N'TN01', N'Cao')
INSERT [dbo].[TayNghe] ([maTayNghe], [tenTayNghe]) VALUES (N'TN02', N'Trung Bình')
INSERT [dbo].[TayNghe] ([maTayNghe], [tenTayNghe]) VALUES (N'TN03', N'Thấp')
GO
INSERT [dbo].[TrinhDo] ([maTrinhDo], [tenTrinhDo]) VALUES (N'TD01', N'Đại học')
INSERT [dbo].[TrinhDo] ([maTrinhDo], [tenTrinhDo]) VALUES (N'TD02', N'Cao Đẳng')
INSERT [dbo].[TrinhDo] ([maTrinhDo], [tenTrinhDo]) VALUES (N'TD03', N'Trung Học')
GO
ALTER TABLE [dbo].[ChamCongCN]  WITH CHECK ADD  CONSTRAINT [FK_ChamCongCN_CaLamCN] FOREIGN KEY([maCa])
REFERENCES [dbo].[CaLamCN] ([maCa])
GO
ALTER TABLE [dbo].[ChamCongCN] CHECK CONSTRAINT [FK_ChamCongCN_CaLamCN]
GO
ALTER TABLE [dbo].[ChamCongCN]  WITH CHECK ADD  CONSTRAINT [FK_ChamCongCN_PhanCongCN] FOREIGN KEY([maPC])
REFERENCES [dbo].[PhanCongCN] ([maPC])
GO
ALTER TABLE [dbo].[ChamCongCN] CHECK CONSTRAINT [FK_ChamCongCN_PhanCongCN]
GO
ALTER TABLE [dbo].[ChamCongNV]  WITH CHECK ADD  CONSTRAINT [FK_ChamCongNV_CaLamNV] FOREIGN KEY([maCa])
REFERENCES [dbo].[CaLamNV] ([maCa])
GO
ALTER TABLE [dbo].[ChamCongNV] CHECK CONSTRAINT [FK_ChamCongNV_CaLamNV]
GO
ALTER TABLE [dbo].[ChamCongNV]  WITH CHECK ADD  CONSTRAINT [FK_ChamCongNV_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[ChamCongNV] CHECK CONSTRAINT [FK_ChamCongNV_NhanVien]
GO
ALTER TABLE [dbo].[CongDoanSanXuat]  WITH CHECK ADD  CONSTRAINT [FK_CongDoanSanXuat_SanPham] FOREIGN KEY([maSP])
REFERENCES [dbo].[SanPham] ([maSP])
GO
ALTER TABLE [dbo].[CongDoanSanXuat] CHECK CONSTRAINT [FK_CongDoanSanXuat_SanPham]
GO
ALTER TABLE [dbo].[CongNhan]  WITH CHECK ADD  CONSTRAINT [FK_CongNhan_TayNghe] FOREIGN KEY([maTayNghe])
REFERENCES [dbo].[TayNghe] ([maTayNghe])
GO
ALTER TABLE [dbo].[CongNhan] CHECK CONSTRAINT [FK_CongNhan_TayNghe]
GO
ALTER TABLE [dbo].[LuongCN]  WITH CHECK ADD  CONSTRAINT [FK_LuongCN_ChamCongCN] FOREIGN KEY([maCong])
REFERENCES [dbo].[ChamCongCN] ([maCong])
GO
ALTER TABLE [dbo].[LuongCN] CHECK CONSTRAINT [FK_LuongCN_ChamCongCN]
GO
ALTER TABLE [dbo].[LuongNV]  WITH CHECK ADD  CONSTRAINT [FK_LuongNV_ChamCongNV] FOREIGN KEY([maCong])
REFERENCES [dbo].[ChamCongNV] ([maCong])
GO
ALTER TABLE [dbo].[LuongNV] CHECK CONSTRAINT [FK_LuongNV_ChamCongNV]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_TrinhDo] FOREIGN KEY([maTrinhDo])
REFERENCES [dbo].[TrinhDo] ([maTrinhDo])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_TrinhDo]
GO
ALTER TABLE [dbo].[PhanCongCN]  WITH CHECK ADD  CONSTRAINT [FK_PhanCongCN_CongDoanSanXuat] FOREIGN KEY([maCD])
REFERENCES [dbo].[CongDoanSanXuat] ([maCD])
GO
ALTER TABLE [dbo].[PhanCongCN] CHECK CONSTRAINT [FK_PhanCongCN_CongDoanSanXuat]
GO
ALTER TABLE [dbo].[PhanCongCN]  WITH CHECK ADD  CONSTRAINT [FK_PhanCongCN_CongNhan] FOREIGN KEY([maCN])
REFERENCES [dbo].[CongNhan] ([maCN])
GO
ALTER TABLE [dbo].[PhanCongCN] CHECK CONSTRAINT [FK_PhanCongCN_CongNhan]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_CongNhan] FOREIGN KEY([maCN])
REFERENCES [dbo].[CongNhan] ([maCN])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_CongNhan]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVien]
GO
