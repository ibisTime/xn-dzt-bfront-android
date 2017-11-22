package com.cdkj.baselibrary.appmanager;


import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cdkj.baselibrary.BaseApplication;
import com.cdkj.baselibrary.utils.SPUtils;
import com.cdkj.baselibrary.utils.ToastUtil;

/**
 * SPUtils 工具辅助类
 */

public class SPUtilHelpr {

	private static final String USERTOKEN="user_toke_hydz";
	private static final String USERID="user_id_hydz";
	private static final String LOCATIONINFO="location_info_hydz";

	/**
	 * 设置用户token
	 * @param s
	 */
	public static void saveUserToken(String s)
	{
		SPUtils.put(BaseApplication.getContext(),USERTOKEN,s);
	}

/**
	 * 设置用户token
	 * @param
	 */
	public static String getUserToken()
	{
     	return  SPUtils.getString(BaseApplication.getContext(),USERTOKEN,"");
	}


	/**
	 * 设置用户token
	 * @param s
	 */
	public static void saveUserId(String s)
	{
		SPUtils.put(BaseApplication.getContext(),USERID,s);
	}
	/**
	 * 设置用户手机号码
	 * @param s
	 */
	public static void saveUserPhoneNum(String s)
	{
		SPUtils.put(BaseApplication.getContext(),"user_phone",s);
	}
	/**
	 * 获取用户手机号
	 */
	public static String getUserPhoneNum()
	{
	return 	SPUtils.getString(BaseApplication.getContext(),"user_phone","");
	}

	/**
	 * 设置用户手机号码
	 *
	 * @param s
	 */
	public static void saveUserName(String s) {
		SPUtils.put(BaseApplication.getContext(), "user_name", s);
	}

	/**
	 * 获取用户手机号
	 */
	public static String getUserName() {
		return SPUtils.getString(BaseApplication.getContext(), "user_name", "");
	}

	/**
	 * 设置用户真实姓名
	 *
	 * @param s
	 */
	public static void saveRealName(String s) {
		SPUtils.put(BaseApplication.getContext(), "real_name", s);
	}

	/**
	 * 获取用户真实姓名
	 */
	public static String getRealName() {
		return SPUtils.getString(BaseApplication.getContext(), "real_name", "");
	}

	/**
	 * 设置用户支付密码Flag
	 *
	 * @param s
	 */
	public static void saveTradePwdFlag(boolean s) {
		SPUtils.put(BaseApplication.getContext(), "trade_pwd", s);
	}

	/**
	 * 获取用户支付密码Flag
	 */
	public static boolean getTradePwdFlag() {
		return SPUtils.getBoolean(BaseApplication.getContext(), "trade_pwd", false);
	}

	/**
	 * 设置用户token
	 * @param
	 */
	public static String getUserId()
	{
	  return SPUtils.getString(BaseApplication.getContext(),USERID,"");

	}

	/**
	 * 保存选择定位信息
	 * @param s
	 */
	public static void saveRestLocationInfo(String s)
	{
		SPUtils.put(BaseApplication.getContext(),"LOCATIONINFRESET",s);
	}


	/**
	 * 设置订单modelCode
	 *
	 * @param s
	 */
	public static void saveModelCode(String s) {
		SPUtils.put(BaseApplication.getContext(), "model_code", s);
	}

	/**
	 * 获取订单modelCode
	 */
	public static String getModelCode() {
		return SPUtils.getString(BaseApplication.getContext(), "model_code","");
	}

	/**
	 * 判断用户是否登录
	 * @return
	 */
	public static boolean isLogin(Context context,boolean canopenmain){
		if(TextUtils.isEmpty(getUserToken())){
			SPUtilHelpr.logOutClear();
			ToastUtil.show(context,"请先登录");
			// 路由跳转登录页面
			ARouter.getInstance().build("/user/login")
					.withBoolean("canOpenMain",canopenmain)
					.navigation();
			return false;
		}

		return true;
	}

	/**
	 * 判断用户是否登录
	 * @return
	 */
	public static boolean isLoginNoStart(){
		if(TextUtils.isEmpty(getUserToken())){
			return false;
		}
		return true;
	}


	/**
	 * 退出登录清除数据
	 */
	public static void logOutClear(){
		saveUserToken("");
		saveUserId("");
		saveUserPhoneNum("");
		saveUserName("");
		saveTradePwdFlag(false);
	}

}
