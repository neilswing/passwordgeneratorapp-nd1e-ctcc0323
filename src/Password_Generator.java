import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
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

/**
 *
 * Presented by Swing,Valle,Trinidad
 */
public class Password_Generator extends JFrame {

    private JCheckBox lowerCaseCheckBox;
    private JCheckBox upperCaseCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JSpinner lengthSpinner;
    private JTextField passwordTextField;
    private JButton generateButton;
    
    public Password_Generator(){
        // Set up the JFrame properties
        setTitle("Password Generator CTCC0323");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        initialize(); // initialize the user interface
    }
    
    
    private void initialize(){
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
        lengthSpinner = new JSpinner(new SpinnerNumberModel(8,4,20,1));
        
        // Create a text field to display the generated password
        passwordTextField = new JTextField(20);
        passwordTextField.setFont(new Font("Sans", Font.PLAIN, 16));
        passwordTextField.setEditable(false);
        
        // Create a button to generate passwords
        generateButton = new JButton("Generate Password");
        generateButton.setBackground(new Color(63,81,181));
        generateButton.setForeground(Color.white);
        generateButton.setFocusPainted(false);
        generateButton.setBorderPainted(false);
        generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                generatePassword();
                
            }
        });
        
        
        // Create panels to hold UI 
        JPanel mainPanel = new JPanel(new GridLayout(8,1,10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPanel.setBackground(Color.white);
        
        mainPanel.add(lowerCaseCheckBox);
        mainPanel.add(upperCaseCheckBox);
        mainPanel.add(numbersCheckBox);
        mainPanel.add(specialCharsCheckBox);
        
        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lengthPanel.setBackground(Color.gray);
        lengthPanel.add(new JLabel("Password Length"));
        lengthPanel.add(lengthSpinner);
        mainPanel.add(lengthPanel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(generateButton);
        mainPanel.add(buttonPanel);
        mainPanel.add(passwordTextField);
        
        getContentPane().setBackground(Color.white);
        add(mainPanel);
        
    }
    
    
    private String generatePassword()
    {
        // Get the desired password length from the spinner
        int passwordLength = (int) lengthSpinner.getValue();
        
        // Define character sets for password generation
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        
        
        // Initialize the characters string based on user selections
        String characters = "";
        if(lowerCaseCheckBox.isSelected()) characters += lowerCase;
        if(upperCaseCheckBox.isSelected()) characters += upperCase;
        if(numbersCheckBox.isSelected()) characters += numbers;
        if(specialCharsCheckBox.isSelected()) characters += specialChars;
        
        // If no character type is selected, show an error message
        if (characters.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please Use At Least One Option");
            return "";
        }
        
        // Generate the password by selecting random characters from the characters string
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        
        for(int i = 0; i < passwordLength; i++)
        {
        int randomIndex = random.nextInt(characters.length());
           char randomChar = characters.charAt(randomIndex);
           password.append(randomChar);
        }
        
        // Display the generated password in the text field
        passwordTextField.setText(password.toString());
        return password.toString();
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
        
            try
            {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
            Password_Generator app = new Password_Generator();
            app.setVisible(true);
        
        });
        
        
    }

}
