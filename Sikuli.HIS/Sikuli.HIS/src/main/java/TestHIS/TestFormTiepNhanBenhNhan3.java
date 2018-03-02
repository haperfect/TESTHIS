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

public class TestFormTiepNhanBenhNhan3 extends TiepNhanBenhNhan {

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

	@Test(priority = 156)
	public void tiepNhanBenhNhan_10167_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tuyến chuyển");
		TestLogger.info(
				"1.Kiểm tra mặc định trường Tuyến chuyển khi chọn đối tượng là BHYT + Hình thức đến khám là Cơ quan Y tế giới thiệu");
		TestLogger.info("Expect :Enable trường Tuyến chuyển, và mặc định giá trị trong combobox là Blank");
		TestLogger.info("Điền đối tượng");
		dienDoiTuong("BHYT 100%");

		TestLogger.info("Điền hình thức khám");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		TestLogger.info("Chon lý do khám");
		LiDo = "Khám bệnh";
		dienLiDo(LiDo);
		s.type(Key.ENTER);
		if (getTuyenChuyen().equals("")) {
			setTestcaseStatus("PASS", "Enable trường Tuyến chuyển, và mặc định giá trị trong combobox là Blank");
		} else {
			setTestcaseStatus("FAIL", " Không Enable trường Tuyến chuyển, và mặc định giá trị trong combobox là Blank");
		}
	}

	@Test(priority = 157)
	public void tiepNhanBenhNhan_10167_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tuyến chuyển");
		TestLogger.info("1.Kiểm tra bỏ trống combobox Tuyến chuyển khi trường Tuyến chuyển Enable");
		TestLogger.info("Expect :Hiển thị thông tin:”Bạn phải nhập tuyến chuyển!” và focus chuột vào ô cần nhập");
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
		TestLogger.info("Điền đối tượng");
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
		dienNgayChuyen("16112017");
		s.type(Key.TAB);

		TestLogger.info("Nhap tuyen chuyen");
		dienTuyenChuyen("");
		s.type(Key.TAB);

		TestLogger.info("Nhap ly do chuyen");
		dienLiDoChuyen("Đủ điều kiện chuyển tuyến (đúng tuyến)");
		s.type(Key.TAB);

		TestLogger.info("Nhap chan doan NGT");
		dienChanDoanNGT("Đau đầu");
		
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
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Trường nơi giới thiệu không được để trống");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Trường nơi giới thiệu không được để trống");
		}
	}

	@Test(priority = 158)
	public void tiepNhanBenhNhan_10167_7() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tuyến chuyển");
		TestLogger.info("1.Kiểm tra chọn một giá trị trong combox Tuyến chuyển");
		TestLogger.info("Expect :Chọn 1 giá trị trong combobox thành công");

		dienDoiTuong("BHYT 80%");

		TestLogger.info("Điền hình thức khám");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		TestLogger.info("Chon lý do khám");
		LiDo = "Khám bệnh";
		dienLiDo(LiDo);

		// click tọa độ trường nơi giới thiệu
		clickToaDo(1012, 387);
		// click tọa độ chọn một trường trong nơi giới thiệu---Tuyến dưới liền kề---
		clickToaDo(713, 436);
		if (getTuyenChuyen().equalsIgnoreCase("Tuyến dưới liền kề")) {
			setTestcaseStatus("PASS", "Chọn nơi giới thiệu thành công");
		} else {
			setTestcaseStatus("FAIL", "Chọn nơi giới thiệu không thành công");
		}
	}

	@Test(priority = 159)
	public void tiepNhanBenhNhan_10167_9() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tuyến chuyển");
		TestLogger.info("1.Kiểm tra nhập một giá trị vào combox Tuyến chuyển");
		TestLogger.info("Expect :cho phép nhập giá trị trong combobox thành công");

		dienTuyenChuyen("Tuyến dưới liền kề");
		s.type(Key.ENTER);
		if (getTuyenChuyen().equalsIgnoreCase("Tuyến dưới liền kề")) {
			setTestcaseStatus("PASS", "Chọn nơi giới thiệu thành công");
		} else {
			setTestcaseStatus("FAIL", "Chọn nơi giới thiệu không thành công");
		}
	}

	@Test(priority = 160)
	public void tiepNhanBenhNhan_10200_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Ngày chuyển tuyến");
		TestLogger.info(
				"Kiểm tra mặc định trường Ngày chuyển tuyến khi chọn đối tượng khác đối tượng BHYT (Đối tượng Dịch vụ, Đối tượng Yêu cầu...)");
		TestLogger.info("Expect :Disable và không cho phép chọn");

		TestLogger.info("Điền đối tượng");
		dienDoiTuong("Dịch vụ");

		s.type(Key.TAB);
		sleep(5);
		s.type(Key.TAB);

		TestLogger.info("Điền hình thức khám");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		TestLogger.info("Chon lý do khám");
		LiDo = "Khám bệnh";
		dienLiDo(LiDo);
		s.type(Key.ENTER);
		sleep(2);
		dienNgayChuyen("1012018");
		if (getNgayChuyen().length() == 0) {
			setTestcaseStatus("PASS", "Disable và không cho phép chọn");
		} else {
			setTestcaseStatus("FAIL", "Không Disable và cho phép chọn");
		}
	}

	@Test(priority = 161)
	public void tiepNhanBenhNhan_10200_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Ngày chuyển tuyến");
		TestLogger.info(
				"Kiểm tra mặc định trường Ngày chuyển tuyến khi chọn đối tượng là BHYT + Hình thức đến khám là Tự đến");
		TestLogger.info("Expect :Disable và không cho phép chọn");

		dienDoiTuong("BHYT 100%");
		TestLogger.info("Điền hình thức khám");
		chonHinhThuc("Tự đến");
		TestLogger.info("Chon lý do khám");
		LiDo = "Khám bệnh";
		dienLiDo(LiDo);
		s.type(Key.ENTER);
		dienNgayChuyen("1012018");
		if (getNgayChuyen().length() == 0) {
			setTestcaseStatus("PASS", "Disable và không cho phép chọn");
		} else {
			setTestcaseStatus("FAIL", "Không Disable và cho phép chọn");
		}
	}

	@Test(priority = 162)
	public void tiepNhanBenhNhan_10200_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Ngày chuyển tuyến");
		TestLogger.info(
				"Kiểm tra mặc định trường Ngày chuyển tuyến khi chọn đối tượng là BHYT + Hình thức đến khám là Cơ quan Y tế giới thiệu");
		TestLogger.info("Expect :Enable trường Ngày chuyển tuyến, và mặc định giá trị là Blank ");
		dienDoiTuong("BHYT 100%");

		TestLogger.info("Điền hình thức khám");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		TestLogger.info("Chon lý do khám");
		LiDo = "Khám bệnh";
		dienLiDo(LiDo);
		s.type(Key.ENTER);
		if (getNgayChuyen().equalsIgnoreCase("")) {
			setTestcaseStatus("PASS", "Enable trường Ngày chuyển tuyến, và mặc định giá trị là Blank");
		} else {
			setTestcaseStatus("FAIL", "Không Enable trường Ngày chuyển tuyến, và mặc định giá trị không phải là Blank");
		}
	}

	@Test(priority = 163)
	public void tiepNhanBenhNhan_10200_4() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Ngày chuyển tuyến");
		TestLogger.info("Kiểm tra để trống trường Ngày chuyển tuyến ");
		TestLogger.info("Expect :Hiển thị cảnh báo ");

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
		TestLogger.info("Điền đối tượng");
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
		dienMaDKKCB("01048");
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
		dienNgayChuyen("");
		s.type(Key.TAB);

		TestLogger.info("Nhap tuyen chuyen");
		dienTuyenChuyen("Tuyến dưới liền kề");
		s.type(Key.TAB);

		TestLogger.info("Nhap ly do chuyen");
		dienLiDoChuyen("Đủ điều kiện chuyển tuyến (đúng tuyến)");
		s.type(Key.TAB);

		TestLogger.info("Nhap chan doan NGT");
		dienChanDoanNGT("Đau đầu");
		
		clickOn(TiepNhanBenhNhan_Luu);
		sleep(2);
		
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Ngày chuyển tuyến không được để trống");
		} else {
			setTestcaseStatus("FAIL", "Ngày chuyển tuyến được để trống");
		}
	}

	@Test(priority = 164)
	public void tiepNhanBenhNhan_10200_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Ngày chuyển tuyến");
		TestLogger.info("Kiểm tra chọn  Ngày chuyển tuyến  là ngày nhỏ hơn hoặc bằng ngày hiện tại");
		TestLogger.info("Expect :Cho phép Lưu");

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
		TestLogger.info("Điền đối tượng");
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
		dienNgayChuyen("01012018");
		s.type(Key.TAB);

		TestLogger.info("Nhap tuyen chuyen");
		dienTuyenChuyen("Tuyến dưới liền kề");
		s.type(Key.TAB);

		TestLogger.info("Nhap ly do chuyen");
		dienLiDoChuyen("Đủ điều kiện chuyển tuyến (đúng tuyến)");
		s.type(Key.TAB);

		TestLogger.info("Nhap chan doan NGT");
		dienChanDoanNGT("Đau đầu");
		s.type(Key.ENTER);
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
			setTestcaseStatus("FAIL", "Cho phép Lưu");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Cho phép Lưu");
		}
	}

	@Test(priority = 165)
	public void tiepNhanBenhNhan_10200_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Ngày chuyển tuyến");
		TestLogger.info("Kiểm tra chọn  Ngày chuyển tuyến  là ngày lớn hơn ngày hiện tại");
		TestLogger.info("Expect :Cho phép Lưu");
		String ngayHienTai;
		ngayHienTai = TienIch.getNgayThangNamHienTaicuaMayTinh();

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
		TestLogger.info("Điền đối tượng");
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
		String ngaychuyen = TienIch.getNgayHienTaicuaMayTinh() + TienIch.getThangHienTaicuaMayTinh()
		+ (Integer.parseInt(TienIch.getNamHienTaicuaMayTinh()) + 1);
		dienNgayChuyen(ngaychuyen);
		s.type(Key.TAB);

		if (getNgayChuyen().equalsIgnoreCase(ngayHienTai)) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);

			setTestcaseStatus("PASS", "lấy ngày hiện tại chính xác");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);

			setTestcaseStatus("FAIL", "Lấy ngày hiện tại không chính xác");
		}
	}

	

	// @AfterTest
	public void ketThucLuong() {
		dangXuatKhoiTaikhoan();
		thoatUngdungHIS();
	}

}
