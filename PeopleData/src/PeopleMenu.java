import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMenu extends JFrame {
    public JPanel mainPanel;
    private JTextField IdField;
    private JTextField NameField;
    private JTextField NumberField;
    private JButton SubmitButton;
    private JButton Cancel;
    private JButton deleteButton;
    private JTable table1;
    private JLabel titleLabel;
    private JComboBox<String> comboBoxCat;

    private Database database;
    private int selectedIndex = -1;

    public static void main(String[] args) {
        // buat object window
        PeopleMenu menu = new PeopleMenu();
        // atur ukuran window
        menu.setSize(700, 600);
        // Letakkan window di tengah layar
        menu.setLocationRelativeTo(null);
        // isi window
        menu.setContentPane(menu.mainPanel);
        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);
        // tampilkan window
        menu.setVisible(true);
        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // constructor
    public PeopleMenu() {
        database = new Database();

        // isi tabel person dari database
        table1.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        titleLabel.setText("People Menu");

        // atur isi combo box dengan Rank Valorant
        String[] rankData = { "Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ascendant", "Immortal", "Radiant" };
        comboBoxCat.setModel(new DefaultComboBoxModel<>(rankData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });

        // saat tombol cancel ditekan
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // saat salah satu baris tabel ditekan
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = table1.getSelectedRow();

                // simpan value textfield dan combo box
                String curUsername = table1.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = table1.getModel().getValueAt(selectedIndex, 2).toString();
                String curUmur = table1.getModel().getValueAt(selectedIndex, 3).toString();
                String curRank = table1.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                IdField.setText(curUsername);
                NameField.setText(curNama);
                NumberField.setText(curUmur);
                comboBoxCat.setSelectedItem(curRank);

                // Kunci username agar tidak bisa diubah saat mode update
                IdField.setEditable(false);

                // ubah button "Submit" menjadi "Update"
                SubmitButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = { "No", "Username", "Nama", "Umur", "Rank" };
        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);
        try {
            // ambil data dari database
            ResultSet resultSet = database.selectQuery("SELECT * FROM person");
            int i = 0;
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = i + 1;
                row[1] = resultSet.getString("username");
                row[2] = resultSet.getString("name");
                row[3] = resultSet.getInt("umur");
                row[4] = resultSet.getString("kategori");
                tmp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }

    public void insertData() {
        try {
            // ambil value dari textfield dan combobox
            String username = IdField.getText().trim();
            String nama = NameField.getText().trim();
            String umurStr = NumberField.getText().trim();
            String rank = comboBoxCat.getSelectedItem().toString();

            // validasi kolom kosong
            if (username.isEmpty() || nama.isEmpty() || umurStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua kolom input tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // validasi duplikasi username
            ResultSet checkRs = database.selectQuery("SELECT * FROM person WHERE username = '" + username + "'");
            if (checkRs.next()) {
                JOptionPane.showMessageDialog(null, "Username '" + username + "' sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int umur = Integer.parseInt(umurStr);

            // tambahkan data ke dalam database
            String sqlQuery = "INSERT INTO person VALUES ('" + username + "', '" + nama + "', " + umur + ", '" + rank + "')";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            table1.setModel(setTable());
            // bersihkan form
            clearForm();
            // feedback
            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Umur harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateData() {
        try {
            // ambil data dari form
            String username = IdField.getText().trim();
            String nama = NameField.getText().trim();
            String umurStr = NumberField.getText().trim();
            String rank = comboBoxCat.getSelectedItem().toString();

            // validasi kolom kosong
            if (username.isEmpty() || nama.isEmpty() || umurStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua kolom input tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int umur = Integer.parseInt(umurStr);

            // ubah data person di database
            String sqlQuery = "UPDATE person SET name = '" + nama + "', umur = " + umur + ", kategori = '" + rank + "' WHERE username = '" + username + "'";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            table1.setModel(setTable());
            // bersihkan form
            clearForm();
            // feedback
            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Umur harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        // hapus data dari database
        String username = IdField.getText().trim();
        String sqlQuery = "DELETE FROM person WHERE username = '" + username + "'";
        database.insertUpdateDeleteQuery(sqlQuery);

        // update tabel
        table1.setModel(setTable());
        // bersihkan form
        clearForm();
        // feedback
        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }

    public void clearForm() {
        // kosongkan semua textfield atau combo box
        IdField.setText("");
        NameField.setText("");
        NumberField.setText("");
        comboBoxCat.setSelectedIndex(0);

        // buka kembali akses edit username
        IdField.setEditable(true);

        // ubah button "Update" kembali menjadi "Submit"
        SubmitButton.setText("Submit");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
}