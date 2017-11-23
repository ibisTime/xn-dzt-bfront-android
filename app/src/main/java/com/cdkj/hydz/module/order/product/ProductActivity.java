package com.cdkj.hydz.module.order.product;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityProductBinding;
import com.cdkj.hydz.databinding.FootProductBinding;
import com.cdkj.hydz.databinding.HeadProductBinding;
import com.cdkj.hydz.module.adapter.ProductAdapter;
import com.cdkj.hydz.module.adapter.ProductChildAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.FHYModel;
import com.cdkj.hydz.module.model.OrderDetailModel;
import com.cdkj.hydz.module.model.ProductCommitModel;
import com.cdkj.hydz.module.model.ProductFabricCommitModel;
import com.cdkj.hydz.module.model.ProductModel;

import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class ProductActivity extends AbsBaseActivity {

    private ActivityProductBinding mBinding;
    private HeadProductBinding mHerderView;
    private FootProductBinding mFooterView;

    private String orderCode = "";
    private String modelCode = "";

    private ProductAdapter mAdapter;
    private GridLayoutManager mManager;
    private List<ProductModel> mData = new ArrayList<>();

    // 子产品
    private List<ProductModel.ModelSpecsListBean> childList;
    private ProductChildAdapter childAdapter;
    // 已选择的产品
    private ProductModel selectModel;

    private OrderDetailModel orderDetailModel;
    private FHYModel fhyModel;


    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, String orderCode) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("orderCode", orderCode);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_product, null, false);
        mHerderView = DataBindingUtil.inflate(getLayoutInflater(), R.layout.head_product, null, false);
        mFooterView = DataBindingUtil.inflate(getLayoutInflater(), R.layout.foot_product, null, false);

        if (getIntent() != null) {
            orderCode = getIntent().getStringExtra("orderCode");
        }

        initListView();
        iniListener();
        initRecyclerView();

        return mBinding.getRoot();
    }

    private void initListView() {
        childList = new ArrayList<>();
        childAdapter = new ProductChildAdapter(this, childList);
        mBinding.listChildProduct.addHeaderView(mHerderView.getRoot());
        mBinding.listChildProduct.addFooterView(mFooterView.getRoot());
        mBinding.listChildProduct.setAdapter(childAdapter);
    }

    private void iniListener() {
        mHerderView.txtProductSelect.setOnClickListener(view -> {

            mHerderView.txtProductSelect.setVisibility(View.GONE);
//            mHerderView.layoutProductBtn.setVisibility(View.GONE);
            mHerderView.recyclerProduct.setVisibility(View.VISIBLE);

        });
        mHerderView.txtProductConfirm.setOnClickListener(view -> {
            if (check()) {
                productConfirm();
            }
        });


        mFooterView.txtAddressEdit.setOnClickListener(view -> {
            mFooterView.txtAddressEdit.setVisibility(View.GONE);
            mFooterView.layoutAddressBtn.setVisibility(View.VISIBLE);
            mFooterView.layoutAddress.setVisibility(View.VISIBLE);
        });
        mFooterView.txtAddressConfirm.setOnClickListener(view -> {

            if (TextUtils.equals(mFooterView.edtAddress.getText().toString(), "")) {
                Toast.makeText(this, "请填写邮寄地址", Toast.LENGTH_SHORT).show();
            } else {
                mFooterView.txtAddressEdit.setVisibility(View.VISIBLE);
                mFooterView.layoutAddressBtn.setVisibility(View.GONE);
                mFooterView.layoutAddress.setVisibility(View.GONE);

                mFooterView.txtAddress.setText(mFooterView.edtAddress.getText().toString());
            }

        });


        mFooterView.txtRemarkEdit.setOnClickListener(view -> {
            mFooterView.txtRemarkEdit.setVisibility(View.GONE);
            mFooterView.layoutRemarkBtn.setVisibility(View.VISIBLE);
            mFooterView.layoutRemark.setVisibility(View.VISIBLE);
        });
        mFooterView.txtRemarkConfirm.setOnClickListener(view -> {
            if (TextUtils.equals(mFooterView.edtRemark.getText().toString(), "")) {
                Toast.makeText(this, "请填写订单备注", Toast.LENGTH_SHORT).show();
            } else {
                mFooterView.txtRemarkEdit.setVisibility(View.VISIBLE);
                mFooterView.layoutRemarkBtn.setVisibility(View.GONE);
                mFooterView.layoutRemark.setVisibility(View.GONE);

                mFooterView.txtRemark.setText(mFooterView.edtRemark.getText().toString());
            }

        });

        mFooterView.btnConfirm.setOnClickListener(view -> {

            for (ProductModel model : mData) {

                if (model.isSelect()) {

                    if (checkCommit()) {// 定价
                        madePrice();
                        return;
                    } else {
                        return;
                    }


                }
            }

            showToast("请选择定制产品");

        });
    }


    /**
     * 产品选择确认
     */
    private void productConfirm() {
        mHerderView.recyclerProduct.setVisibility(View.GONE);
        mHerderView.layoutProductBtn.setVisibility(View.GONE);
        mHerderView.txtProductSelect.setVisibility(View.VISIBLE);

        childList.clear();
        childList.addAll(selectModel.getModelSpecsList());
        childAdapter.notifyDataSetChanged();

        setTotalPrice();
    }

    private boolean checkCommit() {
        for (ProductModel.ModelSpecsListBean bean : childList) {
            if (bean.getClothCode() == null || bean.getClothCode().equals("")) {
                showToast("请选择" + bean.getName() + "基础");
                return false;
            }

            if (bean.getCodeList() == null || bean.getCodeList().size() == 0) {
                showToast("请选择" + bean.getName() + "工艺");
                return false;
            }

        }

        if (TextUtils.equals(mFooterView.edtAddress.getText().toString(), "")) {
            showToast("请填写邮寄地址");
            return false;
        }

//        if (TextUtils.equals(mFooterView.edtRemark.getText().toString(), "")){
//            showToast("请填写订单备注");
//            return false;
//        }


        return true;
    }

    private void initRecyclerView() {
        mManager = new GridLayoutManager(this, 3);
        mAdapter = new ProductAdapter(this, mData);
        mHerderView.recyclerProduct.setAdapter(mAdapter);
        mHerderView.recyclerProduct.setLayoutManager(mManager);
        mHerderView.recyclerProduct.setItemAnimator(new DefaultItemAnimator());
        mHerderView.recyclerProduct.setNestedScrollingEnabled(false);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("定价");
        setSubLeftImgState(true);
        getFHY();

    }

    @Subscribe
    public void setCraftData(ProductCommitModel model) {
        for (ProductModel.ModelSpecsListBean bean : childList) {
            if (bean.getCode().equals(model.getModelSpecsCode())) {

                try {
                    List<String> codeList = new ArrayList<>();
                    BigDecimal bigDecimal = new BigDecimal(0);

                    for (ProductCommitModel.ValueBean valueBean : model.getValueList()) {
                        if (valueBean.getKind().equals("3")) {
                            Map<String, String> map = new HashMap<>();
                            map.put(valueBean.getKey(), valueBean.getName());
                            bean.setMap(map);
                        } else {
                            codeList.add(valueBean.getCode());
                            bigDecimal = bigDecimal.add(valueBean.getPrice());
                        }

                    }

                    bean.setCraftPrice(bigDecimal);
                    bean.getCodeList().addAll(codeList);

                    childAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        setTotalPrice();

    }

    @Subscribe
    public void setFabricData(ProductFabricCommitModel model) {

        for (ProductModel.ModelSpecsListBean bean : childList) {
            if (bean.getCode().equals(model.getModelSpecsCode())) {

                bean.setClothCode(model.getCode());
                bean.setFabricPrice(model.getPrice());
                childAdapter.notifyDataSetChanged();
            }
        }

        setTotalPrice();
    }

    private void setTotalPrice() {
        BigDecimal craftPrice = new BigDecimal(0);
        BigDecimal fabricPrice = new BigDecimal(0);

        for (ProductModel.ModelSpecsListBean bean : childList) {
            craftPrice = craftPrice.add(bean.getCraftPrice());
            fabricPrice = fabricPrice.add(bean.getFabricPrice());
        }

        for (ProductModel model : mData) {

            if (model.isSelect()) {

                if (model.getType().equals("0")) { //衬衫
                    mFooterView.txtPrice.setText(MoneyUtils.showPriceWithUnit(craftPrice.add(fabricPrice)));
                } else {

                    if (orderDetailModel.getLevel().equals("1")) { // 非会员
                        mFooterView.txtPrice.setText(MoneyUtils.showPriceWithUnit(craftPrice.add(fabricPrice)));
                    } else {

                        BigDecimal price = craftPrice.add(fabricPrice);
                        BigDecimal fhy = new BigDecimal(Double.parseDouble(fhyModel.getCvalue()));

                        mFooterView.txtPrice.setText(MoneyUtils.showPriceWithUnit(price.multiply(fhy)));
                    }

                }

            }

        }


    }


    @Subscribe
    public void setView(ProductModel model) {
        if (model.getEventBusTag().equals(EventTags.PRODUCT)) {
            selectModel = model;

            modelCode = model.getCode();
            mHerderView.txtProductName.setText(model.getName());
        }

        productConfirm();

    }

    public void getFHY() {

        Map<String, String> map = new HashMap<>();
        map.put("ckey", "FHY");
        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        Call call = RetrofitUtils.createApi(MyApiServer.class).getFHY("620917", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<FHYModel>(this) {

            @Override
            protected void onSuccess(FHYModel data, String SucMessage) {
                if (data == null)
                    return;

                fhyModel = data;

                getProduct();


            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void getProduct() {
        Map<String, String> map = new HashMap<>();

        map.put("status", "1");
        map.put("orderDir", "asc");
        map.put("orderColumn", "order_no");
        map.put("orderDir", "asc");
        Call call = RetrofitUtils.createApi(MyApiServer.class).getProductList("620014", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<ProductModel>(this) {

            @Override
            protected void onSuccess(List<ProductModel> data, String SucMessage) {
                if (data == null)
                    return;


                mData.clear();
                mData.addAll(data);

                if (mData != null) {
                    getOrder();
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    public void getOrder() {
        Map<String, String> map = new HashMap<>();

        map.put("code", orderCode);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getOrderDetails("620231", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<OrderDetailModel>(this) {

            @Override
            protected void onSuccess(OrderDetailModel data, String SucMessage) {
                if (data == null)
                    return;

                orderDetailModel = data;

                if (data.getProduct() != null) {
                    modelCode = data.getProduct().getModelCode();

                    for (ProductModel model : mData) {

                        if (TextUtils.equals(model.getCode(), modelCode)) {

                            model.setSelect(true);

                            mHerderView.txtProductSelect.setVisibility(View.GONE);
                            mHerderView.layoutProductBtn.setVisibility(View.VISIBLE);

                            mHerderView.recyclerProduct.setVisibility(View.VISIBLE);
                        }

                    }

                }
                setProductView(data);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setProductView(OrderDetailModel data) {

        if (data.getLevel().equals("1")) {
            mFooterView.txtPriceType.setText("售价");
        } else {
            mFooterView.txtPriceType.setText("会员价");
        }

    }

    private class ReqList {
        private String clothCode;
        private List<String> codeList;
        private Map<String, String> map;
        private String modelSpecsCode;

        public String getClothCode() {
            return clothCode;
        }

        public void setClothCode(String clothCode) {
            this.clothCode = clothCode;
        }

        public List<String> getCodeList() {
            return codeList;
        }

        public void setCodeList(List<String> codeList) {
            this.codeList = codeList;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }

        public String getModelSpecsCode() {
            return modelSpecsCode;
        }

        public void setModelSpecsCode(String modelSpecsCode) {
            this.modelSpecsCode = modelSpecsCode;
        }
    }

    private void madePrice() {

        List<ReqList> reqList = new ArrayList<>();
        for (ProductModel.ModelSpecsListBean bean : childList) {
            ReqList rl = new ReqList();
            rl.setClothCode(bean.getClothCode());
            rl.setCodeList(bean.getCodeList());
            rl.setMap(bean.getMap());
            rl.setModelSpecsCode(bean.getCode());
            reqList.add(rl);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("address", mFooterView.edtAddress.getText().toString().trim());
        map.put("remark", mFooterView.edtRemark.getText().toString().trim());
        map.put("quantity", "1");
        map.put("modelCode", modelCode);
        map.put("orderCode", orderCode);

        map.put("reqList", reqList);
        map.put("updater", SPUtilHelpr.getUserId());
        map.put("token", SPUtilHelpr.getUserToken());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("620203", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {

            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    showToast("定价成功,请等待用户支付");
                    finish();
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private boolean check() {

        for (ProductModel model : mData) {
            if (model.isSelect()) {
                return true;
            }
        }

        Toast.makeText(this, "请选择定制产品", Toast.LENGTH_SHORT).show();
        return false;

    }
}
