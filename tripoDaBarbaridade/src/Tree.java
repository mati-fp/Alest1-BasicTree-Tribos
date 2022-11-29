public class Tree {
    
    //NODO BASICO PARA A CONSTRUÇÃ DA ÁRVORE
    private class TreeNode{
        public String nome;
        public TreeNode father;
        private TreeNode [] children;
        private int nChild;
        public int terras;
        public int nLinhagem;
        public String espacos = "";
        public int heranca;

        public TreeNode (String guerreiro, Integer element){
            father=null;
            children=new TreeNode[10];
            nome=guerreiro;
            terras=element;
            nChild=0;
        }
        public void addSubtree (TreeNode n){
            if(nChild==children.length)
                grow();
            children[nChild] = n;
            n.father=this;
            nChild++;
            n.nLinhagem = this.nLinhagem + 1;
            n.espacos = this.espacos + " ";
        }
        private void grow(){
            TreeNode aux [] = new TreeNode[children.length*2];
            for(int i=0; i<children.length; i++)
              aux[i]=children[i];
            children=aux;            
        }
        public void passaHenanca(){
            heranca = terras/nChild;
            for (int i= 0; i < nChild; i++){
                children[i].terras += heranca;
            }
            terras = 0;
        }

        // busca subtree pelo indice dentro da lista de filhos
        public TreeNode getSubtree(int i){
            if(i>=0 && i<nChild)
              return children[i];
            return null;
        }
        //retorna o número de filhos
        public int getSubtreeSize(){
            return nChild;
        }  
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // ATRIBUTOS E MÉTODOS DA ÁRVORE
        private TreeNode root;
        private int nElements;
        private TreeNode descendenteMaisRico;

        public Tree(){
            this.root=null;
            this.nElements=0;
        }

        // método privado elaborado na versão 0.2
        private TreeNode searchNode(String value, TreeNode ref){

            if(ref!=null){
                if(ref.nome.equals(value))
                    return ref;
                else{
                    TreeNode aux=null;
                    for(int i=0; i<ref.getSubtreeSize(); i++){
                        aux=searchNode(value, ref.getSubtree(i));
                        if(aux != null){
                            return aux;
                        }
                    }
                    return null;
                }
            }
            else
                return null;
    
        }
    
        //insere o elemento e como filho de father
        // Versao 0.2 -> Permite a inclusão de mais níveis na árvore
        public boolean add(String father, String son, Integer terras){
            TreeNode aux;
            if(nElements==0){
                this.root=new TreeNode(son, terras);
                descendenteMaisRico = root;
                root.nLinhagem = 1;
            }
            else{
                aux = searchNode(father, root);
                if(aux==null)
                    return false;
                else
                    aux.addSubtree(new TreeNode(son, terras));
                    
            }
            nElements++;
    
            return true;
        }

        public void distribuiHeranca(){
            
            herancaDoPapai(root);

        }

        public boolean herancaDoPapai(TreeNode VeioDaLancha){

            if (VeioDaLancha.getSubtreeSize() > 0){
                for (int i =0; i < VeioDaLancha.getSubtreeSize(); i++){
                    VeioDaLancha.passaHenanca();
                    if (VeioDaLancha.getSubtree(i).getSubtreeSize() > 0)
                        herancaDoPapai(VeioDaLancha.getSubtree(i));
                }
            }
            return true;
        }
    
        //remove todos os elementos da árvore
        public void clear(){
            //requer navegação
            //ou solução baseada em garbage collector
            this.root=null;
            this.nElements=0;
        }
    
    
        public void doTheString(){
    
            //printValueResult(root, root);
            //n tá funcionando direito essa merda
    
        }
    
        private void printValueResult(TreeNode ref, TreeNode refEspaços){
    
            if(ref!=null){
                if (ref.nome.equals(refEspaços.nome))
                    System.out.print(ref.nome+" Filhos: ");
                else {
                    System.out.print(ref.espacos+ref.nome+" Filhos: ");
                }
                System.out.print(" [ ");
                for(int i=0; i<ref.getSubtreeSize(); i++){
                    if (ref.getSubtreeSize() == 0)
                        System.out.print(" ] ");
                    printValueResult(ref.getSubtree(i), ref);
                    
                }
                
            }
    
        }

        public void printDescentendeMaisRico(){
            printDescentendeMaisRico(root);
            System.out.println();
            System.out.println("Descentende mais rico: "+descendenteMaisRico.nome+ " - Quantidade de Terras: "+descendenteMaisRico.terras);
        }

        private void printDescentendeMaisRico(TreeNode ref){

            if (ref != null){
                if (ref.getSubtreeSize() == 0){
                    if (ref.nLinhagem > descendenteMaisRico.nLinhagem){
                        descendenteMaisRico = ref;
                    }
                    else if (ref.nLinhagem == descendenteMaisRico.nLinhagem){
                        if (ref.terras >= descendenteMaisRico.terras){
                            descendenteMaisRico = ref;
                        }
                    }
                }
                for (int i = 0; i < ref.getSubtreeSize(); i++){
                    printDescentendeMaisRico(ref.getSubtree(i));
                }
                
            }

        }
}
