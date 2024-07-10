package DAO;

import DTO.ChiTietThueDTO;
import java.sql.*;
import java.util.ArrayList;
import java.text.ParseException;
import static dao.DBConnect.getConnection;

public class ChiTietThueDAO {

    public static ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();

    public static void LoadData() {
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from ChiTietThue";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietThueDTO Nhanvien = new ChiTietThueDTO();

                Nhanvien.setMaChiTietThue(rs.getString("maChiTietThue"));
                Nhanvien.setMaKH(rs.getString("maKH"));
                Nhanvien.setMaNV(rs.getString("maNV"));
                Nhanvien.setTinhTrangXuLy(rs.getInt("tinhTrangXuLy"));
                Nhanvien.setXuLy(rs.getInt("xuLy"));
                Nhanvien.setTienDatCoc(rs.getInt("tienDatCoc"));
                CTTList.add(Nhanvien);

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<ChiTietThueDTO> LoadData2() {
        CTTList = new ArrayList<>();
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from ChiTietThue";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietThueDTO Nhanvien = new ChiTietThueDTO();

                Nhanvien.setMaChiTietThue(rs.getString("maChiTietThue"));
                Nhanvien.setMaKH(rs.getString("maKH"));
                Nhanvien.setMaNV(rs.getString("maNV"));
                Nhanvien.setTinhTrangXuLy(rs.getInt("tinhTrangXuLy"));
                Nhanvien.setXuLy(rs.getInt("xuLy"));
                Nhanvien.setTienDatCoc(rs.getInt("tienDatCoc"));
                CTTList.add(Nhanvien);

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return CTTList;
    }

    public static ChiTietThueDTO LoadCTT(String maCTT) {
        ChiTietThueDTO Nhanvien = new ChiTietThueDTO();
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from ChiTietThue WHERE maChiTietThue='" + maCTT + "'";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Nhanvien.setMaChiTietThue(rs.getString("maChiTietThue"));
                Nhanvien.setMaKH(rs.getString("maKH"));
                Nhanvien.setMaNV(rs.getString("maNV"));
                Nhanvien.setTinhTrangXuLy(rs.getInt("tinhTrangXuLy"));
                Nhanvien.setXuLy(rs.getInt("xuLy"));
                Nhanvien.setTienDatCoc(rs.getInt("tienDatCoc"));

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Nhanvien;
    }

    public static void InsertChiTietThue(ChiTietThueDTO NV) throws SQLException, ParseException {
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO ChiTietThue (maChiTietThue, maKH, maNV, tinhTrangXuLy, xuLy, tienDatCoc) values (?,?,?,?,?,?)";
        statement = cons.prepareCall(sql);
        statement.setString(1, NV.getMaChiTietThue());
        statement.setString(2, NV.getMaKH());
        statement.setString(3, NV.getMaNV());
        statement.setInt(4, NV.getTinhTrangXuLy());
        statement.setInt(5, NV.getXuLy());
        statement.setInt(6, NV.getTienDatCoc());
        statement.execute();
        cons.close();
    }

    public static void DelChiTietThue(ChiTietThueDTO NV) throws SQLException, ParseException {
        String sqlChange = "UPDATE ChiTietThue SET xuLy=?  WHERE maChiTietThue='" + NV.getMaChiTietThue() + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.setInt(1, 1);
        statement.executeUpdate();
        cons.close();
    }

    public static void UpdateChiTietThue(ChiTietThueDTO NV) throws SQLException, ParseException {
        String sqlChange = "UPDATE ChiTietThue SET maKH=?, maNV=?, tinhTrangXuLy=?,xuLy=?  WHERE maChiTietThue='" + NV.getMaChiTietThue() + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.setString(1, NV.getMaKH());
        statement.setString(2, NV.getMaNV());
        statement.setInt(3, NV.getTinhTrangXuLy());
        statement.setInt(4, NV.getXuLy());
        statement.executeUpdate();
        cons.close();
    }

    public static int sizeCTT() {
        int size = 0;
        try {
            java.sql.Connection conn = getConnection();
            String query = "select * from ChiTietThue";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                size++;
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception e) {
        }
        return size + 1;
    }

    public static boolean insertCTT(ChiTietThueDTO ctt) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "insert into ChiTietThue values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, ctt.getMaChiTietThue());
            ps.setString(2, ctt.getMaKH());
            ps.setString(3, ctt.getMaNV());
            ps.setInt(4, ctt.getTienDatCoc());
            ps.setInt(5, ctt.getTinhTrangXuLy());
            ps.setInt(6, ctt.getXuLy());
            if (ps.executeUpdate() >= 1) {
                check = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return check;
    }

    public static boolean checkMaCTT(String maCTT) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "select * from ChiTietThue where maChiTietThue = '" + maCTT + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            check = rs.next();
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return check;
    }

    public static boolean changeTT(String maCTT, int tinhTrang) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "update ChiTietThue set tinhTrangXuLy = " + tinhTrang + " where maChiTietThue = '" + maCTT + "'";
            Statement st = conn.createStatement();
            if (st.executeUpdate(query) >= 1) {
                check = true;
            }
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return check;
    }
    
    public static String getMaCTT(String maKH) {
        String maCTT = "";
        try {
            java.sql.Connection conn = getConnection();
            String query = "select maChiTietThue from chiTietThue where tinhTrangXuLy = 0 and maKH = '"+maKH+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()) {
                maCTT = rs.getString("maChiTietThue");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return maCTT;
    }
}
