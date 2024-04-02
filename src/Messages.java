
public class Messages {

	int id;
	String text, senderID, receiverID;
	public Messages(int id, String text, String senderID, String receiverID) {

			this.id = id;
			this.text = text;
			this.senderID = senderID;
			this.receiverID = receiverID;
	}

		
		public int getID(){
			 return id;
		}

		public String getText(){
			 return text;
		}
		public String getSenderID(){
			 return senderID;
		}
		public String getReceiverID(){
			 return receiverID;
		}

		
		public void setID(int newid){
			  id = newid;
		}

		public void setText(String newtext){
			text = newtext;
		}
		public void setSenderID(String newsid){
			senderID = newsid;
		}
		public void setreceiverID(String newrid){
			receiverID = newrid;
		}

		
		public String toString(){ 
			return (this.getID()+"\t|"+this.getText()+"\t|"+this.getSenderID()+"\t|\t"+this.getReceiverID()); 
		}

}
