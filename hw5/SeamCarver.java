import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
public class SeamCarver {
    private int width;
    private int height;
    private Picture picture;
    private int[][] RGB;
    public SeamCarver(Picture picture){
        this.picture = picture;
        width = picture.width();
        height = picture.height();
        RGB = new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                RGB[i][j] = picture.getRGB(j, i);
            }
        }

    }

    public int[] getRGB_value(int x, int y){
        int Red =  (RGB[y][x] & 0xff0000) >> 16;
        int Green = (RGB[y][x] & 0xff00) >> 8;
        int Blue = RGB[y][x] & 0xff;
        int[] RGB_array = new int[3];
        RGB_array[0] = Red;
        RGB_array[1] = Green;
        RGB_array[2] = Blue;
        return RGB_array;
    }
    public Picture picture(){
        // current picture
        return picture;
    }
    public int width(){
        // width of current picture
        return width;
    }
    public int height(){
        // height of current picture
        return height;
    }
    public double energy(int x, int y){
        // energy of pixel at column x and row y
        if(y < 0 || y > height-1 || x < 0 || x > width-1){
            throw new IndexOutOfBoundsException();
        }
        int energy_x = 0;
        int energy_y = 0;
        int energy = 0;
        if(x != 0 && x != width-1 && y != 0 && y != height-1 ){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(x+1, y)[i] - getRGB_value(x-1, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y+1)[i] - getRGB_value(x, y-1)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        if(x == 0 && y != 0 && y != height - 1){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(x+1, y)[i] - getRGB_value(width-1, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y+1)[i] - getRGB_value(x, y-1)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        if( x == width-1 && y != 0 && y != height-1){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(x-1, y)[i] - getRGB_value(0, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y+1)[i] - getRGB_value(x, y-1)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        if( x != width-1 && y == 0 && x != 0){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(x-1, y)[i] - getRGB_value(x+1, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y+1)[i] - getRGB_value(x, height-1)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        if( x != width-1 && y == height-1 && x != 0){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(x-1, y)[i] - getRGB_value(x+1, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y-1)[i] - getRGB_value(x, 0)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        if( x == 0 && y == 0){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(width-1, y)[i] - getRGB_value(x+1, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y+1)[i] - getRGB_value(x, height-1)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        if( x == width-1 && y == 0){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(x-1, y)[i] - getRGB_value(0, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y+1)[i] - getRGB_value(x, height-1)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        if( x == 0 && y == height-1){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(width-1, y)[i] - getRGB_value(x+1, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y-1)[i] - getRGB_value(x, 0)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        if( x == width-1 && y == height-1){
            for(int i = 0; i < 3; i++){
                energy_x = getRGB_value(x-1, y)[i] - getRGB_value(0, y)[i];
                energy += energy_x*energy_x;
                energy_y = getRGB_value(x, y-1)[i] - getRGB_value(x, 0)[i];
                energy += energy_y*energy_y;
            }
            return energy;
        }
        return 0.0;

    }

    public double[][] costArray_vertical(){
        double[][] costArray = new double[height][width];
        for(int i = 0; i < width; i++){
            costArray[0][i] = energy(i, 0);
        }
        for(int i = 1; i < height; i++){
            for(int j = 0; j < width; j++){
                if(j == 0){
                    costArray[i][j] =  Math.min(costArray[i-1][j], costArray[i-1][j+1]) + energy(j, i);
                } else if (j == width-1) {
                    costArray[i][j] = Math.min(costArray[i-1][j], costArray[i-1][j-1]) + energy(j, i);
                }else{
                    costArray[i][j] = Math.min(Math.min(costArray[i-1][j], costArray[i-1][j+1]), costArray[i-1][j-1]) + energy(j, i);
                }
            }
        }
        return costArray;
    }

    public double[][] costArray_horizontal(){
        double[][] costArray = new double[height][width];
        for(int i = 0; i < height; i++){
            costArray[i][0] = energy(0, i);
        }
        for(int i = 1; i < width; i++){
            for(int j = 0; j < height; j++){
                if(j == 0){
                    costArray[j][i] =  Math.min(costArray[j][i-1], costArray[j+1][i-1]) + energy(i, j);
                } else if (j == height-1) {
                    costArray[j][i] = Math.min(costArray[j][i-1], costArray[j-1][i-1]) + energy(i, j);
                }else{
                    costArray[j][i] = Math.min(Math.min(costArray[j][i-1], costArray[j-1][i-1]), costArray[j+1][i-1]) + energy(i, j);
                }
            }
        }
        return costArray;
    }


    public int[] findHorizontalSeam(){
        // sequence of indices for horizontal seam
        double[][] costarray = costArray_horizontal();
        int[] a = new int[width];
        for(int i = 0; i < width; i++){
            int k = 0;
            double min = Double.MAX_VALUE;
            for(int j = 0; j < height; j++){
                if(i == 0 && costarray[j][i] < min){
                    k = j;
                    min = costarray[j][i];
                }
                if(i != 0 && costarray[j][i] < min && (j == a[i-1]-1 || j == a[i-1]+1 || j == a[i-1])){
                    k = j;
                    min = costarray[j][i];
                }

            }
            a[i] = k;
        }
        return a;
    }
    public int[] findVerticalSeam(){
        // sequence of indices for vertical seam
        double[][] costarray = costArray_vertical();
        int[] a = new int[height];
        for(int i = 0; i < height; i++){
            int k = 0;
            double min = Double.MAX_VALUE;
            for(int j = 0; j < width; j++){
                if(i == 0 && costarray[i][j] < min){
                    k = j;
                    min = costarray[i][j];
                }
                if(i != 0 && costarray[i][j] < min && (j == a[i-1]-1 || j == a[i-1]+1 || j == a[i-1]) ){
                    k = j;
                    min = costarray[i][j];
                }

            }
            a[i] = k;
        }
        return a;
    }
    public  void removeHorizontalSeam(int[] seam){
        // remove horizontal seam from picture
        for(int var2 = 0; var2 < seam.length - 2; ++var2) {
            if (Math.abs(seam[var2] - seam[var2 + 1]) > 1) {
                throw new IllegalArgumentException("Invalid seam, consecutive horizontal indices are greater than one apart.");
            }
        }
        SeamRemover.removeHorizontalSeam(picture, seam);
    }

    public  void removeVerticalSeam(int[] seam){
        // remove vertical seam from picture
        for(int var2 = 0; var2 < seam.length - 2; ++var2) {
            if (Math.abs(seam[var2] - seam[var2 + 1]) > 1) {
                throw new IllegalArgumentException("Invalid seam, consecutive vertical indices are greater than one apart.");
            }
        }
        SeamRemover.removeVerticalSeam(picture, seam);
    }

}
