package BUS;

import DAO.SuDungDichVuDAO;
import DTO.DichVuDTO;
import DTO.SuDungDichVuDTO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class SuDungDichVuBUS {

    public static boolean insertSDDV(SuDungDichVuDTO sddv) {
        if (SuDungDichVuDAO.check(sddv.getMaChiTietThue(), sddv.getMaDV(), sddv.getNgaySuDungString())) {
            return false;
        }
        return SuDungDichVuDAO.insertSDDV(sddv);
    }

    public static ArrayList<SuDungDichVuDTO> getListSDDV_maDV(String maCTT, ArrayList<DichVuDTO> listDV) {
        return SuDungDichVuDAO.getListSDDV_maDV(maCTT, listDV);
    }

    public static boolean updateSuDungDichVu(String maCTT, String maDV, String date, int SL, int gia) {
        return SuDungDichVuDAO.updateSuDungDichVu(maCTT, maDV, date, SL, gia);
    }

    public static boolean deleteSuDungDichVu(String maCTT, String maDV, String date) {
        return SuDungDichVuDAO.deleteDV(maCTT, maDV, date);
    }

    public static ArrayList<SuDungDichVuDTO> getListSuDungDichVu() {
        ArrayList<SuDungDichVuDTO> listSuDungDichVu = new ArrayList<>();
        for (SuDungDichVuDTO sddv : SuDungDichVuDAO.LoadData()) {
            if (sddv.getXuLy() == 0) {
                listSuDungDichVu.add(sddv);
            }
        }
        return listSuDungDichVu;
    }

    //Vĩnh Khoa
    public static ArrayList<SuDungDichVuDTO> LoadData() {
        return SuDungDichVuDAO.LoadData();
    }

    public static void DelSuDungDichVu(String maCTT, String maDV, String ngaySD) throws SQLException, ParseException {
        SuDungDichVuDAO.DelSuDungDichVu(maCTT, maDV, ngaySD);
    }

    public static ArrayList<SuDungDichVuDTO> LoadSDDV(String maCTT) {
        return SuDungDichVuDAO.LoadSDDV(maCTT);
    }

    public static void UpdateSuDungDichVu(SuDungDichVuDTO NV) throws SQLException, ParseException {
        SuDungDichVuDAO.UpdateSuDungDichVu(NV);
    }

    public static boolean check(String maCTT, String maDV, String ngaySD) {
        return SuDungDichVuDAO.check(maCTT, maDV, ngaySD);
    }

    public static void InsertSuDungDichVu(SuDungDichVuDTO NV) throws SQLException, ParseException {
        SuDungDichVuDAO.InsertSuDungDichVu(NV);
    }
}
