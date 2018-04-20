package com.text.markdownedit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MarkdownEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_markdown_edit_text);
        editText = findViewById(R.id.makedown);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ib_b:
                editText.insertStrong(null);
                break;
            case R.id.ib_blockquote:
                editText.toBlockquote();
                break;
            case R.id.ib_camera:
                editText.insertImage("hello", "https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=5a0f156f57b5c9ea7df305e3e538b622/cf1b9d16fdfaaf519d4aa2db805494eef01f7a2c.jpg");
                break;
            case R.id.ib_code:
                editText.insertCodeBlocks(" @Override\n" +
                        "    protected void onCreate(Bundle savedInstanceState) {\n" +
                        "        super.onCreate(savedInstanceState);\n" +
                        "        setContentView(R.layout.sample_markdown_edit_text);\n" +
                        "        editText = findViewById(R.id.makedown);\n" +
                        "    }", "java");
                break;
            case R.id.ib_header:
                editText.toHeadings(1);
                break;
            case R.id.ib_link:
                editText.insertLink("baidu", "http://image.baidu.com/");
                break;
            case R.id.ib_list:
                editText.toUnorderedList();
                break;
            case R.id.ib_pic:
                editText.insertImage("hello", "https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=5a0f156f57b5c9ea7df305e3e538b622/cf1b9d16fdfaaf519d4aa2db805494eef01f7a2c.jpg");
                break;
            case R.id.ib_ruler:
                editText.insertHorizontalRule();
                break;
            case R.id.ib_table:
                editText.insertTables(3, 3);
                break;
            default:
                break;
        }
    }
}
