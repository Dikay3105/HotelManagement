package DTO;

public class SuDungDichVuDTO {

    private String maChiTietThue;
    private String maDV;
    private String ngaySuDungString;
    private int soLuong;
    private int donGia;
    private int xuLy;

    public SuDungDichVuDTO() {
    }

    public SuDungDichVuDTO(String maChiTietThue, String maDV, String ngaySuDungString, int soLuong, int donGia, int xuLy) {
        this.maChiTietThue = maChiTietThue;
        this.maDV = maDV;
        this.ngaySuDungString = ngaySuDungString;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.xuLy = xuLy;
    }

    public String getMaChiTietThue() {
        return maChiTietThue;
    }

    public void setMaChiTietThue(String maChiTietThue) {
        this.maChiTietThue = maChiTietThue;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getNgaySuDungString() {
        return ngaySuDungString;
    }

    public void setNgaySuDungString(String ngaySuDungString) {
        this.ngaySuDungString = ngaySuDungString;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }

}
