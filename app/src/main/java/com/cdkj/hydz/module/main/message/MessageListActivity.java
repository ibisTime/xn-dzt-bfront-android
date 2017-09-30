package com.cdkj.hydz.module.main.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseRefreshActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.adapter.MessageListAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.NoticeModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class MessageListActivity extends BaseRefreshActivity<NoticeModel.ListBean> {

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, MessageListActivity.class));
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return true;
    }

    @Override
    protected void onInit(Bundle savedInstanceState, int pageIndex, int limit) {
        setTopTitle(getString(R.string.message_title));
        setSubLeftImgState(true);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            NoticeModel.ListBean bean = (NoticeModel.ListBean) adapter.getItem(position);
            if (bean.getCommenter().equals(SPUtilHelpr.getUserId())){
                MessageActivity.open(this,bean.getReceiver());
            }else {
                MessageActivity.open(this,bean.getCommenter());
            }



        });

        getListData(pageIndex,limit,true);
    }

    @Override
    protected void getListData(int pageIndex, int limit, boolean canShowDialog) {
        Map<String, String> map = new HashMap<>();
        map.put("limit", limit+"");
        map.put("start", pageIndex+"");
        map.put("type", "1");
        map.put("receiver", SPUtilHelpr.getUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNoticeListData("620148", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<NoticeModel>(this) {


            @Override
            protected void onSuccess(NoticeModel data, String SucMessage) {
                setData(data.getList());
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<NoticeModel.ListBean> mData) {
        return new MessageListAdapter(mData);
    }

    @Override
    public String getEmptyInfo() {
        return "暂无消息";
    }

    @Override
    public int getEmptyImg() {
        return 0;
    }


}
