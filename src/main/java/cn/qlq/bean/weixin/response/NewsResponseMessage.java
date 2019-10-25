package cn.qlq.bean.weixin.response;

/**
 * 图文消息
 * 
 * @author Administrator
 *
 */
public class NewsResponseMessage extends AbstractResponseMessage {

	/**
	 * 图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
	 */
	private String ArticleCount;

	/**
	 * 图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
	 */
	private NewsResponseMessageArticle Articles = new NewsResponseMessageArticle();

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public NewsResponseMessageArticle getArticles() {
		return Articles;
	}

	public void setArticles(NewsResponseMessageArticle articles) {
		Articles = articles;
	}

}
