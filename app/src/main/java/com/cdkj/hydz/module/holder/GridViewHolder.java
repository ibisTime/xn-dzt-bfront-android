package com.cdkj.hydz.module.holder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.ProductCraftModel;

import java.util.List;

/**
 * Created by lei on 2017/9/14.
 */

public class GridViewHolder extends BaseHolder<List<ProductCraftModel.ProductCategoryListBean>> {

    Activity mActivity;

    private int craftPosition;
    private int colorPosition;

    private TextView txtEdit;
    private TextView txtConfirm;
    private TextView txtCraftName;
    private TextView txtCraftValue;
    private LinearLayout layoutBtn;
    private LinearLayout layoutGrid;
    private RecyclerView recyclerCraft;

    private TextView txtColorEdit;
    private TextView txtColorName;
    private TextView txtColorValue;
    private TextView txtColorConfirm;
    private LinearLayout layoutColor;
    private LinearLayout layoutColorBtn;
    private RecyclerView recyclerColor;

    private final int ONE_LINE_SHOW_NUMBER = 3;
    private List<ProductCraftModel.ProductCategoryListBean> productCategoryList;


    public GridViewHolder(Context context, int viewId, ViewGroup parent, int viewType) {
        super(context, viewId, parent, viewType);
        mActivity = (Activity) context;

        txtEdit = itemView.findViewById(R.id.txt_edit);
        txtConfirm = itemView.findViewById(R.id.txt_confirm);
        txtCraftName = itemView.findViewById(R.id.txt_craft_name);
        txtCraftValue = itemView.findViewById(R.id.txt_craft_value);
        layoutBtn = itemView.findViewById(R.id.layout_btn);
        layoutGrid = itemView.findViewById(R.id.layout_grid);
        recyclerCraft = itemView.findViewById(R.id.recycler_craft);

        txtColorEdit = itemView.findViewById(R.id.txt_color_edit);
        txtColorName = itemView.findViewById(R.id.txt_color_name);
        txtColorValue = itemView.findViewById(R.id.txt_color_value);
        txtColorConfirm = itemView.findViewById(R.id.txt_color_confirm);
        layoutColor = itemView.findViewById(R.id.layout_color);
        layoutColorBtn = itemView.findViewById(R.id.layout_color_btn);
        recyclerColor = itemView.findViewById(R.id.recycler_color);

    }

    public void refreshData(List<ProductCraftModel.ProductCategoryListBean> list, int position) {
        super.refreshData(list, position);
        productCategoryList = list;

        if (list.get(position).getKind().equals("4") || list.get(position).getKind().equals("1")) {

            for (ProductCraftModel.ProductCategoryListBean bean : list) {
                if (bean.getKind().equals("3")) {
                    if (bean.getCraftList().size() > 0) {
                        layoutGrid.setVisibility(View.VISIBLE);
                    } else {
                        layoutGrid.setVisibility(View.GONE);
                    }
                }
            }

        } else {
            layoutGrid.setVisibility(View.VISIBLE);
        }

        Log.e("position=", position + "");
        Log.e("getDvalue()=", list.get(position).getDvalue() + "");

        txtCraftName.setText(list.get(position).getDvalue());
        txtCraftValue.setText("");

        // 设置已选的默认值
        for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : list.get(position).getCraftList()) {

            if (bean.isSelect()) {
                txtCraftValue.setText(bean.getName()
                        + "("
                        + MoneyUtils.showPriceWithUnit(bean.getPrice())
                        + ")");
            }
        }

        //每行显示3个，水平显示
        recyclerCraft.setLayoutManager(new GridLayoutManager(mActivity, ONE_LINE_SHOW_NUMBER, LinearLayoutManager.VERTICAL, false));
        //设置Adapter
        GridAdapter gradapter = new GridAdapter(list.get(position).getCraftList(), position);
        recyclerCraft.setAdapter(gradapter);
        gradapter.setDefluteValue();

        txtEdit.setOnClickListener(view -> {
            txtEdit.setVisibility(View.GONE);
            layoutBtn.setVisibility(View.VISIBLE);
            recyclerCraft.setVisibility(View.VISIBLE);
        });

        txtConfirm.setOnClickListener(view -> {
            if (check(list, position)) {
                txtEdit.setVisibility(View.VISIBLE);
                layoutBtn.setVisibility(View.GONE);
                recyclerCraft.setVisibility(View.GONE);

                list.get(position).getCraftList().get(craftPosition).setSelect(true);

            }

        });

        // 是否有颜色需要选择
        if (list.get(position).getColorPcList().size() > 0)
            initColorRecycle(list, position);
    }

    private void initColorRecycle(List<ProductCraftModel.ProductCategoryListBean> list, int position) {

        txtColorName.setText(list.get(position).getColorPcList().get(0).getDvalue());

        // 设置已选的默认值
        for (ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean bean : list.get(position).getColorPcList().get(0).getColorCraftList()) {
            if (bean.isSelect()) {
                txtColorValue.setText(bean.getName());
            }
        }

        //每行显示3个，水平显示
        recyclerColor.setLayoutManager(new GridLayoutManager(mActivity, ONE_LINE_SHOW_NUMBER, LinearLayoutManager.VERTICAL, false));
        //设置Adapter
        ColorAdapter colorAdapter = new ColorAdapter(list.get(position).getColorPcList().get(0).getColorCraftList());
        recyclerColor.setAdapter(colorAdapter);
        colorAdapter.setDefluteValue();

        txtColorEdit.setOnClickListener(view -> {
            txtColorEdit.setVisibility(View.GONE);
            layoutColorBtn.setVisibility(View.VISIBLE);
            recyclerColor.setVisibility(View.VISIBLE);
        });

        txtColorConfirm.setOnClickListener(view -> {
            if (checkColor(list, position)) {
                txtColorEdit.setVisibility(View.VISIBLE);
                layoutColorBtn.setVisibility(View.GONE);
                recyclerColor.setVisibility(View.GONE);

            }

        });

    }

    private boolean check(List<ProductCraftModel.ProductCategoryListBean> list, int position) {
        for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : list.get(position).getCraftList()) {
            if (bean.isSelect()) {
                return true;
            }
        }

        Toast.makeText(mActivity, "请选择" + list.get(position).getDvalue(), Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean checkColor(List<ProductCraftModel.ProductCategoryListBean> list, int position) {
        for (ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean bean : list.get(position).getColorPcList().get(0).getColorCraftList()) {
            if (bean.isSelect()) {
                return true;
            }
        }

        Toast.makeText(mActivity, "请选择" + list.get(position).getColorPcList().get(0).getDvalue(), Toast.LENGTH_SHORT).show();
        return false;
    }

    private class GridAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private int itemPosition;
        private List<ProductCraftModel.ProductCategoryListBean.CraftListBean> craftList;


        public GridAdapter(List<ProductCraftModel.ProductCategoryListBean.CraftListBean> craftList, int itemPosition) {
            this.craftList = craftList;
            this.itemPosition = itemPosition;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mActivity, R.layout.item_order_craft, parent, viewType);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {

            ProductCraftModel.ProductCategoryListBean.CraftListBean beanDate = craftList.get(position);

            if (beanDate == null) return;

            if (beanDate.isSelect()) {
//                holder.txtBorder.setBackgroundResource(R.drawable.border_order_blue);
                ImgUtils.loadRoundImage(mActivity, MyConfig.IMGURL + beanDate.getSelected(), holder.imgItem);
            } else {
//                holder.txtBorder.setBackgroundResource(R.drawable.border_order_gray);
                ImgUtils.loadRoundImage(mActivity, MyConfig.IMGURL + beanDate.getPic(), holder.imgItem);
            }

            holder.txtName.setText(beanDate.getName());


            holder.imgItem.setOnClickListener(view -> {

                craftPosition = position;

                for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : craftList) {
                    bean.setSelect(false);
                }
                craftList.get(position).setSelect(true);

                setSelectValue(craftList.get(position));

                if (craftList.get(position).getIsHit().equals("1")) {
                    layoutColor.setVisibility(View.VISIBLE);
                } else {
                    layoutColor.setVisibility(View.GONE);

                    if (productCategoryList.get(itemPosition).getColorPcList().size() > 0) {
                        for (ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean bean : productCategoryList.get(itemPosition).getColorPcList().get(0).getColorCraftList()) {
                            bean.setSelect(false);
                        }
                        txtColorValue.setText("");
                    }

                }

                notifyDataSetChanged();

            });
        }

        private void setSelectValue(ProductCraftModel.ProductCategoryListBean.CraftListBean bean) {
            txtCraftValue.setText(bean.getName()
                    + "("
                    + MoneyUtils.showPriceWithUnit(bean.getPrice())
                    + ")");
        }

        public void setDefluteValue() {
            for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : craftList) {
                if (bean == null) continue;

                if (TextUtils.equals("1", bean.getIsDefault())) {
                    setSelectValue(bean);
                    break;
                }
            }
        }


        @Override
        public int getItemCount() {
            return craftList.size();
        }
    }

    private class ColorAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        List<ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean> colorList;

        public ColorAdapter(List<ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean> colorList) {
            this.colorList = colorList;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mActivity, R.layout.item_order_color, parent, viewType);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            if (colorList.get(position).isSelect()) {
                holder.txtBorder.setBackgroundResource(R.drawable.border_order_blue);
            } else {
                holder.txtBorder.setBackgroundResource(R.drawable.border_order_gray);
            }

            ImgUtils.loadRoundImage(mActivity, MyConfig.IMGURL + colorList.get(position).getPic(), holder.imgItem);

            holder.txtName.setText(colorList.get(position).getName());

            holder.txtBorder.setOnClickListener(view -> {
                if (!colorList.get(position).isSelect()) {
                    for (ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean bean : colorList) {
                        bean.setSelect(false);
                    }

                    colorList.get(position).setSelect(true);

                    setSelectValue(colorList.get(position));

                    notifyDataSetChanged();

                }

            });

        }

        //设置默认值
        private void setSelectValue(ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean craftListBean) {
            txtColorValue.setText(craftListBean.getName()
                    + "("
                    + MoneyUtils.showPriceWithUnit(craftListBean.getPrice())
                    + ")");
        }

        public void setDefluteValue() {
            for (ProductCraftModel.ProductCategoryListBean.ColorPcList.ColorCraftListBean bean : colorList) {
                if (bean == null) continue;

                if (TextUtils.equals("1", bean.getIsDefault())) {
                    setSelectValue(bean);
                    break;
                }

            }
        }


        @Override
        public int getItemCount() {
            return colorList.size();
        }
    }

}
