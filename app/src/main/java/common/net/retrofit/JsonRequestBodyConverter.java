package common.net.retrofit;

import com.google.gson.Gson;
import com.squareup.okhttp.MultipartBuilder;
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
    //    private static final MediaType MEDIA_TYPE = MediaType.parse("multipart/form-data; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final Type type;

    public JsonRequestBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        try {
//            gson.toJson(value, type, writer);
            writer.write(value.toString());
            writer.flush();
        } catch (IOException e) {
            throw new AssertionError(e); // Writing to Buffer does no I/O.
        }
        return RequestBody.create(MultipartBuilder.FORM, buffer.readByteString());
    }
}
