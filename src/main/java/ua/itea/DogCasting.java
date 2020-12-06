package ua.itea;

import java.util.Optional;

public class DogCasting {
    private ColourSet color;
    private int age;
    private boolean blokhable;
    private boolean isHotDog;
    private Class<? extends SomeDogInterface> dogClass;

    public DogCasting(Class<? extends SomeDogInterface> dogClass) {
        this.dogClass = dogClass;
        blokhable = dogClass.isAnnotationPresent(Blokhable.class);

        isHotDog = !blokhable
                && (color != ColourSet.BLACK || Math.random() < 0.5)
                && age <= 3;

        color = Optional.ofNullable(dogClass.getDeclaredAnnotation(Colour.class))
                .map(Colour::colour)
                .orElse(ColourSet.BLACK);

        age = Optional.ofNullable(dogClass.getAnnotation(DogYearsOld.class))
                .map(DogYearsOld::old)
                .orElse(2);
        }

    public boolean isHotDog() {
        return isHotDog;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(200);

        builder.append("  Раса: " + color + "\n");
        builder.append("  Жизненный опыт (лет жизни): " + age +  "\n");
        builder.append("  Уровень блохастости: " + blokhable + "\n");
        builder.append("  Судьба: " + (isHotDog() ? "на хот-дог" : "на мыло") + "\n");
        System.out.println("=======================================================");
        return builder.toString();
    }
}
