package DAO;

import DTO.DichVuDTO;
import DTO.SuDungDichVuDTO;
import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static dao.DBConnect.getConnection;
import java.util.Date;

public class SuDungDichVuDAO {

    public static ArrayList<SuDungDichVuDTO> SDDVList = new ArrayList<>();

    public static ArrayList<SuDungDichVuDTO> LoadData() {
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from SuDungDichVu";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuDungDichVuDTO Nhanvien = new SuDungDichVuDTO();

                Nhanvien.setMaChiTietThue(rs.getString("maChiTietThue"));
                Nhanvien.setMaDV(rs.getString("maDV"));
                Date date = rs.getDate("ngaySuDung");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = formatter.format(date);
                Nhanvien.setNgaySuDungString(formattedDate);
                Nhanvien.setSoLuong(rs.getInt("soLuong"));
                Nhanvien.setDonGia(rs.getInt("donGia"));
                Nhanvien.setXuLy(rs.getInt("xuLy"));

                SDDVList.add(Nhanvien);

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return SDDVList;
    }

    ///////////////////////////////////////////////////////////////Anh Khoa
    public static void InsertSuDungDichVu_import(SuDungDichVuDTO NV) throws SQLException, ParseException {
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO SuDungDichVu (maChiTietThue, maDV, ngaySuDung, soLuong, donGia, xuLy) values (?,?,?,?,?,?)";
        statement = cons.prepareCall(sql);
        statement.setString(1, NV.getMaChiTietThue());
        statement.setString(2, NV.getMaDV());
        statement.setInt(4, NV.getSoLuong());
        statement.setInt(5, NV.getDonGia());
        statement.setInt(6, NV.getXuLy());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date date = sdf.parse(/*chuyendang(*/NV.getNgaySuDungString()/*)*/);
        statement.setDate(3, new java.sql.Date(date.getTime()));

        statement.execute();
        cons.close();
    }
    //////////////////////////////////////////

    public static ArrayList<SuDungDichVuDTO> LoadSDDV(String maCTT) {
        ArrayList<SuDungDichVuDTO> sddvtmp = new ArrayList<>();
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from SuDungDichVu WHERE maChiTietThue='" + maCTT + "'";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuDungDichVuDTO Nhanvien = new SuDungDichVuDTO();

                Nhanvien.setMaChiTietThue(rs.getString("maChiTietThue"));
                Nhanvien.setMaDV(rs.getString("maDV"));
                Date date = rs.getDate("ngaySuDung");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = formatter.format(date);
                Nhanvien.setNgaySuDungString(formattedDate);
                Nhanvien.setSoLuong(rs.getInt("soLuong"));
                Nhanvien.setDonGia(rs.getInt("donGia"));
                Nhanvien.setXuLy(rs.getInt("xuLy"));

                sddvtmp.add(Nhanvien);

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sddvtmp;
    }

    public static boolean check(String maCTT, String maDV, String ngaySD) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from SuDungDichVu where maChiTietThue = '" + maCTT + "' and maDV='" + maDV + "' and ngaySuDung='" + chuyendang(ngaySD) + "'";
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

    public static void InsertSuDungDichVu(SuDungDichVuDTO NV) throws SQLException, ParseException {
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO SuDungDichVu (maChiTietThue, maDV, ngaySuDung, soLuong, donGia, xuLy) values (?,?,?,?,?,?)";
        statement = cons.prepareCall(sql);
        statement.setString(1, NV.getMaChiTietThue());
        statement.setString(2, NV.getMaDV());
        statement.setInt(4, NV.getSoLuong());
        statement.setInt(5, NV.getDonGia());
        statement.setInt(6, NV.getXuLy());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(chuyendang(NV.getNgaySuDungString()));
        statement.setDate(3, new java.sql.Date(date.getTime()));

        statement.execute();
        cons.close();
    }

    public static void DelSuDungDichVu(SuDungDichVuDTO NV) throws SQLException, ParseException {
        String sqlChange = "UPDATE SuDungDichVu SET xuLy=?  WHERE maChiTietThue='" + NV.getMaChiTietThue() + "' and maDV='" + NV.getMaDV() + "'"
                + " and ngaySuDung='" + chuyendang(NV.getNgaySuDungString()) + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.setInt(1, 1);
        statement.executeUpdate();
        cons.close();
    }

    public static void DelSuDungDichVu(String maCTT, String maDV, String ngaySD) throws SQLException, ParseException {
        String sqlChange = "Delete from SuDungDichVu WHERE maChiTietThue='" + maCTT + "' and maDV='" + maDV + "'"
                + " and ngaySuDung='" + chuyendang(ngaySD) + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.executeUpdate();
        cons.close();
    }

    public static void UpdateSuDungDichVu(SuDungDichVuDTO NV) throws SQLException, ParseException {
        String sqlChange = "UPDATE SuDungDichVu SET soLuong=?, donGia=?,xuLy=?  WHERE maChiTietThue='" + NV.getMaChiTietThue() + "' and maDV='" + NV.getMaDV() + "'"
                + "and ngaySuDung='" + chuyendang(NV.getNgaySuDungString()) + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.setInt(1, NV.getSoLuong());
        statement.setInt(2, NV.getDonGia());
        statement.setInt(3, NV.getXuLy());

        statement.executeUpdate();
        cons.close();
    }

    public static String chuyendang(String day) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = originalFormat.parse(day);
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }

    ////////////////////////////
    public static boolean insertSDDV(SuDungDichVuDTO sddv) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "insert into SuDungDichVu values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, sddv.getMaChiTietThue());
            ps.setString(2, sddv.getMaDV());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(sddv.getNgaySuDungString());
            ps.setDate(3, new java.sql.Date(date.getTime()));

            ps.setInt(4, sddv.getSoLuong());
            ps.setInt(5, sddv.getDonGia());
            ps.setInt(6, sddv.getXuLy());
            if (ps.executeUpdate() >= 1) {
                check = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return check;
    }

    public static ArrayList<SuDungDichVuDTO> getListSDDV_maDV(String maCTT, ArrayList<DichVuDTO> listDV) {
        ArrayList<SuDungDichVuDTO> listSuDungDV = new ArrayList<>();
        listDV.clear();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select maChiTietThue, SuDungDichVu.maDV, ngaySuDung, soLuong, donGia, SuDungDichVu.xuLy, tenDV, tenLoaiDV, giaDV, DichVu.xuLy as xl "
                    + "from SuDungDichVu, DichVu "
                    + "where SuDungDichVu.maDV = DichVu.maDV and maChiTietThue = '" + maCTT + "'  order by ngaySuDung desc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            while (rs.next()) {
                SuDungDichVuDTO sddv = new SuDungDichVuDTO();
                sddv.setMaChiTietThue(maCTT);
                sddv.setMaDV(rs.getString("maDV"));
                sddv.setDonGia(rs.getInt("donGia"));
                sddv.setSoLuong(rs.getInt("soLuong"));
                sddv.setXuLy(rs.getInt("xuLy"));
                sddv.setNgaySuDungString(sdf.format(rs.getDate("ngaySuDung")));
                listSuDungDV.add(sddv);

                DichVuDTO dv = new DichVuDTO();
                dv.setMaDV(rs.getString("maDV"));
                dv.setTenDV(rs.getString("tenDV"));
                dv.setTenLoaiDV(rs.getString("tenLoaiDV"));
                dv.setGiaDV(rs.getInt("giaDV"));
                dv.setXuLy(rs.getInt("xl"));
                listDV.add(dv);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return listSuDungDV;
    }

    public static boolean updateSuDungDichVu(String maCTT, String maDV, String date, int SL, int gia) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "update SuDungDichVu set donGia = " + gia + ",soLuong = " + SL + " where maDV = '" + maDV + "' and maChiTietThue = '" + maCTT + "' and ngaySuDung = '" + date + "'";
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

    public static boolean deleteDV(String maCTT, String maDV, String date) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "delete from SuDungDichVu where maDV = '" + maDV + "' and maChiTietThue = '" + maCTT + "' and ngaySuDung = '" + date + "'";
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
    //////////////////////////////
}
