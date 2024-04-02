
public class Comment {
	private int comment_id;
	private int post_id;
	private String user_id;
	private String text;
	
	public Comment(int comment_id, int post_id, String user_id, String text) {
		this.comment_id = comment_id;
		this.post_id = post_id;
		this.user_id = user_id;
		this.text = text;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return comment_id + "|\t" + post_id + "|\t" + user_id + "|\t" + text;
	}
	
	
	
}
