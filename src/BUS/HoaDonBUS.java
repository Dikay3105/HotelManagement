package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class HoaDonBUS {

    public static ArrayList<HoaDonDTO> getListHD() {
        ArrayList<HoaDonDTO> listHD = new ArrayList<>();
        for (HoaDonDTO x : HoaDonDAO.getListHD()) {
            if (x.getXuLy() == 0) {
                listHD.add(x);
            }
        }
        return listHD;
    }

    public static void insertHoaDon(HoaDonDTO hd) throws SQLException, ParseException {
        HoaDonDAO.InsertHoaDon(hd);
    }

    public static int getSize() {
        return HoaDonDAO.getListHoaDon().size() + 1;
    }

    public static int tienPhong(int month) {
        return HoaDonDAO.tienPhong(month);
    }

    public static ArrayList<Integer> getListYear() {
        return HoaDonDAO.getListYear();
    }

    public static String getMaNV(String maHD) {
        return HoaDonDAO.getMaNV(maHD);
    }

    public static HoaDonDTO getHoaDon(String maCTT) {
        return HoaDonDAO.getHoaDon(maCTT);
    }

    public static int tienPhong() {
        return HoaDonDAO.tienPhong();
    }

    public static int tienDichVu() {
        return HoaDonDAO.tienDichVu();
    }

    public static int tongTien() {
        return HoaDonDAO.tongTien();
    }

    public static ArrayList<Integer> getListYear_1() {
        return HoaDonDAO.getListYear_1();
    }

    public static ArrayList<Integer> getTienNam(ArrayList<Integer> tienPh, ArrayList<Integer> tienDV, ArrayList<Integer> listTien, int year) {
        return HoaDonDAO.getTienNam(tienPh, tienDV, listTien, year);
    }

    public static ArrayList<Integer> getTienThang(ArrayList<Integer> tienPh, ArrayList<Integer> tienDV, int month, int year) {
        return HoaDonDAO.getTienThang(tienPh, tienDV, month, year);
    }
}
