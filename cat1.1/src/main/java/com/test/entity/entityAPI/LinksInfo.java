package com.test.entity.entityAPI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksInfo {
    private String url;  //图片访问 url
    private String html;  // 包含图片的HTML代码，可以直接在网页中嵌入图片的HTML片段。
    private String bbcode;  //BBCode 是一种用于在论坛、博客等网络平台上添加格式和样式的标记语言，可以用于在支持 BBCode 的平台上显示图片。
    private String markdown;  // 这里是图片在 Markdown 格式下的描述。
    private String markdown_with_link; // 链接的 Markdown 形式,图片在 Markdown 格式下的描述，并包含了图片的链接信息。
    private String thumbnail_url;  //缩略图 url
    private String delete_url;  //图片删除 url

    public LinksInfo(String url, String html, String bbcode, String markdown, String markdown_with_link, String thumbnail_url, String delete_url) {
        this.url = url;
        this.html = html;
        this.bbcode = bbcode;
        this.markdown = markdown;
        this.markdown_with_link = markdown_with_link;
        this.thumbnail_url = thumbnail_url;
        this.delete_url = delete_url;

    }
}
