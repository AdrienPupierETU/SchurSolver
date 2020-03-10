public class SchurDimacsCreator {
    public  String createDimacs(int n, int k){
        int nbVar=n*k; //n*k
        int nbClause=0;
        StringBuilder stringBuilder= new StringBuilder();

        //Au moins dans une boite
        int varcount=1;
        for(int i=0;i<n;i++){ //boule i
            StringBuilder clauseLoc= new StringBuilder();
            for(int j=0;j<k;j++){ //boite j
                clauseLoc.append(varcount).append(" ");
                varcount++;
            }
            clauseLoc.append(0).append("\n");
            stringBuilder.append(clauseLoc.toString());
            nbClause++;
        }
        //Au plus dans une boite
        for(int i=1;i<=nbVar;i++){
            for(int j=1;j<k;j++){
                StringBuilder clauseLoc= new StringBuilder();
                int res1=(int)Math.ceil((double)i/k); // valeur de la boule de depart
                int res2=(int)Math.ceil((double)(i+j)/k); // valeur de la boule plus le nombre de boite que l'on parcours
                if(res1!=res2){  // si ce n'est pas la même boule.
                    break;
                }
                clauseLoc.append(-i).append(" ").append(-(i + j)).append(" ");
                clauseLoc.append(0).append("\n");
                stringBuilder.append(clauseLoc.toString());
                nbClause++;
            }

        }
        //x+y=z interdit dans une boite
        for(int i=1;i<=nbVar;i++){
            for(int j=1;j<=nbVar;j++){
                int x=(int)Math.ceil((double)i/k);
                int y=(int)Math.ceil((double)j/k);
                if(x==y){
                    continue;
                }
                int z=x+y;

                if(z<=n){
                    for(int l=0;l<k;l++){ // bannir cette combinaison dans chaque boite
                        StringBuilder clauseLoc = new StringBuilder();
                        int xn=(x*k)-(k-1); //boule x dans boite 1
                        int yn=(y*k)-(k-1); //boule y dans boite 1
                        int zn=(z*k)-(k-1); //boule z dans boite 1
                        clauseLoc.append(-(xn+l)).append(" ").append(-(yn+l)).append(" ");

                        clauseLoc.append(-(zn+l)).append(" ");
                        clauseLoc.append(0).append("\n");
                        stringBuilder.append(clauseLoc.toString()); // on ajoute a la chaine principale
                        nbClause++;
                    }
                }
            }
        }
        stringBuilder.insert(0,"p cnf "+nbVar +" "+nbClause+" \n");
        return stringBuilder.toString();
    }
}
