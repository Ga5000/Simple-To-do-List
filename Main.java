import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Main {
    JFrame frame;
    JPanel taskPanel;
    int numberOfTasks = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("To-do List");
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setPreferredSize(new Dimension(100, 50));

        JLabel titleLabel = new JLabel("To-do List");
        Font font = new Font("Open Sans", Font.BOLD, 35);
        titleLabel.setFont(font);
        titleLabel.setForeground(Color.orange);

        panel.add(titleLabel);
        frame.add(panel, BorderLayout.NORTH);

        taskPanel = new JPanel(new GridLayout(0, 1)); // Use GridLayout for task list
        frame.add(new JScrollPane(taskPanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.setPreferredSize(new Dimension(100, 50));

        JButton addTaskButton = new JButton("Add Task");
        Font buttonFont = new Font("Open Sans", Font.BOLD, 30);
        addTaskButton.setBackground(Color.white);
        addTaskButton.setFont(buttonFont);
        addTaskButton.setPreferredSize(new Dimension(200, 45));
        addTaskButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        JButton clearAll = new JButton("Clear");
        clearAll.setFont(buttonFont);
        clearAll.setBackground(Color.white);
        clearAll.setForeground(Color.black);
        clearAll.setPreferredSize(new Dimension(200, 45));
        clearAll.setBorder(new EmptyBorder(0, 0, 0, 0));

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = JOptionPane.showInputDialog("Add a task:");

                if (userInput != null) {
                    if (!userInput.isEmpty()) {
                        numberOfTasks++;
                        JLabel taskLabel = new JLabel(userInput);
                        taskLabel.setLayout(new BorderLayout());
                        JCheckBox checkBox = new JCheckBox();
                        checkBox.setPreferredSize(new Dimension(40, 40));
                        taskLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
                        taskLabel.setForeground(Color.black);
                        taskLabel.setBackground(Color.lightGray);
                        taskLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                        taskLabel.add(checkBox, BorderLayout.EAST);
                        taskPanel.add(taskLabel);
                        frame.revalidate(); 
                    }
            if(numberOfTasks == 10){
            addTaskButton.setEnabled(false);
            JOptionPane.showMessageDialog(null,"This application doesn't support more than 10 tasks :(","Warning!!",JOptionPane.INFORMATION_MESSAGE,null);
        }
    } 
        }
        });

        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear all tasks
                taskPanel.removeAll();
                taskPanel.revalidate();
                taskPanel.repaint();
                numberOfTasks = 0;
            }
        });

        buttonPanel.add(addTaskButton, BorderLayout.CENTER);
        buttonPanel.add(clearAll, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(800, 700);
        frame.setVisible(true);
    }
}
