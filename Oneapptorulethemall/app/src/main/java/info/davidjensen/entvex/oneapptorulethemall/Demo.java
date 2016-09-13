package info.davidjensen.entvex.oneapptorulethemall;

/**
 * Created by entvex on 10-09-2016.
 */
public class Demo {

    private String name;
    private String description;
    private String intentAction;
    private int resultCode;

    public Demo(String demoName, String demodescription){

        this(demoName, demodescription, null, 0);
    }

    public Demo(String demoName, String demodescription, String demoAction, int demoResultCode){

        this.name = demoName;
        this.description = demodescription;
        this.intentAction = demoAction;
        this.resultCode = demoResultCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntentAction() {
        return intentAction;
    }

    public void setIntentAction(String intentAction) {
        this.intentAction = intentAction;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}