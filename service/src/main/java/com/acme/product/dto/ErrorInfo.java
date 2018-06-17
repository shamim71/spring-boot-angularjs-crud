package com.acme.product.dto;

public class ErrorInfo {

    private String url;
    
    private String message;
    
    private String code;
    public ErrorInfo(String url, String message) {
	super();
	this.url = url;
	this.message = message;
    }
    
    public ErrorInfo(String url, String message, String code) {
	super();
	this.url = url;
	this.message = message;
	this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
