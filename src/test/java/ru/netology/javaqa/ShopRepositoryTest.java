package ru.netology.javaqa;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    private ShopRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ShopRepository();
    }

    @Test
    void shouldRemoveExistingProduct() {
        // given
        Product product1 = new Product(1, "Книга", 500);
        Product product2 = new Product(2, "Ручка", 50);
        repository.add(product1);
        repository.add(product2);


        repository.remove(1);


        Product[] expected = {product2};
        assertArrayEquals(expected, repository.findAll());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenRemovingNonExistingProduct() {
        // given
        repository.add(new Product(1, "Книга", 500));

        // when + then
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> repository.remove(100));

        assertEquals("Element with id: 100 not found", exception.getMessage());
    }
}