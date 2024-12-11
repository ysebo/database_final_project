package viewer;

import controller.DoctorController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class DoctorPanel extends JPanel {
    private JButton viewPatientsButton;
    private JButton searchPatientButton;
    private JButton manageAppointmentsButton;
    private JTextField searchField;
    private JTable patientTable;
    private JPanel topPanel, centerPanel;
    private JScrollPane scrollPane;
    private DoctorController doctorController;
    public DoctorPanel(Viewer viewer , LoginPanel loginPanel) {
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        doctorController = new DoctorController(viewer , this , loginPanel);
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
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        patientTable = new JTable();
        scrollPane = new JScrollPane(patientTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        searchPatientButton.setActionCommand("Search");
        searchPatientButton.addActionListener(doctorController);
        viewPatientsButton.addActionListener(doctorController);
        viewPatientsButton.setActionCommand("View_Patients");
        manageAppointmentsButton.addActionListener(doctorController);
        manageAppointmentsButton.setActionCommand("Manage_Appointments");
    }
    public String getSearchQuery() {
        return searchField.getText().trim();
    }

    public void setPatientTableData(Object[][] data, String[] columnNames) {
        patientTable.setModel(new DefaultTableModel(data, columnNames));
    }
}
