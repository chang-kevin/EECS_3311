package helpers;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class userDOB{
    private String[] datesList;
    private String[] monthsShortList;
    private String[] years;
    private JComboBox dateBox;
    private JComboBox monthBox;
    private JComboBox yearBox;
    private JLabel dobLabel;

    public userDOB(){
        Bounds dobBounds = new Bounds(50, 200, 50, 25);
        dobLabel = ComponentFactory.generateLabelComponent("DOB", dobBounds);

        this.datesList = generateDates();
        Bounds datesBounds = new Bounds(100, 200, 100, 25);
        dateBox = generateJComboBox(datesList, datesBounds);
        
        this.monthsShortList = new DateFormatSymbols().getShortMonths();
        Bounds monthBounds = new Bounds(200, 200, 100, 25);
        monthBox = generateJComboBox(monthsShortList, monthBounds);

        this.years = generateYears();
        Bounds yearBounds = new Bounds(300, 200, 100, 25);
        yearBox = generateJComboBox(years, yearBounds);
    }

    /**
     * Generate the year list dynamically
     */
    public String[] generateYears(){
        int endYear = Calendar.getInstance().get(Calendar.YEAR);
        int difference = endYear - 1940;
        int startYear = 1940;
        String[] arr = new String[difference+1];
        arr[0] = " ";
        for (int i = 1; i < arr.length; i++){
            arr[i] = Integer.toString(startYear);
            startYear++;
        }
        return arr;
    }

    public String[] generateMonths(){
        String[] arr = new String[13];
        String[] arrNew = new DateFormatSymbols().getShortMonths();
        arr[0] = "0";
        for(int i = 1; i < arr.length; i++){
            arr[i] = arrNew[i - 1];
        }
        return arr;
    }

    /*
     * generateDates will generate the dates of the month from 1 - 31
     */
    private String[] generateDates(){
        String[] datesArr = new String[32];
       //Getting dates of the month (1 - 31)
        datesArr[0] = " ";
        for (int dates = 1; dates < datesArr.length; dates++){
            datesArr[dates] = Integer.toString(dates);
        }
        return datesArr;
    }
    
    /*
     * generate a combo box for the months, days, and years
     * @param arr will take in the list of options
     * @param bounds will take in the size of the combo box
     * @return will return the combo box generated
     */
    public JComboBox generateJComboBox (String[] arr, Bounds bounds){
        JComboBox comboBox = new JComboBox(arr);
        comboBox.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return comboBox;
    }

    public JComboBox getDateBox(){
        return this.dateBox;
    }

    public JComboBox getMonthBox(){
        return this.monthBox;
    }
    public JComboBox getYearBox(){
        return this.yearBox;
    }

    public JLabel getDobLabel(){
        return this.dobLabel;
    }

    public String getDateItem(){
        return (String) this.getDateBox().getSelectedItem();
    }

    public int getMonthItem(){
        return this.getMonthBox().getSelectedIndex();
    }

    public String getYearItem(){
        return (String) this.getYearBox().getSelectedItem();
    }
}
