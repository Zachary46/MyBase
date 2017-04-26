package com.zachary.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zachary.base.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * ListView的ViewHolder
 * */
public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.mPosition = position;
        return holder;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setSpanText(int viewId, Spanned text) {
        TextView view = getView(viewId);
        view.setText(text);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public ViewHolder setSpanText(int viewId, SpannableStringBuilder text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @return
     */
    public ViewHolder setMovementMethod(int viewId) {
        TextView view = getView(viewId);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }


    /**
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setChbText(int viewId, String text) {
        CheckBox view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置字体颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public ViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    /**
     * 为TextView设置背景色
     *
     * @param viewId
     * @param color
     * @return
     */
    public ViewHolder setTextBackgroundColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 为TextView设置背景色
     *
     * @param viewId
     * @param color
     * @return
     */
    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }
    public void setClickAble(int viewId, boolean c){
        View view = getView(viewId);
        view.setClickable(c);
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
        Glide.with(mContext).load(url).centerCrop().placeholder(R.mipmap.ic_place_holder).into((ImageView) getView
                (viewId));
        return this;
    }

    public ViewHolder setImageByUrlAndResize(int viewId, String url, int size) {
        ImageView imageView = getView(viewId);
        //Glide.with(mContext).load(url).placeholder(R.drawable.ic_place_holder).centerCrop().override(size, size)
        // .into(imageView);
        Glide.with(mContext).load(url).asBitmap().error(R.mipmap.ic_place_holder).centerCrop().into(imageView);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size;
        imageView.requestLayout();
        return this;
    }

    public ViewHolder setImageByUrlAndResize(int viewId, String url, int width,int height) {
        ImageView imageView = getView(viewId);
        //Glide.with(mContext).load(url).placeholder(R.drawable.ic_place_holder).centerCrop().override(size, size)
        // .into(imageView);
        Glide.with(mContext).load(url).asBitmap().error(R.mipmap.ic_place_holder).centerCrop().into(imageView);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        imageView.requestLayout();
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url, boolean fullSize) {
        Glide.with(mContext).load(url).asBitmap().into((ImageView) getView
                (viewId));
        return this;
    }


    /**
     * 为ImageView设置圆形图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrlToRound(int viewId, String url) {
        Glide.with(mContext).load(url).asBitmap().placeholder(R.mipmap.ic_default_header).error(R.mipmap
                .ic_default_header).centerCrop().
                transform(new CropCircleTransformation(mContext)).into((ImageView) getView(viewId));
        return this;
    }


    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url, int size) {
        ImageView imageView = getView(viewId);
        //Glide.with(mContext).load(url).placeholder(R.drawable.ic_place_holder).centerCrop().override(size, size)
        // .into(imageView);
        Glide.with(mContext).load(url).error(R.mipmap.ic_place_holder).centerCrop().into(imageView);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size;
        imageView.requestLayout();
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrToCircle(int viewId, String url, int size) {
        ImageView imageView = getView(viewId);
        //Glide.with(mContext).load(url).placeholder(R.drawable.ic_place_holder).centerCrop().override(size, size)
        // .into(imageView);
   /*     ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size;
        imageView.requestLayout();*/
        Glide.with(mContext).load(url).error(R.mipmap.ic_place_holder)
                .override(size, size).centerCrop().into(imageView);
        return this;
    }


    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setRoundImageByUrl(int viewId, String url, int size, int dp) {
        ImageView imageView = getView(viewId);
        Glide.with(mContext).load(url).error(R.mipmap.ic_place_holder).centerCrop().bitmapTransform(new
                RoundedCornersTransformation(mContext, dp,0))
                .into(imageView);
       /* Glide.with(mContext).load(url).error(R.mipmap.ic_place_holder).centerCrop().bitmapTransform(new
                RoundTransform(mContext, dp,0))
                .into(imageView);*/
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size;
        imageView.requestLayout();
        return this;
    }

    /**
     * Add links into a TextView.
     *
     * @param viewId The id of the TextView to linkify.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the range of a ProgressBar to 0...max.
     *
     * @param viewId The view id.
     * @param max    The max value of a ProgressBar.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setVisibility(int viewId, int status) {
        View view = getView(viewId);
        view.setVisibility(status);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }


    public int getPosition() {
        return mPosition;
    }

    public ViewHolder setWidth(int viewId, int size) {
        View v = getView(viewId);
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        layoutParams.width = size;
        v.requestLayout();
        return this;
    }

    public ViewHolder setChecked(int chDefault, boolean b) {
        CheckBox box = getView(chDefault);
        box.setChecked(b);
        return this;
    }

    public ViewHolder setTypeface(int viewId, int type) {
        TextView tv = getView(viewId);
        tv.setTypeface(Typeface.MONOSPACE, type);
        return this;
    }

    public ViewHolder setFlags(int viewId, int flag) {
        TextView tv = getView(viewId);
        tv.getPaint().setFlags(flag);
        return this;
    }
}
