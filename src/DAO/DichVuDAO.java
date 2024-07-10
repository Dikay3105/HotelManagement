package DAO;

import DTO.DichVuDTO;
import static dao.DBConnect.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DichVuDAO {

    public static ArrayList<DichVuDTO> getListDichVu() {
        ArrayList<DichVuDTO> listDichVu = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select * from DICHVU";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DichVuDTO dvDTO = new DichVuDTO();
                dvDTO.setMaDV(rs.getString("maDV"));
                dvDTO.setTenDV(rs.getString("tenDV"));
                dvDTO.setTenLoaiDV(rs.getString("tenLoaiDV"));
                dvDTO.setGiaDV(rs.getInt("giaDV"));
                dvDTO.setXuLy(rs.getInt("xuLy"));
                listDichVu.add(dvDTO);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDichVu;
    }

    public static boolean insertDV(DichVuDTO dvDTO) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "insert into DICHVU values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, dvDTO.getMaDV());
            ps.setString(2, dvDTO.getTenDV());
            ps.setString(3, dvDTO.getTenLoaiDV());
            ps.setInt(4, dvDTO.getGiaDV());
            ps.setInt(5, 0);
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

    public static boolean checkMaDV(String maDV) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "select * from DICHVU where maDV = '" + maDV + "'";
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

    public static boolean updateDV(DichVuDTO dvDTO) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "update DICHVU set tenDV = ?, tenLoaiDV = ?, giaDV = ? where maDV = '" + dvDTO.getMaDV() + "'";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, dvDTO.getTenDV());
            ps.setString(2, dvDTO.getTenLoaiDV());
            ps.setInt(3, dvDTO.getGiaDV());
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

    public static boolean deleteDV(String maDV) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "update DICHVU set xuLy = 1 where maDV = '"+maDV +"'";
            PreparedStatement ps = conn.prepareCall(query);
            if(ps.executeUpdate()>=1) {
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
