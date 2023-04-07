package view.dashboard;

import java.awt.*;

import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class CalendarCustom extends RoundedPanel {

    LocalDate now;
    private int numOfDays;
    private int remaining;
    private GridBagConstraints gbc;

    public CalendarCustom() {
        super(30, 30);

        setBackground(new Color(232, 240, 238));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        gbc = new GridBagConstraints();
        now = LocalDate.now();

        setMonth();
        setWeek();
        setDays();
    }

    public void setMonth() {
        String monthName = now.format(DateTimeFormatter.ofPattern("MMMM"));
        JPanel c = new JPanel();
        c.setBackground(new Color(232, 240, 238));
        JLabel month = new JLabel(monthName);
        c.add(month);
        month.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 17));
        month.setForeground(new Color(53, 79, 82));

        add(c);
    }

    public void setWeek() {
        String[] daysOfWeek = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        JPanel week = new JPanel();
        week.setBackground(new Color(232, 240, 238));
        week.setLayout(new GridLayout(0, 7));
        for(int i = 0; i < daysOfWeek.length; i++) {
            JLabel weekName = new JLabel(daysOfWeek[i]);
            setStyle(weekName);
            week.add(weekName);

        }

        add(week);
    }

    public void setDays() {
        DayOfWeek firstOfMonth = now.withDayOfMonth(1).getDayOfWeek();
        numOfDays = now.lengthOfMonth();


        String[] dayOfWeek = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        int start = 0;
        for(int i = 0; i < 7; i++) {
            if(dayOfWeek[i] == firstOfMonth.toString()) {
                start = i;
            }
        }

        setRows(start);


    }

    public void setRows(int start) {
        remaining = start;
        int j = 1;
        JPanel rows = new JPanel();
        rows.setBackground(new Color(232, 240, 238));
        rows.setLayout(new GridLayout(6, 7));
        for(int i = 0; i < numOfDays + remaining; i++) {
            JLabel day = new JLabel();
            if(i == start) {
                day.setText(Integer.toString(j++));
                start++;
            }

            setStyle(day);
            rows.add(day);
        }

        add(rows);
    }

    public void setStyle(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
        label.setForeground(new Color(53, 79, 82));

        if(label.getText().contentEquals(Integer.toString(now.getDayOfMonth()))) {
            label.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        }
    }

}
