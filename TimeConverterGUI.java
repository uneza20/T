package TIME;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TimeConverterGUI extends JFrame implements ActionListener {
    private JLabel titleLabel, currentLabel, convertedLabel;
    private JComboBox<String> countryComboBox;
    private JButton convertButton;
    private Map<String, ZoneId> countryTimeZones;

    public TimeConverterGUI() {
        setTitle("Time Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        titleLabel = new JLabel("Time Conversion", SwingConstants.CENTER);
        currentLabel = new JLabel("Current Time: ", SwingConstants.RIGHT);
        convertedLabel = new JLabel("Converted Time: ", SwingConstants.RIGHT);
        countryComboBox = new JComboBox<>();
        convertButton = new JButton("Convert");

        countryTimeZones = new HashMap<>();
        countryTimeZones.put("New York", ZoneId.of("America/New_York"));
        countryTimeZones.put("London", ZoneId.of("Europe/London"));
        countryTimeZones.put("Tokyo", ZoneId.of("Asia/Tokyo"));
        countryTimeZones.put("Sydney", ZoneId.of("Australia/Sydney"));
        countryTimeZones.put("Dubai", ZoneId.of("Asia/Dubai"));
        countryTimeZones.put("Paris", ZoneId.of("Europe/Paris"));
        countryTimeZones.put("Moscow", ZoneId.of("Europe/Moscow"));
        countryTimeZones.put("Singapore", ZoneId.of("Asia/Singapore"));
        countryTimeZones.put("Berlin", ZoneId.of("Europe/Berlin"));
        countryTimeZones.put("Beijing", ZoneId.of("Asia/Shanghai"));

        for (String country : countryTimeZones.keySet()) {
            countryComboBox.addItem(country);
        }

        convertButton.addActionListener(this);

        add(titleLabel);
        add(new JLabel());
        add(currentLabel);
        add(new JLabel());
        add(convertedLabel);
        add(countryComboBox);
        add(new JLabel());
        add(convertButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TimeConverterGUI::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            String selectedCountry = (String) countryComboBox.getSelectedItem();
            ZoneId selectedTimeZone = countryTimeZones.get(selectedCountry);
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            currentLabel.setText("Current Time: " + currentTime.format(formatter));

            LocalDateTime convertedTime = currentTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(selectedTimeZone).toLocalDateTime();
            convertedLabel.setText("Converted Time: " + convertedTime.format(formatter));
        }
    }
}
