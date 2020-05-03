public class triangle_1b {
    public static void drawTriangle(int N) {
        String star = "";
        int i = 0;
        while (i < N) {
            star = star + "*";
            System.out.println(star);
            i = i + 1;
        }
    }
    
    public static void main(String[] args) {
        drawTriangle(10);
    }
}