package com.epam.domain;

public class BookInfo extends Book{

	/**
	 * 
	 */
	private static final long serialVersionUID = 967194468562548074L;
	
	String imageUrl;
	String description;
	String language;
	
	public BookInfo() {
	}

	public BookInfo(int id, String title, String author, BookType type, String imageUrl, String description) {
		super(id, title, author, type);
		this.imageUrl = imageUrl;
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
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
		BookInfo other = (BookInfo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookInfo [imageUrl=" + imageUrl + ", description=" + description + ", language=" + language
				+ ", getId()=" + getId() + ", getTitle()=" + getTitle() + ", getAuthor()=" + getAuthor()
				+ ", getType()=" + getType() + "]";
	}

}
