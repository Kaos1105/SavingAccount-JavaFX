USE [master]
GO
/****** Object:  Database [SavingAccount]    Script Date: 6/24/2019 9:11:03 PM ******/
CREATE DATABASE [SavingAccount]
 CONTAINMENT = NONE
GO
ALTER DATABASE [SavingAccount] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SavingAccount].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SavingAccount] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SavingAccount] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SavingAccount] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SavingAccount] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SavingAccount] SET ARITHABORT OFF 
GO
ALTER DATABASE [SavingAccount] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [SavingAccount] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SavingAccount] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SavingAccount] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SavingAccount] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SavingAccount] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SavingAccount] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SavingAccount] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SavingAccount] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SavingAccount] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SavingAccount] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SavingAccount] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SavingAccount] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SavingAccount] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SavingAccount] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SavingAccount] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SavingAccount] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SavingAccount] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SavingAccount] SET  MULTI_USER 
GO
ALTER DATABASE [SavingAccount] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SavingAccount] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SavingAccount] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SavingAccount] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SavingAccount] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SavingAccount] SET QUERY_STORE = OFF
GO
USE [SavingAccount]
GO
/****** Object:  Table [dbo].[LoaiTietKiem]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiTietKiem](
	[MaLoaiTietKiem] [int] IDENTITY(1,1) NOT NULL,
	[TenLoaiTietKiem] [nvarchar](100) NOT NULL,
	[SoNgayDuocRut] [int] NOT NULL,
	[DangDung] [bit] NOT NULL,
	[SoTienGuiToiThieu] [int] NOT NULL,
	[TienGuiThemToiThieu] [int] NULL,
	[LaiSuatThang] [float] NULL,
	[KyHan] [int] NOT NULL,
	[LaiSuat] [float] NULL,
	[ThoiGianDuocGoiThem] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoaiTietKiem] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuGuiTien]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuGuiTien](
	[MaPhieuGuiTien] [int] IDENTITY(1,1) NOT NULL,
	[MaSoTietKiem] [int] NOT NULL,
	[TenKhachHang] [nvarchar](100) NOT NULL,
	[SoTienGui] [bigint] NOT NULL,
	[NgayGui] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaPhieuGuiTien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuRutTien]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuRutTien](
	[MaPhieuRutTien] [int] IDENTITY(1,1) NOT NULL,
	[MaSoTietKiem] [int] NOT NULL,
	[TenKhachHang] [nvarchar](100) NOT NULL,
	[SoTienRut] [bigint] NOT NULL,
	[NgayRut] [date] NOT NULL,
	[TatToan] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaPhieuRutTien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SoTietKiem]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SoTietKiem](
	[MaSoTietKiem] [int] IDENTITY(1,1) NOT NULL,
	[TenKhachHang] [nvarchar](100) NOT NULL,
	[SoCMND] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[NgayMoSo] [date] NOT NULL,
	[SoDu] [bigint] NOT NULL,
	[MaLoaiTietKiem] [int] NOT NULL,
	[DongSo] [bit] NOT NULL,
	[NgayDaoHan] [date] NULL,
	[NgayRutTien] [date] NOT NULL,
	[NgayGoiThem] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSoTietKiem] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[LoaiTietKiem] ON 

INSERT [dbo].[LoaiTietKiem] ([MaLoaiTietKiem], [TenLoaiTietKiem], [SoNgayDuocRut], [DangDung], [SoTienGuiToiThieu], [TienGuiThemToiThieu], [LaiSuatThang], [KyHan], [LaiSuat], [ThoiGianDuocGoiThem]) VALUES (1, N'Không kì hạn', 15, 1, 1000000, 100000, 0.15, 0, NULL, 15)
INSERT [dbo].[LoaiTietKiem] ([MaLoaiTietKiem], [TenLoaiTietKiem], [SoNgayDuocRut], [DangDung], [SoTienGuiToiThieu], [TienGuiThemToiThieu], [LaiSuatThang], [KyHan], [LaiSuat], [ThoiGianDuocGoiThem]) VALUES (2, N'3 tháng', 90, 1, 1000000, 100000, NULL, 90, 0.5, 90)
INSERT [dbo].[LoaiTietKiem] ([MaLoaiTietKiem], [TenLoaiTietKiem], [SoNgayDuocRut], [DangDung], [SoTienGuiToiThieu], [TienGuiThemToiThieu], [LaiSuatThang], [KyHan], [LaiSuat], [ThoiGianDuocGoiThem]) VALUES (3, N'6 tháng', 180, 1, 1000000, 100000, NULL, 180, 0.55, 180)
SET IDENTITY_INSERT [dbo].[LoaiTietKiem] OFF
ALTER TABLE [dbo].[LoaiTietKiem] ADD  DEFAULT ((0)) FOR [SoNgayDuocRut]
GO
ALTER TABLE [dbo].[LoaiTietKiem] ADD  DEFAULT ((0)) FOR [SoTienGuiToiThieu]
GO
ALTER TABLE [dbo].[LoaiTietKiem] ADD  DEFAULT ((0)) FOR [KyHan]
GO
ALTER TABLE [dbo].[PhieuGuiTien] ADD  DEFAULT ((0)) FOR [SoTienGui]
GO
ALTER TABLE [dbo].[PhieuRutTien] ADD  DEFAULT ((0)) FOR [TatToan]
GO
ALTER TABLE [dbo].[SoTietKiem] ADD  DEFAULT ((0)) FOR [SoDu]
GO
ALTER TABLE [dbo].[PhieuGuiTien]  WITH CHECK ADD FOREIGN KEY([MaSoTietKiem])
REFERENCES [dbo].[SoTietKiem] ([MaSoTietKiem])
GO
ALTER TABLE [dbo].[PhieuRutTien]  WITH CHECK ADD FOREIGN KEY([MaSoTietKiem])
REFERENCES [dbo].[SoTietKiem] ([MaSoTietKiem])
GO
ALTER TABLE [dbo].[SoTietKiem]  WITH CHECK ADD FOREIGN KEY([MaLoaiTietKiem])
REFERENCES [dbo].[LoaiTietKiem] ([MaLoaiTietKiem])
GO
/****** Object:  StoredProcedure [dbo].[USP_AddLoaiTietKiem]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

----AddLoaiTietKiem
CREATE proc [dbo].[USP_AddLoaiTietKiem] 
@name nvarchar(100), @period int, @interest float, @interestMonth float, @money bigint, @moneyAdd bigint, @dateAdd int, @dateWithdraw int, @isActive bit
as
begin
insert into LoaiTietKiem(KyHan, LaiSuat, LaiSuatThang, SoTienGuiToiThieu, TienGuiThemToiThieu, ThoiGianDuocGoiThem, SoNgayDuocRut, DangDung, TenLoaiTietKiem) values (@period, @interest, @interestMonth, @money,  @moneyAdd, @dateAdd,  @dateWithdraw, @isActive ,@name)
end

GO
/****** Object:  StoredProcedure [dbo].[USP_InsertLoaiTietKiem]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[USP_InsertLoaiTietKiem] 
@name nvarchar(100), @interest float, @interestMonth float, @money bigint, @moneyAdd bigint, @dateAdd int, @dateWithdraw int, @isActive bit
as
begin
update LoaiTietKiem set LaiSuat=@interest, LaiSuatThang=@interestMonth, SoTienGuiToiThieu=@money, TienGuiThemToiThieu =@moneyAdd, ThoiGianDuocGoiThem=@dateAdd, SoNgayDuocRut =@dateWithdraw, DangDung=@isActive where TenLoaiTietKiem=@name
end

GO
/****** Object:  StoredProcedure [dbo].[USP_InsertPhipeuGuiTien]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[USP_InsertPhieuGuiTien]
@accID int, @name nvarchar(100), @money bigint, @dateAdd date
as
begin
	--declare @addMoney int
	--select @addMoney= TienGuiThemToiThieu from LoaiTietKiem l, SoTietKiem stk where l.MaLoaiTietKiem = stk.MaLoaiTietKiem and stk.MaSoTietKiem = @accID
	update SoTietKiem set SoDu = SoDu +@money where @accID = MaSoTietKiem
	insert into PhieuGuiTien(MaSoTietKiem, TenKhachHang, SoTienGui, NgayGui)
	values (@accID, @name, @money, @dateAdd)
end
GO
/****** Object:  StoredProcedure [dbo].[USP_InsertPhieuRutTien]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[USP_InsertPhieuRutTien]
@accID int, @name nvarchar(100), @money bigint, @dateWithdraw date, @tatToan bit
as
begin
	update SoTietKiem set SoDu = SoDu -@money where @accID = MaSoTietKiem
	insert into PhieuRutTien(MaSoTietKiem, TenKhachHang, SoTienRut, NgayRut, TatToan)
	values (@accID, @name, @money, @dateWithdraw, @tatToan)
end

GO
/****** Object:  StoredProcedure [dbo].[USP_InsertSoTietKiem]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[USP_InsertSoTietKiem]
@typeID int, @money bigint, @name nvarchar(100), @cmnd nvarchar(100), @address nvarchar(100), @dateOpen date
as
begin
	declare @datePeriod date
	declare @dateAdd date
	declare @dateWithdraw date
	declare @kyHan int
	--select @datePeriod = Dateadd(DAY,  l.Kyhan, @dateOpen ) from LoaiTietKiem l where l.MaLoaiTietKiem=@typeID
	select @kyHan=KyHan from LoaiTietKiem where MaLoaiTietKiem=@typeID
	if(@kyHan=0)
		set @datePeriod = NULL
	else
		set @datePeriod = Dateadd(DAY,  @kyHan, @dateOpen ) 
	select @dateAdd = Dateadd(DAY,  l.ThoiGianDuocGoiThem, @dateOpen ) from LoaiTietKiem l where l.MaLoaiTietKiem=@typeID
	select @dateWithdraw =  Dateadd(DAY,  l.SoNgayDuocRut, @dateOpen ) from LoaiTietKiem l where l.MaLoaiTietKiem=@typeID
	insert into SoTietKiem(TenKhachHang, SoCMND, DiaChi, NgayMoSo, SoDu, MaLoaiTietKiem, DongSo, NgayDaoHan, NgayRutTien, NgayGoiThem)
	values (
	@name, @cmnd, @address, @dateOpen, @money, @typeID, 1, @datePeriod, @dateWithdraw, @dateAdd)
end

GO
/****** Object:  StoredProcedure [dbo].[USP_ReportDate]    Script Date: 6/24/2019 9:11:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[USP_ReportDate]
@date date
as
begin
	select t1.TenLoaiTietKiem, t1.TongThu, t2.TongChi from	
	(select TenLoaiTietKiem, sum(SoTienGui) as TongThu
	from PhieuGuiTien pgt, LoaiTietKiem ltk, SoTietKiem stk
	where pgt.MaSoTietKiem=stk.MaSoTietKiem and stk.MaLoaiTietKiem = ltk.MaLoaiTietKiem and pgt.NgayGui=@date
	group by TenLoaiTietKiem) t1
	join
	(select TenLoaiTietKiem, sum(SoTienRut) as TongChi
	from PhieuRutTien prt, LoaiTietKiem ltk, SoTietKiem stk
	where prt.MaSoTietKiem=stk.MaSoTietKiem and stk.MaLoaiTietKiem = ltk.MaLoaiTietKiem and prt.NgayRut=@date
	group by TenLoaiTietKiem) t2
	on t1.TenLoaiTietKiem = t2.TenLoaiTietKiem
end

GO
USE [master]
GO
ALTER DATABASE [SavingAccount] SET  READ_WRITE 
GO