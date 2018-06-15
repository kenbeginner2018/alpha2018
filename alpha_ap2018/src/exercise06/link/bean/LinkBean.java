package exercise06.link.bean;

/**
 * リンクを表すクラスです。
 *
 */
public class LinkBean {

	/** リンクID */
	private String linkId;

	/** リンク種別ID */
	private String categoryId;

	/** リンク種別名 */
	private String categoryName;

	/** リンク名 */
	private String linkName;

	/** URL */
	private String url;

	/** リンクIDを取得します。 */
	public String getLinkId() {
		return linkId;
	}

	/** リンクIDを設定します。 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	/** リンク種別IDを取得します。 */
	public String getCategoryId() {
		return categoryId;
	}

	/** リンク種別IDを設定します。 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/** リンク種別名を取得します。 */
	public String getCategoryName() {
		return categoryName;
	}

	/** リンク種別名を設定します。 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/** リンク名を取得します。 */
	public String getLinkName() {
		return linkName;
	}

	/** リンク名を設定します。 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/** URLを取得します。 */
	public String getUrl() {
		return url;
	}

	/** URLを設定します。 */
	public void setUrl(String url) {
		this.url = url;
	}

}