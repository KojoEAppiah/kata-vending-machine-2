import java.text.DecimalFormat;

public class DisplayScreen {

    private String currentText;

    DisplayScreen(String text) {
        this.currentText = text;
    }

    public void setCurrentText(String text){
        this.currentText = text;
    }

    public void setCoinDisplay(double value){
        DecimalFormat formatter = new DecimalFormat("$0.00");
        this.currentText = formatter.format(value);
    }

    public void stateChangeCheck(boolean canMakeChange) {
        if (this.currentText == "INSERT COIN" || this.currentText == "EXACT CHANGE ONLY")
            if (canMakeChange)
                this.currentText = "INSERT COIN";
            else
                this.currentText = "EXACT CHANGE ONLY";
    }

    public String getCurrentText() {
        return this.currentText;
    }

}