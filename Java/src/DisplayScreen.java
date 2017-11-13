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
        if (this.currentText == "INSERT COIN" || this.currentText == "EXACT CHANGE ONLY") {
            if (canMakeChange) {
                this.currentText = "INSERT COIN";
            } else {
                this.currentText = "EXACT CHANGE ONLY";
            }
        }
    }


    public String getCurrentText() {
        return this.currentText;
    }


    public void toggleSoldOut(double currentCoinsValue, boolean canMakeChange) {
        if(this.currentText.contains("$") || this.currentText == "INSERT COIN" || this.currentText == "EXACT CHANGE ONLY"){
            this.currentText = "SOLD OUT";
        }
        else if(this.currentText == "SOLD OUT") {
            if(currentCoinsValue > 0) {
                this.setCoinDisplay(currentCoinsValue);
            }
            else if(canMakeChange) {
                this.currentText = "INSERT COIN";
            }
            else {
                this.currentText = "EXACT CHANGE ONLY";
            }
        }
    }


    public void togglePrice(Double price, double currentCoinsValue, boolean canMakeChange) {
        if(this.currentText.contains("PRICE")){
            if(currentCoinsValue > 0) {
                this.setCoinDisplay(currentCoinsValue);
            }
            else if(canMakeChange) {
                this.currentText = "INSERT COIN";
            }
            else{
                this.currentText = "EXACT CHANGE ONLY";
            }
        }

        else {
            this.setCoinDisplay(price);
            this.currentText = "PRICE " + this.currentText;
        }
    }
}