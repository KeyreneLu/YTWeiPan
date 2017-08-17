package com.jdyy.ytwp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将Linux时间戳转化为标准时间
 * Created by Administrator on 2016/9/6 0006.
 */
public class TimeUtils {
    /**
     * 时间戳转为年月日，时分秒
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }
}
