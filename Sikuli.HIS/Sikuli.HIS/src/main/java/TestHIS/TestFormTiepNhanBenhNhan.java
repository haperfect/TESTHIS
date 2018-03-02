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

public class TestFormTiepNhanBenhNhan extends TiepNhanBenhNhan {

	public String Hoten, NgaySinh, SoCMND, GioiTinh, SoDienThoai, NgheNghiep, DanToc, NhapNhanh, SoNha, NoiLamViec;
	public String NguoiLienHe, SoDienThoaiNguoiLienHe, DoiTuong, UuTien, HinhThuc, LiDo, TenDichVu, ThuTienSau,
			NoiThucHien;
	public String soTiepNhan;
	public String sotheBHYT;
	public String MaDKKCB;
	public String chanDoanNGT;

	HisActions his = new HisActions();
	DanhSachBenhNhan dsbn = new DanhSachBenhNhan();

	//@BeforeTest
	public void dieukienDauTien() {
		if (his.khoiDongHIS(HisActions.DUONG_DAN_FILE_CHAY_HIS) == true) {
			his.dangNhapHIS(FormKhuVuc.ten_dangNhap_admin, FormKhuVuc.matKhau_dangNhap_admin);
			his.chonPhongLamViec("Khám TMH");
			moMenuTiepNhanBenhNhan();
		} else
			setTestcaseStatus("FAIL", "Không thể khởi động ứng dụng HIS !");
	}

	@Test(priority = 1)
	public void tiepNhanBenhNhan_9870_1() {

		sleep(3);
		clickToaDo(637, 540);

		TestLogger.info("[Hành chính] Kiểm tra validate trường Họ tên");
		TestLogger.info("1.Kiểm tra giá trị mặc định Text box Họ tên");
		TestLogger.info("Expect : Giá trị mặc định là trống");
		if (getHoten().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
		}

	}

	@Test(priority = 2)
	public void tiepNhanBenhNhan_9870_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Họ tên");
		TestLogger.info("2.Kiểm tra việc nhập ký tự đặc biệt ^^ vào trường Họ tên");
		TestLogger.info("Expect : Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		dienHoTen("nguyen viet ^^");
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);
		if (getHoten().equalsIgnoreCase("nguyen viet ^^")) {

			setTestcaseStatus("PASS",
					"Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		} else {
			setTestcaseStatus("FAIL",
					"Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		}

	}

	@Test(priority = 3)
	public void tiepNhanBenhNhan_9870_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Họ tên");
		TestLogger.info("3.Kiểm tra việc để trống trường dữ liệu Họ tên");
		TestLogger.info("Expect : Hiển thị thông báo Họ tên không được để trống, và không cho phép Lưu");
		dienHoTen("");
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);

		if (waitForObjectPresent(CanhBaoHoTen, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Hiển thị thông báo Họ tên không được để trống, và không cho phép Lưu");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị thông báo Họ tên không được để trống, và không cho phép Lưu");
		}
	}

	@Test(priority = 4)
	public void tiepNhanBenhNhan_9870_4() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Họ tên");
		TestLogger.info("4.Kiểm tra việc nhập Họ tê  vượt quá maxlength 500 ký tự cho phép (256 ký tự)");
		TestLogger.info("Expect : Confirm từ ký tự 256 trở đi sẽ không nhận giá trị");
		dienHoTen(TienIch.taoRandomChu(502));
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);
		if (getHoten().length() == 500) {
			TestLogger.info("==> " + getHoten().length());
			setTestcaseStatus("PASS", "Confirm từ ký tự 501 trở đi sẽ không nhận giá trị");
		} else {
			TestLogger.info("==> " + getHoten().length());
			setTestcaseStatus("FAIL", "Confirm từ ký tự 500 trở đi sẽ không nhận giá trị");
		}
	}

	@Test(priority = 5)
	public void tiepNhanBenhNhan_9870_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Họ tên");
		TestLogger.info("5.Kiểm tra việc nhập không quá maxlength là 500 ký tự ");
		TestLogger.info("Expect : Confirm cho phép thêm mới thành công");
		dienHoTen(TienIch.taoRandomChu(500));
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);
		if (getHoten().length() == 500) {
			TestLogger.info("==> " + getHoten().length());
			setTestcaseStatus("PASS", "Confirm từ ký tự 500 trở đi sẽ không nhận giá trị");
		} else {
			TestLogger.info("==> " + getHoten().length());
			setTestcaseStatus("FAIL", "Confirm từ ký tự 500 trở đi sẽ không nhận giá trị");
		}
	}

	@Test(priority = 6)
	public void tiepNhanBenhNhan_9870_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Họ tên");
		TestLogger.info("6.Kiểm tra việc nhập không quá maxlength là 500 ký tự ");
		TestLogger.info("Expect : Confirm cho phép thêm mới thành công");
		dienHoTen(TienIch.taoRandomChu(255));
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);
		if (getHoten().length() == 255) {
			TestLogger.info("==> " + getHoten().length());
			setTestcaseStatus("PASS", "Confirm cho phép thêm mới thành công");
		} else {
			TestLogger.info("==> " + getHoten().length());
			setTestcaseStatus("FAIL", "Confirm cho phép thêm mới thành công");
		}
	}

	@Test(priority = 7)
	public void tiepNhanBenhNhan_9870_7() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Họ tên");
		TestLogger.info("7.Kiểm tra việc nhập câu lệnh javascript: <script>alert(test)</script>");
		TestLogger.info("Expect : Confirm cho phép thêm mới thành công");
		dienHoTen("<script>alert(test)</script>");
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);
		if (getHoten().equalsIgnoreCase("<script>alert(test)</script>")) {
			setTestcaseStatus("PASS", "Confirm cho phép thêm mới thành công");
		} else {
			setTestcaseStatus("FAIL", "Confirm cho phép thêm mới thành công");
		}
	}

	@Test(priority = 8)
	public void tiepNhanBenhNhan_9907_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Ngày sinh");
		TestLogger.info("Kiểm tra giá trị mặc định");
		dienHoTen("Nguyễn Việt Hà");
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);
		TestLogger.info("Expect : Mặc định là trống ");

		if (getNgaySinh().length() == 0) {
			setTestcaseStatus("PASS", "Mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Mặc định là trống");
		}
	}

	@Test(priority = 9)
	public void tiepNhanBenhNhan_9907_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Ngày sinh");
		TestLogger.info("Kiểm tra giá trị mặc định");
		dienHoTen("Nguyễn Việt Hà");
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);
		TestLogger.info("Expect : Mặc định là trống ");
		if (getNgaySinh().length() == 0) {
			setTestcaseStatus("PASS", "Mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Mặc định là trống");
		}
	}

	@Test(priority = 10)
	public void tiepNhanBenhNhan_9930_1() throws SQLException {

		TestLogger.info("[Hành chính] Kiểm tra validate trường số CMND");
		TestLogger.info("Expect : Giá trị mặc định là trống");
		if (getSoCMND().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
		}

	}

	@Test(priority = 11)
	public void tiepNhanBenhNhan_9930_2() throws SQLException {

		TestLogger.info("[Hành chính] Kiểm tra validate trường số CMND");
		TestLogger.info("Kiểm tra nhập giá trị là chữ");
		TestLogger.info("Expect : Cho phép nhập lưu");
		sleep(3);
		dienSoCMND("chet12345678");
		clickOn(TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);

		if (getSoCMND().equals("chet12345678")) {
			setTestcaseStatus("PASS", "Cho phép nhập lưu");
		} else {
			setTestcaseStatus("FAIL", "Cho phép nhập lưu");
		}

	}

	@Test(priority = 12)
	public void tiepNhanBenhNhan_9930_3() throws SQLException {

		TestLogger.info("[Hành chính] Kiểm tra validate trường số CMND");
		TestLogger.info("Kiểm tra nhập giá trị là số");
		TestLogger.info("Expect : Cho phép nhập lưu");
		sleep(3);
		dienSoCMND("12345678");
		clickOn(TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);

		if (getSoCMND().equals("12345678")) {
			setTestcaseStatus("PASS", "Cho phép nhập lưu số");
		} else {
			setTestcaseStatus("FAIL", "Cho phép nhập lưu số ");
		}
	}

	@Test(priority = 13)
	public void tiepNhanBenhNhan_9930_4() throws SQLException {

		TestLogger.info("[Hành chính] Kiểm tra validate trường số CMND");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt ^^^^");
		TestLogger.info("Expect : không cho phép nhập lưu");
		sleep(3);
		dienSoCMND("^^^^");
		clickOn(TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);

		if (getSoCMND().length() == 0) {
			setTestcaseStatus("PASS", "Không cho phép nhập lưu số");
		} else {
			setTestcaseStatus("FAIL", "Vẫn cho phép nhập lưu số ");
		}
	}

	@Test(priority = 14)
	public void tiepNhanBenhNhan_9930_5() throws SQLException {

		TestLogger.info("[Hành chính] Kiểm tra validate trường số CMND");
		TestLogger.info("Kiểm tra nhập ký tự tr 9 hoặc 12 ký tự");
		TestLogger.info("Expect : Cho phép nhập lưu");
		sleep(3);
		dienSoCMND(TienIch.taoRandomSovaChu(9));
		clickOn(TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);

		if (getSoCMND().length() == 9) {
			setTestcaseStatus("PASS", "Cho phép nhập lưu số");
		} else {
			setTestcaseStatus("FAIL", "Không cho phép nhập lưu số ");
		}

		dienSoCMND(TienIch.taoRandomSovaChu(12));
		clickOn(TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);

		if (getSoCMND().length() == 12) {
			setTestcaseStatus("PASS", "Cho phép nhập lưu số");
		} else {
			setTestcaseStatus("FAIL", "Không cho phép nhập lưu số ");
		}
	}

	@Test(priority = 15)
	public void tiepNhanBenhNhan_9930_6() throws SQLException {

		TestLogger.info("[Hành chính] Kiểm tra validate trường số CMND");
		TestLogger.info("Kiểm tra nhập ký tự > 12 ký tự");
		TestLogger.info("Expect : Chặn luôn kể từ ký tự thứ 13");
		sleep(3);
		dienSoCMND(TienIch.taoRandomSovaChu(13));
		clickOn(TiepNhanBenhNhan_Luu);
		s.type(Key.ENTER);

		if (getSoCMND().length() == 12) {
			setTestcaseStatus("PASS", "Chặn luôn kể từ ký tự thứ 13");
		} else {
			setTestcaseStatus("FAIL", "Chặn luôn kể từ ký tự thứ 13 ");
		}

	}

	@Test(priority = 16)
	public void tiepNhanBenhNhan_9930_7() throws SQLException {

		TestLogger.info("[Hành chính] Kiểm tra validate trường số CMND");
		TestLogger.info("Kiểm tra nhập ký tự < 9 ký tự");
		TestLogger.info("Expect : Thông báo số đt không hợp lệ");
		sleep(3);
		dienSoCMND(TienIch.taoRandomSovaChu(8));
		clickOn(TiepNhanBenhNhan_Luu);

		if (waitForObjectPresent(Hochieu_cmnd, 4)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Thông báo số CMND không hợp lệ");
		} else {
			setTestcaseStatus("FAIL", "Thông báo số CMND không hợp lệ ");
		}

	}

	@Test(priority = 17)
	public void tiepNhanBenhNhan_10010_1() throws SQLException {

		TestLogger.info("Kiểm tra giá trị mặc định Text box Số nhà");
		TestLogger.info("Giá trị mặc định là trống");
		if (getSoNha().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
		}

	}

	
	@Test(priority = 18)
	public void tiepNhanBenhNhan_10010_3() throws SQLException {

		TestLogger.info("Kiểm tra việc nhập Số nhà vượt quá maxlength 30 ký tự cho phép (31 ký tự) ");
		dienSoNha(TienIch.taoRandomSovaChu(31));
		TestLogger.info("Confirm từ ký tự 31 trở đi sẽ không nhận giá trị");
		if (getSoNha().length() == 30) {
			setTestcaseStatus("PASS", "ký tự 31 trở đi sẽ không nhận giá trị");
		} else {
			setTestcaseStatus("FAIL", "ký tự 31 trở đi sẽ không nhận giá trị");
		}

	}

	@Test(priority = 19)
	public void tiepNhanBenhNhan_10010_4() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Số nhà");
		TestLogger.info("4.Kiểm tra việc nhập Số nhà vượt quá maxlength 30 ký tự cho phép (31 ký tự)");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(31);
		dienSoNha(SoNha);
		clickOn(TiepNhanBenhNhan_Luu);
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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

		if (getSoNha().length() >= 31) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "SoNha vẫn nhận hơn 30 kí tự");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "SoNha không nhận hơn 30 kí tự ");
		}
	}

	@Test(priority = 20)
	public void tiepNhanBenhNhan_10010_5() {
		TestLogger.info("Kiểm tra việc nhập với ngôn ngữ Tiếng Việt có dấu ");
		TestLogger.info(
				"5.Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");

		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		clickOn(TiepNhanBenhNhan_Luu);
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		if (getSoNha().equals("Số 78 Hàng Ngang")) {
			setTestcaseStatus("PASS",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);

		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
		}
	}

	@Test(priority = 21)
	public void tiepNhanBenhNhan_10028_1() {
		TestLogger.info("[Tiếp nhận] Kiểm tra Validate checkbox Ưu tiên");
		TestLogger.info("Giá trị mặc định của  checkbox");
		TestLogger.info("Expect : Mặc định uncheck");
		if (waitForObjectAppearOnRegion(TiepNhanBenhNhan_DoiTuong, TiepNhanBenhNhan_UuTien_Unchecked, 306, 32, 3)) {
			setTestcaseStatus("PASS", "Mặc định uncheck");
		} else {
			setTestcaseStatus("FAIL", "Mặc định là khác uncheck");
		}

	}

	@Test(priority = 22)
	public void tiepNhanBenhNhan_10028_2() {
		TestLogger.info("Kiểm tra việc CHECK check box ");
		TestLogger.info("Expect : Cho phép check chọn check box");
		clickOn(TiepNhanBenhNhan_UuTien);
		if (waitForObjectAppearOnRegion(TiepNhanBenhNhan_DoiTuong, TiepNhanBenhNhan_UuTien_Unchecked, 306, 32, 3)) {
			setTestcaseStatus("FAIL", "Không Cho phép check chọn check box");
		} else {
			setTestcaseStatus("PASS", "Đã thay đổi được trạng thái checkbox");
		}

	}

	@Test(priority = 23)
	public void tiepNhanBenhNhan_9972_1() {
		TestLogger.info("Kiểm tra giá trị mặc định Text box Nơi làm việc ");
		TestLogger.info("Expect : Giá trị mặc định là trống");
		if (getNoiLamViec().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là trống");

		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không là trống");
		}
	}

	@Test(priority = 24)
	public void tiepNhanBenhNhan_9972_2() {
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt ^^^ - vào trường Nơi làm việc");
		TestLogger.info("Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");

		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Số ^^^^^^^^");
		clickOn(TiepNhanBenhNhan_Luu);

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		if (getNoiLamViec().equals("Số ^^^^^^^^")) {
			setTestcaseStatus("PASS",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);

		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
		}

	}

	@Test(priority = 25)
	public void tiepNhanBenhNhan_9972_4_5() {
		TestLogger.info("Kiểm tra việc nhập Nơi làm việc  vượt quá maxlength 255 ký tự cho phép (256 ký tự)");
		dienNoiLamViec(TienIch.taoRandomChu(256));
		TestLogger.info("Confirm từ ký tự 256 trở đi sẽ không nhận giá trị");
		if (getNoiLamViec().length() > 255) {
			setTestcaseStatus("FAIL", "từ ký tự 256 trở đi sẽ không nhận giá trị");

		} else {
			setTestcaseStatus("PASS", "từ ký tự 256 trở đi sẽ không nhận giá trị");
		}

	}

	@Test(priority = 26)
	public void tiepNhanBenhNhan_9972_6() {
		TestLogger.info("Kiểm tra việc nhập với ngôn ngữ Tiếng Việt có dấu");
		TestLogger.info(
				"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");

		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Số 72 ngõ Đống Đa");
		clickOn(TiepNhanBenhNhan_Luu);

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		if (getNoiLamViec().equals("Số 72 ngõ Đống Đa")) {
			setTestcaseStatus("PASS",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);

		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
		}

	}

	@Test(priority = 27)
	public void tiepNhanBenhNhan_9976_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Người liên hệ");
		TestLogger.info("Kiểm tra giá trị mặc định Text box Người liên hệ");
		TestLogger.info("Expect : Giá trị mặc định là trống");
		if (getNguoiLienHe().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định hiện không trống !");
		}
	}

	@Test(priority = 28)
	public void tiepNhanBenhNhan_9976_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Người liên hệ");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt vào trường Người liên hệ");
		TestLogger.info("Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Số ^^^^^^^^");
		dienNguoiLienHe("Liên Hệ ^^^^");
		clickOn(TiepNhanBenhNhan_Luu);

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		if (getNguoiLienHe().equals("Liên Hệ ^^^^")) {
			setTestcaseStatus("PASS",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);

		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
		}

	}

	@Test(priority = 29)
	public void tiepNhanBenhNhan_9976_4() {
		TestLogger.info("Kiểm tra việc nhập Người liên hệ  vượt quá maxlength 100 ký tự cho phép (101 ký tự)   ");
		TestLogger.info("Confirm từ ký tự 101 trở đi sẽ không nhận giá trị");
		dienNguoiLienHe(TienIch.taoRandomChu(101));
		if (getNguoiLienHe().length() == 100) {
			setTestcaseStatus("PASS", "Ký tự 101 trở đi sẽ không nhận giá trị");
		} else {
			setTestcaseStatus("FAIL", "Ký tự thứ 101 trở đi vẫn nhận giá trị");
		}
	}

	@Test(priority = 30)
	public void tiepNhanBenhNhan_9976_5() {
		TestLogger.info("Kiểm tra việc nhập không quá maxlength là 100 ký tự ");
		TestLogger.info("Confirm cho phép thêm mới thành công");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Số ^^^^^^^^");
		dienNguoiLienHe(TienIch.taoRandomSovaChu(100));
		clickOn(TiepNhanBenhNhan_Luu);

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		if (getNguoiLienHe().length() == 100) {
			setTestcaseStatus("PASS",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);

		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
		}

	}

	@Test(priority = 31)
	public void tiepNhanBenhNhan_9976_6() {
		TestLogger.info("Kiểm tra việc nhập không quá maxlength là 100 ký tự ");
		TestLogger.info("Confirm cho phép thêm mới thành công");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Số ^^^^^^^^");
		dienNguoiLienHe("Nguyễn Công");
		clickOn(TiepNhanBenhNhan_Luu);

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		if (getNguoiLienHe().equals("Nguyễn Công")) {
			setTestcaseStatus("PASS",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);

		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL",
					"Confirm cho phép thêm mới thành công, dữ liệu của trường được view lên bình thường, không bị lỗi font");
		}

	}

	@Test(priority = 32)
	public void tiepNhanBenhNhan_9865_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Giới tính");
		TestLogger.info("Kiểm tra giá trị radio button Giới tính");
		TestLogger.info("3 giá trị Nam, Nữ, Không xác định");
		if (waitForObjectPresent(TiepNhanBenhNhan_GioiTinhNamNuKhongXacDinh, 5)) {
			setTestcaseStatus("PASS", "Show 3 giá trị Nam, Nữ, Không xác định");
		} else {
			setTestcaseStatus("FAIL", "Không show 3 giá trị Nam, Nữ, Không xác định");

		}
	}

	@Test(priority = 33)
	public void tiepNhanBenhNhan_9865_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Giới tính");
		TestLogger.info("Kiểm tra giá trị mặc định ");
		TestLogger.info("Expect : Mặc định giới tính nam");
		if (waitForObjectAppearOnRegion(TiepNhanBenhNhan_HoTen, TiepNhanBenhNhan_GioiTinh_Radio_Checked, 357, 29, 5)) {
			setTestcaseStatus("PASS", "Mặc định giới tính nam");
		} else {
			setTestcaseStatus("FAIL", "Giá trị giới tính mặc định không phải là nam");
		}

	}

	@Test(priority = 34)
	public void tiepNhanBenhNhan_9865_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Giới tính");
		TestLogger.info("Kiểm tra chọn lại giá trị khác giá trị mặc định Nam ");
		TestLogger.info("Expect : Cho phép lưu giới tính Nữ, và Không xác định");
		clickOn(TiepNhanBenhNhan_GioiTinhNu);
		if (waitForObjectAppearOnRegion(TiepNhanBenhNhan_HoTen, TiepNhanBenhNhan_GioiTinh_Radio_Checked, 357, 29, 5)) {
			setTestcaseStatus("FAIL", "Cho phép lưu giới tính Nữ, và Không xác định");
		} else {
			setTestcaseStatus("PASS", "Cho phép lưu giới tính Nữ, và Không xác định");
		}
	}

	@Test(priority = 35)
	public void tiepNhanBenhNhan_9977_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra giá trị mặc định trường SĐT liên hệ");
		TestLogger.info("Expect : Giá trị mặc định là trống");

		if (getSoDienThoaiNguoiLienHe().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định là không trống");
		}

	}

	@Test(priority = 36)
	public void tiepNhanBenhNhan_9977_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra nhập giá trị là chữ");
		TestLogger.info("Expect : Không cho phép nhập");
		dienSoDienThoaiNguoiLienHe(TienIch.taoRandomChu(12));
		if (getSoDienThoaiNguoiLienHe().length() > 0) {
			setTestcaseStatus("FAIL", "Vẫn cho phép nhập");
		} else {
			setTestcaseStatus("PASS", "Không cho phép nhập");
		}
	}

	@Test(priority = 37)
	public void tiepNhanBenhNhan_9977_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra nhập giá trị là chữ");
		TestLogger.info("Expect : Cho phép nhập lưu");
		dienSoDienThoaiNguoiLienHe(TienIch.taoRandomSo(12));
		if (getSoDienThoaiNguoiLienHe().length() == 12) {
			setTestcaseStatus("PASS", "Cho phép nhập lưu");
		} else {
			setTestcaseStatus("FAIL", "Không cho phép nhập");
		}
	}

	@Test(priority = 38)
	public void tiepNhanBenhNhan_9977_4_5() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt ^^^^ ()...");
		TestLogger.info("Expect : cho phép nhập");
		dienSoDienThoaiNguoiLienHe("^^^^^^^^^");
		if (getSoDienThoaiNguoiLienHe().equals("^^^^^^^^^")) {
			setTestcaseStatus("PASS", "cho phép nhập");
		} else {
			setTestcaseStatus("FAIL", "không cho phép nhập");
		}
	}

	@Test(priority = 39)
	public void tiepNhanBenhNhan_9977_6() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra nhập ký tự số và ký tự vượt quá 20 ký tự");
		TestLogger.info("Expect : Chặn luôn kể từ ký tự thứ 21");
		dienSoDienThoaiNguoiLienHe(TienIch.taoRandomSo(21));
		if (getSoDienThoaiNguoiLienHe().length() == 20) {
			setTestcaseStatus("PASS", " chặn luôn từ kí tự thứ 21");
		} else {
			setTestcaseStatus("FAIL", "không chặn từ thế kỉ 21 ");
		}
	}

	@Test(priority = 40)
	public void tiepNhanBenhNhan_9977_7() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra nhập ký tự  <10 ký tự");
		TestLogger.info("Expect : Thông báo số điện thoại không đúng định dạng ");
		dienSoDienThoaiNguoiLienHe(TienIch.taoRandomSo(9));
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Số ^^^^^^^^");
		dienNguoiLienHe("Liên Hệ ^^^^");
		dienSoDienThoaiNguoiLienHe(TienIch.taoRandomSo(9));
		clickOn(TiepNhanBenhNhan_Luu);

		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 3)) {
			setTestcaseStatus("PASS", "Thông báo số điện thoại không đúng định dạng");
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);

		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Không thông báo số điện thoại không đúng định dạng");
		}
	}

	@Test(priority = 41)
	public void tiepNhanBenhNhan_10656_1() {
		TestLogger.info("Kiểm tra giá trị mặc định Text box Nhập nhanh");
		TestLogger.info("Giá trị mặc định là trống");
		if (getNhapNhanh().length() == 0) {
			setTestcaseStatus("PASS", "Giá trị mặc định là trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định là không phải là trống");
		}
	}

	@Test(priority = 42)
	public void tiepNhanBenhNhan_10656_4() {
		TestLogger.info("Kiểm tra giá trị mặc định Text box Nhập nhanh");
		TestLogger.info("Kiểm tra việc nhập Nhập nhanh nằm ngoài khoảng [4, 12] ký tự. Ví dụ nhập 3 hoặc 13 ký tự");
		TestLogger.info("Không cho phép Lưu, và không load tự động ra thông tin tương ứng ");
		dienNhapNhanh("ACB");
		s.type(Key.ENTER);
		if (getThanhPho().equalsIgnoreCase("Cao Bằng")) {
			setTestcaseStatus("FAIL", "Vẫn cho phép load tự động ");
		} else {
			setTestcaseStatus("PASS", "Không cho phép load tự động");
		}

	}

	@Test(priority = 43)
	public void tiepNhanBenhNhan_10656_5_7() {
		TestLogger.info("Kiểm tra giá trị mặc định Text box Nhập nhanh");
		TestLogger.info(
				"Kiểm tra việc nhập giá trị hợp lệ  trong khoảng [4, 12] ký tự. Và ký tự viết tắt đã được quy định trong danh mục Tỉnh/ thành phố, Quận/ huyện, Phường/ xã");
		TestLogger.info(
				"Cho phép tự động load ra theo thứ tự Tỉnh/ thành phố, Quận/ huyện, Phường/ xã tương ứng với tên viết tắt đã khai báo");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.ENTER);
		if (getThanhPho().equalsIgnoreCase("Tỉnh Hà Nam")) {
			setTestcaseStatus("PASS", "nhập nhanh cho quận huyện là đúng ");

			if (getQuanHuyen().equals("Thành phố Phủ Lý")) {
				setTestcaseStatus("PASS", "nhập nhanh cho quận huyện là đúng ");

				if (getPhuongXa().equals("Phường Quang Trung")) {

					setTestcaseStatus("PASS", "nhập nhanh cho phường xã là đúng");
				} else {
					setTestcaseStatus("FAIL", "nhập nhanh cho phường xã là sai");
				}

			} else {
				setTestcaseStatus("FAIL", "nhập nhanh cho quận huyện là sai ");
			}
		} else {
			setTestcaseStatus("FAIL", "nhập nhanh cho thành phố  là sai ");
		}

	}

	@Test(priority = 44)
	public void tiepNhanBenhNhan_10086_1() {
		TestLogger.info("[Tiếp nhận] Kiểm tra validate combobox Lý do");
		TestLogger.info("Kiểm tra giá trị mặc định");
		TestLogger.info("Expect : Giá trị mặc định Khám bệnh");
		if (getLiDo().equals("Khám bệnh")) {
			setTestcaseStatus("PASS", "Giá trị mặc định Khám bệnh");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định Khám bệnh");
		}
	}

	@Test(priority = 45)
	public void tiepNhanBenhNhan_10086_2() {
		TestLogger.info("[Tiếp nhận] Kiểm tra validate combobox Lý do");
		TestLogger.info("Kiểm tra bỏ trống trường Lý do");
		TestLogger.info("Expect : Hiển thị thông báo trường Lý do không được để trống");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Số ^^^^^^^^");
		dienNguoiLienHe("Liên Hệ ^^^^");
		dienLiDo("");
		clickOn(TiepNhanBenhNhan_Luu);

		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 3)) {
			setTestcaseStatus("PASS", "Hiển thị thông báo trường Lý do không được để trống");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị thông báo trường Lý do không được để trống");
		}

	}

	@Test(priority = 46)
	public void tiepNhanBenhNhan_10086_3() {
		TestLogger.info("[Tiếp nhận] Kiểm tra validate combobox Lý do");
		TestLogger.info("Kiểm tra cho phép nhập giá trị tìm kiếm trong combobox");
		TestLogger.info(
				"Expect : Cho phép nhập giá trị tìm kiếm trong combobox thành công, kết quả tra ra ứng với từ khóa tìm kiếm trong combobox.");
		dienLiDo("Nhập viện");

		if (getLiDo().equals("Nhập viện")) {
			setTestcaseStatus("PASS",
					"Cho phép nhập giá trị tìm kiếm trong combobox thành công, kết quả tra ra ứng với từ khóa tìm kiếm trong combobox");
		} else {
			setTestcaseStatus("FAIL",
					"Cho phép nhập giá trị tìm kiếm trong combobox thành công, kết quả tra ra ứng với từ khóa tìm kiếm trong combobox");
		}

	}

	
	@Test(priority = 47)
	public void tiepNhanBenhNhan_10193_3() {
		TestLogger.info("[BHYT] Kiểm tra cho phép nhập giá trị tìm kiếm trong combobox");
		TestLogger.info(
				"Kiểm tra mặc định trường Số tuyến chuyển  khi chọn đối tượng là BHYT + Hình thức đến khám là Cơ quan Y tế giới thiệu");
		dienDoiTuong("BHYT 100");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		TestLogger.info("Expect : Enable trường Nơi giới thiệu, và mặc định giá trị trong combobox là Blank");
		if (getSoTuyenChuyen().length() == 0) {
			setTestcaseStatus("PASS", "Enable trường Số tuyến chuyển  và mặc định giá trị trong combobox là Blank");
		} else {
			setTestcaseStatus("FAIL", "Enable trường Số tuyến chuyển  và mặc định giá trị trong combobox là Blank");
		}
	}

	@Test(priority = 48)
	public void tiepNhanBenhNhan_10193_4() {
		TestLogger.info("[BHYT] Kiểm tra cho phép nhập giá trị tìm kiếm trong combobox");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt ^^^ - vào trường Số tuyến chuyển");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Nơi làm việc ^^^^^^^^");
		dienNguoiLienHe("Người Liên Hệ ^^^^");
		dienLiDo("Khám bệnh");
		dienDoiTuong("BHYT 100");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		dienNoiGioiThieu("Bệnh viện 108");
		dienSoTuyenChuyen("^^^^^^^^^^");

		if (getSoTuyenChuyen().equals("^^^^^^^^^^")) {
			setTestcaseStatus("PASS", "Cho nhập ký tự đặc biệt ");
		} else {
			setTestcaseStatus("FAIL", "Cho nhập ký tự đặc biệt ");
		}

	}

	@Test(priority = 49)
	public void tiepNhanBenhNhan_10193_5() {
		TestLogger.info("[BHYT] Kiểm tra cho phép nhập giá trị tìm kiếm trong combobox");
		TestLogger.info("Kiểm tra bỏ trống combobox Số tuyến chuyển khi trường Số tuyến chuyển Enable");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Nơi làm việc ^^^^^^^^");
		dienNguoiLienHe("Người Liên Hệ ^^^^");
		dienLiDo("Khám bệnh");
		dienDoiTuong("BHYT 100");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		dienNoiGioiThieu("Bệnh viện 108");
		clickOn(TiepNhanBenhNhan_Luu);
		TestLogger.info("Hiển thị thông tin:”Bạn phải nhập số tuyến chuyển!” và focus chuột vào ô cần nhập");
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 3)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Hiển thị thông tin:”Bạn phải nhập số tuyến chuyển");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị thông tin:”Bạn phải nhập số tuyến chuyển");
		}
	}

	@Test(priority = 50)
	public void tiepNhanBenhNhan_10193_9() {
		TestLogger.info("[BHYT] Kiểm tra cho phép nhập giá trị tìm kiếm trong combobox");
		TestLogger.info("Kiểm tra việc nhập Số tuyến chuyển  vượt quá maxlength 10 ký tự cho phép (11 ký tự)  ");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Nơi làm việc ^^^^^^^^");
		dienNguoiLienHe("Người Liên Hệ ^^^^");
		dienLiDo("Khám bệnh");
		dienDoiTuong("BHYT 100");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		dienNoiGioiThieu("Bệnh viện 108");
		dienSoTuyenChuyen(TienIch.taoRandomChu(11));
		if (getSoTuyenChuyen().length() == 10) {
			setTestcaseStatus("PASS", "Số tuyến chuyển không vượt quá maxlength 10 ký tự");
		} else {
			setTestcaseStatus("FAIL", "Số tuyến chuyển vượt quá maxlength 10 ký tự");
		}

	}

	@Test(priority = 51)
	public void tiepNhanBenhNhan_10193_10() {
		TestLogger.info("[BHYT] Kiểm tra cho phép nhập giá trị tìm kiếm trong combobox");
		TestLogger.info(
				"Kiểm tra thông tin đang Enable sau đó chọn lại Hình thức (Tự đến...) hoặc chọn Đối tượng ( khác đối tượng BHYT) ");
		TestLogger.info("Điền Họ tên :");
		Hoten = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);
		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = TienIch.taoRandomTheoGioiHan(1000008900, 1234567890);
		dienSoDienThoai("1234567890");
		s.type(Key.TAB);
		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);
		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);
		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);
		TestLogger.info("Điền số nhà");
		SoNha = "Số 78 Hàng Ngang";
		dienSoNha(SoNha);
		dienNoiLamViec("Nơi làm việc ^^^^^^^^");
		dienNguoiLienHe("Người Liên Hệ ^^^^");
		dienLiDo("Khám bệnh");
		dienDoiTuong("BHYT 100 ");
		chonHinhThuc("Cơ quan y tế giới thiệu");
		dienNoiGioiThieu("Bệnh viện 108");
		dienSoTuyenChuyen(TienIch.taoRandomChu(11));
		dienDoiTuong("Dịch vụ");
		dienNoiGioiThieu("ABCD");

		TestLogger.info("Expect : Trường thông tin bị Disable lại  ");

		if (getNoiGioiThieu().equals("Bệnh viện 108")) {
			clickOn(TiepNhanBenhNhan_BoQua);
			waitForObjectPresent(TiepNhanBenhNhan_Them, 5);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Trường thông tin bị Disable lại");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			waitForObjectPresent(TiepNhanBenhNhan_Them, 5);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Trường thông tin bị Disable lại");
		}

	}

	

	@Test(priority = 52)
	public void tiepNhanBenhNhan_9855_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Mã BN");
		TestLogger.info("Kiểm tra giá trị mặc định");
		TestLogger.info("Expect : Mặc định giá trị Disable, không cho phép nhập ");
		dienMaBenhNhan("sssss");
		if (getMaBenhNhan().length() > 0) {
			setTestcaseStatus("FAIL", "Mặc định giá trị Disable, không cho phép nhập");
		} else {
			setTestcaseStatus("PASS", "Mặc định giá trị Disable, không cho phép nhập");
		}

	}

	@Test(priority = 53)
	public void tiepNhanBenhNhan_9855_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate trường Mã BN");
		TestLogger.info("Kiểm tra quy tắc sinh tự động Mã BN khi bấm Lưu");
		TestLogger.info(
				"Expect :  - Khi bấm Lưu, hệ thống tự động sinh mã BN - Gồm 8 ký tự, trong đó 2 ký tự đầu là năm 6 ký tự sau sẽ tự tăng");
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1111111100, 1222222200);
		dienSoDienThoaiNguoiLienHe("1519222321");
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
		dienNoiThucHien(NoiThucHien);
		s.type(Key.TAB);

		sleep(3);
		ThuTienSau = "Yes";
		chonThuTienSau(ThuTienSau);

		clickOn(TiepNhanBenhNhan_Luu);

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		String maBenhNhan = getMaBenhNhan();
		TestLogger.info("Do dai cua Ma Benh Nhan la  : " + maBenhNhan.length());
		TestLogger.info("2 so dau cua Nam Hien Tai la : " + TienIch.getNamHienTaicuaMayTinh().substring(2, 4)); // 2017
		if (maBenhNhan.length() == 8 && maBenhNhan.startsWith(TienIch.getNamHienTaicuaMayTinh().substring(0, 1))) {
			clickOn(TiepNhanBenhNhan_Sua);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "");
		} else {
			clickOn(TiepNhanBenhNhan_Sua);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "");
		}
	}

	@Test(priority = 54)
	public void tiepNhanBenhNhan_9943_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate combobox Tỉnh/ Thành phố");
		TestLogger.info("Kiểm tra giá trị mặc định");
		TestLogger.info("Expect : Giá trị mặc định Hà Nội");
		if (getThanhPho().equalsIgnoreCase("Thành phố Hà Nội")) {
			setTestcaseStatus("PASS", "Giá trị mặc định Hà Nội");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định Hà Nội");
		}

	}

	@Test(priority = 55)
	public void tiepNhanBenhNhan_9943_2() {
		TestLogger.info("[Hành chính] Kiểm tra validate combobox Tỉnh/ Thành phố");
		TestLogger.info("Kiểm tra bỏ trống trường Tỉnh/ TP");
		TestLogger.info("Hiển thị thông báo trường Tỉnh/ TP không được để trống");
		dienHoTen("Nguyen Viet Ha");
		dienNamSinh("2017");
		clickOn(TiepNhanBenhNhan_Luu);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 2)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Hiển thị thông báo trường Tỉnh/ TP không được để trống");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị thông báo trường Tỉnh/ TP không được để trống");

		}

	}

	@Test(priority = 56)
	public void tiepNhanBenhNhan_9943_3() {
		TestLogger.info("[Hành chính] Kiểm tra validate combobox Tỉnh/ Thành phố");
		TestLogger.info("Kiểm tra cho phép nhập giá trị tìm kiếm trong combobox");
		TestLogger
				.info("Expect:- Cho phép nhập giá trị tìm kiếm trong combobox thành công. kết quả tra ra ứng với từ khóa tìm kiếm trong combobox."
						+ "- Nhập giá trị viết tắt của tên Tỉnh thành, hiển thị ra Tỉnh thành");
		dienThanhPho("Tỉnh Bắc Giang");
		s.type(Key.ENTER);
		if (getThanhPho().equals("Tỉnh Bắc Giang")) {
			setTestcaseStatus("PASS", "kết quả tra ra ứng với từ khóa tìm kiếm trong combobox");
		} else {
			setTestcaseStatus("FAIL", "kết quả tra ra ứng với từ khóa tìm kiếm trong combobox");
		}

		dienThanhPho("BG");
		s.type(Key.ENTER);
		if (getThanhPho().equals("Tỉnh Bắc Giang")) {
			setTestcaseStatus("PASS", "kết quả tra ra ứng với từ khóa tìm kiếm trong combobox");
		} else {
			setTestcaseStatus("FAIL", "kết quả tra ra ứng với từ khóa tìm kiếm trong combobox");
		}

	}

	@Test(priority = 57)
	public void tiepNhanBenhNhan_9939_1() {
		TestLogger.info("[Hành chính] Kiểm tra validate combobox Dân tộc");
		TestLogger.info("Kiểm tra giá trị mặc định");
		TestLogger.info("Expect : Giá trị mặc định Kinh");

		if (getDanToc().equals("Kinh")) {
			setTestcaseStatus("PASS", "Giá trị mặc định Kinh");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định Kinh");
		}

	}

	@Test(priority = 58) // tu thu 3
	public void tiepNhanBenhNhan_9939_7() {
		TestLogger.info("[Hành chính] Kiểm tra validate combobox Dân tộc");
		TestLogger.info("Kiểm tra cho phép nhập giá trị tìm kiếm trong combobox");
		TestLogger.info(
				"Expect : Cho phép nhập giá trị tìm kiếm trong combobox thành công,kết quả tra ra ứng với từ khóa tìm kiếm trong combobox.");
		dienDanToc("hoa");
		if (getDanToc().equals("Hoa")) {
			setTestcaseStatus("PASS", "");
		} else {
			setTestcaseStatus("FAIL", "");
		}

	}

	@Test(priority = 59)
	public void tiepNhanBenhNhan_14479_1() {
		TestLogger.info("Kiểm tra tự động load ra Hình thức đến khám");
		TestLogger.info("Khi nhập mã ĐKKCB / quét số thẻ BHYT mà mã ĐKKCB trùng với bệnh viện đang cấu hình");
		dienDoiTuong("BHYT 100");
		dienSoTheBHYT("SV401" + TienIch.taoRandomSo(10));
		dienMaDKKCB("01005");
		TestLogger.info("Expect :Hiển thị hình thức là Tự đến ");
		if (getHinhThuc().equals("Tự đến")) {
			setTestcaseStatus("PASS", "Hiển thị hình thức là Tự đến ");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị hình thức là Tự đến ");
		}

		dienMaDKKCB("01043");
		TestLogger.info("Expect :Hiển thị hình thức là Cơ quan y tế giới thiệu ");
		if (getHinhThuc().equals("Cơ quan y tế giới thiệu")) {
			setTestcaseStatus("PASS", "Hiển thị hình thức là Cơ quan y tế giới thiệu ");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị hình thức là Cơ quan y tế giới thiệu ");
		}

	}

	@Test(priority = 60)
	public void tiepNhanBenhNhan_12573_1() {
		TestLogger.info("Kiểm tra validate trường Đơn giá BHYT - Check BHYT chi trả cho ngoại trú");
		TestLogger.info("Kiểm tra giá trị hiển thị tại cột Đơn giá BHYT khi đã chọn dịch vụ nhưng đối tượng khác BHYT");
		dienDoiTuong("Dịch vụ");
		dienTenDichVu("Khám tai mũi họng");
		dienNoiThucHien("Khám TMH");
		TestLogger.info("Expect : Đơn giá BHYT = 0");
		clickToaDo(1033, 383);

		String giaTriTien = layTextTuAnh(1172, 532, 70, 19);
		TestLogger.info("Gia Tri Tien BHYT chi tra :" + giaTriTien);
		TestLogger.info("do dai:" + giaTriTien.length());
		System.out.println("0" + giaTriTien.charAt(0));
		System.out.println("1" + giaTriTien.charAt(1));
		System.out.println("2" + giaTriTien.charAt(2));

		if (giaTriTien.charAt(0) == '0') {
			sleep(2);
			clickToaDo(1358, 383);
			setTestcaseStatus("PASS", "Đơn giá BHYT = 0");
		} else {
			clickToaDo(1358, 383);
			setTestcaseStatus("FAIL", "Đơn giá BHYT = 0");
		}
	}

	@Test(priority = 61)
	public void tiepNhanBenhNhan_12573_3() {
		TestLogger.info("Kiểm tra validate trường Đơn giá BHYT - Check BHYT chi trả cho ngoại trú");
		TestLogger.info(
				"Đơn giá BHYT của dịch vụ được lấy tương ứng trong danh mục giá BHYT hiện hành của dịch vụ (item 8136)");
		dienDoiTuong("BHYT 100");
		dienTenDichVu("Khám tai mũi họng");
		dienNoiThucHien("Khám TMH");
		TestLogger.info(
				"Expect :Đơn giá BHYT của dịch vụ được lấy tương ứng trong danh mục giá BHYT hiện hành của dịch vụ");
		clickToaDo(1033, 383);
		String giaTriTien = layTextTuAnh(988, 531, 76, 20);
		TestLogger.info("Gia Tri Tien BHYT chi tra :" + giaTriTien);
		TestLogger.info("do dai:" + giaTriTien.trim().length());

		if (giaTriTien.trim().equals("195.000")) {
			sleep(2);
			clickToaDo(1358, 383);
			setTestcaseStatus("PASS",
					"Đơn giá BHYT của dịch vụ được lấy tương ứng trong danh mục giá BHYT hiện hành của dịch vụ");
		} else {
			clickToaDo(1358, 383);
			setTestcaseStatus("FAIL",
					"Đơn giá BHYT của dịch vụ được lấy tương ứng trong danh mục giá BHYT hiện hành của dịch vụ");
		}

		// check voi BHYT 80 phan tram
		TestLogger.info("Kiểm tra validate trường Đơn giá BHYT - Check BHYT chi trả cho ngoại trú");
		TestLogger.info(
				"Đơn giá BHYT của dịch vụ được lấy tương ứng trong danh mục giá BHYT hiện hành của dịch vụ (item 8136)");
		dienDoiTuong("BHYT 95%");
		clickToaDo(1033, 383);
		giaTriTien = layTextTuAnh(1246, 531, 90, 20);
		TestLogger.info("Gia Tri Tien BHYT chi tra :" + giaTriTien);
		TestLogger.info("do dai:" + giaTriTien.trim().length());

		if (giaTriTien.trim().equals("0")) {
			sleep(2);
			clickToaDo(1358, 383);
			setTestcaseStatus("PASS", "Đơn giá BHYT của dịch vụ được lấy tương ứng trong danh mục giá BHYT 80");
		} else {
			clickToaDo(1358, 383);
			setTestcaseStatus("FAIL", "Đơn giá BHYT của dịch vụ được lấy tương ứng trong danh mục giá BHYT 80");
		}

	}

	@Test(priority = 62)
	public void tiepNhanBenhNhan_9858_1() {
		TestLogger.info("Kiểm tra giá trị mặc định");
		TestLogger.info("Mặc định giá trị trống, không cho phép nhập");
		dienSoTiepNhan("2321323434");
		if (getSoTiepNhan().length() > 0) {
			setTestcaseStatus("FAIL", "Mặc định giá trị trống, không cho phép nhập");
		} else {
			setTestcaseStatus("PASS", "Mặc định giá trị trống, không cho phép nhập");
		}
	}

	@Test(priority = 63)
	public void tiepNhanBenhNhan_9858_2() {
		TestLogger.info("Kiểm tra quy tắc sinh tự động sinh số TN khi bấm Lưu");
		TestLogger.info(" Khi bấm Lưu, hệ thống tự động sinh số TN - Gồm 10 ký tự: ");

		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(123456789, 987654321);
		dienSoDienThoaiNguoiLienHe("1234567890");
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
		dienNoiThucHien(NoiThucHien);
		s.type(Key.TAB);

		sleep(3);
		ThuTienSau = "Yes";
		chonThuTienSau(ThuTienSau);

		clickOn(TiepNhanBenhNhan_Luu);

		if (waitForObjectPresent(Phieukham, 2)) {
			s.click();
			s.type(Key.F4, Key.ALT);
		}

		TestLogger.info("Check : Bệnh nhân vừa tiếp nhận nằm trong danh sách chờ khám");
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		soTiepNhan = getSoTiepNhan();
		if (soTiepNhan.length() == 10) {
			setTestcaseStatus("PASS", "");
		} else {
			setTestcaseStatus("FAIL", "");
		}
		String sub = soTiepNhan.substring(0, 2);
		String sub2 = getNamHienTaicuaMayTinh().toString().substring(2, 4);
		TestLogger.info("sub : " + sub + "sub2 : " + getNamHienTaicuaMayTinh().toString().substring(2, 4));
		if (soTiepNhan.substring(0, 2).equals(sub2)) {
			setTestcaseStatus("PASS", "+ 2 ký tự đầu là 2 số cuối của năm");
		} else {
			setTestcaseStatus("FAIL", "+ 2 ký tự đầu là 2 số cuối của năm");
		}

		TestLogger.info("Expect :  + 2 ký tự tiếp theo là tháng");

		TestLogger.info("thang cua so tiep nhan : " + soTiepNhan.substring(2, 4));
		TestLogger.info("thang hien tai ==>" + getThangHienTaicuaMayTinh());
		if (soTiepNhan.substring(2, 4).equals(getThangHienTaicuaMayTinh())) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);

			setTestcaseStatus("PASS", "+ 2 ký tự tiếp theo là tháng");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);

			setTestcaseStatus("FAIL", "+ 2 ký tự tiếp theo là tháng");
		}

	}

	@Test(priority = 64)
	public void tiepNhanBenhNhan_11250_1() {
		TestLogger
				.info("[Tiếp nhận BN chung] Kiểm tra chức năng Hủy (nút xóa trên grid Đăng ký khám) khi đã thanh toán");
		TestLogger.info("Không cho phép xóa");
		sleep(2);
		// click danh sach benh nhan
		clickToaDo(75, 685);

		do {
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			sleep(3);

		} while (waitForObjectPresent(DanhSachBenhNhan.DanhSachBenhNhan_TrangThai, 1) != true);

		hoverImage(DanhSachBenhNhan.DanhSachBenhNhan_TrangThai);
		clickToaDo(1361, 156);
		clickOn(DanhSachBenhNhan.DanhSachBenhNhan_EnterTextToSearch);
		doubleClick(DanhSachBenhNhan.DanhSachBenhNhan_ChoKetQua);
		moveMouseDownFromLogo(DanhSachBenhNhan.DanhSachBenhNhan_TimKiem, 142);
		s.doubleClick();
		clickOn(TiepNhanBenhNhan_Sua);
		if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 1)) {
			s.type(Key.ENTER);

			setTestcaseStatus("PASS", "Không cho phép xóa");
		} else {
			setTestcaseStatus("FAIL", "Không cho phép xóa");
		}

	}

	@Test(priority = 65)
	public void tiepNhanBenhNhan_11250_2() {
		TestLogger.info("Kiểm tra xóa dịch vụ khi Grid có cả dịch vụ đã thanh toán và dịch vụ chưa thanh toán");
		TestLogger.info("Chỉ cho phép xóa dịch vụ chưa thanh toán");
		clickToaDo(75, 685);
		do {
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			clickToaDo(1357, 717);
			sleep(3);

		} while (waitForObjectPresent(DanhSachBenhNhan.DanhSachBenhNhan_TrangThai, 1) != true);

		hoverImage(DanhSachBenhNhan.DanhSachBenhNhan_TrangThai);
		clickToaDo(1361, 156);
		clickOn(DanhSachBenhNhan.DanhSachBenhNhan_EnterTextToSearch);

		doubleClick(DanhSachBenhNhan.DanhSanhBenhNhan_ChoKham);

		moveMouseDownFromLogo(DanhSachBenhNhan.DanhSachBenhNhan_TimKiem, 142);
		s.doubleClick();
		clickOn(TiepNhanBenhNhan_Sua);
		if (!waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 1)) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Cho phép xóa");
		} else {

			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Không cho phép xóa");
		}
	}

	@Test(priority = 66)
	public void tiepNhanBenhNhan_10289_1() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu");
		TestLogger.info("Kiểm tra giá trị mặc định khi chưa chọn dịch vụ");
		// lay gia tri mac dinh cua don gia doanh thu
		String giaDoanhThu = layTextTuAnh(758, 531, 110, 17);
		TestLogger.info("Expect : Mặc định giá trị trống");
		if (giaDoanhThu.length() == 0) {
			setTestcaseStatus("PASS", "Mặc định giá trị trống");
		} else {
			setTestcaseStatus("FAIL", "Mặc định giá trị trống");
		}

	}

	@Test(priority = 67)
	public void tiepNhanBenhNhan_10289_2() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu");
		TestLogger.info("Kiểm tra giá trị Đơn giá doanh thu theo đối tượng là Dịch vụ");
		dienDoiTuong("Dịch vụ");
		dienTenDichVu("Khám tai mũi họng");
		dienNoiThucHien("Khám TMH");
		// get don gia Doanh Thu

		String giaDoanhThu = layTextTuAnh(758, 531, 110, 17);
		clickToaDo(1033, 383);

		// get gia Benh nhan phai tra tien

		String giaBenhNhanPhaiChiTra = layTextTuAnh(1247, 532, 91, 20);

		if (giaDoanhThu.trim().equals(giaBenhNhanPhaiChiTra.trim())) {
			setTestcaseStatus("PASS", " Đơn giá doanh thu bằng giá Bệnh Nhân thanh toán phải trả ");
		} else {
			setTestcaseStatus("FAIL", " Đơn giá doanh thu bằng giá Bệnh Nhân thanh toán phải trả ");
		}

	}

	@Test(priority = 68)
	public void tiepNhanBenhNhan_10289_3() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu");
		TestLogger.info(
				"Kiểm tra giá trị Đơn giá doanh thu theo đối tượng là Yêu cầu hoặc đối tượng khác được thiết lập thêm tùy nghiệp vụ của bệnh viện từng thời kỳ ");
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu");
		TestLogger.info("Kiểm tra giá trị Đơn giá doanh thu theo đối tượng là Dịch vụ");
		dienDoiTuong("Yêu cầu");
		dienTenDichVu("Khám tai mũi họng");
		dienNoiThucHien("Khám TMH");
		// get don gia Doanh Thu

		String giaDoanhThu = layTextTuAnh(758, 531, 110, 17);
		clickToaDo(1033, 383);

		// get gia Benh nhan phai tra tien

		String giaBenhNhanPhaiChiTra = layTextTuAnh(1247, 532, 91, 20);

		if (giaDoanhThu.trim().equals(giaBenhNhanPhaiChiTra.trim())) {
			setTestcaseStatus("PASS", " Đơn giá doanh thu bằng giá Bệnh Nhân thanh toán phải trả ");
		} else {
			setTestcaseStatus("FAIL", " Đơn giá doanh thu bằng giá Bệnh Nhân thanh toán phải trả ");
		}

	}

	@Test(priority = 69)
	public void tiepNhanBenhNhan_10289_4() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Đơn giá doanh thu");
		TestLogger.info("Kiểm tra giá trị Đơn giá doanh thu theo đối tượng là BHYT");
		dienDoiTuong("BHYT 100");
		dienTenDichVu("Khám tai mũi họng");
		dienNoiThucHien("Khám TMH");
		// get don gia Doanh Thu

		String giaDoanhThu = layTextTuAnh(758, 531, 110, 17);
		clickToaDo(1033, 383);

		int donGiaDoanhThu = Integer.valueOf(giaDoanhThu.trim().replace(".", "").replaceAll("\\s+", ""));

		// get don gia Bao Hiem Y Te

		String GiaBaoHiemYTe = layTextTuAnh(987, 530, 76, 22);
		int donGiaBaoHiemYTe = Integer.valueOf(GiaBaoHiemYTe.trim().replace(".", "").replaceAll("\\s+", ""));

		// gia benh nhan phai chi tra
		String giaBenhNhanPhaiChiTra = layTextTuAnh(1247, 532, 91, 20);

		int donGiaBenhNhanPhaiChiTra = Integer
				.valueOf(giaBenhNhanPhaiChiTra.trim().replace(".", "").replaceAll("\\s+", ""));
		// if đơn giá doanh thu = Đơn giá bảo hiểm y Tế

		if (donGiaDoanhThu <= donGiaBaoHiemYTe) {
			Assert.assertEquals(donGiaBenhNhanPhaiChiTra, 0);
			setTestcaseStatus("PASS", "  Nếu dv không thu chênh lệch: Đơn giá doanh thu = Đơn giá BHYT");
		} else {

			setTestcaseStatus("FAIL", "  Nếu dv không thu chênh lệch: Đơn giá doanh thu = Đơn giá BHYT ");
		}

		if (donGiaDoanhThu > donGiaBaoHiemYTe) {
			Assert.assertEquals(donGiaBenhNhanPhaiChiTra, donGiaDoanhThu - donGiaBaoHiemYTe);
			clickToaDo(1357, 383);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "  Nếu dv có thu chênh lệch: Đơn giá doanh thu > Đơn giá BHYT");
		} else {
			clickToaDo(1357, 383);
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "  Nếu dv có thu chênh lệch: Đơn giá doanh thu > Đơn giá BHYT ");
		}

	}

	@Test(priority = 70)
	public void tiepNhanBenhNhan_10272_1() {
		TestLogger.info("[Nghiệp vụ]Kiểm tra Validate checkbox Thu tiền sau");
		TestLogger.info("Giá trị mặc định của  checkbox");
		sleep(2);
		dienTenDichVu("Vận chuyển cấp cứu");
		TestLogger.info("Expect : Mặc định uncheck");
		if (findObjectInToaDo(TiepNhanBenhNhan_ThuTienSau_Uncheck, 652, 531, 52, 20)) {
			setTestcaseStatus("PASS", "Mặc định uncheck");
		} else {
			setTestcaseStatus("FAIL", "Mặc định lại là có check");
		}

	}

	@Test(priority = 71)
	public void tiepNhanBenhNhan_10272_2() {
		TestLogger.info("[Nghiệp vụ]Kiểm tra Validate checkbox Thu tiền sau");
		dienTenDichVu("Vận chuyển cấp cứu");
		TestLogger.info("Kiểm tra việc CHECK chọn check box 1 dịch vụ");
		clickToaDo(679, 540);
		hoverImage(TiepNhanBenhNhan_DanToc);

		if (findObjectInToaDo(TiepNhanBenhNhan_ThuTienSau_Uncheck, 652, 531, 52, 20)) {
			setTestcaseStatus("FAIL", "Không cho phép check chọn check box");
		} else {
			setTestcaseStatus("PASS", "Cho phép check chọn check box");
		}

	}

	@Test(priority = 72)
	public void tiepNhanBenhNhan_10272_5() {
		TestLogger.info("[Nghiệp vụ]Kiểm tra Validate checkbox Thu tiền sau");
		TestLogger.info("Check box thu tiền sau khi chưa bấm Lưu ");

		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(123456789, 987654321);
		dienSoDienThoaiNguoiLienHe("1234567890");
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		dienTenDichVu2("Khám tai mũi họng");

		TestLogger.info(
				"Expect : Khi chưa bấm Lưu, nếu dv đầu tiên chọn check thì các dv sau cũng tự động chọn check. Và ngược lại  nếu dv đầu tiên chọn uncheck thì các dv sau cũng tự động chọn uncheck");
		if (findObjectInToaDo(TiepNhanBenhNhan_ThuTienSau_Uncheck, 647, 553, 52, 20)) {
			setTestcaseStatus("FAIL",
					"Khi chưa bấm Lưu, nếu dv đầu tiên chọn check thì các dv sau cũng tự động chọn check. Và ngược lại  nếu dv đầu tiên chọn uncheck thì các dv sau cũng tự động chọn uncheck");
		} else {
			setTestcaseStatus("PASS",
					"Khi chưa bấm Lưu, nếu dv đầu tiên chọn check thì các dv sau cũng tự động chọn check. Và ngược lại  nếu dv đầu tiên chọn uncheck thì các dv sau cũng tự động chọn uncheck");
		}

	}

	@Test(priority = 73)
	public void tiepNhanBenhNhan_10272_6() {
		TestLogger.info("[Nghiệp vụ]Kiểm tra Validate checkbox Thu tiền sau");
		TestLogger.info("Check box thu tiền sau khi đã bấm Lưu ");
		TestLogger
				.info("Expect : Khi đã bấm Lưu, dv chọn sau chọn check/ uncheck không tự động giống với dv chọn trước");
		clickOn(TiepNhanBenhNhan_Luu);
		if (waitForObjectPresent(Phieukham, 6)) {
			s.click();
			s.type(Key.F4, Key.ALT);
		}
		if (waitForObjectPresent(FormKhamBenh.PhieuChiDinh, 5)) {
			s.click();
			s.type(Key.F4, Key.ALT);
		}
		clickOn(TiepNhanBenhNhan_DanhSachBenhNhan);
		waitForObjectPresent(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan, 4);
		clickOn(DanhSachBenhNhan.TitleForm_DanhSachBenhNhan);

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
		dienTenDichVu3("Khám tiêu hóa");
		if (findObjectInToaDo(TiepNhanBenhNhan_ThuTienSau_Uncheck, 646, 576, 52, 20)) {

			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS",
					"Khi đã bấm Lưu, dv chọn sau chọn check/ uncheck không tự động giống với dv chọn trước");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			sleep(2);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL",
					"Khi đã bấm Lưu, dv chọn sau chọn check/ uncheck không tự động giống với dv chọn trước");
		}

	}

	@Test(priority = 74)
	public void tiepNhanBenhNhan_10339_2() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Bệnh nhân thanh toán");
		TestLogger.info("Kiểm tra giá trị khi chọn dịch vụ :");
		dienDoiTuong("Dịch vụ");
		dienTenDichVu("(Honglee) Khám nội tiết");
		dienTenDichVu2("");
		clickToaDo(1030, 382);
		// get Thanh Tien Doanh Thu
		String thanhTienDoanhThu = layTextTuAnh(886, 531, 99, 18);
		TestLogger.info(thanhTienDoanhThu.trim());
		// get Benh Nhan Thanh Toan
		String benhNhanThanhToan = layTextTuAnh(1252, 531, 83, 20);
		TestLogger.info(benhNhanThanhToan.trim());
		if (thanhTienDoanhThu.trim().equals(benhNhanThanhToan.trim())) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("PASS", "Đối tượng Dịch vụ: BN thanh toán = Thành tiền doanh thu ");
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			setTestcaseStatus("FAIL", "Đối tượng Dịch vụ: BN thanh toán = Thành tiền doanh thu ");
		}

	}

	@Test(priority = 75)
	public void tiepNhanBenhNhan_10339_3() {
		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Bệnh nhân thanh toán");
		TestLogger.info("Kiểm tra giá trị khi chọn dịch vụ BHYT : ");

		dienDoiTuong("BHYT 95 ");
		dienTenDichVu("(Hằng test) Khám tổng quát ");
		clickToaDo(1030, 382);
		// get Thanh Tien Doanh Thu
		String TienDoanhThu = layTextTuAnh(886, 531, 99, 18);

		int thanhTienDoanhThu = Integer.valueOf(TienDoanhThu.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("thanhTienDoanhThu 1 : " + thanhTienDoanhThu);

		// get Don Gia Bao Hiem Y Te
		/*
		 * String GiaBaoHiemYTe = layTextTuAnh(993, 531, 68, 18); int
		 * donGiaBaoHiemYTe = Integer.valueOf(GiaBaoHiemYTe.trim().replace(".",
		 * "").replaceAll("\\s+", "")); TestLogger.info("donGiaBaoHiemYTe 2 : "
		 * + donGiaBaoHiemYTe);
		 */

		// get Thanh Tien Bao Hiem Y Te
		String TienBaoHiemYTe = layTextTuAnh(1075, 531, 91, 18);
		int thanhTienBaoHiemYTe = Integer.valueOf(TienBaoHiemYTe.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("thanhTienBaoHiemYTe 3 : " + thanhTienBaoHiemYTe);
		// get Bao Hiem Y Te chi tra
		String BaoHiemYTeChiTra = layTextTuAnh(1179, 531, 61, 19);
		int thanhTienBaoHiemYTeChiTra = Integer
				.valueOf(BaoHiemYTeChiTra.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("thanhTienBaoHiemYTeChiTra 4 : " + thanhTienBaoHiemYTeChiTra);
		// get Benh nhan Thanh Toan
		String BenhNhanThanhToan = layTextTuAnh(1254, 531, 81, 19);
		int thanhTienBenhNhanThanhToan = Integer
				.valueOf(BenhNhanThanhToan.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("thanhTienBenhNhanThanhToan 5 : " + thanhTienBenhNhanThanhToan);

		if (thanhTienDoanhThu <= thanhTienBaoHiemYTe) {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			Assert.assertEquals(thanhTienBenhNhanThanhToan, thanhTienBaoHiemYTe - thanhTienBaoHiemYTeChiTra);
		} else {
			clickOn(TiepNhanBenhNhan_BoQua);
			s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Them);
			Assert.assertEquals(thanhTienBenhNhanThanhToan,
					(thanhTienDoanhThu - thanhTienBaoHiemYTe) + (thanhTienBaoHiemYTe - thanhTienBaoHiemYTeChiTra));
		}

	}

	@Test(priority = 76)
	public void tiepNhanBenhNhan_10338_3() {

		TestLogger.info("[Đăng ký khám] Kiểm tra validate trường Thành tiền BHYT ");
		TestLogger.info("Kiểm tra giá trị khi chọn dịch vụ không có giá tại cột Đơn giá BHYT: ");
		dienDoiTuong("BHYT 95 ");
		dienTenDichVu("Vận chuyển cấp cứu");

		// get Thanh Tien Bao Hiem Y Te
		String TienBaoHiemYTe = layTextTuAnh(1075, 531, 91, 18);
		int thanhTienBaoHiemYTe = Integer.valueOf(TienBaoHiemYTe.trim().replace(".", "").replaceAll("\\s+", ""));
		if (thanhTienBaoHiemYTe == 0) {
			setTestcaseStatus("PASS", "Hiển thị dữ liệu blank");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị dữ liệu blank");
		}

	}

	@Test(priority = 77)
	public void tiepNhanBenhNhan_11583_1() {

		TestLogger.info("(Chờ confirm) [Tiếp nhận BN chung] Chọn bệnh nhân từ form Danh sách BN");
		TestLogger.info("Chọn 1 bệnh nhân từ danh sách bệnh nhân - TH bệnh nhân có 1 số tiếp nhận");
		TestLogger.info(
				"Expect : (pass) Load đầy đủ và chính xác các thông tin đã khai báo của BN từ màn hình tiếp nhận. Thông tin BN hiển thị ở dạng chỉ cho View và không cho phép sửa");

		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1234567890, 1876543211);
		dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
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

		TestLogger.info("Check : Bệnh nhân vừa tiếp nhận nằm trong danh sách chờ khám");
		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		soTiepNhan = getSoTiepNhan();

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

	@Test(priority = 78)
	public void tiepNhanBenhNhan_10520_2() {
		TestLogger.info("[Phòng khám] Kiểm tra validate cột Tổng số");
		// click để mở rộng cửa sổ phòng khám
		moRongKhungPhongKham();
		// get tổng số bệnh nhân tại phòng khám tai mũi họng hiện tại khi chưa
		// tiếp nhận thêm
		String SoBenhNhan = layTextTuAnh(1218, 124, 54, 20);

		int tongSoBenhNhan = Integer.valueOf(SoBenhNhan.trim().replace(".", "").replaceAll("\\s+", ""));

		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1234567890, 1876543211);
		dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
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

		TestLogger.info("Kiểm tra hiển thị cột Tổng số khi đã có thông tin về số lượt của BN khám trong ngày");

		TestLogger.info("Expect : Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		// get tổng số bệnh nhân tại phòng khám tai mũi họng hiện tại khi chưa
		// tiếp nhận thêm
		String SoBenhNhanSauKhiTiepNhanThem = layTextTuAnh(1218, 124, 54, 20);

		int tongSoBenhNhanSauKhiTiepNhanThem = Integer
				.valueOf(SoBenhNhanSauKhiTiepNhanThem.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("==> " + tongSoBenhNhanSauKhiTiepNhanThem);
		if (tongSoBenhNhanSauKhiTiepNhanThem - tongSoBenhNhan == 1) {
			thuHepKhungPhongKham();
			setTestcaseStatus("PASS", "Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		} else {
			thuHepKhungPhongKham();
			setTestcaseStatus("FAIL", "Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		}
	}

	@Test(priority = 79)
	public void tiepNhanBenhNhan_10520_3() {
		TestLogger.info("[Phòng khám] Kiểm tra validate cột Tổng số");
		TestLogger.info(
				"Kiểm tra BN đang chờ khám hủy dịch vụ khám thành công (Bác sĩ Hủy dịch vụ tại màn hình của bác sĩ)");
		TestLogger.info("Expect : Tổng số sẽ trừ đi số bệnh nhân hủy dịch vụ ");
		moRongKhungPhongKham();
		// get tổng số bệnh nhân tại phòng khám tai mũi họng hiện tại khi chưa
		// tiếp nhận thêm
		String SoBenhNhan = layTextTuAnh(1218, 124, 54, 20);

		int tongSoBenhNhan = Integer.valueOf(SoBenhNhan.trim().replace(".", "").replaceAll("\\s+", ""));

		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1234567890, 1876543211);
		dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
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

		TestLogger.info("Kiểm tra hiển thị cột Tổng số khi đã có thông tin về số lượt của BN khám trong ngày");

		TestLogger.info("Expect : Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		// get tổng số bệnh nhân tại phòng khám tai mũi họng hiện tại khi chưa
		// tiếp nhận thêm
		String SoBenhNhanSauKhiTiepNhanThem = layTextTuAnh(1218, 124, 54, 20);

		int tongSoBenhNhanSauKhiTiepNhanThem = Integer
				.valueOf(SoBenhNhanSauKhiTiepNhanThem.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("==> " + tongSoBenhNhanSauKhiTiepNhanThem);
		if (tongSoBenhNhanSauKhiTiepNhanThem - tongSoBenhNhan == 1) {

			setTestcaseStatus("PASS", "Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		} else {

			setTestcaseStatus("FAIL", "Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		}

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		// xoa dich vu
		clickToaDo(31, 539);

		clickOn(TiepNhanBenhNhan_Luu);
		String SoBenhNhanSauKhiBoDichVu = layTextTuAnh(1218, 124, 54, 20);

		int tongSoBenhNhanSauKhiBoDichVu = Integer
				.valueOf(SoBenhNhanSauKhiBoDichVu.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("==> " + tongSoBenhNhanSauKhiBoDichVu);

		if (tongSoBenhNhanSauKhiBoDichVu == tongSoBenhNhan) {
			thuHepKhungPhongKham();

			setTestcaseStatus("PASS", "Tổng số sẽ trừ đi số bệnh nhân hủy dịch vụ");

		} else {
			thuHepKhungPhongKham();
			setTestcaseStatus("FAIL", "Tổng số sẽ trừ đi số bệnh nhân hủy dịch vụ");
		}

	}

	@Test(priority = 80)
	public void tiepNhanBenhNhan_10504_2() {
		TestLogger.info("[Phòng khám] Kiểm tra validate cột Chờ");

		// click để mở rộng cửa sổ phòng khám
		moRongKhungPhongKham();
		// get tổng số bệnh nhân tại phòng khám tai mũi họng hiện tại khi chưa
		// tiếp nhận thêm
		String SoBenhNhanCho = layTextTuAnh(1291, 123, 52, 17);

		int tongSoBenhNhanCho = Integer.valueOf(SoBenhNhanCho.trim().replace(".", "").replaceAll("\\s+", ""));

		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1234567890, 1876543211);
		dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
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

		TestLogger.info("Kiểm tra hiển thị cột Tổng số khi đã có thông tin về số lượt của BN khám trong ngày");

		TestLogger.info("Kiểm tra hiển thị cột Chờ khi đã có thông tin về số lượt của BN chờ khám trong ngày");
		// get tổng số bệnh nhân đang chờ tại tại phòng khám tai mũi họng hiện
		// tại khi đã tiếp nhận thêm
		String SoBenhNhanSauKhiTiepNhanThem = layTextTuAnh(1291, 123, 52, 17);

		int tongSoBenhNhanSauKhiTiepNhanThem = Integer
				.valueOf(SoBenhNhanSauKhiTiepNhanThem.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("==> " + tongSoBenhNhanSauKhiTiepNhanThem);
		if (tongSoBenhNhanSauKhiTiepNhanThem - tongSoBenhNhanCho >= 1) {
			thuHepKhungPhongKham();
			setTestcaseStatus("PASS", "Expect : Hiển thị chính xác số lượng BN chờ khám trong ngày hiện thời");
		} else {
			thuHepKhungPhongKham();
			setTestcaseStatus("FAIL", "Expect : Hiển thị chính xác số lượng BN chờ khám trong ngày hiện thời");
		}

	}

	@Test(priority = 81)
	public void tiepNhanBenhNhan_10504_3() {
		TestLogger.info("[Phòng khám] Kiểm tra validate cột Chờ");
		TestLogger.info("Kiểm tra BN đang chờ khám hủy dịch vụ khám");
		moRongKhungPhongKham();
		// get tổng số bệnh nhân tại phòng khám tai mũi họng hiện tại khi bac si
		// chưa bỏ dịch vụ
		String SoBenhNhanCho = layTextTuAnh(1291, 123, 52, 17);

		int tongSoBenhNhanCho = Integer.valueOf(SoBenhNhanCho.trim().replace(".", "").replaceAll("\\s+", ""));

		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1234567890, 1876543211);
		dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
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

		TestLogger.info("Kiểm tra hiển thị cột Tổng số khi đã có thông tin về số lượt của BN khám trong ngày");

		TestLogger.info("Expect : Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		// get tổng số bệnh nhân tại phòng khám tai mũi họng hiện tại khi chưa
		// xóa dịch vụ
		String SoBenhNhanSauKhiChuaXoaDichVu = layTextTuAnh(1291, 123, 52, 17);

		int tongSoBenhNhanSauKhiTiepNhanThem = Integer
				.valueOf(SoBenhNhanSauKhiChuaXoaDichVu.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("==> " + tongSoBenhNhanSauKhiTiepNhanThem);
		if (tongSoBenhNhanSauKhiTiepNhanThem - tongSoBenhNhanCho == 1) {

			setTestcaseStatus("PASS", "Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		} else {

			setTestcaseStatus("FAIL", "Hiển thị chính xác số lượng BN khám trong ngày hiện thời");
		}

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		// xoa dich vu
		clickToaDo(31, 539);

		clickOn(TiepNhanBenhNhan_Luu);
		String SoBenhNhanSauKhiBoDichVu = layTextTuAnh(1291, 123, 52, 17);

		int tongSoBenhNhanSauKhiBoDichVu = Integer
				.valueOf(SoBenhNhanSauKhiBoDichVu.trim().replace(".", "").replaceAll("\\s+", ""));
		TestLogger.info("==> " + tongSoBenhNhanSauKhiBoDichVu);

		if (tongSoBenhNhanSauKhiBoDichVu == tongSoBenhNhanCho) {
			thuHepKhungPhongKham();

			setTestcaseStatus("PASS", "Cột Chờ sẽ trừ đi số lượt bệnh nhân hủy dịch vụ ");

		} else {
			thuHepKhungPhongKham();
			setTestcaseStatus("FAIL", "Cột Chờ sẽ trừ đi số lượt bệnh nhân hủy dịch vụ ");
		}

	}

	@Test(priority = 82)
	public void tiepNhanBenhNhan_10504_4() {
		
		// click để mở rộng cửa sổ phòng khám
		moRongKhungPhongKham();
		// get tổng số bệnh nhân tại phòng khám tai mũi họng hiện tại khi chưa
		// tiếp nhận thêm
		String TongSoBenhNhanKhiChuaChuyenDichVu = layTextTuAnh(1219, 123, 52, 17);

		int tongSoBenhNhan = Integer
				.valueOf(TongSoBenhNhanKhiChuaChuyenDichVu.trim().replace(".", "").replaceAll("\\s+", ""));

		// 1.HÀNH CHÍNH
		TestLogger.info("Điền Họ tên :");
		Hoten = "Nguyen Viet Ha" + TienIch.taoRandomChu(5);
		dienHoTen(Hoten);
		s.type(Key.TAB);
		TestLogger.info("Chọn giới tính Nữ");
		s.type(Key.RIGHT);

		s.type(Key.TAB);
		TestLogger.info("Chọn ngày sinh :");
		NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
		dienNgaySinh(NgaySinh);
		s.type(Key.TAB);
		s.type(Key.TAB);

		TestLogger.info("Điền số CMND :");
		// SoCMND = TienIch.taoRandomSo(9);
		SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
		dienSoCMND(SoCMND);
		s.type(Key.TAB);

		TestLogger.info("Điền số ĐIỆN THOẠI :");
		// SoDienThoai = TienIch.taoRandomSo(11);
		SoDienThoai = "1234567890";
		dienSoDienThoai(SoDienThoai);
		s.type(Key.TAB);

		TestLogger.info("Điền nghề nghiệp :");
		NgheNghiep = "Hưu Trí";
		dienNgheNghiep(NgheNghiep);
		s.type(Key.TAB);

		TestLogger.info("Điền dân tộc :");
		DanToc = "Hoa";
		dienDanToc(DanToc);
		s.type(Key.TAB);

		TestLogger.info("Điền mã nhập nhanh cho thành phố ,quận huyện :");
		dienNhapNhanh("HN1pl1qt");
		s.type(Key.TAB);

		TestLogger.info("Điền số nhà");
		SoNha = TienIch.taoRandomSo(3);
		dienSoNha(SoNha);
		s.type(Key.TAB);

		TestLogger.info("Điền nơi làm việc");
		NoiLamViec = TienIch.taoRandomChu(12);
		dienNoiLamViec(NoiLamViec);
		s.type(Key.TAB);

		TestLogger.info("Điền người liên hệ :");
		NguoiLienHe = TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(6) + " " + TienIch.taoRandomChu(3);
		dienNguoiLienHe(NguoiLienHe);
		s.type(Key.TAB);

		TestLogger.info("Điền số điện thoại người liên hệ :");
		SoDienThoaiNguoiLienHe = TienIch.taoRandomTheoGioiHan(1234567890, 1876543211);
		dienSoDienThoaiNguoiLienHe(SoDienThoaiNguoiLienHe);
		s.type(Key.TAB);

		// 2.TIẾP NHẬN
		TestLogger.info("Chọn đối tượng dịch vụ :");
		TenDichVu = "Dịch vụ";
		dienDoiTuong(TenDichVu);
		s.type(Key.TAB);

		UuTien = "Yes";
		chonUuTien(UuTien);
		s.type(Key.TAB);

		TestLogger.info("Chọn hình thức khám :");
		HinhThuc = "Tái khám";
		chonHinhThuc(HinhThuc);
		TestLogger.info("Chọn lí do khám :");
		LiDo = "Chuyển khoa";
		dienLiDo(LiDo);

		dienSoTheBHYT("");

		// 4.ĐĂNG KÍ KHÁM

		TenDichVu = "Khám tai mũi họng";
		dienTenDichVu(TenDichVu);

		sleep(3);
		NoiThucHien = "Khám TMH";
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

		waitForObjectPresent(TiepNhanBenhNhan_DanhSachBenhNhan, 4);
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
		soTiepNhan = getSoTiepNhan();

		// click len Menu Tiep Nhan
		clickOn(HisActions.HIS_MenuTiepNhan);
		clickOn(TiepNhanBenhNhan_ChuyenPhongKham);
		s.type(soTiepNhan);
		s.type(Key.ENTER);
		clickOn(FormChuyenPhongKham.FormChuyenPhongKham_Sua);
		clickToaDo(476, 217);
		s.type(Key.DOWN);
		s.type(Key.ENTER);
		sleep(1);
		clickOn(FormChuyenPhongKham.FormChuyenPhongKham_ChuyenPhongKham);
		sleep(5);
	    TestLogger.info("Mở Menu Tiếp Nhận :");
		waitForObjectPresent(HIS_MenuTiepNhan, 5);
		clickOn(HIS_MenuTiepNhan);
		waitForObjectPresent(HIS_SubMenuTiepNhanBenhNhan, 5);
		clickOn(HIS_SubMenuTiepNhanBenhNhan);
		
	}
	
	// LUC 
	@Test(priority = 83)
		public void tiepNhanBenhNhan_9933_1() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Số điện thoại");
			TestLogger.info("1.Kiểm tra giá trị mặc định Text box Số điện thoại");
			TestLogger.info("Expect : Giá trị mặc định là trống");
			if (getSoDT().length() == 0) {
				setTestcaseStatus("PASS", "Giá trị mặc định là trống");
			} else {
				setTestcaseStatus("FAILSE", "Giá trị mặc định là để trống");
			}

		}

		@Test(priority = 84)
		public void tiepNhanBenhNhan_9931_2() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Số điện thoại");
			TestLogger.info("1.Kiểm tra nhập kí tự chữ vào trường số điện thoại");
			TestLogger.info("Expect : Không cho phép nhập kí tự chữ");
			dienSoDienThoai("abcklops");
			if (getSoDT().length() == 0) {
				setTestcaseStatus("PASS", "Số điện thoại không được nhập kí tự chữ");
			} else {
				setTestcaseStatus("FALS", "Số điện thoại không được nhập kí tự chữ");
			}
		}

		@Test(priority = 85)
		public void tiepNhanBenhNhan_9931_3() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Số điện thoại");
			TestLogger.info("1.Kiểm tra nhập kí tự số vào trường số điện thoại");
			TestLogger.info("Expect : Cho phép nhập kí tự số");
			dienSoDienThoai("0987123908");
			// clickOn(TiepNhanBenhNhan_Luu);
			s.type(Key.TAB);
			if (getSoDT().equalsIgnoreCase("0987123908")) {
				setTestcaseStatus("PASS", "Số điện thoại được nhập kí tự số");
			} else {
				setTestcaseStatus("FAIL", "Số điện thoại được nhập kí tự số");
			}
		}

		@Test(priority = 86)
		public void tiepNhanBenhNhan_9931_4() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Số điện thoại");
			TestLogger.info("1.Kiểm tra nhập kí tự đặc biệt vào trường số điện thoại");
			TestLogger.info("Expect : Cho phép nhập kí tự đặc biệt");
			dienSoDT("^^^^^^^^^^");
			// clickOn(TiepNhanBenhNhan_Luu);
			s.type(Key.TAB);
			if (getSoDT().equals("^^^^^^^^^^")) {
				setTestcaseStatus("FAIL", "Cho phép nhập kí tự đặc biệt");
			} else {
				setTestcaseStatus("PASS", "Cho phép nhập kí tự đặc biệt");
			}
		}

		@Test(priority = 87)
		public void tiepNhanBenhNhan_9931_5() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Số điện thoại");
			TestLogger.info("1.Kiểm tra nhập trường số điện thoại trong khoảng 10-20 kí tự");
			TestLogger.info("Expect : Cho phép nhập thành công");
			SoDienThoai = taoRandomSo(15);
			dienSoDienThoai(SoDienThoai);
			s.type(Key.TAB);
			if (getSoDT().equals(SoDienThoai)) {
				setTestcaseStatus("PASS", "Nhập số điện thoại thành công");
			} else {
				setTestcaseStatus("FAILSE", "Nhập số điện thoại không thành công");
			}
		}

		@Test(priority = 88)
		public void tiepNhanBenhNhan_9931_6() {
			sleep(2);
			TestLogger.info("[Hành chính] Kiểm tra validate trường Số điện thoại");
			TestLogger.info("1.Kiểm tra nhập trường số điện thoại không quá 20 kí tự, chặn từ kí tự 21");
			TestLogger.info("Expect : Từ kí tự 21 trở đi không nhận giá trị");
			SoDienThoai = taoRandomSo(21);
			dienSoDienThoai(SoDienThoai);
			s.type(Key.TAB);
			if (getSoDienThoai().length() == 20) {
				TestLogger.info("==> " + getSoDienThoai().length());
				setTestcaseStatus("PASS", "Confirm từ ký tự 20 trở đi sẽ không nhận giá trị");
			} else {
				TestLogger.info("==> " + getSoDienThoai().length());
				setTestcaseStatus("FAIL", "Confirm từ ký tự 20 trở đi sẽ không nhận giá trị");
			}
		}

		@Test(priority = 89)
		public void tiepNhanBenhNhan_9931_7() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Số điện thoại");
			TestLogger.info("1.Kiểm tra nhập trường số điện thoại nhỏ hơn 10 kia tự");
			TestLogger.info("Expect : Thông báo số điện thoại không đúng định dạng");
			// Nhập họ tên
			dienHoTen("Luc TEST AUTO");
			s.type(Key.TAB);
			// Nhap ngay sinh
			sleep(2);
			s.type(Key.TAB);
			NgaySinh = TienIch.getNamHienTaicuaMayTinh();
			dienNgaySinh(NgaySinh);
			s.type(Key.TAB);
			s.type(Key.TAB);

			TestLogger.info("Điền số CMND :");
			// SoCMND = TienIch.taoRandomSo(9);
			SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
			dienSoCMND(SoCMND);
			s.type(Key.TAB);
			// Điền số điện thoại
			SoDienThoai = taoRandomSo(9);
			dienSoDienThoai(SoDienThoai);
			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageSDTNhoHon10, 10)) {
				s.type(Key.ENTER);
				setTestcaseStatus("PASS", "Số điện thoại không đúng định dạng");
			} else {
				setTestcaseStatus("FAILSE", "Số điện thoại không đúng định dạng");
			}

		}

		@Test(priority = 90)
		public void tiepNhanBenhNhan_9931_8() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Số điện thoại");
			TestLogger.info("1.Kiểm tra khi không nhập trường số điện thoại");
			TestLogger.info("Expect :Cho phép lưu thành công");
			// dien ho ten
			dienHoTen(Hoten);
			s.type(Key.TAB);
			TestLogger.info("Chọn Giới tính Nữ");
			s.type(Key.RIGHT);
			// dien ngày sinh
			s.type(Key.TAB);
			TestLogger.info("Chọn ngày sinh :");
			NgaySinh = TienIch.getNgayThangNamHienTaicuaMayTinh();
			dienNgaySinh(NgaySinh);
			s.type(Key.TAB);
			s.type(Key.TAB);
			// điền căn cước công dân
			TestLogger.info("Điền số CCCD");
			SoCMND = TienIch.taoRandomTheoGioiHan(100000000, 999999999);
			dienSoCMND(SoCMND);
			s.type(Key.TAB);
			// Không điền số điện thoại
			dienSoDienThoai("");
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
			// chon khám dịch vụTestLogger.info("Chọn đối tượng dịch vụ :");
			TenDichVu = "Dịch vụ";
			dienDoiTuong(TenDichVu);
			s.type(Key.TAB);
			UuTien = "Yes";
			chonUuTien(UuTien);
			s.type(Key.TAB);

			TestLogger.info("Chọn hình thức khám :");
			HinhThuc = "Tái khám";
			chonHinhThuc(HinhThuc);
			TestLogger.info("Chọn lí do khám :");
			LiDo = "Chuyển khoa";
			dienLiDo(LiDo);

			dienSoTheBHYT("");

			// ĐĂNG KÍ KHÁM

			TenDichVu = "Khám tai mũi họng";
			dienTenDichVu(TenDichVu);

			sleep(3);
			NoiThucHien = "Khám TMH";
			dienNoiThucHien(NoiThucHien);
			s.type(Key.TAB);

			sleep(3);
			ThuTienSau = "Yes";
			chonThuTienSau(ThuTienSau);

			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(Phieukham, 5)) {
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
				setTestcaseStatus("PASS", "Lưu thông tin thành công");
			} else {
				setTestcaseStatus("FAILSE", "LƯU THÔNG TIN KHÔNG THÀNH CÔNG");
			}
		}

		@Test(priority = 91)
		public void tiepNhanBenhNhan_10092_1() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã thẻ BHYT");
			TestLogger.info("1.Kiểm tra giá trị mặc định trường mã thẻ BHYT");
			TestLogger.info("Expect :Mặc định là null");
			if (getSoTheBHYT().length() == 0) {
				setTestcaseStatus("PASS", "Giá trị mặc định là trống");
			} else {
				setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
			}
		}

		@Test(priority = 92)
		public void tiepNhanBenhNhan_10092_2_3() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã thẻ BHYT");
			TestLogger.info("1.Kiểm tra khi nhập kí tự số và chữ  trường mã thẻ BHYT");
			TestLogger.info("Expect :Cho phép nhập kí tự số và chữ");
			dienSoTheBHYT("SV4112345");
			if (getSoTheBHYT().equalsIgnoreCase("SV4112345")) {
				setTestcaseStatus("PASS", "Cho phép nhập kí tự chữ và số");
			} else {
				setTestcaseStatus("FAIL", "Cho phép nhập kí tự chữ và số");
			}
		}

		@Test(priority = 93)
		public void tiepNhanBenhNhan_10092_4() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã thẻ BHYT");
			TestLogger.info("1.Kiểm tra khi nhập kí tự đặc biệt trường mã thẻ BHYT");
			TestLogger.info("Expect :Không cho phép nhập kí tự đặc biệt");
			dienSoTheBHYT("@@@@");
			if (getSoTheBHYT().equals("@@@@")) {
				setTestcaseStatus("FAIL", "Không Cho phép nhập kí tự đặc biệt");
			} else {
				setTestcaseStatus("PASS", "Không Cho phép nhập kí tự đặc biệt");
			}
		}

		@Test(priority = 94)
		public void tiepNhanBenhNhan_10092_6() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã thẻ BHYT");
			TestLogger.info("1.Kiểm tra không cho phép nhập quá 15 kí tự trường mã thẻ BHYT");
			TestLogger.info("Expect :Không cho phép nhập quá 15 kí tự, cắt từ kí tự 16");
			sotheBHYT = "SV412" + TienIch.taoRandomSo(11);
			dienSoTheBHYT(sotheBHYT);
			if (getSoTheBHYT().length() == 15) {
				TestLogger.info("==> " + getSoTheBHYT().length());
				setTestcaseStatus("PASS", "Confirm từ ký tự 16 trở đi sẽ không nhận giá trị");
			} else {
				TestLogger.info("==> " + getSoTheBHYT().length());
				setTestcaseStatus("FAIL", "Confirm từ ký tự 16 trở đi sẽ không nhận giá trị");
			}
		}

		@Test(priority = 95)
		public void tiepNhanBenhNhan_10092_7_9_11_13() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã thẻ BHYT");
			TestLogger.info("1.Kiểm tra nhập 15 kí tự trường mã thẻ BHYT, không phân biệt hoa/thường...");
			TestLogger.info("Expect :Cho phép nhập 15 kí tự");
			sotheBHYT = "sV412" + TienIch.taoRandomSo(10);
			dienSoTheBHYT(sotheBHYT);
			if (getSoTheBHYT().equalsIgnoreCase(sotheBHYT)) {
				setTestcaseStatus("PASS", "cho phép nhập mã thẻ BHYT");
			} else {
				setTestcaseStatus("FAIL", "Cho phép nhập mã thẻ BHYT");
			}
		}

		@Test(priority = 96)
		public void tiepNhanBenhNhan_10092_12() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã thẻ BHYT");
			TestLogger.info("1.Kiểm tra nhập kí tự 3 và 4 không phải là mã tỉnh thành ");
			TestLogger.info("Expect :Thông báo lỗi");
			sotheBHYT = "SV423" + TienIch.taoRandomSo(10);
			dienSoTheBHYT(sotheBHYT);
			if (waitForObjectPresent(TiepNhanBenhNhan_LoiBHYT, 5)) {
				s.type(Key.ENTER);
				setTestcaseStatus("PASS", "Mã thẻ BHYT sai, mời nhập lại");
			} else {
				setTestcaseStatus("FAIL", "Mã thẻ BHYT sai, mời nhập lại");
			}
		}

		
		@Test(priority = 97)
		public void tiepNhanBenhNhan_10123_2_7() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã ĐKKCB");
			TestLogger.info("1.Kiểm tra nhập giá trị số trường mã ĐKKCB");
			TestLogger.info("Expect :Cho phép nhập kí tự số");
			MaDKKCB = "01005";
			dienMaDKKCB(MaDKKCB);
			if (getMaDKKCB().equals("01005")) {
				setTestcaseStatus("PASS", "Cho phép nhập kí tự số");
			} else {
				setTestcaseStatus("FAIL", "Cho phép nhập kí tự số");
			}
		}

		@Test(priority = 98)
		public void tiepNhanBenhNhan_10123_3() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã ĐKKCB");
			TestLogger.info("1.Kiểm tra nhập giá trị chữ trường mã ĐKKCB");
			TestLogger.info("Expect :Cho phép nhập kí tự chữ");
			MaDKKCB = "ALOP";
			dienMaDKKCB(MaDKKCB);
			if (getMaDKKCB().equals("ALOP")) {
				setTestcaseStatus("PASS", "Cho phép nhập kí tự số");
			} else {
				setTestcaseStatus("FAIL", "Cho phép nhập kí tự số");
			}
		}

		@Test(priority = 99)
		public void tiepNhanBenhNhan_10123_4() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã ĐKKCB");
			TestLogger.info("1.Kiểm tra nhập kí tự đặc biệt trường mã ĐKKCB");
			TestLogger.info("Expect :Không Cho phép nhập kí tự đặc biệt");
			MaDKKCB = "@@@@";
			dienMaDKKCB(MaDKKCB);
			if (getMaDKKCB().equals("@@@@")) {
				setTestcaseStatus("FAIL", "Không cho phép nhập kí tự đặc biệt");
			} else {
				setTestcaseStatus("PASS", "Không Cho phép nhập kí tự đặc biệt");
			}
		}

		@Test(priority = 100)
		public void tiepNhanBenhNhan_10123_5_6() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã ĐKKCB");
			TestLogger.info("1.Kiểm tra không cho phép nhập quá 5 kí tự trường mã ĐKKCB");
			TestLogger.info("Expect :Không cho phép nhập quá 5 kí tự, cắt từ kí tự 6");
			MaDKKCB = TienIch.taoRandomSo(6);
			dienMaDKKCB(MaDKKCB);
			if (getMaDKKCB().length() == 5) {
				TestLogger.info("==> " + getMaDKKCB().length());
				setTestcaseStatus("PASS", "Confirm từ ký tự 6 trở đi sẽ không nhận giá trị");
			} else {
				TestLogger.info("==> " + getMaDKKCB().length());
				setTestcaseStatus("FAIL", "Confirm từ ký tự 6 trở đi sẽ không nhận giá trị");
			}
		}

		@Test(priority = 101)
		public void tiepNhanBenhNhan_10123_8() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường mã ĐKKCB");
			TestLogger.info("1.Kiểm tra khi nhập mã ĐKKCB không nằm trong thiết lập hệ thống");
			TestLogger.info("Expect :Hiển thị hình thức cơ quan y tế giới thiệu");
			dienSoTheBHYT("SV4129081901287");
			s.type(Key.TAB);
			dienMaDKKCB("19823");
			if (waitForObjectPresent(TiepNhanBenhNhan_TiepNhanGioiThieu, 5)) {
				setTestcaseStatus("PASS", "Hiển thị thành công");
			} else {
				setTestcaseStatus("FAIL", "Hiển thị không thành công");
			}
		}

		@Test(priority = 102)
		public void tiepNhanBenhNhan_10128_1() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường từ ngày ");
			TestLogger.info("1.Kiểm tra giá trị mặc định trường từ ngày");
			TestLogger.info("Expect :giá trị mặc định là NULL");
			if (getTuNgay().length() == 0) {
				setTestcaseStatus("PASS", "Giá trị mặc định là trống");
			} else {
				setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
			}
		}

		@Test(priority = 103)
		public void tiepNhanBenhNhan_10128_3() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường từ ngày ");
			TestLogger.info("1.Kiểm tra bỏ trống trường từ ngày khi đã nhập số thẻ");
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
			s.type(Key.ENTER);

			TestLogger.info("Điền hình thức khám");
			HinhThuc = "Tự đến";
			chonHinhThuc(HinhThuc);
			s.type(Key.TAB);
			sleep(1);
			s.type(Key.TAB);
			sleep(1);
			s.type(Key.TAB);
			sleep(1);

			sleep(4);
			TestLogger.info("chon khu vuc");
			s.type(Key.DOWN);
			// s.type(Key.DOWN);
			s.type(Key.ENTER);

			TestLogger.info("Chon tu ngay");
			dienTuNgay("");
			// s.type(Key.ENTER);
			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				s.type(Key.ENTER);
				setTestcaseStatus("PASS", "Trường từ ngày không được để trống");
			} else {
				setTestcaseStatus("FAIL", "Trường từ ngày không được để trống");
			}
		}

		@Test(priority = 104)
		public void tiepNhanBenhNhan_10128_5() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường từ ngày ");
			TestLogger.info("1.Kiểm tra trường từ ngày lớn hơn ngày hiện tại");
			TestLogger.info("Expect :Hiển thị message lỗi");
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
			s.type(Key.ENTER);

			TestLogger.info("Điền hình thức khám");
			HinhThuc = "Tự đến";
			chonHinhThuc(HinhThuc);
			s.type(Key.TAB);
			sleep(1);
			s.type(Key.TAB);
			sleep(1);
			s.type(Key.TAB);
			sleep(1);

			sleep(4);
			TestLogger.info("chon khu vuc");
			s.type(Key.DOWN);
			// s.type(Key.DOWN);
			s.type(Key.ENTER);

			TestLogger.info("Chon tu ngay");
			dienTuNgay("30/12/2018");
			// s.type(Key.ENTER);

			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				s.type(Key.ENTER);
				setTestcaseStatus("PASS", "Trường từ ngày phải nhỏ hơn ngày hiện tại");
			} else {
				setTestcaseStatus("FAIL", "Trường từ ngày phải nhỏ hơn ngày hiện tại");
			}
		}

		@Test(priority = 105)
		public void tiepNhanBenhNhan_10130_1() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường đến ngày ");
			TestLogger.info("1.Kiểm tra giá trị mặc định trường đến ngày");
			TestLogger.info("Expect :Giá trị mặc định là NULL");
			if (getDenNgay().length() == 0) {
				setTestcaseStatus("PASS", "Giá trị mặc định là trống");
			} else {
				setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
			}

		}

		@Test(priority = 106)
		public void tiepNhanBenhNhan_10130_5() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường đến ngày ");
			TestLogger.info("1.Kiểm tra bỏ trống trường đến ngày khi đã nhập số thẻ");
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
			s.type(Key.ENTER);

			TestLogger.info("Điền hình thức khám");
			HinhThuc = "Tự đến";
			chonHinhThuc(HinhThuc);
			s.type(Key.TAB);
			sleep(2);
			s.type(Key.TAB);
			sleep(2);
			s.type(Key.TAB);
			sleep(2);

			sleep(4);
			TestLogger.info("chon khu vuc");
			s.type(Key.DOWN);
			// s.type(Key.DOWN);
			s.type(Key.ENTER);

			TestLogger.info("Chon tu ngay");
			dienTuNgay("12082017");
			s.type(Key.ENTER);

			TestLogger.info("Chọn đến ngày");
			dienDenNgay("12012017");
			sleep(2);
			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				s.type(Key.ENTER);
				setTestcaseStatus("PASS", "Trường đến ngày không được để trống");
			} else {
				setTestcaseStatus("FAIL", "Trường đến ngày không được để trống");
			}

		}

		@Test(priority = 107)
		public void tiepNhanBenhNhan_10130_2() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường đến ngày ");
			TestLogger.info("1.Kiểm tra bỏ trống trường đến ngày khi đã nhập số thẻ");
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
			s.type(Key.ENTER);

			TestLogger.info("Điền hình thức khám");
			HinhThuc = "Tự đến";
			chonHinhThuc(HinhThuc);
			s.type(Key.TAB);
			sleep(2);
			s.type(Key.TAB);
			sleep(2);
			s.type(Key.TAB);
			sleep(2);

			sleep(4);
			TestLogger.info("chon khu vuc");
			s.type(Key.DOWN);
			// s.type(Key.DOWN);
			s.type(Key.ENTER);

			TestLogger.info("Chon tu ngay");
			dienTuNgay("12082017");
			s.type(Key.ENTER);

			TestLogger.info("Chọn đến ngày");
			dienDenNgay("");
			sleep(2);
			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				s.type(Key.ENTER);
				setTestcaseStatus("PASS", "Trường đến ngày không được để trống");
			} else {
				setTestcaseStatus("FAIL", "Trường đến ngày không được để trống");
			}
		}

		@Test(priority = 108)
		public void tiepNhanBenhNhan_10130_6() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường đến ngày ");
			TestLogger.info("1.Kiểm tra nhập đến ngày < ngày hiện tại");
			TestLogger.info("Expect :Hiển thị message lỗi");
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
			s.type(Key.ENTER);

			TestLogger.info("Điền hình thức khám");
			HinhThuc = "Tự đến";
			chonHinhThuc(HinhThuc);
			s.type(Key.TAB);
			sleep(2);
			s.type(Key.TAB);
			sleep(2);
			s.type(Key.TAB);
			sleep(2);

			sleep(4);
			TestLogger.info("chon khu vuc");
			s.type(Key.DOWN);
			// s.type(Key.DOWN);
			s.type(Key.ENTER);

			TestLogger.info("Chon tu ngay");
			dienTuNgay("12082017");
			s.type(Key.ENTER);

			TestLogger.info("Chọn đến ngày");
			dienDenNgay("14122017");
			sleep(2);
			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				s.type(Key.ENTER);
				setTestcaseStatus("PASS", "Thẻ BHYT đã hết hạn");
			} else {
				setTestcaseStatus("FAIL", "Thẻ BHYT đã hết hạn");
			}
		}

		@Test(priority = 109)
		public void tiepNhanBenhNhan_10154_1() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường ngày đủ 5 năm");
			TestLogger.info("1.Kiểm tra giá trị mặc định trường ngày đủ 5 năm");
			TestLogger.info("Expect :Mặc định là null");
			if (getNgayDu5Nam().length() == 0) {
				setTestcaseStatus("PASS", "Giá trị mặc định là trống");
			} else {
				setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
			}
		}

		@Test(priority = 110)
		public void tiepNhanBenhNhan_10154_2() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường ngày đủ 5 năm ");
			TestLogger.info("1.Kiểm tra để trống trường ngày đủ 5 năm");
			TestLogger.info("Expect :Cho phép để trống, lưu thành công");
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
			s.type(Key.ENTER);

			TestLogger.info("Điền hình thức khám");
			HinhThuc = "Tự đến";
			chonHinhThuc(HinhThuc);
			s.type(Key.TAB);
			sleep(2);
			s.type(Key.TAB);
			sleep(2);
			s.type(Key.TAB);
			sleep(2);

			sleep(4);
			TestLogger.info("chon khu vuc");
			s.type(Key.DOWN);
			// s.type(Key.DOWN);
			s.type(Key.ENTER);

			TestLogger.info("Chon tu ngay");
			dienTuNgay("12082017");
			s.type(Key.ENTER);

			TestLogger.info("Chọn đến ngày");
			dienDenNgay("30122017");
			sleep(2);

			TestLogger.info("Chọn ngày đủ 5 năm");
			dienNgayDu5Nam("");
			sleep(2);
			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(Phieukham, 5)) {
				s.type(Key.ENTER);
				setTestcaseStatus("PASS", "Đăng Kí thành công");
			} else {
				setTestcaseStatus("FAIL", "Đăng kí không thành công");
			}
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

			// Điền mã thẻ BHYT
			TestLogger.info("Điền số thẻ BHYT 80");
			sotheBHYT = "SV491" + taoRandomSo(10);
			dienSoTheBHYT(sotheBHYT);
			s.type(Key.ENTER);
			TestLogger.info("Dien ma dang ki KCBBD");
			dienMaDKKCB("01005");
			s.type(Key.ENTER);

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

			// Điền mã thẻ BHYT
			TestLogger.info("Điền số thẻ BHYT 80");
			sotheBHYT = "SV491" + taoRandomSo(10);
			dienSoTheBHYT(sotheBHYT);
			s.type(Key.ENTER);
			TestLogger.info("Dien ma dang ki KCBBD");
			dienMaDKKCB("01005");
			s.type(Key.ENTER);

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

			// Điền mã thẻ BHYT
			TestLogger.info("Điền số thẻ BHYT 80");
			sotheBHYT = "SV491" + taoRandomSo(10);
			dienSoTheBHYT(sotheBHYT);
			s.type(Key.ENTER);
			TestLogger.info("Dien ma dang ki KCBBD");
			dienMaDKKCB("01005");
			s.type(Key.ENTER);

			// Điền trường chẩn đoán NGT vượt quá 100 kí tự
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

			// Điền mã thẻ BHYT
			TestLogger.info("Điền số thẻ BHYT 80");
			sotheBHYT = "SV491" + taoRandomSo(10);
			dienSoTheBHYT(sotheBHYT);
			s.type(Key.ENTER);
			TestLogger.info("Dien ma dang ki KCBBD");
			dienMaDKKCB("01005");
			s.type(Key.ENTER);

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

			if (waitForObjectPresent(Phieukham, 15)) {
				s.type(Key.F4, Key.ALT);
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
				setTestcaseStatus("PASS", "Cho phép để trống trường NGT!");
			} else {

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
			sleep(2);
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
			dienQuanHuyen("TX");
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

			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(Phieukham, 15)) {
				setTestcaseStatus("PASS", "Tiếp nhận bệnh nhân thành công");
			} else {
				setTestcaseStatus("FAIL", "Tiếp nhận bệnh nhân không thành công");
			}
		}

		@Test(priority = 124)
		public void tiepNhanBenhNhan_10270_1() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi thực hiện ");
			TestLogger.info("1.Kiểm tra giá trị mặc định trường Nơi thực hiện");
			TestLogger.info("Expect :Giá trị mặc định là để trống");

			if (getNoiThucHien().length() == 0) {
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
				setTestcaseStatus("PASS", "lấy ra nơi thực hiện thành công");
			} else {
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
			clickToaDo(217, 221);
			sleep(2);
			// click tọa độ chọn một nghề nghiệp ---Sinh Viên----
			clickToaDo(130, 308);
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
			if (waitForObjectPresent(Phieukham, 15)) {
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
				setTestcaseStatus("PASS", "Tiếp nhận bệnh nhân thành công");
			} else {
				setTestcaseStatus("FAIL", "Tiếp nhận bệnh nhân không thành công");
			}
		}

		@Test(priority = 131)
		public void tiepNhanBenhNhan_10080_1() {
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
		public void tiepNhanBenhNhan_10080_2() {
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

			sleep(2);
			clickOn(TiepNhanBenhNhan_Luu);

			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				setTestcaseStatus("PASS", "Không cho phép để trống");
			} else {
				setTestcaseStatus("FAIL", "Không cho phép để trống");
			}
		}

		@Test(priority = 133)
		public void tiepNhanBenhNhan_10080_5() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Lý do");
			TestLogger.info("1.Kiểm tra chọn 1 giá trị trong combox trường lý do");
			TestLogger.info("Expect :Cho phép bỏ trống trường lý do");

			// Click tọa độ lý do
			clickToaDo(793, 275);
			sleep(2);
			// click chọn lý do ---- TN 011-----
			clickToaDo(686, 340);

			if (getLiDo().equalsIgnoreCase("TN 011")) {
				setTestcaseStatus("PASS", "Chọn lý do thành công");
			} else {
				setTestcaseStatus("FAIL", "Chọn lý do không thành công");
			}
		}

		@Test(priority = 134)
		public void tiepNhanBenhNhan_10080_6() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Lý do");
			TestLogger.info("1.Kiểm tra nhập một giá trị trong trường lý do");
			TestLogger.info("Expect :Cho phép nhập trường lý do");
			dienLiDo("TN 011");
			sleep(1);
			if (getLiDo().equalsIgnoreCase("TN 011")) {
				setTestcaseStatus("PASS", "Chọn lý do thành công");
			} else {
				setTestcaseStatus("FAIL", "Chọn lý do không thành công");
			}
		}

		@Test(priority = 135)
		public void tiepNhanBenhNhan_9915_1() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường năm sinh");
			TestLogger.info("1.Kiểm tra giá trị mặc định trường năm sinh");
			TestLogger.info("Expect :Giá trị mặc định là để trống");

			if (getNamSinh().length() == 0) {
				setTestcaseStatus("PASS", "Giá trị mặc định là trống");
			} else {
				setTestcaseStatus("FAIL", "Giá trị mặc định là trống");
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
			// điền năm sinh <1877
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
			if (getNamSinh().equals("1993")) {
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
			if (getPhuongXa().length() == 0) {
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

			clickOn(TiepNhanBenhNhan_Luu);

			if (waitForObjectPresent(Phieukham, 15)) {
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
				setTestcaseStatus("PASS", "Trường phường xã được phép để trống");
			} else {
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
			TestLogger.info("1.Kiểm tra chọn 1 giá trị trong combobox trường phường xã ");
			TestLogger.info("Expect :Cho phép chọn trong combox trường phường xã ");
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
			dienNoiGioiThieu("Bệnh viện thanh nhàn");
			if (getNoiGioiThieu().equalsIgnoreCase("Bệnh viện thanh nhàn")) {
				setTestcaseStatus("FAIL", " Không Disable trường Nơi giới thiệu và cho phép nhập");
			} else {
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
			TestLogger.info("Expect :Disable trường Nơi giới thiệu và không cho phép nhập");
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
			dienNoiGioiThieu("");
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

			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				setTestcaseStatus("PASS", "Trường nơi giới thiệu không được để trống");
			} else {
				setTestcaseStatus("FAIL", "Trường nơi giới thiệu không được để trống");
			}

		}

		@Test(priority = 151)
		public void tiepNhanBenhNhan_10164_6() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Nơi giới thiệu");
			TestLogger.info("1.Kiểm tra chọn một giá trị trong combox trường Nơi giới thiệu");
			TestLogger.info("Expect :Chọn 1 giá trị trong combobox thành công");

			TestLogger.info("Điền đối tượng");
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Cơ quan y tế giới thiệu");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);

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
			TestLogger.info("Điền đối tượng");
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Cơ quan y tế giới thiệu");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);

			dienNoiGioiThieu("Bệnh viện Thanh Nhàn11111");
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
			TestLogger.info("Điền đối tượng");
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Cơ quan y tế giới thiệu");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);

			// click tọa độ nơi giới thiệu
			clickToaDo(1011, 328);
			if (waitForObjectPresent(TiepNhanBenhNhan_KiemTraCotNoiGioiThieu, 5)) {
				setTestcaseStatus("PASS", "Load lên mã BHYT, tên cơ sở khám chữa bệnh");
			} else {
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

			dienTuyenChuyen("Tuyến dưới liền kề");
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
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Tự đến");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);

			dienTuyenChuyen("Tuyến dưới liền kề");
			if (getTuyenChuyen().equalsIgnoreCase("Tuyến dưới liền kề")) {
				setTestcaseStatus("FAIL", "Không Disable trường Tuyến chuyển và không cho phép chọn");
			} else {
				setTestcaseStatus("PASS", "Disable trường Tuyến chuyển và không cho phép chọn");
			}
		}

		@Test(priority = 156)
		public void tiepNhanBenhNhan_10167_3() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Tuyến chuyển");
			TestLogger.info(
					"1.Kiểm tra mặc định trường Tuyến chuyển khi chọn đối tượng là BHYT + Hình thức đến khám là Cơ quan Y tế giới thiệu");
			TestLogger.info("Expect :Enable trường Tuyến chuyển, và mặc định giá trị trong combobox là Blank");
			TestLogger.info("Điền đối tượng");
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Cơ quan y tế giới thiệu");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);
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
			dienTuNgay("30122016");
			s.type(Key.ENTER);

			TestLogger.info("Chon den ngay");
			dienDenNgay("30122018");
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
			dienTuyenChuyen("");
			s.type(Key.TAB);

			TestLogger.info("Nhap ly do chuyen");
			dienLiDoChuyen("Đủ điều kiện chuyển tuyến (đúng tuyến)");
			s.type(Key.TAB);

			TestLogger.info("Nhap chan doan NGT");
			dienChanDoanNGT("Đau đầu");
			s.type(Key.TAB);

			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
				setTestcaseStatus("PASS", "Trường nơi giới thiệu không được để trống");
			} else {
				setTestcaseStatus("FAIL", "Trường nơi giới thiệu không được để trống");
			}
		}

		@Test(priority = 158)
		public void tiepNhanBenhNhan_10167_7() {
			TestLogger.info("[Hành chính] Kiểm tra validate trường Tuyến chuyển");
			TestLogger.info("1.Kiểm tra chọn một giá trị trong combox Tuyến chuyển");
			TestLogger.info("Expect :Chọn 1 giá trị trong combobox thành công");

			TestLogger.info("Điền đối tượng");
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Cơ quan y tế giới thiệu");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);

			// click tọa độ trường nơi giới thiệu
			clickToaDo(1012, 387);
			// click tọa độ chọn một trường trong nơi giới thiệu---Tuyến dưới liền kề---
			clickToaDo(713, 436);
			if (getNoiGioiThieu().equalsIgnoreCase("Tuyến dưới liền kề")) {
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

			TestLogger.info("Điền đối tượng");
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Cơ quan y tế giới thiệu");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);

			dienTuyenChuyen("Tuyến dưới liền kề");
			if (getNoiGioiThieu().equalsIgnoreCase("Tuyến dưới liền kề")) {
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

			TestLogger.info("Điền đối tượng");
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Tự đến");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);
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

			TestLogger.info("Điền đối tượng");
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);

			s.type(Key.TAB);
			sleep(5);
			s.type(Key.TAB);

			TestLogger.info("Điền hình thức khám");
			chonHinhThuc("Cơ quan y tế giới thiệu");
			TestLogger.info("Chon lý do khám");
			LiDo = "Khám bệnh";
			dienLiDo(LiDo);
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
			dienTuNgay("30122016");
			s.type(Key.ENTER);

			TestLogger.info("Chon den ngay");
			dienDenNgay("30122018");
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
			s.type(Key.TAB);

			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
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
			dienTuNgay("30122016");
			s.type(Key.ENTER);

			TestLogger.info("Chon den ngay");
			dienDenNgay("30122018");
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
			s.type(Key.TAB);

			clickOn(TiepNhanBenhNhan_Luu);
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
			dienTuNgay("30122016");
			s.type(Key.ENTER);

			TestLogger.info("Chon den ngay");
			dienDenNgay("30122018");
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
			dienNgayChuyen("01012020");
			s.type(Key.TAB);

			if (getNgayChuyen().equalsIgnoreCase(ngayHienTai)) {

				setTestcaseStatus("PASS", "lấy ngày hiện tại chính xác");
			} else {

				setTestcaseStatus("FAIL", "Lấy ngày hiện tại không chính xác");
			}
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
			if (getTuyen().equals("")) {
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

			if (waitForObjectPresent(TiepNhanBenhNhan_TuyenDungTuyen, 5)) {
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
				setTestcaseStatus("PASS", "Mặc định là Đúng tuyến");
			} else {
				setTestcaseStatus("FAIL", "Mặc định là Đúng tuyến");
			}
		}

		@Test(priority = 168)
		public void tiepNhanBenhNhan_14793_2() {
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
			if (waitForObjectPresent(Phieukham, 10)) {
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
			sleep(1);
			dienSoTheBHYT(sotheBHYT);
			sleep(1);
			dienMaDKKCB("01005");
			waitForObjectPresent(TiepNhanBenhNhan_DanhSachCungMaBHYT, 5);
			clickOn(TiepNhanBenhNhan_DanhSachCungMaBHYTChon);
			s.type(Key.ENTER);
			sleep(5);
			chonHinhThuc("Tự đến");
			sleep(2);
			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
				setTestcaseStatus("PASS", "không thể tiếp nhận lần này là đối tượng BHYT ");
			} else {
				setTestcaseStatus("FAIL", "Tiếp nhận đang thành công");
			}
		}

		@Test(priority = 169)
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
			if (waitForObjectPresent(Phieukham, 10)) {
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
			sleep(1);
			dienSoTheBHYT(sotheBHYT);
			sleep(1);
			dienMaDKKCB("01005");
			waitForObjectPresent(TiepNhanBenhNhan_DanhSachCungMaBHYT, 5);
			clickOn(TiepNhanBenhNhan_DanhSachCungMaBHYTChon);
			s.type(Key.ENTER);
			sleep(5);
			dienDoiTuong("Dịch vụ");
			sleep(2);
			chonHinhThuc("Tự đến");
			sleep(2);
			clickOn(TiepNhanBenhNhan_Luu);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				setTestcaseStatus("FAIL", "Không Cho Lưu và hiển thị cảnh báo  ");
			} else {
				clickOn(TiepNhanBenhNhan_BoQua);
				s.type(Key.ENTER);
				clickOn(TiepNhanBenhNhan_Them);
				setTestcaseStatus("PASS", "Cho Lưu và không hiển thị cảnh báo ");

			}
		}

		@Test(priority = 170)
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
			// ---------------Mã bệnh nhân 17001224-------------------
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
			sleep(3);
			clickOn(TiepNhanBenhNhan_Sua);
			if (waitForObjectPresent(TiepNhanBenhNhan_MessageLoi, 5)) {
				clickOn(TiepNhanBenhNhan_BoQua);
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
			clickOn(TiepNhanBenhNhan_HeThong);
			clickOn(TiepNhanBenhNhan_DangNhap);
			dangNhapHIS(FormKhuVuc.ten_dangNhap_BS01, FormKhuVuc.matKhau_dangNhap_BS01);
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
			clickOn(TiepNhanBenhNhan_HeThong);
			clickOn(TiepNhanBenhNhan_DangNhap);
			dangNhapHIS(FormKhuVuc.ten_dangNhap_BS01, FormKhuVuc.matKhau_dangNhap_BS01);
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
				setTestcaseStatus("PASS", "Load ra thông tin cập nhật mới nhất");
			} else {
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
			sleep(2);
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
			clickOn(TiepNhanBenhNhan_HeThong);
			clickOn(TiepNhanBenhNhan_DangNhap);
			dangNhapHIS(FormKhuVuc.ten_dangNhap_BS01, FormKhuVuc.matKhau_dangNhap_BS01);
			chonPhongLamViec("Khám theo yêu cầu");
			sleep(2);
			clickOn(HIS_MenuTiepNhan);
			waitForObjectPresent(HIS_SubMenuTiepNhanBenhNhan, 5);
			clickOn(HIS_SubMenuTiepNhanBenhNhan);
			waitForObjectPresent(TiepNhanBenhNhan.AfterLoading, 15);
			// sleep(2);
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

			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);
			s.type(Key.ENTER);

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
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);
			clickToaDo(1032, 382);
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
				setTestcaseStatus("PASS", "Mặc định giá trị trống, không cho phép nhập");
			} else {
				setTestcaseStatus("FAIL", "Mặc định Khác giá trị trống, không cho phép nhập");
			}
		}

		@Test(priority = 192)
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

			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);
			s.type(Key.ENTER);

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
			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);
			s.type(Key.ENTER);

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

			DoiTuong = "BHYT 80%";
			clickToaDo(218, 277);
			waitForObjectPresent(TiepNhanBenhNhan_BHYT80, 5);
			clickOn(TiepNhanBenhNhan_BHYT80);
			s.type(Key.ENTER);

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
			dienTenDichVu2("Khám tiêu hóa");
			s.type(Key.ENTER);
			sleep(2);
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

	@AfterTest
	public void ketThucLuong() {
		dangXuatKhoiTaikhoan();
		thoatUngdungHIS();
	}

}
