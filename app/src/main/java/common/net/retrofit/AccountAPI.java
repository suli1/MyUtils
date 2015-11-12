package common.net.retrofit;

import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import rx.Observable;

/**
 * Created by suli on 2015/11/12.
 */
public interface AccountAPI {

    @Multipart
    @POST("/login")
    Observable<String> login(@Part("account") String account, @Part("password") String password);

}
