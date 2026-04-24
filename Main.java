import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        new ToDoApp();
    }
}

class ToDoApp extends JFrame {

    private DefaultListModel<String> taskModel;
    private JList<String> taskList;
    private JTextField taskField;

    public ToDoApp() {
        setTitle("To-Do Manager");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Тапсырмалар моделі
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);

        // Скролл
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Төменгі панель
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        taskField = new JTextField();
        JButton addButton = new JButton("Қосу");
        JButton deleteButton = new JButton("Өшіру");
        JButton doneButton = new JButton("Орындалды");

        // Батырмалар панелі
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(doneButton);

        panel.add(taskField, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // --- ACTIONS ---

        // Қосу
        addButton.addActionListener(e -> {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                taskModel.addElement(task);
                taskField.setText("");
            }
        });

        // Өшіру
        deleteButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                taskModel.remove(selected);
            }
        });

        // Орындалды
        doneButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                String task = taskModel.get(selected);
                taskModel.set(selected, "✔ " + task);
            }
        });

        setVisible(true);
    }
}
