package viewer;

import controller.PatientController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class PatientPanel extends JPanel {
    private JButton viewInfoButton;
    private JButton viewAppointmentsButton;
    private JButton bookAppointmentButton;
    private JTable appointmentsTable;
    private PatientController patientController;
    private JLabel doctorIdLabel;
    private JLabel appointmentDateLabel;
    private JLabel reasonLabel;
    private JTextField doctorIdField;
    private JTextField appointmentDateField;
    private JTextField reasonField;

    private JButton confirmButton;
    public PatientPanel(Viewer viewer , LoginPanel loginPanel) {
        setLayout(new BorderLayout());
        patientController = new PatientController(viewer , this, loginPanel);
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
    public void showAppointmentBookingFields() {
//        JPanel bookingPanel = new JPanel();
//        bookingPanel.setLayout(new GridLayout(4, 2, 10, 10));
//        JLabel doctorIdLabel = new JLabel("Doctor ID:");
//        appointmentDateLabel = new JLabel("Appointment Date (yyyy-MM-dd):");
//        reasonLabel = new JLabel("Reason:");
//
//        doctorIdField = new JTextField();
//        appointmentDateField = new JTextField();
//        reasonField = new JTextField();
//
//        confirmButton = new JButton("Confirm");
//        bookingPanel.add(doctorIdLabel);
//        bookingPanel.add(doctorIdField);
//        bookingPanel.add(appointmentDateLabel);
//        bookingPanel.add(appointmentDateField);
//        bookingPanel.add(reasonLabel);
//        bookingPanel.add(reasonField);
//        bookingPanel.add(new JLabel());
//        bookingPanel.add(confirmButton);
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(viewInfoButton);
//        buttonPanel.add(viewAppointmentsButton);
//        buttonPanel.add(bookAppointmentButton);
//        add(buttonPanel, BorderLayout.NORTH);
//
//        add(bookingPanel, BorderLayout.CENTER);
//        revalidate();
//        repaint();
    }
}
