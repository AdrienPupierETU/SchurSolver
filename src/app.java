public class app {
    public static void main(String[] args){
        String result = SchurDimacsCreator.createDimacs(20,3);
        System.out.println(result);
    }
}
