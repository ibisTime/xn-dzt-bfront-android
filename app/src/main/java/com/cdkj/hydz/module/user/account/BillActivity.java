package com.cdkj.hydz.module.user.account;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseRefreshActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.HeadBillBinding;
import com.cdkj.hydz.module.adapter.BillAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.BillModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class BillActivity extends BaseRefreshActivity<BillModel.ListBean> {

    private HeadBillBinding mHerderView;

    private String accountNumber;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context,String accountNumber) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, BillActivity.class);
        intent.putExtra("accountNumber", accountNumber);
        context.startActivity(intent);
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return true;
    }

    @Override
    protected void onInit(Bundle savedInstanceState, int pageIndex, int limit) {
        setTopTitle("账户明细");
        setSubLeftImgState(true);

        mHerderView = DataBindingUtil.inflate(getLayoutInflater(), R.layout.head_bill, null, false);
        mAdapter.setHeaderAndEmpty(true);
        mAdapter.addHeaderView(mHerderView.getRoot());

        initListener();

        accountNumber = getIntent().getStringExtra("accountNumber");
        if (accountNumber != null) {
            getListData(pageIndex,limit,true);
        }

    }

    private void initListener() {
        mHerderView.layoutBill.setOnClickListener(view -> {
            BillPastActivity.open(this,accountNumber);
        });
    }

    @Override
    protected void getListData(int pageIndex, int limit, boolean canShowDialog) {

        Map<String, String> map = new HashMap<>();
        map.put("limit", limit+"");
        map.put("start", pageIndex+"");
        map.put("accountNumber", accountNumber);
        map.put("token", SPUtilHelpr.getUserToken());
        map.put("systemCode", MyConfig.SYSTEMCODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getBillListData("802524", StringUtils.getJsonToString(map));

        addCall(call);

        if (canShowDialog)
            showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<BillModel>(this) {

            @Override
            protected void onSuccess(BillModel data, String SucMessage) {

                setData(data.getList());
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<BillModel.ListBean> data) {
        return new BillAdapter(data);
    }

    @Override
    public String getEmptyInfo() {
        return "暂无明细";
    }

    @Override
    public int getEmptyImg() {
        return 0;
    }
}
