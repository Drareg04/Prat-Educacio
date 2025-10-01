package Calculadora;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.*;

public class buttons extends JFrame {
    private JLabel display;
    private JLabel statusDisplay; // Added status display
    private String currentInput = "";
    private double result = 0.0;
    private String lastOperator = "=";
    private String currentBase = "DEC    ";
    private final JButton[] baseButtons = new JButton[4];
    private final Color ORANGE = new Color(255, 165, 0);
    private JButton historyToggleButton;
    // History components
    private JPanel historyPanel;
    private JList<String> historyList;
    private DefaultListModel<String> historyModel;
    private boolean historyVisible = false;

    public buttons() {
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocationRelativeTo(null);
        createUI();
        createHistoryComponents((JPanel) getContentPane().getComponent(0));
    }

    private void createUI() {
        JPanel background = new JPanel(null);
        background.setBackground(Color.BLACK);
        add(background);

        // Display container
        JPanel displayContainer = new JPanel(new BorderLayout());
        displayContainer.setBounds(30, 20, 540, 230);
        displayContainer.setBackground(Color.BLACK);  // Changed from DISPLAY_COLOR
        displayContainer.setBorder(new RoundedBorder(15, Color.DARK_GRAY));
        // Status display
        statusDisplay = new JLabel("", SwingConstants.RIGHT);
        statusDisplay.setFont(new Font("Arial", Font.PLAIN, 16));
        statusDisplay.setForeground(Color.WHITE);  // Changed from GRAY
        statusDisplay.setBorder(BorderFactory.createEmptyBorder(2, 30, 2, 30));
        displayContainer.add(statusDisplay, BorderLayout.NORTH);
        // Main display
        display = new JLabel("0", SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 42));
        display.setForeground(Color.WHITE);  // Added white text color
        display.setVerticalAlignment(SwingConstants.BOTTOM);
        display.setBorder(BorderFactory.createEmptyBorder(10, 30, 15, 30));
        displayContainer.add(display, BorderLayout.CENTER);

        // Base buttons
        JPanel basePanel = new JPanel(new GridLayout(4, 1, 0, 5));
        basePanel.setOpaque(false);
        basePanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 20));

        String[] bases = {"HEX    ", "DEC    ", "OCT    ", "BIN    "};
        for (int i = 0; i < bases.length; i++) {
            JButton btn = new JButton();
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setForeground(Color.WHITE);  // Changed from BLACK
            btn.setBackground(Color.BLACK);  // Changed from DISPLAY_COLOR
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setPreferredSize(new Dimension(150, 30));  // Reduced height
            btn.setVerticalAlignment(SwingConstants.CENTER);
            btn.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 5, 0, 0, Color.BLACK),
                new EmptyBorder(0, 10, 0, 10)  // Reduced padding
            ));
            btn.addActionListener(new BaseButtonListener());
            baseButtons[i] = btn;
            basePanel.add(btn);
            baseButtons[i].setForeground(Color.WHITE);
            btn.putClientProperty("baseType", bases[i]);
        }
        displayContainer.add(basePanel, BorderLayout.SOUTH);
        background.add(displayContainer);

        // Calculator buttons
        setupCalculatorButtons(background);
        updateBaseButtons();
        updateBaseButtonBorders();
        updateButtonStates();
    }

    private void createHistoryComponents(JPanel background) {
        // History panel
        historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBounds(600, 20, 250, 650);
        historyPanel.setBackground(Color.BLACK);
        historyPanel.setBorder(new CompoundBorder(
            new LineBorder(Color.DARK_GRAY, 15),
            new EmptyBorder(10, 10, 10, 10)
        ));
        historyPanel.setVisible(false);
        background.add(historyPanel);

        // History list
        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyList.setBackground(Color.BLACK);
        historyList.setForeground(Color.WHITE);
        historyList.setFont(new Font("Arial", Font.PLAIN, 14));
        historyList.setSelectionBackground(new Color(80, 80, 80));
        historyList.setSelectionForeground(Color.WHITE);
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    restoreFromHistory();
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Clear history button
        JButton clearHistoryButton = new JButton("Clear History");
        clearHistoryButton.setForeground(Color.WHITE);
        clearHistoryButton.setBackground(new Color(80, 80, 80));
        clearHistoryButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearHistoryButton.setBorder(new RoundedBorder(15, new Color(80, 80, 80).darker()));
        clearHistoryButton.setFocusPainted(false);
        clearHistoryButton.setContentAreaFilled(false);
        clearHistoryButton.setOpaque(true);
        clearHistoryButton.addActionListener(_-> historyModel.clear());
        historyPanel.add(clearHistoryButton, BorderLayout.SOUTH);
    }

    private void toggleHistory() {
        historyVisible = !historyVisible;
        historyPanel.setVisible(historyVisible);
        if (historyVisible) {
            setSize(870, 800);
            historyToggleButton.setBackground(ORANGE);
        } else {
            setSize(600, 800);
            historyToggleButton.setText("H");
            historyToggleButton.setBackground(new Color(80, 80, 80));
        }
    }

    private void addToHistory(String operation) {
        if (!operation.isEmpty()) {
            historyModel.addElement(operation);
        }
    }

    private void restoreFromHistory() {
        String selected = historyList.getSelectedValue();
        if (selected != null) {
            String[] parts = selected.split(" = ");
            if (parts.length >= 2) {
                currentInput = parts[parts.length-1];
                display.setText(currentInput.replace('.', ',').toUpperCase(Locale.ROOT));
                result = parseNumber(currentInput, getCurrentRadix());
                lastOperator = "=";
            }
        }
    }

    private void setupCalculatorButtons(JPanel background) {
        int BUTTON_WIDTH = 80;
        int BUTTON_HEIGHT = 60;
        int SPACING = 10;
        int START_X = 30;
        int START_Y = 260;

        String[][] labels = {
            {"A", "π", "%", "CE", "C", "{x]"},
            {"B", "H", "x²", "x³", "xʸ", "÷"},
            {"C", "7", "8", "9", "√x", "×"}, // Second 'C' (input)
            {"D", "4", "5", "6", "¹⁄x", "-"},
            {"E", "1", "2", "3", "x!", "+"},
            {"F", "+/-", "0", ",", "×10", "="},
        };

        for (int row = 0; row < labels.length; row++) {
            for (int col = 0; col < labels[row].length; col++) {
                String text = labels[row][col];
                if (text.isEmpty()) continue;

                JButton btn = new JButton(text);
                btn.setBounds(
                    START_X + col * (BUTTON_WIDTH + SPACING),
                    START_Y + row * (BUTTON_HEIGHT + SPACING),
                    BUTTON_WIDTH,
                    BUTTON_HEIGHT
                );

                // Assign unique action command to the second 'C' (input)
                if (row == 2 && col == 0) {
                    btn.setActionCommand("HEX_C");
                } else {
                    btn.setActionCommand(text);
                }

                // History toggle button (unchanged)
                if (row == 1 && col == 1) {
                    historyToggleButton = btn;
                    styleHistoryButton(btn);
                    btn.addActionListener(_ -> toggleHistory());
                } else {
                    styleButton(btn, text);

                    // Handle the first 'C' (Clear Entry) separately
                    if (row == 0 && col == 4 && text.equals("C")) {
                        btn.addActionListener(_ -> clearEntry());
                    } 
                    // Add listener to all other buttons except the first 'C'
                    else if (!(row == 0 && col == 4 && text.equals("C"))) {
                        btn.addActionListener(new ButtonClickListener());
                    }
                }

                background.add(btn);
            }
        }
    }
    private void styleHistoryButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(80, 80, 80));
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(10, Color.DARK_GRAY));
    }

    private void styleButton(JButton btn, String text) {
        Color bgColor = text.equals("=") ? ORANGE :
                       isOperatorOrSpecial(text) ? new Color(80, 80, 80) :
                       new Color(120, 120, 120);
        
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(15, bgColor.darker()));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
    }

    private void updateBaseButtons() {
        try {
            double value = getCurrentDisplayValue();
            String[] bases = {"HEX    ", "DEC    ", "OCT    ", "BIN    "};
            
            for (int i = 0; i < baseButtons.length; i++) {
                int radix = getRadix(bases[i]); // Now correctly identifies each base
                String converted = convertDoubleToString(value, radix, 6);
                String baseColor = bases[i].equals(currentBase) ? "#FFA500" : "#FFFFFF";
                
                String buttonText = String.format(
                    "<html><div style='width:140px; font-family:Monospaced;'>" +
                    "<span style='font-size:12px;'>%s</span>" +
                    "<span style='color:%s; font-size:12px; margin-left:40px;'>%s</span>" +
                    "</div></html>",
                    bases[i], // Base name without spaces (spacing handled by CSS)
                    baseColor,
                    converted
                );
                
                baseButtons[i].setText(buttonText);
            }
        } catch (NumberFormatException ex) {
            for (int i = 0; i < baseButtons.length; i++) {
                baseButtons[i].setText(String.format(
                    "<html><div style='width:140px; font-family:Monospaced;'>" +
                    "<span style='font-size:12px;'>%s</span>" +
                    "<span style='color:#FFFFFF; font-size:12px; margin-left:40px;'>ERR</span>" +
                    "</div></html>", 
                    new String[]{"HEX    ", "DEC    ", "OCT    ", "BIN    "}[i]
                ));
            }
        }
    }

    private double getCurrentDisplayValue() {
        try {
            if (!lastOperator.equals("=") && !currentInput.isEmpty()) {
                double secondOperand = parseNumber(currentInput, getCurrentRadix());
                return computeOperation(result, lastOperator, secondOperand);
            } else {
                return currentInput.isEmpty() 
                    ? (!lastOperator.equals("=") ? result : 0.0)
                    : parseNumber(currentInput, getCurrentRadix());
            }
        } catch (NumberFormatException | ArithmeticException ex) {
            return Double.NaN;
        }
    }

    private double computeOperation(double first, String operator, double second) {
        switch (operator) {
            case "+" -> {
                return first + second;
            }
            case "-" -> {
                return first - second;
            }
            case "×" -> {
                return first * second;
            }
            case "÷" -> {
                if (second == 0) {
                    return Double.NaN;
                }
                return first / second;
            }
            case "x^y" -> {
                return Math.pow(first, second);
            }
            default -> {
                return second;
            }
        }
    }

    private void updateBaseButtonBorders() {
        final String[] bases = {"HEX    ", "DEC    ", "OCT    ", "BIN    "}; // Add class-level array if needed
        
        for (int i = 0; i < baseButtons.length; i++) {
            // Directly use the bases array instead of parsing HTML
            String base = bases[i]; 
            boolean isActive = base.equals(currentBase);
            
            baseButtons[i].setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 5, 0, 0, isActive ? ORANGE : Color.BLACK),
                new EmptyBorder(5, 15, 5, 5)
            ));
        }
    }

    private void clearEntry() {
        currentInput = "";
        display.setText("0");
        updateBaseButtons(); // Optional: Update base displays if needed
    }

    private class BaseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clicked = (JButton) e.getSource();
            String newBase = (String) clicked.getClientProperty("baseType"); 
            String oldBase = currentBase;
            currentBase = newBase;
            
            try {
                if (!currentInput.isEmpty()) {
                    double value = parseNumber(currentInput, getRadix(oldBase));
                    currentInput = convertDoubleToString(value, getRadix(newBase), 6);
                }
                // Directly update display instead of calling updateDisplay()
                if (currentInput.isEmpty()) {
                    display.setText("0");
                } else {
                    display.setText(currentInput.replace('.', ',').toUpperCase(Locale.ROOT));
                }
            } catch (NumberFormatException ex) {
                currentInput = "";
                display.setText("0");
            }
            
            updateBaseButtons();
            updateBaseButtonBorders();
            updateButtonStates();
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            // Handle the second 'C' (HEX_C) separately
            if ("HEX_C".equals(command)) {
                if (currentBase.equals("HEX    ")) {
                    currentInput = currentInput.equals("0") ? "C" : currentInput + "C";
                    updateDisplay();
                }
            } else {
                handleInput(command); // Existing logic for other buttons
            }

            updateBaseButtons();
            updateBaseButtonBorders();
        }

        private void handleInput(String text) {
            switch (text) {
                case "=" -> performOperation(true);
                case "x²" -> square();
                case "x³" -> cube();
                case "√x" -> squareRoot();
                case "x!" -> factorial();
                case "{x]" -> backspace();
                case "CE" -> clearAll();
                case "C" -> clearEntry();
                case "%" -> handlePercentage();
                case "¹⁄x" -> handleReciprocal();
                case "π" -> insertPi();
                case "×10" -> multiplyByRadix();
                case "xʸ" -> handleOperator("x^y");
                default -> {
                    if (text.matches("[0-9A-F]")) {
                        if (isValidForCurrentBase(text)) {
                            currentInput = currentInput.equals("0") ? text : currentInput + text;
                        }
                    } else if (text.matches("[÷×+\\-]")) {
                        handleOperator(text);
                    } else if (text.equals(",")) {
                        if (!currentInput.contains(".")) {
                            currentInput += currentInput.isEmpty() ? "0." : ".";
                        }
                    } else if (text.equals("+/-")) {
                        currentInput = currentInput.isEmpty() || currentInput.equals("0") 
                            ? "-" 
                            : (currentInput.startsWith("-") 
                                ? currentInput.substring(1) 
                                : "-" + currentInput);
                    }
                }
            }
            updateDisplay();
            updateButtonStates();
        }

        private void updateDisplay() {
            if (currentInput.isEmpty() || currentInput.equals("0")) {
                display.setText("0");
            } else {
                String displayText = currentInput.replace('.', ',').toUpperCase(Locale.ROOT);
                display.setText(displayText);
            }
        }

        private void performOperation(boolean isFinal) {
            try {
                double currentValue = parseNumber(currentInput, getCurrentRadix());
                double firstOperand = result;
                String operator = lastOperator;
                String secondOperand = currentInput;
                
                switch (operator) {
                    case "+" -> result += currentValue;
                    case "-" -> result -= currentValue;
                    case "×" -> result *= currentValue;
                    case "÷" -> {
                        if (currentValue == 0) throw new ArithmeticException();
                        result /= currentValue;
                    }
                    case "x^y" -> result = Math.pow(result, currentValue);
                    default -> result = currentValue;
                }
                
                currentInput = convertDoubleToString(result, getCurrentRadix(), 6);
                
                if (isFinal) {
                    String operation = convertDoubleToString(firstOperand, getCurrentRadix(), 6) 
                        + " " + operator + " " + secondOperand 
                        + " = " + currentInput;
                    addToHistory(operation);
                    statusDisplay.setText(""); // Clear status after equals
                }
                
                lastOperator = "=";
            } catch (NumberFormatException | ArithmeticException ex) {
                currentInput = "";
                display.setText("ERR");
            }
            updateDisplay();
        }

        private void handleOperator(String operator) {
            if (!currentInput.isEmpty()) {
                performOperation(!lastOperator.equals("=")); // Add to history if previous operator was not '='
            }
            lastOperator = operator;
            currentInput = "";
            updateStatusDisplay();
        }

        private void updateStatusDisplay() {
            if (lastOperator.equals("=")) {
                statusDisplay.setText("");
            } else {
                String status = convertDoubleToString(result, getCurrentRadix(), 6) + " " + lastOperator;
                statusDisplay.setText(status);
            }
        }

        private void insertPi() {
            double piValue = Math.PI;
            currentInput = convertDoubleToString(piValue, getCurrentRadix(), 15);
            updateDisplay();
        }

        private void factorial() {
            try {
                double value = parseNumber(currentInput, getCurrentRadix());
                if (value < 0 || value != Math.floor(value)) throw new NumberFormatException();
                long longValue = (long) value;
                long fact = 1;
                for (int i = 2; i <= longValue; i++) fact *= i;
                currentInput = convertDoubleToString(fact, getCurrentRadix(), 0);
            } catch (NumberFormatException | ArithmeticException ex) {
                currentInput = "";
                display.setText("ERR");
            }
            updateDisplay();
        }

        private void handleReciprocal() {
            try {
                if (currentInput.isEmpty()) return;
                double value = parseNumber(currentInput, getCurrentRadix());
                if (value == 0) throw new ArithmeticException();
                currentInput = convertDoubleToString(1.0/value, getCurrentRadix(), 6);
            } catch (NumberFormatException | ArithmeticException ex) {
                currentInput = "";
                display.setText("ERR");
            }
            updateDisplay();
        }

        private void square() {
            try {
                double value = parseNumber(currentInput, getCurrentRadix());
                currentInput = convertDoubleToString(value*value, getCurrentRadix(), 6);
            } catch (NumberFormatException ex) {
                currentInput = "";
            }
            updateDisplay();
        }
        private void cube() {
            try {
                double value = parseNumber(currentInput, getCurrentRadix());
                currentInput = convertDoubleToString(value * value * value, getCurrentRadix(), 6);
            } catch (NumberFormatException ex) {
                currentInput = "";
            }
            updateDisplay();
        }

        private void squareRoot() {
            try {
                double value = parseNumber(currentInput, getCurrentRadix());
                if (value < 0) throw new NumberFormatException();
                currentInput = convertDoubleToString(Math.sqrt(value), getCurrentRadix(), 6);
            } catch (NumberFormatException ex) {
                currentInput = "";
                display.setText("ERR");
            }
            updateDisplay();
        }

        private void handlePercentage() {
            try {
                double value = parseNumber(currentInput, getCurrentRadix());
                currentInput = convertDoubleToString(value/100.0, getCurrentRadix(), 6);
            } catch (NumberFormatException ex) {
                currentInput = "";
            }
            updateDisplay();
        }

        private void multiplyByRadix() {
            try {
                if (currentInput.isEmpty()) currentInput = "0";
                double value = parseNumber(currentInput, getCurrentRadix());
                currentInput = convertDoubleToString(value * getCurrentRadix(), getCurrentRadix(), 6);
            } catch (NumberFormatException ex) {
                currentInput = "";
                display.setText("ERR");
            }
            updateDisplay();
        }

        private void backspace() {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
            }
            updateDisplay();
        }

        private void clearEntry() {
            currentInput = "";
            updateDisplay(); // Only clears current input, keeps operation state
        }

        private void clearAll() {
            currentInput = "";
            statusDisplay.setText(""); 
            result = 0.0;
            lastOperator = "=";
            updateDisplay();
        }
    }

    private int getCurrentRadix() {
        return getRadix(currentBase);
    }

    private int getRadix(String base) {
        return switch (base) {
            case "BIN    " -> 2;
            case "OCT    " -> 8;
            case "DEC    " -> 10;
            default -> 16;
        };
    }

    private boolean isOperatorOrSpecial(String text) {
        return text.matches("π|%|CE|C|\\(|\\)|H|xʸ|x²|x³|÷|√x|×|¹⁄x|-|x!|\\+|\\+/-|,|×10|=|\\{x]");
    }

    private void updateButtonStates() {
        Component[] components = ((JPanel) getContentPane().getComponent(0)).getComponents();
        boolean foundFirstC = false;

        for (Component comp : components) {
            if (comp instanceof JButton btn) {
                String text = btn.getText();
                String command = btn.getActionCommand();

                // Handle the second 'C' (HEX_C)
                if ("HEX_C".equals(command)) {
                    boolean enabled = currentBase.equals("HEX    ");
                    btn.setEnabled(enabled);
                    btn.setBackground(enabled ? new Color(120, 120, 120) : new Color(30, 30, 30));
                    continue;
                }

                // First 'C' (clear entry) remains always enabled
                if (text.equals("C") && !foundFirstC) {
                    foundFirstC = true;
                    btn.setEnabled(true);
                    btn.setBackground(new Color(80, 80, 80));
                    continue;
                }

                // Existing logic for other buttons
                boolean enabled = isValidForCurrentBase(text);
                boolean isOperator = isOperatorOrSpecial(text);

                if (text.equals("=")) {
                    btn.setEnabled(enabled);
                    btn.setBackground(enabled ? ORANGE : new Color(30, 30, 30));
                } else {
                    Color bgColor = enabled ?
                        (isOperator ? new Color(80, 80, 80) : new Color(120, 120, 120)) :
                        new Color(30, 30, 30);
                    btn.setEnabled(enabled);
                    btn.setBackground(bgColor);
                }
            }
        }
    }

    private boolean isValidForCurrentBase(String text) {
        if (text.equals("π")) return true;
        if (text.matches("[0-9A-F]")) {
            // Allow letters only in hexadecimal mode
            if (text.matches("[A-F]")) {
                return currentBase.equals("HEX    ");
            }
            // Allow numbers based on current base
            int maxDigit = getMaxDigit();
            int digit = Integer.parseInt(text);
            return digit <= maxDigit;
        }
        return true;
    }

    private int getMaxDigit() {
        return switch (currentBase) {
            case "BIN    " -> 1;
            case "OCT    " -> 7;
            case "DEC    " -> 9;
            default -> 15;
        };
    }

    private double parseNumber(String input, int radix) throws NumberFormatException {
        // Handle empty input and explicit zero cases
        if (input.isEmpty() || input.equals("0") || input.equals("0.") || input.equals("0.0")) {
            return 0.0;
        }

        boolean negative = false;
        if (input.startsWith("-")) {
            negative = true;
            input = input.substring(1);
        } else if (input.startsWith("+")) {
            input = input.substring(1);
        }

        String[] parts = input.split("\\.");
        if (parts.length > 2) {
            throw new NumberFormatException("Invalid number format");
        }

        // Parse integer part
        long integerValue = 0;
        if (!parts[0].isEmpty()) {
            integerValue = Long.parseLong(parts[0], radix);
        }

        // Parse fractional part
        double fractionalValue = 0.0;
        if (parts.length > 1 && !parts[1].isEmpty()) {
            String fractionalPart = parts[1];
            for (int i = 0; i < fractionalPart.length(); i++) {
                char c = fractionalPart.charAt(i);
                int digit = Character.digit(c, radix);
                
                if (digit == -1) {
                    throw new NumberFormatException("Invalid digit '" + c + "' in base " + radix);
                }
                
                fractionalValue += digit * Math.pow(radix, -(i + 1));
            }
        }

        double result = integerValue + fractionalValue;
        return negative ? -result : result;
    }

    private String convertDoubleToString(double value, int radix, int maxFractionalDigits) {
        // Handle special cases first
        if (Double.isNaN(value)) return "ERR";
        if (value == 0.0 || value == -0.0) return "0";
        
        boolean negative = value < 0;
        value = Math.abs(value);
        
        // Convert integer part
        long integerPart = (long) value;
        String integerStr = Long.toString(integerPart, radix).toUpperCase(Locale.ROOT);
        
        // Convert fractional part
        double fractionalPart = value - integerPart;
        StringBuilder fractionalStr = new StringBuilder();
        
        int digitsAdded = 0;
        while (fractionalPart > 0 && digitsAdded < maxFractionalDigits) {
            fractionalPart *= radix;
            int digit = (int) fractionalPart;
            fractionalStr.append(Character.forDigit(digit, radix));
            fractionalPart -= digit;
            digitsAdded++;
        }
        
        // Build final string
        String result = (negative ? "-" : "") + integerStr;
        
        // Add fractional part if exists
        if (fractionalStr.length() > 0) {
            result += "." + fractionalStr.toString().toUpperCase(Locale.ROOT);
        }
        
        // Special handling for binary trailing zeros
        if (radix == 2 && result.endsWith(".0")) {
            result = result.substring(0, result.length() - 2);
        }
        
        return result;
    }

    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color color;

        RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new buttons().setVisible(true));
    }
}