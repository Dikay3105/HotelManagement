package DAO;

import DTO.PhongDTO;
import static dao.DBConnect.getConnection;
import java.sql.*;
import java.util.ArrayList;

public class PhongDAO {

    public static ArrayList<PhongDTO> getListPhong() {
        ArrayList<PhongDTO> listPhong = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from PHONG";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhongDTO pDTO = new PhongDTO();
                pDTO.setMaP(rs.getString("maP"));
                pDTO.setTenP(rs.getString("tenP"));
                pDTO.setLoaiP(rs.getString("loaiP"));
                pDTO.setGiaP(rs.getInt("giaP"));
                pDTO.setTinhTrang(rs.getString("tinhTrang"));
                pDTO.setHienTrang(rs.getString("hienTrang"));
                pDTO.setXuLy(rs.getInt("xuLy"));
                listPhong.add(pDTO);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPhong;
    }

    public static boolean insertPhong(PhongDTO pDTO) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "insert into PHONG values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, pDTO.getMaP());
            ps.setString(2, pDTO.getTenP());
            ps.setString(3, pDTO.getLoaiP());
            ps.setInt(4, pDTO.getGiaP());
            ps.setString(5, pDTO.getTinhTrang());
            ps.setString(6, pDTO.getHienTrang());
            ps.setInt(7, pDTO.getXuLy());
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

    public static boolean checkMaP(String maP) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from PHONG where maP = '" + maP + "'";
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

    public static boolean updatePhong(PhongDTO pDTO) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update PHONG set tenP = ?, loaiP = ?, giaP = ?, tinhTrang = ?, hienTrang = ? where maP = '" + pDTO.getMaP() + "'";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, pDTO.getTenP());
            ps.setString(2, pDTO.getLoaiP());
            ps.setInt(3, pDTO.getGiaP());
            ps.setString(4, pDTO.getTinhTrang());
            ps.setString(5, pDTO.getHienTrang());
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

    public static boolean updateTT(String maP, String tinhTrang) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update PHONG set tinhTrang = N'" + tinhTrang + "' where maP = '" + maP + "'";
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

    public static boolean deletePhong(String maP) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update PHONG set xuLy = 1 where maP = '" + maP + "'";
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

    public static ArrayList<PhongDTO> getListPhong(String datetimeThue, String datetimeTra) {
        ArrayList<PhongDTO> listPhong = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select * from phong "
                    + "where tinhTrang = N'Trống' and map not in (select maP from ChiTietThue, ThuePhong "
                    + "where chitietThue.maChiTietThue = ThuePhong.maChiTietThue and tinhTrangXuLy=0 and ngayThue <= '" + datetimeTra + "' and ngayTra >= '" + datetimeThue + "')";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhongDTO pDTO = new PhongDTO();
                pDTO.setMaP(rs.getString("maP"));
                pDTO.setTenP(rs.getString("tenP"));
                pDTO.setLoaiP(rs.getString("loaiP"));
                pDTO.setGiaP(rs.getInt("giaP"));
                pDTO.setTinhTrang(rs.getString("tinhTrang"));
                pDTO.setHienTrang(rs.getString("hienTrang"));
                pDTO.setXuLy(rs.getInt("xuLy"));
                listPhong.add(pDTO);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPhong;
    }

    //Ham add du lieu vao bieu do tron co dieu kien
    public static ArrayList<String> getCountLoai(ArrayList<Integer> listCount, String dateTuNgay, String dateDenNgay) {
        ArrayList<String> listLoai = new ArrayList<>();
        listCount.clear();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select count(LoaiP) as count, loaiP from Phong, ChiTietThue, ThuePhong where Phong.MaP = ThuePhong.maP and ThuePhong.MaChiTietThue = ChiTietThue.maChiTietThue and TinhTrangXuLy = 1 and ngaythue >= '" + dateTuNgay + "' and ngaythue <= '" + dateDenNgay + "' group by LoaiP";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String loaiP = rs.getString("loaiP");
                int count = rs.getInt("count");
                listCount.add(count);
                listLoai.add(loaiP);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return listLoai;
    }
    //

    //Ham add du lieu vao bieu do tron k dieu kien
    public static ArrayList<String> getCountLoai(ArrayList<Integer> listCount) {
        ArrayList<String> listLoai = new ArrayList<>();
        listCount.clear();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select count(LoaiP) as count, loaiP from Phong, ChiTietThue, ThuePhong where Phong.MaP = ThuePhong.maP and ThuePhong.MaChiTietThue = ChiTietThue.maChiTietThue and TinhTrangXuLy = 1 group by LoaiP";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String loaiP = rs.getString("loaiP");
                int count = rs.getInt("count");
                listCount.add(count);
                listLoai.add(loaiP);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return listLoai;
    }
    //
}
