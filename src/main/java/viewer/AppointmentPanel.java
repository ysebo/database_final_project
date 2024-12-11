package viewer;

import controller.AppointmentController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppointmentPanel extends JPanel {
    private JLabel doctorIdLabel;
    private JLabel appointmentDateLabel;
    private JLabel reasonLabel;
    private JTextField doctorIdField;
    private JTextField appointmentDateField;
    private JTextField reasonField;
    private JButton confirmButton;
    private JButton backButton;
    private AppointmentController appointmentController;
    public AppointmentPanel(Viewer viewer , LoginPanel loginPanel) {
        removeAll();
        setLayout(null);
        appointmentController = new AppointmentController(viewer , this , loginPanel);
        doctorIdLabel = new JLabel("Doctor ID:");
        doctorIdLabel.setBounds(300, 250, 100, 25);
        doctorIdField = new JTextField();
        doctorIdField.setBounds(450, 250, 200, 25);

        appointmentDateLabel = new JLabel("Appointment Date:");
        appointmentDateLabel.setBounds(300, 300, 150, 25);
        appointmentDateField = new JTextField();
        appointmentDateField.setBounds(450, 300, 200, 25);

        reasonLabel = new JLabel("Reason:");
        reasonLabel.setBounds(300, 350, 100, 25);
        reasonField = new JTextField();
        reasonField.setBounds(450, 350, 200, 25);

        backButton = new JButton("Back");
        backButton.setBounds(62, 700, 100, 30);


        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(587, 400, 100, 30);
        add(doctorIdLabel);
        add(doctorIdField);
        add(appointmentDateLabel);
        add(appointmentDateField);
        add(reasonLabel);
        add(reasonField);
        add(confirmButton);
        add(backButton);
        backButton.setActionCommand("Back");
        backButton.addActionListener(appointmentController);
        confirmButton.setActionCommand("Confirm");
        confirmButton.addActionListener(appointmentController);

    }
    public void clearAllFields() {
        doctorIdField.setText("");
        appointmentDateField.setText("");
        reasonField.setText("");
    }
    public String getDoctorId() {
        return doctorIdField.getText().trim();
    }
    public String getAppointmentDate() {
        return appointmentDateField.getText().trim();
    }
    public String getReason() {
        return reasonField.getText().trim();
    }
    public void showAppointmentDialog() {
        JOptionPane.showMessageDialog(this, "Appointment scheduled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

}
