package exercise06.link.bean;

/**
 * リンク種別を表すクラスです。
 *
 */
public class LinkCategoryBean {

	/** リンク種別ID */
	private String categoryId;

	/** リンク種別名 */
	private String categoryName;

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

}