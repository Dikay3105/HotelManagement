package DTO;

public class HoaDonDTO {

    private String maHD;
    private int tienPhong;
    private int tienDichVu;
    private int tongTien;
    private String ngayThanhToan;
    private String maChiTietThue;
    private int giamGia;
    private String maNV;
    private int xuLy;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String maHD, int tienPhong, int tienDichVu, int tongTien, String ngayThanhToan, String maChiTietThue, int giamGia, int xuLy, String maNV) {
        this.maHD = maHD;
        this.tienPhong = tienPhong;
        this.tienDichVu = tienDichVu;
        this.tongTien = tongTien;
        this.ngayThanhToan = ngayThanhToan;
        this.maChiTietThue = maChiTietThue;
        this.giamGia = giamGia;
        this.xuLy = xuLy;
        this.maNV = maNV;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getTienPhong() {
        return tienPhong;
    }

    public void setTienPhong(int tienPhong) {
        this.tienPhong = tienPhong;
    }

    public int getTienDichVu() {
        return tienDichVu;
    }

    public void setTienDichVu(int tienDichVu) {
        this.tienDichVu = tienDichVu;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getMaChiTietThue() {
        return maChiTietThue;
    }

    public void setMaChiTietThue(String maChiTietThue) {
        this.maChiTietThue = maChiTietThue;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

}
