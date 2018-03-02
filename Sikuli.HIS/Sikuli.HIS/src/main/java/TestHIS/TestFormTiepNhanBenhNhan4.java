package TestHIS;

import java.sql.SQLException;

import org.sikuli.script.Key;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HIS.DanhSachBenhNhan;
import HIS.FormChuyenPhongKham;
import HIS.FormCoSoKhamChuaBenh;
import HIS.FormKhamBenh;
import HIS.FormKhuVuc;
import HIS.FormNgheNghiep;
import HIS.TiepNhanBenhNhan;
import desktop_Framework.HisActions;
import desktop_Framework.TestLogger;
import desktop_Framework.TienIch;

public class TestFormTiepNhanBenhNhan4 extends TiepNhanBenhNhan {

	public String Hoten, NgaySinh, SoCMND, GioiTinh, SoDienThoai, NgheNghiep, DanToc, NhapNhanh, SoNha, NoiLamViec;
	public String NguoiLienHe, SoDienThoaiNguoiLienHe, DoiTuong, UuTien, HinhThuc, LiDo, TenDichVu, ThuTienSau,
			NoiThucHien;
	public String soTiepNhan;
	public String sotheBHYT;
	public String MaDKKCB;
	public String chanDoanNGT;

	HisActions his = new HisActions();
	DanhSachBenhNhan dsbn = new DanhSachBenhNhan();

	@BeforeTest
	public void dieukienDauTien() {
		if (his.khoiDongHIS(HisActions.DUONG_DAN_FILE_CHAY_HIS) == true) {
			his.dangNhapHIS(FormKhuVuc.ten_dangNhap_admin, FormKhuVuc.matKhau_dangNhap_admin);
			his.chonPhongLamViec("Khám TMH");
			moMenuTiepNhanBenhNhan();
		} else
			setTestcaseStatus("FAIL", "Không thể khởi động ứng dụng HIS !");
	}

	@Test(priority = 166)
	public void tiepNhanBenhNhan_11316_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate combobox Tuyến");
		TestLogger.info(
				"Kiểm tra mặc định trường Tuyến khi chọn đối tượng khác đối tượng BHYT (Đối tượng Dịch vụ, Đối tượng Yêu cầu...)");
		TestLogger.info("Expect :Mặc định Blank");

		sleep(2);
		dienDoiTuong("Dịch vụ");
		s.type(Key.ENTER);
		String textdoituong = layTextTuAnh(349, 321, 152, 19);
		if (textdoituong.trim().equals("")) {
			setTestcaseStatus("PASS", "Mặc định Blank");
		} else {
			setTestcaseStatus("FAIL", "Mặc định Không phải là  Blank");
		}
	}

	@Test(priority = 167)
	public void tiepNhanBenhNhan_11316_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate combobox Tuyến");
		TestLogger.info("Kiểm tra mặc định trường Tuyến chuyển khi chọn đối tượng là BHYT");
		TestLogger.info("Expect :Mặc định là Đúng tuyến");

		sleep(2);
		dienDoiTuong("Dịch vụ");
		s.type(Key.ENTER);
		sleep(1);
		TestLogger.info("Điền số thẻ BHYT 80");
		sotheBHYT = "SV491" + taoRandomSo(10);
		dienSoTheBHYT(sotheBHYT);
		sleep(2);
		String textdoituong = layTextTuAnh(349, 321, 152, 19);
		// mm myé'n - đúng tuyến

		if (textdoituong.trim().equals("mm myé'n")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Mặc định là Đúng tuyến");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Mặc định Không phải là Đúng tuyến");
		}
	}

	@Test(priority = 168)
	public void tiepNhanBenhNhan_14793_1() {
		TestLogger.info("Kiểm tra chức năng Tiếp nhận đối tượng BHYT khi BN chưa thực hiện xác nhận BHYT");
		TestLogger.info("Dữ liệu: Đã tiếp nhận bệnh nhân đối tượng BHYT nhưng chưa xác nhận BHYT ");
		TestLogger.info("Tiếp nhận lần tiếp theo là đối tượng BHYT");
		TestLogger.info(
				"Hiển thị cảnh báo \"Lần trước chưa xác nhận BHYT.\" và không thể tiếp nhận lần này là đối tượng BHYT ");
		FormKhamBenh kb = new FormKhamBenh();
		String SoTiepNhan;
		// Tạo dữ liệu bệnh nhân BHYT, tiếp nhận nhưng chưa xác nhận BHYT
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

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		// 2.CHỌN ĐỐI TƯỢNG
		dienDoiTuong("BHYT 100%");
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
		dienMaDKKCB("01043");
		 s.type(Key.ENTER);

		sleep(4);
		TestLogger.info("chon khu vuc");
		s.type(Key.DOWN);
		// s.type(Key.DOWN);
		s.type(Key.ENTER);

		TestLogger.info("Chon tu ngay");
		dienTuNgay("30122016");
		s.type(Key.ENTER);

		TestLogger.info("Chon den ngay");
		String denngay = TienIch.getNgayHienTaicuaMayTinh() + TienIch.getThangHienTaicuaMayTinh()
				+ (Integer.parseInt(TienIch.getNamHienTaicuaMayTinh()) + 1);
		TestLogger.info("den ngay la" + denngay);
		dienDenNgay(denngay);

		TestLogger.info("Nhap noi gioi thieu");
		dienNoiGioiThieu("Bệnh viện Thanh Nhàn11111");
		s.type(Key.TAB);

		TestLogger.info("Nhap so chuyen tuyen");
		dienSoTuyenChuyen("012");
		s.type(Key.TAB);

		TestLogger.info("Nhap ngay chuyen");
		String ngaychuyen = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgayChuyen(ngaychuyen);
		s.type(Key.TAB);

		TestLogger.info("Nhap tuyen chuyen");
		dienTuyenChuyen("Tuyến dưới liền kề");
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
		if (waitForObjectPresent(Phieukham, 10)) {
			clickToaDo(696, 435);
			s.type(Key.F4, Key.ALT);
		}
		sleep(10);
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 5);
		clickOn(TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);
		dsbn.dienDieuKienTimKiem(Hoten);
		dsbn.clickDupVaoBenhNhanChoKham();
		sleep(3);
		clickOn(TiepNhanBenhNhan_Sua);
		SoTiepNhan = getSoTiepNhan();
		sotheBHYT = getSoTheBHYT();
		sleep(2);
		clickToaDo(1233, 31);
		sleep(2);
		chonPhongLamViec("Khám theo yêu cầu");
		sleep(2);
		s.type(Key.ENTER);
		waitForObjectPresent(HisActions.HIS_MenuKhamBenh, 5);
		sleep(6);
		clickOn(HisActions.HIS_MenuKhamBenh);
		clickToaDo(215, 73);

		kb.dienSoTiepNhan(SoTiepNhan);
		kb.clickTimKiem();
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_soDongBang1, 5);
		hoverImage(FormKhamBenh.FormKhamBenh_LamMoi);
		moveMouseDownFromLogo(FormKhamBenh.FormKhamBenh_TimKiem, 95);
		s.doubleClick();
		// BS đo chỉ số sinh tồn cho bệnh nhân
		sleep(2);
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
		sleep(2);
		// BS Khám bệnh sau khi đã đo xong chỉ số sinh tồn
		clickOn(FormKhamBenh.FormKhamBenh_KhamBenh);
		kb.dienLiDoKham("Dau Bung");
		kb.dienBenhChinhDefault();
		clickOn(FormKhamBenh.FormKhamBenh_Luu);
		// BS kết luận
		sleep(2);
		clickOn(FormKhamBenh.FormKhamBenh_KetLuan);
		moveMouseHorizontallyFromLogo(FormKhamBenh.FormKhamBenh_HuongDieuTri, 297);
		s.click();
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_NhapVien, 5);
		clickOn(FormKhamBenh.FormKhamBenh_NhapVien);
		;
		sleep(3);

		s.type(Key.TAB);
		clickToaDo(569, 308);
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_KhoaNhapVien, 5);
		clickOn(FormKhamBenh.FormKhamBenh_KhoaNhapVien);
		sleep(3);
		waitForObjectPresent(FormKhamBenh.FormKhamBenh_LuuVaHoanThanh, 3);
		clickOn(FormKhamBenh.FormKhamBenh_LuuVaHoanThanh);
		sleep(2);
		waitForObjectPresent(HisActions.HIS_MenuTiepNhan, 5);
		sleep(4);
		clickOn(HisActions.HIS_MenuTiepNhan);
		clickToaDo(139, 78);
		// waitForObjectPresent(TiepNhanBenhNhan_BoQua, 5);
		// clickOn(TiepNhanBenhNhan_BoQua);
		// sleep(2);
		// waitForObjectPresent(TiepNhanBenhNhan_Them, 5);
		// clickOn(TiepNhanBenhNhan_Them);
		sleep(15);
		dienSoTheBHYT(sotheBHYT);
		sleep(1);
		
		dienMaDKKCB("01043");
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachCungMaBHYT, 5);
		clickOn(TiepNhanBenhNhan_DanhSachCungMaBHYTChon);
		s.type(Key.ENTER);
		sleep(5);
		chonHinhThuc("Tự đến");
		sleep(2);
		
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
		sleep(2);
		
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "không thể tiếp nhận lần này là đối tượng BHYT ");
		} else {
			setTestcaseStatus("FAIL", "Tiếp nhận đang thành công");
		}
	}

	@Test(priority = 169)
	public void tiepNhanBenhNhan_14793_2() {
		TestLogger.info("Kiểm tra chức năng Tiếp nhận đối tượng BHYT khi BN chưa thực hiện xác nhận BHYT");
		TestLogger.info("Dữ liệu: Đã tiếp nhận bệnh nhân đối tượng BHYT nhưng chưa xác nhận BHYT ");
		TestLogger.info("Tiếp nhận lần tiếp theo là đối tượng Dịch vụ");
		TestLogger.info("Cho Lưu và không hiển thị cảnh báo ");
		sleep(3);
		dienDoiTuong("Dịch vụ");
		sleep(2);
		chonHinhThuc("Tự đến");
		sleep(2);
		clickOn(TiepNhanBenhNhan_Luu);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Không Cho Lưu và hiển thị cảnh báo  ");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Cho Lưu và không hiển thị cảnh báo ");

		}
	}

	//@Test(priority = 170)
	public void tiepNhanBenhNhan_14478_1() {
		TestLogger.info(" Kiểm tra chức năng Sửa khi chưa thanh toán hoặc chưa thực hiện");
		TestLogger.info("Bấm Sửa khi dv khám chưa thực hiện/ chưa thanh toán");
		TestLogger.info(
				"Cho phép sửa thông tin Hành chính, check box Ưu tiên, Xóa dịch vụ chưa thực hiện/ chưa thanh toán");
		// ---------------Mã bệnh nhân 18000037-------------------
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 5);
		clickOn(TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);
		moveMouseHorizontallyFromLogo(DanhSachBenhNhan.DanhSachBenhNhan_DieuKien, 100);
		s.click();
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type("18000037");
		s.type(Key.TAB);
		sleep(2);
		s.type(Key.TAB);
		s.type("01012018");
		s.type(Key.TAB);
		sleep(1);
		s.type(Key.TAB);
		s.type(Key.ENTER);
		dsbn.clickDupVaoBenhNhanChoKham();
		sleep(3);
		clickOn(TiepNhanBenhNhan_Sua);
		if (waitForObjectPresent(TiepNhanBenhNhan_XoaDichVu, 5)) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Cho phép sửa");
		} else {
			setTestcaseStatus("FAIL", "Không Cho phép sửa");
		}

	}

	@Test(priority = 171)
	public void tiepNhanBenhNhan_14478_2() {
		TestLogger.info(" Kiểm tra chức năng Sửa khi dịch vụ đã được thực hiện");
		TestLogger.info("Bấm Sửa khi dv khám đã được thực hiện");
		TestLogger.info("Không cho phép sửa");
		// ---------------Mã bệnh nhân 18000472-------------------
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 5);
		clickOn(TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);
		moveMouseHorizontallyFromLogo(DanhSachBenhNhan.DanhSachBenhNhan_DieuKien, 100);
		s.click();
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type("18000472");
		s.type(Key.TAB);
		sleep(2);
		s.type(Key.TAB);
		s.type("01012018");
		s.type(Key.TAB);
		sleep(1);
		s.type(Key.TAB);
		s.type(Key.ENTER);
		dsbn.clickDupVaoBenhNhanChoKham();
		sleep(3);
		clickOn(TiepNhanBenhNhan_Sua);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Không Cho phép sửa");

		} else {
			setTestcaseStatus("FAIL", "Cho phép sửa");
		}

	}

	@Test(priority = 172)
	public void tiepNhanBenhNhan_10124_1() {
		TestLogger.info("Kiểm tra validate trường label Tên nơi ĐKKCB");
		TestLogger.info("Kiểm tra load ra tên nơi ĐKKCB khi nhập mã ĐKKCB đúng ");
		TestLogger.info("Load ra tên nơi ĐKKCB tương ứng với mã ĐKKCB ");
		// ------------01043-Bệnh viện thanh nhàn-------------
		dienMaDKKCB("01043");
		sleep(3);
		if (waitForObjectPresent(TiepNhanBenhNhan_NoiDKKCB, 5)) {
			setTestcaseStatus("PASS", "Load ra tên nơi ĐKKCB tương ứng với mã ĐKKCB");
		} else {
			setTestcaseStatus("FAIL", "KHông Load ra tên nơi ĐKKCB tương ứng với mã ĐKKCB");
		}
	}

	@Test(priority = 173)
	public void tiepNhanBenhNhan_10124_2() {
		TestLogger.info("Kiểm tra validate trường label Tên nơi ĐKKCB");
		TestLogger.info("Kiểm tra khi nhập mã ĐKKCB chưa tồn tại trong danh mục");
		TestLogger.info("Không load ra tên nơi ĐKKCB");
		// ------------09090-Mã này không tồn tại-------------
		dienMaDKKCB("09090");
		String laytext = layTextTuAnh(159, 349, 278, 24);
		sleep(3);
		if (laytext.trim().length() == 0) {
			setTestcaseStatus("PASS", "Không load ra tên nơi ĐKKCB");
		} else {
			setTestcaseStatus("FAIL", "load ra tên nơi ĐKKCB");
		}
	}

	@Test(priority = 174)
	public void tiepNhanBenhNhan_10124_3() {
		TestLogger.info("Kiểm tra validate trường label Tên nơi ĐKKCB");
		TestLogger.info("Kiểm tra khi Danh mục Tên cơ sở KCB bị tạm ngưng");
		TestLogger.info("Không load ra Tên nơi ĐKKCB");
		clickOn(FormKhuVuc.Menu_DanhMuc);
		hoverImage(FormKhuVuc.MenuDanhMuc_BenhVien);
		clickOn(FormKhuVuc.MenuDanhMuc_CoSoKhamChuaBenh);
		waitForObjectPresent(FormCoSoKhamChuaBenh.FormCoSoKhamChuaBenh_Sua, 5);
		clickToaDo(166, 134);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type("00012");
		doubleClick(FormCoSoKhamChuaBenh.FormCoSoKhamChuaBenh_QuocTeVinh);
		waitForObjectPresent(FormCoSoKhamChuaBenh.FormCoSoKhamChuaBenh_FormThemMoi, 5);
		clickOn(FormCoSoKhamChuaBenh.FormThemMoi_TamNgung);
		clickOn(FormCoSoKhamChuaBenh.FormThemMoi_Luu);
		sleep(3);
		clickOn(TiepNhanBenhNhan_HeThong);
		clickOn(TiepNhanBenhNhan_DangXuat);
		/*clickOn(TiepNhanBenhNhan_HeThong);
		clickOn(TiepNhanBenhNhan_DangNhap);*/
		dangNhapHIS(FormKhuVuc.ten_dangNhap_admin, FormKhuVuc.matKhau_dangNhap_admin);
		chonPhongLamViec("Khám theo yêu cầu");
		sleep(2);
		clickOn(HIS_MenuTiepNhan);
		waitForObjectPresent(HIS_SubMenuTiepNhanBenhNhan, 5);
		clickOn(HIS_SubMenuTiepNhanBenhNhan);
		waitForObjectPresent(TiepNhanBenhNhan.AfterLoading, 15);
		dienMaDKKCB("00012");
		sleep(3);
		String laytext = layTextTuAnh(159, 349, 278, 24);
		sleep(3);
		if (laytext.trim().length() == 0) {
			setTestcaseStatus("PASS", "Không load ra tên nơi ĐKKCB");
		} else {
			setTestcaseStatus("FAIL", "load ra tên nơi ĐKKCB");
		}

	}

	@Test(priority = 175)
	public void tiepNhanBenhNhan_10124_4() {
		TestLogger.info("Kiểm tra validate trường label Tên nơi ĐKKCB");
		TestLogger.info("Kiểm tra khi Danh mục Tên cơ sở KCB được cập nhật (Thêm mới hoặc sửa thông tin)");
		TestLogger.info("Load ra thông tin cập nhật mới nhất ");
		clickOn(FormKhuVuc.Menu_DanhMuc);
		hoverImage(FormKhuVuc.MenuDanhMuc_BenhVien);
		clickOn(FormKhuVuc.MenuDanhMuc_CoSoKhamChuaBenh);
		waitForObjectPresent(FormCoSoKhamChuaBenh.FormCoSoKhamChuaBenh_Sua, 5);
		clickToaDo(166, 134);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type("00012");
		doubleClick(FormCoSoKhamChuaBenh.FormCoSoKhamChuaBenh_QuocTeVinh);
		waitForObjectPresent(FormCoSoKhamChuaBenh.FormCoSoKhamChuaBenh_FormThemMoi, 5);
		s.type(Key.ENTER);
		sleep(1);
		s.type(Key.ENTER);
		sleep(1);
		String soRanDom = TienIch.taoRandomSo(5);

		setClipboardValue("Bệnh viện Quốc tế Vinh" + soRanDom);

		s.type("v", Key.CTRL);
		clickOn(FormCoSoKhamChuaBenh.FormThemMoi_TamNgung);

		clickOn(FormCoSoKhamChuaBenh.FormThemMoi_Luu);
		sleep(3);
		clickOn(TiepNhanBenhNhan_HeThong);
		clickOn(TiepNhanBenhNhan_DangXuat);
		/*clickOn(TiepNhanBenhNhan_HeThong);
		clickOn(TiepNhanBenhNhan_DangNhap);*/
		dangNhapHIS(FormKhuVuc.ten_dangNhap_admin, FormKhuVuc.matKhau_dangNhap_admin);
		chonPhongLamViec("Khám theo yêu cầu");
		sleep(2);
		clickOn(HIS_MenuTiepNhan);
		waitForObjectPresent(HIS_SubMenuTiepNhanBenhNhan, 5);
		clickOn(HIS_SubMenuTiepNhanBenhNhan);
		waitForObjectPresent(TiepNhanBenhNhan.AfterLoading, 15);
		dienMaDKKCB("00012");
		sleep(3);
		String laytext = layTextTuAnh(317, 350, 49, 22);
		TestLogger.info(laytext);

		if (laytext.contains(soRanDom)) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Load ra thông tin cập nhật mới nhất");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Khong Load ra thông tin cập nhật mới nhất");
		}
	}

	@Test(priority = 176)
	public void tiepNhanBenhNhan_10354_1() {
		TestLogger.info("[Tiếp nhận BN chung] Kiểm tra chức năng Thêm");
		TestLogger.info("Kiểm tra trạng thái mặc định của nút Thêm ");
		TestLogger.info("Mặc định Disable nút Thêm khi mở form Tiếp nhận");

		if (waitForObjectPresent(TiepNhanBenhNhan_DisnableThem, 5)) {
			setTestcaseStatus("PASS", "Mặc định Disable nút Thêm khi mở form Tiếp nhận");
		} else {
			setTestcaseStatus("FAIL", "Không Disable nút Thêm khi mở form Tiếp nhận");
		}
	}

	@Test(priority = 177)
	public void tiepNhanBenhNhan_10354_2() {
		TestLogger.info("[Tiếp nhận BN chung] Kiểm tra chức năng Thêm");
		TestLogger.info("Kiểm tra trạng thái của nút Thêm sau khi Lưu dữ liệu bệnh nhân trong form thêm mới");
		TestLogger.info("Chỉ khi nút Lưu disable, nút Thêm mới được enable");
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

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		sleep(2);
		
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

		if (waitForObjectPresent(TiepNhanBenhNhan_DisnableThem, 5)) {
			// s.type(Key.F4, Key.ALT);
			
			setTestcaseStatus("PASS", "Disable nút Thêm");
		} else {
			
			setTestcaseStatus("FAIL", "Không Disable nút Thêm");
		}

	}

	@Test(priority = 178)
	public void tiepNhanBenhNhan_10354_3() {
		TestLogger.info("[Tiếp nhận BN chung] Kiểm tra chức năng Thêm");
		TestLogger.info("Kiểm tra hiển thị form khi bấm vào Thêm");
		TestLogger.info(
				"Khi bấm vào nút Thêm, form Tiếp nhận thêm mới được mở ra. Các thông tin mặc định được mặc định giống form Tiếp nhận mới");
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

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
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

		sleep(2);
		clickOn(TiepNhanBenhNhan_Luu);

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 5);
		clickOn(TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);
		dsbn.dienDieuKienTimKiem(Hoten);
		dsbn.clickDupVaoBenhNhanChoKham();
		sleep(3);
		waitForObjectPresent(TiepNhanBenhNhan_Them, 5);
		clickOn(TiepNhanBenhNhan_Them);
		sleep(2);
		if (getHoten().equals("")) {
			setTestcaseStatus("PASS", "tất cả dữ liệu đang trống");
		} else {
			setTestcaseStatus("FAIL", "Đang chứa dữ liệu");
		}
	}

	@Test(priority = 179)
	public void tiepNhanBenhNhan_10354_4() {
		TestLogger.info("[Tiếp nhận BN chung] Kiểm tra chức năng Thêm");
		TestLogger.info("Kiểm tra load ra dữ liệu update mới nhất từ các danh mục");
		TestLogger.info("Khi bấm Thêm, các dữ liệu mới nhất từ các combobox phải được load ra");
		clickOn(FormKhuVuc.Menu_DanhMuc);
		hoverImage(FormKhuVuc.Menu_HanhChinh);
		// clickOn(FormKhuVuc.MenuDanhMuc_NgheNghiep);
		// Click tọa độ chọn menu nghề nghiệp
		clickToaDo(913, 222);
		waitForObjectPresent(FormNgheNghiep.FormNgheNghiep_Title, 5);
		clickOn(FormNgheNghiep.FormNgheNghiep_ThemMoi);
		sleep(2);
		String MaNgheNghiep;
		MaNgheNghiep = TienIch.taoRandomSo(3);
		s.type(MaNgheNghiep);
		s.type(Key.ENTER);
		String TenNgheNghiep = "LUC TEST AUTO" + TienIch.taoRandomChu(3);
		s.type(TenNgheNghiep);
		sleep(2);
		clickOn(FormNgheNghiep.FormNgheNghiep_Luu);
		clickOn(TiepNhanBenhNhan_HeThong);
		clickOn(TiepNhanBenhNhan_DangXuat);
	/*	clickOn(TiepNhanBenhNhan_HeThong);
		clickOn(TiepNhanBenhNhan_DangNhap);*/
		dangNhapHIS(FormKhuVuc.ten_dangNhap_admin, FormKhuVuc.matKhau_dangNhap_admin);
		chonPhongLamViec("Khám theo yêu cầu");
		sleep(2);
		clickOn(HIS_MenuTiepNhan);
		waitForObjectPresent(HIS_SubMenuTiepNhanBenhNhan, 5);
		clickOn(HIS_SubMenuTiepNhanBenhNhan);
		waitForObjectPresent(TiepNhanBenhNhan.AfterLoading, 15);
		 sleep(2);
		dienNgheNghiep(TenNgheNghiep);
		s.type(Key.ENTER);
		if (getNgheNghiep().equalsIgnoreCase(TenNgheNghiep)) {
			setTestcaseStatus("PASS", "Lấy dữ liệu thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy dữ liệu không thành công");
		}

	}

	@Test(priority = 180)
	public void tiepNhanBenhNhan_10485_1() {
		TestLogger.info("[Tiếp nhận BN chung] Kiểm tra chức năng Bỏ qua");
		TestLogger.info("Kiểm tra mặc định button Bỏ qua");
		TestLogger.info("Mặc định Enable khi bắt đầu mở form Tiếp nhận ");
		waitForObjectPresent(TiepNhanBenhNhan_BoQua, 5);
		clickOn(TiepNhanBenhNhan_BoQua);
		if (waitForObjectPresent(TiepNhanBenhNhan_XacNhanBoQua, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Giá trị mặc định là Enable");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không phải là Enable");
		}
	}

	@Test(priority = 181)
	public void tiepNhanBenhNhan_10485_2_1() {
		TestLogger.info("[Tiếp nhận BN chung] Kiểm tra chức năng Bỏ qua");
		TestLogger.info("Kiểm tra Nhập dở thông tin BN, click buttom Bỏ qua, xác nhận bỏ qua");
		TestLogger.info("Reset về form trắng");
		// Điền họ tên
		Hoten = "TRAN VAN LUC" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		waitForObjectPresent(TiepNhanBenhNhan_BoQua, 5);
		clickOn(TiepNhanBenhNhan_BoQua);
		waitForObjectPresent(TiepNhanBenhNhan_XacNhanBoQua, 5);
		s.type(Key.ENTER);
		if (getHoten().equalsIgnoreCase("")) {
			setTestcaseStatus("PASS", "Reset màn hình về form trắng");
		} else {
			setTestcaseStatus("FAIL", "Không Reset màn hình về form trắng");
		}
	}

	@Test(priority = 182)
	public void tiepNhanBenhNhan_10485_2_2() {
		TestLogger.info("[Tiếp nhận BN chung] Kiểm tra chức năng Bỏ qua");
		TestLogger.info("Kiểm tra Nhập dở thông tin BN, click buttom Bỏ qua, xác nhận không bỏ qua");
		TestLogger.info("Expect: Giữ nguyên thông tin đang nhập");
		// Điền họ tên
		Hoten = "TRAN VAN LUC" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		waitForObjectPresent(TiepNhanBenhNhan_BoQua, 5);
		clickOn(TiepNhanBenhNhan_BoQua);
		waitForObjectPresent(TiepNhanBenhNhan_XacNhanBoQua, 5);
		s.type(Key.RIGHT);
		s.type(Key.ENTER);
		if (getHoten().equalsIgnoreCase(Hoten)) {
			setTestcaseStatus("PASS", "Giữ nguyên thông tin đang nhập");
		} else {
			setTestcaseStatus("FAIL", "Giữ nguyên thông tin đang nhập");
		}
	}

	@Test(priority = 183)
	public void tiepNhanBenhNhan_10485_3() {
		TestLogger.info("[Tiếp nhận BN chung] Kiểm tra chức năng Bỏ qua");
		TestLogger.info(
				"Tiếp nhận thành công 1 BN A --> Bấm Lưu. Khi đó gọi đến form Thêm. Thêm bệnh nhân B --> Đang thêm bệnh nhân B --> Bấm Bỏ qua");
		TestLogger.info("Expect:Quay về thông tin của bệnh nhân A ");
		// Tiếp Nhận Bệnh Nhân A
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

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		waitForObjectPresent(TiepNhanBenhNhan_Luu, 5);
		clickOn(TiepNhanBenhNhan_Luu);
		sleep(2);
		clickOn(TiepNhanBenhNhan_BoQua);
		s.type(Key.ENTER);
		sleep(1);
		clickOn(TiepNhanBenhNhan_Them);
		// Tiếp nhận thêm bệnh nhân B
		dienHoTen("Nguyễn Thị Hoa");
		clickOn(TiepNhanBenhNhan_BoQua);
		s.type(Key.ENTER);
		if (getHoten().equalsIgnoreCase(Hoten)) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Quay về thông tin của bệnh nhân A ");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Không quay về thông tin của bệnh nhân A");
		}

	}

	@Test(priority = 184)
	public void tiepNhanBenhNhan_10337_1() {
		TestLogger
				.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá BHYT - Check BHYT không chi trả cho ngoại trú");
		TestLogger.info(
				"Kiểm tra cột Đơn giá BHYT trong trường hợp check BHYT không chi trả cho ngoại trú trong danh mục Dịch vụ");
		TestLogger.info(
				"Expect:Cột đơn giá BHYT = 0, còn đơn giá doanh thu vẫn lấy theo nhóm giá có thứ tự ưu tiên đã được cấu hình theo nhóm đối tượng.");

		DoiTuong = "BHYT 80%";
		clickToaDo(218, 277);
		waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
		clickOn(TiepNhanBenhNhan_BHYT80);
		s.type(Key.ENTER);

		dienTenDichVu("Vận chuyển cấp cứu");
		clickToaDo(1032, 382);
		String laytext = layTextTuAnh(992, 531, 74, 17);
		if (laytext.trim().equals("0")) {
			setTestcaseStatus("PASS", "Cột đơn giá BHYT = 0");
		} else {
			setTestcaseStatus("FAIL", "Cột đơn giá BHYT # 0");
		}
	}

	@Test(priority = 185)
	public void tiepNhanBenhNhan_10337_2() {
		TestLogger
				.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá BHYT - Check BHYT không chi trả cho ngoại trú");
		TestLogger.info(
				"Kiểm tra cột Đơn giá BHYT trong trường hợp check BHYT không chi trả cho ngoại trú trong danh mục Dịch vụ, sau đó uncheck");
		TestLogger.info("Expect:Cột Đơn giá BHYT lấy giá của danh mục giá BHYT của dịch vụ ");
		// -----------Vận chuyển xe cứu thương loại 1- Đơn giá
		// BHYT:150.000--------------------

		dienDoiTuong("BHYT 80%");

		dienTenDichVu("Vận chuyển xe cứu thương loại I");
		clickToaDo(1032, 382);
		String laytext = layTextTuAnh(992, 531, 74, 17);
		if (laytext.trim().equals("150.000")) {
			setTestcaseStatus("PASS", "Cột Đơn giá BHYT lấy giá của danh mục giá BHYT của dịch vụ");
		} else {
			setTestcaseStatus("FAIL", "Cột Đơn giá BHYT lấy giá của danh mục giá BHYT của dịch vụ");
		}
	}

	@Test(priority = 186)
	public void tiepNhanBenhNhan_10465_1() {
		TestLogger
				.info("[Tiếp nhận BN chung] Kiểm tra chức năng Hủy (nút xóa trên grid Đăng ký khám) khi đã thực hiện");
		TestLogger.info("Kiểm tra xóa dịch vụ đang chọn tại Grid khi dịch vụ đã thực hiện");
		TestLogger.info("Expect:Không cho phép xóa");
		// --------------------17001224----------------
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 5);
		clickOn(TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);
		moveMouseHorizontallyFromLogo(DanhSachBenhNhan.DanhSachBenhNhan_DieuKien, 100);
		s.click();
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type("17001224");
		s.type(Key.TAB);
		sleep(2);
		s.type(Key.TAB);
		s.type("01012018");
		s.type(Key.TAB);
		sleep(1);
		s.type(Key.TAB);
		s.type(Key.ENTER);
		dsbn.clickDupVaoBenhNhanChoKham();
		clickOn(TiepNhanBenhNhan_Sua);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Không cho phép xóa");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Cho phép xóa");
		}
	}

	@Test(priority = 187)
	public void tiepNhanBenhNhan_10465_2() {
		TestLogger
				.info("[Tiếp nhận BN chung] Kiểm tra chức năng Hủy (nút xóa trên grid Đăng ký khám) khi đã thực hiện");
		TestLogger.info("Kiểm tra xóa dịch vụ khi Grid chứa cả dịch vụ chưa thực hiện và đã thực hiện");
		TestLogger.info("Expect:Chỉ cho phép xóa dịch vụ chưa thực hiện ");
		// --------------------118000037----------------
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 5);
		clickOn(TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);
		moveMouseHorizontallyFromLogo(DanhSachBenhNhan.DanhSachBenhNhan_DieuKien, 100);
		s.click();
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type("18000037");
		s.type(Key.TAB);
		sleep(2);
		s.type(Key.TAB);
		s.type("01012018");
		s.type(Key.TAB);
		sleep(1);
		s.type(Key.TAB);
		s.type(Key.ENTER);
		dsbn.clickDupVaoBenhNhanChoKham();
		clickOn(TiepNhanBenhNhan_Sua);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Không phép xóa");
		} else {
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Cho phép xóa");
		}
	}

	@Test(priority = 188)
	public void tiepNhanBenhNhan_10300_1() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Thành tiền doanh thu");
		TestLogger.info("Kiểm tra giá trị mặc định khi chưa chọn dịch vụ");
		TestLogger.info("Expect:Mặc định giá trị trống, không cho phép nhập");
		clickToaDo(1032, 382);
		String laytext = layTextTuAnh(873, 532, 113, 16);
		if (laytext.trim().equals("")) {
			setTestcaseStatus("PASS", "Mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Mặc định không để trống");
		}
	}

	@Test(priority = 189)
	public void tiepNhanBenhNhan_10300_2() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Thành tiền doanh thu");
		TestLogger.info("Kiểm tra giá trị khi chọn dịch vụ đã có giá tại cột Đơn giá doanh thu");
		TestLogger.info("Expect:Thành tiền doanh thu = SL x Đơn giá doanh thu, không cho chỉnh sửa");
		// -----------Số lượng = 1
		// -----------Đơn giá doanh thu = 150.000
		// =>> Thành tiền doanh thu = 150.000
		dienDoiTuong("BHYT 80%");
		dienTenDichVu("Vận chuyển xe cứu thương loại I");
		sleep(3);
		String laytext = layTextTuAnh(873, 532, 113, 16);
		if (laytext.trim().equals("150.000")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Lấy ra giá tiền đúng");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Lấy ra giá tiền sai");
		}
	}

	@Test(priority = 190)
	public void tiepNhanBenhNhan_10260_1() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Mã DV");
		TestLogger.info("Kiểm tra giá trị mặc định");
		TestLogger.info("Expect:Mặc định giá trị trống, không cho phép nhập");
		String laytext = layTextTuAnh(102, 530, 80, 20);
		if (laytext.trim().equals("")) {
			setTestcaseStatus("PASS", "Mặc định giá trị trống, không cho phép nhập");
		} else {
			setTestcaseStatus("FAIL", "Mặc định Khác giá trị trống, không cho phép nhập");
		}
	}

	@Test(priority = 191)
	public void tiepNhanBenhNhan_10260_2() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Mã DV");
		TestLogger.info("Kiểm tra hiển thị mã DV");
		TestLogger.info(
				"Expect:Khi chọn dịch vụ, Mã DV được tự động load theo dịch vụ đã chọn theo quy ước trong Danh mục Dịch vụ. ");
		// ----------------Dịch vụ Vận chuyển xe cứu thương loại 1 - mã VCC---
		dienTenDichVu("Vận chuyển xe cứu thương loại I");
		s.type(Key.ENTER);
		String laytext = layTextTuAnh(102, 530, 80, 20);
		if (laytext.trim().equals("vcc")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Mặc định giá trị trống, không cho phép nhập");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Mặc định Khác giá trị trống, không cho phép nhập");
		}
	}

	@Test(dependsOnMethods = {"tiepNhanBenhNhan_10260_2"})
	public void tiepNhanBenhNhan_10304_1() {
		TestLogger.info(
				"[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu_Đối tượng BHYT+Dịch vụ không có giá BHYT");
		TestLogger.info("Kiểm tra giá trị mặc định khi chưa chọn dịch vụ");
		TestLogger.info("Expect:Mặc định giá trị trống, không cho phép nhập");
		String laytext = layTextTuAnh(758, 531, 111, 16);
		if (laytext.trim().equals("")) {
			setTestcaseStatus("PASS", "Mặc định giá trị trống, không cho phép nhập");
		} else {
			setTestcaseStatus("FAIL", "Mặc định Khác giá trị trống, không cho phép nhập");
		}
	}

	@Test(priority = 193)
	public void tiepNhanBenhNhan_10304_2() {
		TestLogger.info(
				"[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu_Đối tượng BHYT+Dịch vụ không có giá BHYT");
		TestLogger.info("Kiểm tra giá trị hiển thị tại cột Đơn giá doanh thu đối với dịch vụ không có giá BHYT");
		TestLogger.info("Expect:Hiển thị đúng giá theo cấu hình");
		// -----Dịch vụ Vận chuyển cấp cứu
		// Giá dịch vụ = 500.000
		// Giá BHYT = 0
		// Giá yêu cầu = 0
		// Check chọn giá dịch vụ có ưu tiên nhỏ hơn giá yêu cầu
		// Expect: Hiển thị giá doanh thu = 500.000

		dienDoiTuong("BHYT 80%");

		dienTenDichVu("Vận chuyển cấp cứu");
		s.type(Key.ENTER);

		String laytext = layTextTuAnh(758, 531, 111, 16);
		if (laytext.trim().equals("500.000")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);

			setTestcaseStatus("PASS", "Hiển thị đúng giá theo cấu hình");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Hiển thị sai giá theo cấu hình");
		}
	}

	@Test(priority = 194)
	public void tiepNhanBenhNhan_10336_1() {
		TestLogger
				.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu_Đối tượng BHYT +Dịch vụ có giá BHYT");
		TestLogger.info("Kiểm tra giá trị mặc định khi chưa chọn dịch vụ");
		TestLogger.info("Expect:Mặc định giá trị trống, không cho phép nhập");

		String laytext = layTextTuAnh(758, 531, 111, 16);
		if (laytext.trim().equals("")) {
			setTestcaseStatus("PASS", "Mặc định giá trị trống, không cho phép nhập");
		} else {
			setTestcaseStatus("FAIL", "Mặc định Khác giá trị trống, không cho phép nhập");
		}
	}

	@Test(priority = 195)
	public void tiepNhanBenhNhan_10336_2() {
		TestLogger
				.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu_Đối tượng BHYT +Dịch vụ có giá BHYT");
		TestLogger.info(
				"Kiểm tra giá trị hiển thị tại cột Đơn giá doanh thu đối với dịch vụ có giá BHYT (TH không check có thu chênh lệch");
		TestLogger.info("Expect:Thì đơn giá doanh thu=đơn giá BHYT hiện hành");
		// Dịch vụ: Vận chuyển xe cứu thương loại I
		// Cấu hình check không thu chênh lệch
		// Giá BHYT hiện tại = 150.000
		// Giá dịch vụ: 1.200.000
		// nhóm giá BHYT (check hỗ trợ và ưu tiên =1)
		// nhóm giá dịch vụ (ưu tiên=2)
		// nhóm giá yêu cầu (ưu tiên=3)
		// Expect: Đơn giá doanh thu = 150.000
		dienDoiTuong("BHYT 80%");

		dienTenDichVu("Vận chuyển xe cứu thương loại I");
		s.type(Key.ENTER);

		String laytext = layTextTuAnh(758, 531, 111, 16);
		if (laytext.trim().equals("150.000")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);

			setTestcaseStatus("PASS", "Thì đơn giá doanh thu=đơn giá BHYT hiện hành");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Thì đơn giá doanh thu#đơn giá BHYT hiện hành");
		}
	}

	@Test(priority = 196)
	public void tiepNhanBenhNhan_10336_3() {
		TestLogger
				.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu_Đối tượng BHYT +Dịch vụ có giá BHYT");
		TestLogger.info(
				"Kiểm tra giá trị hiển thị tại cột Đơn giá doanh thu đối với dịch vụ có giá BHYT (TH có check thu chênh lệch");
		TestLogger.info("Expect:Đơn giá doanh thu=nhóm giá có thứ tự nhỏ hơn.");
		// Dịch vụ: Khám nội 1
		// Cấu hình check không thu chênh lệch
		// Giá BHYT hiện tại = 195.000
		// Giá dịch vụ:50.000
		// nhóm giá BHYT (check hỗ trợ và ưu tiên =1)
		// nhóm giá dịch vụ (ưu tiên=2)
		// nhóm giá yêu cầu (ưu tiên=3)
		// Expect: Đơn giá doanh thu = 50.000
		dienDoiTuong("BHYT 80%");

		dienTenDichVu("Khám nội 1");
		s.type(Key.ENTER);

		String laytext = layTextTuAnh(758, 531, 111, 16);
		if (laytext.trim().equals("50.000")) {
			clickToaDo(1358, 383);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);

			setTestcaseStatus("PASS", "Đơn giá doanh thu=nhóm giá có thứ tự nhỏ hơn");
		} else {
			clickToaDo(1358, 383);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Đơn giá doanh thu#nhóm giá có thứ tự nhỏ hơn");
		}
	}

	@Test(priority = 197)
	public void tiepNhanBenhNhan_10525_1() {
		TestLogger.info("[Đăng ký khám] Sắp xếp tại Grid đăng ký khám");
		TestLogger.info("Kiểm tra sắp xếp tăng dần  ");
		TestLogger.info("Expect:Gridview sẽ được sắp xếp theo thứ tự tăng dần ");

		dienTenDichVu("Vận chuyển cấp cứu");
		s.type(Key.ENTER);
		//s.type(Key.ENTER);
		sleep(2);
		clickToaDo(215, 564);
		dienTenDichVu2("Khám tiêu hóa");
		s.type(Key.ENTER);
		sleep(2);
		waitForObjectPresent(TiepNhanBenhNhan_TenDichVu, 5);
		clickOn(TiepNhanBenhNhan_TenDichVu);
		sleep(2);
		String laytext = layTextTuAnh(56, 534, 41, 17);
		if (laytext.trim().equals("2")) {
			setTestcaseStatus("PASS", "Gridview được sắp xếp theo thứ tự tăng dần");
		} else {
			setTestcaseStatus("FAIL", "Gridview không được sắp xếp theo thứ tự tăng dần");
		}
	}

	@Test(priority = 198)
	public void tiepNhanBenhNhan_10525_2() {
		TestLogger.info("[Đăng ký khám] Sắp xếp tại Grid đăng ký khám");
		TestLogger.info("Kiểm tra sắp xếp giảm dần  ");
		TestLogger.info("Expect: Click 1 lần nữa thì sắp xếp ngược lại ");
		sleep(2);
		clickOn(TiepNhanBenhNhan_TenDichVu);
		sleep(2);
		String laytext = layTextTuAnh(56, 534, 41, 17);
		if (laytext.trim().equals("1")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Gridview được sắp xếp theo thứ tự giảm dần");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Gridview không được sắp xếp theo thứ tự giảm dần");
		}
	}

	// @AfterTest
	public void ketThucLuong() {
		dangXuatKhoiTaikhoan();
		thoatUngdungHIS();
	}

}
