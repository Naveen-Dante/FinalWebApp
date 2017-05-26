package com.epam.domain;

public class UserBook extends Book{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7844602956348478460L;
	private boolean favourite;

	public UserBook() {
		super();
	}

	public UserBook(int id, String title, String author, BookType type, boolean favourite) {
		super(id, title, author, type);
		this.favourite = favourite;
	}

	

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (favourite ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBook other = (UserBook) obj;
		if (favourite != other.favourite)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBook [favourite=" + favourite + ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getAuthor()=" + getAuthor() + ", getType()=" + getType() + "]";
	}

	
	
	
}
