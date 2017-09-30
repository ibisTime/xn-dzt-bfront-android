package com.cdkj.hydz.module.user.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.base.BaseRefreshActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.module.adapter.SystemMessageAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.MessageModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class SystemMessageActivity extends BaseRefreshActivity<MessageModel.ListBean> {

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, SystemMessageActivity.class));
    }

    @Override
    protected void onInit(Bundle savedInstanceState, int pageIndex, int limit) {
        setTopTitle("系统消息");
        setSubLeftImgState(true);

        getListData(pageIndex,limit,true);
    }

    @Override
    protected void getListData(int pageIndex, int limit, boolean canShowDialog) {


        Map<String, String> map = new HashMap<>();
        map.put("limit", limit+"");
        map.put("start", pageIndex+"");
        map.put("fromSystemCode", MyConfig.SYSTEMCODE);
        map.put("channelType", "4");
        map.put("pushType", "");
        map.put("toSystemCode", MyConfig.SYSTEMCODE);
        map.put("toKind", "2");
        map.put("status", "1");

        Call call = RetrofitUtils.createApi(MyApiServer.class).getMessageListData("804040", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<MessageModel>(this) {


            @Override
            protected void onSuccess(MessageModel data, String SucMessage) {
                Log.e("data.getList()",data.getList().size()+"");
                setData(data.getList());
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<MessageModel.ListBean> mDataList) {
        return new SystemMessageAdapter(mDataList);
    }

    @Override
    public String getEmptyInfo() {
        return "暂无系统消息";
    }

    @Override
    public int getEmptyImg() {
        return 0;
    }
}
