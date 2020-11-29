package ua.itea;

public class Telega {
    private int kartoha;
    private String location;

    public Telega(String location) {
        this.location = location;
    }

    public void putKartoha(int kartoha) {
        this.kartoha = kartoha;
    }

    public int getKartoha() {
        return kartoha;
    }

    public boolean isEmpty(){
        return kartoha <1;
    }

    public boolean isPogruzheno(){
        return kartoha >0;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}