package common.net.retrofit;

import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by suli on 2015/11/12.
 */
public class MenuConverterFactory extends Converter.Factory {

    private final Gson gson;

    public static MenuConverterFactory create() {
        return new MenuConverterFactory(new Gson());
    }

    private MenuConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new JsonResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
//        return super.toRequestBody(type, annotations);
        return new JsonRequestBodyConverter<>(gson, type);
    }
}
