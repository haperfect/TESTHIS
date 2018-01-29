package TestHIS;

import org.sikuli.script.Key;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HIS.DanhSachBenhNhan;
import HIS.FormKhamBenh;
import HIS.FormKhuVuc;
import HIS.TiepNhanBenhNhan;
import desktop_Framework.HisActions;
import desktop_Framework.TestLogger;
import desktop_Framework.TienIch;

public class TestFormDanhSachBenhNhan extends DanhSachBenhNhan {

	public String Hoten, NgaySinh, SoCMND, GioiTinh, SoDienThoai, NgheNghiep, DanToc, NhapNhanh, SoNha, NoiLamViec;
	public String NguoiLienHe, SoDienThoaiNguoiLienHe, DoiTuong, UuTien, HinhThuc, LiDo, TenDichVu, ThuTienSau,
			NoiThucHien;
	public String soTiepNhan;
	TiepNhanBenhNhan tnbn = new TiepNhanBenhNhan();
	HisActions his = new HisActions();
	FormKhamBenh kb = new FormKhamBenh();
	@BeforeTest
	public void dieukienDauTien() {
		if (his.khoiDongHIS(HisActions.DUONG_DAN_FILE_CHAY_HIS) == true) {
			his.dangNhapHIS(FormKhuVuc.ten_dangNhap_admin, FormKhuVuc.matKhau_dangNhap_admin);
			his.chonPhongLamViec("Khám TMH");
			moMenuTiepNhanBenhNhan();
		} else
			setTestcaseStatus("FAIL", "Không thể khởi động ứng dụng HIS !");
	}

	@Test(priority = 1)
	public void DanhSachBenhNhan_9880_3() {
		TestLogger.info("Kiểm tra tìm kiếm riêng lẻ ");

		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		tnbn.dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		tnbn.dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		tnbn.dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		tnbn.dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		tnbn.dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		tnbn.dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		tnbn.dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		tnbn.dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		tnbn.dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		tnbn.dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1234567890, 1876543210);
		tnbn.dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		tnbn.dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		tnbn.chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		tnbn.chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		tnbn.dienLiDo(LiDo);

		tnbn.dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		tnbn.dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
		tnbn.dienNoiThucHien(NoiThucHien);
		s.type(Key.TAB);

		sleep(3);
		ThuTienSau = "Yes";
		tnbn.chonThuTienSau(ThuTienSau);

		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);

		/*if (waitForObjectPresent(TiepNhanBenhNhan.Phieukham, 6)) {
			s.type(Key.F4, Key.ALT);
			setTestcaseStatus("PASS", "Tiếp nhận Bệnh nhân thành công !");
		} else {

			setTestcaseStatus("FAIL", "Tiếp nhận Bệnh nhân không thành công !");
		}*/

		TestLogger.info("Check : Bệnh nhân vừa tiếp nhận nằm trong danh sách chờ khám");
		waitForObjectPresent(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBenhNhan, 4);
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);

		dienDieuKienTimKiemBangHoTen(Hoten);
		s.type(Key.ENTER);
		String soDong = layTextTuAnh(173, 680, 12, 22);
		TestLogger.info("Expect : ");

		if (soDong.trim().equals("1")) {
			setTestcaseStatus("PASS", "Hệ thống trả ra đúng với từ khóa tìm kiếm");
		} else {
			setTestcaseStatus("FAIL", "Hệ thống trả ra đúng với từ khóa tìm kiếm");
		}

	}

	@Test(priority = 2)
	public void DanhSachBenhNhan_9880_2() {
		TestLogger.info("Kiểm tra tìm kiếm riêng lẻ ");
		TestLogger.info("Kiểm tra tìm kiếm không ra kết quả");
		dienDieuKienTimKiemBangHoTen(TienIch.taoRandomChu(15));
		s.type(Key.ENTER);
		String soDong = layTextTuAnh(173, 680, 12, 22);
		TestLogger.info(
				"Expect : Hệ thống không thực hiện trả  ra kết quả thoải mãi điều kiện tìm kiếm. Không có bản ghi nào trả ra");

		if (soDong.trim().equals("0")) {
			s.click();
			s.type(Key.F4, Key.ALT);
			setTestcaseStatus("PASS", "");
		} else {
			s.click();
			s.type(Key.F4, Key.ALT);
			setTestcaseStatus("FAIL", "");
		}
	}

	@Test(priority = 3)
	public void DanhSachBenhNhan_11420_1() {

		TestLogger.info("[Danh sách bệnh nhân_Trạng thái] Kiểm tra dữ liệu hiển thị tại cột trạng thái");
		TestLogger.info("Tại màn hình tiếp nhận ,thực hiện thêm mới 1 bệnh nhân ");
		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		tnbn.dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		tnbn.dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		tnbn.dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		tnbn.dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		tnbn.dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		tnbn.dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		tnbn.dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		tnbn.dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		tnbn.dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		tnbn.dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1234567890, 1876543210);
		tnbn.dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		tnbn.dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		tnbn.chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		tnbn.chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		tnbn.dienLiDo(LiDo);

		tnbn.dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		tnbn.dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
		tnbn.dienNoiThucHien(NoiThucHien);
		s.type(Key.TAB);

		sleep(3);
		ThuTienSau = "Yes";
		tnbn.chonThuTienSau(ThuTienSau);

		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);

		if (waitForObjectPresent(TiepNhanBenhNhan.Phieukham, 6)) {
			s.type(Key.F4, Key.ALT);
			
		} 

		TestLogger.info("Vào màn hình danh sách");
		TestLogger.info("Kiểm tra trạng thái bản ghi vừa thêm mới ở trạng thái chờ khám");
		
		waitForObjectPresent(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBenhNhan, 4);
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);

		dienDieuKienTimKiemBangHoTen(Hoten);
		s.type(Key.ENTER);

		do
		{
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		
		}
		while (!waitForObjectPresent(DanhSanhBenhNhan_CotTrangThai, 1));
		String trangThai = layTextTuAnh(1281, 199, 79, 17);
		TestLogger.info( "trang thai la : " + trangThai);
		TestLogger.info("Expect : Hiển thị được trạng thái chờ khám trong danh sách. ");
        // CM khém chính là Chờ khám 
		if (trangThai.trim().equals("CM khém"))
		{
			s.click();
			s.type(Key.F4,Key.ALT);
			setTestcaseStatus("PASS", "Hiển thị được trạng thái chờ khám trong danh sách.");
		}
		else
		{
			s.click();
			s.type(Key.F4,Key.ALT);
			setTestcaseStatus("FAIL", "Không hiển thị được trạng thái chờ khám trong danh sách.");
		}
		
		
	}
	
	@Test(priority = 4)
	public void DanhSachBenhNhan_11420_2() {

		TestLogger.info("Kiểm tra hiển thị trạng thái bản ghi ở trạng thái chờ kết quả ");
		clickToaDo(185, 50);
		// clickOn(HisActions.HIS_MenuKhamBenh);
		sleep(3);
		// click sub-menu Kham Benh
		clickToaDo(198, 74);
		sleep(3);
		clickOn(FormKhamBenh.Menu_DanhSachBenhNhan);
		
		kb.dienHoTen(Hoten);
		kb.clickTimKiem();
		String ketQuaTimKiem = layTextTuAnh(360, 612, 13, 26);
		if (ketQuaTimKiem.trim().equals("1")) {
			hoverImage(FormKhamBenh.FormKhamBenh_LamMoi);
			moveMouseDownFromLogo(FormKhamBenh.FormKhamBenh_TimKiem, 95);
			s.doubleClick();
			setTestcaseStatus("PASS", "Đã tìm thấy bệnh nhân đang chờ khám !");
		} else {
			setTestcaseStatus("FAIL", "Không tìm thấy bệnh nhân đang chờ khám !");
		} 
		
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
		
		moMenuTiepNhanBenhNhan();
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBN);
		
		TestLogger.info("Vào màn hình danh sách");
		TestLogger.info("Kiểm tra trạng thái bản ghi vừa thêm mới ở trạng thái chờ khám");
		
		waitForObjectPresent(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBenhNhan, 4);
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);

		dienDieuKienTimKiemBangHoTen(Hoten);
		s.type(Key.ENTER);
		
		
		do
		{
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		
		}
		while (!waitForObjectPresent(DanhSanhBenhNhan_CotTrangThai, 4));
		String trangThai = layTextTuAnh(1281, 199, 79, 17);
		TestLogger.info( "trang thai la : " + trangThai);
		TestLogger.info("Kiểm tra hiển thị trạng thái bản ghi ở trạng thái chờ kết quả ");
        // 
		
		if (trangThai.trim().equals("cm‘s kamé"))
		{
			
			s.click();
			s.type(Key.F4,Key.ALT);
			setTestcaseStatus("PASS", "Hiển thị được trạng thái chờ khám trong danh sách.");
		}
		else
		{
			s.click();
			s.type(Key.F4,Key.ALT);
			setTestcaseStatus("FAIL", "Không hiển thị được trạng thái chờ khám trong danh sách.");
		}
		TestLogger.info("Expect : Cột trạng thái hiện Chờ kết quả: bệnh nhân đã được bác sĩ cập nhật thông tin lâm sàng, chưa có hướng giải quyế ");
		
	}
	
	@Test (priority = 5)
	public void DanhSachBenhNhan_11420_3() {

		TestLogger.info("Sau khi bác sĩ đưa ra hướng giải quyết ,Kiểm tra hiển thị trạng thái bản ghi ở trạng thái đã khám ");
		clickToaDo(185, 50);
		// clickOn(HisActions.HIS_MenuKhamBenh);
		sleep(3);
		// click sub-menu Kham Benh
		clickToaDo(198, 74);
		sleep(3);
		clickOn(FormKhamBenh.Menu_DanhSachBenhNhan);
		
		kb.dienHoTen(Hoten);
		kb.clickTimKiem();
		String ketQuaTimKiem = layTextTuAnh(360, 612, 13, 26);
		
		if (ketQuaTimKiem.trim().equals("1")) {
			hoverImage(FormKhamBenh.FormKhamBenh_LamMoi);
			moveMouseDownFromLogo(FormKhamBenh.FormKhamBenh_TimKiem, 95);
			s.doubleClick();
			setTestcaseStatus("PASS", "Đã tìm thấy bệnh nhân đang chờ khám !");
		} else {
			setTestcaseStatus("FAIL", "Không tìm thấy bệnh nhân đang chờ khám !");
		} 
		
		clickOn(FormKhamBenh.FormKhamBenh_KetLuan);
		kb.dienBenhChinh("007");
		moveMouseHorizontallyFromLogo(FormKhamBenh.FormKhamBenh_HuongDieuTri, 296);
		s.click();
		// Chon Nhap Vien
		waitForObjectPresent(FormKhamBenh.HuongDieuTri_NhapVien, 4);
		clickOn(FormKhamBenh.HuongDieuTri_NhapVien);
		// Chon khoa nhap
		clickToaDo(568, 310);
		clickOn(FormKhamBenh.KhoaNhap_KhoaGayMeHoiSuc);

		clickOn(FormKhamBenh.FormKhamBenh_LuuVaHoanThanh);
		
		moMenuTiepNhanBenhNhan();
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBN);
		
		dienDieuKienTimKiemBangHoTen(Hoten);
		s.type(Key.ENTER);
		
		
		do
		{
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		clickToaDo(1358, 719);
		
		}
		while (!waitForObjectPresent(DanhSanhBenhNhan_CotTrangThai, 4));
		String trangThai = layTextTuAnh(1281, 199, 79, 17);
		TestLogger.info( "trang thai la : " + trangThai);
		TestLogger.info("Kiểm tra hiển thị trạng thái bản ghi ở trạng thái đã khám ");
        
		if (trangThai.trim().equals("Dﬁkhém"))
		{
			
			s.click();
			s.type(Key.F4,Key.ALT);
			setTestcaseStatus("PASS", "Hiển thị được trạng thái đã khám trong danh sách.");
		}
		else
		{
			s.click();
			s.type(Key.F4,Key.ALT);
			setTestcaseStatus("FAIL", "Không hiển thị được trạng thái đã  vbkhám trong danh sách.");
		}
		
	}
	
	
	@AfterTest
	public void ketThucLuong() {
		dangXuatKhoiTaikhoan();
		thoatUngdungHIS();
	}

}
