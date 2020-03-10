import java.sql.PseudoColumnUsage;

public class app {
    public static void main(String[] args){
        if(args.length<2){
            usage();
            return;
        }
        int n=Integer.parseInt(args[0]);
        int k=Integer.parseInt(args[1]);
        SchurDimacsCreator creator = new SchurDimacsCreator();
        String result = creator.createDimacs(n,k);
        System.out.println(result);
    }
    static void usage(){
        System.err.println("Usage : java -jar SchurDimacsCreator n k ");
    }
}
