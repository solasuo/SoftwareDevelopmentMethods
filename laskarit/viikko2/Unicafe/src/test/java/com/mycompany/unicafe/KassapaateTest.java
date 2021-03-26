package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(500);
    }
       
    @Test
    public void kassassaRahaaPalauttaaoikeanSumman() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test 
    public void myytyjenLounaidenMaaraOikein() {
        assertEquals(0, paate.edullisiaLounaitaMyyty()+paate.maukkaitaLounaitaMyyty());
    }    
   
    
    @Test
    public void syoEdullisestiVaihtorahaOikein() {
        paate.syoEdullisesti(500);
        assertEquals(260, paate.syoEdullisesti(500));
    }
    
    @Test
    public void syoMaukkaastiVaihtorahaOikein() {
        paate.syoMaukkaasti(500);
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    @Test
    public void riittamatonKateismaksuPalautuu() {
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    @Test
    public void lounaidenMaaraKasvaaOikein() {
        paate.syoEdullisesti(500);
        paate.syoMaukkaasti(500);
        paate.syoMaukkaasti(300);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(3, paate.edullisiaLounaitaMyyty()+paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttimaksuEiKasvataKassaa() {
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test 
    public void kortinLatausKasvattaaKassaa() {
        paate.lataaRahaaKortille(kortti, 800);
        assertEquals(100800, paate.kassassaRahaa());
    }
    
    @Test
    public void riittamatonKorttimaksu() {
        paate.syoMaukkaasti(kortti);        
        assertFalse(null, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void negatiivistaSummaaEiLadata() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
    }    
}    