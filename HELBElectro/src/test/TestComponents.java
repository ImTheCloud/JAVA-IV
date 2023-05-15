package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestComponents {
    private Produit produit;
 
    @BeforeEach
    public void setUp() {
        produit = new Produit();
    }
    @Test
    public void testGetVentes() {
        assertEquals(0, produit.getVentes());
    }
    @Test
    public void testIncrementerVentes() {
        produit.incrementerVentes();
        assertEquals(1, produit.getVentes());
    }
}
