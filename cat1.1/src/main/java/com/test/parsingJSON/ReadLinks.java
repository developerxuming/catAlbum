package com.test.parsingJSON;

import com.test.entity.entityAPI.LinksInfo;
import org.json.JSONObject;

public class ReadLinks {
    protected static LinksInfo prasingLink(JSONObject links) {
        String url = links.getString("url");
        String html = links.getString("html");
        String bbcode = links.getString("bbcode");
        String markdown = links.getString("markdown");
        String markdown_with_link = links.getString("markdown_with_link");
        String thumbnail_url = links.getString("thumbnail_url");
        String delete_url = links.getString("delete_url");
        LinksInfo linksInfo = new LinksInfo(url, html, bbcode, markdown, markdown_with_link, thumbnail_url, delete_url);
        return  linksInfo;
    }
}
