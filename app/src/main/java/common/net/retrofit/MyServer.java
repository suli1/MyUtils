package common.net.retrofit;

import com.example.common.Contract;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by suli on 2015/11/12.
 */
public class MyServer {

    private static AccountAPI accountAPI;


    public static synchronized AccountAPI getAccountAPI() {
        if (accountAPI == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Contract.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(FastjsonConverterFactory.create())
                    .client(client)
                    .build();

            accountAPI = retrofit.create(AccountAPI.class);
        }
        return accountAPI;
    }

}
