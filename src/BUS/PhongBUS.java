package BUS;

import DAO.PhongDAO;
import DTO.PhongDTO;
import java.util.ArrayList;

public class PhongBUS {

    public static ArrayList<PhongDTO> getListPhong() {
        ArrayList<PhongDTO> listPhong = new ArrayList<>();
        for (PhongDTO x : PhongDAO.getListPhong()) {
            if (x.getXuLy() == 0) {
                listPhong.add(x);
            }
        }
        return listPhong;
    }

    public static ArrayList<PhongDTO> getListPhong(String dateTimeThue, String dateTimeTra) {
        ArrayList<PhongDTO> listPhong = new ArrayList<>();
        for (PhongDTO x : PhongDAO.getListPhong(dateTimeThue, dateTimeTra)) {
            if (x.getXuLy() == 0) {
                listPhong.add(x);
            }
        }
        return listPhong;
    }

    public static int getSize() {
        return PhongDAO.getListPhong().size();
    }

    public static boolean insertPhong(PhongDTO pDTO) {
        if (PhongDAO.checkMaP(pDTO.getMaP())) {
            return false;
        } else {
            if (PhongDAO.insertPhong(pDTO)) {
                return true;
            }
        }
        return false;
    }

    public static boolean updatePhong(PhongDTO pDTO) {
        if (PhongDAO.checkMaP(pDTO.getMaP())) {
            return PhongDAO.updatePhong(pDTO);
        }
        return false;
    }

    public static boolean updateTT(String maP, String tinhtrang) {
        if (PhongDAO.checkMaP(maP)) {
            return PhongDAO.updateTT(maP, tinhtrang);
        }
        return false;
    }

    public static boolean deletePhong(String maP) {
        if (PhongDAO.checkMaP(maP)) {
            return PhongDAO.deletePhong(maP);
        }
        return false;
    }

    //Ham add du lieu vao bieu do tron co dk
    public static ArrayList<String> getLoaiP_SL(ArrayList<Integer> listInt, String dateTuNgay, String dateDenNgay) {
        return PhongDAO.getCountLoai(listInt, dateTuNgay, dateDenNgay);
    }

    //Ham add du lieu vao bieu do tron kh dk
    public static ArrayList<String> getLoaiP_SL(ArrayList<Integer> listInt) {
        return PhongDAO.getCountLoai(listInt);
    }
}
