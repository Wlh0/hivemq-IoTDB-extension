package hivemq;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.async.Async;
import com.hivemq.extension.sdk.api.async.TimeoutFallback;
import com.hivemq.extension.sdk.api.interceptor.publish.PublishInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.publish.parameter.PublishInboundInput;
import com.hivemq.extension.sdk.api.interceptor.publish.parameter.PublishInboundOutput;
import com.hivemq.extension.sdk.api.packets.publish.ModifiablePublishPacket;
import com.hivemq.extension.sdk.api.services.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static hivemq.Util.getStringFromByteBuffer;

public class IoTDBInterceptor implements PublishInboundInterceptor {

    private final HiveMQToIoTDB ioTDB;
    private static final @NotNull Logger log = LoggerFactory.getLogger(HiveMQToIoTDB.class);
    public IoTDBInterceptor(HiveMQToIoTDB hiveMQToIoTDB) {
        this.ioTDB = hiveMQToIoTDB;
    }

    @Override
    public void onInboundPublish(@NotNull PublishInboundInput publishInboundInput, @NotNull PublishInboundOutput publishInboundOutput) {
        final Async<PublishInboundOutput> async = publishInboundOutput.async(Duration.ofSeconds(10), TimeoutFallback.FAILURE);
        final CompletableFuture<?> taskFuture = Services.extensionExecutorService().submit(() -> {
           final ModifiablePublishPacket publishPacket = publishInboundOutput.getPublishPacket();
           try {
               @NotNull Optional<ByteBuffer> payload = publishPacket.getPayload();
               if (payload.isPresent()){
                   final String payloadStr = getStringFromByteBuffer(payload.orElse(null));
                   if (payloadStr.equals("")){
                       return;
                   }
                   ioTDB.saveData(publishPacket.getTopic(), payloadStr.replace("'", "\\'"));
               }
           }catch (Exception e){
               log.error("save data to iotdb error", e);
           }
        });
        taskFuture.whenComplete((ignored, throwable) ->{
           if (throwable != null){
               log.error("iotdb save data error", throwable);
           }
           async.resume();
        });
    }
}
