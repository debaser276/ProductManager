package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {

    ProductRepository repository = new ProductRepository();

    ProductManager manager = new ProductManager(repository);
    
    Book book1 = new Book(1, "book1", 500, "author1");
    Book book2 = new Book(2, "book2", 750, "author2");
    Book book3 = new Book(3, "book3", 800, "author3");
    Book book4 = new Book(4, "book4", 900, "author1");
    Smartphone smartphone1 = new Smartphone(5, "smartphone1", 5000, "manufacturer1");
    Smartphone smartphone2 = new Smartphone(6, "smartphone2", 7500, "manufacturer2");
    Smartphone smartphone3 = new Smartphone(7, "smartphone3", 8000, "manufacturer3");
    Smartphone smartphone4 = new Smartphone(8, "smartphone4", 9000, "manufacturer2");

    @BeforeEach
    void addProducts() {
        manager.save(book1);
        manager.save(book2);
        manager.save(book3);
        manager.save(book4);
        manager.save(smartphone1);
        manager.save(smartphone2);
        manager.save(smartphone3);
        manager.save(smartphone4);
    }
    
    @Test
    void shouldManagerFindBookByName() {
        Product[] expected = new Product[] { new Book(2, "book2", 750, "author2") };
        Product[] actual = manager.searchBy("book2");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldManagerFindBooksByAuthor() {
        Product[] expected = new Product[] {
            new Book(1, "book1", 500, "author1"),
            new Book(4, "book4", 900, "author1")
        };
        Product[] actual = manager.searchBy("author1");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldManagerFindSmartphoneByName() {
        Product[] expected = new Product[] { new Smartphone(7, "smartphone3", 8000, "manufacturer3") };
        Product[] actual = manager.searchBy("smartphone3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldManagerFindSmartphonesByManufacturer() {
        Product[] expected = new Product[] {
            new Smartphone(6, "smartphone2", 7500, "manufacturer2"),
            new Smartphone(8, "smartphone4", 9000, "manufacturer2")
        };
        Product[] actual = manager.searchBy("manufacturer2");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldManagerFindNothing() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("nothing");
        assertArrayEquals(expected, actual);
    }

}