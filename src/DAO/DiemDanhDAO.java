
package DAO;

/**
 *
 * @author duyli
 */
import DTO.DiemDanhDTO;
import static dao.DBConnect.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class DiemDanhDAO {
    public static ArrayList<DiemDanhDTO> DDList = new ArrayList<>();

    public static ArrayList<DiemDanhDTO> LoadData() {
        DDList = new ArrayList<>();
        try {
            java.sql.Connection cons = getConnection();
            String sql = "select * from DiemDanh";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            while (rs.next()) {
                DiemDanhDTO DD = new DiemDanhDTO();

                DD.setMaNV(rs.getString("maNV"));
                String datetime = sdf.format(rs.getDate("ngayDiLam"));
                DD.setNgayDiLam(datetime);
                DDList.add(DD);

            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DDList;
    }
    public static void InsertDiemDanh(DiemDanhDTO NV) throws SQLException, ParseException {
        java.sql.Connection cons = getConnection();
       
        cons.close();
    }
}
