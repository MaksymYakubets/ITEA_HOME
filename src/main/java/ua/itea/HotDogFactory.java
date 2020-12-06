package ua.itea;

public class HotDogFactory {
    public HotDog createHotDog(Class<? extends SomeDogInterface> someDogClass) {
        DogCasting dogClass = new DogCasting(someDogClass);
        System.out.println(dogClass);

        return dogClass.isHotDog() ? new HotDog() : null;
    }
}
