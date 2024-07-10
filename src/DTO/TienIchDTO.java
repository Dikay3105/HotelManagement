package DTO;

public class TienIchDTO {

    private String maTienIch;
    private String tenTienIch;
    private int xuLy;

    public TienIchDTO() {
    }

    public TienIchDTO(String maTienIch, String tenTienIch, int xuLy) {
        this.maTienIch = maTienIch;
        this.tenTienIch = tenTienIch;
        this.xuLy = xuLy;
    }

    public String getMaTienIch() {
        return maTienIch;
    }

    public void setMaTienIch(String maTienIch) {
        this.maTienIch = maTienIch;
    }

    public String getTenTienIch() {
        return tenTienIch;
    }

    public void setTenTienIch(String tenTienIch) {
        this.tenTienIch = tenTienIch;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }

}
