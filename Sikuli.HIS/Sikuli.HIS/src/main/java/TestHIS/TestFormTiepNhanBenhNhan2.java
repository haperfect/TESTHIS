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

public class TestFormTiepNhanBenhNhan2 extends TiepNhanBenhNhan {

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

	@Test(priority = 111)
	public void tiepNhanBenhNhan_10163_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường chẩn đoán NGT ");
		TestLogger.info("1.Kiểm tra giá trị mặc định trường chẩn đoán NGT");
		TestLogger.info("Expect :Giá trị mặc định là NULL");
		if (getChanDoanNGT().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
		}
	}

	@Test(priority = 112)
	public void tiepNhanBenhNhan_10163_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường chẩn đoán NGT ");
		TestLogger.info("1.Kiểm tra Nhập kí tự đặc biệt vào trường chẩn đoán NGT");
		TestLogger.info("Expect :Cho phép nhập kí tự đặc biệt");
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

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");

		// Điền mã thẻ BHYT
		TestLogger.info("Điền số thẻ BHYT 80");
		sotheBHYT = "SV491" + taoRandomSo(10);
		dienSoTheBHYT(sotheBHYT);
		s.type(Key.ENTER);
		TestLogger.info("Dien ma dang ki KCBBD");
		dienMaDKKCB("01005");
		s.type(Key.ENTER);

		// 2.CHỌN hình thức khám
		chonHinhThuc("Cơ quan y tế giới thiệu");

		// Điền kí tự đặc biệt trường chẩn đoán NGT
		TestLogger.info("Điền chẩn đoán NGT");
		dienChanDoanNGT("@@@@@");
		sleep(2);
		if (getChanDoanNGT().equals("@@@@@")) {
			setTestcaseStatus("PASS", "Cho phép nhập kí tự đặc biệt");
		} else {
			setTestcaseStatus("FAIL", "Không Cho phép nhập kí tự đặc biệt");
		}
	}

	@Test(priority = 113)
	public void tiepNhanBenhNhan_10163_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường chẩn đoán NGT ");
		TestLogger.info("1.Kiểm tra Nhập quá maxlenght 100 kí tự vào trường chẩn đoán NGT");
		TestLogger.info("Expect :Cắt từ kí tự 101");
		TestLogger.info("Điền Họ tên :");

		// Điền trường chẩn đoán NGT vượt quá 100 kí tự
		TestLogger.info("Điền chẩn đoán NGT");
		chanDoanNGT = TienIch.taoRandomChu(103);
		dienChanDoanNGT(chanDoanNGT);

		if (getChanDoanNGT().length() == 100) {
			TestLogger.info("==> " + getChanDoanNGT().length());
			setTestcaseStatus("PASS", "Confirm từ ký tự 101 trở đi sẽ không nhận giá trị");
		} else {
			TestLogger.info("==> " + getChanDoanNGT().length());
			setTestcaseStatus("FAIL", "Confirm từ ký tự 101 trở đi sẽ không nhận giá trị");
		}
	}

	@Test(priority = 114)
	public void tiepNhanBenhNhan_10163_4() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường chẩn đoán NGT ");
		TestLogger.info("1.Kiểm tra Nhập Không quá maxlenght 100 kí tự vào trường chẩn đoán NGT");
		TestLogger.info("Expect :Cho phép thêm mới thành công");

		// Điền trường chẩn đoán NGT không vượt quá 100 kí tự
		TestLogger.info("Điền chẩn đoán NGT");
		chanDoanNGT = TienIch.taoRandomChu(100);
		dienChanDoanNGT(chanDoanNGT);

		if (getChanDoanNGT().length() == 100) {
			TestLogger.info("==> " + getChanDoanNGT().length());
			setTestcaseStatus("PASS", "Confirm cho phép thêm mới thành công");
		} else {
			TestLogger.info("==> " + getChanDoanNGT().length());
			setTestcaseStatus("FAIL", "Confirm thêm mới không thành công");
		}
	}

	@Test(priority = 115)
	public void tiepNhanBenhNhan_10163_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường chẩn đoán NGT ");
		TestLogger.info("1.Kiểm tra Nhập kí tự có dấu vào trường chẩn đoán NGT");
		TestLogger.info("Expect :hiển thị bình thường, cho phép thêm mới thành công");

		// Điền trường chẩn đoán NGT vượt quá 100 kí tự
		TestLogger.info("Điền chẩn đoán NGT");
		chanDoanNGT = "bệnh nhân bị bệnh quá nặng, tuyến dưới không đủ điều điện chữa trị";
		dienChanDoanNGT(chanDoanNGT);

		if (getChanDoanNGT().equals("bệnh nhân bị bệnh quá nặng, tuyến dưới không đủ điều điện chữa trị")) {

			setTestcaseStatus("PASS", "Confirm từ ký tự 101 trở đi sẽ không nhận giá trị");
		} else {

			setTestcaseStatus("FAIL", "Confirm từ ký tự 20 trở đi sẽ không nhận giá trị");
		}
	}

	@Test(priority = 116)
	public void tiepNhanBenhNhan_10163_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường chẩn đoán NGT ");
		TestLogger.info("1.Kiểm tra để trống trường chẩn đoán NGT");
		TestLogger.info("Expect :Cho phép để trống");
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

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");

		// Điền mã thẻ BHYT
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
		dienNgayChuyen(denngay);
		s.type(Key.TAB);

		TestLogger.info("Nhap tuyen chuyen");
		dienTuyenChuyen("Tuyến dưới liền kề");
		s.type(Key.TAB);

		TestLogger.info("Nhap ly do chuyen");
		dienLiDoChuyen("Đủ điều kiện chuyển tuyến (đúng tuyến)");
		s.type(Key.TAB);

		// Để trống trường chẩn đoán NGT
		TestLogger.info("Nhap chan doan NGT");
		dienChanDoanNGT("");
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
		sleep(2);

		if (waitForObjectPresent(Phieukham, 15)) {
			s.type(Key.F4, Key.ALT);
		}
		if (getHoten().equals("")) {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Cho phép để trống trường NGT!");
		} else {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Không cho phép để trống trường NGT!");
		}
	}

	@Test(priority = 117)
	public void tiepNhanBenhNhan_9952_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate combox quận/huyện ");
		TestLogger.info("1.Kiểm tra giá trị mặc định combox quận/huyện");
		TestLogger.info("Expect :Giá trị mặc định là để trống");

		if (getQuanHuyen().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là để trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không để trống");
		}
	}

	@Test(priority = 118)
	public void tiepNhanBenhNhan_9952_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate combox quận/huyện ");
		TestLogger.info("1.Kiểm tra để trống combox quận/huyện");
		TestLogger.info("Expect :Không cho phép để trống");

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
		sleep(2);

		TestLogger.info("Điền tỉnh /thành phố");
		dienThanhPho("Hà Nội");
		s.type(Key.TAB);

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("");

		clickOn(TiepNhanBenhNhan_Luu);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Trường quận huyện không được để trống");
		} else {
			setTestcaseStatus("FAIL", "Trường quận huyện không được để trống");
		}
	}

	@Test(priority = 119)
	public void tiepNhanBenhNhan_9952_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate combox quận/huyện ");
		TestLogger.info("1.Kiểm tra nhập vào  một trường trong combox quận/huyện");
		TestLogger.info("Expect :Cho phép nhập, hiển thị quận/huyện tương ứng");

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("Thanh Xuân");
		s.type(Key.DOWN);
		s.type(Key.ENTER);
		sleep(2);
		if (getQuanHuyen().equalsIgnoreCase("Quận Thanh Xuân")) {
			setTestcaseStatus("PASS", "Thêm mới thành công");
		} else {
			setTestcaseStatus("FAIL", "Thêm mới không thành công");
		}
	}

	@Test(priority = 120)
	public void tiepNhanBenhNhan_9952_8() {
		TestLogger.info("[Hành chính] Kiểm tra validate combox quận/huyện ");
		TestLogger.info("1.Kiểm tra nhập từ viết tắt trong combox quận/huyện");
		TestLogger.info("Expect :Hiển thị kết quả tương ứng   TX -----> Quận Thanh Xuân");

		TestLogger.info("Điền tỉnh /thành phố");
		dienThanhPho("Hà Nội");
		s.type(Key.TAB);

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("TX");
		s.type(Key.DOWN);
		s.type(Key.ENTER);

		if (getQuanHuyen().equalsIgnoreCase("Quận Thanh Xuân")) {
			setTestcaseStatus("PASS", "Thêm quận/huyện thành công");
		} else {
			setTestcaseStatus("FAIL", "Thêm quận huyện không thành công");
		}
	}

	@Test(priority = 121)
	public void tiepNhanBenhNhan_10264_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tên dịch vụ ");
		TestLogger.info("1.Kiểm tra giá trị mặc định trường tên dịch vụ");
		TestLogger.info("Expect :Giá trị mặc định là để trống");

		if (getTenDichVu().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là để trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không để trống");
		}
	}

	@Test(priority = 122)
	public void tiepNhanBenhNhan_10264_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tên dịch vụ ");
		TestLogger.info("1.Kiểm tra chọn 1 dịch vụ trong trường tên dịch vụ");
		TestLogger.info("Expect :Cho phép chọn ");
		dienTenDichVu("(Hằng test) Khám bệnh theo yêu cầu");
		if (getTenDichVu().equalsIgnoreCase("(Hằng test) Khám bệnh theo yêu cầu")) {
			setTestcaseStatus("PASS", "Chọn dịch vụ thành công");
		} else {
			setTestcaseStatus("FAIL", "Chọn dịch vụ không thành công");
		}
	}

	@Test(priority = 123)
	public void tiepNhanBenhNhan_10264_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tên dịch vụ ");
		TestLogger.info("1.Kiểm tra bỏ trống  trường tên dịch vụ");
		TestLogger.info("Expect :Cho phép để trống ");
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
		sleep(2);

		TestLogger.info("Điền tỉnh /thành phố");
		dienThanhPho("Hà Nội");
		s.type(Key.TAB);

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("Quận Thanh Xuân");

		TestLogger.info("Điền phường/xã");
		dienPhuongXa("Phường Nhân Chính");

		TestLogger.info("Điền tên dịch vụ");
		dienTenDichVu("");
		s.type(Key.ENTER);
		sleep(3);

		// clickOn(TiepNhanBenhNhan_Luu);
		if (getHoten().length() == 0) {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Tiếp nhận bệnh nhân thành công, không reset màn hình");
		} else {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Tiếp nhận bệnh nhân thành công, reset màn hình");
		}
	}

	@Test(priority =124)
	public void tiepNhanBenhNhan_10270_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi thực hiện ");
		TestLogger.info("1.Kiểm tra giá trị mặc định trường Nơi thực hiện");
		TestLogger.info("Expect :Giá trị mặc định là để trống");
		sleep(2);
		if (getNoiThucHien().equals("")) {
			setTestcaseStatus("PASS", "Giá trị mặc định là để trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không để trống");
		}
	}

	@Test(priority = 125)
	public void tiepNhanBenhNhan_10270_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi thực hiện");
		TestLogger.info("1.Kiểm tra hiển thị trường nơi thực hiện ----dịch vụ chỉ có 1 nơi thực hiện----");
		TestLogger.info("Expect :Hiển thị nơi thực hiện đúng với dịch vụ đã cấu hình ----Khám tiêu hóa");

		dienTenDichVu("Khám tiêu hóa");
		sleep(2);
		if (getNoiThucHien().equalsIgnoreCase("Khám tiêu hóa")) {
			setTestcaseStatus("PASS", "lấy ra nơi thực hiện thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra nơi thực hiện không thành công");
		}
	}

	@Test(priority = 126)
	public void tiepNhanBenhNhan_10270_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi thực hiện");
		TestLogger.info("1.Kiểm tra hiển thị trường nơi thực hiện ----dịch vụ chỉ có 2 nơi thực hiện----");
		TestLogger.info("Expect :Hiển thị nơi thực hiện đúng với dịch vụ đã cấu hình ----Phòng khám phụ khoa");

		dienTenDichVu("(Hằng test) Khám bệnh theo yêu cầu");
		sleep(2);
		if (getNoiThucHien().equalsIgnoreCase("Phòng khám phụ khoa")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "lấy ra nơi thực hiện thành công");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Lấy ra nơi thực hiện không thành công");
		}
	}

	@Test(priority = 127)
	public void tiepNhanBenhNhan_9936_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nghề nghiệp");
		TestLogger.info("1.Kiểm tra giá trị mặc định trường nghề nghiệp");
		TestLogger.info("Expect :Giá trị mặc định là -----Khác-----");
		sleep(6);
		if (getNgheNghiep().equalsIgnoreCase("Khác")) {
			setTestcaseStatus("PASS", "Giá trị mặc định là ----Khác----");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định Không phải  là ----Khác----");
		}
	}

	@Test(priority = 128)
	public void tiepNhanBenhNhan_9936_7() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nghề nghiệp");
		TestLogger.info("1.Kiểm tra nhập giá trị trường nghề nghiệp");
		TestLogger.info("Expect :Cho phép nhập trường nghề nghiệp");

		dienNgheNghiep("Học sinh");
		if (getNgheNghiep().equalsIgnoreCase("Học sinh")) {
			setTestcaseStatus("PASS", "Điền nghề nghiệp thành công");
		} else {
			setTestcaseStatus("FAIL", "Điền nghề nghiệp không thành công");
		}
	}

	@Test(priority = 129)
	public void tiepNhanBenhNhan_9936_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nghề nghiệp");
		TestLogger.info("1.Kiểm tra chọn một giá trị trường nghề nghiệp");
		TestLogger.info("Expect :Cho phép chọn một nghề nghiệp trong combox trường nghề nghiệp");
		sleep(2);
		// click tọa độ load ds trường nghề nghiệp
		clickToaDo(242, 222);
		sleep(2);
		// click tọa độ chọn một nghề nghiệp ---Sinh Viên----
		clickToaDo(129, 306);
		if (getNgheNghiep().equalsIgnoreCase("Sinh Viên")) {
			setTestcaseStatus("PASS", "Chọn nghề nghiệp trong combox thành công");
		} else {
			setTestcaseStatus("FAIL", "Chọn nghề nghiệp trong combox không thành công");
		}
	}

	@Test(priority = 130)
	public void tiepNhanBenhNhan_9936_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nghề nghiệp");
		TestLogger.info("1.Kiểm tra bỏ trống trường nghề nghiệp");
		TestLogger.info("Expect :Cho phép lưu");
		sleep(2);
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
		dienNgheNghiep("");
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc");
		DanToc = "Kinh";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		sleep(2);

		TestLogger.info("Điền tỉnh /thành phố");
		dienThanhPho("Hà Nội");
		s.type(Key.TAB);
		

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("Quận Thanh Xuân");

		TestLogger.info("Điền phường/xã");
		dienPhuongXa("Phường Nhân Chính");

		clickOn(TiepNhanBenhNhan_Luu);
		sleep(3);
		if (getHoten().length() == 0) {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Tiếp nhận bệnh nhân thành công, không reset màn hình");
		} else {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Tiếp nhận bệnh nhân thành công, reset màn hình");
		}
	}

	@Test(priority = 131)
	public void tiepNhanBenhNhan_10086_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Lý do");
		TestLogger.info("1.Kiểm tra giá trị mặc định");
		TestLogger.info("Expect :Giá trị mặc định là Khám bệnh");
		sleep(2);
		if (getLiDo().equalsIgnoreCase("Khám bệnh")) {
			setTestcaseStatus("PASS", "Giá trị mặc định là khám bệnh");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không phải là khám bệnh");
		}
	}

	@Test(priority = 132)
	public void tiepNhanBenhNhan_10086_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Lý do");
		TestLogger.info("1.Kiểm tra bỏ trống trường lý do");
		TestLogger.info("Expect :Không cho phép bỏ trống trường lý do");
		sleep(2);
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
		dienNgheNghiep("");
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc");
		DanToc = "Kinh";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		sleep(2);

		TestLogger.info("Điền tỉnh /thành phố");
		dienThanhPho("Hà Nội");
		s.type(Key.TAB);

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("Quận Thanh Xuân");

		TestLogger.info("Điền phường/xã");
		dienPhuongXa("Phường Nhân Chính");
		s.type(Key.ENTER);

		TestLogger.info("Điền lý do");
		dienLiDo("");
		clickToaDo(624, 277);

		sleep(2);
		clickOn(TiepNhanBenhNhan_Luu);

		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Không cho phép để trống");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Không cho phép để trống");
		}
	}

	@Test(priority = 133)
	public void tiepNhanBenhNhan_10086_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Lý do");
		TestLogger.info("1.Kiểm tra chọn 1 giá trị trong combox trường lý do");
		TestLogger.info("Expect :Cho phép chọn 1 giá trị trường lý do");

		// Click tọa độ lý do
		clickToaDo(793, 275);
		sleep(2);
		// click chọn lý do ---- TN 011-----
		clickToaDo(702, 359);
		clickOn(TiepNhanBenhNhan_Luu);

		if (getLiDo().equalsIgnoreCase("TN 011")) {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Chọn lý do thành công");
		} else {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Chọn lý do không thành công");
		}
	}

	@Test(priority = 134)
	public void tiepNhanBenhNhan_10086_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Lý do");
		TestLogger.info("1.Kiểm tra nhập một giá trị trong trường lý do");
		TestLogger.info("Expect :Cho phép nhập trường lý do");
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
		dienNgheNghiep("");
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc");
		DanToc = "Kinh";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		sleep(2);

		TestLogger.info("Điền tỉnh /thành phố");
		dienThanhPho("Hà Nội");
		s.type(Key.TAB);

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("Quận Thanh Xuân");

		TestLogger.info("Điền phường/xã");
		dienPhuongXa("Phường Nhân Chính");
		s.type(Key.ENTER);

		dienLiDo("TN 011");

		clickOn(TiepNhanBenhNhan_Luu);
		sleep(3);
		if (getLiDo().equalsIgnoreCase("TN 011")) {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Chọn lý do thành công");
		} else {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Chọn lý do không thành công");
		}
	}

	@Test(priority = 135)
	public void tiepNhanBenhNhan_9915_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
		TestLogger.info("1.Kiểm tra giá trị mặc định trường năm sinh");
		TestLogger.info("Expect :Giá trị mặc định là 0 tuổi");

		if (getNamSinh().equals("0")) {
			setTestcaseStatus("PASS", "Giá trị mặc định là 0 tuổi");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định là  không phải 0 tuổi trống");
		}
	}

	@Test(priority = 136)
	public void tiepNhanBenhNhan_9915_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
		TestLogger.info("1.Kiểm tra để trống trường năm sinh");
		TestLogger.info("Expect :Không cho phép để trống trường năm sinh");

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
		// điền năm sinh
		dienNamSinh("");

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
		dienNgheNghiep("");
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc");
		DanToc = "Kinh";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		sleep(2);

		TestLogger.info("Điền tỉnh /thành phố");
		dienThanhPho("Hà Nội");
		s.type(Key.TAB);

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("Quận Thanh Xuân");

		TestLogger.info("Điền phường/xã");
		dienPhuongXa("Phường Nhân Chính");
		s.type(Key.ENTER);

		TestLogger.info("Điền lý do");
		dienLiDo("TD 011");
		sleep(2);
		clickOn(TiepNhanBenhNhan_Luu);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Không cho phép để trống");
		} else {
			setTestcaseStatus("FAIL", "Không cho phép để trống");
		}
	}

	@Test(priority = 137)
	public void tiepNhanBenhNhan_9915_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
		TestLogger.info("1.Kiểm tra nhập trường năm sinh < 1887");
		TestLogger.info("Expect :tự động chuyển về năm sinh nhỏ nhất được phép là 1887");
		sleep(2);
		// điền năm sinh <1887
		dienNamSinh("1886");
		s.type(Key.TAB);
		if (getNamSinh().equals("1887")) {
			setTestcaseStatus("PASS", "Năm sinh  là 1887");
		} else {
			setTestcaseStatus("FAIL", "Năm Sinh khác 1887");
		}
	}

	@Test(priority = 138)
	public void tiepNhanBenhNhan_9915_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
		TestLogger.info("1.Kiểm tra nhập trường năm sinh > 2018");
		TestLogger.info("Expect :tự động chuyển về năm sinh là năm hiện tại 2018");
		sleep(2);
		// điền năm sinh lớn hơn năm hiện tại
		dienNamSinh("2020");
		s.type(Key.TAB);
		if (getNamSinh().equals("2018")) {
			setTestcaseStatus("PASS", "Năm sinh là 2018");
		} else {
			setTestcaseStatus("FAIL", "Năm Sinh khác 2018");
		}
	}

	@Test(priority = 139)
	public void tiepNhanBenhNhan_9915_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
		TestLogger.info("1.Kiểm tra load năm sinh từ trường ngày sinh ");
		TestLogger.info("Expect :load đúng năm sinh từ trường ngày sinh");
		sleep(2);

		dienNgaySinh("01012018");
		s.type(Key.TAB);
		if (getNamSinh().equals("2018")) {
			setTestcaseStatus("PASS", "Lấy năm sinh thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy năm sinh không thành công");
		}
	}

	@Test(priority = 140)
	public void tiepNhanBenhNhan_9915_7() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
		TestLogger.info("1.Kiểm tra load năm sinh khi nhập tuổi hợp lệ vào trường năm sinh ");
		TestLogger.info("Expect :load đúng năm sinh sau khi nhập tuổi");
		sleep(2);

		dienNamSinh("24");
		s.type(Key.TAB);
		if (getNamSinh().equals("1994")) {
			setTestcaseStatus("PASS", "Hiển thị năm sinh đúng");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị năm sinh sai");
		}
	}

	@Test(priority = 141)
	public void tiepNhanBenhNhan_9915_8() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
		TestLogger.info("1.Kiểm tra load năm sinh khi nhập tuổi không hợp lệ vào trường năm sinh ");
		TestLogger.info("Expect :load đúng năm sinh sau khi nhập tuổi");
		sleep(2);
		dienNamSinh("200");
		s.type(Key.TAB);
		if (getNamSinh().equals("1887")) {
			setTestcaseStatus("PASS", "Hiển thị năm sinh đúng");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị năm sinh sai");
		}
	}

	@Test(priority = 142)
	public void tiepNhanBenhNhan_9915_9() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
		TestLogger.info("1.Kiểm tra nhập kí tự đặc biệt vào trường năm sinh ");
		TestLogger.info("Expect :load đúng năm sinh sau khi nhập tuổi");
		sleep(2);
		dienNamSinh("----");
		s.type(Key.TAB);
		if (getNamSinh().equals("----")) {
			setTestcaseStatus("FAIL", "Không cho phép điền kí tự đặc biệt");
		} else {
			setTestcaseStatus("PASS", "Không cho phép nhập kí tự đặc biệt");
		}
	}

	@Test(priority = 143)
	public void tiepNhanBenhNhan_9955_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường phường xã");
		TestLogger.info("1.Kiểm tra giá trị mặc định  ");
		TestLogger.info("Expect :Giá trị mặc định là null");
		if (getPhuongXa().equals("")) {
			setTestcaseStatus("PASS", "Giá trị mặc định là NULL");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không là NULL");
		}
	}

	@Test(priority = 144)
	public void tiepNhanBenhNhan_9955_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường phường xã");
		TestLogger.info("1.Kiểm tra bỏ trống trường phường xã ");
		TestLogger.info("Expect :Cho phép bỏ trống trường phường xã ");
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
		dienNgheNghiep("");
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc");
		DanToc = "Kinh";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		sleep(2);

		TestLogger.info("Điền tỉnh /thành phố");
		dienThanhPho("Hà Nội");
		s.type(Key.TAB);

		TestLogger.info("Điền quận huyện");
		dienQuanHuyen("Quận Thanh Xuân");

		TestLogger.info("Điền phường/xã");
		dienPhuongXa("");
		sleep(2);
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
			s.type(Key.F4, Key.CTRL);
		}
		if (getHoten().equals("")) {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Trường phường xã được phép để trống");
		} else {
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Trường phường xã không được phép để trống");
		}
	}

	@Test(priority = 145)
	public void tiepNhanBenhNhan_9955_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường phường xã");
		TestLogger.info("1.Kiểm tra chọn 1 giá trị trong combobox trường phường xã ");
		TestLogger.info("Expect :Cho phép bỏ trống trường phường xã ");
		dienQuanHuyen("Quận Ba Đình");
		s.type(Key.ENTER);

		// click tọa độ phường xã
		clickToaDo(792, 161);
		// click tọa độ chọn phường xã ---Phường Phúc Xá---
		clickToaDo(718, 210);

		if (getPhuongXa().equalsIgnoreCase("Phường Phúc Xá")) {
			setTestcaseStatus("PASS", "Chọn được phường xã trong combox phường xã");
		} else {
			setTestcaseStatus("FAIL", "Không chọn được phường xã trong combx phường xã");
		}
	}

	@Test(priority = 146)
	public void tiepNhanBenhNhan_9955_8_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường phường xã");
		TestLogger.info("1.Kiểm tra nhập một giá trị trường phường xã ");
		TestLogger.info("Expect :Cho phép nhập");
		dienQuanHuyen("Quận Ba Đình");
		s.type(Key.ENTER);
		dienPhuongXa("Phường Phúc Xá");
		s.type(Key.ENTER);
		if (getPhuongXa().equalsIgnoreCase("Phường Phúc Xá")) {
			setTestcaseStatus("PASS", "Nhập chọn phường xã thành công");
		} else {
			setTestcaseStatus("FAIL", "Nhập phường xã không thành công");
		}

	}

	@Test(priority = 147)
	public void tiepNhanBenhNhan_9955_8_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường phường xã");
		TestLogger.info("1.Kiểm tra nhập một giá trị viết tắt vào trường phường xã ----PX-Phúc Xá----");
		TestLogger.info("Expect :Cho phép nhập vào  trường phường xã ");
		dienQuanHuyen("Quận Ba Đình");
		s.type(Key.ENTER);
		dienPhuongXa("PX");
		s.type(Key.ENTER);
		if (getPhuongXa().equalsIgnoreCase("Phường Phúc Xá")) {
			setTestcaseStatus("PASS", "Chọn phường xã theo tên viết tắt thành công");
		} else {
			setTestcaseStatus("FAIL", "Chọn phường xã theo tên viết tắt không thành công");
		}
	}

	@Test(priority = 148)
	public void tiepNhanBenhNhan_10164_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi giới thiệu");
		TestLogger.info("1.Kiểm tra mặc định trường Nơi giới thiệu khi chọn Đối tượng BHYT + Hình thức là Tự đến");
		TestLogger.info("Expect :Disable trường Nơi giới thiệu và không cho phép nhập");
		// điền số thẻ BHYT
		sotheBHYT = "SV401" + TienIch.taoRandomSo(10);
		dienSoTheBHYT(sotheBHYT);
		// điền mã ĐKKCB
		dienMaDKKCB("01043");
		sleep(2);
		dienNoiGioiThieu("Bệnh viện Thanh Nhàn11111");
		if (getNoiGioiThieu().equalsIgnoreCase("Bệnh viện Thanh Nhàn11111")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", " Không Disable trường Nơi giới thiệu và cho phép nhập");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Disable trường Nơi giới thiệu và không cho phép nhập");
		}
	}

	@Test(priority = 149)
	public void tiepNhanBenhNhan_10164_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi giới thiệu");
		TestLogger.info(
				"1.Kiểm tra mặc định trường Nơi giới thiệu khi chọn đối tượng là BHYT + Hình thức đến khám là Cơ quan Y tế giới thiệu");
		TestLogger.info("Expect :Enable trường Nơi giới thiệu, và mặc định giá trị trong combobox là Blank");
		// điền số thẻ BHYT
		sotheBHYT = "SV401" + TienIch.taoRandomSo(10);
		dienSoTheBHYT(sotheBHYT);
		// điền mã ĐKKCB
		dienMaDKKCB("01005");
		sleep(2);
		if (getNoiGioiThieu().equalsIgnoreCase("")) {
			setTestcaseStatus("PASS", "Enable trường Nơi giới thiệu, và mặc định giá trị trong combobox là Blank");
		} else {
			setTestcaseStatus("FAIL", "Disable trường Nơi giới thiệu và không cho phép nhập");
		}
	}

	@Test(priority = 150)
	public void tiepNhanBenhNhan_10164_4() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi giới thiệu");
		TestLogger.info("1.Kiểm tra bỏ trống trường Nơi giới thiệu");
		TestLogger.info("Expect :Hiển thị popup thông báo Không được bỏ trống nơi giới thiệu ");
		Hoten = "TRAN VAN LUC" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn Giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");

		// Điền mã thẻ BHYT
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
		dienNoiGioiThieu("");
		clickToaDo(642, 328);
		s.type(Key.TAB);

		TestLogger.info("Nhap so chuyen tuyen");
		dienSoTuyenChuyen("012");
		s.type(Key.TAB);
	

		TestLogger.info("Nhap ngay chuyen");
		dienNgayChuyen(denngay);
		s.type(Key.TAB);
		

		TestLogger.info("Nhap tuyen chuyen");
		dienTuyenChuyen("Tuyến dưới liền kề");
		s.type(Key.TAB);
	

		TestLogger.info("Nhap ly do chuyen");
		dienLiDoChuyen("Đủ điều kiện chuyển tuyến (đúng tuyến)");
		s.type(Key.TAB);
	

		// Để trống trường chẩn đoán NGT
		TestLogger.info("Nhap chan doan NGT");
		dienChanDoanNGT("");
		s.type(Key.TAB);
		clickOn(TiepNhanBenhNhan_Luu);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Trường nơi giới thiệu không được để trống");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Trường nơi giới thiệu không được để trống");
		}

	}

	@Test(priority = 151)
	public void tiepNhanBenhNhan_10164_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi giới thiệu");
		TestLogger.info("1.Kiểm tra chọn một giá trị trong combox trường Nơi giới thiệu");
		TestLogger.info("Expect :Chọn 1 giá trị trong combobox thành công");

		// click tọa độ trường nơi giới thiệu
		clickToaDo(1011, 328);
		// click tọa độ chọn một trường trong nơi giới thiệu---Bệnh viện Thanh
		// Nhàn11111---
		clickToaDo(829, 376);
		if (getNoiGioiThieu().equalsIgnoreCase("Bệnh viện Thanh Nhàn11111")) {
			setTestcaseStatus("PASS", "Chọn nơi giới thiệu thành công");
		} else {
			setTestcaseStatus("FAIL", "Chọn nơi giới thiệu không thành công");
		}
	}

	@Test(priority = 152)
	public void tiepNhanBenhNhan_10164_8() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi giới thiệu");
		TestLogger.info("1.Kiểm tra nhập một giá trị trong trường Nơi giới thiệu");
		TestLogger.info("Expect :Chọn 1 giá trị trong combobox thành công");
		
		dienNoiGioiThieu("Bệnh viện Thanh Nhàn11111");
		s.type(Key.ENTER);
		
		if (getNoiGioiThieu().equalsIgnoreCase("Bệnh viện Thanh Nhàn11111")) {
			setTestcaseStatus("PASS", "Chọn nơi giới thiệu thành công");
		} else {
			setTestcaseStatus("FAIL", "Chọn nơi giới thiệu không thành công");
		}
	}

	@Test(priority = 153)
	public void tiepNhanBenhNhan_10164_10() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi giới thiệu");
		TestLogger.info("1.Kiểm tra các cột hiển thị bên trong combobox");
		TestLogger.info("Expect :Load lên mã BHYT, tên cơ sở khám chữa bệnh");
		// click tọa độ nơi giới thiệu
		clickToaDo(1011, 328);
		if (waitForObjectPresent(TiepNhanBenhNhan_KiemTraCotNoiGioiThieu, 5)) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Load lên mã BHYT, tên cơ sở khám chữa bệnh");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "không Load lên mã BHYT, tên cơ sở khám chữa bệnh");
		}

	}

	@Test(priority = 154)
	public void tiepNhanBenhNhan_10167_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tuyến chuyển");
		TestLogger.info(
				"1.Kiểm tra mặc định trường Tuyến chuyển khi chọn đối tượng khác đối tượng BHYT (Đối tượng Dịch vụ, Đối tượng Yêu cầu...)");
		TestLogger.info("Expect :Disable trường Tuyến chuyển và không cho phép chọn");
		sleep(2);

		TestLogger.info("Điền đối tượng");
		dienDoiTuong("Dịch vụ");

		s.type(Key.TAB);
		sleep(5);
		s.type(Key.TAB);

		TestLogger.info("Điền hình thức khám");
		chonHinhThuc("Tự đến");
		
		TestLogger.info("Chon lý do khám");
		LiDo = "Khám bệnh";
		dienLiDo(LiDo);
		clickToaDo(632, 275);
		
		if (getTuyenChuyen().equalsIgnoreCase("Tuyến dưới liền kề")) {
			setTestcaseStatus("FAIL", "Không Disable trường Tuyến chuyển và không cho phép chọn");
		} else {
			setTestcaseStatus("PASS", "Disable trường Tuyến chuyển và không cho phép chọn");
		}
	}

	@Test(priority = 155)
	public void tiepNhanBenhNhan_10167_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Tuyến chuyển");
		TestLogger.info(
				"1.Kiểm tra mặc định trường Tuyến chuyển khi chọn đối tượng là BHYT + Hình thức đến khám là Tự đến");
		TestLogger.info("Expect :Disable trường Tuyến chuyển và không cho phép chọn");
		sleep(2);
		TestLogger.info("Điền đối tượng");
		dienDoiTuong("BHYT 100%");

		TestLogger.info("Điền hình thức khám");
		chonHinhThuc("Tự đến");
		TestLogger.info("Chon lý do khám");
		LiDo = "Khám bệnh";
		dienLiDo(LiDo);
		s.type(Key.ENTER);

		dienTuyenChuyen("Tuyến dưới liền kề");
		if (getSoTheBHYT().equals("TU")) {
			setTestcaseStatus("PASS", " Disable trường Tuyến chuyển và không cho phép chọn");
		} else {
			setTestcaseStatus("FAIL", "Không Disable trường Tuyến chuyển và không cho phép chọn");
		}
	}
	 
	// @AfterTest
	public void ketThucLuong() {
		dangXuatKhoiTaikhoan();
		thoatUngdungHIS();
	}

}
