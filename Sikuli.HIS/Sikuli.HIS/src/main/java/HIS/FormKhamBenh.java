package HIS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sikuli.script.Key;

import desktop_Framework.HisActions;

public class FormKhamBenh extends HisActions {
  
	public static String FormKhamBenh_soTiepNhan = "FormKhamBenh_soTiepNhan.png";
	public static String FormKhamBenh_TimKiem = "FormKhamBenh_TimKiem.png";
	public static String FormKhamBenh_soDongBang1 = "FormKhamBenh_soDongBang1.png";
	public static String FormKhamBenh_doChiSoSinhTon = "FormKhamBenh_doChiSoSinhTon.png";
	public static String FormKhamBenh_KhamBenh = "FormKhamBenh_KhamBenh.png";
	public static String FormKhamBenh_KetLuan = "FormKhamBenh_KetLuan.png";
	public static String FormKhamBenh_LichSuKhamBenh = "FormKhamBenh_LichSuKhamBenh.png";
	public static String FormKhamBenh_LidoKham = "FormKhamBenh_LidoKham.png";
	public static String FormKhamBenh_BenhChinh = "FormKhamBenh_BenhChinh.png";
	public static String FormKhamBenh_Luu = "FormKhamBenh_Luu.png";
	public static String FormKhamBenh_DiUng = "FormKhamBenh_DiUng.png";
	public static String FormKhamBenh_BenhManTinh = "FormKhamBenh_BenhManTinh.png";
	public static String FormKhamBenh_SuaNhomMau = "FormKhamBenh_SuaNhomMau.png";
	public static String FormKhamBenh_NhomMau = "FormKhamBenh_NhomMau.png";
	public static String FormKhamBenh_YeuToRH = "FormKhamBenh_YeuToRH.png";
	public static String FormKhamBenh_Mach = "FormKhamBenh_Mach.png";
	public static String FormKhamBenh_HuyetAp = "FormKhamBenh_HuyetAp.png";
	public static String FormKhamBenh_NhietDo = "FormKhamBenh_NhietDo.png";
	public static String FormKhamBenh_NhipTho = "FormKhamBenh_NhipTho.png";
	public static String FormKhamBenh_CanNang = "FormKhamBenh_CanNang.png";
	public static String FormKhamBenh_ChieuCao = "FormKhamBenh_ChieuCao.png";
	public static String FormKhamBenh_BMI = "FormKhamBenh_BMI.png";
	public static String FormKhamBenh_LuuChiSoSinhTon = "FormKhamBenh_LuuChiSoSinhTon.png";
	public static String FormKhamBenh_HuongDieuTri = "FormKhamBenh_HuongDieuTri.png";
	public static String FormKhamBenh_HuongDieuTri_ChoVeKhongToa = "FormKhamBenh_HuongDieuTri_ChoVeKhongToa.png";
	public static String FormKhamBenh_HuongDieuTri_ChoVeKeToa = "FormKhamBenh_HuongDieuTri_ChoVeKeToa.png";
	public static String FormKhamBenh_HuongDieuTri_TuVong = "FormKhamBenh_HuongDieuTri_TuVong.png";

	
	public static String FormKhamBenh_LuuVaHoanThanh = "FormKhamBenh_LuuVaHoanThanh.png";
	public static String FormKhamBenh_TrieuChungLamSang = "FormKhamBenh_TrieuChungLamSang.png";
	public static String FormKhamBenh_ChiDinhCanLamSang = "FormKhamBenh_ChiDinhCanLamSang.png";
	public static String FormKhamBenh_LamMoi = "FormKhamBenh_LamMoi.png";
	public static String FormKhamBenh_ChonMaDichVu = "FormKhamBenh_ChonMaDichVu.png";
	public static String FormKhamBenh_TenDichVu = "FormKhamBenh_TenDichVu.png";
	public static String FormKhamBenh_ThuTienSau = "FormKhamBenh_ThuTienSau.png";
	public static String FormKhamBenh_LuuChiDinhCanLamSang = "FormKhamBenh_LuuChiDinhCanLamSang.png";
	
	public static String FormKhamBenh_KhoThuoc = "FormKhamBenh_KhoThuoc.png";
	public static String FormKhamBenh_NguonThuoc = "FormKhamBenh_NguonThuoc.png";
	public static String FormKhamBenh_TenThuoc = "FormKhamBenh_TenThuoc.png";
	public static String FormKhamBenh_SoLuong = "FormKhamBenh_SoLuong.png";
	public static String FormKhamBenh_LieuDung = "FormKhamBenh_LieuDung.png";
	public static String FormKhamBenh_CachDung = "FormKhamBenh_CachDung.png";
	public static String FormKhamBenh_GhiChu = "FormKhamBenh_GhiChu.png";
	public static String FormKhamBenh_KhoThuocNgoaiTru = "FormKhamBenh_KhoThuocNgoaiTru.png";
	public static String FormKhamBenh_NguonThuocDauThau = "FormKhamBenh_NguonThuocDauThau.png";
	public static String FormKhamBenh_TenThuocEpocassa = "FormKhamBenh_TenThuocEpocassa.png";
	public static String FormKhamBenh_NhapVien = "FormKhamBenh_NhapVien.png";
	public static String FormKhamBenh_KhoaNhapVien = "FormKhamBenh_KhoaNhapVien.png";
	public static String FormKhamBenh_KhamThem = "FormKhamBenh_KhamThem.png";
	public static String FormKhamBenh_DichVuKhamThem = "FormKhamBenh_DichVuKhamThem.png";
	public static String FormKhamBenh_HenTaiKham = "FormKhamBenh_HenTaiKham.png";
	public static String FormKhamBenh_ChuyenVien = "FormKhamBenh_ChuyenVien.png";
	public static String FormKhamBenh_BenhVienChuyen = "FormKhamBenh_BenhVienChuyen.png";
	public static String FormKhamBenh_LyDoChuyen = "FormKhamBenh_LyDoChuyen.png";
	public static String FormKhamBenh_InGiayChuyenVien = "FormKhamBenh_InGiayChuyenVien.png";
	public static String FormKhamBenh_GiayChuyenVien = "FormKhamBenh_GiayChuyenVien.png";
	

	public static String FormKhamBenh_KetQuaCanLamSang = "FormKhamBenh_KetQuaCanLamSang.png";
	public static String FormKhamBenh_CDHA_TDCN = "FormKhamBenh_CDHA_TDCN.png";
	public static String FormKhamBenh_In = "FormKhamBenh_In.png";
	public static String FormKhamBenh_SoPhieu = "FormKhamBenh_SoPhieu.png";
	public static String FormKhamBenh_PhieuKhamThem = "FormKhamBenh_PhieuKhamThem.png";

	public static String HuongDieuTri_KetoaChoVe = "HuongDieuTri_KetoaChoVe.png";
	public static String InGiayOm = "InGiayOm.png";
	public static String KhoThuoc = "KhoThuoc.png";
	public static String PhieuChiDinh = "PhieuChiDinh.png";
	public static String NguonThuoc = "NguonThuoc.png";
	public static String NhaThuocBenhVien = "NhaThuocBenhVien.png";
	public static String NguonThuoc_DauThau = "NguonThuoc_DauThau.png";
	public static String TenThuoc = "TenThuoc.png";
	public static String KhoThuoc_TuTrucKhoaNgoai = "KhoThuoc_TuTrucKhoaNgoai.png";
	public static String FormKhamBenh_NutIn = "FormKhamBenh_NutIn.png";
	public static String FormKhamBenh_NutInGiayNghiOm = "FormKhamBenh_NutInGiayNghiOm.png";
	public static String FormKhamBenh_NutInDonThuoc = "FormKhamBenh_NutInDonThuoc.png";
	public static String FormKhamBenh_NutInGiayKhamBenh = "FormKhamBenh_NutInGiayKhamBenh.png";

	public static String FormKhamBenh_GiayChungNhanNghiOm = "FormKhamBenh_GiayChungNhanNghiOm.png";
	public static String FormKhamBenh_DonThuoc = "FormKhamBenh_DonThuoc.png";
	public static String FormKhamBenh_PhieuKhamBenh = "FormKhamBenh_PhieuKhamBenh.png";
	public static String FormKhamBenh_InPhieuKhamBenh = "FormKhamBenh_InPhieuKhamBenh.png";
	public static String FormKhamBenh_InPhieuKhamThem = "FormKhamBenh_InPhieuKhamThem.png";
	public static String FormKhamBenh_InPhieuHenKhamLai = "FormKhamBenh_InPhieuHenKhamLai.png";
	public static String FormKhamBenh_PhieuHenKhamLai = "FormKhamBenh_PhieuHenKhamLai.png";
	public static String FormKhamBenh_InDonThuoc = "FormKhamBenh_InDonThuoc.png";
	

	public static String KeToaNhanh = "KeToaNhanh.png";
	public static String LichSuKhamBenh = "LichSuKhamBenh.png";
	public static String Menu_LichSuKhamBenh = "Menu_LichSuKhamBenh.png";
	public static String HuongDieuTri_NhapVien = "HuongDieuTri_NhapVien.png";
	public static String KhoaNhap_KhoaGayMeHoiSuc = "KhoaNhap_KhoaGayMeHoiSuc.png";
	public static String HuongDieuTri_KhamThem = "HuongDieuTri_KhamThem.png";
	public static String DichVuKham_KhamNoi01 = "DichVuKham_KhamNoi01.png";
	public static String KhoThuoc_KhoLeNoiTru = "KhoThuoc_KhoLeNoiTru.png";
	public static String FormKhamBenh_NutInGiayKhamThem = "FormKhamBenh_NutInGiayKhamThem.png";
	public static String KhoThuoc_KhoChinh = "KhoThuoc_KhoChinh.png";
	public static String HuongDieuTri_TaiKham = "HuongDieuTri_TaiKham.png";
	public static String HuongDieuTri_DieuTriNgoaiTru = "HuongDieuTri_DieuTriNgoaiTru.png";
	public static String HuongDieuTri_ChuyenVien = "HuongDieuTri_ChuyenVien.png";
	public static String ChuyenDen_BenhVienThanhNhan = "ChuyenDen_BenhVienThanhNhan.png";
	public static String Nut_ThongTinChuyenVien = "Nut_ThongTinChuyenVien.png";
	public static String ThongTinChuyenVien_ChuanDoan = "ThongTinChuyenVien_ChuanDoan.png";
	public static String ThongTinChuyenVien_Luu = "ThongTinChuyenVien_Luu.png";
	public static String FormKhamBenh_GiayChuyenTuyen = "FormKhamBenh_GiayChuyenTuyen.png";
	public static String FormKhamBenh_GiayKhamBenh = "FormKhamBenh_GiayKhamBenh.png";
	public static String HuongDieuTri_Chet = "HuongDieuTri_Chet.png";
	public static String Menu_DanhSachBenhNhan = "Menu_DanhSachBenhNhan.png";
	
    public void dienKhoThuoc(String khothuoc)
    {
    	moveMouseHorizontallyFromLogo(KeToaNhanh, -110);
    	s.click();
    	s.type("a",Key.CTRL);
    	s.type(Key.BACKSPACE);
    	setClipboardValue(khothuoc);
    	s.type("v",Key.CTRL);
    	s.type(Key.ENTER);
    }
    
  
    public void dienNguonThuoc(String tenNguonThuoc) {
		moveMouseDownFromLogo(FormKhamBenh_NguonThuoc, 33);
		s.click();
		s.type("a",Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(tenNguonThuoc);
		s.type("c",Key.CTRL);				
	}
    
    public void dienTenThuoc(String tenthuoc)
    {
    	moveMouseDownFromLogo(TenThuoc, 53);
    	s.click();
    	setClipboardValue(tenthuoc);
    	s.type("v",Key.CTRL);
    	sleep(2);
    	s.type(Key.ENTER);
    }
    
	public void dienSoTiepNhan(String soTiepNhan)
	{
		
		clickOn(FormKhamBenh_soTiepNhan);
		s.type(soTiepNhan);
		
	}
	
	public void dienHoTen(String hoTenBenhNhan)
	{
		clickToaDo(854, 120);
		s.type("a",Key.CTRL);
		s.type(Key.BACKSPACE);
		s.type(hoTenBenhNhan);
	}
	
	public void clickTimKiem()
	{
		clickOn(FormKhamBenh_TimKiem);
	}
	 
	public String getSophieuTuChuoi(String chuoi)
	{
		
		
		Matcher m = Pattern.compile(".+\\s(\\d+)\\s\\d\\d\\/").matcher(chuoi);

		//Matcher m1 = Pattern.compile("\\d{9}").matcher(chuoi);
		    if(m.find()){
		        System.out.println(m.group(1));
		        return m.group(1);
		    }
		    else
		    {
		    	return "";
		    }
		
	}
	
	public void chonTenDichVu(String tenDichVu)
	{
		moveMouseDownFromLogo(FormKhamBenh_TenDichVu, 39);
		s.click();
		setClipboardValue(tenDichVu);
		s.type("v" ,Key.CTRL);
		s.type(Key.ENTER);
	}
	
	 
		public void chonMaDichVu(String maDichVu)
		{
			clickToaDo(513, 280);
			// moveMouseDownFromLogo(FormKhamBenh_ChonMaDichVu, 39);
			s.type(maDichVu);
			sleep(1);
			s.type(Key.ENTER);
		}
	
	
	public void dienTrieuChungLS(String trieuchung)
	{
		moveMouseHorizontallyFromLogo(FormKhamBenh_TrieuChungLamSang, 100);
		s.click();
		setClipboardValue(trieuchung);
		s.type("v",Key.CTRL);
	}
	
	public void dienLiDoKham(String lidokham)
	{
		moveMouseHorizontallyFromLogo(FormKhamBenh_LidoKham, 100);
		s.click();
		setClipboardValue(lidokham);
		s.type("v",Key.CTRL);
		
	}
	
	public void dienBenhChinh(String benhchinh)
	{
		moveMouseHorizontallyFromLogo(FormKhamBenh_BenhChinh, 100);
		s.click();
		setClipboardValue(benhchinh);
		s.type("v",Key.CTRL);
	}
	
	public void dienBenhChinhDefault()
	{
		moveMouseHorizontallyFromLogo(FormKhamBenh_BenhChinh, 100);
		s.click();
		s.type(Key.SPACE);
		s.type(Key.ENTER);
	}
	
	public String getNoiDungBenhChinh()
	{
		moveMouseHorizontallyFromLogo(FormKhamBenh_BenhChinh, 350);
		s.click();
		s.type("a",Key.CTRL);
		s.type("c",Key.CTRL);
		return getClipboardValue();
	}
	
	public void dienDiUng(String benhDiUng)
	{
		moveMouseDownFromLogo(FormKhamBenh_DiUng, 53);
		setClipboardValue(benhDiUng);
		s.click();
		s.type("v", Key.CTRL);
		
	}
	
	public void dienBenhManTinh(String benhManTinh)
	{
		moveMouseDownFromLogo(FormKhamBenh_BenhManTinh, 53);
		setClipboardValue(benhManTinh);
		s.click();
		s.type("v", Key.CTRL);
	}
	
	public void chonNhomMau(String nhommau)
	{
		moveMouseHorizontallyFromLogo(FormKhamBenh_NhomMau, 87);
		s.click();
		if (nhommau.equalsIgnoreCase("A"))
		{
			s.type(Key.DOWN);
			s.type(Key.ENTER);
		}
		
		if (nhommau.equalsIgnoreCase("AB"))
		{
			s.type(Key.DOWN);
			s.type(Key.DOWN);
			s.type(Key.ENTER);
		}
		if (nhommau.equalsIgnoreCase("B"))
		{
			s.type(Key.DOWN);
			s.type(Key.DOWN);
			s.type(Key.DOWN);
			s.type(Key.ENTER);
		}
		
		if (nhommau.equalsIgnoreCase("C"))
		{
			s.type(Key.DOWN);
			s.type(Key.DOWN);
			s.type(Key.DOWN);
			s.type(Key.DOWN);
			s.type(Key.ENTER);
		}
		
	}
	
	public void chonYeuToRH(String RH)
	{
		moveMouseHorizontallyFromLogo(FormKhamBenh_YeuToRH, 87);
		s.click();
		if (RH.equalsIgnoreCase("-"))
		{
			s.type(Key.DOWN);
			s.type(Key.ENTER);
		}
		
		if (RH.equalsIgnoreCase("+"))
		{
			s.type(Key.DOWN);
			s.type(Key.DOWN);
			s.type(Key.ENTER);
		}
	
	}
	
	
	public String getTenKhoThuoc() {
		setClipboardValue("");
		waitForObjectPresent(FormKhamBenh_KhoThuoc, 5);
		moveMouseDownFromLogo(FormKhamBenh_KhoThuoc, 33);
		s.click();
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();		
	}
	
	public String getTenNguonThuoc() {
		setClipboardValue("");
		waitForObjectPresent(FormKhamBenh_NguonThuoc, 5);
		moveMouseDownFromLogo(FormKhamBenh_NguonThuoc, 33);
		s.click();
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();		
	}
	
	public String getTenThuoc() {
		setClipboardValue("");
		waitForObjectPresent(FormKhamBenh_TenThuoc, 5);
		moveMouseDownFromLogo(FormKhamBenh_TenThuoc, 33);
		s.click();
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();		
	}
	public void dienSoLuongThuoc(String soLuongThuoc) {
		moveMouseDownFromLogo(FormKhamBenh_SoLuong, 33);
		s.click();
		s.type("a",Key.CTRL);
		s.type(Key.DELETE);
		s.type(soLuongThuoc);				
	}
	public String getSoLuongThuoc() {
		setClipboardValue("");
		waitForObjectPresent(FormKhamBenh_SoLuong, 5);
		moveMouseDownFromLogo(FormKhamBenh_SoLuong, 33);
		s.click();
		s.type("a", Key.CTRL);
		s.type("c", Key.CTRL);
		return getClipboardValue();		
	}
	public void dienLieuDung(String lieuDung) {
		moveMouseDownFromLogo(FormKhamBenh_LieuDung, 55);
		s.click();
		s.type("a",Key.CTRL);
		s.type(Key.DELETE);
		setClipboardValue(lieuDung);
		s.type("v",Key.CTRL);	
				
	}
	public String getLieuDung() {
		setClipboardValue("");
		waitForObjectPresent(FormKhamBenh_LieuDung, 5);
		moveMouseDownFromLogo(FormKhamBenh_LieuDung, 33);
		s.click();
		s.type("a", Key.CTRL);
		String cachDung = getClipboardValue();		
		return getClipboardValue();		
	}
	public void dienCachDung(String cachDung) {
		moveMouseDownFromLogo(FormKhamBenh_CachDung, 33);
		s.click();
		s.type("a",Key.CTRL);
		setClipboardValue(cachDung);
		s.type("v",Key.CTRL);					
	}
	public String getCachDung() {
		setClipboardValue("");
		waitForObjectPresent(FormKhamBenh_CachDung, 5);
		moveMouseDownFromLogo(FormKhamBenh_CachDung, 33);
		s.click();
		s.type("a", Key.CTRL);
		String cachDung = getClipboardValue();
		return getClipboardValue();		
	}
	public void dienGhiChu(String ghiChu) {
		moveMouseDownFromLogo(FormKhamBenh_GhiChu, 33);
		s.click();
		s.type("a",Key.CTRL);
	    setClipboardValue(ghiChu);
		s.type("v",Key.CTRL);				
	}
	public String getGhiChu() {
		setClipboardValue("");
		waitForObjectPresent(FormKhamBenh_GhiChu, 5);
		moveMouseDownFromLogo(FormKhamBenh_GhiChu, 33);
		s.click();
		s.type("a", Key.CTRL);
		String ghiChu = getClipboardValue();
		return getClipboardValue();		
	}
	
	
	

}


	


