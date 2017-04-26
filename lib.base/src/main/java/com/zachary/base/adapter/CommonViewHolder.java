package com.zachary.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.zachary.base.R;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CommonViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews;
    private View rootView;
    private Context mContext;

    public CommonViewHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        mViews = new SparseArray<>();
        mContext = itemView.getContext().getApplicationContext();
    }

    public <T extends View> T getView(int resId) {
        View view = mViews.get(resId);
        if (null == view) {
            view = rootView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }


    public CommonViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public CommonViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        if(null!=view){
            view.setText(text);
        }
        return this;
    }

    public CommonViewHolder setText(int viewId, SpannableStringBuilder text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public CommonViewHolder setMovementMethod(int viewId) {
        TextView view = getView(viewId);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public CommonViewHolder setText(int viewId, int text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public CommonViewHolder setEnable(int viewId, boolean enable) {
        View view = getView(viewId);
        view.setEnabled(enable);
        return this;
    }

    public CommonViewHolder setChbText(int viewId, String text) {
        CheckBox view = getView(viewId);
        view.setText(text);
        return this;
    }

    public CommonViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    public CommonViewHolder setTextBackgroundColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public CommonViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public CommonViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    public CommonViewHolder setImageBackgroundResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setBackgroundResource(drawableId);
        return this;
    }



    public CommonViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public CommonViewHolder setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }
    public void setClickAble(int viewId, boolean c){
        View view = getView(viewId);
        view.setClickable(c);
    }

    public CommonViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    public CommonViewHolder setImageByUrl(int viewId, String url) {
        Glide.with(mContext).load(url).placeholder(R.mipmap.ic_place_holder).into((ImageView) getView
                (viewId));
        //ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId));
        return this;
    }

    public CommonViewHolder setImageByUrl(int viewId, String url, int defaultRes) {
        Glide.with(mContext).load(url).centerCrop().placeholder(R.mipmap.ic_place_holder).error(defaultRes).
                into((ImageView) getView
                        (viewId));
        return this;
    }

    public CommonViewHolder setImageByUrlAndResize(int viewId, String url, int size) {
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

    public CommonViewHolder setImageByUrlAndResize(int viewId, Uri url, int size) {
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

    public CommonViewHolder setSelect(int viewId, boolean select) {
        TextView view = getView(viewId);
        view.setSelected(select);
        //ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId));
        return this;
    }

    public CommonViewHolder setImageByUrlToRound(int viewId, String url) {
        Glide.with(mContext).load(url).asBitmap().centerCrop().
                transform(new CropCircleTransformation(mContext)).placeholder(R.mipmap.ic_default_header).into((ImageView) getView(viewId));
        return this;
    }

    public CommonViewHolder setImageByUrlToBlur(int viewId, String url) {
        Glide.with(mContext).load(url).centerCrop().bitmapTransform(new BlurTransformation(mContext)).into((ImageView) getView(viewId));
        return this;
    }

    public CommonViewHolder setImageByUrlToBlurAndRound(int viewId, String url,int dp) {
        Glide.with(mContext).load(url).bitmapTransform(new BlurTransformation(mContext),new RoundedCornersTransformation(mContext, dp, 0)).into((ImageView) getView(viewId));
        return this;
    }

    public CommonViewHolder setImageByUrlToRound(int viewId, String url, int defaultRes) {
        if (defaultRes == 0) {
            setImageByUrlToRound(viewId, url);
        } else
            Glide.with(mContext).load(url).asBitmap().centerCrop().
                    transform(new CropCircleTransformation(mContext)).error(defaultRes).into((ImageView) getView(viewId));
        return this;
    }

    public CommonViewHolder setImageByUrl(int viewId, String url, int size, int prefix) {
        ImageView imageView = getView(viewId);
        //Glide.with(mContext).load(url).placeholder(R.drawable.ic_place_holder).centerCrop().override(size, size)
        // .into(imageView);
        Glide.with(mContext).load(url).error(R.mipmap.ic_place_holder).centerCrop().into(imageView);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = size - prefix;
        layoutParams.height = size - prefix;
        imageView.requestLayout();
        return this;
    }


    public CommonViewHolder setRoundImageByUrl(int viewId, String url, int size, int dp) {
        ImageView imageView = getView(viewId);
        //Glide.with(mContext).load(url).placeholder(R.drawable.ic_place_holder).centerCrop().override(size, size)
        // .into(imageView);
        Glide.with(mContext).load(url).error(R.mipmap.ic_place_holder).centerCrop().bitmapTransform(new
                RoundedCornersTransformation(mContext, dp, 0)).into(imageView);
        if(size!=0){
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = size;
            layoutParams.height = size;
            imageView.requestLayout();
        }
        return this;
    }

    public CommonViewHolder setRoundImageByUrl2(int viewId, String url,int dp) {
        ImageView imageView = getView(viewId);
        //Glide.with(mContext).load(url).placeholder(R.drawable.ic_place_holder).centerCrop().override(size, size)
        // .into(imageView);
        Glide.with(mContext).load(url).error(R.mipmap.ic_place_holder).centerCrop().bitmapTransform(new
                RoundedCornersTransformation(mContext, dp, 0)).into(imageView);

        return this;
    }

    public CommonViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }


    public CommonViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }


    public CommonViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public CommonViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }


    public CommonViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public CommonViewHolder setVisibility(int viewId, int status) {
        View view = getView(viewId);
        view.setVisibility(status);
        return this;
    }


    public CommonViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public CommonViewHolder setWidth(int viewId, int size) {
        View v = getView(viewId);
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        layoutParams.width = size;
        v.requestLayout();
        return this;
    }

    public CommonViewHolder setChecked(int chDefault, boolean b) {
        CheckBox box = getView(chDefault);
        box.setChecked(b);
        return this;
    }

    public CommonViewHolder setTypeface(int viewId, int type) {
        TextView tv = getView(viewId);
        tv.setTypeface(Typeface.MONOSPACE, type);
        return this;
    }

    public CommonViewHolder setFlags(int viewId, int flag) {
        TextView tv = getView(viewId);
        tv.getPaint().setFlags(flag);
        return this;
    }


    public CommonViewHolder setSpanText(int viewId, Spanned text) {
        TextView view = getView(viewId);
        view.setText(text);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

}
