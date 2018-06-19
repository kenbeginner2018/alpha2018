package library.checker;

public class ValidateChecker {

	// 各メソッドの戻り値 true: 一致, false :不一致

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
}
