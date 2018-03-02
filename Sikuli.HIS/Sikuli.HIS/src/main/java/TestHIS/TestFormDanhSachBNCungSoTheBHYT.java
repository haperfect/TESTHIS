package TestHIS;

import java.util.List;

import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HIS.DanhSachBenhNhan;
import HIS.FormCapNhatDoiTuongVaChiPhi;
import HIS.FormCapNhatThongTinHanhChinh;
import HIS.FormDanhSachBNCungSoTheBHYT;
import HIS.FormKhuVuc;
import HIS.TiepNhanBenhNhan;
import desktop_Framework.HisActions;
import desktop_Framework.TestLogger;
import desktop_Framework.TienIch;

public class TestFormDanhSachBNCungSoTheBHYT extends FormDanhSachBNCungSoTheBHYT {

	TiepNhanBenhNhan tnbn = new TiepNhanBenhNhan();
	HisActions his = new HisActions();
	DanhSachBenhNhan dsbn = new DanhSachBenhNhan();
	FormCapNhatThongTinHanhChinh cntthc = new FormCapNhatThongTinHanhChinh();
	String MaTN;
	List<String> l;

	//@BeforeTest
	public void dieukienDauTien() {
		if (his.khoiDongHIS(HisActions.DUONG_DAN_FILE_CHAY_HIS) == true) {
			his.dangNhapHIS(FormKhuVuc.ten_dangNhap_admin, FormKhuVuc.matKhau_dangNhap_admin);
			his.chonPhongLamViec("Khám TMH");
			moMenuTiepNhanBenhNhan();
		} else
			setTestcaseStatus("FAIL", "Không thể khởi động ứng dụng HIS !");
	}

	// Số thẻ BHYT được sử dụng cho các Test case bên dưới là SV4019890912666,
	// MaDKKCB:01005
	// Chỉ có 1 số tiếp nhận
	@Test(priority = 1)
	public void DanhSachBNCungSoTheBHYT_9894_1() {
		TestLogger.info("[DSBN có cùng số thẻ BHYT] Thông tin thẻ BHYT đã tồn tại trên hệ thống - Nhập tay");
		TestLogger.info(
				"nhập thông tin số thẻ BHYT + Mã ĐKKB đã tồn tại trong hệ thống của một bệnh nhân theo chiều thuận");
		TestLogger.info("Hiển thị popup \" Danh sách bệnh nhân có cùng số thẻ BHYT\"");
		tnbn.dienSoTheBHYT("SV4019890912666");
		s.type(Key.ENTER);
		tnbn.dienMaDKKCB("01005");
		sleep(2);
		if (waitForObjectPresent(FormDanhSachCungBHYT_TitleForm, 5)) {
			clickOn(FormDanhSachCungBHYT_ButtomBoQua);
			setTestcaseStatus("PASS", "Hiển thị popup thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị popup không thành công");
		}
	}

	@Test(priority = 2)
	public void DanhSachBNCungSoTheBHYT_9894_2() {
		TestLogger.info("[DSBN có cùng số thẻ BHYT] Thông tin thẻ BHYT đã tồn tại trên hệ thống - Nhập tay");
		TestLogger.info(
				"nhập thông tin số thẻ BHYT + Mã ĐKKB đã tồn tại trong hệ thống của một bệnh nhân theo chiều ngược");
		TestLogger.info("Hiển thị popup \" Danh sách bệnh nhân có cùng số thẻ BHYT\"");
		tnbn.dienMaDKKCB("01005");
		sleep(2);
		tnbn.dienSoTheBHYT("SV4019890912666");
		s.type(Key.ENTER);
		if (waitForObjectPresent(FormDanhSachCungBHYT_TitleForm, 5)) {
			clickOn(FormDanhSachCungBHYT_ButtomBoQua);
			setTestcaseStatus("PASS", "Hiển thị popup thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị popup không thành công");
		}
	}

	@Test(priority = 3)
	public void DanhSachBNCungSoTheBHYT_11417_2() {
		TestLogger.info("[DSBN có cùng số thẻ BHYT] Validate các thông tin BHYT của BN");
		TestLogger.info("Kiểm tra thông tin trong cột Số thẻ");
		TestLogger.info("Hiển thị đúng số thẻ BHYT của BN");
		tnbn.dienSoTheBHYT("SV4019890912666");
		s.type(Key.ENTER);
		tnbn.dienMaDKKCB("01005");
		sleep(2);
		keoThaDoiTuongTheoToaDo(390, 551, 1067, 551);
		

		String laymathe = layTextTuAnh(528, 174, 128, 17);
		TestLogger.info("mã thẻ hiển thị là:" + laymathe);
		if (laymathe.trim().equals("3140198909125“")) {
			// 3140198909125“ là của mã SV4019890912666
			// clickOn(FormDanhSachCungBHYT_ButtomClose);
			setTestcaseStatus("PASS", "Hiển thị thành công Số thẻ BHYT");
		} else {
			// clickOn(FormDanhSachCungBHYT_ButtomClose);
			setTestcaseStatus("FAIL", "Hiển thị số thẻ BHYT không thành công");
		}
	}

	@Test(priority = 4)
	public void DanhSachBNCungSoTheBHYT_11417_3() {
		TestLogger.info("[DSBN có cùng số thẻ BHYT] Validate các thông tin BHYT của BN");
		TestLogger.info("Kiểm tra thông tin trong cột từ ngày");
		TestLogger.info("Hiển thị đúng thông tin trong cột từ ngày");
		sleep(2);
		String laytungay = layTextTuAnh(688, 175, 82, 16);
		TestLogger.info(laytungay);
		if (laytungay.trim().equals("01/01/2018")) {
			setTestcaseStatus("PASS", "Hiển thị từ ngày thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị từ ngày không thành công");
		}
	}

	@Test(priority = 5)
	public void DanhSachBNCungSoTheBHYT_11417_4() {
		TestLogger.info("[DSBN có cùng số thẻ BHYT] Validate các thông tin BHYT của BN");
		TestLogger.info("Kiểm tra thông tin trong cột đến ngày");
		TestLogger.info("Hiển thị đúng thông tin trong cột đến ngày");
		sleep(2);
		String laydenngay = layTextTuAnh(788, 174, 85, 17);
		TestLogger.info(laydenngay);
		if (laydenngay.trim().equals("05/10/2030")) {
			setTestcaseStatus("PASS", "Hiển thị đến ngày thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị đến ngày không thành công");
		}
	}

	@Test(priority = 6)
	public void DanhSachBNCungSoTheBHYT_11417_5() {
		TestLogger.info("[DSBN có cùng số thẻ BHYT] Validate các thông tin BHYT của BN");
		TestLogger.info("Kiểm tra thông tin trong cột ngày đủ 5 năm");
		TestLogger.info("Hiển thị đúng thông tin trong cột ngày đủ 5 năm");
		sleep(2);
		String ngaydu5nam = layTextTuAnh(889, 175, 85, 17);
		TestLogger.info(ngaydu5nam);
		if (ngaydu5nam.trim().equals("01/01/2073")) {
			// 01-10-2073 tương đương ngày 01/02/2023
			setTestcaseStatus("PASS", "Hiển thị đến ngày thành công");
		} else {
			setTestcaseStatus("FAIL", "Hiển thị đến ngày không thành công");
		}
	}

	@Test(priority = 7)
	public void DanhSachBNCungSoTheBHYT_11417_6() {
		TestLogger.info("[DSBN có cùng số thẻ BHYT] Validate các thông tin BHYT của BN");
		TestLogger.info("Kiểm tra thông tin trong cột khu vực");
		TestLogger.info("Hiển thị đúng thông tin trong cột khu vực");
		sleep(2);
		String khuvuc = layTextTuAnh(1004, 153, 66, 15);
		TestLogger.info(khuvuc);
		if (khuvuc.trim().equals("Khu vu‘c")) {
			// Khu vực trống tương đương Khu vu‘c
			clickOn(FormDanhSachCungBHYT_ButtomClose);
			setTestcaseStatus("PASS", "Hiển thị khu vực thành công");
		} else {
			clickOn(FormDanhSachCungBHYT_ButtomClose);
			setTestcaseStatus("FAIL", "Hiển thị khu vực không thành công");
		}
	}

	@Test(priority = 8)
	public void DanhSachBNCungSoTheBHYT_9895() {
		TestLogger.info("Thông tin số thẻ BHYT chưa tồn tại trong hệ thống - Nhập tay");
		TestLogger.info(
				" Nhập thông tin số thẻ đầy đủ: (Bao gồm Số thẻ+ Mã ĐKKCB) , số thẻ này chưa tồn tại trên hệ thống");
		TestLogger.info("Không hiển thị popup \" danh sách bệnh nhân có cùng số thẻ BHYT\"");
		tnbn.dienSoTheBHYT("SV4013874590123");
		s.type(Key.ENTER);
		tnbn.dienMaDKKCB("01043");
		sleep(2);
		if (waitForObjectPresent(FormDanhSachCungBHYT_TitleForm, 5)) {
			clickOn(FormDanhSachCungBHYT_ButtomBoQua);
			setTestcaseStatus("FAIL", "Hiển thị popup");
		} else {
			setTestcaseStatus("PASS", "Không Hiển thị popup");
		}
	}

	// Các case về sau sử dụng thông tin như sau:
	// Ma BHYT: SV4014590865199
	// Mã ĐKKCB: 01005
	// Số tiếp nhận ban đầu 1802000018
	// Mã BN: 18000248 _ 18(XX)248
	@Test(priority = 9)
	public void DanhSachBNCungSoTheBHYT_9899() {
		TestLogger.info("Kiểm tra chức năng Chọn");
		TestLogger.info("Trong popup, chọn một bản ghi ");
		TestLogger.info(
				"Thông tin bệnh nhân trong bản ghi sẽ được đẩy xuống form tiếp nhận bệnh nhân, không sinh thêm mã BN, thừa hưởng lại mã BN đã có.");
		tnbn.dienSoTheBHYT("SV4014590865199");
		s.type(Key.ENTER);
		tnbn.dienMaDKKCB("01005");
		sleep(2);
		waitForObjectPresent(FormDanhSachCungBHYT_ButtomChon, 3);
		clickOn(FormDanhSachCungBHYT_ButtomChon);
		s.type(Key.ENTER);
		tnbn.chonHinhThuc("Tự đến");
		waitForObjectPresent(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu, 5);
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Luu);
		sleep(2);
		tnbn.dienSoTheBHYT("SV4014590865199");
		s.type(Key.ENTER);
		tnbn.dienMaDKKCB("01005");
		sleep(2);
		String kiemtrama = layTextTuAnh(364, 176, 92, 14);
		TestLogger.info("Mã bệnh nhân là: " + kiemtrama);
		clickToaDo(674, 180);
		s.type("c", Key.CTRL);

		String MaTN = App.getClipboard();
		l = TienIch.getSoTuChuoi(MaTN, 10);

		TestLogger.info(l.get(0));

		if (kiemtrama.trim().equals("18(XX)248")) {
			clickOn(FormDanhSachCungBHYT_ButtomBoQua);
			setTestcaseStatus("PASS", "Không phát sinh thêm mã bệnh nhân mới");
		} else {
			clickOn(FormDanhSachCungBHYT_ButtomBoQua);
			setTestcaseStatus("FAIL", "Phát sinh thêm mã bệnh nhân mới");
		}
	}

	@Test(priority = 10)
	public void DanhSachBNCungSoTheBHYT_11382() {
		TestLogger.info("Hiển thị thông tin sau khi cập nhật thông tin Hành chính");
		TestLogger.info("Cập nhật thông tin HC của BN ");
		TestLogger.info("Load ra đúng thông tin vừa sửa");
		clickOn(HIS_MenuTiepNhan);
		waitForObjectPresent(FormCapNhatThongTinHanhChinh.FormCapNhatThongTinHanhChinh_Menu, 4);
		clickOn(FormCapNhatThongTinHanhChinh.FormCapNhatThongTinHanhChinh_Menu);
		cntthc.dienSoTiepNhan1(l.get(0));
		clickOn(FormCapNhatThongTinHanhChinh.FormCapNhatThongTinHanhChinh_Sua);
		String hoten = "TRAN VAN LUC" + TienIch.taoRandomChu(5);
		cntthc.dienHoTen1(hoten);
		sleep(2);
		clickOn(FormCapNhatThongTinHanhChinh.FormCapNhatThongTinHanhChinh_luu);
		clickOn(FormCapNhatThongTinHanhChinh.FormCapNhatThongTinHanhChinh_CloseForm);
		sleep(2);
		tnbn.dienSoTheBHYT("SV4014590865199");
		tnbn.dienMaDKKCB("01005");
		String layhoten = layTextTuAnh(459, 174, 157, 18);
		if (layhoten.trim().equals(hoten)) {
			clickOn(FormDanhSachCungBHYT_ButtomClose);
			setTestcaseStatus("PASS", "Cập nhật thông tin thành công");
		} else {
			clickOn(FormDanhSachCungBHYT_ButtomClose);
			setTestcaseStatus("FAIL", "Cập nhật thông tin không thành công ");
		}
	}

	@Test(priority = 11)
	public void DanhSachBNCungSoTheBHYT_11383() {
		TestLogger.info("Hiển thị thông tin sau khi cập nhật thông tin BHYT");
		TestLogger.info("Cập nhật thông tin BHYT của BN tại màn hình viện phí");
		TestLogger.info("Load ra đúng thông tin vừa sửa");
		FormCapNhatDoiTuongVaChiPhi cndtvcp = new FormCapNhatDoiTuongVaChiPhi();

		clickOn(MenuVienPhi);
		waitForObjectPresent(FormCapNhatDoiTuongVaChiPhi.FormCapNhatDoiTuongVaChiPhi_Menu, 5);
		clickOn(FormCapNhatDoiTuongVaChiPhi.FormCapNhatDoiTuongVaChiPhi_Menu);
		//điền số tiếp nhận thứ nhất của bệnh nhân: 1802000072, từ ngày: 01/02/2017
		//Số tiếp nhận lần thứ hai: 1802000073
		cndtvcp.diensoTN("1802000073");
		sleep(2);
		//s.type(Key.ENTER); sleep(5);
		clickOn(FormCapNhatDoiTuongVaChiPhi.FormCapNhatDoiTuongVaChiPhi_Sua);
		//String tungay = TienIch.getNgayThangNamHienTaicuaMayTinh();
		//cndtvcp.dienTuNgay(tungay);
		cndtvcp.dienTuNgay("01052017");
		sleep(2);
		s.type(Key.ENTER);
		clickOn(FormCapNhatDoiTuongVaChiPhi.FormCapNhatDoiTuongVaChiPhi_Luu);
		sleep(2);
		s.type(Key.ENTER);
		clickOn(HIS_MenuTiepNhan);
		clickToaDo(209, 74);
		sleep(5);
		waitForObjectPresent(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBenhNhan, 3);
		clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_DanhSachBenhNhan);
		s.type("1802000073");
		s.type(Key.ENTER);
		dsbn.clickDupVaoBenhNhanChoKham();
		//clickOn(TiepNhanBenhNhan.TiepNhanBenhNhan_Sua);
		String laytungay = layTextTuAnh(99, 379, 94, 19);
		TestLogger.info(laytungay);
		if(laytungay.trim().equals("01/05/2017")) {
			setTestcaseStatus("PASS", "Load thông tin thành công");
		}else {
			setTestcaseStatus("FAIL", "Load Thông tin không thành công");
		}			
	}
	@AfterTest
	public void ketThucTestForm() {
		dangXuatKhoiTaikhoan();
		thoatUngdungHIS();
	}

}
