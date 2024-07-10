package DTO;

public class NhanVienDTO {
    private String maNV;
    private String mKNV;
    private String tenNV;
    private String gioiTinh;
    private String ngaySinhString;
    private int heSoLuong;
    private String tenPB;
    private String Email;
    private int xuLy;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String maNV, String mKNV, String tenNV, String gioiTinh, String ngaySinhString, int heSoLuong, String tenPB,String Email, int xuLy) {
        this.maNV = maNV;
        this.mKNV = mKNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinhString = ngaySinhString;
        this.heSoLuong = heSoLuong;
        this.tenPB = tenPB;
        this.Email =Email;
        this.xuLy = xuLy;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getmKNV() {
        return mKNV;
    }

    public void setmKNV(String mKNV) {
        this.mKNV = mKNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinhString() {
        return ngaySinhString;
    }

    public void setNgaySinhString(String ngaySinhString) {
        this.ngaySinhString = ngaySinhString;
    }

    public int getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(int heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
    
}
