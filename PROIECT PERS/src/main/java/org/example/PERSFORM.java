package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PERSFORM {
    private JPanel panel1;
    private JTextField textFieldNume;
    private JTextField textFieldPrenume;
    private JComboBox<Integer> comboBoxVarsta;
    private JRadioButton radioButtonMasculin;
    private JRadioButton radioButtonFeminin;
    private JRadioButton radioButtonAlteGenuri;
    private JRadioButton radioButtonPreferSaNuSpun;
    private JCheckBox checkBoxRomân;
    private JCheckBox checkBoxMaghiar;
    private JCheckBox checkBoxRrom;
    private JCheckBox checkBoxBulgar;
    private JCheckBox checkBoxUcrainean;
    private JCheckBox checkBoxSârb;
    private JCheckBox checkBoxMoldovean;
    private JCheckBox checkBoxAlteNaționalități;
    private JTextField textFieldAdresa;
    private JTextField textFieldStrada;
    private JComboBox<String> comboBoxJudet;
    private JTextField textFieldOras;
    private JTextField textFieldEmail;
    private JPasswordField passwordFieldParola;
    private JButton buttonAnuleaza;
    private JButton buttonSalveaza;

    private List<Persoana> listaPersoane;
    private ButtonGroup grupButoaneGen;

    public PERSFORM() {
        listaPersoane = new ArrayList<>();
        grupButoaneGen = new ButtonGroup();

        // Adaugă butoanele radio la grupul de butoane
        grupButoaneGen.add(radioButtonMasculin);
        grupButoaneGen.add(radioButtonFeminin);
        grupButoaneGen.add(radioButtonAlteGenuri);
        grupButoaneGen.add(radioButtonPreferSaNuSpun);

        // Inițializează componentele și adaugă ascultători de evenimente
        buttonAnuleaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Închide programul
                System.exit(0);
            }
        });

        buttonSalveaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = textFieldNume.getText();
                String prenume = textFieldPrenume.getText();
                int varsta = (int) comboBoxVarsta.getSelectedItem();
                String gen = getGenSelectat();
                List<String> nationalitati = getNationalitatiSelectate();
                String adresa = textFieldAdresa.getText();
                String strada = textFieldStrada.getText();
                String judet = (String) comboBoxJudet.getSelectedItem();
                String oras = textFieldOras.getText();
                String email = textFieldEmail.getText();
                char[] parola = passwordFieldParola.getPassword();

                // Creează un obiect Persoana
                Persoana persoana = new Persoana(nume, prenume, varsta, gen, nationalitati, adresa, strada, judet, oras, email, parola);

                // Adaugă persoana în listă
                listaPersoane.add(persoana);

                // Salvează datele într-un fișier JSON
                salveazaDateJSON();

                // Afișează informațiile persoanei adăugate
                afiseazaInformatiiPersoana(nume, prenume, varsta, gen, nationalitati);

                // Curăță formularul
                curataFormularul();
            }
        });
    }

    private List<String> getNationalitatiSelectate() {
        List<String> nationalitatiSelectate = new ArrayList<>();

        // Verifică fiecare casetă de selectare a naționalității și adaugă cele selectate în listă
        if (checkBoxRomân.isSelected()) nationalitatiSelectate.add("Român");
        if (checkBoxMaghiar.isSelected()) nationalitatiSelectate.add("Maghiar");
        if (checkBoxRrom.isSelected()) nationalitatiSelectate.add("Rrom");
        if (checkBoxBulgar.isSelected()) nationalitatiSelectate.add("Bulgar");
        if (checkBoxUcrainean.isSelected()) nationalitatiSelectate.add("Ucrainean");
        if (checkBoxSârb.isSelected()) nationalitatiSelectate.add("Sârb");
        if (checkBoxMoldovean.isSelected()) nationalitatiSelectate.add("Moldovean");
        if (checkBoxAlteNaționalități.isSelected()) nationalitatiSelectate.add("Alte naționalități");

        return nationalitatiSelectate;
    }

    private String getGenSelectat() {
        if (radioButtonMasculin.isSelected()) {
            return "Masculin";
        } else if (radioButtonFeminin.isSelected()) {
            return "Feminin";
        } else if (radioButtonAlteGenuri.isSelected()) {
            return "Alte genuri";
        } else if (radioButtonPreferSaNuSpun.isSelected()) {
            return "Prefer să nu spun";
        } else {
            return "Necunoscut";
        }
    }

    private void curataFormularul() {
        // Curăță câmpurile text
        textFieldNume.setText("");
        textFieldPrenume.setText("");
        textFieldAdresa.setText("");
        textFieldStrada.setText("");
        textFieldOras.setText("");
        textFieldEmail.setText("");
        passwordFieldParola.setText("");

        // Deselectează butoanele radio din grupul de gen
        grupButoaneGen.clearSelection();

        // Deselectează casetele de selectare a naționalității
        checkBoxRomân.setSelected(false);
        checkBoxMaghiar.setSelected(false);
        checkBoxRrom.setSelected(false);
        checkBoxBulgar.setSelected(false);
        checkBoxUcrainean.setSelected(false);
        checkBoxSârb.setSelected(false);
        checkBoxMoldovean.setSelected(false);
        checkBoxAlteNaționalități.setSelected(false);
    }

    private void afiseazaInformatiiPersoana(String nume, String prenume, int varsta, String gen, List<String> nationalitati) {
        StringBuilder nationalitatiString = new StringBuilder();
        for (String nationalitate : nationalitati) {
            nationalitatiString.append(nationalitate).append(", ");
        }
        if (nationalitatiString.length() > 0) {
            nationalitatiString.setLength(nationalitatiString.length() - 2); // Elimină ultima virgulă și spațiul
        }

        JOptionPane.showMessageDialog(null, "Nume: " + nume + "\nPrenume: " + prenume + "\nVarsta: " + varsta +
                        "\nGen: " + gen + "\nNaționalități: " + nationalitatiString.toString(),
                "Informații Persoană", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salveazaDateJSON() {
        try (FileWriter writer = new FileWriter("date_persoane.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(listaPersoane, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Formular Persoană");
                frame.setContentPane(new PERSFORM().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    private class Persoana {
        private String nume;
        private String prenume;
        private int varsta;
        private String gen;
        private List<String> nationalitati;
        private String adresa;
        private String strada;
        private String judet;
        private String oras;
        private String email;
        private char[] parola;

        public Persoana(String nume, String prenume, int varsta, String gen, List<String> nationalitati,
                        String adresa, String strada, String judet, String oras, String email, char[] parola) {
            this.nume = nume;
            this.prenume = prenume;
            this.varsta = varsta;
            this.gen = gen;
            this.nationalitati = nationalitati;
            this.adresa = adresa;
            this.strada = strada;
            this.judet = judet;
            this.oras = oras;
            this.email = email;
            this.parola = parola;
        }
    }
}
