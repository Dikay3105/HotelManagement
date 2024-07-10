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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ANH KHOA
 */
public class ImportExcel {

    public void ImportExcel_NhanVien(String path) {
        String[] expectedHeaders = {"STT", "Mã Nhân Viên", "Mật Khẩu", "Họ Tên", "Giới Tính", "Ngày Sinh", "Phòng Ban", "Email", "Hệ Số", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {
                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        NhanVienDTO nv = new NhanVienDTO();
                        nv.setMaNV(row.getCell(1).getStringCellValue());
                        nv.setmKNV(row.getCell(2).getStringCellValue());
                        nv.setTenNV(row.getCell(3).getStringCellValue());
                        nv.setGioiTinh(row.getCell(4).getStringCellValue());
                        nv.setNgaySinhString(row.getCell(5).getStringCellValue());
                        nv.setTenPB(row.getCell(6).getStringCellValue());
                        nv.setEmail(row.getCell(7).getStringCellValue());
                        nv.setHeSoLuong((int) row.getCell(8).getNumericCellValue());
                        nv.setXuLy((int) row.getCell(9).getNumericCellValue());

                        ArrayList<NhanVienDTO> ListNhanVien = NhanVienDAO.LoadData();
                        int count = 0;
                        for (NhanVienDTO x : ListNhanVien) {
                            if (x.getMaNV().equals(nv.getMaNV())) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            new NhanVienDAO().InsertNhanVien(nv);
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }

            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }

            workbook.close();
            file.close();
        } catch (Exception e) {
        }

    }

    public void ImportExcel_KhachHang(String path) {
        String[] expectedHeaders = {"STT", "Mã Khách Hàng", "Tên Khách Hàng", "CMND", "Giới Tính", "Số Điện Thoại", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {

                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        KhachHangDTO kh = new KhachHangDTO();
                        kh.setMaKH(row.getCell(1).getStringCellValue());
                        kh.setTenKH(row.getCell(2).getStringCellValue());
                        kh.setCmnd(row.getCell(3).getStringCellValue());
                        kh.setGioiTinh(row.getCell(4).getStringCellValue());
                        kh.setSdt(row.getCell(5).getStringCellValue());
                        kh.setXuLy((int) row.getCell(6).getNumericCellValue());

                        ArrayList<KhachHangDTO> ListKhachHang = KhachHangDAO.LoadData();
                        int count = 0;
                        for (KhachHangDTO x : ListKhachHang) {
                            if (x.getMaKH().equals(kh.getMaKH())) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            KhachHangBUS.InsertKH2(kh);
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }
            workbook.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ImportExcel_Phong(String path) throws SQLException, ParseException {
        String[] expectedHeaders = {"STT", "Mã Phòng", "Tên Phòng", "Loại Phòng", "Giá Phòng", "Tình Trạng", "Hiện Trạng", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {

                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        PhongDTO p = new PhongDTO();

                        p.setMaP(row.getCell(1).getStringCellValue() + "");
                        p.setTenP(row.getCell(2).getStringCellValue() + "");
                        p.setLoaiP(row.getCell(3).getStringCellValue() + "");
                        p.setGiaP((int) row.getCell(4).getNumericCellValue());
                        p.setTinhTrang(row.getCell(5).getStringCellValue());
                        p.setHienTrang(row.getCell(6).getStringCellValue());
                        p.setXuLy((int) row.getCell(7).getNumericCellValue());

                        ArrayList<PhongDTO> ListPhong = PhongDAO.getListPhong();
                        int count = 0;
                        for (PhongDTO x : ListPhong) {
                            if (x.getMaP().equals(row.getCell(1).getStringCellValue() + "")) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            PhongDAO.insertPhong(p);
                        }
                    }

                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }

            workbook.close();
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ImportExcel_ThuePhong(String path) throws SQLException, ParseException {
        String[] expectedHeaders = {"STT", "Mã Chi Tiết Thuê", "Mã Phòng", "Ngày Thuê", "Ngày Trả", "Loại Hình Thuê", "Giá", "Tình Trạng", "Ngày CheckOut", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {
                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        ThuePhongDTO p = new ThuePhongDTO();

                        p.setMaChiTietThue(row.getCell(1).getStringCellValue() + "");
                        p.setMaP(row.getCell(2).getStringCellValue() + "");

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

                        Date NgayThueDate = row.getCell(3).getDateCellValue();
                        String NgayThue = dateFormat.format(NgayThueDate);
                        p.setNgayThue(NgayThue);

                        Date NgayTraDate = row.getCell(4).getDateCellValue();
                        String NgayTra = dateFormat.format(NgayTraDate);
                        p.setNgayTra(NgayTra);

                        p.setLoaiHinhThue(row.getCell(5).getStringCellValue());
                        p.setGia((int) row.getCell(6).getNumericCellValue());
                        p.setTinhTrang((int) row.getCell(7).getNumericCellValue());

                        Date NgayCheckOutDate = row.getCell(8).getDateCellValue();
                        String NgayCheckOut = dateFormat.format(NgayCheckOutDate);
                        p.setNgayCheckOut(NgayCheckOut + "");

                        p.setXuLy((int) row.getCell(9).getNumericCellValue());
                        ArrayList<ThuePhongDTO> ListThuePhong = ThuePhongDAO.LoadData();
                        int count = 0;
                        int count1 = 0;
                        int count2 = 0;
                        for (ThuePhongDTO x : ListThuePhong) {
                            if (x.getMaChiTietThue().equals(row.getCell(1).getStringCellValue() + "") && x.getMaP().equals(row.getCell(2).getStringCellValue() + "")) {
                                count++;
                            }
                        }
                        ArrayList<ChiTietThueDTO> ListCTT = ChiTietThueDAO.LoadData2();
                        for (ChiTietThueDTO ctt : ListCTT) {
                            if (ctt.getMaChiTietThue().equals(p.getMaChiTietThue())) {
                                count1++;
                            }
                        }
                        ArrayList<PhongDTO> ListP = PhongDAO.getListPhong();
                        for (PhongDTO pdto : ListP) {
                            if (pdto.getMaP().equals(p.getMaP())) {
                                count2++;
                            }
                        }
                        if (count == 0 && count1 == 1 && count2 == 1) {
                            ThuePhongDAO.InsertThuePhong(p);
                        } else {
                            if (count1 == 0) {
                                JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã " + p.getMaChiTietThue());
                            }
                            if (count2 == 0) {
                                JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã Phòng " + p.getMaP());
                            }
                        }
                    }

                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }

        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ImportExcel_DichVu(String path) throws SQLException, ParseException {
        String[] expectedHeaders = {"STT", "Mã Dịch Vụ", "Tên Dịch Vụ", "Tên Loại Dịch Vụ", "Giá Dịch Vụ", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {
                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        DichVuDTO p = new DichVuDTO();

                        p.setMaDV(row.getCell(1).getStringCellValue() + "");
                        p.setTenDV(row.getCell(2).getStringCellValue() + "");
                        p.setTenLoaiDV(row.getCell(3).getStringCellValue() + "");
                        p.setGiaDV((int) row.getCell(4).getNumericCellValue());
                        p.setXuLy((int) row.getCell(5).getNumericCellValue());

                        ArrayList<DichVuDTO> ListDichVu = DichVuDAO.getListDichVu();
                        int count = 0;
                        for (DichVuDTO x : ListDichVu) {
                            if (x.getMaDV().equals(row.getCell(1).getStringCellValue() + "")) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            DichVuDAO.insertDV(p);
                        }
                    }

                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }
            workbook.close();
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ImportExcel_SuDungDichVu(String path) throws SQLException, ParseException {
        String[] expectedHeaders = {"STT", "Mã Chi Tiết Thuê", "Mã Dịch Vụ", "Ngày Sử Dụng", "Số Lượng", "Đơn Giá", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {
                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        SuDungDichVuDTO p = new SuDungDichVuDTO();

                        p.setMaChiTietThue(row.getCell(1).getStringCellValue() + "");
                        p.setMaDV(row.getCell(2).getStringCellValue() + "");

//                        Date NgaySuDungDate = row.getCell(3).getDateCellValue();
//                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//                        String dateString = format.format(NgaySuDungDate);
                        String dateString = row.getCell(3).getStringCellValue();
                        p.setNgaySuDungString(dateString);

                        p.setSoLuong((int) row.getCell(4).getNumericCellValue());
                        p.setDonGia((int) row.getCell(5).getNumericCellValue());
                        p.setXuLy((int) row.getCell(6).getNumericCellValue());

                        ArrayList<SuDungDichVuDTO> ListSuDungDichVu = SuDungDichVuDAO.LoadData();
                        int count = 0;
                        for (SuDungDichVuDTO x : ListSuDungDichVu) {
                            if (x.getMaChiTietThue().equals(row.getCell(1).getStringCellValue() + "") && x.getMaDV().equals(row.getCell(2).getStringCellValue() + "") && x.getNgaySuDungString().equals(p.getNgaySuDungString())) {
                                count++;
                            }
                        }
                        int count1 = 0;
                        ArrayList<ChiTietThueDTO> ListCTT = ChiTietThueDAO.LoadData2();
                        for (ChiTietThueDTO ctt : ListCTT) {
                            if (ctt.getMaChiTietThue().equals(p.getMaChiTietThue())) {
                                count1++;
                            }
                        }
                        int count2 = 0;
                        ArrayList<DichVuDTO> ListDV = DichVuDAO.getListDichVu();
                        for (DichVuDTO dv : ListDV) {
                            if (dv.getMaDV().equals(p.getMaDV())) {
                                count2++;
                            }
                        }
                        if (count == 0 && count1 == 1 && count2 == 1) {
                            SuDungDichVuDAO.InsertSuDungDichVu_import(p);
                        }
//                        else{ 
//                            if(count1==0)
//                                JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã "+ p.getMaChiTietThue());
//                            if(count2==0)   
//                                 JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã "+ p.getMaDV());
//                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }
            workbook.close();
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ImportExcel_TienIch(String path) throws SQLException, ParseException {
        String[] expectedHeaders = {"STT", "Mã Tiện Ích", "Tên Tiện Ích", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {
                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        TienIchDTO p = new TienIchDTO();

                        p.setMaTienIch(row.getCell(1).getStringCellValue() + "");
                        p.setTenTienIch(row.getCell(2).getStringCellValue() + "");
                        p.setXuLy((int) row.getCell(3).getNumericCellValue());

                        ArrayList<TienIchDTO> ListTienIch = TienIchDAO.getListTienIch();
                        int count = 0;
                        for (TienIchDTO x : ListTienIch) {
                            if (x.getMaTienIch().equals(row.getCell(1).getStringCellValue() + "")) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            TienIchDAO.insertTienIch(p);
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }
            workbook.close();
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ImportExcel_ChiTietTienIch(String path) throws SQLException, ParseException {

        String[] expectedHeaders = {"STT", "Mã Tiện Ích", "Mã Phòng", "Số Lượng"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {
                    for (Row row : sheet) {

                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        ChiTietTienIchDTO p = new ChiTietTienIchDTO();

                        p.setMaTienIch(row.getCell(1).getStringCellValue() + "");
                        p.setMaP(row.getCell(2).getStringCellValue() + "");
                        p.setSoLuong((int) row.getCell(3).getNumericCellValue());

                        ArrayList<TienIchDTO> ListTI = TienIchDAO.getListTienIch();
                        int count = 0;
                        for (TienIchDTO x : ListTI) {
                            if (x.getMaTienIch().equals(p.getMaTienIch() + "")) {
                                count++;
                            }
                        }
                        int count1 = 0;
                        ArrayList<PhongDTO> ListP = PhongDAO.getListPhong();
                        for (PhongDTO phg : ListP) {
                            if (phg.getMaP().equals(row.getCell(2).getStringCellValue() + "")) {
                                count1++;
                            }
                        }
                        if (count != 0 && count1 != 0) {
                            ChiTietTienIchDAO.insertChiTietTienIch(p);
                        }
//                        else 
//                            if(count1!=0)
//                                JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã "+ p.getMaP());

                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }
            workbook.close();
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ImportExcel_ChiTietThue(String path) throws SQLException, ParseException {
        String[] expectedHeaders = {"STT", "Mã Chi Tiết Thuê", "Mã Khách Hàng", "Mã Nhân Viên", "Tiền Đặt Cọc", "Tình Trạng", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {
                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        ChiTietThueDTO p = new ChiTietThueDTO();

                        p.setMaChiTietThue(row.getCell(1).getStringCellValue() + "");
                        p.setMaKH(row.getCell(2).getStringCellValue() + "");
                        p.setMaNV(row.getCell(3).getStringCellValue());
                        p.setTienDatCoc((int) row.getCell(4).getNumericCellValue());
                        p.setTinhTrangXuLy((int) row.getCell(5).getNumericCellValue());
                        p.setXuLy((int) row.getCell(6).getNumericCellValue());

                        ArrayList<ChiTietThueDTO> ListChiTietThue = ChiTietThueDAO.LoadData2();
                        int count = 0;
                        for (ChiTietThueDTO x : ListChiTietThue) {
                            if (x.getMaChiTietThue().equals(row.getCell(1).getStringCellValue() + "")) {
                                count++;
                            }

                        }
                        ArrayList<NhanVienDTO> ListNhanVien = NhanVienDAO.LoadData();
                        int count1 = 0;
                        for (NhanVienDTO x : ListNhanVien) {
                            if (x.getMaNV().equals(p.getMaNV())) {
                                count1++;
                            }
                        }
                        ArrayList<KhachHangDTO> ListKhachHang = KhachHangDAO.LoadData();
                        int count2 = 0;
                        for (KhachHangDTO x : ListKhachHang) {
                            if (x.getMaKH().equals(p.getMaKH())) {
                                count2++;
                            }
                        }
                        if (count == 0 && count1 != 0 && count2 != 0) {
                            ChiTietThueDAO.InsertChiTietThue(p);
                        }
//                        else{ 
//                            if(count1==0)
//                                JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã "+ p.getMaNV());
//                            if(count2==0)   
//                                 JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã "+ p.getMaKH());
//                        }    
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }
            workbook.close();
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ImportExcel_HoaDon(String path) throws SQLException, ParseException {
        String[] expectedHeaders = {"STT", "Mã Hóa Đơn", "Tiền Phòng", "Tiền Dịch Vụ", "Tổng Tiền", "Ngày Thanh Toán", "Mã Chi Tiết Thuê", "Giảm Giá", "Mã Nhân Viên", "Xử Lý"};
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            if (hasNullValue(sheet) == false) {
                if (checkHeader(sheet, expectedHeaders)) {
                    for (Row row : sheet) {
                        // Bỏ qua hàng đầu tiên vì nó chứa tiêu đề của bảng Excel
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        HoaDonDTO p = new HoaDonDTO();

                        p.setMaHD(row.getCell(1).getStringCellValue() + "");
                        p.setTienPhong((int) row.getCell(2).getNumericCellValue());
                        p.setTienDichVu((int) row.getCell(3).getNumericCellValue());
                        p.setTongTien((int) row.getCell(4).getNumericCellValue());

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date NgayThanhToanDate = row.getCell(5).getDateCellValue();
                        String NgayThanhToanDatetxt = dateFormat.format(NgayThanhToanDate);
                        p.setNgayThanhToan(NgayThanhToanDatetxt);

                        p.setMaChiTietThue(row.getCell(6).getStringCellValue() + "");
                        p.setGiamGia((int) row.getCell(7).getNumericCellValue());
                        p.setMaNV(row.getCell(8).getStringCellValue() + "");
                        p.setXuLy((int) row.getCell(9).getNumericCellValue());

                        ArrayList<HoaDonDTO> ListHoaDon = HoaDonDAO.getListHoaDon();
                        int count = 0;
                        for (HoaDonDTO x : ListHoaDon) {
                            if (x.getMaHD().equals(row.getCell(1).getStringCellValue() + "")) {
                                count++;
                            }

                        }
                        ArrayList<NhanVienDTO> ListNhanVien = NhanVienDAO.LoadData();
                        int count1 = 0;
                        for (NhanVienDTO x : ListNhanVien) {
                            if (x.getMaNV().equals(p.getMaNV())) {
                                count1++;
                            }
                        }
                        ArrayList<ChiTietThueDTO> ListChiTietThue = ChiTietThueDAO.LoadData2();
                        int count2 = 0;
                        for (ChiTietThueDTO x : ListChiTietThue) {
                            if (x.getMaChiTietThue().equals(p.getMaChiTietThue())) {
                                count2++;
                            }
                        }
                        if (count == 0 && count1 != 0 && count2 != 0) {
                            HoaDonDAO.InsertHoaDon(p);
                        }
//                        else{ 
//                            if(count1==0)
//                                JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã "+ p.getMaNV());
//                            if(count2==0)
//                                JOptionPane.showConfirmDialog(null, "Việc Thực Hiện Vi Phạm CSDL Kiểm Tra Lại Dữ Liệu Có Mã "+ p.getMaChiTietThue());
//                        }        
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Tiêu Đề Cột Hoặc Thứ Tự Cột Dữ Liệu ");
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Hãy Kiểm Tra Lại, Có Giá Trị Là NULL");
            }
            workbook.close();
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkHeader(Sheet sheet, String[] expectedHeaders) {
        Row headerRow = sheet.getRow(0);
        int numColumns = headerRow.getLastCellNum();
        if (numColumns != expectedHeaders.length) {
            return false;
        }
        for (int i = 0; i < numColumns; i++) {
            Cell cell = headerRow.getCell(i);
            String actualHeader = cell.getStringCellValue().trim();
            String expectedHeader = expectedHeaders[i];
            if (!actualHeader.equals(expectedHeader)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasNullValue(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        Row firstRow = sheet.getRow(0);
        int lastCellNum = firstRow.getLastCellNum();
        for (int i = 0; i <= lastRowNum; i++) { // duyệt hết các hàng trong bảng
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = 0; j < lastCellNum; j++) { // duyệt hết các ô trong hàng
                Cell cell = row.getCell(j);
                if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    return true; // nếu có bất kỳ ô nào null hoặc rỗng thì trả về true và dừng hàm
                }
                if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().isEmpty()) {
                    return true; // nếu có ô chứa chuỗi rỗng thì trả về true và dừng hàm
                }
            }
        }
        return false; // nếu không có ô nào null hoặc rỗng thì trả về false
    }
}
