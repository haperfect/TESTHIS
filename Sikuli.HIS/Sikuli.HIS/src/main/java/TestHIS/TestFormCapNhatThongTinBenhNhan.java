package TestHIS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sikuli.script.Key;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HIS.FormCapNhatThongTinHanhChinh;
import HIS.FormKhuVuc;
import desktop_Framework.HisActions;
import desktop_Framework.KiemSoatDatabase;
import desktop_Framework.TestLogger;
import desktop_Framework.TienIch;

public class TestFormCapNhatThongTinBenhNhan extends FormCapNhatThongTinHanhChinh {
	public String soTN, HoTen1, ngaySinh1, namSinh1, soCMND1, soDT1, QuocTich1, Thanhpho1, QuanHuyen1, PhuongXa1,
			SoNha1, NoiLamViec1, NguoiLienHe1, SoDTnguoiLienHe1;
	public String NgheNghiep1;

	HisActions his = new HisActions();

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
	public void capNhatThongTin_9864_1_1() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Số TN");
		TestLogger.info("Kiểm tra giá trị mặc định Khi chưa có thông tin BN từ màn hình Tiếp nhận");
		TestLogger.info("thông tin trên Màn hình Cập nhật thông tin HC là NULL");
		s.type("u", Key.CTRL);
		if (getSoTiepNhan1().equals("")) {
			// s.type(Key.F4, Key.ALT);
			setTestcaseStatus("PASS", "thông tin trên Màn hình Cập nhật thông tin HC là NULL");

		} else {
			// s.type(Key.F4, Key.ALT);
			setTestcaseStatus("FAIL", "thông tin trên Màn hình Cập nhật thông tin HC # NULL");
		}
	}

	@Test(priority = 2)
	public void capNhatThongTin_9864_2() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Số TN");
		TestLogger.info("Kiểm tra việc nhập trong khoảng maxlenght 10 ký tự số vào trường STN");
		TestLogger.info("Confirm sau khi nhấn Enter hệ thống sẽ hiển thị thông tin của bệnh nhân đó");
		// BN NGUYEN VIET HAOASCR có stn: 1801000115
		// MaBN: 18000100
		soTN = "1801000115";
		dienSoTiepNhan1(soTN);
		s.type(Key.ENTER);
		sleep(2);
		if (getHoTen1().equalsIgnoreCase("NGUYEN VIET HAOASCR")) {
			setTestcaseStatus("PASS", "Hiển thị tên bệnh nhân thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị tên bệnh nhân không thành công");
		}
		if (getNgaySinh1().equalsIgnoreCase("10/01/2018")) {
			setTestcaseStatus("PASS", "Hiển thị Ngày sinh bệnh nhân thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị ngày sinh bệnh nhân Không thành công");
		}
		if (getSoCMND1().equalsIgnoreCase("217944680")) {
			setTestcaseStatus("PASS", "Hiển thị số chứng minh nhân dân đúng");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị số chứng minh nhân dân sai");
		}
		if (getQuocTich1().equalsIgnoreCase("Việt Nam 1")) {
			setTestcaseStatus("PASS", "Hiển thị quốc tịch đúng");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị quốc tịch sai");
		}
		if (getDanToc1().equalsIgnoreCase("Hoa")) {
			setTestcaseStatus("PASS", "Hiển thị dân tộc thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị dân tộc Không thành công");
		}
		if (getThanhPho1().equalsIgnoreCase("Tỉnh Hà Nam")) {
			setTestcaseStatus("PASS", "Hiển thị tỉnh/thành phố thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị tỉnh/thành phố không thành công");
		}
		if (getQuanHuyen1().equalsIgnoreCase("Thành phố Phủ Lý")) {
			setTestcaseStatus("PASS", "Hiển thị quận huyện thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị quận huyện không thành công");
		}
		if (getPhuongXa1().equalsIgnoreCase("Phường Quang Trung")) {
			setTestcaseStatus("PASS", "Hiển thị phường xã thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị phường xã không thành công");
		}
		if (getSoNha1().equalsIgnoreCase("917")) {
			setTestcaseStatus("PASS", "Hiển thị số nhà thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị số nhà không thành công");
		}
		if (getNoiLamViec1().equalsIgnoreCase("lVDdutJDOcmi")) {
			setTestcaseStatus("PASS", "Hiển thị nơi làm việc thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị nơi làm việc không thành công");
		}
		if (getNguoiLienHe1().equalsIgnoreCase("LEYaOc ZqwGbT yzG")) {
			setTestcaseStatus("PASS", "Hiển thị người liên hệ thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị người liên hệ không thành công");
		}
		if (getNamSinh1().equalsIgnoreCase("2018")) {
			setTestcaseStatus("PASS", "Hiển thị năm sinh thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị năm sinh không thành công");
		}
		if (getNgheNghiep1().equalsIgnoreCase("Hưu trí")) {
			setTestcaseStatus("PASS", "Hiển thị nghề nghiệp thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị nghề nghiệp không thành công");
		}
		if (getSoDienThoaiNguoiLienHe1().equalsIgnoreCase("1542370677")) {
			clickToaDo(986, 177);
			// s.type(Key.F4, Key.ALT);
			setTestcaseStatus("PASS", "Hiển thị Số điện thoại thành công");
		} else {
			clickToaDo(986, 177);
			// s.type(Key.F4, Key.ALT);
			setTestcaseStatus("FAIL", "Hiển thị số điện thoại không thành công");
		}
	}

	@Test(priority = 3)
	public void capNhatThongTin_9901_1() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Họ tên");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt vào trường Họ tên");
		TestLogger.info("Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		// THÔNG TIN CHO TẤT CẢ CÁC CASE VỀ SAU TỪ CASE NÀY: BN NGUYEN VIET HAITPOB có
		// stn:1801000120
		// MaBN: 18000105
		s.type("u", Key.CTRL);
		soTN = "1801000120";
		dienSoTiepNhan1(soTN);
		s.type(Key.ENTER);
		sleep(2);
		// click buttom Sửa
		clickToaDo(785, 539);
		sleep(2);
		String kiTuDacBiet = "@@";
		setClipboardValue("NGUYEN VIET HAITPOB" + kiTuDacBiet);
		s.type("v", Key.CTRL);
		sleep(2);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		clickToaDo(785, 539);
		if (getHoTen1().equalsIgnoreCase("NGUYEN VIET HAITPOB@@")) {
			setTestcaseStatus("PASS", "Có thể nhập kí tự đặc biệt");
		} else {
			setTestcaseStatus("FAIL", "Không thể nhập kí tự đặc biệt");
		}
	}

	@Test(priority = 3)
	public void capNhatThongTin_9901_3() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Họ tên");
		TestLogger.info("Kiểm tra việc nhập Họ tên vượt quá maxlength 255 ký tự cho phép (256 ký tự)");
		TestLogger.info("Confirm từ ký tự 256 trở đi sẽ không nhận giá trị");
		HoTen1 = TienIch.taoRandomChu(256);
		dienHoTen1(HoTen1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getHoTen1().length() == 255) {
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm từ ký tự 256 trở đi sẽ không nhận giá trị");
		} else {
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Nhận kí tự thứ 256");
		}
	}

	@Test(priority = 4)
	public void capNhatThongTin_9901_4() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Họ tên");
		TestLogger.info("Kiểm tra việc nhập không quá maxlength là 255 ký tự ");
		TestLogger.info("Confirm cho phép thêm mới thành công");
		HoTen1 = TienIch.taoRandomChu(254);
		dienHoTen1(HoTen1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getHoTen1().length() == 254) {
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm từ ký tự 256 trở đi sẽ không nhận giá trị");
		} else {
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Nhận kí tự thứ 256");
		}
	}

	@Test(priority = 5)
	public void capNhatThongTin_9901_5() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Họ tên");
		TestLogger.info("Kiểm tra việc nhập câu lệnh javascript: <script>alert(test)</script> ");
		TestLogger.info("Confirm cho phép thêm mới thành công, không bị mã hóa");
		dienHoTen1("<script>alert(test)</script>");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getHoTen1().equalsIgnoreCase("<script>alert(test)</script>")) {
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm cho phép thêm mới thành công, không bị mã hóa");
		} else {
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm cho phép thêm mới Không thành công");
		}
	}

	@Test(priority = 6)
	public void capNhatThongTin_9901_6() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Họ tên");
		TestLogger.info("Kiểm tra việc nhập thẻ html: <h1>test</h1>");
		TestLogger.info("Confirm cho phép thêm mới thành công, không bị mã hóa");
		dienHoTen1("<h1>test</h1>");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getHoTen1().equalsIgnoreCase("<h1>test</h1>")) {
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm cho phép thêm mới thành công, không bị mã hóa");
		} else {
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm cho phép thêm mới Không thành công");
		}
	}

	@Test(priority = 7)
	public void capNhatThongTin_9901_7() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Họ tên");
		TestLogger.info("Kiểm tra việc nhập với ngôn ngữ Tiếng Việt có dấu");
		TestLogger.info("Confirm cho phép thêm mới thành công, không bị mã hóa");
		dienHoTen1("Nguyễn Văn Nam");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getHoTen1().equalsIgnoreCase("Nguyễn Văn Nam")) {
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm cho phép thêm mới thành công, không bị mã hóa");
		} else {
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm cho phép thêm mới Không thành công");
		}
	}

	@Test(priority = 8)
	public void capNhatThongTin_9901_2() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Họ tên");
		TestLogger.info("Kiểm tra bỏ trống trường họ tên");
		TestLogger.info("Confirm Không cho phép bỏ trống");
		dienHoTen1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			dienHoTen1("Nguyễn Văn Nam");
			setTestcaseStatus("PASS", "Không cho phép bỏ trống");
		} else {
			s.type(Key.ENTER);
			dienHoTen1("Nguyễn Văn Nam");
			setTestcaseStatus("FAIL", " cho phép bỏ trống");
		}
	}

	@Test(priority = 9)
	public void capNhatThongTin_9979_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số CMND");
		TestLogger.info("Kiểm tra bỏ trống trường CMND");
		TestLogger.info("Confirm hệ thống cho phép lưu thành công");
		dienSoCMND1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Lưu không thành công");
		} else {
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu Thành công");
		}
	}

	@Test(priority = 10)
	public void capNhatThongTin_9979_3() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số CMND");
		TestLogger.info("Kiểm tra nhập kí tự số quá maxlenght");
		TestLogger.info("Confirm Confirm hệ thống không nhận giá trị từ kí tự 13");
		soCMND1 = TienIch.taoRandomSo(13);
		dienSoCMND1(soCMND1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoCMND1().length() == 12) {
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm hệ thống không nhận giá trị từ ");
		} else {
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lỗi trường Số CMND");
		}
	}

	@Test(priority = 11)
	public void capNhatThongTin_9979_5() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số CMND");
		TestLogger.info("Kiểm tra nhập không quá ký tự maxlength");
		TestLogger.info("Confirm báo lỗi");
		soCMND1 = TienIch.taoRandomSo(5);
		dienSoCMND1(soCMND1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Confirm báo lỗi");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Confirm không lỗi");
		}
	}

	@Test(priority = 11)
	public void capNhatThongTin_9979_6_7() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số CMND");
		TestLogger.info("Kiểm tra nhập kí tự chữ và kí tự đặc biệt");
		TestLogger.info("Confirm Không cho phép nhập");
		dienSoCMND1("amn@");
		sleep(2);
		if (getSoCMND1().equalsIgnoreCase("amn@")) {
			setTestcaseStatus("FAIL", "Cho phép nhập kí tự chữ và kí tự đặc biệt");
		} else {
			setTestcaseStatus("PASS", "Không cho phép nhập kí tự chữ và kí tự đặc biệt");
		}

	}

	@Test(priority = 12)
	public void capNhatThongTin_10005_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT");
		TestLogger.info("Kiểm tra bỏ trống trường số điện thoại");
		TestLogger.info("Hệ thống cho phép thêm mới thành công.");
		dienSoDT1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm cho phép để trống trường SĐT");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm không cho phép để trống trường SĐT");
		}
	}

	@Test(priority = 13)
	public void capNhatThongTin_10005_2() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT");
		TestLogger.info("Kiểm tra cho phép nhập ký tự đặc biệt (),+, dấu cách.");
		TestLogger.info("Hệ thống cho phép thêm mới thành công");
		dienSoDT1("+84 963329889");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm cho phép để trống trường SĐT");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm không cho phép để trống trường SĐT");
		}
	}

	@Test(priority = 14)
	public void capNhatThongTin_10005_3() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT");
		TestLogger.info("Kiểm tra nhập quá ký tự maxlength [10;20] ký tự");
		TestLogger.info("Confirm từ ký tự 21 hệ thống không cho phép nhận giá trị");
		soDT1 = TienIch.taoRandomSo(21);
		dienSoDT1(soDT1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoDT1().length() == 20) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Chỉ nhận 20 kí tự đầu");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Đang nhận 21 kí tự");
		}
	}

	@Test(priority = 15)
	public void capNhatThongTin_10005_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT");
		TestLogger.info("Kiểm tra nhập nhỏ hơn 10 kí tự");
		TestLogger.info("Confirm hiển thị lỗi");
		soDT1 = TienIch.taoRandomSo(9);
		dienSoDT1(soDT1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Confirm hiển thị lỗi");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Không hiển thị lỗi");
		}
	}

	@Test(priority = 16)
	public void capNhatThongTin_10005_5() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT");
		TestLogger.info("Kiểm tra việc nhập ký tự chữ");
		TestLogger.info("Confirm hiển thị lỗi");
		dienSoDT1("abcdef");
		sleep(2);
		if (getSoDT1().equals("abcdef")) {
			setTestcaseStatus("FAIL", "Trường SĐT đang cho phép nhập kí tự chữ");
		} else {
			setTestcaseStatus("PASS", "Không cho phép nhập kí tự chữ");
		}
	}

	@Test(priority = 17)
	public void capNhatThongTin_10005_6() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT");
		TestLogger.info("Kiểm tra việc nhập trong khoảng [10,20] ký tự ");
		TestLogger.info("Confirm cho phép nhập và lưu thành công");
		soDT1 = TienIch.taoRandomSo(12);
		dienSoDT1(soDT1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoDT1().equalsIgnoreCase(soDT1)) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu thành công");

		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu không thành công");
		}
	}

	@Test(priority = 18)
	public void capNhatThongTin_10007_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Nghề nghiệp");
		TestLogger.info("Kiểm tra bỏ trống trường Nghề nghiệp");
		TestLogger.info("Confirm  cho phép bỏ trống");
		dienNgheNghiep1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Confirm Không cho phép để trống trường nghề nghiệp");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Confirm cho phép để trống trường nghề nghiệp");
		}
	}

	@Test(priority = 18)
	public void capNhatThongTin_10007_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Nghề nghiệp");
		TestLogger.info("Kiểm tra khả năng nhập vào trường Nghề nghiệp");
		TestLogger.info("Hệ thống cho phép nhập text tìm kiếm Nghề nghiệp theo mã và tên");
		dienNgheNghiep1("Sinh viên");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNgheNghiep1().equalsIgnoreCase("Sinh viên")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Điền thông tin nghề nghiệp thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Điền thông tin nghề nghiệp không thành công");
		}
	}

	@Test(priority = 19)
	public void capNhatThongTin_10014_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Dân tộc");
		TestLogger.info("Kiểm tra bỏ trống trường Dân tộc");
		TestLogger.info("Confirm hệ thông cho phép lưu thành công");
		dienDanToc1("");
		clickToaDo(683, 537);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm hệ thông cho phép lưu thành công");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm hệ thông cho phép lưu thành công");
		}
	}

	@Test(priority = 20)
	public void capNhatThongTin_10014_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Dân tộc");
		TestLogger.info("Kiểm tra khả năng nhập vào trường Dân tộc");
		TestLogger.info("Confirm hệ thông cho phép lưu thành công");
		dienDanToc1("Xinh-mun");
		s.type(Key.ENTER);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getDanToc1().equalsIgnoreCase("Xinh-mun")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Điền thông tin và lưu thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Điền thông tin và lưu không thành công");
		}
	}

	@Test(priority = 21)
	public void capNhatThongTin_10024_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Quốc tịch");
		TestLogger.info("Kiểm tra bỏ trống trường Quốc tịch");
		TestLogger.info("Confirm hệ thông cho phép lưu thành công");
		dienQuocTich1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm hệ thông cho phép lưu thành công");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm hệ thông cho phép lưu thành công");
		}
	}

	@Test(priority = 22)
	public void capNhatThongTin_10024_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Quốc tịch");
		TestLogger.info("Kiểm tra khả năng nhập vào trường Quốc tịch");
		TestLogger.info("Hệ thống cho phép nhập text tìm kiếm Quốc tịch theo mã và tên");
		dienQuocTich1("Tây Ban Nha");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getQuocTich1().equalsIgnoreCase("Tây Ban Nha")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Chọn quốc tịch thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Chọn quốc tịch không thành công");
		}
	}

	@Test(priority = 23)
	public void capNhatThongTin_10026_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Tỉnh/TP");
		TestLogger.info("Kiểm tra bỏ trống giá trị bắt buộc");
		TestLogger.info("Hệ thống hiển thị cảnh báo: \"Tỉnh/TP không được để trống");
		dienThanhPho1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Confirm Không cho phép để trống tỉnh thành phố");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Confirm cho phép để trống tỉnh thành phố");
		}
	}

	@Test(priority = 24)
	public void capNhatThongTin_10026_3() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Tỉnh/TP");
		TestLogger.info("Kiểm tra sắp xếp các giá trị trong combobox Tỉnh/TP");
		TestLogger.info("Giá trị trong combobox được sắp xếp theo thứ tự, mã, tênvà hiển thị Viết tắt, tên tỉnh thành");
		sleep(1);
		clickToaDo(652, 405);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_SapXepTinh, 5)) {
			setTestcaseStatus("PASS", "Hiển thị đầy đủ thông tin các cột");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị thiếu thông tin các cột");
		}
	}

	@Test(priority = 25)
	public void capNhatThongTin_10026_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Tỉnh/TP");
		TestLogger.info("Kiểm tra khả năng nhập vào trường Tỉnh/TP");
		TestLogger.info("Hệ thống cho phép nhập text tìm kiếm phòng ban theo viết tắt, tên tỉnh thành phố");
		dienThanhPho1("Tỉnh Bắc Kạn");
		s.type(Key.ENTER);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getThanhPho1().equalsIgnoreCase("Tỉnh Bắc Kạn")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS",
					"Hệ thống cho phép nhập text tìm kiếm phòng ban theo viết tắt, tên tỉnh thành phố");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS",
					"Hệ thống  không cho phép nhập text tìm kiếm phòng ban theo viết tắt, tên tỉnh thành phố");
		}
	}

	@Test(priority = 26)
	public void capNhatThongTin_10032_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Quận/huyện");
		TestLogger.info("Kiểm tra bỏ trống giá trị bắt buộc");
		TestLogger.info("Hệ thống hiển thị cảnh báo: \"Quận/huyện không được để trống\"");
		dienQuanHuyen1("");
		clickToaDo(577, 532);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);

		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Hệ thống hiển thị cảnh báo: \\\"Quận/huyện không được để trống");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Hệ thống hiển thị cảnh báo: \\\"Quận/huyện không được để trống");
		}
	}

	@Test(priority = 27)
	public void capNhatThongTin_10032_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Quận/huyện");
		TestLogger.info("Kiểm tra khả năng nhập vào trường Quận/huyện");
		TestLogger.info("Hệ thống cho phép nhập text tìm kiếm Quận/huyện theo viết tắt và tên");
		dienQuanHuyen1("Thành phố Cao Bằng");
		s.type(Key.ENTER);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getQuanHuyen1().equalsIgnoreCase("Thành phố Cao Bằng")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Hệ thống cho phép nhập text tìm kiếm Quận/huyện theo viết tắt và tên");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Hệ thống không cho phép nhập text tìm kiếm Quận/huyện theo viết tắt và tên");
		}
	}

	@Test(priority = 28)
	public void capNhatThongTin_10033_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Phường/xã");
		TestLogger.info("Kiểm tra bỏ trống giá trị bắt buộc ");
		TestLogger.info("Confirm hệ thống cho phép lưu thành công");
		dienPhuongXa1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "KHông cho phép lưu");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm hệ thống cho phép lưu thành công");
		}

	}

	@Test(priority = 29)
	public void capNhatThongTin_10033_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Phường/xã");
		TestLogger.info("Kiểm tra khả năng nhập vào trường Phường/xã");
		TestLogger.info("Hệ thống cho phép nhập text tìm kiếm Phường/xã theo viết tắt và tên");
		dienPhuongXa1("Phường Sông Hiến");
		s.type(Key.ENTER);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getPhuongXa1().equalsIgnoreCase("Phường Sông Hiến")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Hệ thống cho phép nhập text tìm kiếm Phường/xã theo viết tắt và tên");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu không thành công");
		}
	}

	@Test(priority = 30)
	public void capNhatThongTin_10034_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số nhà");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt");
		TestLogger.info("Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		dienSoNha1("@@@");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoNha1().equalsIgnoreCase("@@@")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS",
					"Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lỗi");
		}
	}

	@Test(priority = 31)
	public void capNhatThongTin_10034_2() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số nhà");
		TestLogger.info("Kiểm tra việc để trống trường Số nhà");
		TestLogger.info("Confirm hệ thống cho phép lưu thành công");
		dienSoNha1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			setTestcaseStatus("FAIL", "Không cho phép lưu");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Cho phép lưu thành công");
		}
	}

	@Test(priority = 32)
	public void capNhatThongTin_10034_3() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số nhà");
		TestLogger.info("Kiểm tra việc nhập Số nhà vượt quá maxlength 30 ký tự cho phép (31 ký tự)");
		TestLogger.info("Confirm từ ký tự 31 hệ thống không nhận giá trị");
		SoNha1 = TienIch.taoRandomSo(31);
		dienSoNha1(SoNha1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoNha1().length() == 30) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm từ ký tự 31 hệ thống không nhận giá trị");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Sai trường số nhà");
		}
	}

	@Test(priority = 33)
	public void capNhatThongTin_10034_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số nhà");
		TestLogger.info("Kiểm tra việc nhập không quá maxlength là 30 ký tự ");
		TestLogger.info("Confirm cho phép lưu thành công");
		SoNha1 = TienIch.taoRandomSo(29);
		dienSoNha1(SoNha1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoNha1().length() == 29) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm từ ký tự 31 hệ thống không nhận giá trị");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Sai trường số nhà");
		}
	}

	@Test(priority = 34)
	public void capNhatThongTin_10034_5() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số nhà");
		TestLogger.info("Kiểm tra việc nhập câu lệnh javascript");
		TestLogger.info(
				"Confirm cho phép lưu thành công, thẻ script không bị mã hóa khi view lên và khi lưu vào database");
		dienSoNha1("<script>alert(test)</script>");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoNha1().equalsIgnoreCase("<script>alert(test)</script>")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu thông tin thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu thông tin không thành công");
		}
	}

	@Test(priority = 35)
	public void capNhatThongTin_10034_6() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Số nhà");
		TestLogger.info("Kiểm tra việc nhập câu lệnh HTML");
		TestLogger
				.info("Confirm cho phép lưu thành công, thẻ HTML không bị mã hóa khi view lên và khi lưu vào database");
		dienSoNha1("<h1>test</h1>");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoNha1().equalsIgnoreCase("<h1>test</h1>")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu thông tin thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu thông tin không thành công");
		}
	}

	@Test(priority = 36)
	public void capNhatThongTin_10044_1() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Nơi làm việc");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt");
		TestLogger.info("Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		dienNoiLamViec1("@@@");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNoiLamViec1().equalsIgnoreCase("@@@")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS",
					"Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lỗi");
		}
	}

	@Test(priority = 37)
	public void capNhatThongTin_10044_2() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Nơi làm việc");
		TestLogger.info("Kiểm tra việc để trống trường nơi làm việc ");
		TestLogger.info("Confirm hệ thống cho phép lưu thành công");
		dienNoiLamViec1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			setTestcaseStatus("FAIL", "Không cho phép lưu");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Cho phép lưu thành công");
		}
	}

	@Test(priority = 38)
	public void capNhatThongTin_10044_3() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Nơi làm việc");
		TestLogger.info("Kiểm tra việc nhập Nơi làm việc vượt quá maxlength 255 ký tự cho phép");
		TestLogger.info("Confirm từ ký tự 256 trở đi sẽ không nhận giá trị");
		NoiLamViec1 = TienIch.taoRandomChu(256);
		dienNoiLamViec1(NoiLamViec1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNoiLamViec1().length() == 255) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm từ ký tự 256 hệ thống không nhận giá trị");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Sai trường nơi làm việc");
		}
	}

	@Test(priority = 39)
	public void capNhatThongTin_10044_4() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Nơi làm việc");
		TestLogger.info("Kiểm tra việc nhập Nơi làm việc không vượt quá maxlength 255 ký tự cho phép");
		TestLogger.info("Confirm cho phép thêm mới thành công");
		NoiLamViec1 = TienIch.taoRandomChu(254);
		dienNoiLamViec1(NoiLamViec1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNoiLamViec1().length() == 254) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm cho phép thêm mới thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Sai trường nơi làm việc");
		}
	}

	@Test(priority = 40)
	public void capNhatThongTin_10044_5() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Nơi làm việc");
		TestLogger.info("Kiểm tra việc nhập câu lệnh javascript");
		TestLogger.info(
				"Confirm cho phép lưu thành công, thẻ script không bị mã hóa khi view lên và khi lưu vào database");
		dienNoiLamViec1("<script>alert(hoahoa)</script>");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNoiLamViec1().equalsIgnoreCase("<script>alert(test)</script>")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu thông tin thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu thông tin không thành công");
		}
	}

	@Test(priority = 41)
	public void capNhatThongTin_10044_6() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Nơi làm việc");
		TestLogger.info("Kiểm tra việc nhập thẻ html");
		TestLogger.info(
				"Confirm cho phép lưu thành công, thẻ script không bị mã hóa khi view lên và khi lưu vào database");
		dienNoiLamViec1("<h1>test</h1>");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNoiLamViec1().equalsIgnoreCase("<h1>test</h1>")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu thông tin thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu thông tin không thành công");
		}
	}

	@Test(priority = 42)
	public void capNhatThongTin_10049_1() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Người liên hệ");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt");
		TestLogger.info("Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		dienNguoiLienHe1("@@@");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNguoiLienHe1().equalsIgnoreCase("@@@")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS",
					"Confirm có thể nhập ký tự đặc biệt và được view lên bình thường, không có lỗi font");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lỗi");
		}
	}

	@Test(priority = 44)
	public void capNhatThongTin_10049_3() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Người liên hệ");
		TestLogger.info("Kiểm tra việc nhập trường Người liên hệ vượt quá maxlength 100 ký tự cho phép");
		TestLogger.info("Confirm từ ký tự 256 trở đi sẽ không nhận giá trị");
		NguoiLienHe1 = TienIch.taoRandomChu(101);
		// TestLogger.info(NguoiLienHe1);
		dienNguoiLienHe1(NguoiLienHe1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNguoiLienHe1().length() == 100) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm từ ký tự 256 hệ thống không nhận giá trị");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Sai trường người liên hệ");
		}
	}

	@Test(priority = 45)
	public void capNhatThongTin_10049_4() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Người liên hệ");
		TestLogger.info("Kiểm tra việc nhập trường Người liên hệ không vượt quá maxlength 100 ký tự cho phép");
		TestLogger.info("Confirm cho phép thêm mới thành công");
		NguoiLienHe1 = TienIch.taoRandomChu(99);
		// TestLogger.info(NguoiLienHe1);
		dienNguoiLienHe1(NguoiLienHe1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNguoiLienHe1().length() == 99) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm từ ký tự 256 hệ thống không nhận giá trị");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Sai trường người liên hệ");
		}
	}

	@Test(priority = 46)
	public void capNhatThongTin_10049_5() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường người liên hệ");
		TestLogger.info("Kiểm tra việc nhập câu lệnh javascript");
		TestLogger.info(
				"Confirm cho phép lưu thành công, thẻ script không bị mã hóa khi view lên và khi lưu vào database");
		dienNguoiLienHe1("<script>alert(hoahoa)</script>");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNguoiLienHe1().equalsIgnoreCase("<script>alert(test)</script>")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu thông tin thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu thông tin không thành công");
		}
	}

	@Test(priority = 47)
	public void capNhatThongTin_10049_6() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường người liên hệ");
		TestLogger.info("Kiểm tra việc nhập câu lệnh HTML");
		TestLogger
				.info("Confirm cho phép lưu thành công, thẻ HTML không bị mã hóa khi view lên và khi lưu vào database");
		dienNguoiLienHe1("h1>test</h1>");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNguoiLienHe1().equalsIgnoreCase("h1>test</h1>")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu thông tin thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu thông tin không thành công");
		}
	}

	@Test(priority = 48)
	public void capNhatThongTin_10049_7() {
		TestLogger.info("[Cập nhật_Validate]Kiểm tra Validate trường Người liên hệ");
		TestLogger.info("Kiểm tra việc nhập với ngôn ngữ Tiếng Việt có dấu ");
		TestLogger.info("Confirm có thể nhập ngôn ngữ tiếng việt và được view lên bình thường, không có lỗi font");
		dienNguoiLienHe1("Trần Thị Thùy Dương");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNguoiLienHe1().equalsIgnoreCase("Trần Thị Thùy Dương")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS",
					"Confirm có thể nhập ngôn ngữ tiếng việt và được view lên bình thường, không có lỗi font");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lỗi");
		}
	}

	@Test(priority = 49)
	public void capNhatThongTin_10057_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra bỏ trống trường số điện thoại liên hệ");
		TestLogger.info("Hệ thống cho phép thêm mới thành công.");
		dienSoDienThoaiNguoiLienHe1("");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm cho phép để trống trường SĐT");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm không cho phép để trống trường SĐT");
		}
	}

	@Test(priority = 50)
	public void capNhatThongTin_10057_2() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra cho phép nhập ký tự đặc biệt (),+, dấu cách.");
		TestLogger.info("Hệ thống cho phép thêm mới thành công");
		dienSoDienThoaiNguoiLienHe1("+84 963329889");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Confirm cho phép để trống trường SĐT");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm không cho phép để trống trường SĐT");
		}
	}

	@Test(priority = 51)
	public void capNhatThongTin_10057_3() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra nhập quá ký tự maxlength [10;20] ký tự");
		TestLogger.info("Confirm từ ký tự 21 hệ thống không cho phép nhận giá trị");
		SoDTnguoiLienHe1 = TienIch.taoRandomSo(21);
		dienSoDienThoaiNguoiLienHe1(SoDTnguoiLienHe1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoDienThoaiNguoiLienHe1().length() == 20) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Chỉ nhận 20 kí tự đầu");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Đang nhận 21 kí tự");
		}
	}

	@Test(priority = 52)
	public void capNhatThongTin_10057_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra nhập nhỏ hơn 10 kí tự");
		TestLogger.info("Confirm hiển thị lỗi");
		SoDTnguoiLienHe1 = TienIch.taoRandomSo(9);
		dienSoDienThoaiNguoiLienHe1(SoDTnguoiLienHe1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Confirm hiển thị lỗi");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Không hiển thị lỗi");
		}
	}

	@Test(priority = 53)
	public void capNhatThongTin_10057_5() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra việc nhập ký tự chữ");
		TestLogger.info("Confirm hiển thị lỗi");
		dienSoDienThoaiNguoiLienHe1("abcdef");
		sleep(2);
		if (getSoDienThoaiNguoiLienHe1().equals("abcdef")) {
			setTestcaseStatus("FAIL", "Trường SĐT đang cho phép nhập kí tự chữ");
		} else {
			setTestcaseStatus("PASS", "Không cho phép nhập kí tự chữ");
		}
	}

	@Test(priority = 54)
	public void capNhatThongTin_10057_6() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường SĐT liên hệ");
		TestLogger.info("Kiểm tra việc nhập trong khoảng [10,20] ký tự ");
		TestLogger.info("Confirm cho phép nhập và lưu thành công");
		SoDTnguoiLienHe1 = TienIch.taoRandomSo(15);
		dienSoDienThoaiNguoiLienHe1(SoDTnguoiLienHe1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getSoDienThoaiNguoiLienHe1().equalsIgnoreCase(SoDTnguoiLienHe1)) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Lưu thành công");

		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Lưu không thành công");
		}
	}

	@Test(priority = 55)
	public void capNhatThongTin_10065() {
		TestLogger.info("[Cập nhật_Sửa]Kiểm tra validate trường Mã TN trên form Sửa");
		TestLogger.info("Kiểm tra validate trường Mã TN trên form Sửa");
		TestLogger.info("Confirm hiển thị trường Mã BN là disable và người dùng không thể thao tác trên trường Mã BN");
		dienSoTiepNhan1("0912345609");
		if (getSoTiepNhan1().equalsIgnoreCase("0912345609")) {
			setTestcaseStatus("FAIL", "Đang sửa được trường Mã TN");
		} else {
			setTestcaseStatus("PASS",
					"Confirm hiển thị trường Mã BN là disable và người dùng không thể thao tác trên trường Mã BN");
		}
	}

	@Test(priority = 56)
	public void capNhatThongTin_9903_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Ngày sinh");
		TestLogger.info("Kiểm tra cho phép nhập ngày sinh  ");
		TestLogger.info("cho phép nhập ngày tháng năm sinh thành công");
		sleep(2);
		dienNgaySinh1("09081995");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNgaySinh1().equalsIgnoreCase("09/08/1995")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "cho phép nhập ngày tháng năm sinh thành công");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Cập nhật ngày sinh không thành công");
		}
	}

	@Test(priority = 57)
	public void capNhatThongTin_9903_5() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Ngày sinh");
		TestLogger.info("Kiểm tra việc chỉ nhập ngày/tháng" + "Ex: nhập 10/12");
		TestLogger.info("Confirm hệ thống hiển thị mặc định là năm hiện tại");
		dienNgaySinh1("1012");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNgaySinh1().equalsIgnoreCase("10/12/2018")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "cho phép nhập ngày tháng năm sinh thành công");
		} else {
			s.type(Key.ENTER);
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Cập nhật ngày sinh không thành công");
		}
	}

	@Test(priority = 58)
	public void capNhatThongTin_9963_1() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Năm sinh");
		TestLogger.info("Kiểm tra việc nhập ký tự đặc biệt vào trường Năm sinh");
		TestLogger.info("Confirm hệ thống không cho phép nhập ký tự đặc biệt trường Năm sinh");
		dienNamSinh1("@@@");
		if (getNamSinh1().equals("@@@")) {
			setTestcaseStatus("FAIL", "Đang cho nhập kí tự đặc biệt vào trường năm sinh");
		} else {
			setTestcaseStatus("PASS", "Confirm hệ thống không cho phép nhập ký tự đặc biệt trường Năm sinh");
		}
	}

	@Test(priority = 59)
	public void capNhatThongTin_9963_2() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Năm sinh");
		TestLogger.info("Kiểm tra việc nhập ký tự chữ cái vào trường Năm sinh");
		TestLogger.info("Confirm hệ thống không cho phép nhập ký tự chữ cái vào trường Năm sinh");
		dienNamSinh1("abcop");
		if (getNamSinh1().equals("abcop")) {
			setTestcaseStatus("FAIL", "Đang cho nhập kí tự chữ cái vào trường năm sinh");
		} else {
			setTestcaseStatus("PASS", "Confirm hệ thống không cho phép nhập ký tự chữ cái trường Năm sinh");
		}
	}

	@Test(priority = 60)
	public void capNhatThongTin_9963_4() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Năm sinh");
		TestLogger.info("Kiểm tra việc nhập trong khoảng maxlength [1887;năm hiện hành]");
		TestLogger.info("Confifrm hệ thống hiển thị thông báo Năm sinh không được phép để trống");
		dienNamSinh1("1990");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNamSinh1().equals("1990")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Hiển thị năm sinh thành công");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Hiển thị năm sinh không thành công");
		}
	}

	@Test(priority = 61)
	public void capNhatThongTin_9963_5() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Năm sinh");
		TestLogger.info("Kiểm tra việc nhập số âm ");
		TestLogger.info("Confirm hệ thống không cho phép nhập số âm");
		dienNamSinh1("-1990");
		if (getNamSinh1().equals("-1990")) {
			setTestcaseStatus("FAIL", "Đang cho phép nhập năm sinh là số âm");
		} else {
			setTestcaseStatus("PASS", "Confirm hệ thống không cho phép nhập số âm");
		}
	}

	@Test(priority = 62)
	public void capNhatThongTin_9963_6() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Năm sinh");
		TestLogger.info("Kiểm tra việc nhập ngoài giá trị maxlength [1877; năm sinh] và [0; năm hiện hành - 1877]");
		TestLogger.info("Confifrm hệ thống hiển thị thông báo lỗi ");
		dienNamSinh1("1876");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (waitForObjectPresent(FormCapNhatThongTinHanhChinh_MessageLoi, 5)) {
			setTestcaseStatus("PASS", "Confifrm hệ thống hiển thị thông báo lỗi ");
		} else {
			setTestcaseStatus("FAIL", "Hệ thống lỗi ");
		}
	}

	@Test(priority = 63)
	public void capNhatThongTin_9963_7() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Năm sinh");
		TestLogger.info("Kiểm tra việc nhập số tuổi trong khoảng [0;năm hiện hành - 1877] ");
		TestLogger.info("Confirm hệ thống tự động nhảy năm sinh ứng với số tuổi nhập vào");
		dienNamSinh1("28");
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);
		if (getNamSinh1().equals("1990")) {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("PASS", "Confirm hệ thống tự động nhảy năm sinh ứng với số tuổi nhập vào");
		} else {
			// click tọa độ buttom sửa
			clickToaDo(785, 539);
			setTestcaseStatus("FAIL", "Không hiển thị năm sinh theo số tuổi");
		}
	}

	@Test(priority = 64)
	public void capNhatThongTin_9963_8() {
		TestLogger.info("[Cập nhật_Validate] Kiểm tra validate trường Năm sinh");
		TestLogger.info("Kiểm tra việc đã nhập ngày tháng năm sinh vào trường Ngày sinh ");
		TestLogger.info("Confirm hệ thống tự động cập nhật năm sinh");
		dienNgaySinh1("10091996");
		if (getNamSinh1().equals("1996")) {
			// s.type("F4", Key.ALT);
			setTestcaseStatus("PASS", "Hiển thị năm sinh thành công");
		} else {
			// s.type("F4", Key.ALT);
			setTestcaseStatus("FAIL", "Hiển thị năm sinh không thành công");
		}
	}

	@Test(priority = 65)
	public void capNhatThongTin_10073() throws SQLException {
		TestLogger.info("[DB_Sửa]Kiểm tra bản ghi được sửa trong db khi thực hiện lưu thành công");
		TestLogger.info("Hiển thị bản ghi vừa sửa trong BD đúng");
		String HoTen1 = "TRAN VAN LUC" + TienIch.taoRandomChu(5);
		dienHoTen1(HoTen1);
		// click buttom Lưu
		clickToaDo(882, 538);
		sleep(2);

		KiemSoatDatabase DB = new KiemSoatDatabase();
		Connection con = DB.taoKetNoi(KiemSoatDatabase.URL, KiemSoatDatabase.USERNAME, KiemSoatDatabase.PASSWORD);
		Statement smt = con.createStatement();
		ResultSet rs = smt
				.executeQuery("Select * from E_EXAM_RECORD WHERE HEALTH_EXAM_RECORD_CODE = '" + 1801000120 + "'");
		if (rs.next()) {
			if (rs.getString("PATIENT_NAME").equals(HoTen1)) {
				clickOn(FormCapNhatThongTinHanhChinh.FormCapNhatThongTinHanhChinh_CloseForm);
				setTestcaseStatus("PASS", "cap nhat thanh cong");
			} else {
				clickOn(FormCapNhatThongTinHanhChinh.FormCapNhatThongTinHanhChinh_CloseForm);
				setTestcaseStatus("FAIL", "cap nhat khong thanh cong");
			}
		} else {
			clickOn(FormCapNhatThongTinHanhChinh.FormCapNhatThongTinHanhChinh_CloseForm);
			TestLogger.info("Khong co ban ghi nao thoa man ! ");
		}
	}

	@AfterTest
	public void ketThucForm() {
		dangXuatKhoiTaikhoan();
		thoatUngdungHIS();
	}

}
