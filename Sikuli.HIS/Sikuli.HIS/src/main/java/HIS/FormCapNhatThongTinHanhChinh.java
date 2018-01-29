package HIS;

import org.sikuli.script.Key;

import desktop_Framework.HisActions;

public class FormCapNhatThongTinHanhChinh extends HisActions {
	public static String FormCapNhatThongTinHanhChinh_TitleForm = "FormCapNhatThongTinHanhChinh_TitleForm.png";
	public static String FormCapNhatThongTinHanhChinh_SoTiepNhan = "FormCapNhatThongTinHanhChinh_SoTiepNhan.png";
	public static String FormCapNhatThongTinHanhChinh_HoTen = "FormCapNhatThongTinHanhChinh_HoTen.png";
	public static String FormCapNhatThongTinHanhChinh_NgaySinh = "FormCapNhatThongTinHanhChinh_NgaySinh.png";
	public static String FormCapNhatThongTinHanhChinh_GioiTinhNam = "FormCapNhatThongTinHanhChinh_GioiTinhNam.png";
	public static String FormCapNhatThongTinHanhChinh_GioiTinhNu = "FormCapNhatThongTinHanhChinh_GioiTinhNu.png";
	public static String FormCapNhatThongTinHanhChinh_GioiTinhKhongXacDinh = "FormCapNhatThongTinHanhChinh_GioiTinhKhongXacDinh.png";
	public static String FormCapNhatThongTinHanhChinh_SoCMND = "FormCapNhatThongTinHanhChinh_SoCMND.png";
	public static String FormCapNhatThongTinHanhChinh_QuocTich = "FormCapNhatThongTinHanhChinh_QuocTich.png";
	public static String FormCapNhatThongTinHanhChinh_TinhThanhPho = "FormCapNhatThongTinHanhChinh_TinhThanhPho.png";
	public static String FormCapNhatThongTinHanhChinh_PhuongXa = "FormCapNhatThongTinHanhChinh_PhuongXa.png";
	public static String FormCapNhatThongTinHanhChinh_NoiLamViec = "FormCapNhatThongTinHanhChinh_NoiLamViec.png";
	public static String FormCapNhatThongTinHanhChinh_NguoiLienHe = "FormCapNhatThongTinHanhChinh_NguoiLienHe.png";
	public static String FormCapNhatThongTinHanhChinh_NamSinh = "FormCapNhatThongTinHanhChinh_NamSinh.png";
	public static String FormCapNhatThongTinHanhChinh_NgheNghiep = "FormCapNhatThongTinHanhChinh_NgheNghiep.png";
	public static String FormCapNhatThongTinHanhChinh_SoDienThoai = "FormCapNhatThongTinHanhChinh_SoDienThoai.png";
	public static String FormCapNhatThongTinHanhChinh_DanToc = "FormCapNhatThongTinHanhChinh_DanToc.png";
	public static String FormCapNhatThongTinHanhChinh_QuanHuyen = "FormCapNhatThongTinHanhChinh_QuanHuyen.png";
	public static String FormCapNhatThongTinHanhChinh_SoNha = "FormCapNhatThongTinHanhChinh_SoNha.png";
	public static String FormCapNhatThongTinHanhChinh_SDTLienHe = "FormCapNhatThongTinHanhChinh_SDTLienHe.png";
	public static String FormCapNhatThongTinHanhChinh_Sua = "FormCapNhatThongTinHanhChinh_Sua.png";
	public static String FormCapNhatThongTinHanhChinh_luu = "FormCapNhatThongTinHanhChinh_luu.png";
	public static String FormCapNhatThongTinHanhChinh_BoQua = "FormCapNhatThongTinHanhChinh_BoQua.png";
	public static String FormCapNhatThongTinHanhChinh_MessageLoi = "FormCapNhatThongTinHanhChinh_MessageLoi.png";
	public static String FormCapNhatThongTinHanhChinh_SapXepTinh = "FormCapNhatThongTinHanhChinh_SapXepTinh.png";
	public static String FormCapNhatThongTinHanhChinh_CloseForm = "FormCapNhatThongTinHanhChinh_CloseForm.png";
	
	
	

	public void dienSoTiepNhan1(String soTN) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(462, 215);
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(soTN);
		s.type("v", Key.CTRL);
	}

	public String getSoTiepNhan1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(462, 215);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);		
		String soTN = getClipboardValue();
		return soTN;
	}

	public void dienHoTen1(String HoTen1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(525, 246);
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(HoTen1);
		s.type("v", Key.CTRL);
	}

	public String getHoTen1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(525, 246);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		String HoTen1 = getClipboardValue();
		return HoTen1;
	}

	public void dienNgaySinh1(String ngaySinh1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(486, 278);
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		s.type(ngaySinh1);
		//setClipboardValue(ngaySinh1);
		//s.type("v", Key.CTRL);
	}

	public String getNgaySinh1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(486, 278);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		String ngaySinh1 = getClipboardValue();
		return ngaySinh1;
	}

	public void dienNamSinh1(String namSinh1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(828, 277);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type(namSinh1);
	}

	public String getNamSinh1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(828, 277);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		String namSinh1 = getClipboardValue();
		return namSinh1;
	}

	public void dienSoCMND1(String soCMTND1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(487, 341);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type(soCMTND1);
	}

	public String getSoCMND1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(487, 341);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		String soCMTND1 = getClipboardValue();
		return soCMTND1;
	}

	public void dienSoDT1(String soDT1) {

		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(809, 342);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type(soDT1);
	}

	public String getSoDT1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(809, 342);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		String soDT1 = getClipboardValue();
		return soDT1;
	}

	public void dienQuocTich1(String QuocTich1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(470, 373);
		s.type("a", Key.CTRL);
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(QuocTich1);
		s.type("v", Key.CTRL);
		s.type(Key.ENTER);
	}

	public String getQuocTich1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(470, 373);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}

	public void dienThanhPho1(String Thanhpho1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(474, 405);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		setClipboardValue(Thanhpho1);
		s.type("v", Key.CTRL);
	}

	public String getThanhPho1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(474, 405);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}

	public void dienQuanHuyen1(String QuanHuyen1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(815, 405);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		setClipboardValue(QuanHuyen1);
		s.type("v", Key.CTRL);
	}

	public String getQuanHuyen1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(815, 405);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}

	public void dienPhuongXa1(String PhuongXa1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(479, 439);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		setClipboardValue(PhuongXa1);
		s.type("v", Key.CTRL);
	}

	public String getPhuongXa1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(479, 439);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}

	public void dienSoNha1(String SoNha1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
        clickToaDo(816, 437);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		setClipboardValue(SoNha1);
		s.type("v", Key.CTRL);
	}
	public String getSoNha1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(816, 437);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}


	public void chonGioiTinhNam1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(484, 312);
	}

	public void chonGioiTinhNu1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(530, 311);
	}

	public void chonGioiTinhChuaXacDinh1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(589, 312);
	}

	public void dienNoiLamViec1(String NoiLamViec1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(468, 470);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type(NoiLamViec1);
	}

	public String getNoiLamViec1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(468, 470);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}

	public void dienNguoiLienHe1(String NguoiLienHe1) {

		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(472, 501);
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(NguoiLienHe1);
		s.type("v", Key.CTRL);
	}

	public String getNguoiLienHe1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(472, 501);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}

	public void dienSoDienThoaiNguoiLienHe1(String SoDTnguoiLienHe1) {

		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(810, 501);
		s.type("a", Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type(SoDTnguoiLienHe1);
	}
	public String getSoDienThoaiNguoiLienHe1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(810, 501);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}
	public void dienDanToc1(String DanToc) {

		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(817, 373);
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(DanToc);
		s.type("v", Key.CTRL);
	}
	public String getDanToc1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(817, 373);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
	}
	public void dienNgheNghiep1(String NgheNghiep1) {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(815, 309);
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(NgheNghiep1);
		s.type("v", Key.CTRL);
		s.type(Key.ENTER);
	}	
	public String getNgheNghiep1() {
		waitForObjectPresent(FormCapNhatThongTinHanhChinh_TitleForm, 5);
		clickToaDo(815, 309);
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();
}
}
