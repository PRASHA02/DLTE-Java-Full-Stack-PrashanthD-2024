package java.loggers;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Loggers{
    static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public void makeSomeLog()
    {

        LOGGER.log(Level.INFO, "First log message");


    }
    public static void main(String[] args)
    {
        Loggers obj = new Loggers();
        obj.makeSomeLog();



        Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


        log.log(Level.INFO, "This is a log message");

    }
}
