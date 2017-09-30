package com.cdkj.hydz.module.order.helper;

import com.cdkj.hydz.module.model.HPlusCraftModel;
import com.cdkj.hydz.module.model.ManModel;
import com.cdkj.hydz.module.model.OrderDetailModel;
import com.cdkj.hydz.module.model.OrderProcessModel;
import com.cdkj.hydz.module.model.SystemParameterModel;
import com.cdkj.hydz.module.model.UserParameterModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderHelper {

    // 订单数据类
    public static OrderDetailModel orderDeatilModel;
    // 用户数据类
    public static ManModel manModel;

    // 参数对比类
    public static OrderProcessModel orderProcessModel;
    public static UserParameterModel userParameterModel;
    public static SystemParameterModel systemParameterModel;

    // 产品以选择工艺
    public static HPlusCraftModel hPlusCraftModel;

    // 是否需要在选择工艺时加上价格
    public static boolean isShowCraftPrice = false;

    // 是否是订单详情界面
    public static boolean isOrder = true;

    public static String getStatus(String status) {
        String format = "";
        switch (status){
            case "1": // 待量体
                format = "待量体";
                break;

            case "2": // 待支付
                format = "待支付";
                break;

            case "3": // 已支付
                format = "已支付";
                break;

            case "4": // 待复核
                format = "待复核";
                break;

            case "5": // 待生产
                format = "待生产";
                break;

            case "6": // 生产中
                format = "生产中";
                break;

            case "7": // 已发货
                format = "已发货";
                break;

            case "8": // 已收货
                format = "已收货";
                break;

            case "9": // 已评价
                format = "已评价";
                break;

            case "10": // 已归档
                format = "已归档";
                break;

            case "11": // 取消订单
                format = "取消订单";
                break;

            default:
                format = "";
                break;

        }

        return format;
    }

    public static String getType(String type) {
        String format = "";
        switch (type){
            case "0": // 衬衫
                format = "衬衫";
                break;

            case "1": // H+
                format = "H+";
                break;

            default:
                format = "";
                break;

        }

        return format;
    }

    public static String getValue(String key,String json){

        String value = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            value = jsonObject.get(key).toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }


    public static String getBodyValue(String key){
        String v;
        String value = "";

        switch (key){
            case "4-01":
                v = userParameterModel.get_$401();
                break;

            case "4-02":
                v = userParameterModel.get_$402();
                break;

            case "4-03":
                v = userParameterModel.get_$403();
                break;

            case "4-04":
                v = userParameterModel.get_$404();
                break;

            case "4-05":
                v = userParameterModel.get_$405();
                break;

            case "4-06":
                v = userParameterModel.get_$406();
                break;

            case "4-07":
                v = userParameterModel.get_$407();
                break;

            case "4-08":
                v = userParameterModel.get_$408();
                break;

            case "4-09":
                v = userParameterModel.get_$409();
                break;

            case "4-10":
                v = userParameterModel.get_$410();
                break;

            case "4-11":
                v = userParameterModel.get_$411();
                break;

            case "4-12":
                v = userParameterModel.get_$412();
                break;

            default:
                v ="";
                break;

        }

        if(!v.equals("")){
            try {
                JSONObject object = new JSONObject(v);
                Iterator it = object.keys();
                while (it.hasNext()){
                    String k = (String) it.next();
                    value += object.getString(k) +",";
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return value;
    }

    public static String getBodyValueKey(String key){
        String v;
        String value = "";

        switch (key){
            case "4-01":
                v = userParameterModel.get_$401();
                break;

            case "4-02":
                v = userParameterModel.get_$402();
                break;

            case "4-03":
                v = userParameterModel.get_$403();
                break;

            case "4-04":
                v = userParameterModel.get_$404();
                break;

            case "4-05":
                v = userParameterModel.get_$405();
                break;

            case "4-06":
                v = userParameterModel.get_$406();
                break;

            case "4-07":
                v = userParameterModel.get_$407();
                break;

            case "4-08":
                v = userParameterModel.get_$408();
                break;

            case "4-09":
                v = userParameterModel.get_$409();
                break;

            case "4-10":
                v = userParameterModel.get_$410();
                break;

            case "4-11":
                v = userParameterModel.get_$411();
                break;

            case "4-12":
                v = userParameterModel.get_$412();
                break;

            default:
                v ="";
                break;

        }

        if(!v.equals("")){
            try {
                JSONObject object = new JSONObject(v);
                Iterator it = object.keys();
                while (it.hasNext()){
                    String k = (String) it.next();
                    value += k +",";
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return value;
    }
}
