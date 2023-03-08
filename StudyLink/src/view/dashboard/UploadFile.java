package view.dashboard;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class UploadFile extends JPanel {

    JPanel upload;
    private JPanel panel;
    private JTextField textField;
    private JPanel borderPanel;
    private JPanel dropPanel;
    private JPanel panelContainer;


    public UploadFile() {


        createPanel();
        createBorderPanels();
        pageTitle();
        createLabels();
        createInputField();
        fileChooser();
        displayFileName();
        uploadIcon();

    }


    public void createPanel() {
        upload = new JPanel();
        upload = createUpload();
    }

    public void createBorderPanels() {
        panelContainer = new JPanel();
        panelContainer.setBackground(new Color(244, 181, 181));
        panelContainer.setBounds(20, 20, 497, 308);
        panelContainer.setOpaque(true);
        upload.add(panelContainer);
        panelContainer.setLayout(null);

        borderPanel = new JPanel();
        borderPanel.setBackground(new Color(255, 255, 255));
        borderPanel.setBounds(10, 170, 477, 127);
        panelContainer.add(borderPanel);
        borderPanel.setLayout(null);

        dropPanel = new JPanel();
        dropPanel.setBackground(new Color(255, 255, 255));
        dropPanel.setBounds(10, 11, 457, 105);
        dropPanel.setBorder(BorderFactory.createDashedBorder(Color.black));
        borderPanel.add(dropPanel);
        dropPanel.setLayout(null);
    }

    public void pageTitle() {
        JLabel pageTitle = new JLabel();
        pageTitle.setBounds(10, 0, 111, 33);
        panelContainer.add(pageTitle);
        pageTitle.setForeground(new Color(255, 255, 255));
        pageTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        pageTitle.setText("Upload File");

    }

    public void createLabels() {
        JLabel codeText = new JLabel("Course Code");
        codeText = textLayout(codeText, 40);

        JLabel topicText = new JLabel("Topic");
        topicText = textLayout(topicText, 70);

        JLabel titleText = new JLabel("Title");
        titleText = textLayout(titleText, 100);
    }

    public JLabel textLayout(JLabel label, int y) {
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        label.setBounds(20, y, 81, 14);
        panelContainer.add(label);

        return label;
    }

    public void createInputField() {
        JPanel courseCodeList = new JPanel();
        courseCodeList.setBackground(new Color(255, 255, 255));
        courseCodeList.setBounds(154, 40, 104, 20);
        panelContainer.add(courseCodeList);

        JPanel topicList = new JPanel();
        topicList.setBackground(new Color(255, 255, 255));
        topicList.setBounds(154, 70, 104, 20);
        panelContainer.add(topicList);

        textField = new JTextField();
        textField.setBounds(154, 100, 104, 20);
        panelContainer.add(textField);
        textField.setColumns(10);
    }

    public void displayFileName() {
        JLabel fileName = new JLabel("//dsiplay file name here");
        fileName.setForeground(new Color(255, 255, 255));
        fileName.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
        fileName.setBounds(154, 135, 149, 14);
        panelContainer.add(fileName);

        JButton btnNewButton = new JButton("Submit");
        btnNewButton.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
        btnNewButton.setBorderPainted(false);
        btnNewButton.setBackground(new Color(197, 230, 193));
        btnNewButton.setFocusPainted(false);
        btnNewButton.setFocusTraversalPolicyProvider(true);
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBounds(383, 145, 93, 25);
        panelContainer.add(btnNewButton);
    }

    public void fileChooser() {
        JButton chooseFileBtn = new JButton("Choose File");
        chooseFileBtn.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
        chooseFileBtn.setBorder(null);
        chooseFileBtn.setFocusPainted(false);
        chooseFileBtn.setForeground(new Color(255, 255, 255));
        chooseFileBtn.setBackground(new Color(197, 230, 193));
        chooseFileBtn.setBounds(20, 130, 81, 23);
        panelContainer.add(chooseFileBtn);
    }

    public void uploadIcon() {


        String path = "/document.png";
        JLabel container = new JLabel();
        container.setBorder(null);
        container.setBounds(201, 11, 54, 55);

        ImageIcon image = new ImageIcon(getClass().getResource(path));
        Image newImage = getScaledImage(image.getImage(), container.getWidth(), container.getHeight());
        ImageIcon icon = new ImageIcon(newImage);

        container.setIcon(icon);
        dropPanel.add(container);

        JLabel lblNewLabel = new JLabel("Drag files here to upload");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
        lblNewLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        lblNewLabel.setForeground(new Color(192, 192, 192));
        lblNewLabel.setBounds(107, 75, 253, 26);
        dropPanel.add(lblNewLabel);



    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 2, 1, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public JPanel createUpload() {
        upload = panelBorder(panel);
        upload.setBounds(169, 203, 537, 348);

        return upload;
    }


    public JPanel panelBorder(JPanel panel) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(50, 50);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }
        };



        panel.setForeground(new Color(239, 239, 239, 75));
        panel.setBackground(new Color(239, 239, 239, 75));
        panel.setBorder(null);
        panel.setOpaque(false);
        panel.setLayout(null);




        return panel;
    }
}
