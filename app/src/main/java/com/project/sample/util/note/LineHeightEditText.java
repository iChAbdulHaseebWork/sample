/*
 * Copyright (C) 2017 Hanks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.project.sample.util.note;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.project.sample.R;
import com.project.sample.util.DimensionConverter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * fix EditText lineHeight and cursor effect when set lineSpaceExtra or lineSpaceMult
 * Created by hanks on 16/7/2.
 */
public class LineHeightEditText extends AppCompatEditText {

    private float mSpacingMult = 1f;
    private float mSpacingAdd = 0f;
    private TextWatcher textWatcher;
    private int cursorColor;
    private int cursorWidth;
    private int cursorHeight;
    private final Rect mRect;
    private final Paint mPaint;
    private final Paint mPaint1;
    private final Context context;

    public LineHeightEditText(Context context) {
        this(context, null);
    }

    public LineHeightEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public LineHeightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#d9d9d9"));
        mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setColor(Color.parseColor("#fec6c6"));
        // init
        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.LineHeightEditText, defStyleAttr, 0);
        cursorColor = a.getColor(R.styleable.LineHeightEditText_cursorColor, getColorAccent(context));
        cursorHeight = a.getDimensionPixelSize(R.styleable.LineHeightEditText_cursorHeight, (int) (1.25 * getTextSize()));
        cursorWidth = a.getDimensionPixelSize(R.styleable.LineHeightEditText_cursorWidth, 6);
        a.recycle();

        getLineSpacingAddAndLineSpacingMult();
        setTextCursorDrawable();
        listenTextChange();
    }

    private int getColorAccent(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        return typedValue.data;
    }

    private void listenTextChange() {
        addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (textWatcher != null) {
                    textWatcher.beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setLineSpacing(0f, 1f);
                setLineSpacing(mSpacingAdd, mSpacingMult);
                if (textWatcher != null) {
                    textWatcher.onTextChanged(s, start, before, count);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textWatcher != null) {
                    textWatcher.afterTextChanged(s);
                }
            }
        });
    }

    private void setTextCursorDrawable() {
        try {
            Method method = TextView.class.getDeclaredMethod("createEditorIfNeeded");
            method.setAccessible(true);
            method.invoke(this);
            Field field1 = TextView.class.getDeclaredField("mEditor");
            Field field2 = Class.forName("android.widget.Editor").getDeclaredField("mCursorDrawable");
            field1.setAccessible(true);
            field2.setAccessible(true);
            Object arr = field2.get(field1.get(this));
            Array.set(arr, 0, new LineSpaceCursorDrawable(getCursorColor(), getCursorWidth(), getCursorHeight(), this));
            Array.set(arr, 1, new LineSpaceCursorDrawable(getCursorColor(), getCursorWidth(), getCursorHeight(), this));
        } catch (Exception ignored) {
        }
    }

    private void getLineSpacingAddAndLineSpacingMult() {
        try {
            Field mSpacingAddField = TextView.class.getDeclaredField("mSpacingAdd");
            Field mSpacingMultField = TextView.class.getDeclaredField("mSpacingMult");
            mSpacingAddField.setAccessible(true);
            mSpacingMultField.setAccessible(true);
            mSpacingAdd = mSpacingAddField.getFloat(this);
            mSpacingMult = mSpacingMultField.getFloat(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCursorColor() {
        return cursorColor;
    }

    public void setCursorColor(int cursorColor) {
        this.cursorColor = cursorColor;
        setTextCursorDrawable();
        invalidate();
    }

    public int getCursorHeight() {
        return cursorHeight;
    }

    public void setCursorHeight(int cursorHeight) {
        this.cursorHeight = cursorHeight;
        setTextCursorDrawable();
        invalidate();
    }

    public int getCursorWidth() {
        return cursorWidth;
    }

    public void setCursorWidth(int cursorWidth) {
        this.cursorWidth = cursorWidth;
        setTextCursorDrawable();
        invalidate();
    }

    /**
     * Adds a TextWatcher to the list of those whose methods are called
     * whenever this TextView's text changes.
     *
     * @param textWatcher TextWatcher
     */
    public void addTextWatcher(TextWatcher textWatcher) {
        this.textWatcher = textWatcher;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int line_height = getLineHeight();

        int count = height / line_height;

        if (getLineCount() > count)
            count = getLineCount();

        Rect r = mRect;
        int baseline = getLineBounds(0, r);

        for (int i = 0; i < count; i++) {

            canvas.drawLine(r.left - DimensionConverter.convertDpToPx(context,24f),
                    baseline + DimensionConverter.convertDpToPx(context,8f),
                    r.right + DimensionConverter.convertDpToPx(context,24f),
                    baseline + DimensionConverter.convertDpToPx(context,8f), mPaint);
            baseline += getLineHeight();
            canvas.drawLine(DimensionConverter.convertDpToPx(context,15.5f), getLineHeight()-baseline,
                    DimensionConverter.convertDpToPx(context,15.5f), baseline, mPaint1);
        }

        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // set measure dimension again, LINE_BREAK="\n"
        if (getText().toString().endsWith("\n")) {
            setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredHeight() + getLineSpacingExtra()));
        }
    }
}
