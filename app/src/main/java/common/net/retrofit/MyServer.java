package common.net.retrofit;

import com.example.common.Contract;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by suli on 2015/11/12.
 */
public class MyServer {


    public static AccountAPI getAccountAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contract.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(AccountAPI.class);
    }

}
