package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    public static final Logger logger = LoggerFactory.getLogger("root");
    public static void INFO(String str, Object... objects) {
        logger.info(str, objects);
    }
}
