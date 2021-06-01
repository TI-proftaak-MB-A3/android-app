package nl.avans.ti.MQTT;

public class CodeDecryption {
    private int code;
    private String attraction;
    private String question;
    private String combination;


    public CodeDecryption(int code) {
        this.code = code;
        this.attraction = "";
        this.question = "";
        this.combination = "";
    }

    public void decryptCode(){
        String stringCode = String.valueOf(code);

        this.attraction = "" + stringCode.charAt(0) + stringCode.charAt(1);
        this.question = "" + stringCode.charAt(2) + stringCode.charAt(3);
        this.combination = "" + stringCode.charAt(4) + stringCode.charAt(5);
    }

    public int getCode() {
        return code;
    }

    public String getAttraction() {
        return attraction;
    }

    public String getQuestion() {
        return question;
    }

    public String getCombination() {
        return combination;
    }
}
