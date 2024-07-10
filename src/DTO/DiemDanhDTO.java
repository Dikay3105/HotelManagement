package DTO;

public class DiemDanhDTO {

    private String ngayDiLam;
    private String maNV;

    public DiemDanhDTO() {
    }

    public DiemDanhDTO(String ngayDiLam, String maNV) {
        this.ngayDiLam = ngayDiLam;
        this.maNV = maNV;
    }

    public String getNgayDiLam() {
        return ngayDiLam;
    }

    public void setNgayDiLam(String ngayDiLam) {
        this.ngayDiLam = ngayDiLam;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

}
