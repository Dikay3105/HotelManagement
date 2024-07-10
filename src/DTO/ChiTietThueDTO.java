package DTO;

public class ChiTietThueDTO {

    private String maChiTietThue;
    private String maKH;
    private String maNV;
    private int tienDatCoc;
    private int tinhTrangXuLy;
    private int xuLy;

    public ChiTietThueDTO() {
    }

    public ChiTietThueDTO(String maChiTietThue, String maKH, String maNV, int tienDatCoc, int tinhTrangXuLy, int xuLy) {
        this.maChiTietThue = maChiTietThue;
        this.maKH = maKH;
        this.maNV = maNV;
        this.tienDatCoc = tienDatCoc;
        this.tinhTrangXuLy = tinhTrangXuLy;
        this.xuLy = xuLy;
    }

    public String getMaChiTietThue() {
        return maChiTietThue;
    }

    public void setMaChiTietThue(String maChiTietThue) {
        this.maChiTietThue = maChiTietThue;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getTienDatCoc() {
        return tienDatCoc;
    }

    public void setTienDatCoc(int tienDatCoc) {
        this.tienDatCoc = tienDatCoc;
    }

    public int getTinhTrangXuLy() {
        return tinhTrangXuLy;
    }

    public void setTinhTrangXuLy(int tinhTrangXuLy) {
        this.tinhTrangXuLy = tinhTrangXuLy;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }

}
