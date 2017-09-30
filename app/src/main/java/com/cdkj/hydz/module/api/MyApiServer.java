package com.cdkj.hydz.module.api;

import com.cdkj.baselibrary.api.BaseResponseListModel;
import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.hydz.module.model.AccountModel;
import com.cdkj.hydz.module.model.BankModel;
import com.cdkj.hydz.module.model.BannerModel;
import com.cdkj.hydz.module.model.BillModel;
import com.cdkj.hydz.module.model.CardModel;
import com.cdkj.hydz.module.model.FHYModel;
import com.cdkj.hydz.module.model.LogisticsModel;
import com.cdkj.hydz.module.model.ManListModel;
import com.cdkj.hydz.module.model.ManModel;
import com.cdkj.hydz.module.model.MessageModel;
import com.cdkj.hydz.module.model.NoticeModel;
import com.cdkj.hydz.module.model.OrderDetailModel;
import com.cdkj.hydz.module.model.OrderMaterialModel;
import com.cdkj.hydz.module.model.OrderModel;
import com.cdkj.hydz.module.model.OrderProcessModel;
import com.cdkj.hydz.module.model.ProductCraftModel;
import com.cdkj.hydz.module.model.ProductModel;
import com.cdkj.hydz.module.model.ServiceDataModel;
import com.cdkj.hydz.module.model.SystemParameterModel;
import com.cdkj.hydz.module.model.UserInfoModel;
import com.cdkj.hydz.module.model.UserParameterModel;
import com.cdkj.hydz.module.model.WithdrawModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lei on 2017/8/21.
 */

public interface MyApiServer {

    /**
     * //获取订单列表
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<OrderModel>> getOrderData(@Field("code") String code, @Field("json") String json);

    /**
     * //获取轮播图
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<BannerModel>> getBannerData(@Field("code") String code, @Field("json") String json);

    /**
     * //获取轮播图
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<NoticeModel>> getNoticeListData(@Field("code") String code, @Field("json") String json);

    /**
     * 系统消息
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<MessageModel>> getMessageListData(@Field("code") String code, @Field("json") String json);

    /**
     *获取用户信息详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<UserInfoModel>> getUserInfoDetails(@Field("code") String code, @Field("json") String json);

    /**
     *获取用户信息详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ManListModel>> getManListDeta(@Field("code") String code, @Field("json") String json);

    /**
     *获取用户账户
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<AccountModel>> getUserAccountDetails(@Field("code") String code, @Field("json") String json);

    /**
     *获取用户账户
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<WithdrawModel>> getWithdrawAccount(@Field("code") String code, @Field("json") String json);

    /**
     *获取用户账户
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<BillModel>> getBillListData(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<CardModel>> getCardistData(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行渠道
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<BankModel>> getBankListData(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<CardModel.ListBean>> getCardDetails(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<String>> getCardSuccess(@Field("code") String code, @Field("json") String json);

    /**
     * //添加银行卡
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<WithdrawModel>> getWithdrawTip(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<OrderDetailModel>> getOrderDetails(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ManModel>> getManDetails(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<SystemParameterModel>> getSystemParameterDetails(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<FHYModel>> getFHY(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<UserParameterModel>> getUserParameterDetails(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<OrderProcessModel>> getProcessList(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<OrderMaterialModel>> getMaterialList(@Field("code") String code, @Field("json") String json);

    /**
     * //获取产品工艺
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ProductCraftModel>> getProductCraftList(@Field("code") String code, @Field("json") String json);

    /**
     * //获取银行卡详情
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<ProductModel>> getProductList(@Field("code") String code, @Field("json") String json);


    /**
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ServiceDataModel>> getServiceData(@Field("code") String code, @Field("json") String json);

    /**
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<LogisticsModel>> getLogisticsData(@Field("code") String code, @Field("json") String json);
}
