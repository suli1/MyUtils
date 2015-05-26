package common.utils;

import com.suli.myutils.GlobalContext;

/**
 * Created by suli on 2015/4/24.
 */
public class DisplayUtil {
    /*
	 * 将px值转换为dip或dp
	 */

    public static int px2dip(float pxValue) {
        final float scale = GlobalContext.getInstance().getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    /*
     * 将dip转换为px
     */
    public static int dip2px(float dipValue) {
        final float scale = GlobalContext.getInstance().getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    /*
     * 将px转换为sp
     */
    public static int px2sp(float pxValue) {
        final float fontScale = GlobalContext.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5f);
    }

    /*
     * 将sp转换为px
     */
    public static int sp2px(float spValue) {
        final float fontScale = GlobalContext.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5f);
    }
}
