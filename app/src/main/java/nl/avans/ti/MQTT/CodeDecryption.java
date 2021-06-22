package nl.avans.ti.MQTT;

public class CodeDecryption {
    private String code;
    private String attraction;
    private String question;
    private String combination;


    public CodeDecryption(String code) {
        this.code = code;
        this.attraction = "";
        this.question = "";
        this.combination = "";
        decryptCode();
    }

    //splits the code to get the right question for the attraction
    public void decryptCode(){
        String stringCode = String.valueOf(code);

        this.attraction = "" + stringCode.charAt(0) + stringCode.charAt(1); //which attraction
        this.question = "" + stringCode.charAt(2) + stringCode.charAt(3);   //which question from the attraction
        this.combination = "" + stringCode.charAt(4) + stringCode.charAt(5); // random number
    }

    public String getCode() {
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
