package DTO;

public class ChiTietTienIchDTO {

    private String maP;
    private String maTienIch;
    private int soLuong;

    public ChiTietTienIchDTO() {
    }

    public ChiTietTienIchDTO(String maP, String maTienIch, int soLuong) {
        this.maP = maP;
        this.maTienIch = maTienIch;
        this.soLuong = soLuong;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public String getMaTienIch() {
        return maTienIch;
    }

    public void setMaTienIch(String maTienIch) {
        this.maTienIch = maTienIch;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

}
