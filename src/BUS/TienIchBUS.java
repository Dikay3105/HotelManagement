package BUS;

import DAO.TienIchDAO;
import DTO.TienIchDTO;
import java.util.ArrayList;

public class TienIchBUS {

    public static ArrayList<TienIchDTO> getListTienIch() {
        ArrayList<TienIchDTO> listTienIch = new ArrayList<>();
        for (TienIchDTO x : TienIchDAO.getListTienIch()) {
            if (x.getXuLy() == 0) {
                listTienIch.add(x);
            }
        }
        return listTienIch;
    }
    
    public static int getSize() {
        return TienIchDAO.getListTienIch().size();
    }

    public static boolean insertTienIch(TienIchDTO tienIchDTO) {
        if (TienIchDAO.checkMaTienIch(tienIchDTO.getMaTienIch())) {
            return false;
        } else {
            if (TienIchDAO.insertTienIch(tienIchDTO)) {
                return true;
            }
        }
        return false;
    }

    public static boolean updateTienIch(TienIchDTO tienIchDTO) {
        if (TienIchDAO.checkMaTienIch(tienIchDTO.getMaTienIch())) {
            return TienIchDAO.updateTienIch(tienIchDTO);
        }
        return false;
    }

    public static boolean deleteTienIch(String maTienIch) {
        if (TienIchDAO.checkMaTienIch(maTienIch)) {
            return TienIchDAO.deleteTienIch(maTienIch);
        }
        return false;
    }

    public static ArrayList<TienIchDTO> getListTienIchCTTI(String maP, ArrayList<Integer> listSL) {
        return TienIchDAO.getListTienIchCTTI(maP, listSL);
    }
}
