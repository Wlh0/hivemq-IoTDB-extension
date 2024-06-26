package hivemq;

import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Util {

    public static String getStringFromByteBuffer(final @NotNull ByteBuffer buffer){
        if (buffer == null){
            return null;
        }
        final byte[] bytes = new byte[buffer.remaining()];
        for (int i = 0; i < buffer.remaining(); i++) {
            bytes[i] = buffer.get(i);
        }
        Charset charset = Charset.forName("GBK");
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        CharBuffer charBuffer = charset.decode(buf);
        return charBuffer.toString();
    }
}
