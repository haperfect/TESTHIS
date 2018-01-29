package HIS;

import org.sikuli.api.robot.Key;

import desktop_Framework.HisActions;

public class FormXacNhanBaoHiemYTe extends HisActions{

public static String FormXacNhanBaoHiemYTe_SoTN = "FormXacNhanBaoHiemYTe_SoTN.png";
public static String  FormXacNhanBaoHiemYTe_Xacnhan = "FormXacNhanBaoHiemYTe_Xacnhan.png";
public static String FormXacNhanBaoHiemYTe_dongy = "FormXacNhanBaoHiemYTe_dongy.png";

public void dienSotiepNhan(String SoTiepNhan) {
	moveMouseDownFromLogo(FormXacNhanBaoHiemYTe_SoTN, 60);
	s.click();
	s.type(SoTiepNhan);
	s.type(Key.ENTER);
	
}
}
