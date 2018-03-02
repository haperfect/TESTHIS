package TestHIS;

import org.sikuli.script.Key;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HIS.FormChuyenPhongKham;
import HIS.FormKhuVuc;
import HIS.TiepNhanBenhNhan;
import desktop_Framework.HisActions;
import desktop_Framework.TestLogger;

public class TestFormChuyenPhongKham extends FormChuyenPhongKham {
	public String soTiepNhan1, PhongKham;
	HisActions his = new HisActions();

	@BeforeTest
	public void dieukienDauTien() {
		if (his.khoiDongHIS(HisActions.DUONG_DAN_FILE_CHAY_HIS) == true) {
			his.dangNhapHIS(FormKhuVuc.ten_dangNhap_admin, FormKhuVuc.matKhau_dangNhap_admin);
			his.chonPhongLamViec("Khám TMH");
			waitForObjectPresent(HIS_MenuTiepNhan, 5);
			clickOn(HIS_MenuTiepNhan);
			waitForObjectPresent(FormChuyenPhongKham_SubMenu, 5);
			clickOn(FormChuyenPhongKham_SubMenu);
			waitForObjectPresent(TiepNhanBenhNhan.AfterLoading, 15);
			if (waitForObjectPresent(FormChuyenPhongKham_Title, 5)) {
				TestLogger.info("Mở form Chuyển phòng khám thành công");
			} else {
				TestLogger.info("Mở form Chuyển phòng khám không thành công");
			}
		} else
			setTestcaseStatus("FAIL", "Không thể khởi động ứng dụng HIS !");
	}

	@Test(priority = 1)
	public void chuyenPhongKham_15348_1() {
		TestLogger.info("[Hành chính_Validate]Kiểm tra trường số TN");
		TestLogger.info("Kiểm tra giá trị mặc định khi mở trực tiếp form chuyển phòng khám");
		TestLogger.info("Hiển thị giá trị mặc định là null");
		if (getSoTiepNhan1().equals("")) {
			setTestcaseStatus("PASS", "Giá trị mặc định là NULL");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không phải là NULL");
		}
	}

	@Test(priority = 2)
	public void chuyenPhongKham_15348_2() {
		TestLogger.info("[Hành chính_Validate]Kiểm tra trường số TN");
		TestLogger.info(" Kiểm tra việc bỏ trống ");
		TestLogger.info("Hệ thống hiển thị cảnh báo yêu cầu nhập trường bắt buộc");
		dienSoTiepNhan1("");
		if (waitForObjectPresent(FormChuyenPhongKham_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Hệ thống hiển thị cảnh báo yêu cầu nhập trường bắt buộc");
		} else {
			setTestcaseStatus("FAIL", "Hệ thống không hiện cảnh báo");
		}
	}

	@Test(priority = 3)
	public void chuyenPhongKham_15348_3() {
		TestLogger.info("[Hành chính_Validate]Kiểm tra trường số TN");
		TestLogger.info(" Kiểm tra nhập số tiếp nhận không tồn tại/sai ");
		TestLogger.info("Hệ thống hiển thị cảnh báo");
		dienSoTiepNhan1("0987112345");
		if (waitForObjectPresent(FormChuyenPhongKham_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Hệ thống hiển thị cảnh báo yêu cầu nhập trường bắt buộc");
		} else {
			setTestcaseStatus("FAIL", "Hệ thống không hiện cảnh báo");
		}
	}

	@Test(priority = 4)
	public void chuyenPhongKham_15348_4() {
		TestLogger.info("[Hành chính_Validate]Kiểm tra trường số TN");
		TestLogger.info(" Kiểm tra nhập số tiếp nhận đang ở trạng thái chờ kết quả ");
		TestLogger.info("Hệ thống hiển thị cảnh báo");
		// 1801000084- BN đang chờ kết quả
		dienSoTiepNhan1("1801000084");
		sleep(2);
		clickOn(FormChuyenPhongKham_Sua);
		dienPhongKham1("Phòng siêu âm tim BHYT");
		sleep(2);
		waitForObjectPresent(FormChuyenPhongKham_ChuyenPhongKham, 5);
		clickOn(FormChuyenPhongKham_ChuyenPhongKham);

		if (waitForObjectPresent(FormChuyenPhongKham_MessageLoi, 5)) {
			s.type(Key.ENTER);
			// clickOn();
			setTestcaseStatus("PASS", "Hệ thống hiển thị cảnh báo");
		} else {
			s.type(Key.ENTER);
			setTestcaseStatus("FAIL", "Hệ thống không hiện cảnh báo");
		}
	}

	@Test(priority = 5)
	public void chuyenPhongKham_15348_5() {
		TestLogger.info("[Hành chính_Validate]Kiểm tra trường số TN");
		TestLogger.info(" Kiểm tra nhập số tiếp nhận không ở trạng thái chờ kết quả/đã hoàn thành ");
		TestLogger.info("Hệ thống hiển thị đầy đủ thông tin bệnh nhân");
		// THông tin bệnh nhân Test
		// Số TN: 1802000133, mã bệnh nhân: 18000339
		// Họ Tên: NGUYEN VIET HAZVAPA
		// Năm sinh: 2018, giới tính nữ, đối tượng Dịch vụ, địa chỉ Phường Quang Trung,
		// Thành phố Phủ Lý, Tỉnh Hà Nam
		// Dịch vụ: Khám Tai mũi họng, phòng khám trước TMH
		dienSoTiepNhan1("1802000133");
		// Lấy ra mã Bệnh nhân
		String laytext1 = layTextTuAnh(265, 92, 80, 18);
		// Lấy ra Họ Tên, Họ tên trả về "MEN VIEr HAIFJIH = NGUYEN VIET HAIFJIM
		String laytext2 = layTextTuAnh(449, 97, 187, 19);
		// Lấy ra năm sinh - 20m ~ 2018
		String laytext3 = layTextTuAnh(838, 97, 51, 20);
		// Lấy ra giới tính - Nữ = Mfr
		String laytext4 = layTextTuAnh(974, 96, 39, 17);
		// Lấy ra đối tượng - Dick“! = Dịch vụ
		String laytext5 = layTextTuAnh(80, 127, 69, 20);
		// Lấy ra địa chỉ _ pnuang Quang Tmng, mam. pna pm‘. Ly, Tinh Ha Mam
		String laytext6 = layTextTuAnh(448, 124, 390, 18);
		// Lấy ra Tên dịch vụ - Kna'mm mm hong
		String laytext7 = layTextTuAnh(50, 210, 126, 19);
		// Lấy ra tên dịch vụ trước - Kna'mm mm hong
		String laytext8 = layTextTuAnh(488, 209, 154, 16);
		// Lấy ra phòng khám trước - Khém TMH
		String laytext9 = layTextTuAnh(704, 208, 82, 18);

		if (laytext1.trim().equals("18000339")) {
			setTestcaseStatus("PASS", "Lấy ra đúng thông tin mã bệnh nhân");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra không đúng thông tin mã bệnh nhân");
		}
		if (laytext2.trim().equals("\"MEN VIEr HAIVAPA")) {
			setTestcaseStatus("PASS", "Lấy ra đúng thông tin tên bệnh nhân");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra không đúng thông tin tên bệnh nhân");
		}
		if (laytext3.trim().equals("20m")) {
			setTestcaseStatus("PASS", "Lấy ra năm sinh thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra năm sinh không thành công");
		}
		if (laytext4.trim().equals("Mfr")) {
			setTestcaseStatus("PASS", "Lấy ra giới tính thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra giới tính không thành công");
		}
		// TestLogger.info(laytext4.trim());
		if (laytext5.trim().equals("Dick“!")) {
			setTestcaseStatus("PASS", "Lấy ra đối tượng thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra đối tượng không thành công");
		}
		if (laytext6.trim().equals("pnuang Quang Tmng, mam. pna pm‘. Ly, Tinh Ha Mam")) {
			setTestcaseStatus("PASS", "Lấy ra địa chỉ thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra địa chỉ không thành công");
		}
		if (laytext7.trim().equals("Kna'mm mm hong")) {
			setTestcaseStatus("PASS", "Lấy ra tên dịch vụ thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra tên dịch vụ không thành công");
		}
		if (laytext8.trim().equals("Kna'mm mm hong")) {
			setTestcaseStatus("PASS", "Lấy ra tên dịch vụ trước thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra tên dịch vụ trước không thành công");
		}
		if (laytext9.trim().equals("Khém TMH")) {
			setTestcaseStatus("PASS", "Lấy ra phòng khám trước thành công");
		} else {
			setTestcaseStatus("FAIL", "Lấy ra phòng khám trước không thành công");
		}
	}

	@Test(priority = 6)
	public void chuyenPhongKham_15354_1() {
		TestLogger.info("[Đăng ký khám_Valdiate]Kiểm tra trường Phòng khám");
		TestLogger.info("Kiểm tra giá trị mặc định");
		TestLogger.info("Giá trị mặc định là để trống");
		if (getPhongKham1().equals("")) {
			setTestcaseStatus("PASS", "Giá trị mặc định là để trống");
		} else {
			setTestcaseStatus("FAIL", "Giá trị mặc định không để trống");
		}
	}

	@Test(priority = 7)
	public void chuyenPhongKham_15354_2() {
		TestLogger.info("[Đăng ký khám_Valdiate]Kiểm tra trường Phòng khám");
		TestLogger.info("Kiểm tra bỏ trống trường phòng khám ");
		TestLogger.info("Hiển thị cảnh báo lỗi");
		dienPhongKham1("");
		clickOn(FormChuyenPhongKham_Sua);
		sleep(1);
		clickOn(FormChuyenPhongKham_ChuyenPhongKham);
		if (waitForObjectPresent(FormChuyenPhongKham_MessageLoi, 5)) {
			s.type(Key.ENTER);
			setTestcaseStatus("PASS", "Hiển thị cảnh báo");
		} else {
			setTestcaseStatus("FAIL", "Không hiển thị cảnh báo, đang cho phép bỏ trống ");
		}
	}

	@Test(priority = 8)
	public void chuyenPhongKham_15354_4() {
		TestLogger.info("[Đăng ký khám_Valdiate]Kiểm tra trường Phòng khám");
		TestLogger.info("Kiểm tra việc chọn Phòng ban trùng với phòng ban hiện tại  ");
		TestLogger.info("COmbobox ko hiển thị phòng khám trước liền kề");
		sleep(1);
		// lấy text phòng khám trước
		String laytext1 = layTextTuAnh(705, 210, 108, 17);
		TestLogger.info(laytext1);
		// click tọa độ chọn combobox hiển thị phòng khám
		clickToaDo(475, 216);
		sleep(2);
		// Lấy text phòng khám
		String laytext2 = layTextTuAnh(271, 309, 74, 18);
		TestLogger.info(laytext2);

		if (laytext1.trim().equals(laytext2.trim())) {
			setTestcaseStatus("FAIL", "Combox đang hiển thị phòng khám trước liền kề");
		} else {
			setTestcaseStatus("PASS", "COmbobox ko hiển thị phòng khám trước liền kề");
		}
	}

	@Test(priority = 9)
	public void chuyenPhongKham_15357_1() {
		TestLogger.info("[Đăng ký khám_Validate]Kiểm tra trường Người chuyển");
		TestLogger.info("Kiểm tra việc bệnh nhân chưa chuyển phòng khám lần nào");
		TestLogger.info(" Người chuyển = Blank");
		// lấy text trường người chuyển
		String laytext = layTextTuAnh(923, 211, 215, 17);
		if (laytext.trim().equals("")) {
			setTestcaseStatus("PASS", " Người chuyển = Blank");
		} else {
			setTestcaseStatus("FAIL", " Người chuyển # Blank");
		}
	}
	@AfterTest
	public void ketThucForm() {
		dangXuatKhoiTaikhoan();
		thoatUngdungHIS();
	}

}
