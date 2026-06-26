public class InterfaceDemo {

    public static void main(String[] args) {

        Playable guitar = new Guitar();
        Playable piano = new Piano();

        guitar.play();
        piano.play();
    }
}