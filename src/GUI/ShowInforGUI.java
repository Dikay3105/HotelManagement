package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author ANH KHOA
 */
public class ShowInforGUI {

    private JFrame frBackground = new JFrame();
    private JPanel pnContainer = new JPanel();
    private JLabel lbTitle = new JLabel("THÔNG TIN PHÒNG");
    private JPanel pnContent = new JPanel();
    private JLabel lbMaP = new JLabel("Mã phòng:");
    private JLabel lbTenP = new JLabel("Tên phòng:");
    private JLabel lbLoaiP = new JLabel("Loại phòng:");
    private JLabel lbGiaP = new JLabel("Giá phòng:");
    private JLabel lbTinhTrangP = new JLabel("Tình trạng:");
    private JLabel lbHienTrangP = new JLabel("Hiện trạng:");

    private JLabel txtMaP, txtTenP, cbLoaiP, txtGiaP, txtTinhTrangP, cbHienTrangP;

    private Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    private Font sgUI15b = new Font("Segoe UI", Font.BOLD, 15);
    private Font sgUI15 = new Font("Segoe UI", Font.PLAIN, 15);
    private Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);

    public ShowInforGUI(String maP, String tenP, String loaiP, String GiaP, String tinhTrang, String hienTrang) {
        initComponents(maP, tenP, loaiP, GiaP, tinhTrang, hienTrang);
    }

    public void initComponents(String maP, String tenP, String loaiP, String GiaP, String tinhTrang, String hienTrang) {
        frBackground.setVisible(true);
        frBackground.setSize(350, 600);
        frBackground.setLocationRelativeTo(null);
        frBackground.setLayout(new BorderLayout());
        frBackground.setResizable(false);

        pnContainer.setLayout(new BorderLayout(1, 1));
        pnContainer.setPreferredSize(new Dimension(350, 600));

        lbTitle.setPreferredSize(new Dimension(frBackground.getWidth(), 50));
        lbTitle.setOpaque(true);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setFont(sgUI15b);

        if (tinhTrang.equals("Trống")) {
            lbTitle.setBackground(Color.decode("#90EE90"));
            pnContent.setBackground(Color.decode("#F0FFF0"));
            pnContainer.setBorder(new MatteBorder(7, 7, 7, 7, Color.decode("#90EE90")));

        } else if (tinhTrang.equals("Đang được thuê")) {
            lbTitle.setBackground(Color.decode("#E54646"));
            pnContent.setBackground(Color.decode("#FFE4E1"));
            pnContainer.setBorder(new MatteBorder(7, 7, 7, 7, Color.decode("#E54646")));
        } else {
            lbTitle.setBackground(Color.decode("#FEF889"));
            pnContent.setBackground(Color.decode("#FFFFF0"));
            pnContainer.setBorder(new MatteBorder(7, 7, 7, 7, Color.decode("#FEF889")));
        }

        pnContent.setLayout(null);

        lbMaP.setBounds(60, 50, 150, 40);
        lbMaP.setFont(sgUI15b);
        txtMaP = new JLabel(maP);
        txtMaP.setBounds(160, 50, 230, 40);
        txtMaP.setFont(sgUI15);

        lbTenP.setBounds(60, 120, 100, 40);
        lbTenP.setFont(sgUI15b);
        txtTenP = new JLabel(tenP);
        txtTenP.setBounds(160, 120, 230, 40);
        txtTenP.setFont(sgUI15);

        lbLoaiP.setBounds(60, 190, 100, 40);
        lbLoaiP.setFont(sgUI15b);
        cbLoaiP = new JLabel(loaiP);
        cbLoaiP.setBounds(160, 190, 230, 40);
        cbLoaiP.setFont(sgUI15);

        lbGiaP.setBounds(60, 260, 100, 40);
        lbGiaP.setFont(sgUI15b);
        txtGiaP = new JLabel(GiaP);
        txtGiaP.setBounds(160, 260, 230, 40);
        txtGiaP.setFont(sgUI15);

        lbTinhTrangP.setBounds(60, 330, 100, 40);
        lbTinhTrangP.setFont(sgUI15b);
        txtTinhTrangP = new JLabel(tinhTrang);
        txtTinhTrangP.setBounds(160, 330, 230, 40);
        txtTinhTrangP.setFont(sgUI15);

        lbHienTrangP.setBounds(60, 400, 100, 40);
        lbHienTrangP.setFont(sgUI15b);
        cbHienTrangP = new JLabel(hienTrang);
        cbHienTrangP.setBounds(160, 400, 230, 40);
        cbHienTrangP.setFont(sgUI15);

        pnContent.add(lbMaP);
        pnContent.add(lbTenP);
        pnContent.add(lbLoaiP);
        pnContent.add(lbGiaP);
        pnContent.add(lbTinhTrangP);
        pnContent.add(lbHienTrangP);
        pnContent.add(txtMaP);
        pnContent.add(txtTenP);
        pnContent.add(cbLoaiP);
        pnContent.add(txtGiaP);
        pnContent.add(txtTinhTrangP);
        pnContent.add(cbHienTrangP);

        pnContainer.add(lbTitle, BorderLayout.NORTH);
        pnContainer.add(pnContent, BorderLayout.CENTER);
        frBackground.add(pnContainer, BorderLayout.CENTER);
    }
}
