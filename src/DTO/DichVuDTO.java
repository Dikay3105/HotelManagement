package DTO;

public class DichVuDTO {

    private String maDV;
    private String tenDV;
    private String tenLoaiDV;
    private int giaDV;
    private int xuLy;

    public DichVuDTO() {
    }

    public DichVuDTO(String maDV, String tenDV, String tenLoaiDV, int giaDV, int xuLy) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.tenLoaiDV = tenLoaiDV;
        this.giaDV = giaDV;
        this.xuLy = xuLy;
    }

    public String getTenLoaiDV() {
        return tenLoaiDV;
    }

    public void setTenLoaiDV(String tenLoaiDV) {
        this.tenLoaiDV = tenLoaiDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }

}
