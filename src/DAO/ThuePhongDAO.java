package DAO;

import DTO.PhongDTO;
import DTO.ThuePhongDTO;
import static dao.DBConnect.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThuePhongDAO {

    public static ArrayList<ThuePhongDTO> NVList = new ArrayList<>();

    public static ArrayList<ThuePhongDTO> LoadData() {
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from ThuePhong";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuePhongDTO Nhanvien = new ThuePhongDTO();

                Nhanvien.setMaChiTietThue(rs.getString("maChiTietThue"));
                Nhanvien.setMaP(rs.getString("maP"));
                java.sql.Timestamp timestamp = rs.getTimestamp("ngayThue");
                Date date = new Date(timestamp.getTime());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

                String formattedDate = formatter.format(date);
                Nhanvien.setNgayThue(formattedDate);

                timestamp = rs.getTimestamp("ngayTra");
                date = new Date(timestamp.getTime());
                formattedDate = formatter.format(date);
                Nhanvien.setNgayTra(formattedDate);
                Nhanvien.setLoaiHinhThue(rs.getString("loaiHinhThue"));
                Nhanvien.setGia(rs.getInt("gia"));
                Nhanvien.setTinhTrang(rs.getInt("tinhTrang"));
                Nhanvien.setXuLy(rs.getInt("xuLy"));

                timestamp = rs.getTimestamp("ngayCheckOut");
                date = new Date(timestamp.getTime());
                formattedDate = formatter.format(date);
                Nhanvien.setNgayCheckOut(formattedDate);

                NVList.add(Nhanvien);

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return NVList;
    }

    public static void InsertThuePhong(ThuePhongDTO NV) throws SQLException, ParseException {
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO ThuePhong (maChiTietThue, maP, ngayThue, ngaytra, tinhTrang, xuLy,loaiHinhThue,ngayCheckOut,gia) values (?,?,?,?,?,?,?,?,?)";
        statement = cons.prepareCall(sql);
        statement.setString(1, NV.getMaChiTietThue());
        statement.setString(2, NV.getMaP());
        statement.setInt(5, NV.getTinhTrang());
        statement.setInt(6, NV.getXuLy());
        statement.setString(7, NV.getLoaiHinhThue());
        statement.setInt(9, NV.getGia());

        statement.setTimestamp(3, java.sql.Timestamp.valueOf(chuyendang(NV.getNgayThue())));

        statement.setTimestamp(4, java.sql.Timestamp.valueOf(chuyendang(NV.getNgayTra())));

        statement.setTimestamp(8, java.sql.Timestamp.valueOf(chuyendang(NV.getNgayCheckOut())));
        statement.execute();
        cons.close();
    }

    public static void DelThuePhong(String mactt, String maP, String ngayThue) throws SQLException, ParseException {
        String sqlChange = "Delete from ThuePhong WHERE maChiTietThue='" + mactt + "' and maP='" + maP + "'"
                + " and ngayThue='" + chuyendang(ngayThue) + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.executeUpdate();
        cons.close();
    }

    public static void UpdateThuePhong(ThuePhongDTO NV) throws SQLException, ParseException {
        String sqlChange = "UPDATE ThuePhong SET ngaytra=?, loaiHinhThue=?,"
                + "gia=?,ngayCheckOut=? ,tinhTrang=?,xuLy=? WHERE maChiTietThue='" + NV.getMaChiTietThue() + " and maP=" + NV.getMaP() + "'"
                + "and ngayThue='" + chuyendang(NV.getNgayThue()) + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.setString(2, NV.getLoaiHinhThue());
        statement.setInt(3, NV.getGia());
        statement.setInt(5, NV.getTinhTrang());
        statement.setInt(6, NV.getXuLy());

        statement.setTimestamp(1, java.sql.Timestamp.valueOf(chuyendang(NV.getNgayTra())));

        statement.setTimestamp(4, java.sql.Timestamp.valueOf(chuyendang(NV.getNgayCheckOut())));

        statement.executeUpdate();
        cons.close();
    }

    public static String chuyendang(String day) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date date = originalFormat.parse(day);
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }

    public static void UpdateTinhTrang(String mctt, String maP, String ngayThue, String ngayCheckOut, int i) throws SQLException, ParseException {
        String sqlChange = "UPDATE ThuePhong SET tinhTrang=?, ngayCheckOut=? WHERE maChiTietThue='" + mctt + "' and maP='" + maP + "'"
                + "and ngayThue='" + chuyendang(ngayThue) + "'";
        java.sql.Connection cons = getConnection();
        PreparedStatement statement = cons.prepareCall(sqlChange);
        statement.setInt(1, i);
        statement.setTimestamp(2, java.sql.Timestamp.valueOf(chuyendang(ngayCheckOut)));

        statement.executeUpdate();
        cons.close();
    }

    /////////////////////////////////////////////////////////
    public static boolean checkTP(String maChiTietThue, String maP, String ngayThue) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "select * from ThuePhong where maChiTietThue = '" + maChiTietThue + "' and maP = '" + maP + "' and ngayThue = '" + ngayThue + "'";
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

    public static boolean insertTP(ThuePhongDTO tp) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            String query = "insert into ThuePhong values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);

            ps.setString(1, tp.getMaChiTietThue());
            ps.setString(2, tp.getMaP());

            Date dateThue = sdf.parse(tp.getNgayThue());
            ps.setTimestamp(3, new java.sql.Timestamp(dateThue.getTime()));

            Date dateTra = sdf.parse(tp.getNgayTra());
            ps.setTimestamp(4, new java.sql.Timestamp(dateTra.getTime()));

            ps.setString(5, tp.getLoaiHinhThue());
            ps.setInt(6, tp.getGia());
            ps.setInt(7, tp.getTinhTrang());
            ps.setInt(8, tp.getXuLy());

            Date date = sdf.parse(tp.getNgayCheckOut());
            ps.setTimestamp(9, new java.sql.Timestamp(date.getTime()));
            if (ps.executeUpdate() >= 1) {
                check = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return check;
    }

    public static ThuePhongDTO getThuePhong(String maP) {
        ThuePhongDTO tp = new ThuePhongDTO();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select ThuePhong.maChiTietThue,maP,ngayThue,ngayTra,ngayCheckOut,loaiHinhThue,gia,tinhTrang,ThuePhong.xuLy from ThuePhong,ChiTietThue where maP = '" + maP + "' and ChiTietThue.maChiTietThue = ThuePhong.maChiTietThue  and tinhTrangXuLy = 0 and tinhTrang = 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String dateThue = sdf.format(rs.getDate("ngayThue"));
                String dateTra = sdf.format(rs.getDate("ngayTra"));
                tp.setMaChiTietThue(rs.getString("maChiTietThue"));
                tp.setMaP(rs.getString("maP"));
                tp.setNgayThue(dateThue);
                tp.setNgayTra(dateTra);
                tp.setGia(rs.getInt("gia"));
                tp.setTinhTrang(rs.getInt("tinhTrang"));
                tp.setNgayCheckOut(dateTra);
                tp.setXuLy(rs.getInt("xuLy"));
                tp.setLoaiHinhThue(rs.getString("loaiHinhThue"));
            }
        } catch (Exception e) {
        }
        return tp;
    }

    public static ArrayList<ThuePhongDTO> getListTP_P(String maCTT, ArrayList<PhongDTO> listP) {
        ArrayList<ThuePhongDTO> listTP = new ArrayList<>();
        listP.clear();
        try {
            java.sql.Connection conn = getConnection();
            String query = "select maChiTietThue, ThuePhong.maP, ngayThue, ngayTra, loaiHinhThue, gia, ThuePhong.tinhTrang, ngayCheckOut, ThuePhong.xuLy, tenP, giaP, loaiP,Phong.tinhTrang as tt, hienTrang, Phong.xuLy as xl "
                    + "from ThuePhong, Phong "
                    + "where ThuePhong.maP = Phong.maP and maChiTietThue = '" + maCTT + "' order by tinhTrang asc ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            SimpleDateFormat sdfm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while (rs.next()) {
                ThuePhongDTO x = new ThuePhongDTO();
                x.setMaChiTietThue(maCTT);
                x.setMaP(rs.getString("maP"));

                String dateThue = sdfm.format(rs.getTimestamp("ngayThue"));
                x.setNgayThue(dateThue);

                String dateTra = sdfm.format(rs.getTimestamp("ngayTra"));
                x.setNgayTra(dateTra);

                x.setLoaiHinhThue(rs.getString("loaiHinhThue"));

                x.setGia(rs.getInt("gia"));
                x.setTinhTrang(rs.getInt("tinhTrang"));

                x.setXuLy(rs.getInt("xuLY"));

                String dateCheckOut = sdfm.format(rs.getTimestamp("ngayCheckOut"));
                x.setNgayCheckOut(dateCheckOut);

                listTP.add(x);

                PhongDTO pDTO = new PhongDTO();
                pDTO.setMaP(rs.getString("maP"));
                pDTO.setTenP(rs.getString("tenP"));
                pDTO.setLoaiP(rs.getString("loaiP"));
                pDTO.setGiaP(rs.getInt("giaP"));
                pDTO.setTinhTrang(rs.getString("tt"));
                pDTO.setHienTrang(rs.getString("hienTrang"));
                pDTO.setXuLy(rs.getInt("xl"));
                listP.add(pDTO);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        }
        return listTP;
    }

    public static boolean deleteTP(String maCTT, String maP, String ngayThue) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Delete from ThuePhong where maChiTietThue = '" + maCTT + "' and maP = '" + maP + "' and ngayThue = '" + ngayThue + "'";
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

    public static boolean updateCheckOut(String maCTT, String maP, String ngayThue, String date) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update ThuePhong set ngayCheckOut = '" + date + "', tinhTrang = 2 where maChiTietThue = '" + maCTT + "' and maP = '" + maP + "' and ngayThue = '" + ngayThue + "'";
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

    public static boolean updateTT(String maCTT, String maP, String ngayThue, int tinhtrang) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update ThuePhong set tinhTrang = " + tinhtrang + " where maChiTietThue = '" + maCTT + "' and maP = '" + maP + "' and ngayThue = '" + ngayThue + "'";
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

    public static boolean updateTTAll(String maCTT) {
        boolean check = false;
        try {
            java.sql.Connection conn = getConnection();
            String query = "Update ThuePhong set tinhTrang = 2 where maChiTietThue = '" + maCTT + "'";
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
}
