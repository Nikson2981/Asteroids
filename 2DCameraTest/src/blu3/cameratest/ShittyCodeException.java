package blu3.cameratest;


// There is no way this just works on the first try
// this was just written by directly editing the github page from google chrome on my phone
// this needs to change ASAP

public class ShittyCodeException extends Exception {
    
    // something about serialversionuid
    
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
