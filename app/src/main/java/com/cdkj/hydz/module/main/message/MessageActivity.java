package com.cdkj.hydz.module.main.message;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityMessageBinding;
import com.cdkj.hydz.module.adapter.MessageAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.NoticeModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class MessageActivity extends AbsBaseActivity {

    private ActivityMessageBinding mBinding;

    private String commenter;

    private int pageIndex;

    private MessageAdapter mAdapter;
    private List<NoticeModel.ListBean> list = new ArrayList<>();

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, String commenter) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra("commenter", commenter);
        context.startActivity(intent);
    }
    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_message, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("消息回复");
        setSubLeftImgState(true);

        mAdapter = new MessageAdapter(this,list);
        mBinding.listMessage.setAdapter(mAdapter);

        mBinding.btnConfirm.setOnClickListener(view -> {
            if(mBinding.edtContent.getText().toString().equals("")){
                showToast("请填写回复内容");
            }else {
                reply(mBinding.edtContent.getText().toString());
            }
        });

        if(getIntent() == null)
            return;

        commenter = getIntent().getStringExtra("commenter");
        getMessage();

        initRefreshLayout();
    }

    private void initRefreshLayout() {

        mBinding.refreshLayout.setEnableLoadmoreWhenContentNotFull(true);

        mBinding.refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex++;
                getMessage();
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex = 1;
                getMessage();
            }
        });
    }

    private void getMessage(){
        Map<String, String> map = new HashMap<>();
        map.put("commenter", commenter);
        map.put("limit", 10+"");
        map.put("start", pageIndex+"");
        map.put("type", "1");
        map.put("orderDir","asc");
        map.put("receiver", SPUtilHelpr.getUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNoticeListData("620149", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<NoticeModel>(this) {

            @Override
            protected void onSuccess(NoticeModel data, String SucMessage) {
                if (pageIndex == 1){
                    list.clear();
                }
                list.addAll(data.getList());
                mAdapter.notifyDataSetChanged();

                if (mBinding.refreshLayout.isRefreshing()) {
                    mBinding.refreshLayout.finishRefresh();
                } else if (mBinding.refreshLayout.isLoading()) {
                    mBinding.refreshLayout.finishLoadmore();
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void reply(String content){
        Map<String, String> map = new HashMap<>();
        map.put("commenter", SPUtilHelpr.getUserId()); // 量体师自己
        map.put("content", content);
        map.put("receiver", commenter); // 用户

        Call call = RetrofitUtils.getBaseAPiService().successRequest("620141", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {


            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {

                mBinding.edtContent.setText("");
                showToast("回复成功");
                pageIndex = 1;
                getMessage();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }


}
