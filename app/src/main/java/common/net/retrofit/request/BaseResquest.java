package common.net.retrofit.request;

import common.utils.SystemUtil;

/**
 * Created by suli on 2/25/2016.
 */
public class BaseResquest {
    /**
     * 终端操作系统,默认为 maxLen:45
     **/
    public final String str_client_os = "android";

    /**
     * android OS版本号 maxLen:45
     **/
    public final String str_client_os_version = SystemUtil.getVersion();

    /**
     * 终端型号，如A001 maxLen:45
     **/
    public final String str_client_model_type = SystemUtil.getModel();

    /**
     * 分辨率 W*H maxLen:45
     **/
    public final String str_client_screen_resolution = SystemUtil.getScreenResolution();

    /**
     * 终端MAC地址 maxLen:45
     **/
    public final String str_client_mac = SystemUtil.getMac();

    /**
     * 语言 maxLen:45
     **/
    public final String str_client_language = SystemUtil.getLanguage();

    /**
     * app的版本 maxLen:45
     **/
    public final String str_client_app_version = SystemUtil.getVersionName();


    /**
     * 记录请求发送时间，onSuccess则更新
     * （防止重复提交请求的问题）
     * 可选
     */
    public Long nonce = null;

}
