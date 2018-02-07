package HIS;

import org.sikuli.script.Key;

import desktop_Framework.HisActions;

public class FormCapNhatDoiTuongVaChiPhi extends HisActions {
	public static String FormCapNhatDoiTuongVaChiPhi_Title = "FormCapNhatDoiTuongVaChiPhi_Title.png";
	public static String FormCapNhatDoiTuongVaChiPhi_SoTN = "FormCapNhatDoiTuongVaChiPhi_SoTN.png";
	public static String FormCapNhatDoiTuongVaChiPhi_Sua = "FormCapNhatDoiTuongVaChiPhi_Sua.png";
	public static String FormCapNhatDoiTuongVaChiPhi_Menu = "FormCapNhatDoiTuongVaChiPhi_Menu.png";
	public static String FormCapNhatDoiTuongVaChiPhi_Luu= "FormCapNhatDoiTuongVaChiPhi_Luu.png";
	public static String FormCapNhatDoiTuongVaChiPhi_TuNgay= "FormCapNhatDoiTuongVaChiPhi_TuNgay.png";
	
	public void diensoTN(String sotiepnhan) {
		moveMouseHorizontallyFromLogo(FormCapNhatDoiTuongVaChiPhi_SoTN, 84);
		s.click();
		s.type("a",Key.CTRL);
		s.type(sotiepnhan);
		s.type(Key.ENTER);
		
	}
	public void dienTuNgay(String tungay) {
		moveMouseHorizontallyFromLogo(FormCapNhatDoiTuongVaChiPhi_TuNgay, 90);
		s.click();
		s.type("a",Key.CTRL);
		s.type(tungay);
		s.type(Key.ENTER);
		
		
	}
	
	
	
}
