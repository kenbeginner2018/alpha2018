package library.bean;

import java.io.Serializable;

public class GenreBean implements Serializable{

	private String genreId;
	private String genreName;

	public String getGenreId() {
		return genreId;
	}
	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
