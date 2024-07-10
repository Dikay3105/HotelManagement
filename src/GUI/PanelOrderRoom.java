package GUI;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PanelOrderRoom extends JPanel {

    Font sgUI18 = new Font("Segoe UI", Font.BOLD, 15);

    public PanelOrderRoom() {
        setLayout(new BorderLayout());
        add(new InforPane());
    }

    public class InforPane extends JPanel {

        public InforPane() {
            setBorder(new EmptyBorder(8, 8, 8, 8));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 2;
            TitlePane title = new TitlePane();
            add(title, gbc);

            gbc.gridwidth = 1;
            gbc.gridy++;
            gbc.weighty = 2;
            gbc.weightx = 2;

            KHPane namePane = new KHPane();
            namePane.setBorder(new CompoundBorder(new TitledBorder("Chi tiết khách hàng"), new EmptyBorder(4, 4, 4, 4)));
            add(namePane, gbc);

            gbc.gridx++;
            gbc.weightx = 1;

            RoomPane emailPane = new RoomPane();
            emailPane.setBorder(new CompoundBorder(new TitledBorder("Chi tiết phòng thuê"), new EmptyBorder(4, 4, 4, 4)));
            add(emailPane, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;

            PnDVPane Pane = new PnDVPane();
            Pane.setBorder(new CompoundBorder(new TitledBorder("Chi tiết phòng và dịch vụ"), new EmptyBorder(4, 4, 4, 4)));
            add(Pane, gbc);

            gbc.gridy++;
            AddDVPane Pane1 = new AddDVPane();
            Pane1.setBorder(new CompoundBorder(new TitledBorder("Thêm dịch vụ cho phòng"), new EmptyBorder(4, 4, 4, 4)));
            add(Pane1, gbc);
        }

    }

    public class TitlePane extends JPanel {

        public TitlePane() {

            JLabel lblTitle = new JLabel("Đặt trả phòng");
            lblTitle.setFont(sgUI18);
            lblTitle.setForeground(Color.red);
            add(lblTitle);
        }

    }

    public class KHPane extends JPanel {

        public KHPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(10, 10, 10, 5);
            add(new JLabel("Ngày check in:"), gbc);

            gbc.gridy++;
            add(new JLabel("Ngày check out:"), gbc);

            gbc.gridy++;
            add(new JLabel("Tên khách hàng:"), gbc);

            gbc.gridy++;
            add(new JLabel("CMND:"), gbc);

            gbc.gridy++;
            add(new JLabel("SĐT:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 2;
            gbc.gridwidth = 2;

            JDateChooser dcsDayDV = new JDateChooser();
            dcsDayDV.setDateFormatString("yyyy-MM-dd");

            add(dcsDayDV, gbc);

            JDateChooser dcsDayDV1 = new JDateChooser();
            dcsDayDV1.setDateFormatString("yyyy-MM-dd");

            gbc.gridy++;
            add(dcsDayDV1, gbc);

            gbc.gridy++;
            add(new JTextField(20), gbc);

            gbc.gridy++;
            add(new JTextField(20), gbc);

            gbc.gridy++;
            add(new JTextField(20), gbc);

            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.weightx = 1;
            gbc.gridwidth = 1;

            add(new JLabel("Giới tính:"), gbc);

            gbc.gridy++;
            add(new JLabel("Phí phụ thu:"), gbc);

            gbc.gridy++;
            add(new JLabel("Giảm giá:"), gbc);

            gbc.gridy++;
            add(new JLabel("Thanh toán:"), gbc);

            //------------------//
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            //gbc.weightx = 0.5;

            String gioitinh[] = {"Nam", "Nữ"};
            JComboBox cbGioiTinh = new JComboBox(gioitinh);

            add(cbGioiTinh, gbc);

            gbc.gridy++;
            add(new JTextField(10), gbc);

            gbc.gridy++;
            add(new JTextField(10), gbc);

            String PTTT[] = {"Tiền mặt", "Momo"};
            JComboBox cbPTTT = new JComboBox(PTTT);

            gbc.gridy++;
            add(cbPTTT, gbc);

        }
    }

    protected class RoomPane extends JPanel {

        public RoomPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(10, 10, 10, 5);
            add(new JLabel("Hình thức thuê:"), gbc);

            gbc.gridy++;
            add(new JLabel("Trả trước:"), gbc);

            gbc.gridy++;
            add(new JLabel("Số phòng đã thuê:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;

            JRadioButton btnThueNgay = new JRadioButton("Thuê theo ngày");
            JRadioButton btnThueGio = new JRadioButton("Thuê theo giờ");
            ButtonGroup group = new ButtonGroup();
            group.add(btnThueNgay);
            group.add(btnThueGio);

            add(btnThueNgay, gbc);

            gbc.weightx = 0.5;
            gbc.gridx++;
            add(btnThueGio, gbc);

            gbc.gridx = 1;
            gbc.gridy++;
            gbc.weightx = 1; // sửa giá trị weightx
            gbc.fill = GridBagConstraints.BOTH;
            add(new JTextField("0", 10), gbc);

            gbc.gridy++;
            add(new JTextField("0", 3), gbc);

            gbc.weightx = 3;
            gbc.gridwidth = 3;
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.weighty = 3;
            DefaultTableModel dtm = new DefaultTableModel();
            JTable table = new JTable();

            dtm.addColumn("Tầng");
            dtm.addColumn("Số phòng");
            dtm.addColumn("Loại phòng");
            dtm.addColumn("Số giường");
            table.setPreferredScrollableViewportSize(new Dimension(500, 5 * table.getRowHeight()));
            table.setModel(dtm);
            int i = 1;
            for (; i < 5; i++) {
                Object[] data = {"a", "a", "a", "a"};
                dtm.addRow(data);
            }

            add(new JScrollPane(table), gbc);

        }

    }

    public class PnDVPane extends JPanel {

        public PnDVPane() {
            setLayout(new BorderLayout());
            DefaultTableModel dtm = new DefaultTableModel();
            JTable table = new JTable();

            dtm.addColumn("STT");
            dtm.addColumn("Mã phòng");
            dtm.addColumn("Tên phòng");
            dtm.addColumn("Loại phòng");
            dtm.addColumn("Giá phòng");
            dtm.addColumn("Tình trạng");
            dtm.addColumn("Hiện trạng");
            table.setPreferredScrollableViewportSize(new Dimension(500, 5 * table.getRowHeight()));
            table.setModel(dtm);
            int i = 1;
            for (; i < 5; i++) {
                Object[] data = {"a", "a", "a", "a", "a", "a", "a"};
                dtm.addRow(data);

            }

            JButton btnDelDV = new JButton("Xóa dịch vụ");

            btnDelDV.setPreferredSize(new Dimension(150, 40));
            btnDelDV.setMaximumSize(new Dimension(150, 40));
            ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/xoa.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            btnDelDV.setIcon(iconAdd);
            btnDelDV.setFont(sgUI18);

            JPanel pnButton = new JPanel();
            pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
            pnButton.add(btnDelDV);
            add(new JScrollPane(table), BorderLayout.CENTER);
            add(pnButton, BorderLayout.SOUTH);

        }

    }

    public class AddDVPane extends JPanel {

        public AddDVPane() {

            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;

            add(new InforRoom(), gbc);

            //JPanel aJPanel=new JPanel();
            //aJPanel.setBackground(Color.red);
            //aJPanel.setBorder(new CompoundBorder(new TitledBorder("Chọn dịch vụ"), new EmptyBorder(4, 4, 4, 4)));
            gbc.gridx++;
            add(new ChooseDV(), gbc);

        }

    }

    public class InforRoom extends JPanel {

        public InforRoom() {
            DefaultTableModel dtm = new DefaultTableModel();
            JTable table = new JTable();

            dtm.addColumn("Tầng");
            dtm.addColumn("Số phòng");
            dtm.addColumn("Loại phòng");
            dtm.addColumn("Số giường");
            dtm.addColumn("Giá ngày");
            dtm.addColumn("Giá giờ");
            table.setPreferredScrollableViewportSize(new Dimension(500, 5 * table.getRowHeight()));
            table.setModel(dtm);
            int i = 1;
            for (; i < 10; i++) {
                Object[] data = {"a", "a", "a", "a", "a", "a", "a"};
                dtm.addRow(data);

            }

            setLayout(new BorderLayout());
            setBorder(new CompoundBorder(new TitledBorder("Thông tin phòng được chọn"), new EmptyBorder(4, 4, 4, 4)));
            add(new JScrollPane(table), BorderLayout.CENTER);
        }

    }

    public class ChooseDV extends JPanel {

        public ChooseDV() {
            setLayout(new GridBagLayout());
            setBorder(new CompoundBorder(new TitledBorder("Chọn dịch vụ"), new EmptyBorder(4, 4, 4, 4)));
            String country[] = {"India", "Aus", "U.S.A", "England", "Newzealand"};
            JComboBox cbDV = new JComboBox(country);

            JButton btnAdd = new JButton("Thêm dịch vụ");
            btnAdd.setPreferredSize(new Dimension(150, 40));
            btnAdd.setMaximumSize(new Dimension(150, 40));
            ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/them.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            btnAdd.setIcon(iconAdd);
            //btnAdd.setFont(sgUI18);

            JDateChooser dcsDayDV = new JDateChooser();
            LocalDate dateDefault = java.time.LocalDate.now();
            Date date = java.sql.Date.valueOf(dateDefault);
            dcsDayDV.setDate(date);
            dcsDayDV.setDateFormatString("yyyy-MM-dd");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 2;
            gbc.weightx = 2;
            add(cbDV, gbc);

            gbc.gridx += 2;
            add(btnAdd, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.weightx = 1;
            add(new JLabel("Thời gian:"), gbc);

            gbc.gridx++;
            add(dcsDayDV, gbc);

            gbc.gridx++;
            gbc.gridwidth = 1;
            gbc.weightx = 1;
            JLabel timeLabel = new JLabel();
            timeLabel.setSize(100, 40);
            timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            // Cập nhật thời gian mỗi giây bằng cách sử dụng Timer
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                    String time = sdf.format(new Date());
                    timeLabel.setText(time);
                }
            });
            timer.start();
            add(timeLabel, gbc);

        }

    }

}
