package com.szr.jkxsx.teacher.utils;

/**
 * Created by zcZhang on 15/3/19.
 */
public class Utils {
    public static final boolean initDebugCard = false;
    public static final boolean errorJump = false;

    /**
     * 对话题距离格式化，数值和单位用逗号分隔
     *
     * @param dis
     * @return
     */
    public static String formatTopicDistance(int dis) {
        StringBuilder result = new StringBuilder();
        if (dis < 1000) {//小于1000米
            if (dis < 10) {
                result.append(dis).append(",").append("m");
            } else {
                result.append(dis / 10).append(0).append(",").append("m");
            }
        } else {
            result.append(dis / 1000).append(".").append((dis - 1000) / 10).append(",").append("km");
        }
        return result.toString();
    }

}
