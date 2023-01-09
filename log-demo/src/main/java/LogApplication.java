import lombok.extern.slf4j.Slf4j;

/**
 * @author buhuan.wang
 * @since 2023/1/9
 */
@Slf4j
public class LogApplication {
    public static void main(String[] args) {
        Integer a = 5;
        log.info("a: {}", a);
        log.warn("a: {}", a);
        log.error("a: {}", a);

        try {
            Integer ret = devideTest(a);
            log.info("ret: {}", ret);
        } catch (Exception e) {
            log.error("error occurred. a:{}", a, e);
            log.error("==============================");
            log.warn("error occurred. a:{}", a, e);
            log.warn("==============================");
            log.info("error occurred. a:{}", a, e);
        }
    }

    public static Integer devideTest(Integer param) {
        Integer baseZero = 0;
        Integer ret = param / baseZero;
        return ret;
    }
}
