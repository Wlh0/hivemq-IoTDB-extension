package hivemq;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import org.apache.iotdb.isession.util.Version;
import org.apache.iotdb.rpc.IoTDBConnectionException;
import org.apache.iotdb.session.Session;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HiveMQToIoTDB {

    private static final @NotNull Logger log = LoggerFactory.getLogger(HiveMQToIoTDB.class);
    private static final int SUCCESS_CODE = 200;
    private Config config;
    private Session session;
    private final Lock lock = new ReentrantLock();


    /**
     * 构造方法，加载配置文件
     */
    public HiveMQToIoTDB(String configPath) throws Exception{
        // 加载配置文件
        File file = new File(configPath);
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element element = document.getRootElement();
        // 核查配置文件
        checkConfig(element);
        // 连接session
        session = new Session.Builder()
                .host(config.getIP())
                .port(Integer.parseInt(config.getPORT()))
                .username(config.getUSERNAME())
                .password(config.getPASSWORD())
                .version(Version.valueOf(config.getVERSION()))
                .build();
        try {
            session.open(false);
        }catch (IoTDBConnectionException e){
            throw new RuntimeException(e);
        }
    }

    //TODO：判断配置文件是否为空，为空加载默认值
    public void checkConfig(Element element){

    }

}
