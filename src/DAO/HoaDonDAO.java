package DAO;

import DTO.HoaDonDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import static dao.DBConnect.getConnection;
import java.text.ParseException;

public class HoaDonDAO {

    public static ArrayList<HoaDonDTO> getListHoaDon() {
        ArrayList<HoaDonDTO> listHD = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from HoaDon";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getString("maHD"));
                hd.setMaChiTietThue(rs.getString("maChiTietThue"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(rs.getDate("ngayThanhToan"));
                hd.setNgayThanhToan(date);
                hd.setTienPhong(rs.getInt("tienPhong"));
                hd.setTienDichVu(rs.getInt("tienDichVu"));
                hd.setTongTien(rs.getInt("tongTien"));
                hd.setGiamGia(rs.getInt("giamGia"));
                hd.setMaNV(rs.getString("maNV"));
                hd.setXuLy(rs.getInt("xuLy"));
                listHD.add(hd);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return listHD;
    }

    public static void InsertHoaDon(HoaDonDTO NV) throws SQLException, ParseException {
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO HoaDon (maHD, tienPhong, tienDichVu, tongTien, ngayThanhToan, maChiTietThue, giamGia, maNV, xuLy) values (?,?,?,?,?,?,?,?,?)";
        statement = cons.prepareCall(sql);
        statement.setString(1, NV.getMaHD());
        statement.setInt(2, NV.getTienPhong());
        statement.setInt(3, NV.getTienDichVu());
        statement.setInt(4, NV.getTongTien());
        statement.setString(6, NV.getMaChiTietThue());
        statement.setInt(7, NV.getGiamGia());
        statement.setString(8, NV.getMaNV());
        statement.setInt(9, NV.getXuLy());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(NV.getNgayThanhToan());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        statement.setDate(5, sqlDate);

        statement.execute();
        cons.close();
    }

    public static ArrayList<HoaDonDTO> getListHD() {
        ArrayList<HoaDonDTO> listHD = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from HoaDon";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getString("maHD"));
                hd.setMaChiTietThue(rs.getString("maChiTietThue"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(rs.getDate("ngayThanhToan"));
                hd.setNgayThanhToan(date);
                hd.setTienPhong(rs.getInt("tienPhong"));
                hd.setTienDichVu(rs.getInt("tienDichVu"));
                hd.setTongTien(rs.getInt("tongTien"));
                hd.setGiamGia(rs.getInt("giamGia"));
                hd.setMaNV(rs.getString("maNV"));
                hd.setXuLy(rs.getInt("xuLy"));
                listHD.add(hd);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return listHD;
    }

    public static int tienPhong(int month) {
        int money = 0;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select count(tienPhong) from HoaDon where month(ngayThanhToan) = " + month;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                money = rs.getInt("count(tienPhong)");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return money;
    }

    public static ArrayList<Integer> getListYear() {
        ArrayList<Integer> listInt = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select distinct year(ngayThanhToan) as year from HoaDon";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int i = rs.getInt("year");
                listInt.add(i);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return listInt;
    }

    public static String getMaNV(String maHD) {
        String maNV = "";
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select maNV from HoaDon where maHD = '" + maHD + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                maNV = rs.getString("maNV");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return maNV;
    }

    public static HoaDonDTO getHoaDon(String maCTT) {
        HoaDonDTO x = new HoaDonDTO();
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select * from hoadon where maChiTietThue = '" + maCTT + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                x.setMaHD(rs.getString("maHD"));
                x.setTienPhong(rs.getInt("tienPhong"));
                x.setTienDichVu(rs.getInt("tienDichVu"));
                x.setTongTien(rs.getInt("tongTien"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                x.setNgayThanhToan(sdf.format(rs.getDate("ngayThanhToan")));
                x.setMaChiTietThue(rs.getString("maChiTietThue"));
                x.setGiamGia(rs.getInt("giamGia"));
                x.setMaNV(rs.getString("maNV"));
                x.setXuLy(rs.getInt("xuLy"));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return x;
    }

    //Ham dung de tinh tong tien phong
    public static int tienPhong() {
        int money = 0;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select sum(tienPhong) as tong from HoaDon";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                money = rs.getInt("tong");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return money;
    }
    //

    //Ham dung de tinh tong tien dich vu
    public static int tienDichVu() {
        int money = 0;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select sum(tienDichVu) as tong from HoaDon";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                money = rs.getInt("tong");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return money;
    }
    //

    //Ham dung de tinh tong tien
    public static int tongTien() {
        int money = 0;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select sum(tongTien) as tong from HoaDon";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                money = rs.getInt("tong");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return money;
    }
    //

    //Ham dung de lay nam add vao ComboBox
    public static ArrayList<Integer> getListYear_1() {
        ArrayList<Integer> listNum = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "SELECT DISTINCT YEAR(NGAYTHANHTOAN) as num FROM HOADON";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            try {
                while (rs.next()) {
                    int i = rs.getInt("num");
                    listNum.add(i);
                }
            } catch (Exception e) {
            }
            rs.close();
        } catch (Exception e) {
        }
        return listNum;
    }
    //

    //Ham dung de add du lieu cho bieu do cot
    public static ArrayList<Integer> getTienNam(ArrayList<Integer> tienPh, ArrayList<Integer> tienDV, ArrayList<Integer> listTien, int year) {
        ArrayList<Integer> listThang = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select sum(tienPhong) as tp, sum(tienDichVu) as tdv, sum(tongTien) as tt, month(ngayThanhToan) as month from HoaDon where year(ngayThanhToan) = " + year + " group by month(ngayThanhToan)";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int tienP = rs.getInt("tp");
                int tienD = rs.getInt("tdv");
                int tong = rs.getInt("tt");
                int thang = rs.getInt("month");
                tienPh.add(tienP);
                tienDV.add(tienD);
                listTien.add(tong);
                listThang.add(thang);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return listThang;
    }
    //

    //Ham dung de add du lieu cho bieu do duong
    public static ArrayList<Integer> getTienThang(ArrayList<Integer> tienPh, ArrayList<Integer> tienDV, int month, int year) {
        ArrayList<Integer> listNgay = new ArrayList<>();
        try {
            java.sql.Connection conn = getConnection();
            String query = "Select tienPhong, tienDichVu, day(ngayThanhToan) as ngay from HoaDon where month(ngayThanhToan) = " + month + " and year(ngayThanhToan) = " + year;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int tienP = rs.getInt("tienPhong");
                int tienD = rs.getInt("tienDichVu");
                int ngay = rs.getInt("ngay");
                tienPh.add(tienP);
                tienDV.add(tienD);
                listNgay.add(ngay);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return listNgay;
    }
    //
}
