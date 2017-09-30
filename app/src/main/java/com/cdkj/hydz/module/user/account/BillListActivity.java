package com.cdkj.hydz.module.user.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseRefreshActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.module.adapter.BillAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.BillModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.utils.DateUtil.getDaysOfMonth;


public class BillListActivity extends BaseRefreshActivity<BillModel.ListBean> {

    private String date;
    private String accountNumber;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context,String accountNumber,String date) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, BillListActivity.class);
        intent.putExtra("date",date);
        intent.putExtra("accountNumber",accountNumber);
        context.startActivity(intent);
    }


    @Override
    protected void onInit(Bundle savedInstanceState, int pageIndex, int limit) {

        setSubLeftImgState(true);

        if (getIntent() == null)
            return;

        date = getIntent().getStringExtra("date");
        accountNumber = getIntent().getStringExtra("accountNumber");

        setTopTitle(date.split("-")[1]+"月账单");

        if (accountNumber != null) {
            getListData(pageIndex,limit,true);
        }
    }

    @Override
    protected void getListData(int pageIndex, int limit, boolean canShowDialog) {

        String y = date.split("-")[0];
        String m = date.split("-")[1];

        Map<String, String> map = new HashMap<>();
        map.put("limit", limit+"");
        map.put("start", pageIndex+"");
        map.put("dateStart", y+"-"+m+"-01");
        map.put("dateEnd", y+"-"+m+"-"+getDaysOfMonth(date));
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
