/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.lab.pkg1;

import java.util.Random;

/**
 *
 * @author pmele
 */
public class RandSeq {
    private static char[] nucAlphabet = new char[] {'A', 'C', 'G', 'T'};
    private static char[] aminoAlphabet = new char[] {'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'Y'};
    
    public static String generateRandomSequence(int length) throws Exception { //Default, even spread of nucleotides
        return generateRandomSequence(nucAlphabet, new float[] {.25f, .25f, .25f, .25f}, length);
    }
    
    public static String generateRandomSequence(float[] probWeight, int length) throws Exception {
        if (probWeight.length == 20) {
            return generateRandomSequence(aminoAlphabet, probWeight, length);
        } else {
            return generateRandomSequence(nucAlphabet, probWeight, length);
        }
    }
    
    public static String generateRandomSequence(char[] alphabet, float[] probWeight, int length) throws Exception {
        if (length < 1) {
            throw new Exception("Length must be above 0.");
        }
        if (probWeight.length != alphabet.length) {
            throw new Exception("Must include weights for each character.");
        }
        float probSum = 0.0f;
        float[] probBounds = new float[probWeight.length];
        for (int i = 0; i < probWeight.length; i++) {
            probSum += probWeight[i];
            probBounds[i] = probSum;
        }
        if (Math.abs(probSum - 1.0f) >= 0.01) {
            throw new Exception("Make sure your probabilities add up to an event space of 1. Currently they sum to " + probSum);
        }
        
        StringBuilder sequence = new StringBuilder("");
        Random rand = new Random();
        boolean failure;
        
        for (int seqPos = 0; seqPos < length; seqPos++){
            float key = rand.nextFloat();
            failure = true;
            for (int i = 0; i < probBounds.length; i++) {
                if (key <= probBounds[i]) {
                    sequence.append(alphabet[i]);
                    failure = false;
                    break;
                }
            }
            if (failure) {
                //Shouldn't be reached if the probability check works, exists just in case.
                throw new Exception("Probability error! probSum = " + probSum + "\tkey = " + key);
            }
            
        }
        
        return sequence.toString();
    }
}
