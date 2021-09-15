/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.lab.pkg1;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pmele
 */
public class RandSeqTest {
    private static final char[] nucAlphabet = new char[] {'A', 'C', 'G', 'T'};
    private static final char[] aminoAlphabet = new char[] {'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'Y'};
    private static ArrayList<Character> nucCharacters = new ArrayList<>();
    private static ArrayList<Character> aminoCharacters = new ArrayList<>();
    
    public RandSeqTest() {
        for (int i = 0; i < nucAlphabet.length; i++) {
            nucCharacters.add(nucAlphabet[i]);
        }
        for (int i = 0; i < aminoAlphabet.length; i++) {
            aminoCharacters.add(aminoAlphabet[i]);
        }
    }
    
    /**
     * Test of generateRandomSequence method, of class RandSeq.
     */
    @org.junit.Test
    public void testGenerateRandomSequence_int() throws Exception {
        String testSeq = RandSeq.generateRandomSequence(5);
        assertEquals(5, testSeq.length());
        for (int i = 0; i < testSeq.length(); i++) {
            assertTrue(nucCharacters.contains(testSeq.charAt(i)));
        }
        boolean successfulException = false;
        try {
            testSeq = RandSeq.generateRandomSequence(-1);
        } catch (Exception e) {
            successfulException = true;
        }
        assertTrue(successfulException);
    }

    /**
     * Test of generateRandomSequence method, of class RandSeq.
     */
    @org.junit.Test
    public void testGenerateRandomSequence_floatArr_int() throws Exception {
        String testSeq = RandSeq.generateRandomSequence(new float[]{.20f, .40f, .0f, .40f}, 5);
        assertEquals(5, testSeq.length());
        for (int i = 0; i < testSeq.length(); i++) {
            assertTrue(nucCharacters.contains(testSeq.charAt(i)));
            if (testSeq.charAt(i) == 'G') {
                fail("There should be no chance of generating a G!");
            }
        }
        testSeq = RandSeq.generateRandomSequence(new float[]{0.072658f, 0.024692f, 0.050007f, 0.061087f,
		        0.041774f, 0.071589f, 0.023392f, 0.052691f, 0.063923f,
		        0.089093f, 0.023150f, 0.042931f, 0.052228f, 0.039871f,
		        0.052012f, 0.073087f, 0.055606f, 0.063321f, 0.012720f,
		        0.032955f}, 30);
        assertEquals(30, testSeq.length());
        for (int i = 0; i < testSeq.length(); i++) {
            assertTrue(aminoCharacters.contains(testSeq.charAt(i)));
        }
        try {
            testSeq = RandSeq.generateRandomSequence(new float[]{.20f, .40f, .0f, .40f}, -1); //Negative length
            fail("Should have caught the negative length!");
        } catch (Exception e) {
        }
        try {
            testSeq = RandSeq.generateRandomSequence(new float[]{.80f, .40f, .0f, .40f}, 20); //Probabilities don't sum to 1
            fail("Should have caught the probabilities not summing to 1!");
        } catch (Exception e) {
        }
        try {
            testSeq = RandSeq.generateRandomSequence(new float[]{-.80f, .40f, .0f, .40f}, 20); //Probabilities include a negative
            fail("Should have caught the negative probability!");
        } catch (Exception e) {
        }
        try {
            testSeq = RandSeq.generateRandomSequence(new float[]{.80f, .40f}, 20); //Probabilities don't match a default length
            fail("Should have caught the non-default probability array length!");
        } catch (Exception e) {
        }
    }

    /**
     * Test of generateRandomSequence method, of class RandSeq.
     */
    @org.junit.Test
    public void testGenerateRandomSequence_3args() throws Exception {
        char[] customCharsAlphabet = new char[]{'X', 'Y', 'Z'};
        ArrayList<Character> customChars = new ArrayList<>();
        for (int i = 0; i < customCharsAlphabet.length; i++) {
            customChars.add(customCharsAlphabet[i]);
        }
        
        
        String testSeq = RandSeq.generateRandomSequence(customCharsAlphabet, new float[]{.20f, .40f, .40f}, 5);
        assertEquals(5, testSeq.length());
        for (int i = 0; i < testSeq.length(); i++) {
            assertTrue(customChars.contains(testSeq.charAt(i)));
        }
        try {
            testSeq = RandSeq.generateRandomSequence(customCharsAlphabet, new float[]{.20f, .40f, .40f}, -1); //Negative length
            fail("Should have caught the negative length!");
        } catch (Exception e) {
        }
        try {
            testSeq = RandSeq.generateRandomSequence(customCharsAlphabet, new float[]{.80f, .40f, .40f}, 5); //Probabilities don't sum to 1
            fail("Should have caught the probabilities not summing to 1!");
        } catch (Exception e) {
        }
        try {
            testSeq = RandSeq.generateRandomSequence(customCharsAlphabet, new float[]{-.20f, .40f, .40f}, 5);//Probabilities include a negative
            fail("Should have caught the negative probability!");
        } catch (Exception e) {
        }
        try {
            testSeq = RandSeq.generateRandomSequence(customCharsAlphabet, new float[]{.20f, .40f, .20f, .20f}, 5);//Probabilities don't match the alphabet length
            fail("Should have caught the non-matching probability and alphabet array length!");
        } catch (Exception e) {
        }
    }
    
}
