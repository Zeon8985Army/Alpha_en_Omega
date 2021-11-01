package id.ac.ukdw.fti.rpl.bermudaTriangle.modal;

public class Verse {
    private int verseId;
    private String verse;
    private String verseText;
    private String people;
    private String places;

    public void setVerseId(int verseId) {
        this.verseId = verseId;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public void setVerseText(String verseText) {
        this.verseText = verseText;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setPlaces(String places) {
        this.places = places;
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

    public String getPeople() {
        return this.people;
    }

    public String getPlaces() {
        return this.places;
    }
}