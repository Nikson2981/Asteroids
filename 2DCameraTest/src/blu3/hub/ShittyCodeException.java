package blu3.hub;

// i write shit code
// the code for this exception is ironically shit
public class ShittyCodeException extends Exception {

    static final long serialVersionUID = 456329988775434L;
    
    public ShittyCodeException(String text) {
        super(text);
    }
   
    public void post() {
        RuntimeException up = new RuntimeException(this);
        Logger.ERROR("[FATAL] ShittyCodeException thrown! Shutting down...");
        up.printStackTrace();
        System.exit(1);
        throw up;
    }
}
