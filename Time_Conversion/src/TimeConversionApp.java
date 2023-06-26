import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimeConversionApp extends JFrame {
    private final String[] availableTimeZones;
    private final JComboBox<String> timeZoneComboBox;
    private final JLabel convertedTimeLabel;

    public TimeConversionApp() {
        // Set up the main frame
        setTitle("Time Conversion by Country");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Retrieve available time zones
        availableTimeZones = TimeZone.getAvailableIDs();

        // Create and add components to the frame
        JLabel timeZoneLabel = new JLabel("Select Time Zone:");
        timeZoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(20, 20, 10, 10);
        add(timeZoneLabel, constraints);

        timeZoneComboBox = new JComboBox<>(availableTimeZones);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(20, 0, 10, 20);
        add(timeZoneComboBox, constraints);

        JButton convertButton = new JButton("Convert");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 20, 20, 20);
        add(convertButton, constraints);

        convertedTimeLabel = new JLabel("Converted Time: ");
        convertedTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 20, 20, 20);
        add(convertedTimeLabel, constraints);

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTime();
            }
        });
    }

    private void convertTime() {
        // Get the selected time zone from the combo box
        String selectedTimeZoneId = availableTimeZones[timeZoneComboBox.getSelectedIndex()];

        // Convert the current time to the selected time zone
        ZoneId selectedZoneId = ZoneId.of(selectedTimeZoneId);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime convertedTime = currentTime.atZone(ZoneId.systemDefault())
                .withZoneSameInstant(selectedZoneId).toLocalDateTime();

        // Format the converted time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = convertedTime.format(formatter);

        // Update the converted time label
        convertedTimeLabel.setText("Converted Time: " + formattedTime);
    }

    public static void main(String[] args) {
        TimeConversionApp app = new TimeConversionApp();
        app.setVisible(true);
    }
}
