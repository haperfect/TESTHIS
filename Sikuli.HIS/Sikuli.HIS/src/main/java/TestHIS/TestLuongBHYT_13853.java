package TestHIS;

import org.sikuli.api.robot.Key;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HIS.DanhSachBenhNhan;
import HIS.FormKhamBenh;
import HIS.FormKhuVuc;
import HIS.FormVienPhiNgoaiTru;
import HIS.FormXacNhanBaoHiemYTe;
import HIS.TiepNhanBenhNhan;
import desktop_Framework.HisActions;
import desktop_Framework.TestLogger;
import desktop_Framework.TienIch;

public class TestLuongBHYT_13853 extends TiepNhanBenhNhan {

	// Luồng 1.1 Tiếp nhận 1 BN BHYT, check thu tiền sau, chọn dịch vụ có thu chênh
	// lệch, khám bệnh, không chỉ định cận lâm sàng
	public String Hoten, NgaySinh, SoCMND, GioiTinh, SoDienThoai, NgheNghiep, DanToc, NhapNhanh, SoNha, NoiLamViec;
	public String NguoiLienHe, SoDienThoaiNguoiLienHe, DoiTuong, UuTien, HinhThuc, LiDo, TenDichVu, ThuTienSau,
			NoiThucHien;
	public String SoTiepNhan;
	public String sotheBHYT;
	public String tenKhoThuoc, tenNguonThuoc, tenThuoc, soLuongThuoc, lieuDung, cachDung, ghiChu;

	DanhSachBenhNhan dsbn = new DanhSachBenhNhan();
	FormVienPhiNgoaiTru vpnt = new FormVienPhiNgoaiTru();
	HisActions his = new HisActions();
	FormKhamBenh kb = new FormKhamBenh();
	FormXacNhanBaoHiemYTe yt = new FormXacNhanBaoHiemYTe();

	// Login bằng tài khoản TD01 de tiếp nhận bệnh nhân vào khám
	//@BeforeTest
	public void dieukienDauTien() {

		if (his.khoiDongHIS(HisActions.DUONG_DAN_FILE_CHAY_HIS) == true) {
			his.dangNhapHIS(FormKhuVuc.ten_dangNhap_TDNT, FormKhuVuc.matKhau_dangNhap_TDNT);
			his.chonPhongLamViec("Tiếp đón ngoại trú");
			moMenuTiepNhanBenhNhan();
		} else
			setTestcaseStatus("FAIL", "Không thể khởi động ứng dụng HIS !");
	}

	// Điền thông tin bệnh nhân BHYT và in phiếu khám
	@Test(priority = 1)
	public void BHYT_13853_1() {
		// 1.Hành chính
		TestLogger.info("Điền Họ tên :");
		Hoten = "TRAN VAN LUC" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn Giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CCCD");
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền Số Điện Thoại:");
		SoDienThoai = TienIch.taoRandomSo(11);
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền Nghề Nghiệp:");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc");
		DanToc = "Kinh";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(2);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(15);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền SĐT người liên hệ");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomSo(11);
		dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.CHỌN ĐỐI TƯỢNG
		TestLogger.info("Điền đối tượng");
		DoiTuong = "BHYT 80%";
		clickToaDo(218, 277);
		waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
		clickOn(TiepNhanBenhNhan_BHYT80);

		s.type(Key.TAB);
		sleep(5);
		s.type(Key.TAB);

		TestLogger.info("Điền hình thức khám");
		HinhThuc = "Tự đến";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chon lý do khám");
		LiDo = "Khám bệnh";
		dienLiDo(LiDo);

		TestLogger.info("Điền số thẻ BHYT 80");
		sotheBHYT = "SV491" + taoRandomSo(10);
		dienSoTheBHYT(sotheBHYT);
		s.type(Key.ENTER);

		TestLogger.info("Dien ma dang ki KCBBD");
		dienMaDKKCB("01005");
		// s.type(Key.ENTER);

		sleep(4);
		TestLogger.info("chon khu vuc");
		s.type(Key.DOWN);
		// s.type(Key.DOWN);
		s.type(Key.ENTER);

		TestLogger.info("Chon tu ngay");
		dienTuNgay("30/12/2016");
		s.type(Key.ENTER);

		TestLogger.info("Chon den ngay");
		dienDenNgay("30/12/2018");
		s.type(Key.ENTER);
		sleep(4);
		s.type(Key.TAB);
		sleep(3);
		s.type(Key.TAB);

		TestLogger.info("Nhap noi gioi thieu");
		dienNoiGioiThieu("Bệnh viện Thanh Nhàn11111");
		s.type(Key.TAB);

		TestLogger.info("Nhap so chuyen tuyen");
		dienSoTuyenChuyen("012");
		s.type(Key.TAB);

		TestLogger.info("Nhap ngay chuyen");
		dienNgayChuyen("16/11/2017");
		s.type(Key.TAB);

		TestLogger.info("Nhap tuyen chuyen");
		dienTuyenChuyen("BV Bạch Mai");
		s.type(Key.TAB);

		TestLogger.info("Nhap ly do chuyen");
		dienLiDoChuyen("Đủ điều kiện chuyển tuyến (đúng tuyến)");
		s.type(Key.TAB);

		TestLogger.info("Nhap chan doan NGT");
		dienChanDoanNGT("Đau đầu");
		s.type(Key.TAB);

		// Chọn dịch vụ khám
		TenDichVu = "(Hằng test) Khám tổng quát ";
		dienTenDichVu(TenDichVu);
		s.type(Key.TAB);

		sleep(3);
		NoiThucHien = "Khám theo yêu cầu";
		dienNoiThucHien(NoiThucHien);
		s.type(Key.TAB);

		sleep(3);
		ThuTienSau = "Yes";
		chonThuTienSau(ThuTienSau);

		clickOn(TiepNhanBenhNhan_Luu);

		if (waitForObjectPresent(Phieukham, 5)) {
			s.type(Key.F4, Key.ALT);
			setTestcaseStatus("PASS", "Tiếp nhận Bệnh nhân thành công !");
		} else {

			setTestcaseStatus("FAIL", "Tiếp nhận Bệnh nhân không thành công !");
		}
	}

	// Kiem tra benh nhan da có trong danh sach kham benh hay chua
	@Test(priority = 2)
	public void BHYT_13853_2() {
		TestLogger.info("Kiem tra lai benh nhan vua tiep nhan");
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 5);
		clickOn(TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);
		dsbn.dienDieuKienTimKiem(Hoten);
		if (waitForObjectPresent(DanhSachBenhNhan.DanhSachBenhNhan_SoDongBang1, 4)) {
			setTestcaseStatus("PASS", "Bệnh nhân vừa tiếp nhận nằm trong danh sách chờ khám");

		} else {
			setTestcaseStatus("FAIL", "Bệnh nhân vừa tiếp nhận không nằm trong danh sách chờ khám");
		}

		TestLogger.info("- Click double vào bệnh nhân");
		dsbn.clickDupVaoBenhNhanChoKham();
		sleep(3);
		clickOn(TiepNhanBenhNhan_Sua);
		SoTiepNhan = getSoTiepNhan();

		TestLogger.info("Expect : Chuyển sang form Tiếp nhận Bệnh nhân,check thông tin bệnh nhân ,"
				+ "Expect : Hiển thị đúng thông tin của bệnh nhân ");
		if (waitForObjectPresent(TiepNhanBenhNhan_MenuHanhChinh, 5)) {
			setTestcaseStatus("PASS", "Đã chuyển sang form Tiếp nhận Bệnh nhân");
		} else {
			setTestcaseStatus("FAIL", "Không chuyển sang form Tiếp nhận Bệnh nhân");
		}

		if (getHoten().equalsIgnoreCase(Hoten)) {
			setTestcaseStatus("PASS", "Hoten");
		} else {
			setTestcaseStatus("FAIL", "Hoten");
		}

		if (getNgaySinh().equalsIgnoreCase(NgaySinh)) {
			setTestcaseStatus("PASS", "NgaySinh");
		} else {
			setTestcaseStatus("FAIL", "NgaySinh");
		}

		if (getSoCMND().equalsIgnoreCase(SoCMND)) {
			setTestcaseStatus("PASS", "SoCMND");
		} else {
			setTestcaseStatus("FAIL", "SoCMND");
		}

		if (getSoDienThoai().equalsIgnoreCase(SoDienThoai)) {
			setTestcaseStatus("PASS", "SoDienThoai");
		} else {
			setTestcaseStatus("FAIL", "SoDienThoai");
		}

		if (getNgheNghiep().equalsIgnoreCase(NgheNghiep)) {
			setTestcaseStatus("PASS", "NgheNghiep");
		} else {
			setTestcaseStatus("FAIL", "NgheNghiep");
		}

		if (getDanToc().equalsIgnoreCase(DanToc)) {
			setTestcaseStatus("PASS", "DanToc");
		} else {
			setTestcaseStatus("FAIL", "DanToc");
		}

		if (getThanhPho().equalsIgnoreCase("Tỉnh Hà Nam")) {
			setTestcaseStatus("PASS", "Tỉnh Hà Nam");
		} else {
			setTestcaseStatus("FAIL", "Tỉnh Hà Nam");
		}

		if (getQuanHuyen().equalsIgnoreCase("Thành phố Phủ Lý")) {
			setTestcaseStatus("PASS", "Thành phố Phủ Lý");
		} else {
			setTestcaseStatus("FAIL", "Thành phố Phủ Lý");
		}

		if (getPhuongXa().equalsIgnoreCase("Phường Quang Trung")) {
			setTestcaseStatus("PASS", "Phường Quang Trung");
		} else {
			setTestcaseStatus("FAIL", "Phường Quang Trung");
		}

		if (getSoNha().equalsIgnoreCase(SoNha)) {
			setTestcaseStatus("PASS", "SoNha");
		} else {
			setTestcaseStatus("FAIL", "SoNha");
		}

		if (getNoiLamViec().equalsIgnoreCase(NoiLamViec)) {
			setTestcaseStatus("PASS", "NoiLamViec");
		} else {
			setTestcaseStatus("FAIL", "NoiLamViec");
		}

		if (getNguoiLienHe().equalsIgnoreCase(NguoiLienHe)) {
			setTestcaseStatus("PASS", "NguoiLienHe");
		} else {
			setTestcaseStatus("FAIL", "NguoiLienHe");
		}

		if (getSoDienThoaiNguoiLienHe().equalsIgnoreCase(SoDienThoaiNguoiLienHe)) {
			setTestcaseStatus("PASS", "SoDienThoaiNguoiLienHe");
		} else {
			setTestcaseStatus("FAIL", "SoDienThoaiNguoiLienHe");
		}

		if (getHinhThuc().equalsIgnoreCase(HinhThuc)) {
			setTestcaseStatus("PASS", "HinhThuc");
		} else {
			setTestcaseStatus("FAIL", "HinhThuc");
		}

		if (getLiDo().equalsIgnoreCase(LiDo)) {
			setTestcaseStatus("PASS", "LiDo");
		} else {
			setTestcaseStatus("FAIL", "LiDo");
		}

		if (getNoiThucHien().equalsIgnoreCase(NoiThucHien)) {
			setTestcaseStatus("PASS", "NoiThucHien");
		} else {
			setTestcaseStatus("FAIL", "NoiThucHien");
		}

	}

	// Thoat khoi tai khoan TD01 => chuyen sang Tai khoan BS01 de kham cho benh
	// nhan co
	// so tiep don benh nhan da dang ki o tren
	@Test(priority = 3)
	public void BHYT_13853_2_2() {

		// Log out tai khoan cu,
		clickOn(TiepNhanBenhNhan_HeThong);
		clickOn(TiepNhanBenhNhan_DangXuat);
		clickOn(TiepNhanBenhNhan_HeThong);
		clickOn(TiepNhanBenhNhan_DangNhap);

		// Dang nhap tai khoan BS01
		dangNhapHIS(FormKhuVuc.ten_dangNhap_BS01, FormKhuVuc.matKhau_dangNhap_BS01);
		chonPhongLamViec("Khám theo yêu cầu");
		waitForObjectPresent(HisActions.HIS_MenuKhamBenh, 5);
		sleep(6);
		clickOn(HisActions.HIS_MenuKhamBenh);
		clickToaDo(215, 73);

		kb.dienSoTiepNhan(SoTiepNhan);
		kb.clickTimKiem();
		if (waitForObjectPresent(FormKhamBenh.FormKhamBenh_soDongBang1, 5)) {
			hoverImage(FormKhamBenh.FormKhamBenh_LamMoi);
			moveMouseDownFromLogo(FormKhamBenh.FormKhamBenh_TimKiem, 95);
			s.doubleClick();
			setTestcaseStatus("PASS", "Đã tìm thấy bệnh nhân đang chờ khám !");
		} else {
			setTestcaseStatus("FAIL", "Không tìm thấy bệnh nhân đang chờ khám !");
		}
	}
	// BS01 đo chỉ số sinh tồn cho bệnh nhân

	@Test(priority = 4)
	public void BHYT_13853_3() {
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_doChiSoSinhTon, 5);
		clickOn(FormKhamBenh.FormKhamBenh_doChiSoSinhTon);
		kb.dienDiUng("phan hoa");
		kb.dienBenhManTinh("tieu chay");
		clickOn(FormKhamBenh.FormKhamBenh_SuaNhomMau);
		kb.chonNhomMau("a");
		s.type(Key.TAB);
		sleep(2);
		s.type(Key.UP);
		s.type(Key.TAB);
		TestLogger.info("Do Mach");
		s.type("150");
		s.type(Key.TAB);
		TestLogger.info("Do Huyet Ap");
		s.type("40");
		s.type(Key.TAB);
		s.type("180");
		s.type(Key.TAB);
		TestLogger.info("Do nhiet do");
		s.type("38");
		s.type(Key.TAB);
		TestLogger.info("Do nhip tho");
		s.type("120");
		s.type(Key.TAB);
		TestLogger.info("Do can nang");
		s.type("55");
		s.type(Key.TAB);
		TestLogger.info("Do chieu cao");
		s.type("180");
		// click Luu chỉ số sinh tồn
		clickOn(FormKhamBenh.FormKhamBenh_LuuChiSoSinhTon);
		if (waitForObjectPresent(HisActions.HIS_LOI_UNGDUNG, 2)) {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Lưu Chỉ số sinh tồn gặp sự cố !");
		} else {
			setTestcaseStatus("PASS", "Đã Lưu được chỉ số sinh tồn ");

		}
	}

	// BS01 kham benh sau khi da do xong cac chi so sinh ton
	@Test(priority = 5)
	public void BHYT_13853_3_2() {
		clickOn(FormKhamBenh.FormKhamBenh_KhamBenh);
		kb.dienLiDoKham("Dau Bung");
		kb.dienBenhChinhDefault();
		clickOn(FormKhamBenh.FormKhamBenh_Luu);
		if (waitForObjectPresent(HisActions.HIS_LOI_UNGDUNG, 2)) {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Không thể lưu kết quả khám bệnh , gặp sự cố !");
		} else {
			setTestcaseStatus("PASS", "Đã Lưu được kết quả khám bệnh ! ");
		}
	}

	@Test(priority = 6)
	// BS01 kham benh, cho về và kê toa
	public void BHYT_13853_4() {
		clickOn(FormKhamBenh.FormKhamBenh_KetLuan);
		moveMouseHorizontallyFromLogo(FormKhamBenh.FormKhamBenh_HuongDieuTri, 297);
		s.click();
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_HuongDieuTri_ChoVeKeToa, 5);
		clickOn(FormKhamBenh.FormKhamBenh_HuongDieuTri_ChoVeKeToa);
		// Chọn Kho thuốc
		TestLogger.info("Chọn kho thuốc:");
		s.type(Key.TAB);
		sleep(3);
		s.type(Key.TAB);
		sleep(2);
		s.type(Key.TAB);
		sleep(1);
		s.type(Key.TAB);
		sleep(2);
		s.type(Key.TAB);
		// chọn tọa độ kho thuốc
		clickToaDo(348, 414);
		sleep(1);
		clickToaDo(348, 414);
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_KhoThuocNgoaiTru, 5);
		clickOn(FormKhamBenh.FormKhamBenh_KhoThuocNgoaiTru);

		// Chọn nguồn thuốc
		TestLogger.info("Chọn nguon thuốc:");
		sleep(2);
		clickToaDo(467, 415);
		sleep(1);
		clickToaDo(467, 415);
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_NguonThuocDauThau, 5);
		clickOn(FormKhamBenh.FormKhamBenh_NguonThuocDauThau);
		// Chọn tên thuốc
		sleep(3);
		TestLogger.info("Chọn ten thuốc:");
		clickToaDo(786, 413);
		sleep(1);
		clickToaDo(786, 413);
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_TenThuocEpocassa, 5);
		clickOn(FormKhamBenh.FormKhamBenh_TenThuocEpocassa);
		// Chọn số lượng
		soLuongThuoc = "12";
		kb.dienSoLuongThuoc(soLuongThuoc);
		s.type(Key.ENTER);
		// s.type(Key.TAB);
		// Chọn liều dùng
		sleep(3);
		lieuDung = "Ngày 4 viên, chia 2 lần";
		kb.dienLieuDung(lieuDung);
		sleep(2);
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_LuuVaHoanThanh, 3);

		clickOn(FormKhamBenh.FormKhamBenh_LuuVaHoanThanh);

		if (waitForObjectPresent(HisActions.HIS_LOI_UNGDUNG, 3)) {
			s.type(Key.TAB);
			setTestcaseStatus("FAILSE", "KHÔNG LƯU ĐƯỢC KẾT LUẬN, ĐÃ GẶP SỰ CỐ!");
		} else {
			setTestcaseStatus("TRUE", "LƯU KẾT LUẬN THÀNH CÔNG!");
		}
	}

	// thoat tai khoan BS01, dang nhap tai khoan THUNGAN01 để thu tiền bệnh nhân
	// BHYT
	@Test(priority = 7)
	public void BHYT_13853_5() {

		// Thoat tai khoan BS01 va dang nhap bang tai khoan THUNGAN01
		thoatTaikhoanvaDangNhapBangTaikhoankhac(HisActions.ten_dangNhap_THUNGAN01,
				HisActions.matKhau_dangNhap_THUNGAN01);
		// Chon phong lam viec
		chonPhongLamViec("Viện phí ngoại trú");
		sleep(6);
		TestLogger.info("Xac nhan BHYT");
		waitForObjectPresent(MenuVienPhi, 5);
		clickOn(HisActions.MenuVienPhi);
		waitForObjectPresent(MenuXacNhanBHYT, 5);
		clickOn(HisActions.MenuXacNhanBHYT);

		// Nhap ma tiep nhan benh nhan
		TestLogger.info("Nhap ma tiep nhan benh nhan");
		yt.dienSotiepNhan(SoTiepNhan);

		// Xác nhận BHYT
		TestLogger.info("xac nhan BHYT");
		clickOn(FormXacNhanBaoHiemYTe.FormXacNhanBaoHiemYTe_Xacnhan);
		if (waitForObjectPresent(FormXacNhanBaoHiemYTe.FormXacNhanBaoHiemYTe_dongy, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("Pass", "Xac nhan BHYT thành cong!");
		} else {
			setTestcaseStatus("False", "Xac nhan BHYT khong thanh cong");
		}

	}

	// Thanh toan vien phi
	@Test(priority = 8)
	public void BHYT_13853_6() {
		sleep(3);
		TestLogger.info("thanh toan vien phi");
		waitForObjectPresent(MenuVienPhi, 5);
		clickOn(HisActions.MenuVienPhi);
		waitForObjectPresent(SubMenuThanhToanVienPhi, 4);
		clickOn(HisActions.SubMenuThanhToanVienPhi);

		vpnt.nhapChungTu();
		sleep(4);
		vpnt.dienSoTiepNhan(SoTiepNhan);
		sleep(4);
		String tongSoDanhMuc = layTextTuAnh(266, 625, 16, 25);

		if (tongSoDanhMuc.equalsIgnoreCase("2")) {
			setTestcaseStatus("PASS", "Đã tìm thấy đầy đủ thông tin người cần thanh toán viện phí");
			vpnt.clickOn(FormVienPhiNgoaiTru.VienPhiNgoaiTru_Luu);
			if (waitForObjectPresent(HisActions.HIS_LOI_UNGDUNG, 2)) {
				s.type(Key.ENTER);
				setTestcaseStatus("FAIL", "Không thể lưu kết quả thanh toán Viện phí , đã gặp sự cố !");
			} else {
				setTestcaseStatus("PASS", "Đã Lưu được kết quả thanh toán Viện phí ! ");

			}
		} else {
			setTestcaseStatus("FAIL",
					"Không tìm thấy đủ thông tin người cần thanh toán viện phí ,thiếu thông tin toa thuốc ! ");
		}
	
	}



	@AfterTest
	public void ketThucLuong1() {
		dangXuatKhoiTaikhoan();
	}

}
