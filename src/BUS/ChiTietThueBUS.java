/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietThueDAO;
import static DAO.ChiTietThueDAO.CTTList;
import DTO.ChiTietThueDTO;
import java.text.ParseException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author duyli
 */
public class ChiTietThueBUS {

    public void LoadCTT() throws SQLException, ParseException {

        new ChiTietThueDAO().LoadData();
        for (int i = 0; i < CTTList.size(); i++) {
            if (CTTList.get(i).getXuLy() == 1) {
                CTTList.remove(i);
            }
        }
    }

    public void InsertCTT(ChiTietThueDTO a) throws SQLException, ParseException {
        new ChiTietThueDAO().InsertChiTietThue(a);
    }

    public ArrayList<ChiTietThueDTO> LoadCTT2() throws SQLException, ParseException {
        ArrayList<ChiTietThueDTO> a = new ArrayList<>();
        ArrayList<ChiTietThueDTO> b = new ArrayList<>();
        a = new ChiTietThueDAO().LoadData2();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getXuLy() != 1) {
                b.add(a.get(i));
            }
        }
        return b;
    }
    /// Anh Khoa

    public static ArrayList<ChiTietThueDTO> getListCTT() {
        ArrayList<ChiTietThueDTO> listCTT = new ArrayList<>();
        for (ChiTietThueDTO ctt : ChiTietThueDAO.LoadData2()) {
            if (ctt.getXuLy() == 0) {
                listCTT.add(ctt);
            }
        }
        return listCTT;
    }
    ////

    /////////////////////////////////////////////////////
    public static boolean insertCTT(ChiTietThueDTO ctt) {
        if (ChiTietThueDAO.checkMaCTT(ctt.getMaChiTietThue())) {
            return false;
        }
        return ChiTietThueDAO.insertCTT(ctt);
    }

    public static int getSize() {
        return ChiTietThueDAO.sizeCTT();
    }
    
    public static boolean changeTT(String maCTT,int tt) {
        return ChiTietThueDAO.changeTT(maCTT, tt);
    }
    
    public static String getMaCTT(String maKH) {
        return ChiTietThueDAO.getMaCTT(maKH);
    }

    //Vĩnh Khoa
    public static ChiTietThueDTO LoadCTT(String maCTT) {
        return ChiTietThueDAO.LoadCTT(maCTT);
    }
}
