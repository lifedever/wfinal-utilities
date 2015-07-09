package io.github.gefangshuai.wfinal.model.search;

/**
 * sql连接符
 * Created by gefangshuai on 2015/7/9.
 */
public enum Link {
    AND("and"), OR("or");

    Link(String link) {
        this.link = link;
    }

    private String link;

    public String getLink() {
        return link;
    }
}
