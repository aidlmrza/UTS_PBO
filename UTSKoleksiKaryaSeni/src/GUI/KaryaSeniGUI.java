package GUI;

import User.User;
import KaryaSeni.KaryaSeni;
import KaryaSeni.KaryaSeniIlustrasi;
import KaryaSeni.KaryaSeniGif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class KaryaSeniGUI extends JFrame {
    private User user;

    public KaryaSeniGUI(User user) {
        this.user = user;
        setTitle("Karya Seni Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Tambah Karya Seni", createAddPanel());
        tabbedPane.addTab("Daftar Karya Seni", createListPanel());
        
        add(tabbedPane);
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner tahunSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(tahunSpinner, "yyyy");
        tahunSpinner.setEditor(editor);
        tahunSpinner.setValue(new java.util.Date());
        
        JTextField judulField = new JTextField(15);
        JTextField artisField = new JTextField(15);
        JTextField tahunField = new JTextField(15);
        JTextField durasiField = new JTextField(15);
        JTextField teknikField = new JTextField(15);
        JButton uploadButton = new JButton("Upload Gambar");
        JLabel gambarLabel = new JLabel("Gambar Path: ");
        JComboBox<String> jenisKaryaComboBox = new JComboBox<>(new String[]{"Pilih Jenis Karya", "Ilustrasi", "GIF"});
        
        
        JLabel teknikLabel = new JLabel("Teknik:");
        JLabel durasiLabel = new JLabel("Durasi:");
        teknikField.setVisible(false);
        durasiField.setVisible(false);
        teknikLabel.setVisible(false);
        durasiLabel.setVisible(false);

        jenisKaryaComboBox.addActionListener(e -> {
            String selected = (String) jenisKaryaComboBox.getSelectedItem();
            if ("Ilustrasi".equals(selected)) {
                teknikField.setVisible(true);
                teknikLabel.setVisible(true);
                durasiField.setVisible(false);
                durasiLabel.setVisible(false);
            } else if ("GIF".equals(selected)) {
                teknikField.setVisible(false);
                teknikLabel.setVisible(false);
                durasiField.setVisible(true);
                durasiLabel.setVisible(true);
            } else {
                teknikField.setVisible(false);
                durasiField.setVisible(false);
                teknikLabel.setVisible(false);
                durasiLabel.setVisible(false);
            }
            panel.revalidate(); 
            panel.repaint();
        });

        JButton addButton = new JButton("Tambah Karya Seni");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String judul = judulField.getText();
                String artis = artisField.getText();
                String tahun = new SimpleDateFormat("yyyy").format(tahunSpinner.getValue());
                String gambarPath = gambarLabel.getText();

                if (gambarPath.isEmpty() || judul.isEmpty() || artis.isEmpty() || tahun.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Semua field harus diisi!");
                    return;
                }

                boolean isGif = "GIF".equals(jenisKaryaComboBox.getSelectedItem());
                String teknik = null;
                String durasi = null;

                if (isGif) {
                    durasi = durasiField.getText();
                } else {
                    teknik = teknikField.getText();
                }

                KaryaSeni karya;
                if (isGif) {
                    karya = new KaryaSeniGif(judul, artis, tahun, durasi, gambarPath);
                } else {
                    karya = new KaryaSeniIlustrasi(judul, artis, tahun, teknik, gambarPath);
                }

                user.createKaryaSeni(karya); 
                JOptionPane.showMessageDialog(panel, "Karya seni berhasil ditambahkan!");
                clearFields(judulField, artisField, tahunField, teknikField, durasiField, gambarLabel);
            }
        });

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(panel);
            if (result == JFileChooser.APPROVE_OPTION) {
                gambarLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Jenis Karya:"), gbc);
        gbc.gridx = 1;
        panel.add(jenisKaryaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Judul:"), gbc);
        gbc.gridx = 1;
        panel.add(judulField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Artis:"), gbc);
        gbc.gridx = 1;
        panel.add(artisField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Tahun:"), gbc);
        gbc.gridx = 1;
        panel.add(tahunSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(teknikLabel, gbc);
        gbc.gridx = 1;
        panel.add(teknikField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(durasiLabel, gbc);
        gbc.gridx = 1;
        panel.add(durasiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(uploadButton, gbc);
        gbc.gridx = 1;
        panel.add(gambarLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2; 
        panel.add(addButton, gbc);

        return panel;
    }

    private void clearFields(JTextField judulField, JTextField artisField, JTextField tahunField,
                             JTextField teknikField, JTextField durasiField, JLabel gambarLabel) {
        judulField.setText("");
        artisField.setText("");
        tahunField.setText("");
        teknikField.setText("");
        durasiField.setText("");
        gambarLabel.setText("Gambar Path: ");
    }

    private JPanel createListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JButton showButton = new JButton("Tampilkan Karya Seni");

        showButton.addActionListener(e -> {
            textArea.setText(""); 
            for (KaryaSeni karya : user.getKoleksi()) {
                textArea.append("Judul: " + karya.getJudul() + "\n");
                textArea.append("Artis: " + karya.getArtis() + "\n");
                textArea.append("Tahun: " + karya.getTahun() + "\n");
                if (karya instanceof KaryaSeniIlustrasi) {
                    KaryaSeniIlustrasi ilustrasi = (KaryaSeniIlustrasi) karya;
                    textArea.append("Teknik: " + ilustrasi.getTeknik() + "\n");
                } else if (karya instanceof KaryaSeniGif) {
                    KaryaSeniGif gif = (KaryaSeniGif) karya;
                    textArea.append("Durasi: " + gif.getDurasi() + "\n");
                }
                textArea.append("Path Gambar: " + karya.getGambarPath() + "\n");
                textArea.append("\n");
            }
        });

        panel.add(showButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton exitButton = new JButton("Keluar");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton, BorderLayout.SOUTH);

        return panel;
    }
}
