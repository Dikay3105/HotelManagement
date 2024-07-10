package BUS;

import DTO.DichVuDTO;
import DAO.DichVuDAO;
import java.util.ArrayList;

public class DichVuBUS {

    public static ArrayList<DichVuDTO> getListDichVu() {
        ArrayList<DichVuDTO> listDichVu = new ArrayList<>();
        for (DichVuDTO dvDTO : DichVuDAO.getListDichVu()) {
            if (dvDTO.getXuLy() == 0) {
                listDichVu.add(dvDTO);
            }
        }
        return listDichVu;
    }
    public static boolean insertDV(DichVuDTO dvDTO) {
        if(DichVuDAO.checkMaDV(dvDTO.getMaDV())) {
            return false;
        }
        else {
            if(DichVuDAO.insertDV(dvDTO)) {
                return true;
            }
        }
        return false;
    }
    public static boolean updateDV(DichVuDTO dvDTO) {
        if(DichVuDAO.checkMaDV(dvDTO.getMaDV())) {
            return DichVuDAO.updateDV(dvDTO);
        }
        return false;
    }
    public static boolean deleteDV(String maDV) {
        if(DichVuDAO.checkMaDV(maDV)) {
            return DichVuDAO.deleteDV(maDV);
        }
        return false;
    }
    public static ArrayList<DichVuDTO> getListDV() {
        return DichVuDAO.getListDichVu();
    }
}
