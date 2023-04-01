package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestResUi {

    private static Map<String, Double> menu = new HashMap<>(); // map to store menu items and their prices
    private static List<String> order = new ArrayList<>(); // list to store order items
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTable table;
    private JTextField textField;
    private JButton button,buttonName;
    private JList<String> list;
    private JTable createMenuTable() {
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Item", "Price"}, 0);
        for (String item : menu.keySet()) {
            double price = menu.get(item);
            tableModel.addRow(new Object[]{item, price});
        }
        table.setModel(tableModel);
        return table;
    }
    public TestResUi() {
        // Create and configure the frame
        frame = new JFrame("Restaurant App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);

        // Create the panel and add it to the frame
        panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel);

        // Create and configure the label
        label = new JLabel("Enter item name (or 'done' to finish order):");
        panel.add(label, BorderLayout.NORTH);

        // Create and configure the text field
        textField = new JTextField();

        panel.add(textField, BorderLayout.CENTER);

        // Create and configure the button
        button = new JButton("Add to order");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToList();
            }
        });
        panel.add(button, BorderLayout.EAST);
        // panel.add(buttonName,BorderLayout.CENTER);

        table = createMenuTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.WEST);

        // Create and configure the list
        list = new JList<>(new DefaultListModel<>());
        JScrollPane scrollPane2 = new JScrollPane(list);
        panel.add(scrollPane2, BorderLayout.SOUTH);



    }

    public static void main(String[] args) {
        initializeMenu(); // initialize the menu with some items
        displayMenu(); // display the menu
        TestResUi testResUi = new TestResUi();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                testResUi.frame.setVisible(true);
                initializeMenu();
            }
        });
//
//        Scanner scanner = new Scanner(System.in);
//        boolean continueOrdering = true;
//
//        while (continueOrdering) {
//            System.out.print("Enter item name (or 'done' to finish order): ");
//            String itemName = scanner.nextLine();
//
//            if (itemName.equalsIgnoreCase("done")) {
//                continueOrdering = false;
//            } else if (menu.containsKey(itemName)) {
//                double itemPrice = menu.get(itemName);
//                order.add(itemName);
//
//                // ask for customization (if applicable)
//                System.out.print("Would you like to customize this item? (y/n): ");
//                String customize = scanner.nextLine();
//                if (customize.equalsIgnoreCase("y")) {
//                    System.out.print("Enter customization details: ");
//                    String customization = scanner.nextLine();
//                    order.add(customization);
//                }
//
//                System.out.printf("Added %s ($%.2f) to your order.\n", itemName, itemPrice);
//            } else {
//                System.out.println("Sorry, that item is not on the menu.");
//            }
//        }
//
//        displayOrder(); // display the order
//        double total = calculateTotal(); // calculate the total cost
//        System.out.printf("Your total is: $%.2f\n", total); // display the total cost
    }

    private static void initializeMenu() {
        menu.put("cheeseburger", 8.99);
        menu.put("sandwich", 12.99);
        menu.put("zinger", 9.99);
        menu.put("pizza", 9.99);
        menu.put("fries", 3.99);
        menu.put("chicken Sandwich", 7.99);
        menu.put("salad", 5.99);
        menu.put("soft Drink", 1.99);
        menu.put("tea", 1.99);
        menu.put("coffee", 2.99);
        menu.put("broast", 3.99);
        menu.put("roll", 3.99);
        menu.put("pasta", 6.99);
        menu.put("chicken tikka", 6.99);
        menu.put("chickenRice", 4.99);
        menu.put("coleSlow", 6.99);
        menu.put("cheese", 8.99);
        menu.put("donut", 4.99);
        menu.put("drumSicks", 3.99);

        JTable table;
        table=new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        for (String item : menu.keySet()) {
            double price = menu.get(item);
            tableModel.addRow(new Object[]{item, price});
        }
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        for (String item : menu.keySet()) {
            double price = menu.get(item);
            System.out.printf("%s ($%.2f)\n", item, price);
        }
        System.out.println();
    }

    private static void displayOrder() {
        System.out.println("Order:");
        for (String item : order) {
            System.out.println(item);
        }
        System.out.println();
    }

    private static double calculateTotal() {
        double total = 0;
        for (String item : order) {
            if (menu.containsKey(item)) {
                total += menu.get(item);
            }
        }
        return total;
    }
    private void addItemToList() {
        String itemName = textField.getText();

        if (itemName.equalsIgnoreCase("done")) {
            frame.dispose();
            displayOrder();
            double total = calculateTotal();
            JOptionPane.showMessageDialog(null, String.format("Your total is: $%.2f", total));
        } else if (menu.containsKey(itemName)) {
            double itemPrice = menu.get(itemName);
            order.add(itemName);

            // ask for customization (if applicable)
            int choice = JOptionPane.showConfirmDialog(null, "Would you like to customize this item?", "Customization", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                String customization = JOptionPane.showInputDialog(null, "Enter customization details:");
                order.add(customization);
            }

            DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
            model.addElement(String.format("%s ($%.2f)", itemName, itemPrice));
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, that item is not on the menu.");
        }

        textField.setText("");
        textField.requestFocus();
    }
}
