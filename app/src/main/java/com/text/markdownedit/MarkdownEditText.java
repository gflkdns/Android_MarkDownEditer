package com.text.markdownedit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class MarkdownEditText extends EditText implements MarkDown {

    MarkDownTheme theme = MarkDownTheme.sample;

    public MarkdownEditText(Context context) {
        super(context);
        init();
    }

    public MarkdownEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarkdownEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setGravity(Gravity.TOP);
        setLines(Integer.MAX_VALUE);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        // super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    /**
     * 在光标当前自然段的前面加上某某字符
     *
     * @param str
     */
    private void addTextToLineLeft(String str) {
        int index = getSelectionStart();
        if (index == 0) {
            getText().insert(0, str);
            return;
        }
        //当前位置向前找到\n 找到之后根据level加上对应的#
        String text = getText().toString();
        int p = text.substring(0, index).lastIndexOf("\n");
        if (p != -1) {
            getText().insert(p + 1, str);
        } else {
            getText().insert(0, str);
        }
    }

    @Override
    public void setTheme(MarkDownTheme theme) {
        if (theme == null) {
            return;
        }
        this.theme = theme;
    }

    @Override
    public void toHeadings(int level) {
        if (level > 6) {
            return;
        }
        StringBuilder heading = new StringBuilder();
        for (int i = 0; i < level; i++) {
            heading.append("#");
        }
        heading.append(' ');
        addTextToLineLeft(heading.toString());
    }

    @Override
    public void insertItalics(String value) {
        addLiftAndRightText("_", value, "_");
    }

    /**
     * 在光标位置的前后加入对应的字符串
     *
     * @param value
     */
    private void addLiftAndRightText(String lift, String value, String right) {
        StringBuilder builder = new StringBuilder();
        int start = getSelectionStart();
        int end = getSelectionEnd();
        if (value == null) {
            if (end - start == 0) {
                builder.append(lift).append(right);
                getText().insert(start, builder.toString());
                setSelection(start + lift.length());
                return;
            }
            value = getText().toString().substring(start, end);
        }
        builder.append(lift).append(value).append(right);
        getText().delete(start, end);
        getText().insert(getSelectionStart(), builder.toString());
    }

    @Override
    public void insertStrong(String value) {
        addLiftAndRightText("**", value, "**");
    }

    @Override
    public void insertStrikethrough(String value) {
        addLiftAndRightText("~~", value, "~~");
    }

    @Override
    public void toOrderedList() {
        //获得上一行的数值
        int prenum = 1;
        String text = getText().toString();
        if (text.length() == 0) {
            addTextToLineLeft(String.valueOf(prenum) + ". ");
        } else {
            int a = text.substring(0, getSelectionStart()).lastIndexOf("\n");
            int b = text.substring(0, a).lastIndexOf("\n");
            prenum = text.charAt(b);
            addTextToLineLeft(String.valueOf(prenum + 1) + ". ");
        }
    }

    @Override
    public void toUnorderedList() {

    }

    @Override
    public void insertLink(String title, String link) {
        //[title](link)
        addLiftAndRightText("[", title, "](" + link + ")");
    }

    @Override
    public void insertImage(String title, String imagelink) {
        addLiftAndRightText("![", title, "](" + imagelink + ")");
    }

    @Override
    public void toBlockquote() {
        addTextToLineLeft("> ");
    }

    @Override
    public void insertCodeBlocks(String code, String type) {
        addLiftAndRightText("```" + type + "\n", code, "```");
    }

    @Override
    public void insertInlineCode(String code) {
        addLiftAndRightText("`", code, "`");
    }

    @Override
    public void insertTables(int width, int height) {
        StringBuilder builder = new StringBuilder();
        //第一行 宽度
        for (int i = 0; i < width; i++) {
            builder.append(" | name" + i);
        }
        builder.append("|\n");
        for (int i = 0; i < width; i++) {
            builder.append(" | -- ");
        }
        builder.append("|\n");
        //高度
        StringBuilder heightitem = new StringBuilder();
        for (int i = 0; i < width; i++) {
            heightitem.append(" | item" + i);
        }
        heightitem.append("|\n");
        for (int i = 0; i < height; i++) {
            builder.append(heightitem);
        }
        getText().insert(getSelectionStart(), builder.toString());
    }

    @Override
    public void insertHorizontalRule() {
        addLiftAndRightText(null, null, "\n---");
    }
}
