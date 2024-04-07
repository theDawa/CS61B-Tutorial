/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = Integer.MIN_VALUE;
        for(String i : asciis){
            max = max > i.length() ? max : i.length();
        }

        int[] counts = new int[256];
        String[] sorted2 = new String[asciis.length];
        while(max > 0){
            for (String i : asciis) {
                int j = 256 - (int)i.charAt(max-1);
                counts[j]++;
            }
            int[] starts = new int[256];
            int pos = 0;
            for (int i = 0; i < starts.length; i += 1) {
                starts[i] = pos;
                pos += counts[i];
            }
            for (int i = asciis.length-1; i >= 0; i--) {
                int item = 256 - (int)asciis[i].charAt(max-1);
                int place = starts[item];
                sorted2[place] = asciis[i];
                starts[item] += 1;
            }
            max--;
        }
        return sorted2;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
