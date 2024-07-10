package BUS;
import DAO.ChiTietThueDAO;
import DAO.ChiTietTienIchDAO;
import DAO.DichVuDAO;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.PhongDAO;
import DAO.SuDungDichVuDAO;
import DAO.ThuePhongDAO;
import DAO.TienIchDAO;
import DTO.ChiTietThueDTO;
import DTO.ChiTietTienIchDTO;
import DTO.DichVuDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.PhongDTO;
import DTO.SuDungDichVuDTO;
import DTO.ThuePhongDTO;
import DTO.TienIchDTO;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author ANH KHOA
 */
public class BackUpData {
    
//backup_NhanVien
    public void backup_NhanVien(String path) throws FileNotFoundException, IOException{
        
        ArrayList<NhanVienDTO> ListNhanVien = NhanVienDAO.LoadData();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Thông Tin Nhân Viên");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Nhân Viên");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Mật Khẩu");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Họ Tên");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Giới Tính");
        Cell headerCell6 = headerRow.createCell(5);
        headerCell6.setCellValue("Ngày Sinh");
        Cell headerCell7 = headerRow.createCell(6);
        headerCell7.setCellValue("Phòng Ban");
         Cell headerCell8 = headerRow.createCell(7);
        headerCell8.setCellValue("Email");
        Cell headerCell9 = headerRow.createCell(8);
        headerCell9.setCellValue("Hệ Số");
        Cell headerCell10 = headerRow.createCell(9);
        headerCell10.setCellValue("Xử Lý");

        int rowNum = 1;
        for (NhanVienDTO nv : ListNhanVien) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(nv.getMaNV());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(nv.getmKNV());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(nv.getTenNV());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(nv.getGioiTinh());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(nv.getNgaySinhString());
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(nv.getTenPB());
            Cell cell8 = row.createCell(7);
            cell8.setCellValue(nv.getEmail());
            Cell cell9 = row.createCell(8);
            cell9.setCellValue(nv.getHeSoLuong());
            Cell cell10 = row.createCell(9);
            cell10.setCellValue(nv.getXuLy());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }
//backup_KhachHang    
    public void backup_KhachHang(String path) throws FileNotFoundException, IOException{
        
        ArrayList<KhachHangDTO> ListKhachHang = KhachHangDAO.LoadData();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Thông Tin Khách Hàng");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Khách Hàng");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Tên Khách Hàng");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("CMND");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Giới Tính");
        Cell headerCell6 = headerRow.createCell(5);
        headerCell6.setCellValue("Số Điện Thoại");
        Cell headerCell7 = headerRow.createCell(6);
        headerCell7.setCellValue("Xử Lý");

        int rowNum = 1;
        for (KhachHangDTO kh : ListKhachHang) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(kh.getMaKH());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(kh.getTenKH());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(kh.getCmnd());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(kh.getGioiTinh());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(kh.getSdt());
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(kh.getXuLy());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }
//backup_Phong    
    public void backup_Phong(String path) throws FileNotFoundException, IOException{
        
        ArrayList<PhongDTO> ListPhong = PhongDAO.getListPhong();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Thông Tin Phòng");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Phòng");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Tên Phòng");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Loại Phòng");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Giá Phòng");
        Cell headerCell6 = headerRow.createCell(5);
        headerCell6.setCellValue("Tình Trạng");
        Cell headerCell7 = headerRow.createCell(6);
        headerCell7.setCellValue("Hiện Trạng");
        Cell headerCell8 = headerRow.createCell(7);
        headerCell8.setCellValue("Xử Lý");

        int rowNum = 1;
        for (PhongDTO ph : ListPhong) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(ph.getMaP());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(ph.getTenP());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(ph.getLoaiP());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(ph.getGiaP());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(ph.getTinhTrang());
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(ph.getHienTrang());
            Cell cell8 = row.createCell(7);
            cell8.setCellValue(ph.getXuLy());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }
//backup_ThuePhong
    public void backup_ThuePhong(String path) throws FileNotFoundException, IOException{
        
        ArrayList<ThuePhongDTO> ListThuePhong = ThuePhongDAO.LoadData();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Thông Tin Thuê Phòng");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Chi Tiết Thuê");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Mã Phòng");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Ngày Thuê");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Ngày Trả");
        Cell headerCell6 = headerRow.createCell(5);
        headerCell6.setCellValue("Loại Hình Thuê");
        Cell headerCell7 = headerRow.createCell(6);
        headerCell7.setCellValue("Giá");
        Cell headerCell8 = headerRow.createCell(7);
        headerCell8.setCellValue("Tình Trạng");
        Cell headerCell9 = headerRow.createCell(8);
        headerCell9.setCellValue("Ngày CheckOut");
        Cell headerCell10 = headerRow.createCell(9);
        headerCell10.setCellValue("Xử Lý");

        int rowNum = 1;
        for (ThuePhongDTO tp : ListThuePhong) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(tp.getMaChiTietThue());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(tp.getMaP());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(tp.getNgayThue());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(tp.getNgayTra());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(tp.getLoaiHinhThue());
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(tp.getGia());
            Cell cell8 = row.createCell(7);
            cell8.setCellValue(tp.getTinhTrang());
            Cell cell9 = row.createCell(8);
            cell9.setCellValue(tp.getNgayCheckOut());
            Cell cell10 = row.createCell(9);
            cell10.setCellValue(tp.getXuLy());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }   
//backup_DichVu    
    public void backup_DichVu(String path) throws FileNotFoundException, IOException{
        
        ArrayList<DichVuDTO> ListDichVu = DichVuDAO.getListDichVu();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Thông Tin Dịch Vụ");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Dịch Vụ");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Tên Dịch Vụ");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Tên Loại Dịch Vụ");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Giá Dịch Vụ");
        Cell headerCell6 = headerRow.createCell(5);
        headerCell6.setCellValue("Xử Lý");

        int rowNum = 1;
        for (DichVuDTO dv : ListDichVu) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(dv.getMaDV());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(dv.getTenDV());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(dv.getTenLoaiDV());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(dv.getGiaDV());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(dv.getXuLy());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }
//backup_SuDungDichVu   
    public void backup_SuDungDichVu(String path) throws FileNotFoundException, IOException{
        
        ArrayList<SuDungDichVuDTO> ListSuDungDichVu = SuDungDichVuDAO.LoadData();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sử Dụng Dịch Vụ");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Chi Tiết Thuê");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Mã Dịch Vụ");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Ngày Sử Dụng");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Số Lượng");
        Cell headerCell6 = headerRow.createCell(5);
        headerCell6.setCellValue("Đơn Giá");
        Cell headerCell7 = headerRow.createCell(6);
        headerCell7.setCellValue("Xử Lý");

        int rowNum = 1;
        for (SuDungDichVuDTO sddv : ListSuDungDichVu) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(sddv.getMaChiTietThue());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(sddv.getMaDV());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(sddv.getNgaySuDungString());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(sddv.getSoLuong());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(sddv.getDonGia());
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(sddv.getXuLy());

        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }    

//backup_TienIch
    public void backup_TienIch(String path) throws FileNotFoundException, IOException{
        
        ArrayList<TienIchDTO> ListTienIch = TienIchDAO.getListTienIch();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Thông Tin Tiện Ích");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Tiện Ích");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Tên Tiện Ích");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Xử Lý");

        int rowNum = 1;
        for (TienIchDTO ti : ListTienIch) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(ti.getMaTienIch());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(ti.getTenTienIch());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(ti.getXuLy());
    
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }
//backup_ChiTietTienIch
    public void backup_ChiTietTienIch(String path) throws FileNotFoundException, IOException{
        
        ArrayList<ChiTietTienIchDTO> ListChiTietTienIch = ChiTietTienIchDAO.LoadData();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Chi Tiết Tiện Ích");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Tiện Ích");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Mã Phòng");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Số Lượng");
        int rowNum = 1;
        for (ChiTietTienIchDTO ctti : ListChiTietTienIch) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(ctti.getMaTienIch());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(ctti.getMaP());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(ctti.getSoLuong());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }    
//backup_ChiTietThue
    public void backup_ChiTietThue(String path) throws FileNotFoundException, IOException{
        
        ArrayList<ChiTietThueDTO> ListChiTietThue = ChiTietThueDAO.LoadData2();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Chi Tiết Thuê");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Chi Tiết Thuê");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Mã Khách Hàng");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Mã Nhân Viên");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Tiền Đặt Cọc");
        Cell headerCell6 = headerRow.createCell(5);
        headerCell6.setCellValue("Tình Trạng Xử Lý");
        Cell headerCell7 = headerRow.createCell(6);
        headerCell7.setCellValue("Xử Lý");

        int rowNum = 1;
        for (ChiTietThueDTO cttt : ListChiTietThue) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(cttt.getMaChiTietThue());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(cttt.getMaKH());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(cttt.getMaNV());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(cttt.getTienDatCoc());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(cttt.getTinhTrangXuLy());
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(cttt.getXuLy());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }    
//backup_HoaDon
    public void backup_HoaDon(String path) throws FileNotFoundException, IOException{
        
        ArrayList<HoaDonDTO> ListHoaDon= HoaDonDAO.getListHoaDon();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Danh Sách Hóa Đơn");
            
        XSSFRow headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("STT");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Mã Hóa Đơn");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Tiền Phòng");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Tiền Dịch Vụ");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Tổng Tiền");
        Cell headerCell6 = headerRow.createCell(5);
        headerCell6.setCellValue("Ngày Thanh Toán");
        Cell headerCell7 = headerRow.createCell(6);
        headerCell7.setCellValue("Mã Chi Tiết Thuê");
        Cell headerCell8 = headerRow.createCell(7);
        headerCell8.setCellValue("Giảm Giá");
        Cell headerCell9 = headerRow.createCell(8);
        headerCell9.setCellValue("Mã Nhân Viên");
        Cell headerCell10 = headerRow.createCell(9);
        headerCell10.setCellValue("Xử Lý");

        int rowNum = 1;
        for (HoaDonDTO hd : ListHoaDon) {
            XSSFRow row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(rowNum-1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(hd.getMaHD());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(hd.getTienPhong());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(hd.getTienDichVu());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(hd.getTongTien());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(hd.getNgayThanhToan());
            Cell cell7 = row.createCell(6);
            cell7.setCellValue(hd.getMaChiTietThue());
            Cell cell8 = row.createCell(7);
            cell8.setCellValue(hd.getGiamGia());
            Cell cell9 = row.createCell(8);
            cell9.setCellValue(hd.getMaNV());
            Cell cell10 = row.createCell(9);
            cell10.setCellValue(hd.getXuLy());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }        
}
