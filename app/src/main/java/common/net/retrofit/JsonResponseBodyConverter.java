package common.net.retrofit;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by suli on 2015/11/12.
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Type type;

    JsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        return JSONObject.parseObject(value.bytes(), type);
    }
}
