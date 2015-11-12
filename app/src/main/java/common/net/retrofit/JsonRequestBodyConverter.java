package common.net.retrofit;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okio.Buffer;
import retrofit.Converter;

/**
 * Created by suli on 2015/11/12.
 */
public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("multipart/form-data; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Type type;

    public JsonRequestBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        return null;
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        try {
            // TODO
//            writer.write(JSONObject.toJSONString());
//            JSONObject.toJSON(value, type).;

            writer.flush();
        } catch (IOException e) {
            throw new AssertionError(e); // Writing to Buffer does no I/O.
        }
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}
