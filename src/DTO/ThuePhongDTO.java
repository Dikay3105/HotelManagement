package DTO;

public class ThuePhongDTO {

    private String maChiTietThue;
    private String maP;
    private String ngayThue;
    private String ngayTra;
    private String loaiHinhThue;
    private int gia;
    private int tinhTrang;
    private String ngayCheckOut;
    private int xuLy;

    public ThuePhongDTO() {
    }

    public ThuePhongDTO(String maChiTietThue, String maP, String ngayThue, String ngayTra, String loaiHinhThue, int gia, int tinhTrang, String ngayCheckOut, int xuLy) {
        this.maChiTietThue = maChiTietThue;
        this.maP = maP;
        this.ngayThue = ngayThue;
        this.ngayTra = ngayTra;
        this.loaiHinhThue = loaiHinhThue;
        this.gia = gia;
        this.tinhTrang = tinhTrang;
        this.ngayCheckOut = ngayCheckOut;
        this.xuLy = xuLy;
    }

    public String getMaChiTietThue() {
        return maChiTietThue;
    }

    public void setMaChiTietThue(String maChiTietThue) {
        this.maChiTietThue = maChiTietThue;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getLoaiHinhThue() {
        return loaiHinhThue;
    }

    public void setLoaiHinhThue(String loaiHinhThue) {
        this.loaiHinhThue = loaiHinhThue;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayCheckOut() {
        return ngayCheckOut;
    }

    public void setNgayCheckOut(String ngayCheckOut) {
        this.ngayCheckOut = ngayCheckOut;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }

}
