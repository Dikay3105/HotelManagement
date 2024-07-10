package DAO;

import DTO.ChiTietTienIchDTO;
import static dao.DBConnect.getConnection;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietTienIchDAO {

    public static ArrayList<ChiTietTienIchDTO> CTTIList = new ArrayList<>();

    public static ArrayList<ChiTietTienIchDTO> LoadData() {
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from chiTietTienIch";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietTienIchDTO ChiTietTienIch = new ChiTietTienIchDTO();

                ChiTietTienIch.setMaTienIch(rs.getString("maTienIch"));
                ChiTietTienIch.setMaP(rs.getString("maP"));
                ChiTietTienIch.setSoLuong(rs.getInt("soLuong"));

                CTTIList.add(ChiTietTienIch);

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return CTTIList;
    }

    public static boolean insertChiTietTienIch(ChiTietTienIchDTO chiTietTienIchDTO) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "insert into ChiTietTienIch values(?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, chiTietTienIchDTO.getMaTienIch());
            ps.setString(2, chiTietTienIchDTO.getMaP());
            ps.setInt(3, chiTietTienIchDTO.getSoLuong());
            if (ps.executeUpdate() >= 1) {
                check = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return check;
    }

    public static boolean checkMaTienIchMaP(String maTienIch, String maP) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from chiTietTienIch where maTienIch = '" + maTienIch + "' and maP = '" + maP + "'";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            check = rs.next();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return check;
    }

    public static boolean updateChiTietTienIch(ChiTietTienIchDTO chiTietTienIchDTO) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update chiTietTienIch set soLuong = ? where maTienIch = '" + chiTietTienIchDTO.getMaTienIch() + "' and map = '" + chiTietTienIchDTO.getMaP() + "'";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setInt(1, chiTietTienIchDTO.getSoLuong());
            if (ps.executeUpdate() >= 1) {
                check = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return check;
    }

    public static boolean deleteChiTietTienIch(String maTienIch, String maP) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Delete from chiTietTienIch where maTienIch = '" + maTienIch + "' and maP = '" + maP + "'";
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

}
