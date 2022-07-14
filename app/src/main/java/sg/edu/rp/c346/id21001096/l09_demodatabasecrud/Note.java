package sg.edu.rp.c346.id21001096.l09_demodatabasecrud;

import java.io.Serializable;

public class Note implements Serializable {
//Pass the class object to another Activity.
//Implementing the serializable interface lets us pass object as a Serializable object to another activity
    private 	int id;
    private 	String noteContent;

    public Note( int id, String noteContent  ) {
        this.id = id;
        this.noteContent = noteContent;
    }

    public int getId() {  return id;  }

    public String getNoteContent() { return noteContent; }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public String toString() { return "ID:" + id + ", " + noteContent;  }

}


