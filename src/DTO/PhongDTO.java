package DTO;

public class PhongDTO {

    private String maP;
    private String tenP;
    private String loaiP;
    private int giaP;
    private String tinhTrang;
    private String hienTrang;
    private int xuLy;

    public PhongDTO() {
    }

    public PhongDTO(String maP, String tenP, String loaiP, int giaP, String tinhTrang, String hienTrang, int xuLy) {
        this.maP = maP;
        this.tenP = tenP;
        this.loaiP = loaiP;
        this.giaP = giaP;
        this.tinhTrang = tinhTrang;
        this.hienTrang = hienTrang;
        this.xuLy = xuLy;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public String getTenP() {
        return tenP;
    }

    public void setTenP(String tenP) {
        this.tenP = tenP;
    }

    public String getLoaiP() {
        return loaiP;
    }

    public void setLoaiP(String loaiP) {
        this.loaiP = loaiP;
    }

    public int getGiaP() {
        return giaP;
    }

    public void setGiaP(int giaP) {
        this.giaP = giaP;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getHienTrang() {
        return hienTrang;
    }

    public void setHienTrang(String hienTrang) {
        this.hienTrang = hienTrang;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }

}
