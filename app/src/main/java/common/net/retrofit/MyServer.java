package common.net.retrofit;

import com.example.common.Contract;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import common.utils.SystemUtil;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by suli on 2015/11/12.
 */
public class MyServer {

    private static AccountAPI accountAPI;


    public static AccountAPI getAccountAPI() {
        if (accountAPI == null) {
            OkHttpClient client = new OkHttpClient();
            client.networkInterceptors().add(new StethoInterceptor());
            client.interceptors().add(new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    RequestBody requestBody = new MultipartBuilder()
                            .type(MultipartBuilder.FORM)
                            .addFormDataPart("str_client_os", "android")
                            .addFormDataPart("str_client_os_version", SystemUtil.getVersion())
                            .addFormDataPart("str_client_model_type", SystemUtil.getModel())
                            .addFormDataPart("str_client_screen_resolution", SystemUtil.getScreenResolution())
                            .addFormDataPart("str_client_mac", SystemUtil.getLanguage())
                            .addFormDataPart("str_client_language", SystemUtil.getLanguage())
                            .addFormDataPart("str_client_app_version", SystemUtil.getVersionName())
                            .addPart(request.body())
//                            .addFormDataPart("account_phone", "18664519382")
//                            .addFormDataPart("password", "9266b2534b0fe12c1210d87c97b86c4cff713487ee51531e")
                            .build();
                    request = request.newBuilder().post(requestBody).build();
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Contract.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(MenuConverterFactory.create())
                    .client(client)
                    .build();

            accountAPI = retrofit.create(AccountAPI.class);
        }
        return accountAPI;
    }

}
