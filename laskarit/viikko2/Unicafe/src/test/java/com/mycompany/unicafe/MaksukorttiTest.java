package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        String vastaus = kortti.toString();
        assertEquals("saldo: 100.0", kortti.toString());
    }
    
    @Test
    public void oikeaSaldoPalautuu () {
        kortti.otaRahaa(1000);
        assertEquals(9000, kortti.saldo());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(800);
        assertEquals("saldo: 108.0", kortti.toString());
    }
    
    @Test 
    public void eiVoiLadataNegatiivistaSummaa() {
        kortti.lataaRahaa(-800);
        assertEquals("saldo: 100.0", kortti.toString());
    }
    
    @Test
    public void rahanOttoVahentaaSaldoa() {
        kortti.otaRahaa(800);
        assertEquals("saldo: 92.0", kortti.toString());
    }
    
    @Test
    public void rahanOttoEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(9900);
        kortti.otaRahaa(500);
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void rahanOtonOnnistuminen() {
        assertTrue(kortti.otaRahaa(500));
    }
    
    @Test
    public void rahanOtonEpaonnistuminen() {
        assertFalse(kortti.otaRahaa(11000));
    }
}
