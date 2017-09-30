package com.cdkj.hydz.module.user.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseRefreshActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.adapter.CardAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.CardModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class CardListActivity extends BaseRefreshActivity<CardModel.ListBean> {

    private List<CardModel.ListBean> list = new ArrayList<>();

    private Boolean isWithdraw;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context,Boolean isWithdraw) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CardListActivity.class);
        intent.putExtra("isWithdraw",isWithdraw);
        context.startActivity(intent);
    }

    /**
     * 打开当前页面
     *
     * @param activity
     */
    public static void openForResult(Activity activity, Boolean isWithdraw) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, CardListActivity.class);
        intent.putExtra("isWithdraw",isWithdraw);

        activity.startActivityForResult(intent, 0);
    }

    @Override
    protected void onInit(Bundle savedInstanceState, int pageIndex, int limit) {
        setTopTitle(getString(R.string.card_title));
        setSubLeftImgState(true);
        setSubRightTitleAndClick("添加",v -> {
            CardActivity.open(this,"",false);
        });

        if (getIntent() != null) {
            isWithdraw = getIntent().getBooleanExtra("isWithdraw", false);
        }

        mAdapter.setOnItemClickListener((adapter, view, position) ->  {

            if(isWithdraw){
                setResult(0,new Intent().putExtra("bankcardNumber",list.get(position).getBankcardNumber())
                        .putExtra("bankName",list.get(position).getBankName()));
                finish();
            }else {
                CardActivity.open(this,list.get(position).getCode(),true);
            }


        });

        getListData(pageIndex,limit,true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListData(1,10,true);
    }

    @Override
    protected void getListData(int pageIndex, int limit, boolean canShowDialog) {
        Map<String, String> map = new HashMap<>();
        map.put("limit", limit+"");
        map.put("start", pageIndex+"");
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("token", SPUtilHelpr.getUserToken());
        map.put("systemCode", MyConfig.SYSTEMCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getCardistData("802015", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CardModel>(this) {

            @Override
            protected void onSuccess(CardModel data, String SucMessage) {
                if (pageIndex == 1){
                    list.clear();
                }
                list = data.getList();
                if (list != null) {
                    setData(data.getList());
                }

                if(data.getList().size()>0){
                    setSubRightTitHide();
                }

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<CardModel.ListBean> mDataList) {
        return new CardAdapter(mDataList);
    }

    @Override
    public String getEmptyInfo() {
        return "暂无银行卡";
    }

    @Override
    public int getEmptyImg() {
        return 0;
    }
}
