package admin.create_plt;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ReadData_db {

    public JFreeChart createChart() {
        double[] values = {30000, 20000, 50000, 40000, 20000, 20000, 30000, 20000, 40000, 20000, 45000, 25000};
        String[] labels = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String title = "Earnings (kr)";

        CategoryDataset dataset = createDataset(values, labels);
        return createChart(dataset, title);
    }

    private CategoryDataset createDataset(double[] values, String[] labels) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < values.length; i++) {
            dataset.addValue(values[i], "Earnings", labels[i]);
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset, String title) {
        return ChartFactory.createBarChart(
                title,
                "Months",
                "Earnings",
                dataset
        );
    }

    public void createImage() {
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);

        JFrame frame = new JFrame("Bar Chart");
        frame.setSize(600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.setVisible(true);

        // Save the graph
        saveGraph(chart);
    }

    private void saveGraph(JFreeChart chart) {
        try {
            ChartUtils.saveChartAsPNG(new File("/path/to/save/directory/" + "statistics.png"), chart, 600, 650);
            System.out.println("The graph is saved as " + "statistics.png");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
