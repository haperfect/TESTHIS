package HIS;

import org.sikuli.script.Key;

import desktop_Framework.HisActions;

public class FormChuyenPhongKham extends HisActions {
	public static String FormChuyenPhongKham_SubMenu = "FormChuyenPhongKham_SubMenu.png";
	public static String FormChuyenPhongKham_SoTiepNhan = "FormChuyenPhongKham_SoTiepNhan.png";
	public static String FormChuyenPhongKham_TenDichVu = "FormChuyenPhongKham_TenDichVu.png";
	public static String FormChuyenPhongKham_PhongKham = "FormChuyenPhongKham_PhongKham.png";
	public static String FormChuyenPhongKham_Sua = "FormChuyenPhongKham_Sua.png";
	public static String FormChuyenPhongKham_ChuyenPhongKham = "FormChuyenPhongKham_ChuyenPhongKham.png";
	public static String FormChuyenPhongKham_BoQua = "FormChuyenPhongKham_BoQua.png";
	public static String FormChuyenPhongKham_Title = "FormChuyenPhongKham_Title.png";
	public static String FormChuyenPhongKham_MessageLoi = "FormChuyenPhongKham_MessageLoi.png";

	public void dienSoTiepNhan1(String SoTiepNhan1) {
		waitForObjectPresent(FormChuyenPhongKham_SoTiepNhan, 5);
		moveMouseHorizontallyFromLogo(FormChuyenPhongKham_SoTiepNhan, 70);
		s.click();
		setClipboardValue("");
		// moveMouseHorizontallyFromLogo(FormChuyenPhongKham_SoTiepNhan, 70);
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(SoTiepNhan1);
		s.type("v", Key.CTRL);
		s.type(Key.ENTER);
	}

	public String getSoTiepNhan1() {
		setClipboardValue("");
		moveMouseHorizontallyFromLogo(FormChuyenPhongKham_SoTiepNhan, 70);
		s.click();
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		String soTiepNhan1 = getClipboardValue();
		return soTiepNhan1;
	}

	public void dienPhongKham1(String PhongKham) {
		waitForObjectPresent(FormChuyenPhongKham_PhongKham, 5);
		moveMouseDownFromLogo(FormChuyenPhongKham_PhongKham, 25);
		s.click();
		s.type("a", Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(PhongKham);
		s.type("v", Key.CTRL);
		s.type(Key.ENTER);
	}

	public String getPhongKham1() {
		setClipboardValue("");
		waitForObjectPresent(FormChuyenPhongKham_PhongKham, 5);
		moveMouseDownFromLogo(FormChuyenPhongKham_PhongKham, 25);
		s.click();
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		String PhongKham = getClipboardValue();
		return PhongKham;
	}
}
