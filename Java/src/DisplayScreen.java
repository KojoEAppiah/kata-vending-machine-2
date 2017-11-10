public class DisplayScreen {

    private String currentText;

    DisplayScreen(String text) {
        this.currentText = text;
    }

    public void setCurrentText(String text){
        this.currentText = text;
    }

    public String getCurrentText() {
        return this.currentText;
    }

}