package com.cdkj.hydz.module.order.product;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.base.AbsBaseActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.databinding.ActivityCraftBinding;
import com.cdkj.hydz.module.adapter.ProductCraftAdapter;
import com.cdkj.hydz.module.api.MyApiServer;
import com.cdkj.hydz.module.model.ProductCommitModel;
import com.cdkj.hydz.module.model.ProductCraftModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class CraftActivity extends AbsBaseActivity {

    private ActivityCraftBinding mBinding;

    private String modelSpecsCode;

    private ProductCraftAdapter adapter;
    private List<ProductCraftModel.ProductCategoryListBean> list = new ArrayList<>();

    public static void open(Context context, String modelSpecsCode) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CraftActivity.class);
        intent.putExtra("modelSpecsCode", modelSpecsCode);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_craft, null, false);

        mBinding.btnConfirm.setOnClickListener(view -> {

            if (check()){
                packData();
            }

        });

        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle("选择工艺");
        setSubLeftImgState(true);

        initRecyclerView();

        if (getIntent() != null){
            modelSpecsCode = getIntent().getStringExtra("modelSpecsCode");
            getCraft();

        }
    }



    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        adapter = new ProductCraftAdapter(this, list);

        //竖直排列、正向排序
        mBinding.recyclerCraft.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerCraft.setBackgroundResource(R.color.white);
        mBinding.recyclerCraft.setAdapter(adapter);
    }

    /**
     * 获取工艺
     */
    public void getCraft() {

        Map<String, String> map = new HashMap<>();
        map.put("status","1");
        map.put("modelSpecsCode", modelSpecsCode);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getProductCraftList("620054", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<ProductCraftModel>(this) {

            @Override
            protected void onSuccess(ProductCraftModel data, String SucMessage) {
                if (data == null)
                    return;

                list.addAll(data.getProductCategoryList());
                adapter.notifyDataSetChanged();

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Subscribe
    public void update(String tag){
        if (tag.equals(EventTags.UPDATE)){
            Log.e("tag",tag);
            for (int i = 0; i<list.size(); i++){
                if (list.get(i).getKind().equals("4") || list.get(i).getKind().equals("1")){


//                    Log.e("tag getKind()",list.get(i).getKind());
//                    adapter.notifyItemChanged(i);
//                    Log.e("tag getKind().p",i+"");

                    adapter.notifyDataSetChanged();
                }
            }
        }
    }


    /**
     * 全部已选true，有未选false
     * @return
     */
    private boolean check(){

        for(ProductCraftModel.ProductCategoryListBean bean : list){

            Log.e("value",bean.getDvalue());
            Log.e("kind",bean.getKind());
            Log.e("checkCraft(bean)",checkCraft(bean)+"");

            if (!checkCraft(bean)){
                return false;
            }

        }

        return true;
    }

    /**
     * 需要颜色时判断是否有颜色已选，已选true，全部未选false
     * @param bean
     * @return
     */
    private Boolean checkColor(ProductCraftModel.ProductCategoryListBean bean) {

        if (bean.getColorPcList().size()!=0){
            for (ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean colorCraftListBean : bean.getColorPcList().get(0).getColorCraftList()){
                if (colorCraftListBean.isSelect()){
                    return true;
                }
            }
            Toast.makeText(this, "请选择"+bean.getColorPcList().get(0).getDvalue(), Toast.LENGTH_SHORT).show();
            return false;
        }

        return false;
    }

    /**
     * 工艺是否被选择，已选true，全部未选false
     * @param bean
     * @return
     */
    private boolean checkCraft(ProductCraftModel.ProductCategoryListBean bean) {

        if (checkContent()){ // 已填写刺绣内容
            if (bean.getCraftList().size()!=0){
                for(ProductCraftModel.ProductCategoryListBean.CraftListBean craftListBean : bean.getCraftList()){
                    if (craftListBean.isSelect()){
                        if (craftListBean.getIsHit() != null){
                            if (craftListBean.getIsHit().equals("1")){ // 已选择的工艺是否需要颜色
                                return checkColor(bean);
                            }else {
                                return true;
                            }
                        }else {
                            return true;
                        }

                    }

                }
                Toast.makeText(this, "请选择"+bean.getDvalue(), Toast.LENGTH_SHORT).show();
                return false;

            }
        }else {

            if (bean.getKind().equals("3") || bean.getKind().equals("4") || bean.getKind().equals("1")){
                // 非必填
                return true;
            }else {
                if (bean.getCraftList().size()!=0){
                    for(ProductCraftModel.ProductCategoryListBean.CraftListBean craftListBean : bean.getCraftList()){
                        if (craftListBean.isSelect()){

                            if (craftListBean.getIsHit().equals("1")){ // 已选择的工艺是否需要颜色
                                return checkColor(bean);
                            }else {
                                return true;
                            }

                        }

                    }
                    Toast.makeText(this, "请选择"+bean.getDvalue(), Toast.LENGTH_SHORT).show();
                    return false;

                }
            }

        }

        return false;
    }

    /**
     * 刺绣内容
     */
    private boolean checkContent(){

        for(ProductCraftModel.ProductCategoryListBean bean : list){
            if (bean.getKind().equals("3")){

                if (bean.getCraftList()!=null){

                    for(ProductCraftModel.ProductCategoryListBean.CraftListBean craftListBean : bean.getCraftList()){
                        if (craftListBean.isSelect()){
                            return true;
                        }

                    }
                    return false;

                }

            }

        }

        return false;

    }

    private void packData(){
        ProductCommitModel model = new ProductCommitModel();
        model.setModelSpecsCode(modelSpecsCode);

        for(ProductCraftModel.ProductCategoryListBean bean : list){
            if (bean.getCraftList().size()!=0){
                for(ProductCraftModel.ProductCategoryListBean.CraftListBean craftListBean : bean.getCraftList()){
                    if (craftListBean.isSelect()){

                        ProductCommitModel.ValueBean b =  new ProductCommitModel.ValueBean();
                        b.setKey(bean.getDkey());
                        b.setName(craftListBean.getName());

                        b.setKind(bean.getKind());
                        b.setCode(craftListBean.getCode());
                        b.setPrice(craftListBean.getPrice());
                        model.getValueList().add(b);
                    }
                }
            }

            if (bean.getColorPcList().size()!=0){
                for (ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean colorCraftListBean : bean.getColorPcList().get(0).getColorCraftList()){
                    if (colorCraftListBean.isSelect()){

                        ProductCommitModel.ValueBean b =  new ProductCommitModel.ValueBean();
                        b.setKind(bean.getKind());
                        b.setCode(colorCraftListBean.getCode());
                        b.setPrice(colorCraftListBean.getPrice());
                        model.getValueList().add(b);
                    }
                }
            }
        }

        Log.e("packData().size()",model.getValueList().size()+"");

        EventBus.getDefault().post(model);
        finish();

    }
}
