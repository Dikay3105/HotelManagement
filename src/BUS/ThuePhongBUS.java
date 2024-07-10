package BUS;

import DAO.ChiTietThueDAO;
import DAO.ThuePhongDAO;
import DTO.PhongDTO;
import DTO.ThuePhongDTO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ThuePhongBUS {

    public static boolean insertTP(ThuePhongDTO tp) {
        if (ChiTietThueDAO.checkMaCTT(tp.getMaChiTietThue())) {
            if (ThuePhongDAO.checkTP(tp.getMaChiTietThue(), tp.getMaP(), tp.getNgayThue())) {
                return false;
            } else {
                return ThuePhongDAO.insertTP(tp);
            }
        }
        return false;
//        return ThuePhongDAO.insertTP(tp);
    }

    public static ThuePhongDTO getThuePhong(String maP) {
        return ThuePhongDAO.getThuePhong(maP);
    }

    public static ArrayList<ThuePhongDTO> getListTP_P(String maCTT, ArrayList<PhongDTO> listP) {
        return ThuePhongDAO.getListTP_P(maCTT, listP);
    }

    public static boolean deleteTP(String maCTT, String maP, String date) {
        return ThuePhongDAO.deleteTP(maCTT, maP, date);
    }

    public static boolean updateCheckOut(String maCTT, String maP, String dateThue, String dateCheckOut) {
        return ThuePhongDAO.updateCheckOut(maCTT, maP, dateThue, dateCheckOut);
    }

    public static boolean updateTT(String maCTT, String maP, String dateThue, int tt) {
        return ThuePhongDAO.updateTT(maCTT, maP, dateThue, tt);
    }

    public static boolean updateTTAll(String maCTT) {
        return ThuePhongDAO.updateTTAll(maCTT);
    }

    public static ArrayList<ThuePhongDTO> getListTP() {
        ArrayList<ThuePhongDTO> list = new ArrayList<>();
        for (ThuePhongDTO x : ThuePhongDAO.LoadData()) {
            if (x.getXuLy() == 0) {
                list.add(x);
            }
        }
        return list;
    }

    //Vĩnh Khoa
    public static ArrayList<ThuePhongDTO> LoadData() {
        return ThuePhongDAO.LoadData();
    }

    public static void UpdateTinhTrang(String mctt, String maP, String ngayThue, String ngayCheckOut, int i) throws SQLException, ParseException {
        ThuePhongDAO.UpdateTinhTrang(mctt, maP, ngayThue, ngayCheckOut, i);
    }

    public static void DelThuePhong(String mactt, String maP, String ngayThue) throws SQLException, ParseException {
        ThuePhongDAO.DelThuePhong(mactt, maP, ngayThue);
    }

    public static void InsertThuePhong(ThuePhongDTO NV) throws SQLException, ParseException {
        ThuePhongDAO.InsertThuePhong(NV);
    }

    public static boolean checkTP(String maChiTietThue, String maP, String ngayThue) {
        return ThuePhongDAO.checkTP(maChiTietThue, maP, ngayThue);
    }

    public static void UpdateThuePhong(ThuePhongDTO NV) throws SQLException, ParseException {
        ThuePhongDAO.UpdateThuePhong(NV);
    }
}
