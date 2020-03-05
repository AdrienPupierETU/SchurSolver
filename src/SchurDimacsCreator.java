public class SchurDimacsCreator {
    public static String createDimacs(int n, int k){
        int nbVar=n*k; //n*k
        int nbClause=0;
        StringBuilder stringBuilder= new StringBuilder();

        //Au moins dans une boite
        int varcount=1;
        for(int i=0;i<n;i++){ //boule i
            StringBuilder clauseLoc= new StringBuilder();
            for(int j=0;j<k;j++){ //boite j
                clauseLoc.append(varcount+" ");
                varcount++;
            }
            clauseLoc.append(0+"\n");
            stringBuilder.append(clauseLoc.toString());
            nbClause++;
        }
        //Au plus dans une boite
        for(int i=1;i<=nbVar;i++){
            for(int j=1;j<k;j++){
                StringBuilder clauseLoc= new StringBuilder();
                double res1=(double)i/k;
                double res2=(double)(i+j)/k;
                //ajouter if si res1 et res2 ne sont pas entre les mêmes entiers alors break;
                if(res1==Math.floor(res1)){
                    break;
                }
                if(Math.floor(res1)!=Math.floor(res2-0.000001)){
                    break;
                }
                clauseLoc.append(-i+" "+-(i+j)+" ");
                clauseLoc.append(0+"\n");
                stringBuilder.append(clauseLoc.toString());
                nbClause++;
            }

        }
        //x+y=z interdit dans une boite


        stringBuilder.insert(0,"p cnf "+nbVar +" "+nbClause+" \n");
        return stringBuilder.toString();
    }
}
