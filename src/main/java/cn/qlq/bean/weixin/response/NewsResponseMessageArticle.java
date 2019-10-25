package cn.qlq.bean.weixin.response;

import org.apache.commons.lang.ArrayUtils;

public class NewsResponseMessageArticle {

	private NewsResponseMessageArticleItem[] items = new NewsResponseMessageArticleItem[0];

	public NewsResponseMessageArticleItem[] getItems() {
		return items;
	}

	public void setItems(NewsResponseMessageArticleItem[] items) {
		this.items = items;
	}

	public void addNewsResponseMessageArticleItem(NewsResponseMessageArticleItem itemAdd) {
		items = (NewsResponseMessageArticleItem[]) ArrayUtils.add(items, itemAdd);
	}

}
