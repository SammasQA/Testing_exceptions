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
    void shouldAddProductSuccessfully() {
        // given
        Product product = new Product(1, "Книга", 500);

        // when
        repository.add(product);

        // then
        Product[] expected = {product};
        assertArrayEquals(expected, repository.findAll());
    }

    @Test
    void shouldThrowAlreadyExistsExceptionWhenAddingProductWithDuplicateId() {
        // given
        Product product1 = new Product(1, "Книга", 500);
        Product product2 = new Product(1, "Ручка", 50); // тот же id

        repository.add(product1);

        // when + then
        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class,
                () -> repository.add(product2));

        assertEquals("Product with id: 1 already exists", exception.getMessage());
        // Проверим, что первый товар остался, второй не добавился
        Product[] expected = {product1};
        assertArrayEquals(expected, repository.findAll());
    }
}