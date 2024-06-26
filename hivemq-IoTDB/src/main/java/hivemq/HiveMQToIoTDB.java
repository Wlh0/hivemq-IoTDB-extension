package hivemq;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import org.apache.iotdb.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveMQToIoTDB {

    private static final @NotNull Logger log = LoggerFactory.getLogger(HiveMQToIoTDB.class);
    private static final int SUCCESS_CODE = 200;
    private String ip;
    private String port;
    private String username;
    private String password;
    private String type;



    /**
     * 构造方法，加载配置文件
     */
    public HiveMQToIoTDB(String path){

    }
    /**
     * 获取IoTDBSession连接
     */
    public Session getSession(){
        return null;
    }

    public boolean connect() {
        return true;
    }

    public void close() {
    }

    public void saveData(String topic, String replace) {
    }
}
