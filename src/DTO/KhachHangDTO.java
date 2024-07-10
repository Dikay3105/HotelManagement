package DTO;

public class KhachHangDTO {

    private String maKH;
    private String tenKH;
    private String cmnd;
    private String gioiTinh;
    private String sdt;
    private int xuLy;

    public KhachHangDTO() {
    }

    public KhachHangDTO(String maKH, String tenKH, String cmnd, String gioiTinh, String sdt, int xuLy) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.cmnd = cmnd;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.xuLy = xuLy;

    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
}
