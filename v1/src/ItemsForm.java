import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

    public class ItemsForm  extends JFrame implements ActionListener {

        JTextField number;

        JComboBox<String> itemsComboBox,cityComboBox,purchasedItemsComboBox,unitComboBox;
        JButton save,view;

        public ItemsForm (){
            setLayout(null);

            JLabel country = new JLabel("Country");
            country.setFont(new Font("Raleway",Font.BOLD,20));
            country.setBounds(100,80,400,30);
            add(country);

            String[] countries = {"India", "Sweden", "USA"};
            itemsComboBox = new JComboBox<>(countries);
            itemsComboBox.setBounds(280,80,400,30);
            add(itemsComboBox);

            JLabel stateText = new JLabel("City");
            stateText.setFont(new Font("Raleway",Font.BOLD,20));
            stateText.setBounds(100,120,400,30);
            add(stateText);

            Map<String, String[]> countryCityMap = new HashMap<>();
            countryCityMap.put("India", new String[]{"New Delhi", "Bangalore", "Chennai","Kochi"});
            countryCityMap.put("Sweden", new String[]{"Stockholm", "Göteborg", "Mälmo","Linköping"});
            countryCityMap.put("USA", new String[]{"Chicago", "New York", "Texas","Washington D.C"});
            cityComboBox = new JComboBox<>();
            cityComboBox.setBounds(280,120,400,30);
            add(cityComboBox);

            itemsComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedCountry = (String) itemsComboBox.getSelectedItem();
                    String[] cities = countryCityMap.get(selectedCountry);
                    cityComboBox.setModel(new DefaultComboBoxModel<>(cities));
                }
            });
            itemsComboBox.setSelectedIndex(0);
            cityComboBox.setBounds(280,120,400,30);
            add(cityComboBox);


            JLabel purItms = new JLabel("Purchased Items");
            purItms.setFont(new Font("Raleway",Font.BOLD,20));
            purItms.setBounds(100,160,400,30);
            add(purItms);

            String[] purchasedItems = {"Fruits", "Vegetables", "Meat", "Bags"};
            purchasedItemsComboBox = new JComboBox<>(purchasedItems);
            purchasedItemsComboBox.setBounds(280,160,400,30);
            add(purchasedItemsComboBox);

            JLabel quantity = new JLabel("Quantity");
            quantity.setFont(new Font("Raleway",Font.BOLD,20));
            quantity.setBounds(100,200,400,30);
            add(quantity);


            JLabel no = new JLabel("Number:");
            no.setFont(new Font("Raleway",Font.BOLD,12));
            no.setBounds(230,200,400,30);
            add(no);

            number = new JTextField();
            number.setFont(new Font("Raleway",Font.BOLD,20));
            number.setBounds(280,200,50,30);
            add(number);

            JLabel unit = new JLabel("Unit:");
            unit.setFont(new Font("Raleway",Font.BOLD,12));
            unit.setBounds(350,200,400,30);
            add(unit);

            String[] uom = {"Kg", "lbs", "pounds"};
            unitComboBox = new JComboBox<>(uom);
            unitComboBox.setBounds(380,200,100,30);
            add(unitComboBox);

            save = new JButton("Save");
            save.setForeground(Color.WHITE);
            save.setBackground(Color.BLACK);
            save.setFont(new Font("Raleway",Font.BOLD,20));
            save.setBounds(320,660,100,30);
            add(save);
            save.addActionListener(this);

            view = new JButton("View");
            view.setForeground(Color.WHITE);
            view.setBackground(Color.BLACK);
            view.setFont(new Font("Raleway",Font.BOLD,20));
            view.setBounds(520,660,100,30);
            add(view);
            view.addActionListener(this);

            getContentPane().setBackground(Color.WHITE);

            setSize(850,800);
            setLocation(350,10);
            setVisible(true);
        }
        public void actionPerformed(ActionEvent ae){
            try {
                if(ae.getSource() == save) {
                    String selectedCountry = (String)itemsComboBox .getSelectedItem();
                    String selectedCity = (String)cityComboBox .getSelectedItem();
                    String selectedPurchasedItem = (String)purchasedItemsComboBox .getSelectedItem();
                    String qty = number.getText();
                    String selectedUnit = (String)unitComboBox.getSelectedItem();


                    Conn c = new Conn();
                    System.out.println("Connected");
                    String query = "insert into itemDetails(country,city,items,quantity,unit) values('"+selectedCountry+ "','" +selectedCity+"','"+selectedPurchasedItem+"','"+qty+"','"+selectedUnit+"')";
                    c.s.executeUpdate(query);

                }
                else if (ae.getSource() == view) {
                    setVisible(false);
                    new displayItems().setVisible(true);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        public static void main(String[] args) {
            new ItemsForm();
        }
    }



