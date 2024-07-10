/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.KhachHangDTO;
import java.sql.SQLException;
import java.text.ParseException;
import DAO.KhachHangDAO;
import java.util.ArrayList;

/**
 *
 * @author duyli
 */
public class KhachHangBUS {

    public void InsertKH(KhachHangDTO a) throws SQLException, ParseException {
        new KhachHangDAO().InsertKhachHang(a);
    }

    public void UpdateKH(KhachHangDTO a) throws SQLException, ParseException {
        new KhachHangDAO().UpdateKhachHang(a);
    }

    public void DeleteKH(KhachHangDTO a) throws SQLException, ParseException {
        new KhachHangDAO().DelKhachHang(a);
    }

    //Anh Khoa
    public static ArrayList<KhachHangDTO> getListKhachHang() {
        ArrayList<KhachHangDTO> listKhachHang = new ArrayList<>();
        for (KhachHangDTO kh : KhachHangDAO.LoadData()) {
            if (kh.getXuLy() == 0) {
                listKhachHang.add(kh);
            }
        }
        return listKhachHang;
    }

    public static void InsertKH2(KhachHangDTO a) throws SQLException, ParseException {
        new KhachHangDAO().InsertKhachHang(a);
    }
    //

    public ArrayList<KhachHangDTO> LoadKH() throws SQLException, ParseException {
        ArrayList<KhachHangDTO> a = new ArrayList<>();
        ArrayList<KhachHangDTO> b = new ArrayList<>();
        a = new KhachHangDAO().LoadData();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getXuLy() != 1) {
                b.add(a.get(i));
            }
        }
        return b;
    }

    public static ArrayList<KhachHangDTO> searchSDT(String CMND) {
        ArrayList<KhachHangDTO> listSearch = new ArrayList<>();
        for (KhachHangDTO x : KhachHangDAO.searchSDT(CMND)) {
            if (x.getXuLy() == 0) {
                listSearch.add(x);
            }
        }
        return listSearch;
    }

    /// 
    public static KhachHangDTO getKH_MaKH(String maKH) {
        return KhachHangDAO.getKH_MaKH(maKH);
    }
}
