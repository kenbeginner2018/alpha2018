package library.checker;

public class ValidateChecker {

	// エラーメッセージ一覧
	// ユーザID_エラーメッセージ
	private final String userIdErrorMessage   = "ユーザIDは「アルファベット小文字1文字 + 半角数字8桁」です。";
	// ユーザPw_エラーメッセージ
	private final String userPwErrorMessage   = "パスワードは5文字以上10文字以下です。";
	// 学年_エラーメッセージ
	private final String gradeErrorMessage    = "学年は半角数字1桁で入力してください。";
	// 出版年_エラーメッセージ
	private final String pubYearErrorMessage  = "出版年は西暦(半角数字4桁)で入力してください";
	// 在庫数_エラーメッセージ
	private final String stockNumErrorMessage = "在庫数は1以上(半角数字1桁)で入力してください";

	// 各メソッドの戻り値 true: 一致, false :不一致

	public String getUserIdErrorMessage() {
		return userIdErrorMessage;
	}

	public String getUserPwErrorMessage() {
		return userPwErrorMessage;
	}

	public String getGradeErrorMessage() {
		return gradeErrorMessage;
	}

	public String getPubYearErrorMessage() {
		return pubYearErrorMessage;
	}

	public String getStockNumErrorMessage() {
		return stockNumErrorMessage;
	}

	// 受け取った文字列がユーザIDの形式と一致するかチェック
	public boolean checkUserId(String userId) {
		// ユーザIDを表す正規表現
		String userIdPattern = "^[a-b]{1}[0-9]{8}$";
		if (userId.matches(userIdPattern)) {
			return true;
		} else {
			return false;
		}
	}

	// 受け取った文字列がユーザPwの形式と一致するかチェック
	public boolean checkUserPw(String userPw) {
		// パスワードの長さ (min以上 max以下)
		int min = 5;
		int max = 10;
		if ((userPw.length() >= min) && (userPw.length() <= max)) {
			return true;
		} else {
			return false;
		}
	}

	// 受け取った文字列が学年の形式と一致するかチェック
	public boolean checkGrade(String grade) {
		// 学年を表す正規表現 (数字1桁)
		String gradePattern = "^[0-9]{1}$";
		if (grade.matches(gradePattern)) {
			return true;
		} else {
			return false;
		}
	}

	// 受け取った文字列が出版年の形式と一致するかチェック
	public boolean checkPubYear(String pubYear) {
		// 学年を表す正規表現 (数字4桁)
		String pubYearPattern = "^[12]{1}[0-9]{3}$";
		if (pubYear.matches(pubYearPattern)) {
			return true;
		} else {
			return false;
		}
	}

	// 受け取った文字列が在庫数の形式と一致するかチェック
	public boolean checkStockNum(String stockNum) {
		// 1以上の数字かチェック
		int num;
		try {
			num = Integer.parseInt(stockNum);
			if(num > 0) {
				return true;
			} else {
				return false;
			}
		} catch(NumberFormatException e) {
			return false;
		}
	}
}
