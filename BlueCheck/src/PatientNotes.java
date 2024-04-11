import java.sql.Date;

public class PatientNotes {
	
	
	private Date date; 
	private String note; 
	private String noteSubject; 
	private int id; 
	
	
	public PatientNotes(Date d, String s, String ns, int i) {
		this.setDate(d);
		this.setNote(s);
		this.setId(i);
		this.setNoteSubject(ns);
	}


	public String getDate() {
		return date.toString();
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNoteSubject() {
		return noteSubject;
	}


	public void setNoteSubject(String noteSubject) {
		this.noteSubject = noteSubject;
	}

	
	

}
