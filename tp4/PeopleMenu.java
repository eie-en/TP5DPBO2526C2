import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

    private ArrayList<Person> listPerson;
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
        // inisialisasi listPerson
        listPerson = new ArrayList<>();
        // isi listPerson
        populateList();
        // isi tabel person
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
                String curTahun = table1.getModel().getValueAt(selectedIndex, 3).toString();
                String curRank = table1.getModel().getValueAt(selectedIndex, 4).toString();
                // ubah isi textfield dan combo box
                IdField.setText(curUsername);
                NameField.setText(curNama);
                NumberField.setText(curTahun);
                comboBoxCat.setSelectedItem(curRank);
                // ubah button "Submit" menjadi "Update"
                SubmitButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public void populateList() {
        listPerson.add(new Person("laflagaa", "Azka", 1945, "Gold"));
        listPerson.add(new Person("einoirr", "Agi", 2007, "Immortal"));
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = { "No", "Username", "Nama", "Tahun Lahir", "Rank" };
        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);
        // isi tabel dengan listPerson
        for (int i = 0; i < listPerson.size(); i++) {
            Object[] row = {
                    i + 1,
                    listPerson.get(i).getUsername(),
                    listPerson.get(i).getNama(),
                    listPerson.get(i).getThn(),
                    listPerson.get(i).getRank()
            };
            tmp.addRow(row);
        }
        return tmp;
    }

    public void insertData() {
        try {
            // ambil value dari textfield dan combobox
            String username = IdField.getText();
            String nama = NameField.getText();
            int thn = Integer.parseInt(NumberField.getText());
            String rank = comboBoxCat.getSelectedItem().toString();
            // tambahkan data ke dalam list
            listPerson.add(new Person(username, nama, thn, rank));
            // update tabel
            table1.setModel(setTable());
            // bersihkan form
            clearForm();
            // feedback
            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() {
        try {
            // ambil data dari form
            String username = IdField.getText();
            String nama = NameField.getText();
            int thn = Integer.parseInt(NumberField.getText());
            String rank = comboBoxCat.getSelectedItem().toString();
            // ubah data person di list
            listPerson.get(selectedIndex).setUsername(username);
            listPerson.get(selectedIndex).setNama(nama);
            listPerson.get(selectedIndex).setUmur(thn);
            listPerson.get(selectedIndex).setRank(rank);
            // update tabel
            table1.setModel(setTable());
            // bersihkan form
            clearForm();
            // feedback
            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        // hapus data dari List
        listPerson.remove(selectedIndex);
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
        // ubah button "Update" kembali menjadi "Submit"
        SubmitButton.setText("Submit");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
}