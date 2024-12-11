package viewer;

import controller.PatientController;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PatientPanel extends JPanel {
    private JButton viewInfoButton;
    private JButton viewAppointmentsButton;
    private JButton bookAppointmentButton;
    private JTable appointmentsTable;
    private PatientController patientController;

    public PatientPanel(Viewer viewer, LoginPanel loginPanel) {
        setLayout(new BorderLayout());
        patientController = new PatientController(viewer, this, loginPanel);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        viewInfoButton = new JButton("View My Information");
        viewAppointmentsButton = new JButton("View Appointments");
        bookAppointmentButton = new JButton("Book Appointment");

        topPanel.add(viewInfoButton);
        topPanel.add(viewAppointmentsButton);
        topPanel.add(bookAppointmentButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        appointmentsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(appointmentsTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);
        viewInfoButton.setActionCommand("My_Info");
        viewInfoButton.addActionListener(patientController);
        viewAppointmentsButton.setActionCommand("View_Appointments");
        viewAppointmentsButton.addActionListener(patientController);
        bookAppointmentButton.addActionListener(patientController);
        bookAppointmentButton.setActionCommand("Book_Appointment");
    }

    public JButton getViewInfoButton() {
        return viewInfoButton;
    }

    public JButton getViewAppointmentsButton() {
        return viewAppointmentsButton;
    }

    public JButton getBookAppointmentButton() {
        return bookAppointmentButton;
    }

    public void setAppointmentsTableData(Object[][] data, String[] columnNames) {
        appointmentsTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
