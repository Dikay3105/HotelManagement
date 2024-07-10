package DAO;

import DTO.KhachHangDTO;
import static dao.DBConnect.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.ParseException;

public class KhachHangDAO {

    public static ArrayList<KhachHangDTO> KHList = new ArrayList<>();

    public static ArrayList<KhachHangDTO> LoadData() {
        KHList = new ArrayList<>();
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from KhachHang";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangDTO KhachHang = new KhachHangDTO();

                KhachHang.setMaKH(rs.getString("maKH"));
                KhachHang.setTenKH(rs.getString("tenKH"));
                KhachHang.setCmnd(rs.getString("cmnd"));
                KhachHang.setGioiTinh(rs.getString("gioiTinh"));
                KhachHang.setSdt(rs.getString("sdt"));
                KhachHang.setXuLy(rs.getInt("xuLy"));
                KHList.add(KhachHang);

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return KHList;
    }

    public static void InsertKhachHang(KhachHangDTO NV) throws SQLException, ParseException {
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO KhachHang (maKH, tenKH, cmnd, gioiTinh, sdt, xuLy) values (?,?,?,?,?,?)";
        statement = cons.prepareCall(sql);
        statement.setString(1, NV.getMaKH());
        statement.setString(2, NV.getTenKH());
        statement.setString(3, NV.getCmnd());
        statement.setString(4, NV.getGioiTinh());
        statement.setString(5, NV.getSdt());
        statement.setInt(6, 0);

        statement.execute();
        cons.close();
    }

    public static void DelKhachHang(KhachHangDTO NV) throws SQLException, ParseException {
        String sqlChange = "UPDATE KhachHang SET xuLy=?  WHERE maKH='" + NV.getMaKH() + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.setInt(1, 1);
        statement.executeUpdate();
        cons.close();
    }

    public static void UpdateKhachHang(KhachHangDTO NV) throws SQLException, ParseException {
        String sqlChange = "UPDATE KhachHang SET tenKH=?, cmnd=?, gioiTinh=?, sdt=?, xuLy=?  WHERE MaKH='" + NV.getMaKH() + "'";

        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.setString(1, NV.getTenKH());
        statement.setString(2, NV.getCmnd());
        statement.setString(3, NV.getGioiTinh());
        statement.setString(4, NV.getSdt());
        statement.setInt(5, NV.getXuLy());
        statement.executeUpdate();
        cons.close();
    }

    public static ArrayList<KhachHangDTO> searchSDT(String CMND) {
        ArrayList<KhachHangDTO> listSDT = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select * from KhachHang where cmnd = '" + CMND + "'";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangDTO x = new KhachHangDTO();
                x.setMaKH(rs.getString("maKH"));
                x.setTenKH(rs.getString("tenKH"));
                x.setCmnd(rs.getString("cmnd"));
                x.setGioiTinh(rs.getString("gioiTinh"));
                x.setSdt(rs.getString("sdt"));
                x.setXuLy(rs.getInt("xuLy"));
                listSDT.add(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSDT;
    }

    public static KhachHangDTO getKH_MaKH(String maKH) {
        KhachHangDTO khDTO = new KhachHangDTO();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select * from KhachHang where maKH = '" + maKH + "' and xuLy = 0";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                khDTO.setMaKH(rs.getString("maKH"));
                khDTO.setTenKH(rs.getString("tenKH"));
                khDTO.setCmnd(rs.getString("cmnd"));
                khDTO.setGioiTinh(rs.getString("gioiTinh"));
                khDTO.setSdt(rs.getString("sdt"));
                khDTO.setXuLy(rs.getInt("xuLy"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return khDTO;
    }
}
