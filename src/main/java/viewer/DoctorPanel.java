package viewer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class DoctorPanel extends JPanel {
    private JButton viewPatientsButton;
    private JButton searchPatientButton;
    private JButton manageAppointmentsButton;
    private JTextField searchField;
    private JTable patientTable;
    public DoctorPanel() {
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchPatientButton = new JButton("Search");
        viewPatientsButton = new JButton("View All Patients");
        manageAppointmentsButton = new JButton("Manage Appointments");
        topPanel.add(new JLabel("Search Patient:"));
        topPanel.add(searchField);
        topPanel.add(searchPatientButton);
        topPanel.add(viewPatientsButton);
        topPanel.add(manageAppointmentsButton);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        patientTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(patientTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
    public JButton getViewPatientsButton() {
        return viewPatientsButton;
    }

    public JButton getSearchPatientButton() {
        return searchPatientButton;
    }

    public JButton getManageAppointmentsButton() {
        return manageAppointmentsButton;
    }

    public String getSearchQuery() {
        return searchField.getText();
    }

    public void setPatientTableData(Object[][] data, String[] columnNames) {
        patientTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
