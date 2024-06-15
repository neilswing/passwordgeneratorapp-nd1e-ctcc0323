import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Password_Generator extends JFrame {

    private JCheckBox lowerCaseCheckBox;
    private JCheckBox upperCaseCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JSpinner lengthSpinner;
    private JTextField passwordTextField;
    private JButton generateButton;
    private Image backgroundImage;

    public Password_Generator() {
        // Set up the JFrame properties
        setTitle("Password Generator CTCC0323");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initialize(); // initialize the user interface
    }

    private void initialize() {
        // Create checkboxes for character type selection
        lowerCaseCheckBox = new JCheckBox("Use LowerCase");
        upperCaseCheckBox = new JCheckBox("Use UpperCase");
        numbersCheckBox = new JCheckBox("Use Numbers");
        specialCharsCheckBox = new JCheckBox("Use Special Characters");

        lowerCaseCheckBox.setFocusPainted(false);
        lowerCaseCheckBox.setBorderPainted(false);
        lowerCaseCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        upperCaseCheckBox.setFocusPainted(false);
        upperCaseCheckBox.setBorderPainted(false);
        upperCaseCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        numbersCheckBox.setFocusPainted(false);
        numbersCheckBox.setBorderPainted(false);
        numbersCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        specialCharsCheckBox.setFocusPainted(false);
        specialCharsCheckBox.setBorderPainted(false);
        specialCharsCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Create a spinner for password length selection
        lengthSpinner = new JSpinner(new SpinnerNumberModel(8, 4, 20, 1));

        // Create a text field to display the generated password
        passwordTextField = new JTextField(20);
        passwordTextField.setFont(new Font("Sans", Font.PLAIN, 16));
        passwordTextField.setEditable(false);

        // Create a button to generate passwords
        generateButton = new JButton("Generate Password");
        generateButton.setBackground(new Color(63, 81, 181));
        generateButton.setForeground(Color.white);
        generateButton.setFocusPainted(false);
        generateButton.setBorderPainted(false);
        generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        generateButton.addActionListener(e -> generatePassword());

        // Create panels to hold UI
        JPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new GridLayout(8, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(lowerCaseCheckBox);
        mainPanel.add(upperCaseCheckBox);
        mainPanel.add(numbersCheckBox);
        mainPanel.add(specialCharsCheckBox);

        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lengthPanel.add(new JLabel("Password Length"));
        lengthPanel.add(lengthSpinner);
        mainPanel.add(lengthPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(generateButton);
        mainPanel.add(buttonPanel);
        mainPanel.add(passwordTextField);

        add(mainPanel);
    }

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    private void generatePassword() {
        // Get the desired password length from the spinner
        int passwordLength = (int) lengthSpinner.getValue();

        // Define character sets for password generation
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        // Initialize the characters string based on user selections
        StringBuilder characters = new StringBuilder();
        if (lowerCaseCheckBox.isSelected()) characters.append(lowerCase);
        if (upperCaseCheckBox.isSelected()) characters.append(upperCase);
        if (numbersCheckBox.isSelected()) characters.append(numbers);
        if (specialCharsCheckBox.isSelected()) characters.append(specialChars);

        // If no character type is selected, show an error message
        if (characters.length() == 0) {
            JOptionPane.showMessageDialog(this, "Please Use At Least One Option");
            return;
        }

        // Generate the password by selecting random characters from the characters string
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            password.append(randomChar);
        }

        // Display the generated password in the text field
        passwordTextField.setText(password.toString());

        // Copy the generated password to clipboard
        StringSelection selection = new StringSelection(password.toString());
        Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);

        // Inform the user that the password has been copied
        JOptionPane.showMessageDialog(this, "Password copied to clipboard!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Password_Generator app = new Password_Generator();
            app.setVisible(true);
        });
    }
}
