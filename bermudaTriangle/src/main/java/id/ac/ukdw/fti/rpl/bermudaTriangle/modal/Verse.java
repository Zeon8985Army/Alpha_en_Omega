package id.ac.ukdw.fti.rpl.bermudaTriangle.modal;

public class Verse {
    private int verseId;
    private String verse;
    private String verseText;

    public void setVerseId(int verseId) {
        this.verseId = verseId;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public void setVerseText(String verseText) {
        this.verseText = verseText;
    }

    public int getVerseId() {
        return this.verseId;
    }

    public String getVerse() {
        return this.verse;
    }

    public String getVerseText() {
        return this.verseText;
    }
}