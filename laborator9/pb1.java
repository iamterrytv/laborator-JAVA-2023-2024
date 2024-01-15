package laborator9;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class pb1{
    public static void main(String[] args) {
        List<String> listaCuDuplicate = Arrays.asList("a", "b", "c", "b", "d", "a", "e");

        List<String> listaFaraDuplicate = listaCuDuplicate.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Lista cu duplicate: " + listaCuDuplicate);
        System.out.println("Lista fără duplicate: " + listaFaraDuplicate);

    }
}