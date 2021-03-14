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

import android.graphics.drawable.ShapeDrawable;
import android.widget.EditText;

/**
 * Editor cursor
 * Created by hanks on 2017/1/6.
 */

public class LineSpaceCursorDrawable extends ShapeDrawable {
    private final int mHeight;
    private final EditText mRichEditText;
    public LineSpaceCursorDrawable(int cursorColor, int cursorWidth, int cursorHeight, EditText editText) {
        mHeight = cursorHeight;
        this.mRichEditText = editText;
        setDither(false);
        getPaint().setColor(cursorColor);
        setIntrinsicWidth(cursorWidth);
    }

    public void setBounds(int left, int top, int right, int bottom) {
        if (mRichEditText.getText().toString().endsWith("\n") && mRichEditText.getSelectionStart() == mRichEditText.length()) {
            int lineSpace = (int) mRichEditText.getLineSpacingExtra();
            super.setBounds(left, top + lineSpace, right, this.mHeight + top + lineSpace);
        } else {
            super.setBounds(left, top, right, this.mHeight + top);
        }
    }

}
