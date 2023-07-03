package com.swp.vnhistory.dto.request;

public class SearchBlogForm {

	private long blogId;
	private String contentInShort;
	
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}
	public String getContentInShort() {
		return contentInShort;
	}
	public void setContentInShort(String contentInShort) {
		this.contentInShort = contentInShort;
	}
	public SearchBlogForm(long blogId, String contentInShort) {
		super();
		this.blogId = blogId;
		this.contentInShort = contentInShort;
	}
	public SearchBlogForm() {
		super();
	}
	
	
}
