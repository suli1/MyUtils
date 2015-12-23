package common.net.retrofit;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by suli on 2015/11/12.
 */
public interface AccountAPI {

    @FormUrlEncoded
    @POST("menusv3/v3/account/login")
    Observable<String> login(@Field("account_phone") String account, @Field("password") String password);


}
