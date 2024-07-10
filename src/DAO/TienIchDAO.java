package DAO;

import DTO.ChiTietTienIchDTO;
import DTO.TienIchDTO;
import static dao.DBConnect.getConnection;
import java.sql.*;
import java.util.ArrayList;

public class TienIchDAO {

    public static ArrayList<TienIchDTO> getListTienIch() {
        ArrayList<TienIchDTO> listTienIch = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from TienIch";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TienIchDTO tienIchDTO = new TienIchDTO();
                tienIchDTO.setMaTienIch(rs.getString("maTienIch"));
                tienIchDTO.setTenTienIch(rs.getString("tenTienIch"));
                tienIchDTO.setXuLy(rs.getInt("xuLy"));
                listTienIch.add(tienIchDTO);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTienIch;
    }

    public static boolean insertTienIch(TienIchDTO tienIchDTO) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "insert into TienIch values(?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, tienIchDTO.getMaTienIch());
            ps.setString(2, tienIchDTO.getTenTienIch());
            ps.setInt(3, tienIchDTO.getXuLy());
            if (ps.executeUpdate() >= 1) {
                check = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean checkMaTienIch(String maTienIch) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from TienIch where maTienIch = '" + maTienIch + "'";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            check = rs.next();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean updateTienIch(TienIchDTO tienIchDTO) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update TienIch set tenTienIch = ? where maTienIch = '" + tienIchDTO.getMaTienIch() + "'";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, tienIchDTO.getTenTienIch());
            if (ps.executeUpdate() >= 1) {
                check = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean deleteTienIch(String maTienIch) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update TienIch set xuLy = 1 where maTienIch = '" + maTienIch + "'";
            PreparedStatement ps = conn.prepareCall(query);
            if (ps.executeUpdate() >= 1) {
                check = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public static ArrayList<TienIchDTO> getListTienIchCTTI(String maP, ArrayList<Integer> listSL) {
        ArrayList<TienIchDTO> listTI = new ArrayList<>();
        listSL.clear();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select soLuong, TienIch.maTienIch, tenTienIch, TienIch.xuLy"
                    + " from Phong, chiTietTienIch, TienIch"
                    + " where Phong.maP = chiTietTienIch.maP"
                    + " and chiTietTienIch.maTienIch = TienIch.maTienIch"
                    + " and Phong.maP = '" + maP + "' and TienIch.xuLy = 0";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                TienIchDTO x = new TienIchDTO();
                listSL.add(rs.getInt("soLuong"));
                x.setMaTienIch(rs.getString("maTienIch"));
                x.setTenTienIch(rs.getString("tenTienIch"));
                x.setXuLy(rs.getInt("xuLy"));
                listTI.add(x);
            }
        } catch (Exception e) {
        }
        return listTI;
    }
}
