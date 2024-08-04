package rest;

public enum EContentType {

	JSON("application/json"), 
	Text("text/plain"), 
	FORM_DATA("multipart/form-data");

	String contentType;

	EContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {

		return contentType;
	}

}