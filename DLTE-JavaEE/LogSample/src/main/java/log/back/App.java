package log.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ){
        Logger logger = LoggerFactory.getLogger(App.class);
        for(int i=0;i<20;i++){
            logger.info(i+" position");
        }
    }
}
