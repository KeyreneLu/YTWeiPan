package com.jdyy.ytwp.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class TextDealUtils {
    /**
     * 对手机号进行处理，用星号代替号码中的一些数字
     *
     * @param startposition 开始位置
     * @param endposition   结束位置
     * @param str           手机号
     * @return
     */
    public static String PhoneDeal(int startposition, int endposition, String str) {
        int gap;
        String star = "";
        gap = endposition - startposition;
        for (int i = 0; i < gap; i++) {
            star = star + "*";
        }
        String maskNumber = str.substring(0, startposition) + star + str.substring(endposition, str.length());
        return maskNumber;
    }
    /**
     * 对银行卡号进行处理
     *
     * @param str 银行卡号
     * @return 处理后的银行卡号
     */
    public static String CardDeal(String str) {
        List<String> cards = new ArrayList<>();
        String BankCard = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == 3) {
                cards.add(str.substring(0, i));
            } else if (i == 7) {
                cards.add(str.substring(3, i));
            } else if (i == 11) {
                cards.add(str.substring(7, i));
            } else if (i == 15) {
                if (str.length() - 1 > i) {
                    cards.add(str.substring(11, i));
                    cards.add(str.substring(i, str.length() - 1));
                } else {
                    cards.add(str.substring(11, i));
                }
            }
        }
        for (int j = 0; j < cards.size(); j++) {
            if (j == cards.size() - 1) {
                BankCard = BankCard + cards.get(j) + "";
            } else {
                BankCard = BankCard + "****" + " ";
            }
        }
        return BankCard;
    }
    /**
     * 将金钱数字格式化，末尾带两位小数
     * @param str 传入的数字
     * @return
     */
    public static String MoneyDeal(String str) {
        double number = Double.valueOf(str);
        DecimalFormat df = new DecimalFormat("#.00");
        return String.valueOf(df.format(number));
    }
}
