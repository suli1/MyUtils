package common.net.retrofit;

import common.net.retrofit.request.LoginRequest;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by suli on 2015/11/12.
 */
public interface AccountAPI {

    @POST("menusv3/v3/account/login")
    Observable<String> login(@Body LoginRequest request);
}
