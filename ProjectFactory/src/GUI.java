import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;


public class GUI {
    private JLabel label1, label2, imageLabel;
    private JPanel ButtonPanel, imagePanel;
    private JButton Query1, Query2, Query3, Query4, Query5, executeThreadsButton;
    private JFrame frame;

    //Graphic User Interface
    public GUI() {
        //Constructor for gui screen, setting frame, panel and buttons
        //Building Frame for GUI
        frame = new JFrame();
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Adding Image to the frame
        imagePanel = new JPanel(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("C:/_SourceDev.Java/JvOOP/ProjectFactory/src/GreenHouse.jpg");
        imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        Query1 = new JButton("Max LoggedValue"); // New button for query 1
        Query1.setFont(new Font("MV Boli", Font.PLAIN, 15));
        Query1.setBackground(new Color(70, 162, 49, 255));
        Query2 = new JButton("Total LoggedValue of given LogID"); // New button for query 2
        Query2.setFont(new Font("MV Boli", Font.PLAIN, 15));
        Query2.setBackground(new Color(70, 162, 49, 255));
        Query3 = new JButton("LineID of min LoggedValue"); // New button for query 3
        Query3.setFont(new Font("MV Boli", Font.PLAIN, 15));
        Query3.setBackground(new Color(70, 162, 49, 255));
        Query4 = new JButton("LineID maxLoggedValue between dates"); // New button for query 4
        Query4.setFont(new Font("MV Boli", Font.PLAIN, 15));
        Query4.setBackground(new Color(70, 162, 49, 255));
        Query5 = new JButton("When LoggedValue was 0"); // New button for query 5
        Query5.setFont(new Font("MV Boli", Font.PLAIN, 15));
        Query5.setBackground(new Color(70, 162, 49));
        executeThreadsButton = new JButton("Execute Threads");
        executeThreadsButton.setFont(new Font("MV Boli", Font.PLAIN, 15));
        executeThreadsButton.setBackground(new Color(70, 162, 49, 255));
        //Adding action listeners to execute the queries
        Query1.addActionListener(e -> showResults(SqlQueries.executeMaxLoggedValueDetails()));
        Query2.addActionListener(e -> {
            int userInput = getUserInput();
            String result = SqlQueries.executeTotalLoggedValueQuery(userInput);
            showResults(result);
        });
        Query3.addActionListener(e -> showResults(SqlQueries.executeMinimumLoggedValueQuery()));
        Query4.addActionListener(e -> {
            String[] userInput = QueryPickdates();
            String result = SqlQueries.executeMaxLoggedValueBetweenDatesQuery(userInput[0], userInput[1]);
            showResults(result);
        });
        Query5.addActionListener(e -> displayQueryResults(SqlQueries.executeLoggedValueZeroTimesQuery()));
        executeThreadsButton.addActionListener(e -> executeThreads());
        label1 = new JLabel("<html><font size='5'>Welcome to the factory!</font></html>");
        label1.setForeground(new Color(39, 124, 17));
        label1.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label2 = new JLabel("Please Choose one of the options: ");
        label2.setFont(new Font("MV Boli", Font.PLAIN, 15));
        //Adding Labels and buttons to the panel
        ButtonPanel = new JPanel(new GridLayout(8, 1));
        ButtonPanel.setBackground(new Color(131, 250, 88, 155));
        ButtonPanel.add(label1, BorderLayout.CENTER);
        ButtonPanel.add(label2, BorderLayout.CENTER);
        ButtonPanel.add(label1);
        ButtonPanel.add(label2);
        ButtonPanel.add(Query1);
        ButtonPanel.add(Query2);
        ButtonPanel.add(Query3);
        ButtonPanel.add(Query4);
        ButtonPanel.add(Query5);
        ButtonPanel.add(executeThreadsButton);
        frame.setTitle("Factory producing plastic sheets for greenhouses");
        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(ButtonPanel, BorderLayout.NORTH);
        //Adjusting frame to be in the middle of the screen
        centerFrameOnScreen(frame);
        frame.setVisible(true);
    }


    private static String[] QueryPickdates() {
        // Create a JPanel to hold the components
        JPanel DatePanel = new JPanel();

        JLabel label= new JLabel("Please Choose two dates (first before second): ");
        DatePanel.add(label,BorderLayout.CENTER);
        // Create a UtilDateModel to handle date selection for the first date
        UtilDateModel firstDateModel = new UtilDateModel();
        Properties properties = new Properties();

        // Create a JDatePanel for the first date
        JDatePanelImpl firstDatePanel = new JDatePanelImpl(firstDateModel, properties);

        // Create a custom DateComponentFormatter for the first date
        JDatePickerImpl firstDatePicker = new JDatePickerImpl(firstDatePanel, new DateLabelFormatter());

        // Add the first date picker to the panel
        DatePanel.add(firstDatePicker);

        // Create a UtilDateModel to handle date selection for the second date
        UtilDateModel secondDateModel = new UtilDateModel();

        // Create a JDatePanel for the second date
        JDatePanelImpl secondDatePanel = new JDatePanelImpl(secondDateModel, properties);

        // Create a custom DateComponentFormatter for the second date
        JDatePickerImpl secondDatePicker = new JDatePickerImpl(secondDatePanel, new DateLabelFormatter());

        // Add the second date picker to the panel
        DatePanel.add(secondDatePicker);

        // Show dialog to select dates
        int result = JOptionPane.showConfirmDialog(null, DatePanel, "Pick Dates", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Get the selected dates
            Date selectedFirstDate = (Date) firstDatePicker.getModel().getValue();
            Date selectedSecondDate = (Date) secondDatePicker.getModel().getValue();

            // Format the selected dates
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String formattedFirstDate = dateFormatter.format(selectedFirstDate);
            String formattedSecondDate = dateFormatter.format(selectedSecondDate);

            // Return the selected dates as an array
            return new String[]{formattedFirstDate, formattedSecondDate};
        } else {
            // User canceled, return null or handle accordingly
            return null;
        }
    }




    private static void executeThreads() {
        ///Executing 2 threads
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        JFrame textFrame = new JFrame("Text Results");
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.getContentPane().setLayout(new BorderLayout()); // Set layout manager
        textFrame.getContentPane().add(scrollPane, BorderLayout.CENTER); // Ensure it's added to the content pane

        //creating a new Thread that is in charge to execute TotalLoggedValueQuery
        Thread thread1 = new Thread(() -> {
            int userInput = getUserInput();
            String str = SqlQueries.executeTotalLoggedValueQuery(userInput);
            outputTextArea.append(str + "\n");
        });
        //creating a new Thread that is in charge to execute MaxLoggedValueDetails
        Thread thread2 = new Thread(() -> {
            String str = SqlQueries.executeMaxLoggedValueDetails();
            outputTextArea.append(str + "\n");
        });
        // Start both threads
        thread1.start();
        thread2.start();
        // Set frame size and make it visible
        textFrame.setSize(600, 300);
        textFrame.setLocationRelativeTo(null);
        textFrame.setVisible(true);
    }

    private static void displayQueryResults(ArrayList<String> queryResults) {
        // Create a new JFrame for displaying query results
        JFrame resultsFrame = new JFrame("Query Results");
        resultsFrame.setSize(250,250);
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton button1 = new JButton("Show text"); // New button for query 5 show in text
        button1.setFont(new Font("MV Boli", Font.PLAIN, 15));
        button1.setBackground(new Color(70, 162, 49, 255));
        JButton button2 = new JButton("Show PieChart"); // New button for query 5 show in graph
        button2.setFont(new Font("MV Boli", Font.PLAIN, 15));
        button2.setBackground(new Color(70, 162, 49, 255));
        JPanel resultsPanel = new JPanel();
        resultsPanel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        resultsPanel.setLayout(new GridLayout(2, 1));
        resultsPanel.setBackground(new Color(131, 250, 88, 155));
        button1.addActionListener(e -> showTextResults(queryResults) );
        button2.addActionListener(e -> showPieChartResults(queryResults));
        resultsPanel.add(button1);
        resultsPanel.add(button2);
        resultsFrame.add(resultsPanel, BorderLayout.CENTER); // Add panel to the frame
        resultsFrame.setTitle("Factory Project"); // Title of the window
        resultsFrame.setSize(400, 300);
        centerFrameOnScreen(resultsFrame);
        resultsFrame.setVisible(true); // Set the window to be visible and in focus
    }
    private static int getUserInput() {
        //Method that gets the dates from the user and transfer it to int to use in the query
        String userInput = JOptionPane.showInputDialog(null, "Enter LogID: ");
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            //return 0 in case of an error
            return 0;
        }
    }
    private static void showTextResults(ArrayList<String> queryResults) {
        //Transfer the data from ArrayList to display in Text
            // Create a new JFrame for displaying text results
            JFrame textFrame = new JFrame("Text Results");
            textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // Create a JTextArea to display the results
            JTextArea textArea = new JTextArea();
            for (String result : queryResults) {
                textArea.append(result + "\n");
            }
            // Add the JTextArea to the text JFrame
            textFrame.add(new JScrollPane(textArea));
            // Set the size and make text JFrame visible
            textFrame.setSize(400, 300);
            textFrame.setLocationRelativeTo(null);
            textFrame.setVisible(true);
    }

    private static void showPieChartResults(ArrayList<String> queryResults) {
        //Transfer the data from ArrayList to display in PieChart
            DefaultPieDataset pieDataset = new DefaultPieDataset(); //create an empty dataset
            String str;// the date that we are looking for in the Arraylist
            int count = 0;// how many times str is in the Arraylist
            for (int i = 1; i < queryResults.size(); i++) {
                if (queryResults.get(i) !=null)
                {
                        String[] temp = queryResults.get(i).split(" ");
                        str = temp[0];
                        count++;
                        for (int j = i + 1; j < queryResults.size(); j++) {
                            if(queryResults.get(j)==null)
                                continue;
                            else if (queryResults.get(j).startsWith(str)) {
                                count++;
                                queryResults.set(j, null);
                            }
                        }
                        pieDataset.setValue(str, count);
                        count = 0;
                }
            }
        JFreeChart chart = ChartFactory.createPieChart(
                "When LoggedValue was 0: ",
                pieDataset,
                true,
                true,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        // Set a custom PieSectionLabelGenerator with both percentage and number
        DecimalFormat decimalFormat = new DecimalFormat("0.00%");
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})", NumberFormat.getInstance(), decimalFormat));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        JFrame resultsFrame = new JFrame("Query Results");
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultsFrame.getContentPane().add(chartPanel, BorderLayout.CENTER); // Add the chart panel to the frame
        resultsFrame.pack();
        resultsFrame.setLocationRelativeTo(null);
        resultsFrame.setVisible(true);
    }

    private static void showResults(String results) {
        /*Opening a new Frame to show the results of the queries that return String*/

        // Create a new JFrame for displaying results
        JFrame resultsFrame = new JFrame("Query Results");
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JTextArea to display the results
        JTextArea textArea = new JTextArea(results);
        textArea.setEditable(false);

        // Add the JTextArea to the results JFrame
        resultsFrame.getContentPane().add(new JScrollPane(textArea));

        // Set the size and make results JFrame visible
        resultsFrame.setSize(500, 300);
        // Center the window on the screen
        resultsFrame.setLocationRelativeTo(null);
        resultsFrame.setVisible(true);
    }

    public static void centerFrameOnScreen(JFrame frame) {
        //To center the frame in the middle of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
}