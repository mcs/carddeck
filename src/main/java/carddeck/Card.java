package carddeck;

public record Card(String value) {

    @Override
    public String toString() {
        return value;
    }
}