package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository repository = new ProductRepository();

    private Book coreJava = new Book(7, "Hand", 300, "Chinar");
    private Book two = new Book(8, "Home", 100, "Miyoko");
    private Book tree = new Book(9, "Sun", 200, "Kleo Leo");
    private Book four = new Book(10, "Sun2", 400, "Kolenio");
    private Book six = new Book(15, "Moon", 200, "Kleo Lep");
    private Smartphone samsung = new Smartphone(22, "Samsung", 1000, "Korea");
    private Smartphone nokia = new Smartphone(33, "Nokia", 800, "Finland");
    private Smartphone huawei = new Smartphone(44, "Huawei", 500, "Chinas");
    private Smartphone huawei70 = new Smartphone(45, "Huawei", 500, "China");

    @Test
    public void shouldRemoveExistingProduct() {
        repository.save(coreJava);
        repository.save(huawei);
        repository.save(huawei70);
        repository.save(six);
        repository.removeById(15);

        Product[] expected = new Product[]{huawei70, huawei, coreJava};
        Product[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveNonExistentProduct() {
        repository.save(nokia);
        repository.save(huawei);
        repository.save(samsung);
        repository.save(coreJava);
        repository.save(two);
        repository.save(four);
        repository.save(six);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(50);
        });
    }
}