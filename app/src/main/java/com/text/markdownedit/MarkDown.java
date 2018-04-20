package com.text.markdownedit;

import android.graphics.Color;
import android.util.Range;

/**
 * @author miqt
 * @time 2018/4/20 0020
 */

interface MarkDown {
    public static final String LIST_TYPE_NUMBER = "1. ";
    public static final String LIST_TYPE_DOT = "- ";

    void preview();

    /**
     * 设置markdown风格，默认为MarkDownTheme.sample
     *
     * @param theme markdown风格
     * @see MarkDownTheme
     */
    void setTheme(MarkDownTheme theme);

    /**
     * 当前行设为标题
     *
     * @param level 标题等级
     */
    void toHeadings(int level);

    /**
     * 插入斜体的字符 _underscores_
     *
     * @param value 如果为空，光标位于中间，如果不为空，光标位于最后
     */
    void insertItalics(String value);

    /**
     * 插入加粗字符 **two asterisks**
     *
     * @param value 如果为空，光标位于中间，如果不为空，光标位于最后
     */
    void insertStrong(String value);

    /**
     * 插入被划掉的字符 ~~two tildes~~
     *
     * @param value 如果为空，光标位于中间，如果不为空，光标位于最后
     */
    void insertStrikethrough(String value);

    /**
     * 当前行变为有序list项
     * 1. ***
     * 2. ***
     */
    void toOrderedList();

    /**
     * 当前行变为无序list项
     * · ***
     * · ***
     */
    void toUnorderedList();

    /**
     * 当前光标位置插入一个超链接
     *
     * @param title
     * @param link
     */
    void insertLink(String title, String link);

    /**
     * 当前位置插入一张图像
     *
     * @param title
     * @param imagelink
     */
    void insertImage(String title, String imagelink);

    /**
     * 将当前行变为快引用
     * > abc
     */
    void toBlockquote();


    /**
     * 插入代码块
     * ```java
     * code
     * ```
     *
     * @param code
     * @param type
     */
    void insertCodeBlocks(String code, String type);

    /**
     * 插入代码段
     * `code`
     *
     * @param code
     */
    void insertInlineCode(String code);

    /**
     * 插入一个表格
     *
     * @param width
     * @param height
     */
    void insertTables(int width, int height);

    /**
     * 插入水平线
     * ---
     */
    void insertHorizontalRule();


    enum MarkDownTheme {
        sample(Color.GREEN, Color.BLUE, Color.YELLOW);
        private int titlecolor;
        private int imagelink;
        private int urllink;

        /**
         * markdown风格
         *
         * @param titlecolor 标题颜色
         * @param imagelink  图片引用颜色
         * @param urllink    url引用颜色
         */
        MarkDownTheme(int titlecolor, int imagelink, int urllink) {
            this.titlecolor = titlecolor;
            this.imagelink = imagelink;
            this.urllink = urllink;
        }
    }
}
