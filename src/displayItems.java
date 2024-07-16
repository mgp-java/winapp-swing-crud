import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class displayItems extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public displayItems() {
        // Set up the frame
        setTitle("Table Data Display");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Set up the table model and JTable
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // Create a panel and add the table to it inside a JScrollPane
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the frame
        add(panel);

        // Load data from the database
        loadData();
    }

    private void loadData() {

        try {
            Conn c = new Conn();
            System.out.println("Connected");
            String query = "select * from itemDetails";
            ResultSet rs = c.s.executeQuery(query);

            // Get metadata to create table columns
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Clear existing columns and rows in the table model
            tableModel.setColumnCount(0);
            tableModel.setRowCount(0);

            // Add columns to the table model
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the table model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Run the application in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new displayItems().setVisible(true);
            }
        });
    }
}