package hivemq;

import com.hivemq.extension.sdk.api.ExtensionMain;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.parameter.*;
import com.hivemq.extension.sdk.api.services.Services;
import com.hivemq.extension.sdk.api.services.intializer.InitializerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class IoTDBExtension implements ExtensionMain {
    private HiveMQToIoTDB hiveMQToIoTDB;
    private static final @NotNull Logger log = LoggerFactory.getLogger(IoTDBExtension.class);

    @Override
    public void extensionStart(@NotNull ExtensionStartInput extensionStartInput, @NotNull ExtensionStartOutput extensionStartOutput) {
        try {
            final File path = extensionStartInput.getExtensionInformation().getExtensionHomeFolder();
//        加载配置文件
            hiveMQToIoTDB = new HiveMQToIoTDB(path.getAbsolutePath() + File.separator + "iotdb.xml");
            boolean success = hiveMQToIoTDB.connect();
            if (!success) throw new Exception("connect iotdb error");
//            添加拦截器
            addPublisherModifier();
            final ExtensionInformation extensionInformation = extensionStartInput.getExtensionInformation();
            log.info("Started " + extensionInformation.getName() + ":" + extensionInformation.getVersion());
        }catch (Exception e){
            log.error("Exception thrown at extension start + ", e);
        }

    }

    @Override
    public void extensionStop(@NotNull ExtensionStopInput extensionStopInput, @NotNull ExtensionStopOutput extensionStopOutput) {
        final ExtensionInformation extensionInformation = extensionStopInput.getExtensionInformation();
        log.info("Stopped " + extensionInformation.getName() + ":" + extensionInformation.getVersion());
        if (this.hiveMQToIoTDB != null){
            this.hiveMQToIoTDB.close();
        }
    }
    private void addPublisherModifier(){
        final InitializerRegistry initializerRegistry = Services.initializerRegistry();
        final IoTDBInterceptor ioTDBInterceptor = new IoTDBInterceptor(this.hiveMQToIoTDB);
        initializerRegistry.setClientInitializer((initializerInput, clientContext) -> clientContext.addPublishInboundInterceptor(ioTDBInterceptor));
    }
}
