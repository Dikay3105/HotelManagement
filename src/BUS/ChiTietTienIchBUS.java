package BUS;

import DAO.ChiTietTienIchDAO;
import DTO.ChiTietTienIchDTO;

public class ChiTietTienIchBUS {

    public static boolean insertChiTietTienIch(ChiTietTienIchDTO chiTietTienIchDTO) {
        if (ChiTietTienIchDAO.checkMaTienIchMaP(chiTietTienIchDTO.getMaTienIch(), chiTietTienIchDTO.getMaP())) {
            return false;
        } else {
            if (ChiTietTienIchDAO.insertChiTietTienIch(chiTietTienIchDTO)) {
                return true;
            }
        }
        return false;
    }

    public static boolean updateChiTietTienIch(ChiTietTienIchDTO chiTietTienIchDTO) {
        if (ChiTietTienIchDAO.checkMaTienIchMaP(chiTietTienIchDTO.getMaTienIch(), chiTietTienIchDTO.getMaP())) {
            return ChiTietTienIchDAO.updateChiTietTienIch(chiTietTienIchDTO);
        }
        return false;
    }

    public static boolean deleteChiTietTienIch(String maTienIch, String maP) {
        if (ChiTietTienIchDAO.checkMaTienIchMaP(maTienIch, maP)) {
            return ChiTietTienIchDAO.deleteChiTietTienIch(maTienIch, maP);
        }
        return false;
    }
}
