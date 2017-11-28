package com.cdkj.hydz.module.order;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;

import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.BaseRefreshFragment;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.HeadOrderBinding;
import com.cdkj.hydz.module.adapter.OrderAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.OrderModel;
import com.cdkj.hydz.module.order.product.ProductActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.utils.InputMethodUtils.showInputMethod;

/**
 * Created by lei on 2017/8/21.
 */

public class OrderFragment extends BaseRefreshFragment<OrderModel.ListBean> {

    private HeadOrderBinding mHerderView;

    // 1 待量体,2 待支付,3 已支付（待录入）,4 待复核,5 待生产,6 生产中,7 已发货,8 已收货,9 已评价,10 已归档,11 取消订单
    private String status = "";

    private String burry = "";

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static OrderFragment getInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return true;
    }

    @Override
    protected void afterCreate(int pageIndex, int limit) {
        setTopTitleLine(false);
        setTopTitleViewBg(R.color.white);
        setTopTitleViewColor(R.color.black);
        setTopTitle(getString(R.string.order_title));


        mHerderView = DataBindingUtil.inflate(mActivity.getLayoutInflater(), R.layout.head_order, null, false);
        mAdapter.setHeaderAndEmpty(true);
        mAdapter.addHeaderView(mHerderView.getRoot());

        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            OrderModel.ListBean bean = (OrderModel.ListBean) adapter.getItem(position);
            if (bean.getStatus().equals("1")) {
                ProductActivity.open(mActivity, bean.getCode());
            } else {
                OrderActivity.open(mActivity, bean.getCode(), "", bean.getType(), false, null);
            }

        });

        initHeadListener(pageIndex, limit);

        getListData(pageIndex, limit, true);

    }

    private void initHeadListener(int pageIndex, int limit) {
        int csl = getResources().getColor(R.color.black);

        mHerderView.txtAll.setOnClickListener(v -> {
            setTabDark();
            status = "";
            mHerderView.edtSearch.setText("");
            mHerderView.txtAll.setTextColor(csl);

            mHerderView.edtSearch.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标

            getListData(pageIndex, limit, true);
        });

        mHerderView.txtWaitMeasure.setOnClickListener(v -> {
            setTabDark();
            status = "1";
            mHerderView.edtSearch.setText("");
            mHerderView.txtWaitMeasure.setTextColor(csl);

            mHerderView.edtSearch.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标

            getListData(pageIndex, limit, true);
        });

        mHerderView.txtWaitPay.setOnClickListener(v -> {
            setTabDark();
            status = "2";
            mHerderView.edtSearch.setText("");
            mHerderView.txtWaitPay.setTextColor(csl);

            mHerderView.edtSearch.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标

            getListData(pageIndex, limit, true);
        });

        mHerderView.txtWaitInput.setOnClickListener(v -> {
            setTabDark();
            status = "3";
            mHerderView.edtSearch.setText("");
            mHerderView.txtWaitInput.setTextColor(csl);

            mHerderView.edtSearch.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标

            getListData(pageIndex, limit, true);
        });

        mHerderView.txtWaitCheck.setOnClickListener(v -> {
            setTabDark();
            status = "4";
            mHerderView.edtSearch.setText("");
            mHerderView.txtWaitCheck.setTextColor(csl);

            mHerderView.edtSearch.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标

            getListData(pageIndex, limit, true);
        });

        mHerderView.txtSearch.setOnClickListener(v -> {

            mHerderView.edtSearch.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标

            getListData(1, limit, true);

        });

        mHerderView.edtSearch.setOnClickListener(v -> {
            mHerderView.edtSearch.setFocusable(true);//设置输入框可聚集
            mHerderView.edtSearch.setFocusableInTouchMode(true);//设置触摸聚焦
            mHerderView.edtSearch.requestFocus();//请求焦点
            mHerderView.edtSearch.findFocus();//获取焦点
            showInputMethod(mHerderView.edtSearch);
        });

        mHerderView.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                burry = editable.toString();
            }
        });
    }

    private void setTabDark() {
        mHerderView.txtAll.setTextColor(Color.parseColor("#B3B3B3"));
        mHerderView.txtWaitPay.setTextColor(Color.parseColor("#B3B3B3"));
        mHerderView.txtWaitInput.setTextColor(Color.parseColor("#B3B3B3"));
        mHerderView.txtWaitCheck.setTextColor(Color.parseColor("#B3B3B3"));
        mHerderView.txtWaitMeasure.setTextColor(Color.parseColor("#B3B3B3"));
    }

    @Override
    protected void getListData(int pageIndex, int limit, boolean canShowDialog) {
        Map<String, String> map = new HashMap<>();

        map.put("status", status);
        map.put("burry", burry);
        map.put("limit", limit + "");
        map.put("start", pageIndex + "");
        map.put("ltUser", SPUtilHelpr.getUserId());
        map.put("userId", SPUtilHelpr.getUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getOrderData("620230", StringUtils.getJsonToString(map));

        addCall(call);

        if (canShowDialog) showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<OrderModel>(mActivity) {

            @Override
            protected void onSuccess(OrderModel data, String SucMessage) {
                if (data == null)
                    return;

                setData(data.getList());
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<OrderModel.ListBean> mDataList) {
        return new OrderAdapter(mDataList);
    }


    @Override
    public void onResume() {
        super.onResume();
        getListData(1, 10, true);
    }

    @Override
    public String getEmptyInfo() {
        return "暂无订单";
    }

    @Override
    public int getEmptyImg() {
        return 0;
    }
}
